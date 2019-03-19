package com.kuaikai.game.mahjong.engine.model;

import java.util.LinkedList;
import java.util.List;

/*
 * 一局结果
 */
public class SetResult {
	
	private List<MahjongPlayer> huPlayers;
	private MahjongPlayer dianPlayer;
	private List<MahjongPlayer> winners;
	private List<MahjongPlayer> losers;
	private boolean huangZhuang;

	public void addHuPlayer(MahjongPlayer player) {
		if(huPlayers == null) huPlayers = new LinkedList<MahjongPlayer>();
		if(!huPlayers.contains(player)) huPlayers.add(player);
	}
	
	public boolean checkHuPlayer(MahjongPlayer player) {
		return huPlayers == null?false:huPlayers.contains(player);
	}
	
	public MahjongPlayer getSingleHuPlayer() {
		if(huPlayers == null || huPlayers.isEmpty()) return null;
		return huPlayers.get(0);
	}
	
/*	public void addWinners(List<Player> players) {
		if(winners == null) winners = new LinkedList<Player>();
		for(Player player : players) {
			if(!winners.contains(player)) winners.add(player);
		}
	}*/
	
	public MahjongPlayer getDianPlayer() {
		return dianPlayer;
	}

	public void setDianPlayer(MahjongPlayer dianPlayer) {
		this.dianPlayer = dianPlayer;
	}

	public void addWinner(MahjongPlayer winner) {
		if(winners == null) winners = new LinkedList<MahjongPlayer>();
		if(!winners.contains(winner)) winners.add(winner);
	}

/*	public void addLosers(List<Player> players) {
		if(losers == null) losers = new LinkedList<Player>();
		for(Player player : players) {
			if(!losers.contains(player)) losers.add(player);
		}
	}*/
	
	public void addLoser(MahjongPlayer loser) {
		if(losers == null) losers = new LinkedList<MahjongPlayer>();
		if(!losers.contains(loser)) losers.add(loser);
	}

/*	public Player getSingleLoser() {
		if(losers == null || losers.isEmpty()) return null;
		return losers.get(0);
	}*/

/*	public boolean checkWinner(Player player) {
		return winners == null?false:winners.contains(player);
	}*/

/*	public List<Integer> getWinnerIds() {
		List<Integer> winnerIds = new LinkedList<Integer>();
		if(winners == null) return winnerIds;
		for(Player winner : winners) {
			winnerIds.add(winner.getid());
		}
		return winnerIds;
	}*/

	public boolean isHuangZhuang() {
		return huangZhuang;
	}

	public void setHuangZhuang(boolean huangZhuang) {
		this.huangZhuang = huangZhuang;
	}
	
	/**
	 *	是否一炮多响
	 * @return
	 */
	public boolean isMultipleHu() {
		return dianPlayer != null && huPlayers != null && huPlayers.size() > 1;
	}
	
}
