package com.esh.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.esh.dao.UserDao;
import com.esh.entity.DetailUser;
import com.esh.entity.User;
import com.esh.globle.Constants;
import com.esh.utils.Packager;

@Repository("userDao")
public class UserDaoImpl extends JDBCBase implements UserDao {

	@Override
	public User getUserByUserId(int userId) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from user where user_id=?";
		Object[] parmas={
			userId
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
			e.printStackTrace();
		}finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return user;
	}

	@Override
	public int save(User user) {
		String sql="insert into user(user_name,password,email,real_name,head_pic,pic_name,phone,user_status,last_time) values(?,?,?,?,?,?,?,?,?)";
		Object[] parmas={
				user.getUname(),
				user.getPwd(),
				user.getUemail(),
				user.getRname(),
				user.getHpic(),
				user.getHpicName(),
				user.getUphone(),
				user.getUstatus(),
				user.getUlastime()
		};
		int userId=save(sql, parmas);
		return userId;
	}

	@Override
	public User getUserByAccount(String account) {
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
			e.printStackTrace();
		}finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return user;
	}

	@Override
	public int update(User user) {
		int errorCode=Constants.NO_ERROR_EXIST;
		StringBuilder sql=new StringBuilder("update user set user_status=?");
		List<Object> parmas=new ArrayList<Object>();
		parmas.add(user.getUstatus());
		if(user.getHpic()!=null&&!"".equals(user.getHpic()))
		{
			sql.append(", head_pic=?");
			parmas.add(user.getHpic());
		}
		if(user.getHpicName()!=null&&!"".equals(user.getHpicName()))
		{
			sql.append(", pic_name=?");
			parmas.add(user.getHpicName());
		}
		if(user.getPwd()!=null&&!"".equals(user.getPwd()))
		{
			sql.append(", password=?");
			parmas.add(user.getPwd());
		}
		if(user.getRname()!=null&&!"".equals(user.getRname()))
		{
			sql.append(", real_name=?");
			parmas.add(user.getRname());
		}
		if(user.getUemail()!=null&&!"".equals(user.getUemail()))
		{
			sql.append(", email=?");
			parmas.add(user.getUemail());
		}
		if(user.getUlastime()!=null&&!"".equals(user.getUlastime()))
		{
			sql.append(", last_time=?");
			parmas.add(user.getUlastime());
		}
		if(user.getUname()!=null&&!"".equals(user.getUname()))
		{
			sql.append(", user_name=?");
			parmas.add(user.getUname());
		}
		if(user.getUphone()!=null&&!"".equals(user.getUphone()))
		{
			sql.append(", phone=?");
			parmas.add(user.getUphone());
		}
		sql.append(" where user_id=?");
		parmas.add(user.getUid());
		if(!saveOrUpdateOrDelete(sql.toString(), parmas.toArray()))
		{
			errorCode=Constants.UNKNOWN_OPERATION_ERROR;
		}
		return errorCode;
	}

	@Override
	public int saveOrUpdateUserDetailInfo(DetailUser detailUser) {
		int errorCode=Constants.NO_ERROR_EXIST;
		StringBuilder sql= new StringBuilder("insert into duser(user_id, age, gender, m_history, address, ecall, profession, allergy, height, weight) "
				+ " values(?,?,?,?,?,?,?,?,?,?) on duplicate key update user_id=?");
		List<Object> parmas=new ArrayList<Object>();
		parmas.add(detailUser.getUid());
		parmas.add(detailUser.getDuAge());
		parmas.add(detailUser.getDuGender());
		parmas.add(detailUser.getDuMediHist());
		parmas.add(detailUser.getDuAddress());
		parmas.add(detailUser.getDuEmerCall());
		parmas.add(detailUser.getDuProfession());
		parmas.add(detailUser.getDuAllergy());
		parmas.add(detailUser.getDuHeight());
		parmas.add(detailUser.getDuWeight());
		parmas.add(detailUser.getUid());
		if(detailUser.getDuAddress()!=null&&!"".equals(detailUser.getDuAddress()))
		{
			sql.append(", address=?");
			parmas.add(detailUser.getDuAddress());
		}
		if(detailUser.getDuAge()!=0&&!"".equals(detailUser.getDuAge()))
		{
			sql.append(", age=?");
			parmas.add(detailUser.getDuAge());
		}
		if(detailUser.getDuAllergy()!=null&&!"".equals(detailUser.getDuAllergy()))
		{
			sql.append(", allergy=?");
			parmas.add(detailUser.getDuAllergy());
		}
		if(detailUser.getDuEmerCall()!=null&&!"".equals(detailUser.getDuEmerCall()))
		{
			sql.append(", ecall=?");
			parmas.add(detailUser.getDuEmerCall());
		}
		if(detailUser.getDuGender()!=0&&!"".equals(detailUser.getDuGender()))
		{
			sql.append(", gender=?");
			parmas.add(detailUser.getDuGender());
		}
		if(detailUser.getDuHeight()!=0&&!"".equals(detailUser.getDuHeight()))
		{
			sql.append(", height=?");
			parmas.add(detailUser.getDuHeight());
		}
		if(detailUser.getDuMediHist()!=null&&!"".equals(detailUser.getDuMediHist()))
		{
			sql.append(", m_history=?");
			parmas.add(detailUser.getDuMediHist());
		}
		if(detailUser.getDuProfession()!=null&&!"".equals(detailUser.getDuProfession()))
		{
			sql.append(", profession=?");
			parmas.add(detailUser.getDuProfession());
		}
		if(detailUser.getDuWeight()!=0&&!"".equals(detailUser.getDuWeight()))
		{
			sql.append(", weight=?");
			parmas.add(detailUser.getDuWeight());
		}
		System.out.println(sql.toString());
		if(!saveOrUpdateOrDelete(sql.toString(), parmas.toArray()))
		{
			errorCode=Constants.UNKNOWN_OPERATION_ERROR;
		}
		return errorCode;
	}
}
