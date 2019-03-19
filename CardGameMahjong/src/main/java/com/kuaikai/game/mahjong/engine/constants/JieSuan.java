package com.kuaikai.game.mahjong.engine.constants;

/*
 * 此类定义了显示在结算界面的相关信息
 */
public class JieSuan {
	
	// 通用结算（5001-9999 ）
	
	public static final int TIAN_HU						= 5001;			// 天胡
	public static final int DI_HU						= 5002;			// 地胡
	public static final int ZI_MO						= 5003;			// 自摸
	public static final int GANG_SHANG_HUA				= 5004;			// 杠上花
	public static final int QIANG_GANG_HU				= 5005;			// 抢杠胡
    public static final int DI_FEN						= 5006;			// 底分
//    public static final int MING_KE						= 5007;			// 明刻
//    public static final int AN_KE						= 5008;			// 暗刻
    public static final int MING_GANG					= 5009;			// 明杠
    public static final int AN_GANG						= 5010;			// 暗杠
    public static final int DIAN_GANG					= 5011;			// 点杠
    public static final int BU_GANG						= 5012;			// 补杠
    public static final int SI_GANG						= 5013;			// 四杠
    public static final int SAN_GANG					= 5014;			// 三杠
    public static final int SHUANG_GANG					= 5015;			// 双杠
    public static final int GANG						= 5016;			// 杠
    public static final int PENG						= 5017;			// 碰
    public static final int HU_PAI						= 5018;			// 胡牌
    public static final int ZHI_FEN						= 5019;			// 支分
    public static final int QI_SHOU_BAO_TING			= 5020;			// 起手报听
    public static final int HAI_DI_LAO					= 5021;			// 海底捞
    public static final int BET_SCORE					= 5022;			// 下注分
    public static final int GANG_HOU_PAO				= 5023;			// 杠后炮
    public static final int HUANG_ZHUANG_DOUBLE			= 5024;			// 荒庄翻倍
    public static final int GEN_ZHUANG					= 5025;			// 跟庄
    
	/*
	 *  陕西麻将结算(六位：61开头，615001-619999)
	 */
    public static final int PAO_ZI						= 615001;		// 炮子
    public static final int HUA_SHUI_YU					= 615002;		// 划水抓鱼
    public static final int DIAN_ZI						= 615003;		// 亮六飞一点子
    
	/*
	 *  安徽麻将结算(六位：34开头，345001-349999)
	 */
    
    // 芜湖麻将
    public static final int JIAO						= 345001;		// 交
    public static final int ZUI							= 345002;		// 嘴
    public static final int JIAO_ZUI					= 345003;		// 一交多嘴
    public static final int WUHU_DI						= 345004;		// 底番
    public static final int WUHU_ZHI					= 345005;		// 支番
    
    // 无为麻将
    public static final int ZHUANG_FEN					= 345006;		// 庄分
    public static final int BU_DONG_SHOU				= 345007;		// 不动手
    public static final int GANG_KAI_HU					= 345008;		// 杠开胡
    public static final int GANG_KAI_HU_YA_DANG			= 345009;		// 杠开胡（压档）
    public static final int DI_SI_GE_TOU_KA				= 345010;		// 第四个头卡
    public static final int HONG_ZHONG_MING_GANG		= 345011;		// 红中明杠
    public static final int HONG_ZHONG_AN_GANG			= 345012;		// 红中暗杠
    
    // 歙县麻将
    public static final int DA_CAI						= 345013;		// 打财
    public static final int WU_CAI						= 345014;		// 无财
    public static final int PENG_CAI					= 345015;		// 碰财神
    public static final int GANG_CAI					= 345016;		// 杠财神
    public static final int SHUN_FAN_BAO				= 345017;		// 顺反包
    
    // 和县麻将
    public static final int LAO_XIAO_DUI				= 345018;		// 老小对（一色幺九刻）
    public static final int BANG_BANG					= 345019;		// 棒棒（一色三连对）
    public static final int QUAN_YAO_DUI_DUI_HU			= 345020;		// 全幺对对胡（混幺九+碰碰胡）
    public static final int SI_DA_KAN					= 345021;		// 四大坎（一色四连刻或四字刻）
    public static final int SAN_DA_KAN					= 345022;		// 三大坎（一色三连刻或三字刻）
    public static final int SHUANG_LIAN_HAO				= 345023;		// 双连号
    public static final int XIAO_LIAN_HAO				= 345024;		// 小连号（连六）
    public static final int KAN_KAN_HU					= 345025;		// 坎坎胡（碰碰胡+门前清）
    public static final int SHUANG_BANG_BANG			= 345026;		// 双棒棒（一色三连对双）
    public static final int ZHONG_FA_BAI_SAN_DA_KAN		= 345027;		// 中发白三大坎（大三元）
    public static final int DA_DIAO_CHE					= 345028;		// 大吊车（全求人+碰碰胡）
    public static final int DA_NA						= 345029;		// 大拿
    public static final int QING_NA						= 345030;		// 清拿
    public static final int PING_NA						= 345031;		// 平拿
    public static final int MO_FEN						= 345032;		// 摸分
    public static final int DU_YA						= 345033;		// 独押（边张或坎张）
    public static final int KU_ZHI_YA					= 345034;		// 枯枝押（边张或坎张+绝张）
    public static final int ZHI							= 345035;		// 支
    public static final int TONG						= 345036;		// 通
    public static final int KAN_ZI						= 345037;		// 坎子
    public static final int SAN_DA_KAN_DAI_TOU			= 345038;		// 三大坎带头（一色三连刻或三字刻附一将）
    public static final int BAO_PAI						= 345039;		// 包牌
    public static final int MO_ZI						= 345040;		// 摸子
    public static final int LUAN_FENG					= 345041;		// 乱风
    
    // 明光麻将
    public static final int DU_YI						= 345042;		// 独一
    public static final int SHUANG_QING					= 345043;		// 双清
    public static final int SI_PEI_ZI					= 345044;		// 四配子
    public static final int PEI_ZI_CHI					= 345045;		// 配子吃
    public static final int PEI_ZI_CHI_PEI_ZI			= 345046;		// 配子吃配子
    public static final int DU_PEI_ZI					= 345047;		// 独配子
    
    // 来安麻将老三番
    public static final int KAN_JIANG					= 345048;		// 砍将
    public static final int FENG_JIANG					= 345049;		// 风将
    public static final int YING_QUE					= 345050;		// 硬缺
    public static final int RUAN_QUE					= 345051;		// 软缺
    
}
