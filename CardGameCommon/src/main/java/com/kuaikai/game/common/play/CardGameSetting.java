package com.kuaikai.game.common.play;

public class CardGameSetting {
	
	/** 支付方式：0 房主付，1 AA付，2 大赢家付  */
	//public final static String PAYER = "payer";
	
	/** 麻将局数：8、16  */
	public final static String TOTAL_SET = "totalSet";

	/** 麻将圈数：1、2、3  */
	public final static String TOTAL_QUAN = "totalQuan";
	
	/** 麻将底数（完成所有底时结束游戏）：1、2、3  */
	public final static String TOTAL_DI = "totalDi";
	
	/** 参与人数  */
	public final static String TOTAL_PLAYER = "totalPlayer";
	
	/** 开局最少人数  */
	public final static String MIN_PLAYER = "minPlayer";

	/** 首局手动准备（玩家选座时为手动准备）  */
	public final static String MANUAL_READY_TO_BEGIN = "manualReadyToBegin";

	/** 首局选座模式：SEQUENCE 按顺序入座、PLAYER 玩家选座、RANDOM 随机选座  */
	public final static String CHAIR_MODE = "chairMode";
	
	/** 麻将初始手牌数：13  */
	public final static String INIT_HAND_CARD_NUM = "initHandCardNum";

	/** 牌池剩余牌数时荒庄：0 */
	public final static String REMAIN_CARD_NUM = "remainCardNum";

	/** 基础分 */
	public final static String BASE_RATE = "baseRate";
	
	/** 胡牌基础分 */
	public final static String BASE_RATE_HU = "baseRateHu";

	/** 去筒：1 */
	public final static String QU_TONG = "quTong";
	
	/** 去条：1 */
	public final static String QU_TIAO = "quTiao";

	/** 去字  */
	public final static String QU_ZI = "quZi";

	/** 带红中 */
	public final static String HONG_ZHONG = "hongZhong";
	
	/** 红中赖子 */
	public final static String HONG_ZHONG_ALMIGHTY = "hongZhongAlmighty";

	/** 是否跟庄 */
	public final static String GEN_ZHUANG = "genZhuang";
	
	/** 是否一炮多响 */
	public final static String MULTIPLE_HU = "multipleHu";
	
	/** 是否庄家翻倍 */
	public final static String BANKER_DOUBLE = "bankerDouble";

	/** 是否荒庄翻倍 */
	public final static String HUANG_ZHUANG_DOUBLE = "huangZhuangDouble";
	
	/** 点炮三家付 */
	public final static String DIAN_PAO_PAYER_ALL = "dianPaoPayerAll";

	/** 点杠三家付 */
	public final static String DIAN_GANG_PAYER_ALL = "dianGangPayerAll";

	/** 点炮者支付点杠分 */
	public final static String DIAN_PAO_PAY_DIAN_GANG = "dianPaoPayDianGang";

	/** 点炮者支付补杠和暗杠分 */
	public final static String DIAN_PAO_PAY_OTHER_GANG = "dianPaoPayOtherGang";
	
	/** 点杠包三家：点杠一家付时生效 */
	public final static String DIAN_GANG_PAY_ALL = "dianGangPayAll";

	/** 点炮包三家：点炮一家付时生效 */
	public final static String DIAN_PAO_PAY_ALL = "dianPaoPayAll";
	
	/** 杠牌计分（不论是否胡牌） */
	public static final String GANG_DE_FEN = "gang_de_fen";

	/** 杠随胡走（胡牌才计杠分） */
	public static final String GANG_SUI_HU = "gang_sui_hu";
	
	/** 荒庄计杠分 */
	public final static String GANG_DE_FEN_HUANG_ZHUANG = "gang_de_fen_huang_zhuang";
	
	/** 明杠基础分 */
	public static final String BASE_RATE_MING_GANG = "base_rate_ming_gang";

	/** 暗杠基础分 */
	public static final String BASE_RATE_AN_GANG = "base_rate_an_gang";
	
	/** 结算显示明杠（补杠和点杠） */
	public final static String JIE_SUAN_MING_GANG = "jie_suan_ming_gang";
	
	/** 漏胡：0 有漏胡限制，1 无漏胡限制 */
	public final static String NO_LOU_HU = "no_lou_hu";

	/** 漏胡：0 漏胡一张，大于0 漏胡多张（比如能胡2万和5万，漏胡2万，5万也不能胡），-1 漏胡所有 */
	public final static String LOU_HU_NUM = "lou_hu_num";
	
