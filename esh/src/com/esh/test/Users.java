package com.esh.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.esh.dao.jdbc.JDBCBase;
import com.esh.dao.jdbc.JDBCUtil;
import com.esh.utils.Packager;

public class Users extends JDBCBase{


	@Test
	public void getUsers()
	{
		Connection conn=JDBCUtil.getConnection();
		String sql="select * from user";
		ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			rs=query(ps);
			while(rs.next())
			{
				System.out.println(Packager.PackUserObject(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
