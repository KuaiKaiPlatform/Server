package com.kuaikai.game.mahjong.engine.calculator.common;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.kuaikai.game.common.play.GamePlayer;
import com.kuaikai.game.mahjong.engine.calculator.CalculatorDetail;
import com.kuaikai.game.mahjong.engine.calculator.ScoreDetail;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

/*
 * 通用的结算项，计算winner和loser的得失分，支持多付一、一付一或一付多，不支持多付多
 */
public class CommonCalculatorDetail extends CalculatorDetail implements Comparable<CommonCalculatorDetail> {

	protected MahjongDesk desk;
	// 赢家
	protected List<MahjongPlayer> winners = new LinkedList<MahjongPlayer>();
	// 输家
	protected List<MahjongPlayer> losers = new LinkedList<MahjongPlayer>();
	// 未翻倍的输赢分
	protected int rate;
	// 庄家翻倍
	protected boolean bankerDouble = false;
	// 自摸翻倍
	protected boolean zimoDouble = false;
	// 是否自摸
	protected boolean zimo = false;
	// 本项是否显示在赢家结算栏
	protected boolean winnerDisplay = true;
	// 本项是否显示在输家结算栏
	protected boolean loserDisplay = true;
	// 本项是否计入最终分数
	protected boolean toPay = true;
	
	protected static final int PAY_ALL = 1;			// 一名输家支付时，支付所有人的输分
	protected static final int PAY_SELF = 0;		// 支付自己的
	protected int payStyle;
	
	// 结算项主类
	protected int mainType;

	// 结算项子类
	protected List<Integer> subTypes;
	
	// 优先级（按底数玩法时，优先级高的先计算）
	protected int priority;
	
	public CommonCalculatorDetail(MahjongDesk desk, int rate, boolean bankerDouble, boolean zimoDouble, boolean zimo) {
		this.desk = desk;
		this.rate = rate;
		this.bankerDouble = bankerDouble;
		this.zimoDouble = zimoDouble;
		this.zimo = zimo;
	}

	public void addWinner(MahjongPlayer winner) {
		winners.add(winner);
	}
	
	public MahjongPlayer getSingleWinner() {
		return winners.isEmpty()?null:winners.get(0);
	}
	
	public void clearLosers() {
		losers.clear();
	}
	
	public void addLoser(MahjongPlayer loser) {
		//if(logger.isDebugEnabled()) logger.debug("CommonCalculatorDetail.addPayer@payerId=" + payer.getid() + ",mainType=" + mainType);
		losers.add(loser);
	}
	
	public void setSingleLoser(MahjongPlayer loser) {
		this.clearLosers();
		this.addLoser(loser);
	}
	
	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getRate() {
		return rate;
	}

	public int getMainType() {
		return mainType;
	}

