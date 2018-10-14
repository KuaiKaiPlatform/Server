package com.kuaikai.game.mahjong.engine.checker.paixin;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaikai.game.common.play.CardGameSetting;
import com.kuaikai.game.mahjong.engine.constants.PaiXin;
import com.kuaikai.game.mahjong.engine.model.CardGroup;
import com.kuaikai.game.mahjong.engine.model.MJCard;
import com.kuaikai.game.mahjong.engine.model.Mahjong;
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
		case PaiXin.SHI_SAN_YAO :
			if(ShiSanYao.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.SHI_SAN_YAO);
				//MahjongDesk desk = player.getDesk();
				//loggerSSY.info(desk.getRoomid() + "," + desk.getCurSet() + "," + player.getId() + "," + desk.getCreateRoomParam().getRule());
				return true;
			}
			break;
		case PaiXin.SHI_SAN_BU_KAO :
			switch(ShiSanBuKao.check(handlist, card, groupList, almightyCardNum)) {
			case 5 :
				paiXinChecker.addResult(PaiXin.SHI_SAN_BU_KAO);
				paiXinChecker.addResult(PaiXin.WU_XING_BU_KAO);
				return true;
			case 6 :
				paiXinChecker.addResult(PaiXin.SHI_SAN_BU_KAO);
				return true;
			case 7 :
				paiXinChecker.addResult(PaiXin.SHI_SAN_BU_KAO);
				paiXinChecker.addResult(PaiXin.QI_XING_BU_KAO);
				return true;
			}
			break;
		case PaiXin.SHI_SAN_LUAN_KAO :
			switch(ShiSanLuanKao.check(handlist, card, groupList, almightyCardNum)) {
			case 5 :
				paiXinChecker.addResult(PaiXin.SHI_SAN_LUAN_KAO);
				paiXinChecker.addResult(PaiXin.WU_XING_LUAN_KAO);
				return true;
			case 6 :
				paiXinChecker.addResult(PaiXin.SHI_SAN_LUAN_KAO);
				return true;
			case 7 :
				paiXinChecker.addResult(PaiXin.SHI_SAN_LUAN_KAO);
				paiXinChecker.addResult(PaiXin.QI_XING_LUAN_KAO);
				return true;
			}
			break;
		case PaiXin.QI_DUI :
			if(player.getGameDesk().getSetting().getBool(CardGameSetting.NO_QI_DUI)) break;
			
			switch(QiDui.check(handlist, card, groupList, almightyCardNum)) {
			case 0 :	// 普通七对
				paiXinChecker.addResult(PaiXin.QI_DUI);
				logger.debug("qidui checked");
				return true;
			case 1 :	// 豪华七对
				paiXinChecker.addResult(PaiXin.HAO_QI_DUI);
				logger.debug("haoqidui checked");
				return true;
			case 2 :	// 超豪华七对
				paiXinChecker.addResult(PaiXin.CHAO_HAO_QI_DUI);
				logger.debug("chaohaoqidui checked");
				return true;
			case 3 :	// 最豪华七对
				paiXinChecker.addResult(PaiXin.ZUI_HAO_QI_DUI);
				logger.debug("zuihaohaoqidui checked");
				return true;
			}
			break;
		case PaiXin.ZI_YI_SE :
			if(ZiYiSe.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.ZI_YI_SE);
				return true;
			}
			break;
		case PaiXin.QING_YI_SE :
			if(QingYiSe.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.QING_YI_SE);
				return true;
			}
			break;
		case PaiXin.HUN_YI_SE :
			if(HunYiSe.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.HUN_YI_SE);
				return true;
			}
			break;
		case PaiXin.PENG_PENG_HU :
			if(PengPengHu.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.PENG_PENG_HU);
				return true;
			}
			break;
		case PaiXin.QUAN_QIU_REN :
			if(QuanQiuRen.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.QUAN_QIU_REN);
				return true;
			}
			break;
		case PaiXin.SHUANG_BA_ZHI :
			if(ShuangBaZhi.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.SHUANG_BA_ZHI);
				return true;
			}
			break;
		case PaiXin.SHUANG_SI_HE :
			if(ShuangSiHe.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.SHUANG_SI_HE);
				return true;
			}
			break;
		case PaiXin.BA_ZHI :
			if(BaZhi.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.BA_ZHI);
				return true;
			}
			break;
		case PaiXin.SI_HE :
		case PaiXin.GEN_HU :
			if(SiHe.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.SI_HE);
				return true;
			}
			break;
		case PaiXin.BIAN_ZHANG :
			if(BianZhang.check(handlist, card, groupList, almightyCardNum, paiXinChecker.getPlayer())) {
				paiXinChecker.addResult(PaiXin.BIAN_ZHANG);
				return true;
			}
			break;
		case PaiXin.DAN_DIAO_JIANG :
			if(DanDiaoJiang.check(handlist, card, groupList, almightyCardNum, paiXinChecker.getPlayer())) {
				paiXinChecker.addResult(PaiXin.DAN_DIAO_JIANG);
				return true;
			}
			break;
		case PaiXin.KAN_ZHANG :
			if(KanZhang.check(handlist, card, groupList, almightyCardNum, paiXinChecker.getPlayer())) {
				paiXinChecker.addResult(PaiXin.KAN_ZHANG);
				return true;
			}
			break;
		case PaiXin.YI_TIAO_LONG :
			if(YiTiaoLong.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.YI_TIAO_LONG);
				return true;
			}
			break;
		case PaiXin.SHUANG_LIAN_LIU :
			if(ShuangLianLiu.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.SHUANG_LIAN_LIU);
				return true;
			}
			break;
		case PaiXin.LIAN_LIU :
			if(LianLiu.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.LIAN_LIU);
				return true;
			}
			break;
		case PaiXin.YI_SE_SAN_LIAN_DUI :
			if(SanLianDui.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.YI_SE_SAN_LIAN_DUI);
				return true;
			}
			break;
		case PaiXin.BIAO_ZHUN_HU :
			if(BiaoZhunHu.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.BIAO_ZHUN_HU);
				return true;
			}
			break;
		case PaiXin.ALMIGHTY_4 :
			if(AlmightyX.check(handlist, card, groupList, almightyCardNum, 4, paiXinChecker.getPlayer())) {
				paiXinChecker.addResult(PaiXin.ALMIGHTY_4);
				return true;
			}
			break;
		case PaiXin.SHI_YI_ZHI :
			if(ShiYiZhi.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.SHI_YI_ZHI);
				return true;
			}
			break;
		case PaiXin.MEN_QIAN_QING :
			if(MenQianQing.check(handlist, card, groupList, almightyCardNum, paiXinChecker.getPlayer())) {
				paiXinChecker.addResult(PaiXin.MEN_QIAN_QING);
				return true;
			}
			break;
		case PaiXin.QUE_YI_MEN :
			if(QueYiMen.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.QUE_YI_MEN);
				return true;
			}
			break;
		case PaiXin.DUAN_YAO_JIU :
			if(DuanYaoJiu.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.DUAN_YAO_JIU);
				return true;
			}
			break;
		case PaiXin.HUN_YAO_JIU :
			if(HunYaoJiu.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.HUN_YAO_JIU);
				return true;
			}
			break;
		case PaiXin.SHUANG_TONG :
			if(ShuangTong.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.SHUANG_TONG);
				return true;
			}
			break;
		case PaiXin.QI_TONG :
			if(QiTong.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.QI_TONG);
				return true;
			}
			break;
		case PaiXin.WU_TONG :
			if(WuTong.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.WU_TONG);
				return true;
			}
			break;
		case PaiXin.QUAN_LAO :
			if(QuanLao.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.QUAN_LAO);
				return true;
			}
			break;
		case PaiXin.QUAN_XIAO :
			if(QuanXiao.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.QUAN_XIAO);
				return true;
			}
			break;
		case PaiXin.SHI_LAO :
			if(ShiLao.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.SHI_LAO);
				return true;
			}
			break;
		case PaiXin.SHI_XIAO :
			if(ShiXiao.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.SHI_XIAO);
				return true;
			}
			break;
		case PaiXin.AN_KE :
			Set<Integer> anKes = AnKe.check(handlist, card, groupList, almightyCardNum, paiXinChecker.getPlayer());
			if(anKes != null && !anKes.isEmpty()) {
				paiXinChecker.putExtra(PaiXin.AN_KE, anKes);
				return true;
			}
			break;
		case PaiXin.MING_KE :
			Set<Integer> mingKes = MingKe.check(handlist, card, groupList, almightyCardNum, paiXinChecker.getPlayer());
			if(mingKes != null && !mingKes.isEmpty()) {
				paiXinChecker.putExtra(PaiXin.MING_KE, mingKes);
				return true;
			}
			break;
		case PaiXin.ZHI_SHU :
			Map<Mahjong.CardType, Integer> zhiShu = ZhiShu.check(handlist, card, groupList, almightyCardNum);
			if(zhiShu != null && !zhiShu.isEmpty()) {
				paiXinChecker.putExtra(PaiXin.ZHI_SHU, zhiShu);
				return true;
			}
			break;
		case PaiXin.GANG_PAI :
			Map<Integer, Integer> gangPai = GangPai.check(handlist, card, groupList, almightyCardNum);
			if(gangPai != null && !gangPai.isEmpty()) {
				paiXinChecker.putExtra(PaiXin.GANG_PAI, gangPai);
				return true;
			}
			break;
		case PaiXin.TONG_SHU :
			Map<Integer, Integer> tongShu = TongShu.check(handlist, card, groupList, almightyCardNum);
			if(tongShu != null && !tongShu.isEmpty()) {
				paiXinChecker.putExtra(PaiXin.TONG_SHU, tongShu);
				return true;
			}
			break;
		case PaiXin.LIAN_DUI :
			Map<Integer, Integer> lianDui = LianDui.check(handlist, card, groupList, almightyCardNum);
			if(lianDui != null && !lianDui.isEmpty()) {
				paiXinChecker.putExtra(PaiXin.LIAN_DUI, lianDui);
				return true;
			}
			break;
		case PaiXin.LUAN_ZI :
			if(LuanZi.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.LUAN_ZI);
				return true;
			}
			break;
		case PaiXin.WU_ZI :
			if(WuZi.check(handlist, card, groupList, almightyCardNum)) {
				paiXinChecker.addResult(PaiXin.WU_ZI);
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
