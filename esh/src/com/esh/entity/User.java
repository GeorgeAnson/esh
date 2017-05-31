package com.esh.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int uid=0;//用户id
	private String uname=null;//用户名
	private String pwd=null;//密码
	private String uemail=null;//邮箱
	private String rname=null;//真名
	private byte[] hpic=null;//头像
	private String hpicName=null;//头像图片名称
	private String uphone=null;//手机号码
	private int ustatus=0;//状态
	private Date ulastime=null;//最后登录时间
	
	public User() {
		
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
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

	public int getUstatus() {
		return ustatus;
	}

	public void setUstatus(int ustatus) {
		this.ustatus = ustatus;
	}

	public Date getUlastime() {
		return ulastime;
	}

	public void setUlastime(Date ulastime) {
		this.ulastime = ulastime;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", pwd=" + pwd + ", uemail=" + uemail + ", rname=" + rname
				+ ", hpicName=" + hpicName + ", uphone=" + uphone + ", ustatus=" + ustatus + ", ulastime=" + ulastime
				+ "]";
	}
}
