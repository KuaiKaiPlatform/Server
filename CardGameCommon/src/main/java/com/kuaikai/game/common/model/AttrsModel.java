package com.kuaikai.game.common.model;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.kuaikai.game.common.utils.CollectionUtils;

public class AttrsModel {

	protected Map<Object, Object> attrs = new HashMap<Object, Object>();
	
	public Map<Object, Object> getAttrs() {
		return attrs;
	}

	public void putAttrs(Map<Object, Object> attrs) {
		this.attrs.putAll(attrs);
	}
	
	public String getAttrStr(Object key) {
		return (String)attrs.get(key);
	}

	public int getAttrInt(Object key) {
		return CollectionUtils.getMapInt(attrs, key);
	}

	public long getAttrLong(Object key) {
		return CollectionUtils.getMapLong(attrs, key);
	}
	
	public boolean getAttrBool(Object key) {
		return CollectionUtils.getMapBool(attrs, key);
	}
	
	public void changeAttr(Object key, Object obj) {
		attrs.put(key, obj);
	}

	public void changeAttrIfNotExist(Object key, Object obj) {
		if(!attrs.containsKey(key)) attrs.put(key, obj);
	}
	
	public String toJson() {
		return JSONObject.toJSONString(attrs);
	}
	
}
