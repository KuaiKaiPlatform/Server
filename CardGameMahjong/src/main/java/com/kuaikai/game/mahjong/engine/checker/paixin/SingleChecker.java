package com.kuaikai.game.mahjong.engine.checker.paixin;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.kuaikai.game.mahjong.engine.paixin.AlmightyX;
import com.kuaikai.game.mahjong.engine.paixin.AnKe;
import com.kuaikai.game.mahjong.engine.paixin.BaZhi;
import com.kuaikai.game.mahjong.engine.paixin.BianZhang;
import com.kuaikai.game.mahjong.engine.paixin.BiaoZhunHu;
import com.kuaikai.game.mahjong.engine.paixin.DanDiaoJiang;
import com.kuaikai.game.mahjong.engine.paixin.DuanYaoJiu;
import com.kuaikai.game.mahjong.engine.paixin.GangPai;
import com.kuaikai.game.mahjong.engine.paixin.HunYaoJiu;
import com.kuaikai.game.mahjong.engine.paixin.HunYiSe;
import com.kuaikai.game.mahjong.engine.paixin.KanZhang;
import com.kuaikai.game.mahjong.engine.paixin.LianDui;
import com.kuaikai.game.mahjong.engine.paixin.LianLiu;
import com.kuaikai.game.mahjong.engine.paixin.LuanZi;
import com.kuaikai.game.mahjong.engine.paixin.MenQianQing;
import com.kuaikai.game.mahjong.engine.paixin.MingKe;
import com.kuaikai.game.mahjong.engine.paixin.PengPengHu;
import com.kuaikai.game.mahjong.engine.paixin.QiDui;
import com.kuaikai.game.mahjong.engine.paixin.QiTong;
import com.kuaikai.game.mahjong.engine.paixin.QingYiSe;
import com.kuaikai.game.mahjong.engine.paixin.QuanLao;
import com.kuaikai.game.mahjong.engine.paixin.QuanQiuRen;
import com.kuaikai.game.mahjong.engine.paixin.QuanXiao;
import com.kuaikai.game.mahjong.engine.paixin.QueYiMen;
import com.kuaikai.game.mahjong.engine.paixin.SanLianDui;
import com.kuaikai.game.mahjong.engine.paixin.ShiLao;
import com.kuaikai.game.mahjong.engine.paixin.ShiSanBuKao;
import com.kuaikai.game.mahjong.engine.paixin.ShiSanLuanKao;
import com.kuaikai.game.mahjong.engine.paixin.ShiSanYao;
import com.kuaikai.game.mahjong.engine.paixin.ShiXiao;
import com.kuaikai.game.mahjong.engine.paixin.ShiYiZhi;
import com.kuaikai.game.mahjong.engine.paixin.ShuangBaZhi;
import com.kuaikai.game.mahjong.engine.paixin.ShuangLianLiu;
import com.kuaikai.game.mahjong.engine.paixin.ShuangSiHe;
import com.kuaikai.game.mahjong.engine.paixin.ShuangTong;
import com.kuaikai.game.mahjong.engine.paixin.SiHe;
import com.kuaikai.game.mahjong.engine.paixin.TongShu;
import com.kuaikai.game.mahjong.engine.paixin.WuTong;
import com.kuaikai.game.mahjong.engine.paixin.WuZi;
import com.kuaikai.game.mahjong.engine.paixin.YiTiaoLong;
import com.kuaikai.game.mahjong.engine.paixin.ZhiShu;
import com.kuaikai.game.mahjong.engine.paixin.ZiYiSe;
import com.kuaikai.game.mahjong.msg.pb.CardTypePB.CardType;
import com.kuaikai.game.mahjong.msg.pb.JieSuanPB.JieSuan;

public class SingleChecker {
	
	protected static final Logger logger = LoggerFactory.getLogger("play");
	protected static final Logger loggerSSY = LoggerFactory.getLogger("shisanyao");
	
	private int paiXin;	// checker 本身要检查的牌型
	private Set<Integer> orDependencies		= new HashSet<Integer>();	// 本checker依赖的牌型（先检查过并且满足任一个依赖的牌型，才继续检查本牌型）
	private Set<Integer> andDependencies	= new HashSet<Integer>();	// 本checker依赖的牌型（先检查过并且满足所有依赖的牌型，才继续检查本牌型）
	
	protected SingleChecker nextChecker;		// 下级牌型检查器
	
