package com.kuaikai.game.mahjong.engine.calculator.common;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.calculator.Calculator;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;
import com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan;
import com.kuaikai.game.mahjong.msg.pb.PlayerStatTypePB.PlayerStatType;

public class CommonHuCalculatorDetail extends CommonCalculatorDetail {

	protected HuOperation operation;
	
	public CommonHuCalculatorDetail(HuOperation operation, int rate, boolean bankerDouble, boolean zimoDouble) {
		super(operation.getDesk(), rate, bankerDouble, zimoDouble, operation.isZimo());
		this.operation = operation;
		this.setMainType(zimo?JieSuan.ZI_MO_VALUE:JieSuan.HU_PAI_VALUE);
		this.setPriority(Priority.HU);
		this.addWinner(operation.getPlayer());
		
		if(this.isZimo()) {
			setPayerAll();
		} else if(operation.isQiangGang()) {
			if(desk.getSetting().getBool(CardGameSetting.QIANG_GANG_PAYER_ALL)) {
				setPayerAll();
			} else {
				setPayerQiangGang();
				if(desk.getSetting().getBool(CardGameSetting.QIANG_GANG_PAY_ALL)) this.setPayAll();	// 抢杠胡包三家
			}
		} else {
			if (desk.getSetting().getBool(CardGameSetting.DIAN_PAO_PAYER_ALL))
				setPayerAll();
			else {
				setPayerDianPao();
				if (desk.getSetting().getBool(CardGameSetting.DIAN_PAO_PAY_ALL)) this.setPayAll(); // 点炮包三家
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
			Calculator calculator = desk.getEngine().getCalculator();
			if(this.isZimo()) {
				calculator.getPlayerSetResult(operation.getPlayer()).setZimo(true);
				operation.getPlayer().getGameResult().increaseStat(PlayerStatType.ZI_MO);
			} else {
				calculator.getPlayerSetResult(operation.getPlayer()).setJiePao(true);
				operation.getPlayer().getGameResult().increaseStat(PlayerStatType.JIE_PAO);
				
				calculator.getPlayerSetResult(operation.getPrePlayer()).setDianPao(true);
				operation.getPrePlayer().getGameResult().increaseStat(PlayerStatType.DIAN_PAO);
			}
		}
		return ret;
	}
	
}
