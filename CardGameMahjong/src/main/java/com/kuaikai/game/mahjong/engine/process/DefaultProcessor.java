package com.kuaikai.game.mahjong.engine.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.common.play.GamePlayer;
import com.kuaikai.game.common.utils.RandomUtils;
import com.kuaikai.game.mahjong.engine.constants.ChairMode;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.constants.RoomAttr;
import com.kuaikai.game.mahjong.engine.model.CardPool;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongFactory;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.msg.pb.DirectionPB.Direction;

public class DefaultProcessor implements IMahjongProcessor {

	protected static final Logger logger = LoggerFactory.getLogger("play");
	
	protected MahjongDesk desk;
	
	/**
	 * 根据规则设置返回需要的房卡数：首先看是否设置了总局数，其次看是否设置了圈，最后看是否设置了底
	 */
	@Override
	public int roomCard(Map<Object, Object> setting) {
		int multi = 1;
		int totalSet = setting.containsKey(CardGameSetting.TOTAL_SET)?(Integer)setting.get(CardGameSetting.TOTAL_SET):0;
		int totalQuan = setting.containsKey(CardGameSetting.TOTAL_QUAN)?(Integer)setting.get(CardGameSetting.TOTAL_QUAN):0;
		int totalDi = setting.containsKey(CardGameSetting.TOTAL_DI)?(Integer)setting.get(CardGameSetting.TOTAL_DI):0;
		
		if(totalDi > 0) return totalDi * multi;
		if(totalQuan > 0) return totalQuan/2 * multi;
		if(totalSet > 0) return totalSet/8 * multi;
		
		return multi;
	}
	
	/**
	 * 初始化，创建房间构造麻将引擎时调用
	 */
	@Override
	public void init(MahjongDesk desk) {
		this.desk = desk;
		initSetting();
		
		int totalQuan = desk.getSetting().getInt(CardGameSetting.TOTAL_QUAN);
		if(totalQuan > 0) {
			desk.getEngine().putAttr(RoomAttr.CURRENT_QUAN, 1);	// 从第一圈开始
			addQuan2Round(1);	// 第一圈从第一局开始
		}
		
		int totalDi = desk.getSetting().getInt(CardGameSetting.TOTAL_DI);
		if(totalDi > 0) {
			desk.getEngine().putAttr(RoomAttr.CURRENT_DI, 1);	// 从第一底开始
			addDi2Round(1);	// 第一底从第一局开始
		}
	}

