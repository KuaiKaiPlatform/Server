package com.kuaikai.game.common.model;

public class ClubDesk extends Desk {
	
	private long clubId;

	public ClubDesk(long clubId, int deskId) {
		super(deskId);
		this.clubId = clubId;
	}
	
	public long getClubId() {
		return clubId;
	}

	public void setClubId(long clubId) {
		this.clubId = clubId;
	}
	
}
