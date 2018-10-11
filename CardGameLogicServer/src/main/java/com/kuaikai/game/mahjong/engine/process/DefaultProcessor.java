package com.kuaikai.game.mahjong.engine.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.constants.ChairMode;
import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.constants.RoomAttr;
import com.kuaikai.game.mahjong.engine.model.CardPool;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongFactory;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.rafo.chess.common.utils.RandomUtils;
import com.sy.mahjong.gm.GmManager;
import com.sy.mahjong.message.room.CreateRoomParam;

public class DefaultProcessor implements IMahjongProcessor {

	protected static final Logger logger = LoggerFactory.getLogger("play");
	
	protected MahjongDesk room;
	
	/**
	 * 根据规则设置返回需要的房卡数：首先看是否设置了总局数，其次看是否设置了圈，最后看是否设置了底
	 */
	@Override
	public int roomCard(Map<Object, Object> setting) {
		int multi = 1;
		int totalSet = setting.containsKey(GameSetting.TOTAL_SET)?(Integer)setting.get(GameSetting.TOTAL_SET):0;
		int totalQuan = setting.containsKey(GameSetting.TOTAL_QUAN)?(Integer)setting.get(GameSetting.TOTAL_QUAN):0;
		int totalDi = setting.containsKey(GameSetting.TOTAL_DI)?(Integer)setting.get(GameSetting.TOTAL_DI):0;
		
		if(totalDi > 0) return totalDi * multi;
		if(totalQuan > 0) return totalQuan/2 * multi;
		if(totalSet > 0) return totalSet/8 * multi;
		
		return multi;
	}
	
	/**
	 * 初始化，创建房间构造麻将引擎时调用
	 */
	@Override
	public void init(MahjongDesk room) {
		this.room = room;
		initSetting();
		
		int totalQuan = room.getCreateRoomParam().getSettingInt(GameSetting.TOTAL_QUAN);
		if(totalQuan > 0) {
			room.getEngine().putAttr(RoomAttr.CURRENT_QUAN, 1);	// 从第一圈开始
			addQuan2Round(1);	// 第一圈从第一局开始
		}
		
		int totalDi = room.getCreateRoomParam().getSettingInt(GameSetting.TOTAL_DI);
		if(totalDi > 0) {
			room.getEngine().putAttr(RoomAttr.CURRENT_DI, 1);	// 从第一底开始
			addDi2Round(1);	// 第一底从第一局开始
		}
	}

	/**
	 * 初始化时调用，改变规则设置
	 */
	protected void initSetting() {
		CreateRoomParam params = room.getCreateRoomParam();
		
		params.changeSettingIfNotExist(GameSetting.CHAIR_MODE, ChairMode.SEQUENCE.name());	// 按加入房间顺序入座
		params.changeSettingIfNotExist(GameSetting.INIT_HAND_CARD_NUM, 13);					// 初始每人13张牌
		
		// 设置胡和杠的基础分
		params.changeSettingIfNotExist(GameSetting.BASE_RATE, 1);						// 基础分
		int baseRate = Math.max(1, params.getSettingInt(GameSetting.BASE_RATE));
		params.changeSettingIfNotExist(GameSetting.BASE_RATE_HU, baseRate);				// 胡牌基础分
		params.changeSettingIfNotExist(GameSetting.BASE_RATE_MING_GANG, baseRate);		// 明杠基础分
		params.changeSettingIfNotExist(GameSetting.BASE_RATE_AN_GANG, baseRate*2);		// 暗杠基础分
		
		// 红中是万能牌时，选中带红中
		if(params.getSettingBool(GameSetting.HONG_ZHONG_ALMIGHTY)) {
			params.changeSetting(GameSetting.HONG_ZHONG, true);
		}
	}
	
	/**
	 * 加入房间，玩家加入房间时调用
	 */
	@Override
	public void onJoinRoom(MahjongPlayer player) {
		//resetPoints(player);
	}

	/**
	 * 游戏开始
	 */
	@Override
	public void onGameStart() {
	}
	
	@Override
	public void onSetStart() {
		// 定庄
		dingZhuang();
		
		// 底分玩法时，检查一底是否结束，开始新的一底
		checkAndStartNewDi();

		// 圈数玩法时，检查一圈是否结束，开始新的一圈
		checkAndStartNewQuan();
		
		// 定当前圈风
		dingCurrentQuanFeng();
	}
	
