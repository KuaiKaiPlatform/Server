package com.kuaikai.game.common.play;

public abstract class MessageSender {

	protected GameDesk desk;
	
	public MessageSender(GameDesk desk) {
		this.desk = desk;
	}
	
	/**
	 * 同步牌局初始信息
	 */
	public abstract void sendSSetInit(GamePlayer p);

	/**
	 * 同步牌局结束信息
	 */	
	public abstract void sendSSetResult(boolean over);
	
	/**
	 * 同步整场结束消息
	 */
	public abstract void sendSGameResult(boolean dismiss);
	
}
