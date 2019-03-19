package com.kuaikai.game.mahjong.engine.calculator.common;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan;
import com.kuaikai.game.mahjong.msg.pb.PlayerStatTypePB.PlayerStatType;

public class CommonGangCalculatorDetail extends CommonCalculatorDetail {

	protected BaseOperation operation;
	
	public CommonGangCalculatorDetail(BaseOperation operation, int rate, boolean bankerDouble, boolean zimoDouble) {
		super(operation.getDesk(), rate, bankerDouble, zimoDouble, false);
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
			if(desk.getSetting().getBool(CardGameSetting.DIAN_GANG_PAYER_ALL))	// 点杠三家付
				this.setPayerAll();
			else {
				this.setPayerDianGang();
				if(desk.getSetting().getBool(CardGameSetting.DIAN_GANG_PAY_ALL)) this.setPayAll();	// 点杠包三家
			}
			break;
		default :
			break;
		}
	}
	
	private void initMainType() {
		boolean mingGang = desk.getSetting().getBool(CardGameSetting.JIE_SUAN_MING_GANG);
		switch(operation.getOperType()) {
		case OperType.AN_GANG :
			setMainType(JieSuan.AN_GANG__VALUE);
			break;
		case OperType.BU_GANG :
			if(mingGang)
				setMainType(JieSuan.MING_GANG__VALUE);
			else
				setMainType(JieSuan.BU_GANG__VALUE);
			break;
		case OperType.DIAN_GANG :
			if(mingGang)
				setMainType(JieSuan.MING_GANG__VALUE);
			else
				setMainType(JieSuan.DIAN_GANG__VALUE);
			break;
		default :
			setMainType(JieSuan.GANG__VALUE);
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
				operation.getPlayer().getGameResult().increaseStat(PlayerStatType.MING_GANG);
				break;
			case OperType.AN_GANG :
				operation.getPlayer().getGameResult().increaseStat(PlayerStatType.AN_GANG);
				break;
			}
		}
		return ret;
	}
	
}
