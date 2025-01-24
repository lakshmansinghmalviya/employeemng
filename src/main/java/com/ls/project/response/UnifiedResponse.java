package com.ls.project.response;

import java.io.Serializable;

public class UnifiedResponse<T> implements Serializable {

	private Integer code;
	private String msg;
	private T data;

	public UnifiedResponse() {
	}

	public UnifiedResponse(Integer code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	// Getters and setters
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
