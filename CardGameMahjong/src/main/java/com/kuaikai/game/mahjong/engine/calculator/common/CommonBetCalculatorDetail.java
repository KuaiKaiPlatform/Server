package com.kuaikai.game.mahjong.engine.calculator.common;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.calculator.ScoreDetail;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;
import com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan;

/*
 * 下注分计算
 */
public class CommonBetCalculatorDetail extends CommonHuCalculatorDetail {

	private boolean winnerBetOnly;	// 只计算赢家下注分
	
	public CommonBetCalculatorDetail(HuOperation operation, int rate, boolean bankerDouble, boolean zimoDouble) {
		super(operation, rate, bankerDouble, zimoDouble);
		this.setMainType(JieSuan.BET_SCORE_VALUE);
	}
	
	@Override
	public boolean calc() {
		MahjongPlayer winner = this.getSingleWinner();
		int winnerBet = this.getBet(winner);
		int total = 0;
		boolean winnerBanker = winner.isBanker();
		int baseRate = desk.getSetting().getInt(CardGameSetting.BASE_RATE);
		
		for (MahjongPlayer loser : this.getLosers()) {
			int xianRate = baseRate * (winnerBetOnly?winnerBet:(winnerBet + this.getBet(loser)));
			if(zimo && zimoDouble) xianRate *= 2;
			int lost = (bankerDouble && (winnerBanker || loser.isBanker()))?xianRate*2:xianRate;
			total += lost;
			
			if(lost > 0) {
				ScoreDetail scoreDetail = new ScoreDetail(mainType, lost, loserDisplay, toPay);
				scoreDetail.addSubTypes(subTypes);
				addUserLost(loser.getId(), scoreDetail);
			}
		}
		
		if(total > 0) {
			ScoreDetail scoreDetail = new ScoreDetail(mainType, total, winnerDisplay, toPay);
			scoreDetail.addSubTypes(subTypes);
			addUserGain(winner.getId(), scoreDetail);			
		}
		
		return true;
	}
	
	/*
	 * 返回玩家下注数量，固定注直接返回
	 */
	private int getBet(MahjongPlayer player) {
		//return rate > 0?rate:Math.max(0, player.getSetAttrs().getInt(MahjongPlayer.SetAttr.BET));
		return rate > 0?rate:Math.max(0, player.getBet());
	}

	public void setWinnerBetOnly(boolean winnerBetOnly) {
		this.winnerBetOnly = winnerBetOnly;
	}
	
}
