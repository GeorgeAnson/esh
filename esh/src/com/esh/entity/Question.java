package com.esh.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.esh.utils.Utils;

/**
 * ÎÊ¾íµ÷²é
 * @author Administrator
 *
 */
public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1303702955558396176L;
	private int queId=0;
	private User user=null;
	private Disease disease=null;
	private Suggestion suggestion=null;
	private double satisfaction=0;
	private Date time=null;
	private String effection=null;
	
	public Question() {
		
	}

	public int getQueId() {
		return queId;
	}

	public void setQueId(int queId) {
		this.queId = queId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Suggestion getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}

	public double getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(double satisfaction) {
		this.satisfaction = satisfaction;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = Utils.stringToExactDate(new SimpleDateFormat("yyyy-MM-DD HH:mm:ss").format(time));
	}

	public String getEffection() {
		return effection;
	}

	public void setEffection(String effection) {
		this.effection = effection;
	}

	@Override
	public String toString() {
		return "Question [queId=" + queId + ", satisfaction=" + satisfaction + ", time=" + time + ", effection="
				+ effection + "]";
	}
}
