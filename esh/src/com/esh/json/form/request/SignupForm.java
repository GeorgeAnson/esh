package com.esh.json.form.request;

import com.esh.utils.Utils;

public class SignupForm {

	private String account=null;
	private String password=null;
	
	public SignupForm() {
		// TODO Auto-generated constructor stub
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
		return "SigninForm [account=" + account + ", password=" + password + "]";
	}
	
	
}
