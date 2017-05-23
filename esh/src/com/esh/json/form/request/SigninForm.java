package com.esh.json.form.request;

import com.esh.utils.Utils;

public class SigninForm {

	private int userId=0;
	private String account=null;
	private String password=null;
	
	public SigninForm() {
		// TODO Auto-generated constructor stub
	}

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Utils.toMD5(password);
	}

	@Override
	public String toString() {
		return "SigninForm [userId=" + userId + ", account=" + account + ", password=" + password + "]";
	}
	
}
