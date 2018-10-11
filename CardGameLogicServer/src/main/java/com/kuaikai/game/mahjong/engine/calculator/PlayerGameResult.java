package com.kuaikai.game.mahjong.engine.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kuaikai.game.common.utils.CollectionUtils;
import com.kuaikai.game.mahjong.engine.constants.RoomAttr;
import com.kuaikai.game.mahjong.engine.model.GamePoints;
import com.kuaikai.game.mahjong.engine.model.MahjongPlayer;

/*
 * 一名玩家整场牌局统计
 */
public class PlayerGameResult {

	private MahjongPlayer player;
	private Map<Object, Object> stats = new HashMap<Object, Object>();	// 各统计项数值，如：自摸、接炮、点炮、明杠、暗杠等
	private GamePoints finalGamePoints;									// 最终各项得失分列表，各项顺序由玩法约定（一般只有总分项），如：芜湖麻将，底分和交分分别统计
    private Map<Integer, GamePoints> di2Points; 						// 按底分玩法时，各底分数项列表
	
	public PlayerGameResult(MahjongPlayer player) {
		this.player = player;
		
		Calculator calculator = player.getDesk().getEngine().getCalculator();
		finalGamePoints = new GamePoints(calculator.getPointsSize(), calculator.getInitDiFen());
	}
	
    public MahjongPlayer getPlayer() {
		return player;
	}
    
    public void increaseStat(Object stat) {
    	CollectionUtils.increase(stats, stat);
    }
    
    public GamePoints getFinalGamePoints() {
		return finalGamePoints;
	}

	public GamePoints getCurrentDiGamePoints() {
		int di = player.getDesk().getEngine().getAttrInt(RoomAttr.CURRENT_DI);
    	if(di <= 0) return null;
    	if(di2Points == null) di2Points = new HashMap<Integer, GamePoints>();
    	
    	GamePoints diPoints = di2Points.get(di);
    	if(diPoints == null) {
    		Calculator calculator = player.getDesk().getEngine().getCalculator();
    		diPoints = new GamePoints(calculator.getPointsSize(), calculator.getInitDiFen());
    		di2Points.put(di, diPoints);
    	}
    	return diPoints;
    }
    
	public List<Integer> getCurrentDiPoints() {
		GamePoints diPoints = this.getCurrentDiGamePoints();
		return diPoints==null?new ArrayList<Integer>():diPoints.getPoints();
	}
	
	public boolean diOver() {
		GamePoints diPoints = this.getCurrentDiGamePoints();
		return diPoints==null?false:diPoints.getPoint(Calculator.INDEX_DI) <= 0;
	}
	
/*    public SFSObject toSFSObject() {
    	SFSObject obj = new SFSObject();
        obj.putInt("uid", player.getid());
        obj.putIntArray("points", finalGamePoints.getPoints());		// 各项得失分列表
        
        // 统计项
        for(Map.Entry<Object, Object> entry : stats.entrySet()) {
        	Object key = entry.getKey();
        	Object val = entry.getValue();
        	if(val instanceof Integer) {
        		obj.putInt(key.toString(), (Integer)val);
        	}
        }
        
        return obj;
    }*/
    
}
