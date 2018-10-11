package com.kuaikai.game.mahjong.engine.process;

import java.util.List;
import java.util.Map;

import com.kuaikai.game.mahjong.engine.model.MahjongDesk;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.oper.BaseOperation;

/***
 * 麻将打牌过程处理器
 * 
 * @author clc
 */
public interface IMahjongProcessor {

	/*
	 * 根据房间设置返回需要的房卡数
	 */	
	public int roomCard(Map<Object, Object> setting);
	
	/*
	 * 初始化
	 */	
	public void init(MahjongDesk room);
	
	/*
	 * 进房
	 */		
	public void onJoinRoom(MahjongPlayer player);

	/*
	 * 游戏开始
	 */
	public void onGameStart();
	
	/*
	 * 开始新的一局
	 */
	public void onSetStart();
	
	/*
	 * 定庄
	 */	
	public MahjongPlayer dingZhuang();
	
	/*
	 * 发牌
	 */	
	public void deal();
	
	/*
	 * 返回初始牌
	 */
	public List<Integer> getInitCardPool();

	/*
	 * 返回荒庄时牌池剩余牌数
	 */
	public int getRemainCardNum();
	
	/*
	 * 定万能牌，发牌后执行
	 */
	public void dingAlmighty();
	
	/*
	 * 定当前圈风，发牌后执行
	 */	
	public void dingCurrentQuanFeng();
	
	/*
	 * 打牌动作（碰、杠等）执行完处理
	 */	
	public void onOperationDone(BaseOperation oper);
	
	/*
	 * 检查是否无牌可摸，荒庄
	 */	
	public boolean checkHuangZhuang();
	
	/*
	 * 一局结束，结算前
	 */
	public void onSetEnd();
	
	/*
	 * 荒庄时处理
	 */	
	public void onHuangZhuang();
	
	/*
	 * 检查牌局是否结束
	 */	
	public boolean checkOver();

	/*
	 * 算分完成后处理
	 */
	public void onCalculated();
	
	/*
	 * 底分玩法时，检查并开始新的一底
	 */
	public void checkAndStartNewDi();
	
	/*
	 * 按底分玩法时，算分后检查是否有人输光底分，设置房间属性：一底结束
	 */	
	public boolean checkCurrentDiOver();

	/*
	 * 按圈数玩法时，检查并开始新的一圈
	 */
	public void checkAndStartNewQuan();
	
	/*
	 * 按圈数玩法时，算分后检查最后一名庄家是否不再连庄，设置房间属性：一圈结束
	 */	
	public boolean checkCurrentQuanOver();
	
}
