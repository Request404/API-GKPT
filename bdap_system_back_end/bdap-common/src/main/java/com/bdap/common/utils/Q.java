package com.bdap.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bdap.common.exception.state.StateCode;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class Q extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param key 获取指定key的名字
	 */
	public <T> T getData(String key, TypeReference<T> typeReference){
		Object data = get(key);
		return JSON.parseObject(JSON.toJSONString(data), typeReference);
	}
	/**
	 * 复杂类型转换 TypeReference
	 */
	public <T> T getData(TypeReference<T> typeReference){
		Object data = get("data");
		return JSON.parseObject(JSON.toJSONString(data), typeReference);
	}

	public Q setData(Object data){
		put("data", data);
		return this;
	}
	public Q() {
		put("code", HttpStatus.SC_OK);
		put("msg", "success");
	}

	public static Q error() {
		return error(StateCode.UN_KNOW_EXCEPTION.getCode(), StateCode.UN_KNOW_EXCEPTION.getMsg());
	}

	public static Q error(String msg) {
		return error(StateCode.UN_KNOW_EXCEPTION.getCode(), msg);
	}

	public static Q error(int code, String msg) {
		Q q = new Q();
		q.put("code", code);
		q.put("msg", msg);
		return q;
	}

	public static Q ok(String msg) {
		Q q = new Q();
		q.put("msg", msg);
		return q;
	}

	public static Q ok(Map<String, Object> map) {
		Q q = new Q();
		q.putAll(map);
		return q;
	}

	public static Q ok() {
		return new Q();
	}

	public Q put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public Integer getCode() {
		return (Integer) this.get("code");
	}
}