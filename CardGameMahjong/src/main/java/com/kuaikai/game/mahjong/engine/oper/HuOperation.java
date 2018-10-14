package com.kuaikai.game.mahjong.engine.oper;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.kuaikai.game.mahjong.engine.checker.paixin.PaiXinChecker;
import com.kuaikai.game.mahjong.engine.constants.OperType;
import com.kuaikai.game.mahjong.engine.constants.PaiXin;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

public class HuOperation extends BaseOperation {

	public HuOperation(MahjongPlayer player, BaseOperation preOperation) {
		super(player, preOperation);
		this.operType = OperType.HU;
		this.target = preOperation.target;
	}

	private Set<Integer> paiXins = new HashSet<Integer>();	// 胡牌牌型：超豪华七对，碰碰胡等
	private Set<Integer> huModes = new HashSet<Integer>();	// 胡牌方式：杠上花、天胡等
	
	private Map<Object, Object> extra;

	public void addHuMode(Integer huMode) {
		this.huModes.add(huMode);
	}

	public boolean containsHuMode(int huMode) {
		return huModes.contains(huMode);
	}
	
	public Set<Integer> getHuModes() {
		return huModes;
	}

	public Set<Integer> getPaiXins() {
		return paiXins;
	}

	public int getSinglePaiXin() {
		for(int paiXin : paiXins) return paiXin;
		return PaiXin.BIAO_ZHUN_HU;
	}
	
	public void addPaiXins(Collection<Integer> paiXins) {
		this.paiXins.addAll(paiXins);
	}
	
	public boolean containsPaiXin(int paiXin) {
		return paiXins.contains(paiXin);
	}

	public boolean containsQiDuiPaiXin() {
		return PaiXinChecker.containsQiDui(paiXins);
	}

	public void putExtra(Map<Object, Object> map) {
		if(map == null) return;
		if(extra == null) extra = new HashMap<Object, Object>();
		extra.putAll(map);
	}

	public void putExtra(Object key, Object val) {
		if(extra == null) extra = new HashMap<Object, Object>();
		extra.put(key, val);
	}
	
	public Object getExtra(Object key) {
		if(extra == null) return null;
		return extra.get(key);
	}

	public Map<Object, Object> getExtra() {
		return extra;
	}

	public boolean isQiangGang() {
		return preOperation != null && preOperation.checkOperType(OperType.BU_GANG) && !player.equals(preOperation.getPlayer());
	}
	
	public boolean isZimo() {
		return preOperation != null && preOperation.checkOperType(OperType.MO) && player.equals(preOperation.getPlayer());
	}
	
	@Override
	protected void createCanExecuteOperations() {
	}

	@Override
	protected void executeOperation() {
		// 本局结果中加入胡牌玩家
		desk.getEngine().getCurrentSetResult().addHuPlayer(player);
		
		if(!isZimo()) {
			// 胡牌加入手牌
			player.getCardContainer().addHandCard(target);
			target.setValidAlmighty(false);	// 胡万能牌时，不做万能牌使用

			// 本局结果中加入点炮玩家	
			desk.getEngine().getCurrentSetResult().setDianPlayer(getPrePlayer());
		}
		
		desk.getEngine().getCalculator().onHuOperation(this);
		
		resetQiangGang();
	}
	
	@Override
	protected void postExecute() {
		super.postExecute();
		
		// 一炮多响，暂不结算
		if(desk.getEngine().getOperManager().isMultipleHu()) return;
		
		// 正常胡牌，进入结算，本局结束
		desk.getEngine().enterJieSuanStage();
	}
	
	/**
	 * 重置被抢杠胡的杠及其算分
	 */
	private void resetQiangGang() {
		if(!isQiangGang()) return;
		
		// 将补杠恢复为碰
		BuGangOperation buGang = (BuGangOperation)preOperation;
		buGang.undo();
	}

	@Override
	public boolean match(OperDetail od) {
		return matchCommon(od) && target.getValue() == od.getTarget();
	}
	
	@Override
	protected String getDisplayName() {
		return "胡";
	}

	public static HuOperation check(MahjongPlayer player, BaseOperation preOper) {
		HuOperation operation = player.getHuChecker().checkHuOperation(preOper);
		if(preOper != null) preOper.addCanExecuteOperation(operation);
		return operation;
	}
	
}
