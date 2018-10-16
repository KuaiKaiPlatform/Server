package com.kuaikai.game.mahjong.engine.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.model.Desk;
import com.kuaikai.game.common.model.Player;
import com.kuaikai.game.common.msg.pb.GameStatusPB.GameStatus;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.common.play.DeskRecord;
import com.kuaikai.game.common.play.GameDesk;
import com.kuaikai.game.common.play.GamePlayer;
import com.kuaikai.game.mahjong.engine.MahjongEngine;
import com.kuaikai.game.mahjong.engine.record.MahjongSetRecord;
import com.kuaikai.game.mahjong.engine.sender.DefaultMessageSender;
import com.kuaikai.game.mahjong.engine.sender.SenderFactory;

public class MahjongDesk extends GameDesk {

	public Logger logger = LoggerFactory.getLogger(MahjongDesk.class);
	
	protected MahjongEngine engine;
	
	public MahjongDesk(Desk desk) {
		super(desk);
		engine = new MahjongEngine(this);
		this.initPlayers();
	}

	private void initPlayers() {
		for(Player p : desk.getPlayers()) {
			MahjongPlayer mp = new MahjongPlayer(p, this);
			this.addPlayer(mp);
		}
	}
	
	public MahjongEngine getEngine() {
		return engine;
	}
	
	public DefaultMessageSender getMessageSender() {
		return (DefaultMessageSender)messageSender;
	}

	public MahjongPlayer getNextPlayer(GamePlayer gamePlayer) {
		return (MahjongPlayer)super.getNextPlayer(gamePlayer);
	}
	
	public MahjongPlayer getBanker() {
		return (MahjongPlayer)banker;
	}
	
	@Override
	public void onGameStart() {
		// 处理记录
		this.record = new DeskRecord(this);
		this.messageSender = SenderFactory.createMessageSender(this);
		
		for (GamePlayer player : this.getAllPlayers()) {
			player.onGameStart();
		}
		engine.onGameStart();
		
		onSetStart();
		
	}

	/**
	 * 开始新的一局
	 */
	public void onSetStart() {
		desk.setStatus(GameStatus.STARTING);

		engine.onSetStart();
		for (GamePlayer player : this.getAllPlayers()) {
			player.onSetStart();
		}
		
		// 开始新的一局
		desk.incrCurSet();
		record.addSetRecord(new MahjongSetRecord());
		
		// 检查是否有下注操作，没有直接发牌
		if(desk.getSetting().getBool(CardGameSetting.XIA_ZHU)) {
			engine.enterXiaZhuStage();
		} else {
			dealCards();
		}
		
		// 添加日志
		logger.info("MahjongDesk.onSetStart@details|desk={}|set={}|players={}", desk.getKey(), desk.getCurSet(), this.getAllPlayers());
	}
	
	/**
	 * 洗牌、发牌及后续处理
	 */
	public void dealCards() {
		engine.getCardPool().shuffle();
		engine.getProcessor().deal();
		
		// 发牌后处理：定万能牌，定缺或开始牌局
		engine.onDeal();
		
		logger.info("MahjongDesk.dealCards@details|desk={}|set={}|stage={}|status={}|players={}",
				desk.getKey(), desk.getCurSet(), engine.getStage(), desk.getStatus(), this.getAllPlayers());
	}
	
	/**
	 * 本局结束
	 */
	public void onSetEnd() {
		desk.setStatus(GameStatus.SET_ENDING);
		// 算分
		engine.onSetEnd();

		for (GamePlayer player : this.getAllPlayers()) {
			player.onSetEnd();
		}
		
		messageSender.syncSetEnd();

		//roomRecord.addCurSetEndInfo(curSet);

		logger.info("MahjongRoom.onSetEnd@set end info|desk={}|curSet={}", desk.getKey(), desk.getCurSet());
		
		if (engine.getProcessor().checkOver()) {
			// 全局结束
			onGameEnd(true);
		}
	}
	
	@Override
	public void onGameEnd(boolean normal) {
		// TODO Auto-generated method stub
		
	}
	
}
