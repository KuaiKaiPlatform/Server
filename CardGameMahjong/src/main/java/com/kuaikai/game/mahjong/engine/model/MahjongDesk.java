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
	
	@Override
	public void addPlayer(Player p) {
		this.desk.addPlayer(p);
		MahjongPlayer mp = new MahjongPlayer(p, this);
		this.addPlayer(mp);
	}
	
	public MahjongEngine getEngine() {
		return engine;
	}
	
	public DefaultMessageSender getMessageSender() {
		return (DefaultMessageSender)messageSender;
	}

	public MahjongPlayer getPlayerById(int pid) {
		return (MahjongPlayer)super.getPlayerById(pid);
	}

	public MahjongPlayer getPlayerBySeat(int seat) {
		return (MahjongPlayer)super.getPlayerBySeat(seat);
	}
	
	public MahjongPlayer getNextPlayer(GamePlayer gamePlayer) {
		return (MahjongPlayer)super.getNextPlayer(gamePlayer);
	}
	
	public MahjongPlayer getBanker() {
		return (MahjongPlayer)banker;
	}
	
	public MahjongPlayer initBanker() {
		return engine.getProcessor().dingZhuang();
	}
	
	@Override
	public void onGameStart(long startTime) {
		super.onGameStart(startTime);
		
		// 处理记录
		this.record = new DeskRecord(this);
		this.messageSender = SenderFactory.createMessageSender(this);
		
		for (GamePlayer player : this.getAllPlayers()) {
			player.onGameStart();
		}
		engine.onGameStart();
		
		onSetStart(startTime);
		
	}
	
	/**
	 *	检查是否开始一局
	 */
	public void checkSetStart() {
		if(desk.isFull() && this.isAllPlayerReady()) {
			if(desk.checkStatus(GameStatus.SET_ENDING)) {
				this.onSetStart(System.currentTimeMillis());
			} else {
				this.onGameStart(System.currentTimeMillis());
			}
			this.getMessageSender().sendSSetInit(null);
		}
	}
	
	/**
	 * 开始新的一局
	 */
	@Override
	public void onSetStart(long startTime) {
		super.onSetStart(startTime);
		
		desk.setStatus(GameStatus.STARTING);

		engine.onSetStart();
		for (GamePlayer player : this.getAllPlayers()) {
			player.onSetStart();
		}
		
		// 开始新的一局
		desk.incrCurSet();
		record.addSetRecord(new MahjongSetRecord());
		
		// 检查是否有下注操作，没有直接发牌
//		if(desk.getSetting().getBool(CardGameSetting.XIA_ZHU)) {
//			engine.enterXiaZhuStage();
//		} else {
//			dealCards();
//		}
		
		// 发牌
		dealCards();
		
		// 自动出牌秒数有设置，启动定时任务，自动出牌
		engine.getOperManager().scheduleOperation();
		
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
	@Override
	public void onSetEnd(long endTime) {
		super.onSetEnd(endTime);
		
		desk.setStatus(GameStatus.SET_ENDING);
		// 算分
		engine.onSetEnd();

		for (GamePlayer player : this.getAllPlayers()) {
			player.onSetEnd();
		}
		
		boolean over = engine.getProcessor().checkOver();
		
		// 定下一局庄家
		if(!over) engine.getProcessor().dingZhuang();
		
		// 发送 SSetResult
		messageSender.sendSSetResult(over);

		//roomRecord.addCurSetEndInfo(curSet);

		logger.info("MahjongDesk.onSetEnd@set end info|desk={}|curSet={}", desk.getKey(), desk.getCurSet());
		
		if (over) {
			// 全局结束
			onGameEnd(true, endTime);
		}
		
	}
	
	@Override
	public void onGameEnd(boolean dismiss, long endTime) {
		super.onGameEnd(dismiss, endTime);
		
		desk.setStatus(GameStatus.ENDING);
		
		//engine.onGameEnd();
		
		for (GamePlayer player : this.getAllPlayers()) {
			player.onGameEnd();
		}
		
		//record.save();
		
		// 发送 SSetResult
		messageSender.sendSGameResult(dismiss);

		//roomRecord.addCurSetEndInfo(curSet);

		logger.info("MahjongDesk.onSetEnd@set end info|desk={}|curSet={}", desk.getKey(), desk.getCurSet());
		
	}
	
}