	/** 漏碰：0 无漏碰限制，1 有漏碰限制 */
	public final static String LOU_PENG = "lou_peng";
	
	/** 是否可吃牌 */
	public final static String KE_CHI = "ke_chi";
	
	/** 是否可听 */
	public final static String KE_TING = "ke_ting";

	/** 起手报听（发牌后所有人报听） */
	public final static String TING_QI_SHOU = "ting_qi_shou";
	
	/** 闲家起手报听（未做吃碰杠等动作，摸第一张牌前报听） */
	public final static String TING_XIAN_QI_SHOU = "ting_xian_qi_shou";

	/** 庄家起手报听（发牌后摸第一张牌前报听） */
	public final static String TING_ZHUANG_QI_SHOU = "ting_zhuang_qi_shou";
	
	/** 是否可碰翻 */
	public final static String KE_PENG_FAN = "ke_peng_fan";
	
	/** 吃后可补杠 */
	public final static String CHI_HOU_BU_GANG = "chi_hou_bu_gang";

	/** 吃后可暗杠 */
	public final static String CHI_HOU_AN_GANG = "chi_hou_an_gang";
	
	/** 碰后可补杠 */
	public final static String PENG_HOU_BU_GANG = "peng_hou_bu_gang";

	/** 碰后可暗杠 */
	public final static String PENG_HOU_AN_GANG = "peng_hou_an_gang";

	/** 听后可补杠 */
	public final static String TING_HOU_BU_GANG = "ting_hou_bu_gang";

	/** 听后可暗杠 */
	public final static String TING_HOU_AN_GANG = "ting_hou_an_gang";

	/** 听后可接杠 */
	public final static String TING_HOU_JIE_GANG = "ting_hou_jie_gang";
	
	/** 自摸的牌不算暗刻 */
	public final static String MO_NOT_AN_KE = "mo_not_an_ke";

	/** 自摸的牌算明刻 */
	public final static String MO_MING_KE = "mo_ming_ke";
	
	/** 门前清不允许暗杠 */
	public final static String MEN_QIAN_QING_NOT_AN_GANG = "men_qian_qing_not_an_gang";
	
	/** 万能牌张数时计所有牌（手牌、明牌和其他玩家打的牌） */
	public final static String ALMIGHTY_X_COUNT_ALL = "almighty_x_count_all";
	
	/** 万能牌还原后是否可吃（包括被吃和吃其他牌） */
	public final static String ALMIGHTY_CHI = "almighty_chi";

	/** 万能牌还原后是否可碰 */
	public final static String ALMIGHTY_PENG = "almighty_peng";

	/** 万能牌还原后是否可杠 */
	public final static String ALMIGHTY_GANG = "almighty_gang";

	/** 判断手牌中没有万能牌时略过胡的那张牌  */
	public final static String ALMIGHTY_NONE_IGNORE_HU_CARD = "almighty_none_ignore_hu_card";
	
	/** 不可胡七对 */
	public final static String NO_QI_DUI = "no_qi_dui";

	/** 荒庄后庄家是否继续坐庄  */
	public final static String HUANG_ZHUANG_CONTINUE = "huang_zhuang_continue";

	/** 胡牌后是否坐庄  */
	public final static String HU_TAKE_ZHUANG = "hu_take_zhuang";

	/** 胡牌后是否点炮者坐庄  */
	public final static String DIAN_PAO_TAKE_ZHUANG = "dian_pao_take_zhuang";
	
	/** 是否有定万能牌步骤，发牌后进行该步骤  */
	public final static String DING_ALMIGHTY = "ding_almighty";

	/** 定万能牌时将翻牌作为万能牌，而不是下一张  */
	public final static String DING_ALMIGHTY_FAN = "ding_almighty_fan";
	
	/** 定万能牌时保留翻牌，不移出牌池  */
	public final static String DING_ALMIGHTY_REMAIN_IN_POOL = "ding_almighty_remain_in_pool";

	/** 是否有下注步骤  */
	public final static String XIA_ZHU = "xia_zhu";
	
	/** 是否有定缺步骤  */
	public final static String DING_QUE = "ding_que";

	/** 牌墩最后一张牌显示在牌桌上  */
	public final static String SHOW_LAST = "show_last";
	
	/** 是否只能自摸 */
	public final static String ZI_MO_ONLY = "zi_mo_only";
	
