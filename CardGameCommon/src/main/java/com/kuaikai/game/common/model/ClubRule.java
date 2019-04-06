package com.kuaikai.game.common.model;

import com.kuaikai.game.common.msg.pb.GameRulePB.GameRule;

/**
 * 俱乐部玩法规则
 * @author Alear
 *
 */
public class ClubRule {
	
	private int clubId;
	private GameRule rule;
	private AttrsModel setting;
	
	public int getClubId() {
		return clubId;
	}
	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	public GameRule getRule() {
		return rule;
	}
	public void setRule(GameRule rule) {
		this.rule = rule;
	}
	public AttrsModel getSetting() {
		return setting;
	}
	public void setSetting(AttrsModel setting) {
		this.setting = setting;
	}
	public boolean hasSetting() {
		return setting != null;
	}
	
}
