package com.kuaikai.game.mahjong.engine.calculator.common;

import com.kuaikai.game.mahjong.engine.calculator.Calculator;
import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.JieSuan;
import com.kuaikai.game.mahjong.engine.constants.PlayerStat;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;

public class CommonHuCalculatorDetail extends CommonCalculatorDetail {

	protected HuOperation operation;
	
	public CommonHuCalculatorDetail(HuOperation operation, int rate, boolean bankerDouble, boolean zimoDouble) {
		super(operation.getRoom(), rate, bankerDouble, zimoDouble, operation.isZimo());
		this.operation = operation;
		
		this.setMainType(zimo?JieSuan.ZI_MO:JieSuan.HU_PAI);
		this.setPriority(Priority.HU);
		this.addWinner(operation.getPlayer());
		
		if(this.isZimo()) {
			setPayerAll();
		} else if(operation.isQiangGang()) {
			if(room.getCreateRoomParam().getSettingBool(GameSetting.QIANG_GANG_PAYER_ALL)) {
				setPayerAll();
			} else {
				setPayerQiangGang();
				if(room.getCreateRoomParam().getSettingBool(GameSetting.QIANG_GANG_PAY_ALL)) this.setPayAll();	// 抢杠胡包三家
			}
		} else {
			if (room.getCreateRoomParam().getSettingBool(GameSetting.DIAN_PAO_PAYER_ALL))
				setPayerAll();
			else {
				setPayerDianPao();
				if (room.getCreateRoomParam().getSettingBool(GameSetting.DIAN_PAO_PAY_ALL)) this.setPayAll(); // 点炮包三家
			}
		}
	}
	
	/**
	 * 设置点炮者支付
	 */
	public void setPayerDianPao() {
		this.setSingleLoser(operation.getPrePlayer());
	}

	/**
	 * 设置被抢杠者支付
	 */
	public void setPayerQiangGang() {
		setPayerDianPao();
	}
	
	@Override
	public boolean calc() {
		boolean ret = super.calc();
		if(ret) {
			Calculator calculator = room.getEngine().getCalculator();
			if(this.isZimo()) {
				calculator.getPlayerSetResult(operation.getPlayer()).setZimo(true);
				operation.getPlayer().getMjPlayer().getGameResult().increaseStat(PlayerStat.ZIMO);
			} else {
				calculator.getPlayerSetResult(operation.getPlayer()).setJiePao(true);
				operation.getPlayer().getMjPlayer().getGameResult().increaseStat(PlayerStat.JIE_PAO);
				
				calculator.getPlayerSetResult(operation.getPrePlayer()).setDianPao(true);
				operation.getPrePlayer().getMjPlayer().getGameResult().increaseStat(PlayerStat.DIAN_PAO);
			}
		}
		return ret;
	}
	
}