	/**
	 * 定庄规则：首局东向玩家坐庄，从第二局开始，荒庄或胡牌时，根据规则设置决定庄家；无法决定则下一家坐庄。
	 * 
	 */
	@Override
	public MahjongPlayer dingZhuang() {
		// 首局，东向玩家坐庄
		MahjongPlayer banker = room.getBanker();
		if (banker == null) {
			banker = room.getPlayerByPos(1);
			room.setBanker(banker);
			return banker;
		}
		
		MahjongPlayer zhuang = null;
		if(room.getEngine().getCurrentSetResult().isHuangZhuang()) {	// 荒庄
			zhuang = dingZhuangHuangZhuang();
		} else {
			zhuang = dingZhuangHu();
		}
		
		// 下一家坐庄
		if(zhuang == null) {
			zhuang = room.getNextPlayer(banker);
		}
		
		room.setBanker(zhuang);
		return zhuang;
	}

	/**
	 * 胡牌时定庄：庄家胡牌则继续坐庄，闲家胡牌，根据规则设置决定胡牌坐庄或是点炮者坐庄
	 */
	protected MahjongPlayer dingZhuangHu() {
		if(room.getBanker().isHuPlayer()) {	// 庄家胡牌，继续坐庄
			return room.getBanker();
		}
		
		// 规则设置为胡牌的坐庄
		if(room.getCreateRoomParam().getSettingBool(GameSetting.HU_TAKE_ZHUANG)) {
			MahjongPlayer huPlayer = room.getEngine().getCurrentSetResult().getSingleHuPlayer();
			if(huPlayer != null) {
				room.setBanker(huPlayer);
				return huPlayer;
			}
		}

		// 规则设置为点炮者坐庄
		if(room.getCreateRoomParam().getSettingBool(GameSetting.DIAN_PAO_TAKE_ZHUANG)) {
			MahjongPlayer dianPlayer = room.getEngine().getCurrentSetResult().getDianPlayer();
			if(dianPlayer != null) {
				room.setBanker(dianPlayer);
				return dianPlayer;
			}
		}
		
		return null;
	}
	
	/**
	 * 荒庄时定庄
	 */
	protected MahjongPlayer dingZhuangHuangZhuang() {
		if(room.getCreateRoomParam().getSettingBool(GameSetting.HUANG_ZHUANG_CONTINUE))	//  规则设置为继续坐庄
			return room.getBanker();
		return null;
	}
	
	/**
	 * 发牌规则：依次从牌池中取出初始张牌，放入各玩家手牌中
	 * 
	 */
	@Override
	public void deal() {
		List<MahjongPlayer> players = room.getAllPlayers();
		int count = room.getCreateRoomParam().getSettingInt(GameSetting.INIT_HAND_CARD_NUM);	// 初始手牌数量
		for(MahjongPlayer player : players) {
			List<Integer> cards = GmManager.getInstance().getAndRmGmCards(player.getId());
			if (cards != null) {
				player.onDealCards(cards);
			} else {
				player.onDealCards(room.getEngine().getCardPool().takeNextCards(count));
			}
		}
	}
	
	/**
	 * 默认初始牌：根据规则设置，加上万、条、筒、字牌或红中癞子。
	 * 
	 */
	@Override
	public List<Integer> getInitCardPool() {
		
		List<Integer> cardPool = new ArrayList<Integer>();
		// 加入万条筒
		for(int i=1; i<=9; i++){
			cardPool.add(Mahjong.CardType.WAN.getValue()+i);
		}
		
		if(!room.getCreateRoomParam().getSettingBool(GameSetting.QU_TIAO)) {
			for(int i=1; i<=9; i++){
				cardPool.add(Mahjong.CardType.TIAO.getValue()+i);
			}			
		}
		
		if(!room.getCreateRoomParam().getSettingBool(GameSetting.QU_TONG)) {
			for(int i=1; i<=9; i++){
				cardPool.add(Mahjong.CardType.TONG.getValue()+i);
			}			
		}
		
		if(!room.getCreateRoomParam().getSettingBool(GameSetting.QU_ZI)) {
			for(int i=1; i<=7; i++){
				cardPool.add(Mahjong.CardType.ZI.getValue()+i);
			}				
		}
		
		// 加入红中
		if(room.getCreateRoomParam().getSettingBool(GameSetting.HONG_ZHONG) && !cardPool.contains(Mahjong.Zi.ZHONG.getValue()))
			cardPool.add(Mahjong.Zi.ZHONG.getValue());
		
		return cardPool;
	}

