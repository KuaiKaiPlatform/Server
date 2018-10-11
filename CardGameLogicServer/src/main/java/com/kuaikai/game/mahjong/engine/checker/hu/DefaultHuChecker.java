package com.kuaikai.game.mahjong.engine.checker.hu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.checker.hu.mode.HuModesChecker;
import com.kuaikai.game.mahjong.engine.checker.paixin.CheckerArray;
import com.kuaikai.game.mahjong.engine.checker.paixin.PaiXinChecker;
import com.kuaikai.game.mahjong.engine.checker.paixin.SingleChecker;
import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.constants.PaiXin;
import com.kuaikai.game.mahjong.engine.model.DiscardTingCards;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.model.MahjongFactory;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.HuOperation;
import com.kuaikai.game.mahjong.engine.oper.OperationFactory;

/**
 * 默认的胡牌检查器
 * 
 */
public class DefaultHuChecker implements HuChecker {
	protected static final Logger logger = LoggerFactory.getLogger("play");
	protected final MahjongDesk desk;
	protected final MahjongPlayer player;
	protected PaiXinChecker paiXinChecker;
	
	public DefaultHuChecker(MahjongPlayer player) {
		this.player = player;
		this.desk = player.getGameDesk();
		paiXinChecker = new PaiXinChecker(player);
		initPaiXinChecker();
	}

	/**
	 * 初始化牌型检查器
	 * 
	 ********************************************
	 * 					十三幺
	 ********************************************
	 * 					十三不靠
	 ********************************************
	 *				七对				|	
	 *******************************|
	 *		|		金钩钓			|
	 * 		|***********************|	清一色
	 * 标准胡	|		碰碰胡			|
	 * 		|***********************|
	 * 	    |	    一条龙	  	|	根胡	|
	 * 		|			  	|		|
	 ******************************************** 
	 *
	 */
	protected void initPaiXinChecker() {
		// 十三幺
		SingleChecker shiSanYao = new SingleChecker(PaiXin.SHI_SAN_YAO, paiXinChecker);
		paiXinChecker.setChecker(shiSanYao);
		
		// 十三不靠
		SingleChecker shiSanBuKao = new SingleChecker(PaiXin.SHI_SAN_BU_KAO, paiXinChecker);
		shiSanYao.setNextChecker(shiSanBuKao);
		
		// 其他牌型
		CheckerArray others = new CheckerArray();
		shiSanBuKao.setNextChecker(others);
		
		// 七对
		SingleChecker qiDui = new SingleChecker(PaiXin.QI_DUI, paiXinChecker);
		others.addSingleChecker(qiDui);

		// 清一色
		SingleChecker qingYiSe = new SingleChecker(PaiXin.QING_YI_SE, paiXinChecker);
		qingYiSe.addOrDependency(PaiXin.BIAO_ZHUN_HU);
		qingYiSe.addOrDependency(PaiXin.QI_DUI);
		others.addSingleChecker(qingYiSe);
		
		// 其他牌型（七对）
		CheckerArray othersQiDui = new CheckerArray();
		qiDui.setNextChecker(othersQiDui);
		
		// 标准胡
		SingleChecker biaoZhunHu = new SingleChecker(PaiXin.BIAO_ZHUN_HU, paiXinChecker);
		othersQiDui.addSingleChecker(biaoZhunHu);

		// 金钩钓
		SingleChecker jinGouDiao = new SingleChecker(PaiXin.QUAN_QIU_REN, paiXinChecker);
		jinGouDiao.addOrDependency(PaiXin.BIAO_ZHUN_HU);
		othersQiDui.addSingleChecker(jinGouDiao);
		
		// 碰碰胡
		SingleChecker pengPengHu = new SingleChecker(PaiXin.PENG_PENG_HU, paiXinChecker);
		jinGouDiao.setNextChecker(pengPengHu);

		// 其他牌型（碰碰胡）
		CheckerArray othersPengPengHu = new CheckerArray();
		pengPengHu.setNextChecker(othersPengPengHu);

		// 一条龙
		SingleChecker yiTiaoLong = new SingleChecker(PaiXin.YI_TIAO_LONG, paiXinChecker);
		yiTiaoLong.addOrDependency(PaiXin.BIAO_ZHUN_HU);
		othersPengPengHu.addSingleChecker(yiTiaoLong);

		// 根胡
		SingleChecker genHu = new SingleChecker(PaiXin.GEN_HU, paiXinChecker);
		genHu.addOrDependency(PaiXin.BIAO_ZHUN_HU);
		othersPengPengHu.addSingleChecker(genHu);
		
	}
	