	public void setMainType(int mainType) {
		this.mainType = mainType;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isWinnerDisplay() {
		return winnerDisplay;
	}

	public void setWinnerDisplay(boolean winnerDisplay) {
		this.winnerDisplay = winnerDisplay;
	}

	public boolean isLoserDisplay() {
		return loserDisplay;
	}

	public void setLoserDisplay(boolean loserDisplay) {
		this.loserDisplay = loserDisplay;
	}

	public boolean isToPay() {
		return toPay;
	}

	public void setToPay(boolean toPay) {
		this.toPay = toPay;
	}

	public boolean isZimo() {
		return zimo;
	}

	public void setZimo(boolean zimo) {
		this.zimo = zimo;
	}

	/**
	 * 设置所有人都要支付
	 */
	public void setPayerAll() {
		losers.clear();
		for(GamePlayer p : desk.getAllPlayers()) {
			MahjongPlayer player = (MahjongPlayer)p;
			if(winners.contains(player)) continue;
			losers.add(player);
		}
	}

	/**
	 * 设置支付自己
	 */
	public void setPaySelf() {
		this.payStyle = PAY_SELF;
	}
	
	/**
	 * 设置支付所有人
	 */
	public void setPayAll() {
		this.payStyle = PAY_ALL;
	}
	
	@Override
	public boolean calc() {
		if(invalid) return false;
		if(winners.isEmpty() || losers.isEmpty()) {
			//logger.error("CommonCalculatorDetail.cacl@winners or payers empty|room=%d|mainType=%d", room.getRoomid(), mainType);
			return false;
		}

		if(winners.size() != 1 && losers.size() != 1) {
			//logger.error("CommonCalculatorDetail.cacl@Neither winners nor payers size is one|room=%d|mainType=%d", room.getRoomid(), mainType);
			return false;
		}
		
		// 不算分，只显示结算项
		if(!toPay) {
			return caclUnPay();
		}
		
		// 算分
		if(winners.size() > 1) {	// 一付多
			return calcMultipleWinners();
		} else if(losers.size() > 1) {	// 多付一
			return calcMultipleLosers();
		}
		
		// 一付一
		return calcOne2One();
	}
	
	/**
	 * 不算分，只显示结算项
	 */
	protected boolean caclUnPay() {
		for(MahjongPlayer winner : winners) {
			ScoreDetail scoreGotDetail = new ScoreDetail(getMainType(), rate, winnerDisplay, toPay);
			scoreGotDetail.addSubTypes(subTypes);
			addUserGain(winner.getId(), scoreGotDetail);
		}
		for(MahjongPlayer loser : losers) {
			ScoreDetail scoreLostDetail = new ScoreDetail(getMainType(), rate, loserDisplay, toPay);
			scoreLostDetail.addSubTypes(subTypes);
			addUserLost(loser.getId(), scoreLostDetail);
		}
		return true;		
	}
	
	/**
	 * 一付多
	 * 
	 */
	protected boolean calcMultipleWinners() {
		MahjongPlayer loser = losers.get(0);
		int xianRate = calcXianRate();	// 闲家输赢分
		int total = 0;
		boolean loserBanker = loser.isBanker();
		for (MahjongPlayer winner : winners) {
			int win = (loserBanker || winner.isBanker())?calcBankerRate():xianRate;
			total += win;
			
			ScoreDetail scoreDetail = new ScoreDetail(mainType, win, winnerDisplay, toPay);
			scoreDetail.addSubTypes(subTypes);
			addUserGain(winner.getId(), scoreDetail);
		}
		
		ScoreDetail scoreDetail = new ScoreDetail(mainType, total, loserDisplay, toPay);
		scoreDetail.addSubTypes(subTypes);
		addUserLost(loser.getId(), scoreDetail);
		
		return true;
	}

	/**
	 * 多付一
	 * 
	 */
	protected boolean calcMultipleLosers() {
		MahjongPlayer winner = winners.get(0);
		int xianRate = calcXianRate();	// 闲家输赢分
		int total = 0;
		boolean winnerBanker = winner.isBanker();
		for (MahjongPlayer loser : losers) {
			int lost = (winnerBanker || loser.isBanker())?calcBankerRate():xianRate;
			total += lost;
			
			ScoreDetail scoreDetail = new ScoreDetail(mainType, lost, loserDisplay, toPay);
			scoreDetail.addSubTypes(subTypes);
			addUserLost(loser.getId(), scoreDetail);
		}
		
		ScoreDetail scoreDetail = new ScoreDetail(mainType, total, winnerDisplay, toPay);
		scoreDetail.addSubTypes(subTypes);
		addUserGain(winner.getId(), scoreDetail);
		
		return true;
	}

	/**
	 * 一付一
	 * 
	 */
	protected boolean calcOne2One() {
		MahjongPlayer winner = winners.get(0);
		MahjongPlayer loser = losers.get(0);
		int xianRate = calcXianRate();	// 闲家输赢分
		int total = 0;
		boolean winnerBanker = winner.isBanker();
		
		if(payStyle == PAY_ALL) {
			for (GamePlayer player : desk.getAllPlayers()) {
				if(player.equals(winner)) continue;
				
				int lost = (winnerBanker || player.isBanker())?calcBankerRate():xianRate;
				total += lost;
			}			
		} else {
			int lost = (winnerBanker || loser.isBanker())?calcBankerRate():xianRate;
			total += lost;
		}

		ScoreDetail scoreDetail = new ScoreDetail(mainType, total, loserDisplay, toPay);
		scoreDetail.addSubTypes(subTypes);
		addUserLost(loser.getId(), scoreDetail);
		
		scoreDetail = new ScoreDetail(mainType, total, winnerDisplay, toPay);
		scoreDetail.addSubTypes(subTypes);
		addUserGain(winner.getId(), scoreDetail);
		
		return true;
	}
	
	/*
	 * 闲家输赢分
	 */
	protected int calcXianRate() {
		return (zimo && zimoDouble)?rate*2:rate;
	}

	/*
	 * 庄家输赢分
	 */
	protected int calcBankerRate() {
		if(bankerDouble) return calcXianRate() * 2;
		return calcXianRate();
	}
	
	public void addSubType(int subType) {
		if(this.subTypes == null) this.subTypes = new LinkedList<Integer>();
		this.subTypes.add(subType);
	}

	public void addSubTypes(Collection<Integer> subTypes) {
		if(subTypes == null || subTypes.isEmpty()) return;
		if(this.subTypes == null) this.subTypes = new LinkedList<Integer>();
		this.subTypes.addAll(subTypes);
	}
	
	@Override
	public List<MahjongPlayer> getLosers() {
		return losers;
	}

	@Override
	public List<MahjongPlayer> getWinners() {
		return winners;
	}

	@Override
	public int compareTo(CommonCalculatorDetail o) {
		if(priority != o.getPriority()) return o.getPriority() - priority;
		return o.getRate() - rate;
	}
	
	public String getKey() {
		return priority + "," + rate;
	}
	
}
