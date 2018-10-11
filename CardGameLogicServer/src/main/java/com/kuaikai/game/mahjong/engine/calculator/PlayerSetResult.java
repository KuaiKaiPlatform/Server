package com.kuaikai.game.mahjong.engine.calculator;

import java.util.ArrayList;
import java.util.List;

import com.kuaikai.game.mahjong.engine.model.GamePoints;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;

/*
 * 一名玩家一局的输赢明细
 */
public class PlayerSetResult {

	private MahjongPlayer player;
	private GamePoints gamePoints = new GamePoints();				// 各项得失分列表，各项顺序由玩法约定（一般只有总分项），如：芜湖麻将，底分和交分分别统计
	private List<ScoreDetail> scoreDetails = new ArrayList<>();		// 各结算项明细
	
	private boolean zimo;
	private boolean jiePao;
	private boolean dianPao;
	
	public PlayerSetResult(MahjongPlayer player, int pointsSize) {
		this.player = player;
		gamePoints.initPoints(pointsSize, 0);
	}
	
    public MahjongPlayer getPlayer() {
		return player;
	}
    
    public GamePoints getGamePoints() {
		return gamePoints;
	}

	/*
     * 增加结算项明细
     */
    public void addScoreDetail(ScoreDetail scoreDetail) {
    	//合并 mainType 相同的结算项
    	for(ScoreDetail detail : scoreDetails){
    		if(detail.getMainType() == scoreDetail.getMainType()) {
    			detail.setScore(detail.getScore() + scoreDetail.getScore());
    			return;
    		}
    	}
    	//增加结算项
    	scoreDetails.add(scoreDetail);
    }
    
    public void setZimo(boolean zimo) {
		this.zimo = zimo;
	}

	public void setJiePao(boolean jiePao) {
		this.jiePao = jiePao;
	}

	public void setDianPao(boolean dianPao) {
		this.dianPao = dianPao;
	}
    
    public SFSObject toSFSObject() {
    	SFSObject obj = new SFSObject();
        obj.putInt("uid", player.getId());
        obj.putIntArray("points", gamePoints.getPoints());		// 各项得失分列表
        obj.putBool("zimo", zimo);
        obj.putBool("dianPao", dianPao);
        obj.putBool("jiePao", jiePao);
        obj.putBool("baoTing", player.getMjPlayer().isBaoTing());
        
        // 结算项列表
        SFSArray scoreDetailArr = new SFSArray();
        for(ScoreDetail scoreDetail : scoreDetails) {
        	scoreDetailArr.addSFSObject(scoreDetail.toSFSObject());
        }
        obj.putSFSArray("scoreDetails", scoreDetailArr);
        
        // 手牌与明牌
        obj.putIntArray("handCards", player.getMjPlayer().getCardContainer().getHandCardValues());
        obj.putSFSArray("cardGroups", player.getMjPlayer().getCardContainer().getCardGroupsSFS());
        
        return obj;
    }
    
}