	/**
	 * 初始化时调用，改变规则设置
	 */
	protected void initSetting() {
		AttrsModel setting = desk.getSetting();
		
		setting.putIfNotExist(CardGameSetting.CHAIR_MODE, ChairMode.SEQUENCE.name());	// 按加入房间顺序入座
		setting.putIfNotExist(CardGameSetting.INIT_HAND_CARD_NUM, 13);					// 初始每人13张牌
		
		// 设置胡和杠的基础分
		setting.putIfNotExist(CardGameSetting.BASE_RATE, 1);						// 基础分
		int baseRate = Math.max(1, setting.getInt(CardGameSetting.BASE_RATE));
		setting.putIfNotExist(CardGameSetting.BASE_RATE_HU, baseRate);				// 胡牌基础分
		setting.putIfNotExist(CardGameSetting.BASE_RATE_MING_GANG, baseRate);		// 明杠基础分
		setting.putIfNotExist(CardGameSetting.BASE_RATE_AN_GANG, baseRate*2);		// 暗杠基础分
		
		// 红中是万能牌时，选中带红中
		if(setting.getBool(CardGameSetting.HONG_ZHONG_ALMIGHTY)) {
			setting.put(CardGameSetting.HONG_ZHONG, true);
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
		MahjongPlayer banker = desk.getBanker();
		if (banker == null) {
			banker = (MahjongPlayer)desk.getPlayerBySeat(Direction.DONG_VALUE);
			desk.setBanker(banker);
			return banker;
		}
		
		MahjongPlayer zhuang = null;
		if(desk.getEngine().getCurrentSetResult().isHuangZhuang()) {	// 荒庄
			zhuang = dingZhuangHuangZhuang();
		} else {
			zhuang = dingZhuangHu();
		}
		
		// 下一家坐庄
		if(zhuang == null) {
			zhuang = desk.getNextPlayer(banker);
		}
		
		desk.setBanker(zhuang);
		return zhuang;
	}

	/**
	 * 胡牌时定庄：庄家胡牌则继续坐庄，闲家胡牌，根据规则设置决定胡牌坐庄或是点炮者坐庄
	 */
	protected MahjongPlayer dingZhuangHu() {
		if(desk.getBanker().isHuPlayer()) {	// 庄家胡牌，继续坐庄
			return desk.getBanker();
		}
		
		// 规则设置为胡牌的坐庄
		if(desk.getSetting().getBool(CardGameSetting.HU_TAKE_ZHUANG)) {
			MahjongPlayer huPlayer = desk.getEngine().getCurrentSetResult().getSingleHuPlayer();
			if(huPlayer != null) {
				desk.setBanker(huPlayer);
				return huPlayer;
			}
		}

		// 规则设置为点炮者坐庄
		if(desk.getSetting().getBool(CardGameSetting.DIAN_PAO_TAKE_ZHUANG)) {
			MahjongPlayer dianPlayer = desk.getEngine().getCurrentSetResult().getDianPlayer();
			if(dianPlayer != null) {
				desk.setBanker(dianPlayer);
				return dianPlayer;
			}
		}
		
		return null;
	}
	
	/**
	 * 荒庄时定庄
	 */
	protected MahjongPlayer dingZhuangHuangZhuang() {
		if(desk.getSetting().getBool(CardGameSetting.HUANG_ZHUANG_CONTINUE))	//  规则设置为继续坐庄
			return desk.getBanker();
		return null;
	}
	
	/**
	 * 发牌规则：依次从牌池中取出初始张牌，放入各玩家手牌中
	 * 
	 */
	@Override
	public void deal() {
		int count = desk.getSetting().getInt(CardGameSetting.INIT_HAND_CARD_NUM);	// 初始手牌数量
		for(GamePlayer player : desk.getAllPlayers()) {
/*			List<Integer> cards = GmManager.getInstance().getAndRmGmCards(player.getId());
			if (cards != null) {
				player.onDealCards(cards);
			} else {
				player.onDealCards(desk.getEngine().getCardPool().takeNextCards(count));
			}*/
			List<Integer> cards = desk.getEngine().getCardPool().takeNextCards(count);
			((MahjongPlayer)player).getCardContainer().dealCards(cards);
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
		
		if(!desk.getSetting().getBool(CardGameSetting.QU_TIAO)) {
			for(int i=1; i<=9; i++){
				cardPool.add(Mahjong.CardType.TIAO.getValue()+i);
			}			
		}
		
		if(!desk.getSetting().getBool(CardGameSetting.QU_TONG)) {
			for(int i=1; i<=9; i++){
				cardPool.add(Mahjong.CardType.TONG.getValue()+i);
			}			
		}
		
		if(!desk.getSetting().getBool(CardGameSetting.QU_ZI)) {
			for(int i=1; i<=7; i++){
				cardPool.add(Mahjong.CardType.ZI.getValue()+i);
			}				
		}
		
		// 加入红中
		if(desk.getSetting().getBool(CardGameSetting.HONG_ZHONG) && !cardPool.contains(Mahjong.Zi.ZHONG.getValue()))
			cardPool.add(Mahjong.Zi.ZHONG.getValue());
		
		return cardPool;
	}

	/**
	 * 返回荒庄时牌池剩余牌数
	 */
	public int getRemainCardNum() {
		return desk.getSetting().getInt(CardGameSetting.REMAIN_CARD_NUM);
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
		if(desk.getSetting().getBool(CardGameSetting.HONG_ZHONG_ALMIGHTY))
			desk.getEngine().putAttr(RoomAttr.ALMIGHTY_CARD, Mahjong.Zi.ZHONG.getValue());
		else if(desk.getSetting().getBool(CardGameSetting.DING_ALMIGHTY)) {
			// 掷两个骰子
			int dice1 = RandomUtils.getRandomInt(1,6);
			int dice2 = RandomUtils.getRandomInt(1,6);
			desk.getEngine().putAttr(RoomAttr.ALMIGHTY_DICE_1, dice1);
			desk.getEngine().putAttr(RoomAttr.ALMIGHTY_DICE_2, dice2);
			
			// 从牌池尾部翻牌，存入房间属性
			CardPool cardPool = desk.getEngine().getCardPool();
			int fanCard = 0;
			if(desk.getSetting().getBool(CardGameSetting.DING_ALMIGHTY_REMAIN_IN_POOL))
				fanCard = cardPool.seeTailCard(dice1+dice2);
			else 
				fanCard = cardPool.takeTailCard(dice1+dice2);
			
			MJCard showCard = MahjongFactory.createMJCard(fanCard, null);
			showCard.setShowAlmighty(true);
			
			desk.getEngine().putAttr(RoomAttr.ALMIGHTY_CARD_SHOW, showCard);
			int almightyCard = desk.getSetting().getBool(CardGameSetting.DING_ALMIGHTY_FAN)?fanCard:Mahjong.getNextCardNum(fanCard);
			desk.getEngine().putAttr(RoomAttr.ALMIGHTY_CARD, almightyCard);	// 万能牌是翻牌的下一张
		}
	}
	
	@Override
	public void onSetEnd() {
		// 处理荒庄
		if(desk.getEngine().getCurrentSetResult().isHuangZhuang()) {
			onHuangZhuang();
		}
	}
	
	/**
	 * 荒庄时处理
	 */	
	@Override
	public void onHuangZhuang() {
		//if(!room.getBool(RoomSetting.HUANG_ZHUANG_CONTINUE)) checkAndIncreaseQuan(room);
	}

	/**
	 * 按圈数玩法时，算分后检查最后一名庄家是否不再连庄，设置房间属性：一圈结束
	 */	
	@Override
	public boolean checkCurrentQuanOver() {
		if(desk.getSetting().getInt(CardGameSetting.TOTAL_QUAN) <= 0) return false;
		
		boolean quanOver = desk.getEngine().quanOver();
		if(quanOver) {
			desk.getEngine().putAttr(RoomAttr.CURRENT_QUAN_OVER, true);
		}
		return quanOver;
	}
	
	/**
	 * 按圈数玩法时，检查并开始新的一圈
	 */	
	@Override
	public void checkAndStartNewQuan() {
		if(desk.getSetting().getInt(CardGameSetting.TOTAL_QUAN) <= 0) return;
		if(!desk.getEngine().containsAttr(RoomAttr.CURRENT_QUAN_OVER)) return;
		
		// 检查房间属性：一圈是否结束
		boolean quanOver = desk.getEngine().getAttrBool(RoomAttr.CURRENT_QUAN_OVER);
		if(quanOver) {
			int current = desk.getEngine().getAttrInt(RoomAttr.CURRENT_QUAN);
			desk.getEngine().putAttr(RoomAttr.CURRENT_QUAN, current+1);
			addQuan2Round(desk.getCurSet());
		}
		desk.getEngine().removeAttr(RoomAttr.CURRENT_QUAN_OVER);
	}
	
	/**
	 * 定当前圈风，根据当前圈数决定圈风，第一圈为东，依次东南西北
	 */
	@Override
	public void dingCurrentQuanFeng() {
		if(desk.getSetting().getInt(CardGameSetting.TOTAL_QUAN) <= 0) return;
		int quan = desk.getEngine().getAttrInt(RoomAttr.CURRENT_QUAN);
		int delta = (quan-1)%4;
		desk.getEngine().putAttr(RoomAttr.CURRENT_QUAN_FENG, Mahjong.Zi.DONG.getValue() + delta);
	}
	
	@SuppressWarnings("unchecked")
	private void addQuan2Round(int round) {
		Map<Integer, Integer> quan2round = (Map<Integer, Integer>)desk.getEngine().getAttr(RoomAttr.QUAN_2_ROUND);
		if(quan2round == null) {
			quan2round = new HashMap<Integer, Integer>();
			desk.getEngine().putAttr(RoomAttr.QUAN_2_ROUND, quan2round);
		}
		int quan = desk.getEngine().getAttrInt(RoomAttr.CURRENT_QUAN);
		quan2round.put(quan, round);
	}
	
	/**
	 * 按底分玩法时，算分后检查是否有人输光底分，设置房间属性：一底结束
	 */	
	@Override
	public boolean checkCurrentDiOver() {
		if(desk.getSetting().getInt(CardGameSetting.TOTAL_DI) <= 0) return false;
		
		boolean diOver = desk.getEngine().diOver();
		if(diOver) {
			desk.getEngine().putAttr(RoomAttr.CURRENT_DI_OVER, true);
		}
		return diOver;
	}
	
	/**
	 * 按底分玩法时，一局开始时，若一底结束，开始新的一底
	 */	
	@Override
	public void checkAndStartNewDi() {
		if(desk.getSetting().getInt(CardGameSetting.TOTAL_DI) <= 0) return;
		if(!desk.getEngine().containsAttr(RoomAttr.CURRENT_DI_OVER)) return;
		
		// 检查房间属性：一底是否结束
		boolean diOver = desk.getEngine().getAttrBool(RoomAttr.CURRENT_DI_OVER);
		if(diOver) {
			int current = desk.getEngine().getAttrInt(RoomAttr.CURRENT_DI);
			desk.getEngine().putAttr(RoomAttr.CURRENT_DI, current+1);
			addDi2Round(desk.getCurSet());
		}
		desk.getEngine().removeAttr(RoomAttr.CURRENT_DI_OVER);
	}
	
	@SuppressWarnings("unchecked")
	private void addDi2Round(int round) {
		Map<Integer, Integer> di2round = (Map<Integer, Integer>)desk.getEngine().getAttr(RoomAttr.DI_2_ROUND);
		if(di2round == null) {
			di2round = new HashMap<Integer, Integer>();
			desk.getEngine().putAttr(RoomAttr.DI_2_ROUND, di2round);
		}
		int di = desk.getEngine().getAttrInt(RoomAttr.CURRENT_DI);
		di2round.put(di, round);
	}
	
	/**
	 * 检查牌局是否结束：首先看是否设置了总局数，其次看是否设置了圈，最后看是否扣完点子
	 */	
	@Override
	public boolean checkOver() {
		int totalSet = desk.getSetting().getInt(CardGameSetting.TOTAL_SET);
		int totalQuan = desk.getSetting().getInt(CardGameSetting.TOTAL_QUAN);
		int totalDi = desk.getSetting().getInt(CardGameSetting.TOTAL_DI);
		
		if(totalSet > 0) return desk.getCurSet() >= totalSet;
		if(totalQuan > 0) return desk.getEngine().getAttrInt(RoomAttr.CURRENT_QUAN) >= totalQuan && desk.getEngine().getAttrBool(RoomAttr.CURRENT_QUAN_OVER);
		if(totalDi > 0) return desk.getEngine().getAttrInt(RoomAttr.CURRENT_DI) >= totalDi && desk.getEngine().getAttrBool(RoomAttr.CURRENT_DI_OVER);
		
        return desk.getEngine().diOver();
	}

	/**
	 * 检查是否无牌可摸，荒庄
	 */	
	@Override
	public boolean checkHuangZhuang() {
		return desk.getEngine().getCardPool().isEmpty();
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
