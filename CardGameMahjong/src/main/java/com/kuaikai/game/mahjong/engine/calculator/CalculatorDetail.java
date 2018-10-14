package com.kuaikai.game.mahjong.engine.calculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

/**
 * 
 * @author 张立
 *
 */
public abstract class CalculatorDetail {
	protected static Logger logger = LoggerFactory.getLogger("cacl");
	
	// 设置是否可用
	protected boolean invalid = false;
	
	public abstract boolean calc();
	public abstract List<MahjongPlayer> getWinners();
	public abstract List<MahjongPlayer> getLosers();
	
	//注意user2Gain 和user2lost 是有对应关系的  支持1付多 多付1 不支持多付多这种被支付者和支付者关系不明确的情况  
	//不支持原因是计算结果要能够明确知道支付者和被支付者间的对应关系 进行支付计算
	// 一个计算结果只能产生一个ScoreDetail  
	protected Map<Integer, ScoreDetail> user2Gain = new HashMap<>();
	protected Map<Integer, ScoreDetail> user2Lost = new HashMap<>();

	protected void addUserGain(int userid, ScoreDetail scoreDetail) {
		// 多个 scoreChange 时合并分数
		ScoreDetail detail = user2Gain.get(userid);
		if(detail == null) {
			user2Gain.put(userid, scoreDetail);
		} else {
			detail.setScore(detail.getScore() + scoreDetail.getScore());
		}
	}

	protected void addUserLost(int userid, ScoreDetail scoreDetail) {
		user2Lost.put(userid, scoreDetail);
	}

	public Map<Integer, ScoreDetail> getUser2Gain() {
		return user2Gain;
	}

	public Map<Integer, ScoreDetail> getUser2Lost() {
		return user2Lost;
	}

	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}

}
