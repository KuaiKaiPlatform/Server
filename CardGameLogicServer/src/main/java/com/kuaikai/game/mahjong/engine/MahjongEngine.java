package com.kuaikai.game.mahjong.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;
import com.kuaikai.game.mahjong.engine.calculator.Calculator;
import com.kuaikai.game.mahjong.engine.calculator.CalculatorFactory;
import com.kuaikai.game.mahjong.engine.constants.GameSetting;
import com.kuaikai.game.mahjong.engine.constants.PlayMode;
import com.kuaikai.game.mahjong.engine.constants.RoomAttr;
import com.kuaikai.game.mahjong.engine.model.CardPool;
import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.SetResult;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;
import com.kuaikai.game.mahjong.engine.oper.DaOperation;
import com.kuaikai.game.mahjong.engine.oper.MoOperation;
import com.kuaikai.game.mahjong.engine.oper.OperationManager;
import com.kuaikai.game.mahjong.engine.process.IMahjongProcessor;
import com.kuaikai.game.mahjong.engine.process.ProcessorFactory;

/***
 * 麻将引擎
 */
public class MahjongEngine {
	
	// 游戏中
	private static final int STAGE_GAMING = 0;
	// 下注阶段
	private static final int STAGE_XIA_ZHU = 1;
	// 定缺阶段
	private static final int STAGE_DING_QUE = 2;
	// 结算阶段
	private static final int STAGE_JIE_SUAN = 3;
	
	// 房间所处的阶段
	private int stage;
	
	private MahjongDesk desk;
	private IMahjongProcessor processor;
	private Calculator calculator;
	private OperationManager operManager;

	private Map<Object, Object> attrs = new HashMap<Object, Object>();
	private CardPool cardPool;
	private Map<Integer, SetResult> results = new HashMap<Integer, SetResult>();
	
	public MahjongEngine(MahjongDesk desk) {
		this.desk = desk;
		operManager = new OperationManager(desk);
		init();
	}

	private void init() {
		GameRule rule = desk.getRule();
		processor = ProcessorFactory.createProcessor(rule);
		processor.init(desk);
		
		cardPool = new CardPool(desk, processor.getInitCardPool());
		calculator = CalculatorFactory.createCalculator(desk);
		
		initPlayMode();
	}
	
	public CardPool getCardPool() {
		return cardPool;
	}

	public IMahjongProcessor getProcessor() {
		return processor;
	}

	public Calculator getCalculator() {
		return calculator;
	}

	public OperationManager getOperManager() {
		return operManager;
	}
	
	public boolean checkStage(int stage) {
		return this.stage == stage;
	}

	public int getStage() {
		return stage;
	}

	public boolean isStageGaming() {
		return this.checkStage(STAGE_GAMING);
	}
	
	public void enterGamingStage() {
		stage = STAGE_GAMING;
		
		// 记下听的牌
		List<MahjongPlayer> players = desk.getAllPlayers();
		for(MahjongPlayer player : players) {
			player.getMjPlayer().refreshTingCards();
		}
		
		// 房间规则有起手报听
		if(desk.getCreateRoomParam().getSettingBool(GameSetting.TING_QI_SHOU)) {
			for(MahjongPlayer player : players) {
				List<Integer> tingCards = player.getMjPlayer().getTingCards();
				if(tingCards != null && !tingCards.isEmpty()) {
					//BaseMajongPlayerAction action = ActionManager.createCanExecuteAction(player, ActionType.TING);
					//action.setPriority(ActionType.Priority.COMMON);
				}
			}
		}
		
		// 无可执行操作时，庄家开始摸牌
		if(!operManager.hasCanExecuteOperations()) MoOperation.execute(desk.getBanker(), null);
	}
	
	public boolean isStageXiaZhu() {
		return this.checkStage(STAGE_XIA_ZHU);
	}

	public void enterXiaZhuStage() {
		stage = STAGE_XIA_ZHU;
	}
	
	public boolean isStageDingQue() {
		return this.checkStage(STAGE_DING_QUE);
	}
	
	public void enterDingQueStage() {
		stage = STAGE_DING_QUE;
	}
	
	public boolean isStageJieSuan() {
		return this.checkStage(STAGE_JIE_SUAN);
	}
	
	public void enterJieSuanStage() {
		stage = STAGE_JIE_SUAN;
	}

	public void putAttr(Object key, Object value) {
		attrs.put(key, value);
	}

	public boolean containsAttr(Object key) {
		return attrs.containsKey(key);
	}
	
	public Object getAttr(Object key) {
		return attrs.get(key);
	}

	public Object removeAttr(Object key) {
		return attrs.remove(key);
	}
	
	public String getAttrStr(Object key) {
		return (String)attrs.get(key);
	}

	public int getAttrInt(Object key) {
		return CollectionUtils.getMapInt(attrs, key);
	}

	public boolean getAttrBool(Object key) {
		return CollectionUtils.getMapBool(attrs, key);
	}

	public void onGameStart() {
		processor.onGameStart();
		calculator.onGameStart();
	}
	
	public void onSetStart() {
		processor.onSetStart();
		operManager.onSetStart();
		calculator.onSetStart();
	}

