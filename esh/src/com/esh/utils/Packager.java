package com.esh.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.esh.entity.Disease;
import com.esh.entity.Suggestion;
import com.esh.entity.Acupoint;
import com.esh.entity.AcupointPicture;
import com.esh.entity.Apuser;
import com.esh.entity.User;


public class Packager {
	
	/**
	 * 打包user表数据
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static User PackUserObject(ResultSet rs) throws SQLException
	{
		User user=new User();
		user.setUid(rs.getInt("user_id"));
		user.setUname(rs.getString("user_name"));
		user.setPwd(rs.getString("password"));
		user.setUemail(rs.getString("email"));
		user.setRname(rs.getString("real_name"));
		user.setHpic(rs.getBytes("head_pic"));
		user.setUphone(rs.getString("phone"));
		user.setUstatus(rs.getInt("user_status"));
		user.setUlastime(rs.getDate("last_time"));		
		return user;
	}
	
	/**
	 * 打包disease表信息
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static Disease PackagerDisease(ResultSet rs) throws SQLException
	{
		Disease disease=new Disease();
		disease.setDid(rs.getInt("d_id"));
		disease.setDfid(rs.getInt("f_id"));
		disease.setDname(rs.getString("d_name"));
		disease.setDescription(rs.getString("description"));
		disease.setDhighRiskGroup(rs.getString("hr_group"));
		disease.setDhighRiskGender(rs.getString("hr_gender"));
		disease.setDstatus(rs.getInt("d_status"));
		return disease;
	}

	/**
	 * 打包图片数据
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	public static AcupointPicture PackagerAcupointPic(ResultSet rs) throws SQLException {
		AcupointPicture acupointPicture=new AcupointPicture();
		acupointPicture.setPictureId(rs.getInt("pic_id"));
		acupointPicture.setData(rs.getBytes("pic"));
		acupointPicture.setPictureName(rs.getString("pic_name"));
		return acupointPicture;
	}

	/**
	 * 打包用户治疗数据表信息
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	public static Apuser PackagerApuser(ResultSet rs) throws SQLException {
		Apuser apuser=new Apuser();
		User user=new User();
		Acupoint acupoint=new Acupoint();
		Disease disease=new Disease();
		user.setUid(rs.getInt("user_id"));
		acupoint.setApId(rs.getInt("ap_id"));
		disease.setDid(rs.getInt("d_id"));
		apuser.setUstrength(rs.getInt("ap_strength"));
		apuser.setUmode(rs.getInt("ap_mode"));
		apuser.setUfrequency(rs.getInt("ap_frequenry"));
		apuser.setUtime(rs.getInt("ap_time"));
		apuser.setUpulse(rs.getInt("ap_pulse"));
		apuser.setUperiod(rs.getInt("ap_period"));
		apuser.setUpdateTime(rs.getDate("ap_update"));
		apuser.setUser(user);
		apuser.setAcupoint(acupoint);
		apuser.setDisease(disease);
		return apuser;
	}

	/**
	 * 打包推荐表记录
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	public static Suggestion PackagerSuggestion(ResultSet rs) throws SQLException {
		Suggestion suggestion=new Suggestion();
		Disease disease=new Disease();
		Acupoint acupoint=new Acupoint();
		suggestion.setSid(rs.getInt("s_id"));
		disease.setDid(rs.getInt("d_id"));
		suggestion.setSname(rs.getString("s_name"));
		suggestion.setScontext(rs.getString("s_context"));
		suggestion.setSevaluation(rs.getDouble("s_evaluation"));
		suggestion.setSperiod(rs.getInt("s_period"));
		acupoint.setApId(rs.getInt("ap_id"));
		suggestion.setAcupoint(acupoint);
		suggestion.setDisease(disease);
		return suggestion;
	}

	/**
	 * 打包穴位表数据
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	public static Acupoint PackagerAcupoint(ResultSet rs) throws SQLException {
		Acupoint acupoint=new Acupoint();
		AcupointPicture picture=new AcupointPicture();
		acupoint.setApId(rs.getInt("ap_id"));
		picture.setPictureId(rs.getInt("pic"));
		acupoint.setApName(rs.getString("ap_name"));
		acupoint.setDesctibe(rs.getString("describe"));
		acupoint.setApStrength(rs.getInt("ap_strength"));
		acupoint.setApMode(rs.getInt("ap_mode"));
		acupoint.setApFrequency(rs.getInt("ap_frequency"));
		acupoint.setApTime(rs.getInt("ap_time"));
		acupoint.setApulse(rs.getInt("ap_pulse"));
		acupoint.setApIsFirst(rs.getInt("ap_isfirst"));
		acupoint.setPicture(picture);
		return acupoint;
	}
}
