package com.kuaikai.game.common.play;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 牌局回放记录
 *
 */
public class DeskRecord {

	protected static final Logger logger = LoggerFactory.getLogger("record");

	protected final GameDesk desk;

	// 牌桌记录的唯一id
	protected int rid;

	// 每一局的记录的集合
	protected List<SetRecord> setRecords = new ArrayList<>();

	public DeskRecord(GameDesk desk) {
		this.desk = desk;
	}

	public void addSetRecord(SetRecord setRecord) {
		setRecords.add(setRecord);
	}

	public void makeCurSetStartInfo() {
		SetRecord setRecord = getCurSetRecord(desk.getCurSet());
		if (setRecord == null) {
			logger.error("DeskRecord.makeCurSetStartInfo@error|desk={}|set=%d", desk.getKey(), desk.getCurSet());
			return;
		}
		setRecord.makeStartInfo(desk);
	}

	public void makeCurSetEndInfo() {
		SetRecord setRecord = getCurSetRecord(desk.getCurSet());
		if (setRecord == null) {
			logger.error("DeskRecord.makeCurSetEndInfo@error|desk={}|set={}", desk.getKey(), desk.getCurSet());
			return;
		}
		setRecord.makeEndInfo(desk);
	}

	private SetRecord getCurSetRecord(int curSet) {
		int index = curSet - 1;
		if (index < 0 || index >= setRecords.size()) {
			return null;
		} else {
			return setRecords.get(index);
		}
	}

/*	public void save() throws Exception {
		this.rid = RecordService.getInstance().getNextRecordId();
		// 存储玩家的信息
		for (GamePlayer player : desk.getAllPlayers()) {
			UserService.addRoomRecord(player.getId(), this.rid, RedisKeyConst.MAHJONG_RECORD);
		}
		// 如果是俱乐部记录 要记录俱乐部
		int clubid = desk.getClubid();
		if (clubid > 0) {
			ClubService.saveClubRoomRecord(clubid, ServerType.TYPE_MAHJONG, String.valueOf(this.rid));
		}
		// 注意一定要先存储setrecord
		for (SetRecord setRecord : setRecords) {
			setRecord.save();
		}
		// 存储本局的基本信息情况
		RecordService.addRoomRecordBaseInfo(this.rid, getBaseSFSObj().toBinary());

		// 存储本局详细信息
		RecordService.addRoomRecordDescInfo(this.rid, getSetDescSFSObj().toBinary());
	}*/

/*	private SFSObject getSetDescSFSObj() {
		SFSObject sfsObject = new SFSObject();
		SFSArray setArray = new SFSArray();
		for (SetRecord setRecord : this.setRecords) {
			setArray.addSFSObject(setRecord.getBaseInfo());
		}
		sfsObject.putSFSArray("sets", setArray);
		VoteTask voteTask = desk.getVoteTask();
		if (voteTask != null) {
			SFSObject voteSFSObj = new SFSObject();
			voteSFSObj.putInt("uid", voteTask.getUid());
			voteSFSObj.putIntArray("agrees", voteTask.getAgreeUids());
			voteSFSObj.putIntArray("refuses", voteTask.getDisAgreeUids());
			voteSFSObj.putBool("inStart", voteTask.inStarting());
			sfsObject.putSFSObject("vote", voteSFSObj);
		}
		sfsObject.putSFSObject("roomSet", desk.getCreateRoomParamToSFS());
		return sfsObject;
	}

	private SFSObject getBaseSFSObj() {
		SFSObject sfsObject = new SFSObject();
		sfsObject.putInt("rid", rid);
		sfsObject.putInt("room", desk.getRoomid());
		sfsObject.putInt("time", (int) (desk.getTime() / 1000));
		SFSArray setArrays = new SFSArray();
		// 每个人的最终得分
		for (Player player : desk.getAllPlayers()) {
			LoginUser loginUser = player.getLoginUser();
			SFSObject userINfoObj = new SFSObject();
			userINfoObj.putInt("uid", player.getid());
			userINfoObj.putUtfString("un", loginUser.getName());
			userINfoObj.putUtfString("head", loginUser.getHead());
			userINfoObj.putIntArray("points", player.getMjPlayer().getGameResult().getFinalGamePoints().getPoints());
			userINfoObj.putUtfString("sex", loginUser.getSex());
			setArrays.addSFSObject(userINfoObj);
		}
		sfsObject.putSFSArray("users", setArrays);
		return sfsObject;
	}

	public void addUserOperCard(int curSet, OperDetail operDetail) {
		SetRecord setRecord = getCurSetRecord(curSet);
		if (setRecord == null) {
			logger.error(String.format("RoomRecord.addUserOperCard@do not has curset|curset=%d|operDetail=%s", curSet,
					operDetail.toSfsObject().toString()));
			return;
		}
		setRecord.addOperInfo(operDetail);
	}*/

}
