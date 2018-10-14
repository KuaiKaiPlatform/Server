package com.kuaikai.game.common.play;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class SetRecord {

	// 对局的唯一id
	protected int rid;
	// 开局时间
	protected final long startTime;
	
	// 玩家发牌
	protected Map<Integer, InitInfo> initInfos;
	
	public SetRecord() {
		this.startTime = System.currentTimeMillis();
	}

	/**
	 * 生成该局初始记录
	 */
	public abstract void makeStartInfo(GameDesk room);
	
	/**
	 * 生成该局结束记录
	 */
	public abstract void makeEndInfo(GameDesk room);

	
	public abstract void save();
	
	public static class InitInfo {
		public final int uid;
		public final List<Integer> cards = new LinkedList<>();

		public InitInfo(int uid, List<Integer> cards) {
			this.uid = uid;
			this.cards.addAll(cards);
		}

	}

}
