package com.kuaikai.game.common.setting;

import com.kuaikai.game.common.model.AttrsModel;
import com.kuaikai.game.common.msg.CommonMsgHandler;
import com.kuaikai.game.common.msg.pb.GlobalSettingPB.GlobalSetting;
import com.kuaikai.game.common.redis.UserRedis;
import com.kuaikai.game.common.tcp.OnlineManager;
import com.kuaikai.game.hall.msg.MsgId;
import com.kuaikai.game.hall.msg.pb.SGlobalSettingPB.SGlobalSetting;

public class GlobalSettingManager {

	public static void onUserLogin(int uid) {
		AttrsModel setting = UserRedis.getAllSetting(uid);
		SGlobalSetting msg = SGlobalSetting.newBuilder()
				.setSetting(GlobalSetting.newBuilder().setJson(setting.toJson()))
				.build();
		// 发送
		OnlineManager.sendMsg(uid, new CommonMsgHandler(MsgId.SGlobalSetting, msg));
	}
	
}