	/**
	 * 返回荒庄时牌池剩余牌数
	 */
	public int getRemainCardNum() {
		return room.getCreateRoomParam().getSettingInt(GameSetting.REMAIN_CARD_NUM);
	}
	
	/*
	 * 打牌动作（碰、杠等）执行完处理
	 */	
	@Override
	public void onOperationDone(BaseOperation oper) {
		switch(oper.getOperType()) {
		case OperType.HU :
			break;
		default :
			break;
		}
	}

	/**
	 * 定万能牌，若规则设置有红中赖子，把红中定为万能牌
	 */
	@Override
	public void dingAlmighty() {
		if(room.getCreateRoomParam().getSettingBool(GameSetting.HONG_ZHONG_ALMIGHTY))
			room.getEngine().putAttr(RoomAttr.ALMIGHTY_CARD, Mahjong.Zi.ZHONG.getValue());
		else if(room.getCreateRoomParam().getSettingBool(GameSetting.DING_ALMIGHTY)) {
			// 掷两个骰子
			int dice1 = RandomUtils.getRandomInt(1,6);
			int dice2 = RandomUtils.getRandomInt(1,6);
			room.getEngine().putAttr(RoomAttr.ALMIGHTY_DICE_1, dice1);
			room.getEngine().putAttr(RoomAttr.ALMIGHTY_DICE_2, dice2);
			
			// 从牌池尾部翻牌，存入房间属性
			CardPool cardPool = room.getEngine().getCardPool();
			int fanCard = 0;
			if(room.getCreateRoomParam().getSettingBool(GameSetting.DING_ALMIGHTY_REMAIN_IN_POOL))
				fanCard = cardPool.seeTailCard(dice1+dice2);
			else 
				fanCard = cardPool.takeTailCard(dice1+dice2);
			
			MJCard showCard = MahjongFactory.createMJCard(fanCard, null);
			showCard.setShowAlmighty(true);
			
			room.getEngine().putAttr(RoomAttr.ALMIGHTY_CARD_SHOW, showCard);
			int almightyCard = room.getCreateRoomParam().getSettingBool(GameSetting.DING_ALMIGHTY_FAN)?fanCard:Mahjong.getNextCardNum(fanCard);
			room.getEngine().putAttr(RoomAttr.ALMIGHTY_CARD, almightyCard);	// 万能牌是翻牌的下一张
		}
	}
	
	@Override
	public void onSetEnd() {
		// 处理荒庄
		if(room.getEngine().getCurrentSetResult().isHuangZhuang()) {
			onHuangZhuang();
		}
	}
	
	/**
	 * 荒庄时处理
	 */	
	@Override
	public void onHuangZhuang() {
		//if(!room.getSettingBool(RoomSetting.HUANG_ZHUANG_CONTINUE)) checkAndIncreaseQuan(room);
	}

	/**
	 * 按圈数玩法时，算分后检查最后一名庄家是否不再连庄，设置房间属性：一圈结束
	 */	
	@Override
	public boolean checkCurrentQuanOver() {
		if(room.getCreateRoomParam().getSettingInt(GameSetting.TOTAL_QUAN) <= 0) return false;
		
		boolean quanOver = room.getEngine().quanOver();
		if(quanOver) {
			room.getEngine().putAttr(RoomAttr.CURRENT_QUAN_OVER, true);
		}
		return quanOver;
	}
	
	/**
	 * 按圈数玩法时，检查并开始新的一圈
	 */	
	@Override
	public void checkAndStartNewQuan() {
		if(room.getCreateRoomParam().getSettingInt(GameSetting.TOTAL_QUAN) <= 0) return;
		if(!room.getEngine().containsAttr(RoomAttr.CURRENT_QUAN_OVER)) return;
		
		// 检查房间属性：一圈是否结束
		boolean quanOver = room.getEngine().getAttrBool(RoomAttr.CURRENT_QUAN_OVER);
		if(quanOver) {
			int current = room.getEngine().getAttrInt(RoomAttr.CURRENT_QUAN);
			room.getEngine().putAttr(RoomAttr.CURRENT_QUAN, current+1);
			addQuan2Round(room.getCurSet());
		}
		room.getEngine().removeAttr(RoomAttr.CURRENT_QUAN_OVER);
	}
	
	/**
	 * 定当前圈风，根据当前圈数决定圈风，第一圈为东，依次东南西北
	 */
	@Override
	public void dingCurrentQuanFeng() {
		if(room.getCreateRoomParam().getSettingInt(GameSetting.TOTAL_QUAN) <= 0) return;
		int quan = room.getEngine().getAttrInt(RoomAttr.CURRENT_QUAN);
		int delta = (quan-1)%4;
		room.getEngine().putAttr(RoomAttr.CURRENT_QUAN_FENG, Mahjong.Zi.DONG.getValue() + delta);
	}
	
