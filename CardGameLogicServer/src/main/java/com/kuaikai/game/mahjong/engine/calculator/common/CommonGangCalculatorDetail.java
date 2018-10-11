package com.kuaikai.game.mahjong.engine.calculator.common;

import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.JieSuan;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.constants.PlayerStat;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;

public class CommonGangCalculatorDetail extends CommonCalculatorDetail {

	protected BaseOperation operation;
	
	public CommonGangCalculatorDetail(BaseOperation operation, int rate, boolean bankerDouble, boolean zimoDouble) {
		super(operation.getRoom(), rate, bankerDouble, zimoDouble, false);
		this.operation = operation;
		
		this.addWinner(operation.getPlayer());
		initPayer();
		initMainType();
		this.setPriority(Priority.GANG);
	}
	
	private void initPayer() {
		switch(operation.getOperType()) {
		case OperType.AN_GANG :
		case OperType.BU_GANG :
			this.setPayerAll();
			break;
		case OperType.DIAN_GANG :
			if(room.getCreateRoomParam().getSettingBool(GameSetting.DIAN_GANG_PAYER_ALL))	// 点杠三家付
				this.setPayerAll();
			else {
				this.setPayerDianGang();
				if(room.getCreateRoomParam().getSettingBool(GameSetting.DIAN_GANG_PAY_ALL)) this.setPayAll();	// 点杠包三家
			}
			break;
		default :
			break;
		}
	}
	
	private void initMainType() {
		boolean mingGang = room.getCreateRoomParam().getSettingBool(GameSetting.JIE_SUAN_MING_GANG);
		switch(operation.getOperType()) {
		case OperType.AN_GANG :
			setMainType(JieSuan.AN_GANG);
			break;
		case OperType.BU_GANG :
			if(mingGang)
				setMainType(JieSuan.MING_GANG);
			else
				setMainType(JieSuan.BU_GANG);
			break;
		case OperType.DIAN_GANG :
			if(mingGang)
				setMainType(JieSuan.MING_GANG);
			else
				setMainType(JieSuan.DIAN_GANG);
			break;
		default :
			setMainType(JieSuan.GANG);
			break;
		}
	}
	
	public BaseOperation getOperation() {
		return operation;
	}
	
	/**
	 * 设置点杠者支付
	 */
	public void setPayerDianGang() {
		this.clearLosers();
		this.addLoser(operation.getPrePlayer());
	}
	
	@Override
	public boolean calc() {
		boolean ret = super.calc();
		if (ret) {
			switch(operation.getOperType()) {
			case OperType.DIAN_GANG :
			case OperType.BU_GANG :
				operation.getPlayer().getMjPlayer().getGameResult().increaseStat(PlayerStat.MING_GANG);
				break;
			case OperType.AN_GANG :
				operation.getPlayer().getMjPlayer().getGameResult().increaseStat(PlayerStat.AN_GANG);
				break;
			}
		}
		return ret;
	}
	
}