	public void onSetEnd() {
		processor.onSetEnd();
		calculator.onSetEnd();
	}
	
	public SetResult getCurrentSetResult() {
		SetResult setResult = results.get(desk.getCurSet());
		if(setResult == null) {
			setResult = new SetResult();
			results.put(desk.getCurSet(), setResult);	
		}
		return setResult;
	}

	/*
	 * 返回上一局的结果
	 */
	public SetResult getLastSetResult() {
		int lastSet = desk.getCurSet()-1;
		return results.containsKey(lastSet)?results.get(lastSet):new SetResult();
	}

	public int getAlmightyCardNum() {
		return this.containsAttr(RoomAttr.ALMIGHTY_CARD)?this.getAttrInt(RoomAttr.ALMIGHTY_CARD):-1;
	}
	
	/**
	 * 发牌后处理：定万能牌，设置玩家听牌，定缺，起手操作或开始摸牌
	 */
	public void onDeal() {
		processor.dingAlmighty();
		
		desk.getRoomRecord().addCurSetInitInfo(desk.getCurSet());

		// 检查是否有定缺步骤
		if(desk.getCreateRoomParam().getSettingBool(GameSetting.DING_QUE)) {
			enterDingQueStage();
			return;
		}
		
		enterGamingStage();
	}
	
	private void initPlayMode() {
		int totalDi = desk.getCreateRoomParam().getSettingInt(GameSetting.TOTAL_DI);
		if(totalDi > 0) {
			this.putAttr(RoomAttr.PLAY_MODE, PlayMode.DI);
			return;
		}
		
		int totalQuan = desk.getCreateRoomParam().getSettingInt(GameSetting.TOTAL_QUAN);
		if(totalQuan > 0) {
			this.putAttr(RoomAttr.PLAY_MODE, PlayMode.QUAN);
			return;
		}
		
		int totalSet = desk.getCreateRoomParam().getSettingInt(GameSetting.TOTAL_SET);
		if(totalSet > 0) {
			this.putAttr(RoomAttr.PLAY_MODE, PlayMode.SET);
			return;
		}
	}

	/*
	 * 返回牌局模式
	 */
	public PlayMode getPlayMode() {
		return (PlayMode)this.getAttr(RoomAttr.PLAY_MODE);
	}

	/*
	 * 检查牌局模式
	 */
	public boolean checkPlayMode(PlayMode mode) {
		return mode.equals(getPlayMode());
	}
	
	/*
	 *  底分玩法时，检查有人是负分或零分
	 */
	public boolean diOver() {
        for(MahjongPlayer p : desk.getAllPlayers()) {
            if(p.getMjPlayer().getGameResult().diOver()) return true;
        }
        return false;
	}

	/*
	 *  圈数玩法时，检查最后一名庄家是否不再连庄
	 */
	public boolean quanOver() {
		MahjongPlayer banker = desk.getBanker();
		if(!banker.isLast()) return false;	// 不是最后一名庄家
		if(banker.isHuPlayer()) return false;	// 庄家胡牌，继续坐庄
		
		if(this.getCurrentSetResult().isHuangZhuang()) {	// 荒庄
			if(desk.getCreateRoomParam().getSettingBool(GameSetting.HUANG_ZHUANG_CONTINUE))	//  房间设置为继续坐庄
				return false;
		}
		
        return true;
	}

	/*
	 * 操作执行后
	 */
	public void onOperationDone(BaseOperation oper) {
		operManager.onOperationDone(oper);
		processor.onOperationDone(oper);
	}
	
	/*
	 * 操作执行，消息发送后
	 */
	public void onOperationEnd(BaseOperation oper) {
		operManager.onOperationEnd(oper);
	}
	
	/*
	 * 执行过牌
	 */
	public void executePass(MahjongPlayer player) {
		// 清除当前可执行操作
		operManager.clearCurrentCanExecuteOperations();
		
		// 查找更多可执行的操作：如过了胡，其他玩家可胡、可碰等
		List<BaseOperation> canExecuteOperations = operManager.getCurrentCanExecuteOperations();
		if(!canExecuteOperations.isEmpty()) {
			desk.getMessageSender().syncCanOperRes();
			return;
		}
		
		// 没有上一步操作，过后庄家开始摸牌，如：抢金不胡
		BaseOperation lastDone = operManager.getLastDoneOperation();
		if(lastDone == null) {
			MoOperation.execute(desk.getBanker(), null);
			return;
		}
		
		// 上一步是本人操作，过牌后增加一个打牌操作，如：碰后过了听牌，摸后过了暗杠等
		if(lastDone.getPlayer().equals(player)) {
			DaOperation.check(player, lastDone);
			desk.getMessageSender().syncCanOperRes();
			return;
		}
		
		// 上一步不是本人操作，下家开始摸牌
		MoOperation.execute(desk.getNextPlayer(lastDone.getPlayer()), lastDone);
	}
	
	/*
	 * 检查是否结算阶段，牌局结束
	 */
	public void checkJieSuanStage() {
		if (isStageJieSuan()) {
			desk.handleOnSetEnd();
		}
	}
	
	public void postMultipleHu() {
		calculator.postMultipleHu();
	}

	public void onGenZhuang() {
		calculator.onGenZhuang();
	}
	
}
