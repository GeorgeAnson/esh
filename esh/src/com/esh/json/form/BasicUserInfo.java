package com.esh.json.form;

import java.io.Serializable;

public class BasicUserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uname=null;//�û���
	private String uemail=null;//����
	private byte[] hpic=null;//ͷ��
	private String hpicName=null;//ͷ������
	private String uphone=null;//�ֻ�����
	
	public BasicUserInfo() {
		
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public byte[] getHpic() {
		return hpic;
	}

	public void setHpic(byte[] hpic) {
		this.hpic = hpic;
	}

	public String getHpicName() {
		return hpicName;
	}

	public void setHpicName(String hpicName) {
		this.hpicName = hpicName;
	}

	public String getUphone() {
		return uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	
	@Override
	public String toString() {
		return "BasicUserInfo [uname=" + uname + ", uemail=" + uemail + ", hpicName=" + hpicName + ", uphone=" + uphone
				+ "]";
	}
}
