package com.esh.json.form.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * �û������ʼ��ʱ�������û���Ҫ��Ϣ
 * @author Administrator
 *
 */
public class UserProfile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId=0;//�û�id
	private String uname=null;//�û���
	private byte[] uhdata=null;//ͼƬ����
	private String uhpic=null;//�û�·��
	private double toaTime=0;//�û�������ʱ��
	
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
		//�洢��Ϊ���ӣ�ת����Сʱ
	    BigDecimal bg = new BigDecimal((1.0*toaTime/60)).setScale(2, RoundingMode.UP);
		this.toaTime = bg.doubleValue();
	}

	@Override
	public String toString() {
		return "UserProfile [userId=" + userId + ", uname=" + uname + ", uhpic=" + uhpic + ", toaTime=" + toaTime + "]";
	}	
}
