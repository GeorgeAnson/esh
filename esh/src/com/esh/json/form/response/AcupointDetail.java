package com.esh.json.form.response;

import java.io.Serializable;

/**
 * Ѩλ��Ϣ����
 * @author Administrator
 *
 */
public class AcupointDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int acupointId=0;//ѨλId
	private String acupointName=null;//Ѩλ����
	private String description=null;//Ѩλ����
	private String img=null;//��ͼ��ͼƬ��
	
	public AcupointDetail() {
		
	}

	public int getAcupointId() {
		return acupointId;
	}

	public void setAcupointId(int acupointId) {
		this.acupointId = acupointId;
	}

	public String getAcupointName() {
		return acupointName;
	}

	public void setAcupointName(String acupointName) {
		this.acupointName = acupointName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "AcupointDetail [acupointId=" + acupointId + ", acupointName=" + acupointName + ", description="
				+ description + ", img=" + img + "]";
	}
}
