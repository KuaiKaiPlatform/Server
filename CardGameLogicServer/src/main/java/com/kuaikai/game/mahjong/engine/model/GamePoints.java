package com.kuaikai.game.mahjong.engine.model;

import java.util.ArrayList;
import java.util.List;

public class GamePoints {

	private List<Integer> points = new ArrayList<>();				// 各项得失分列表，各项顺序由玩法约定（一般只有总分项），如：芜湖麻将，底分和交分分别统计
	
	public GamePoints() {
		
	}
	
/*	public GamePoints(int size) {
		this.initPoints(size, 0);
	}*/
	
	public GamePoints(int size, int point) {
		this.initPoints(size, point);
	}
	
	public List<Integer> getPoints() {
		return points;
	}

	public int getPoint(int index) {
    	if(index >= points.size()) return 0;
    	return points.get(index);
    }
    
    public void initPoints(int size, int point) {
    	points.clear();
    	for(int i=0; i<size; i++) {
    		points.add(point);
    	}
    }
    
    /*
     * 修改一项得失分
     */
    public void changePoint(int index, int delta) {
    	if(index > points.size()) return;
    	if(index == points.size()) {		// 增加
    		points.add(index, delta);
    		return;
    	}
        points.set(index, points.get(index) + delta);	// 修改存在项
    }

    /*
     * 设置一项得失分
     */
    public void setPoint(int index, int point) {
    	if(index >= points.size()) return;
        points.set(index, point);
    }
    
    /*
     * 返回总得分
     */
    public int getTotalPoint() {
    	int count = 0;
    	for(int point : points) {
    		count += point;
    	}
    	return count;
    }
    
 /*   public int getSize() {
    	return points.size();
    }*/
	
}
