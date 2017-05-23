package com.esh.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.esh.entity.DiseaseInformation;
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
	 * 打包disease——information表信息
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static DiseaseInformation PackagerDiseaseInformation(ResultSet rs) throws SQLException
	{
		DiseaseInformation diseaseInformation=new DiseaseInformation();
		diseaseInformation.setDid(rs.getInt("disease_id"));
		diseaseInformation.setDname(rs.getString("disease_name"));
		diseaseInformation.setDfid(rs.getInt("father_disease_id"));
		diseaseInformation.setDescription(rs.getString("disease_description"));
		diseaseInformation.setDhighRiskGroup(rs.getString("high-risk_groups"));
		diseaseInformation.setDhighRiskGender(rs.getString("high-risk_gender"));
		return diseaseInformation;
	}
}