	@SuppressWarnings("unchecked")
	private void addQuan2Round(int round) {
		Map<Integer, Integer> quan2round = (Map<Integer, Integer>)room.getEngine().getAttr(RoomAttr.QUAN_2_ROUND);
		if(quan2round == null) {
			quan2round = new HashMap<Integer, Integer>();
			room.getEngine().putAttr(RoomAttr.QUAN_2_ROUND, quan2round);
		}
		int quan = room.getEngine().getAttrInt(RoomAttr.CURRENT_QUAN);
		quan2round.put(quan, round);
	}
	
	/**
	 * 按底分玩法时，算分后检查是否有人输光底分，设置房间属性：一底结束
	 */	
	@Override
	public boolean checkCurrentDiOver() {
		if(room.getCreateRoomParam().getSettingInt(GameSetting.TOTAL_DI) <= 0) return false;
		
		boolean diOver = room.getEngine().diOver();
		if(diOver) {
			room.getEngine().putAttr(RoomAttr.CURRENT_DI_OVER, true);
		}
		return diOver;
	}
	
	/**
	 * 按底分玩法时，一局开始时，若一底结束，开始新的一底
	 */	
	@Override
	public void checkAndStartNewDi() {
		if(room.getCreateRoomParam().getSettingInt(GameSetting.TOTAL_DI) <= 0) return;
		if(!room.getEngine().containsAttr(RoomAttr.CURRENT_DI_OVER)) return;
		
		// 检查房间属性：一底是否结束
		boolean diOver = room.getEngine().getAttrBool(RoomAttr.CURRENT_DI_OVER);
		if(diOver) {
			int current = room.getEngine().getAttrInt(RoomAttr.CURRENT_DI);
			room.getEngine().putAttr(RoomAttr.CURRENT_DI, current+1);
			addDi2Round(room.getCurSet());
		}
		room.getEngine().removeAttr(RoomAttr.CURRENT_DI_OVER);
	}
	
	@SuppressWarnings("unchecked")
	private void addDi2Round(int round) {
		Map<Integer, Integer> di2round = (Map<Integer, Integer>)room.getEngine().getAttr(RoomAttr.DI_2_ROUND);
		if(di2round == null) {
			di2round = new HashMap<Integer, Integer>();
			room.getEngine().putAttr(RoomAttr.DI_2_ROUND, di2round);
		}
		int di = room.getEngine().getAttrInt(RoomAttr.CURRENT_DI);
		di2round.put(di, round);
	}
	
	/**
	 * 检查牌局是否结束：首先看是否设置了总局数，其次看是否设置了圈，最后看是否扣完点子
	 */	
	@Override
	public boolean checkOver() {
		int totalSet = room.getCreateRoomParam().getSettingInt(GameSetting.TOTAL_SET);
		int totalQuan = room.getCreateRoomParam().getSettingInt(GameSetting.TOTAL_QUAN);
		int totalDi = room.getCreateRoomParam().getSettingInt(GameSetting.TOTAL_DI);
		
		if(totalSet > 0) return room.getCurSet() >= totalSet;
		if(totalQuan > 0) return room.getEngine().getAttrInt(RoomAttr.CURRENT_QUAN) >= totalQuan && room.getEngine().getAttrBool(RoomAttr.CURRENT_QUAN_OVER);
		if(totalDi > 0) return room.getEngine().getAttrInt(RoomAttr.CURRENT_DI) >= totalDi && room.getEngine().getAttrBool(RoomAttr.CURRENT_DI_OVER);
		
        return room.getEngine().diOver();
	}

	/**
	 * 检查是否无牌可摸，荒庄
	 */	
	@Override
	public boolean checkHuangZhuang() {
		return room.getEngine().getCardPool().isEmpty();
	}

	/**
	 * 一局算分完成后处理
	 */	
	@Override
	public void onCalculated() {
/*		boolean diOver = false;
		if(room.getEngine().getAttrBool(RoomAttr.CURRENT_DI_OVER)) {
			diOver = true;
		}
		
		List<Player> players = room.getAllPlayers();
		for(Player player : players) {
			if(diOver) player.getMjPlayer().resetPoints();	// 底分玩法时，有人输光底分，重置玩家分数
		}*/
		
	}
	
}
