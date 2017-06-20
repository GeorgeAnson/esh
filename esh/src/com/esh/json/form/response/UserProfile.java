package com.esh.json.form.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 用户界面初始化时，保存用户概要信息
 * @author Administrator
 *
 */
public class UserProfile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId=0;//用户id
	private String uname=null;//用户名
	private byte[] uhdata=null;//图片数据
	private String uhpic=null;//用户路径
	private double toaTime=0;//用户总治疗时间
	
	public UserProfile() {
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public byte[] getUhdata() {
		return uhdata;
	}

	public void setUhdata(byte[] uhdata) {
		this.uhdata = uhdata;
	}

	public String getUhpic() {
		return uhpic;
	}

	public void setUhpic(String uhpic) {
		this.uhpic = uhpic;
	}

	public double getToaTime() {
		return toaTime;
	}

	public void setToaTime(double toaTime) {
		//存储的为分钟，转换成小时
	    BigDecimal bg = new BigDecimal((1.0*toaTime/60)).setScale(2, RoundingMode.UP);
		this.toaTime = bg.doubleValue();
	}

	@Override
	public String toString() {
		return "UserProfile [userId=" + userId + ", uname=" + uname + ", uhpic=" + uhpic + ", toaTime=" + toaTime + "]";
	}	
}