	@Override
	public Set<Integer> checkPaiXins(BaseOperation oper) {
		List<MJCard> handCards = new ArrayList<MJCard>();
		handCards.addAll(player.getCardContainer().getHandCards());
		if (!oper.checkOperType(OperType.MO)) {
			handCards.add(oper.getTarget());
			oper.getTarget().setValidAlmighty(false); // 如果打了一张万能牌，只做牌本身用
		}
		
/*		if(logger.isDebugEnabled())
			logger.debug(String.format("DefaultHuChecker.checkPaiXins@room=%d|set=%d|user=%d|cards=%s|target=%s",
					desk.getKey(),
					desk.getCurSet(),
					player.getId(),
					PaiXinHelper.toCardsList(handCards),
					oper.getTarget()));*/
		
		return checkPaiXins(handCards, oper.getTarget());
	}
	
	@Override
	public Set<Integer> checkPaiXins(List<MJCard> handCards, MJCard card) {
		int almightyCardNum = desk.getEngine().getAlmightyCardNum();
		paiXinChecker.clearResult();
		paiXinChecker.check(player, handCards, card, player.getCardContainer().getCardGroups(), almightyCardNum);
		Set<Integer> result = new HashSet<Integer>();
		
		// 玩法设置要求缺一门时，清一色、混一色、缺一门、乱字必须存在任意一种牌型。
		if(desk.getSetting().getBool(GameSetting.QUE_YI_MEN)) {
			if(!paiXinChecker.containsResult(PaiXin.QING_YI_SE) &&
					!paiXinChecker.containsResult(PaiXin.HUN_YI_SE) &&
					!paiXinChecker.containsResult(PaiXin.QUE_YI_MEN) &&
					!paiXinChecker.containsResult(PaiXin.LUAN_ZI))
				return result;
		}
		
		result.addAll(paiXinChecker.getResult());
		return result;
	}
	
	protected boolean preCheck(BaseOperation oper) {
/*		if(logger.isDebugEnabled())
			logger.debug(String.format("DefaultHuChecker.preCheck@room=%d|set=%d|user=%d|target=%s",
					desk.getRoomid(),
					desk.getCurSet(),
					player.getId(),
					oper.getTarget()));*/
		
		if(player.equals(oper.getPlayer())) return true;	// 可以自摸
		
		boolean qiangGang = oper.checkOperType(OperType.BU_GANG);
		
		// 听所有牌
		if(HuModesChecker.tingAll(player)) {
			if(!desk.getSetting().getBool(GameSetting.TING_ALL_DIAN) && !qiangGang) return false;		// 不能点炮胡 
			if(!desk.getSetting().getBool(GameSetting.TING_ALL_QIANG_GANG) && qiangGang) return false;	// 不能抢杠胡
		}
		
		// 先判断抢杠胡
		if(qiangGang) {
			// 房间规则为不能抢杠胡
			if (desk.getSetting().getBool(GameSetting.NO_QIANG_GANG))
				return false;
			else 
				return true;
		} 
		
		// 普通点炮胡，房间规则为只能自摸
		if (desk.getSetting().getBool(GameSetting.ZI_MO_ONLY)) return false;
		
		return true;
	}

	/*
	 * 检查是否漏胡：true 可胡；false 不可胡
	 */	
	@SuppressWarnings("unchecked")
	@Override
	public boolean louHuCheck(BaseOperation oper) {
		if(desk.getSetting().getBool(GameSetting.NO_LOU_HU)) return true;		// 无漏胡限制
		if(!player.getSetAttrs().contains(MahjongPlayer.SetAttr.LOU_HU_CARDS)) return true;	// 玩家当前不处于漏胡状态
		if(desk.getSetting().getInt(GameSetting.LOU_HU_NUM) < 0) return false;	// -1 漏胡所有
		
		// 漏胡一张或多张
		List<Integer> cards = (List<Integer>)player.getSetAttrs().get(MahjongPlayer.SetAttr.LOU_HU_CARDS); // 不能胡的牌列表
		
/*		if(logger.isDebugEnabled())
			logger.debug(String.format("DefaultHuChecker.louHuCheck@room=%d|set=%d|user=%d|louHuCards=%s|target=%d",
					desk.getRoomid(),
					desk.getCurSet(),
					player.getId(),
					cards.toString(),
					oper.getTarget().getValue()));*/
		
		return !cards.contains(oper.getTarget().getValue());
	}
	
	protected boolean postCheck(BaseOperation oper, HuOperation huOper) {
		return true;
	}

