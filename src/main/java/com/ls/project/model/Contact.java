package com.ls.project.model;

public class Contact {
	private Profile profile;
	private String wa_id;
	@Override
	public String toString() {
		return "Contact [profile=" + profile + ", wa_id=" + wa_id + "]";
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public String getWa_id() {
		return wa_id;
	}
	public void setWa_id(String wa_id) {
		this.wa_id = wa_id;
	}
	
	
}
