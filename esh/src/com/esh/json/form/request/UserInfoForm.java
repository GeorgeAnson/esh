package com.esh.json.form.request;

import java.io.Serializable;

import com.esh.json.form.BasicUserInfo;
import com.esh.json.form.DetailUserInfo;

/**
 * �û���ϸ��Ϣ����������
 * @author Administrator
 *
 */
public class UserInfoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId=0;//�û�id
	private BasicUserInfo basicUser=null;
	private DetailUserInfo detailUser=null;
	
	public UserInfoForm() {
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public BasicUserInfo getBasicUser() {
		return basicUser;
	}

	public void setBasicUser(BasicUserInfo basicUser) {
		this.basicUser = basicUser;
	}

	public DetailUserInfo getDetailUser() {
		return detailUser;
	}

	public void setDetailUser(DetailUserInfo detailUser) {
		this.detailUser = detailUser;
	}

	@Override
	public String toString() {
		return "UserDetailForm [userId=" + userId + ", basicUser=" + basicUser + ", detailUser=" + detailUser + "]";
	}
}
