package com.esh.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.esh.dao.UserDao;
import com.esh.entity.User;
import com.esh.utils.Packager;

@Repository("userDao")
public class UserDaoImpl extends JDBCBase implements UserDao {

	@Override
	public User getUserByConditions(String condition) {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from user where email=? or phone=?";
		Object[] parmas={
			condition,
			condition
		};
		User userLogin=null;
		try {
			ps=conn.prepareStatement(sql);
			rs=query(ps,parmas);
			while(rs.next())
			{
				userLogin=Packager.PackUserObject(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return userLogin;
	}

	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		String sql="insert into user(user_name,password,email,real_name,head_pic,phone,user_status,last_time) values(?,?,?,?,?,?,?,?)";
		Object[] parmas={
				user.getUname(),
				user.getPwd(),
				user.getUemail(),
				user.getRname(),
				user.getHpic(),
				user.getUphone(),
				user.getUstatus(),
				user.getUlastime()
		};
		int userId=save(sql, parmas);
		return userId;
	}

	@Override
	public User getUserByAccount(String account) {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from user where email=? or phone=? or user_name=?";
		Object[] parmas={
				account,
				account,
				account
		};
		User user=null;
		try {
			ps=conn.prepareStatement(sql);
			rs=query(ps,parmas);
			while(rs.next())
			{
				user=Packager.PackUserObject(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return user;
	}

}
