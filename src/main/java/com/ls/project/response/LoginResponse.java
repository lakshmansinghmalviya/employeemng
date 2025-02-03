package com.ls.project.response;

public class LoginResponse {
	private String roles;

	public LoginResponse() {
		super();
	}

	public LoginResponse(String roles) {
		super();
		this.roles = roles;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "LoginResponse [roles=" + roles + "]";
	}
}