	/** 听所有牌时能点炮胡 */
	public final static String TING_ALL_DIAN = "ting_all_dian";

	/** 听所有牌时能抢杠胡 */
	public final static String TING_ALL_QIANG_GANG = "ting_all_qiang_gang";
	
	/** 是否必须够八支：1 必需八支，0 无需八支 */
	public final static String BA_ZHI = "ba_zhi";
	
	/** 是否必须缺一门 */
	public final static String QUE_YI_MEN = "que_yi_men";

	/** 不能抢杠胡 */
	public final static String NO_QIANG_GANG = "no_qiang_gang";

	/** 抢杠胡时三家付 */
	public final static String QIANG_GANG_PAYER_ALL = "qiang_gang_payer_all";
	
	/** 抢杠胡包三家 */
	public final static String QIANG_GANG_PAY_ALL = "qiang_gang_pay_all";
	
	/** 房卡消耗倍数（默认房卡数乘以倍数为最终消耗房卡数） */
	public final static String ROOM_CARD_MULTI = "room_card_multi";
	
	
	/** 十三幺 */
	public final static String SHI_SAN_YAO = "shi_san_yao";

	/** 十三不靠 */
	public final static String SHI_SAN_BU_KAO = "shi_san_bu_kao";
	

	/*
	 * 以下为陕西麻将特有设置
	 */
	
	/** 陕西麻将下炮子：小于0时表示自由炮；等于0表示没有下炮子玩法；大于0表示固定炮 */
	public final static String PAO_ZI = "pao_zi";
	
	/** 亮六飞一底分，使用 BASE_RATE */ 
	//public final static String LIANG_DI_FEN = "liang_di_fen";

	/** 159麻将买码 */
	public final static String MA_159 = "ma_159";
	
	/** 划水麻将抓鱼 */
	public final static String HUA_SHUI_YU = "hua_shui_yu";
	
	/** 汉中麻将加番 */
	public final static String HAN_ZHONG_JIA_FAN = "han_zhong_jia_fan";
	
	/*
	 * 以下为安徽麻将特有设置
	 */
	
	/** 芜湖麻将番数规则："315"，"525" */
	public final static String WU_HU_FAN_TYPE = "wu_hu_fan_type";

	/** 芜湖麻将报警规则：1 报警，0 不报警 */
	public final static String WU_HU_ALERT = "wu_hu_alert";
	
	/** 无为麻将带对子（对子计分）： */
	public final static String WU_WEI_DAI_DUI_ZI = "wu_wei_dai_dui_zi";

	/** 无为麻将庄底：2、5 */
	public final static String WU_WEI_ZHUANG_DI = "wu_wei_zhuang_di";
	
	/** 歙县麻将顺反包 */
	public final static String SHE_XIAN_SHUN_FAN_BAO = "she_xian_shun_fan_bao";	

	/** 歙县麻将32分封顶 */
	public final static String SHE_XIAN_32_FENG_DING = "she_xian_32_feng_ding";

	/** 和县麻将刀子数额：10、15、25、50、100 */
	public final static String HE_XIAN_DAO_SHU = "he_xian_dao_shu";

	/** 和县麻将附加大拿(31+10) */
	public final static String HE_XIAN_31_10 = "he_xian_31_10";

	/** 和县麻将附加大拿(点炮50点) */
	public final static String HE_XIAN_DIAN_50 = "he_xian_dian_50";
	
	/** 和县麻将附加大拿(自摸100点) */
	public final static String HE_XIAN_ZI_MO_100 = "he_xian_zi_mo_100";
	
	/** 和县麻将附加大拿(双通) */
	public final static String HE_XIAN_SHUANG_TONG = "he_xian_shuang_tong";
	
	/** 和县麻将附加大拿(双杠) */
	public final static String HE_XIAN_SHUANG_GANG = "he_xian_shuang_gang";
	
	/** 和县麻将附加大拿(双连号) */
	public final static String HE_XIAN_SHUANG_LIAN_HAO = "he_xian_shuang_lian_hao";
	
	/** 和县麻将附加大拿(三大坎带头) */
	public final static String HE_XIAN_SAN_DA_KAN_DAI_TOU = "he_xian_san_da_kan_dai_tou";
	
	/** 明光麻将大牌自摸翻倍 */
	public final static String MING_GUANG_ZI_MO_DOUBLE = "ming_guang_zi_mo_double";
	
	
}