	@Override
	public HuOperation checkHuOperation(BaseOperation oper) {
		// 检查该玩家是否漏胡
		if(!louHuCheck(oper)) {
/*			logger.info(String.format("DefaultHuChecker.checkHuOperation@louHuCheck failed|room=%d|set=%d|user=%d|target=%d",
					desk.getRoomid(),
					desk.getCurSet(),
					player.getId(),
					oper.getTarget().getValue()));*/
			return null;
		}
		
		// 检查该玩家是否有胡牌的条件
		if(!preCheck(oper)) return null;	
		
		// 检查可胡牌型
		Set<Integer> paiXins = checkPaiXins(oper);
		if(paiXins == null || paiXins.isEmpty()) return null;
		
/*		logger.info(String.format("DefaultHuChecker.checkHuOperation@paiXins found|room=%d|set=%d|user=%d|paiXins=%s",
				desk.getRoomid(),
				desk.getCurSet(),
				player.getId(),
				paiXins,
				oper.getTarget()));*/
		
		// 生成 HuAction
		HuOperation huOper = OperationFactory.createHuOperation(player, oper);
		huOper.addPaiXins(paiXins);
		huOper.putExtra(paiXinChecker.getExtra());
		
		// 检查可胡牌型和胡牌方式是否具备胡牌条件，做后续操作
		if(!postCheck(oper, huOper)) {
/*			logger.info(String.format("DefaultHuChecker.checkHuOperation@postCheck failed|room=%d|set=%d|user=%d|target=%d",
					desk.getRoomid(),
					desk.getCurSet(),
					player.getId(),
					oper.getTarget().getValue()));*/
			return null;
		}
		
		return huOper;
	}

	@Override
	public Map<Integer, DiscardTingCards> getDiscard2TingCards() {
		List<MJCard> hands = player.getCardContainer().getHandCards();
		if(hands.size()%3 != 2) return null;
		
		// 打一张牌，可以听哪些牌
		Map<Integer, DiscardTingCards> discard2TingCards = new HashMap<>();
		List<MJCard> handsDiscard = new ArrayList<MJCard>();

		Set<Integer> checkedCards = new HashSet<>();	// 已经查过的手牌
		List<Integer> initCardPool = desk.getEngine().getProcessor().getInitCardPool();

		for (MJCard discard : hands) {
			if(!player.equals(discard.getPlayer())) return null;	// 手牌中有别人的牌，说明已经是胡牌状态
			if (checkedCards.contains(discard.getValue()))
				continue;
			checkedCards.add(discard.getValue());
			
			handsDiscard.clear();
			handsDiscard.addAll(hands);
			handsDiscard.remove(discard);		// 移除一张手牌
			
			// 所有牌替换测试是否胡牌
			for (Integer num : initCardPool) {
				List<MJCard> handsToCheck = new ArrayList<MJCard>();
				handsToCheck.addAll(handsDiscard);

				MJCard c = MahjongFactory.createMJCard(num, player);
				handsToCheck.add(c);
				
				Set<Integer> paiXins = checkPaiXins(handsToCheck, c);
				if(paiXins == null || paiXins.isEmpty()) continue;
				
				DiscardTingCards tingCards = discard2TingCards.get(discard.getValue());
				if(tingCards == null){
					tingCards = new DiscardTingCards(discard.getValue());
					discard2TingCards.put(discard.getValue(), tingCards);
				}
				tingCards.addTingCard(num);
			}
		}
		return discard2TingCards;
	}

	@Override
	public List<Integer> getTingCards() {
		List<MJCard> hands = player.getCardContainer().getHandCards();
		if(hands.size()%3 != 1) return null;
		
		List<Integer> tingCards = new LinkedList<Integer>();
		List<Integer> initCardPool = desk.getEngine().getProcessor().getInitCardPool();

		// 所有牌测试是否胡牌
		List<MJCard> handsToCheck = new ArrayList<MJCard>();
		for (Integer num : initCardPool) {
			handsToCheck.clear();
			handsToCheck.addAll(hands);

			MJCard c = MahjongFactory.createMJCard(num, player);
			handsToCheck.add(c);
			
			Set<Integer> paiXins = checkPaiXins(handsToCheck, c);
			if(paiXins == null || paiXins.isEmpty()) continue;
			tingCards.add(num);
		}
		return tingCards.isEmpty()?null:tingCards;
	}

	@Override
	public boolean isTing(List<MJCard> hands) {
		if(hands == null || hands.size()%3 != 1) return false;
		
		List<Integer> initCardPool = desk.getEngine().getProcessor().getInitCardPool();
		// 所有牌测试是否胡牌
		List<MJCard> handsToCheck = new ArrayList<MJCard>();
		for (Integer num : initCardPool) {
			handsToCheck.clear();
			handsToCheck.addAll(hands);

			MJCard c = MahjongFactory.createMJCard(num, player);
			handsToCheck.add(c);
			
			Set<Integer> paiXins = checkPaiXins(handsToCheck, c);
			if(paiXins == null || paiXins.isEmpty()) continue;
			return true;
		}
		return false;
	}
	
}
