package com.ls.project.config.request;

public class LoginRequest {

	// press shift while hovering to see the impl in place
	// alt +Shift+A - toggle block action (selection tool)
	// quick access box - ctrl+3
	// ifnull - will put the null check
	// alt+shift+t - to extract the class field into new one

	private String email;
	private String password;

	public LoginRequest() {
		super();
	}

	public LoginRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "LoginRequest [email=" + email + ", password=" + password + "]";
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