	private PaiXinChecker paiXinChecker;	// 外部检查器，存放所有已检查到的牌型

	public SingleChecker() {}
	
	public SingleChecker(int paiXin, PaiXinChecker paiXinChecker) {
		this.paiXin = paiXin;
		this.paiXinChecker = paiXinChecker;
	}

	public void setNextChecker(SingleChecker checker) {
		this.nextChecker = checker;
	}
	
	public void addOrDependency(int dependency) {
		orDependencies.add(dependency);
	}

	public void addAndDependency(int dependency) {
		andDependencies.add(dependency);
	}
	
	/*
	 * 先根据 paiXin 值检查本身牌型能否胡牌，能胡，将 paiXin 值放到 paiXinChecker 中，结束。
	 * 不能胡，则检查nextChecker能否胡牌。
	 * 
	 * 
	 */
	public boolean check(MahjongPlayer player, List<MJCard> handlist, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		//if(logger.isDebugEnabled()) logger.debug("checking paixin:" + paiXin + ",cards=" + PaiXinHelper.toCardsList(handlist));
		
		if(!paiXinChecker.containsAnyResult(orDependencies)) {
			//logger.debug("orDependencies failed");
			checkNext(player, handlist, card, groupList, almightyCardNum);
			return false;
		}
		
		if(!paiXinChecker.containsAllResult(andDependencies)) {
			//logger.debug("andDependencies failed");
			checkNext(player, handlist, card, groupList, almightyCardNum);
			return false;
		}
		
		switch(paiXin) {
		case JieSuan.SHI_SAN_YAO_VALUE :
			if(ShiSanYao.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.SHI_SAN_YAO_VALUE);
				//MahjongDesk desk = player.getDesk();
				//loggerSSY.info(desk.getRoomid() + "," + desk.getCurSet() + "," + player.getId() + "," + desk.getCreateRoomParam().getRule());
				return true;
			}
			break;
		case JieSuan.SHI_SAN_BU_KAO_VALUE :
			switch(ShiSanBuKao.check(handlist, card, groupList, almightyCardNum)) {
			case 5 :
				paiXinChecker.addResult(JieSuan.SHI_SAN_BU_KAO_VALUE);
				paiXinChecker.addResult(JieSuan.WU_XING_BU_KAO_VALUE);
				return true;
			case 6 :
				paiXinChecker.addResult(JieSuan.SHI_SAN_BU_KAO_VALUE);
				return true;
			case 7 :
				paiXinChecker.addResult(JieSuan.SHI_SAN_BU_KAO_VALUE);
				paiXinChecker.addResult(JieSuan.QI_XING_BU_KAO_VALUE);
				return true;
			}
			break;
		case JieSuan.SHI_SAN_LUAN_KAO_VALUE :
			switch(ShiSanLuanKao.check(handlist, card, groupList, almightyCardNum)) {
			case 5 :
				paiXinChecker.addResult(JieSuan.SHI_SAN_LUAN_KAO_VALUE);
				paiXinChecker.addResult(JieSuan.WU_XING_LUAN_KAO_VALUE);
				return true;
			case 6 :
				paiXinChecker.addResult(JieSuan.SHI_SAN_LUAN_KAO_VALUE);
				return true;
			case 7 :
				paiXinChecker.addResult(JieSuan.SHI_SAN_LUAN_KAO_VALUE);
				paiXinChecker.addResult(JieSuan.QI_XING_LUAN_KAO_VALUE);
				return true;
			}
			break;
		case JieSuan.QI_DUI_VALUE :
			if(player.getGameDesk().getSetting().getBool(CardGameSetting.NO_QI_DUI)) break;
			
			switch(QiDui.check(handlist, card, groupList, almightyCardNum)) {
			case 0 :	// 普通七对
				paiXinChecker.addResult(JieSuan.QI_DUI_VALUE);
				logger.debug("qidui checked");
				return true;
			case 1 :	// 豪华七对
				paiXinChecker.addResult(JieSuan.HAO_QI_DUI_VALUE);
				logger.debug("haoqidui checked");
				return true;
			case 2 :	// 超豪华七对
				paiXinChecker.addResult(JieSuan.CHAO_HAO_QI_DUI_VALUE);
				logger.debug("chaohaoqidui checked");
				return true;
			case 3 :	// 最豪华七对
				paiXinChecker.addResult(JieSuan.ZUI_HAO_QI_DUI_VALUE);
				logger.debug("zuihaohaoqidui checked");
				return true;
			}
			break;
		case JieSuan.ZI_YI_SE_VALUE :
			if(ZiYiSe.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.ZI_YI_SE_VALUE);
				return true;
			}
			break;
		case JieSuan.QING_YI_SE_VALUE :
			if(QingYiSe.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.QING_YI_SE_VALUE);
				return true;
			}
			break;
		case JieSuan.HUN_YI_SE_VALUE :
			if(HunYiSe.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.HUN_YI_SE_VALUE);
				return true;
			}
			break;
		case JieSuan.PENG_PENG_HU_VALUE :
			if(PengPengHu.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.PENG_PENG_HU_VALUE);
				return true;
			}
			break;
		case JieSuan.QUAN_QIU_REN_VALUE :
			if(QuanQiuRen.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.QUAN_QIU_REN_VALUE);
				return true;
			}
			break;
		case JieSuan.SHUANG_BA_ZHI_VALUE :
			if(ShuangBaZhi.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.SHUANG_BA_ZHI_VALUE);
				return true;
			}
			break;
		case JieSuan.SHUANG_SI_HE_VALUE :
			if(ShuangSiHe.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.SHUANG_SI_HE_VALUE);
				return true;
			}
			break;
		case JieSuan.BA_ZHI_VALUE :
			if(BaZhi.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.BA_ZHI_VALUE);
				return true;
			}
			break;
		case JieSuan.SI_HE_VALUE :
		case JieSuan.GEN_HU_VALUE :
			if(SiHe.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.SI_HE_VALUE);
				return true;
			}
			break;
		case JieSuan.BIAN_ZHANG_VALUE :
			if(BianZhang.check(handlist, card, groupList, almightyCardNum, paiXinChecker.getPlayer())) {
				paiXinChecker.addResult(JieSuan.BIAN_ZHANG_VALUE);
				return true;
			}
			break;
		case JieSuan.DAN_DIAO_JIANG_VALUE :
			if(DanDiaoJiang.check(handlist, card, groupList, almightyCardNum, paiXinChecker.getPlayer())) {
				paiXinChecker.addResult(JieSuan.DAN_DIAO_JIANG_VALUE);
				return true;
			}
			break;
		case JieSuan.KAN_ZHANG_VALUE :
			if(KanZhang.check(handlist, card, groupList, almightyCardNum, paiXinChecker.getPlayer())) {
				paiXinChecker.addResult(JieSuan.KAN_ZHANG_VALUE);
				return true;
			}
			break;
		case JieSuan.YI_TIAO_LONG_VALUE :
			if(YiTiaoLong.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.YI_TIAO_LONG_VALUE);
				return true;
			}
			break;
		case JieSuan.SHUANG_LIAN_LIU_VALUE :
			if(ShuangLianLiu.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.SHUANG_LIAN_LIU_VALUE);
				return true;
			}
			break;
		case JieSuan.LIAN_LIU_VALUE :
			if(LianLiu.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.LIAN_LIU_VALUE);
				return true;
			}
			break;
		case JieSuan.YI_SE_SAN_LIAN_DUI_VALUE :
			if(SanLianDui.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.YI_SE_SAN_LIAN_DUI_VALUE);
				return true;
			}
			break;
		case JieSuan.BIAO_ZHUN_HU_VALUE :
			if(BiaoZhunHu.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.BIAO_ZHUN_HU_VALUE);
				return true;
			}
			break;
		case JieSuan.ALMIGHTY_4_VALUE :
			if(AlmightyX.check(handlist, card, groupList, almightyCardNum, 4, paiXinChecker.getPlayer())) {
				paiXinChecker.addResult(JieSuan.ALMIGHTY_4_VALUE);
				return true;
			}
			break;
		case JieSuan.SHI_YI_ZHI_VALUE :
			if(ShiYiZhi.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.SHI_YI_ZHI_VALUE);
				return true;
			}
			break;
		case JieSuan.MEN_QIAN_QING_VALUE :
			if(MenQianQing.check(handlist, card, groupList, almightyCardNum, paiXinChecker.getPlayer())) {
				paiXinChecker.addResult(JieSuan.MEN_QIAN_QING_VALUE);
				return true;
			}
			break;
		case JieSuan.QUE_YI_MEN_VALUE :
			if(QueYiMen.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.QUE_YI_MEN_VALUE);
				return true;
			}
			break;
		case JieSuan.DUAN_YAO_JIU_VALUE :
			if(DuanYaoJiu.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.DUAN_YAO_JIU_VALUE);
				return true;
			}
			break;
		case JieSuan.HUN_YAO_JIU_VALUE :
			if(HunYaoJiu.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.HUN_YAO_JIU_VALUE);
				return true;
			}
			break;
		case JieSuan.SHUANG_TONG_VALUE :
			if(ShuangTong.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.SHUANG_TONG_VALUE);
				return true;
			}
			break;
		case JieSuan.QI_TONG_VALUE :
			if(QiTong.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.QI_TONG_VALUE);
				return true;
			}
			break;
		case JieSuan.WU_TONG_VALUE :
			if(WuTong.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.WU_TONG_VALUE);
				return true;
			}
			break;
		case JieSuan.QUAN_LAO_VALUE :
			if(QuanLao.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.QUAN_LAO_VALUE);
				return true;
			}
			break;
		case JieSuan.QUAN_XIAO_VALUE :
			if(QuanXiao.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.QUAN_XIAO_VALUE);
				return true;
			}
			break;
		case JieSuan.SHI_LAO_VALUE :
			if(ShiLao.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.SHI_LAO_VALUE);
				return true;
			}
			break;
		case JieSuan.SHI_XIAO_VALUE :
			if(ShiXiao.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.SHI_XIAO_VALUE);
				return true;
			}
			break;
		case JieSuan.AN_KE_VALUE :
			Set<Integer> anKes = AnKe.check(handlist, card, groupList, almightyCardNum, paiXinChecker.getPlayer());
			if(anKes != null && !anKes.isEmpty()) {
				paiXinChecker.putExtra(JieSuan.AN_KE_VALUE, anKes);
				return true;
			}
			break;
		case JieSuan.MING_KE_VALUE :
			Set<Integer> mingKes = MingKe.check(handlist, card, groupList, almightyCardNum, paiXinChecker.getPlayer());
			if(mingKes != null && !mingKes.isEmpty()) {
				paiXinChecker.putExtra(JieSuan.MING_KE_VALUE, mingKes);
				return true;
			}
			break;
		case JieSuan.ZHI_SHU_VALUE :
			Map<CardType, Integer> zhiShu = ZhiShu.check(handlist, card, groupList, almightyCardNum);
			if(zhiShu != null && !zhiShu.isEmpty()) {
				paiXinChecker.putExtra(JieSuan.ZHI_SHU_VALUE, zhiShu);
				return true;
			}
			break;
		case JieSuan.GANG_PAI_VALUE :
			Map<Integer, Integer> gangPai = GangPai.check(handlist, card, groupList, almightyCardNum);
			if(gangPai != null && !gangPai.isEmpty()) {
				paiXinChecker.putExtra(JieSuan.GANG_PAI_VALUE, gangPai);
				return true;
			}
			break;
		case JieSuan.TONG_SHU_VALUE :
			Map<Integer, Integer> tongShu = TongShu.check(handlist, card, groupList, almightyCardNum);
			if(tongShu != null && !tongShu.isEmpty()) {
				paiXinChecker.putExtra(JieSuan.TONG_SHU_VALUE, tongShu);
				return true;
			}
			break;
		case JieSuan.LIAN_DUI_VALUE :
			Map<Integer, Integer> lianDui = LianDui.check(handlist, card, groupList, almightyCardNum);
			if(lianDui != null && !lianDui.isEmpty()) {
				paiXinChecker.putExtra(JieSuan.LIAN_DUI_VALUE, lianDui);
				return true;
			}
			break;
		case JieSuan.LUAN_ZI_VALUE :
			if(LuanZi.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.LUAN_ZI_VALUE);
				return true;
			}
			break;
		case JieSuan.WU_ZI_VALUE :
			if(WuZi.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(JieSuan.WU_ZI_VALUE);
				return true;
			}
			break;
		}
		
		checkNext(player, handlist, card, groupList, almightyCardNum);
		return false;
	}
	
	protected void checkNext(MahjongPlayer player, List<MJCard> handlist, MJCard card, List<CardGroup> groupList, int almightyCardNum) {
		// 本checker不能胡，则检查nextChecker能否胡牌。
		if(nextChecker != null) {
			nextChecker.check(player, handlist, card, groupList, almightyCardNum);
		}
	}
	
}
