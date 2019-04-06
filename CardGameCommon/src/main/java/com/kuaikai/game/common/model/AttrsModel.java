package com.kuaikai.game.common.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;
import com.kuaikai.game.common.utils.CollectionUtils;

public class AttrsModel {

	protected Map<Object, Object> attrs = new HashMap<Object, Object>();
	
	public AttrsModel put(Object key, Object value) {
		attrs.put(key, value);
		return this;
	}

	public AttrsModel putIfNotExist(Object key, Object obj) {
		if(!attrs.containsKey(key)) attrs.put(key, obj);
		return this;
	}
	
	public Map<Object, Object> getAll() {
		return attrs;
	}
	
	public AttrsModel putAll(Map map) {
		this.attrs.putAll(map);
		return this;
	}

	public boolean contains(Object key) {
		return attrs.containsKey(key);
	}
	
	public void remove(Object key) {
		attrs.remove(key);
	}

	public Object get(Object key) {
		return attrs.get(key);
	}
	
	public Map<String, String> getAllStr() {
		Map<String, String> result = new HashMap<String, String>();
		for(Map.Entry entry : attrs.entrySet()) {
			result.put(entry.getKey().toString(), entry.getValue().toString());
		}
		return result;
	}
	
	public String getStr(Object key) {
		return (String)attrs.get(key);
	}

	public int getInt(Object key) {
		return CollectionUtils.getMapInt(attrs, key);
	}

	public long getLong(Object key) {
		return CollectionUtils.getMapLong(attrs, key);
	}
	
	public boolean getBool(Object key) {
		return CollectionUtils.getMapBool(attrs, key);
	}
	
	public void copy(AttrsModel model) {
		this.attrs.putAll(model.getAll());
	}
	
	public void clear() {
		attrs.clear();
	}
	
	public String toJson() {
		return JSONObject.toJSONString(attrs);
	}
	
	public AttrsModel fromJson(String json) {
		JSONObject jsonObj = JSONObject.parseObject(json);
		for(Entry<String, Object> entry : jsonObj.entrySet()) {
			this.put(entry.getKey(), entry.getValue());
		}
		return this;
	}
	
}
