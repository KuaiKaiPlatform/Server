package com.kuaikai.game.mahjong.engine.model;

import com.kuaikai.game.mahjong.msg.pb.CardTypePB.CardType;

/**
 * 一张麻将牌
 */
public class MJCard {
	
    protected int value;
    protected MahjongPlayer player;
    
    protected boolean validAlmighty = true;	// 是否是有效的万能牌，当打出万能牌时，只做牌本身使用
    protected boolean showAlmighty = false;	// 是否是定万能牌时翻出的牌，摸牌时跳过这张牌
    
    protected MJCard(int value, MahjongPlayer player) {
    	this.value = value;
    	this.player = player;
    }
    
    public int getValue() {
		return value;
	}
    
	public MahjongPlayer getPlayer() {
		return player;
	}

	public CardType getCardType() {
    	return isAlmighty()?CardType.ALMIGHTY:Mahjong.getCardType(value);
    }
    
	public boolean isAlmighty() {
		int almighty = player.getGameDesk().getEngine().getAlmightyCardNum();
		return value == almighty;
	}

	public boolean isValidAlmighty() {
		return validAlmighty;
	}

	public void setValidAlmighty(boolean validAlmighty) {
		this.validAlmighty = validAlmighty;
	}
    
    public boolean isShowAlmighty() {
		return showAlmighty;
	}

	public void setShowAlmighty(boolean showAlmighty) {
		this.showAlmighty = showAlmighty;
	}

	public boolean isWanTiaoTong() {
    	return Mahjong.isWanTiaoTong(value);
    }
	
	public boolean isYaoJiu() {
		return Mahjong.isYaoJiu(value);
	}

	public boolean isZi() {
		return CardType.ZI.equals(getCardType());
	}

	public int getRemain() {
		return Mahjong.getRemain(value);
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
}
