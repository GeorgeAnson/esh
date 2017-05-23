package com.esh.service;

import com.esh.json.form.request.SigninForm;
import com.esh.json.form.request.SignupForm;

public interface UserService {

	public int save(SignupForm signupForm);

	public int checkLoginInfo(SigninForm signinForm);

	public int check(SignupForm signupForm);

}
