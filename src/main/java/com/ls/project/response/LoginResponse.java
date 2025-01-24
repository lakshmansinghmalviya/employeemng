package com.ls.project.response;

import com.ls.project.enums.*;

public class LoginResponse {
	private Role role;

	public LoginResponse() {
		super();
	}

	public Role getRole() {
		return role;
	}
 
	public LoginResponse(Role role) {
		super();
		this.role = role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
