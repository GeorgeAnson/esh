package com.esh.entity;

import java.io.Serializable;

public class AcupointPicture implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pictureId=0;//Í¼Æ¬id
	private byte[] data=null;//Í¼Æ¬Êý¾Ý
	private String pictureName=null;//Í¼Æ¬Ãû³Æ-Â·¾¶
	
	public AcupointPicture() {
		
	}

	public int getPictureId() {
		return pictureId;
	}

	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	@Override
	public String toString() {
		return "DiseasePicture [pictureId=" + pictureId + ", pictureName=" + pictureName + "]";
	}
	
}
