package com.kuaikai.game.mahjong.engine.model;

/**
 * Wan(11-19), Tiao(21-29), Tong(31-39), Zi(41-47);
 * 
 */
public class Mahjong {
    
	// 玩家座位：东南西北
	public static final int POS_DONG	= 1;
	public static final int POS_NAN		= 2;
	public static final int POS_XI		= 3;
	public static final int POS_BEI		= 4;
	
	public static final int POS_MIN		= POS_DONG;
	public static final int POS_MAX		= POS_BEI;
	
    public enum CardType {

        WAN(10), TIAO(20), TONG(30), ZI(40), HUA(50), ALMIGHTY(100);

        int value;
        CardType(int value){
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }

        public static CardType getType(int value){
            for (CardType s : values()) {
                if (s.getValue() == value)
                    return s;
            }
            return null;
        }
        
        public static boolean isWanTiaoTong(CardType type) {
        	return WAN.equals(type) || TIAO.equals(type) || TONG.equals(type);
        }
        
    }

    public static CardType getCardType(int card) {
    	card -= getRemain(card);
    	return CardType.getType(card);
    }
    
    public enum Zi {
    	DONG(41), NAN(42), XI(43), BEI(44), ZHONG(45), FA(46), BAI(47);
    	
        int value;
        Zi(int value){
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }

        public static Zi get(int value){
            for (Zi s : values()) {
                if (s.getValue() == value)
                    return s;
            }
            return null;
        }
    }

    public static int getRemain(int card) {
    	return card % 10;
    }
	
	/**
	 * 返回指定牌的下一张牌值，1万的下一张为2万，9万的下一张为1万，东的下一张为南，白板的下一张为东
	 */
	public static int getNextCardNum(int card) {
		CardType type = getCardType(card);
		switch(type) {
		case WAN :
		case TIAO :
		case TONG :
			if(getRemain(card) == 9) return card-8;
			break;
		case ZI :
			if(card == Zi.BAI.getValue()) return Zi.DONG.getValue();
			break;
		default :
			break;
		}
		
		return card+1;
	}

	/**
	 * 返回和指定牌差值的牌，不存在或不是同一花色时返回0
	 */
	public static int getDeltaCardNum(int card, int delta) {
		int cardNew = card+delta;
		CardType typeNew = getCardType(cardNew);
		CardType type = getCardType(card);
		if(!type.equals(typeNew)) return 0;
		
		return isValid(typeNew, cardNew)?cardNew:0;
	}

	/**
	 * 返回和指定牌值是否是有效的牌
	 */
	public static boolean isValid(CardType type, int card) {
		int remain = getRemain(card);
		switch(type) {
		case WAN :
		case TIAO :
		case TONG :
			return remain >= 1 && remain <= 9;
		case ZI :
			return remain >= 1 && remain <= 7;
		case HUA :
			return true;
		case ALMIGHTY :
			return remain == 0;
		default :
			return false;
		}
	}
	
	public static boolean isValid(int card) {
		return isValid(getCardType(card), card);
	}
	
	public static boolean isYaoJiu(int card) {
		if(!isWanTiaoTong(card)) return false;
		int remain = getRemain(card);
		return (remain == 1 || remain == 9);
	}

	public static boolean isZi(int card) {
		return Mahjong.CardType.ZI.equals(getCardType(card));
	}

	public static boolean isFeng(int card) {
		return card >= Mahjong.Zi.DONG.getValue() && card <= Mahjong.Zi.BEI.getValue();
	}

	public static boolean isJian(int card) {
		return card >= Mahjong.Zi.ZHONG.getValue() && card <= Mahjong.Zi.BAI.getValue();
	}

	public static boolean isWanTiaoTong(int card) {
		switch(getCardType(card)) {
		case WAN :
		case TIAO :
		case TONG :
			return true;
		default :
			break;
		}
		return false;
	}
	
}
