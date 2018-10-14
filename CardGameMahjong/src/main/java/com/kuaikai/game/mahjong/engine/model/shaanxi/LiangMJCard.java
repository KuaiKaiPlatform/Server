package com.kuaikai.game.mahjong.engine.model.shaanxi;

import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

/**
 * 
 * 亮六飞一牌
 *
 */
public class LiangMJCard extends MJCard {

	public final static int VALUE_INCR = 100;	// 亮牌增加的牌值
    private boolean liang = false;			// 亮出的牌
	
	public LiangMJCard(int card, MahjongPlayer player) {
		super(card, player);
	}
	
	/*
	 * OperDetail需要的value
	 */
    public int getOperValue() {
		return liang?value+VALUE_INCR:value;
	}
    
	public boolean isLiang() {
		return liang;
	}

	public void setLiang(boolean liang) {
		this.liang = liang;
	}

}
