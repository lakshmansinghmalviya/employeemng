package com.ls.project.response;

import com.ls.project.config.request.LoginRequest;

public class Message {
	private String msg;

	public String getMsg() {
		return msg;
	}

	public Message() {
		super();
	}

	public Message(String msg) {
		super();
		this.msg = msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
