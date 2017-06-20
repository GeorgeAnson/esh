package com.esh.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.esh.dao.ApuserDao;
import com.esh.entity.Apuser;
import com.esh.globle.Constants;
import com.esh.utils.Packager;

@Repository("apuserDao")
public class ApuserDaoImpl extends JDBCBase implements ApuserDao {

	Logger logger=LoggerFactory.getLogger(getClass().getName());
	
	@Override
	public List<Apuser> getApuserByUserIdAndDiseaseId(int userId, int diseaseId) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Apuser> apusers=new ArrayList<Apuser>();
		String sql="select * from apuser where user_id=? and d_id=?";
		Object[] parma={
			userId,
			diseaseId
		};
		try {
			ps=conn.prepareStatement(sql);
			rs=query(ps, parma);
			while(rs.next())
			{
				apusers.add(Packager.PackagerApuser(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			JDBCUtil.close(rs, ps, conn);
			logger.info(apusers==null?"获取用户治疗数据为为null，位置"+this.getClass().getName():"获取用户治疗数据成功");
		}
		return apusers;
	}

	@Override
	public int getCountOfApuserByAcpointIdAndDiseaseId(int did, int apId) {
		String sql="select count(*) as sum from apuser where d_id="+did+" and ap_id="+apId;
		int sum=getCount(sql);
		return sum;
	}

	@Override
	public Apuser getApuserByUserIdAndAcupointId(int userId, int acupoingId, int diseaseId) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Apuser apuser=null;
		String sql="select * from apuser where user_id=? and ap_id=? and d_id=?";
		Object[] parma={
			userId,
			acupoingId,
			diseaseId
		};
		try {
			ps=conn.prepareStatement(sql);
			rs=query(ps, parma);
			if(rs.next())
			{
				apuser=Packager.PackagerApuser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			JDBCUtil.close(rs, ps, conn);
			logger.info(apuser==null?"获取用户治疗数据参数为为null，位置"+this.getClass().getName():"获取用户治疗数据参数成功");
		}
		return apuser;
	}

	@Override
	public int saveOrUpdateCueDevParma(Apuser apuser) {
		String sql="insert into apuser(user_id,ap_id,d_id,ap_strength,ap_mode,ap_frequenry,ap_time,ap_pulse,ap_period,ap_update) "
				+ "values(?,?,?,?,?,?,?,?,?,?)  on duplicate key update ap_strength=?,ap_mode=?,ap_frequenry=?,ap_time=?,ap_pulse=?,ap_period=?,ap_update=?";
		Object[] parma={
			apuser.getUser().getUid(),
			apuser.getAcupoint().getApId(),
			apuser.getDisease().getDid(),
			apuser.getUstrength(),
			apuser.getUmode(),
			apuser.getUfrequency(),
			apuser.getUtime(),
			apuser.getUpulse(),
			apuser.getUperiod(),
			apuser.getUpdateTime(),
			apuser.getUstrength(),
			apuser.getUmode(),
			apuser.getUfrequency(),
			apuser.getUtime(),
			apuser.getUpulse(),
			apuser.getUperiod(),
			apuser.getUpdateTime()
		};
		int errorCode=Constants.NO_ERROR_EXIST;
		if(!saveOrUpdateOrDelete(sql, parma))
		{
			errorCode=Constants.UNKNOWN_OPERATION_ERROR;
		}
		return errorCode;
	}

	@Override
	public List<Apuser> getHistApusersByUserId(int userId) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Apuser> apusers=new ArrayList<Apuser>();
		String sql="select * from apuser where user_id=? order by ap_update desc";
		Object[] parma={
			userId	
		};
		try {
			ps=conn.prepareStatement(sql);
			rs=query(ps, parma);
			while(rs.next())
			{
				apusers.add(Packager.PackagerApuser(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return apusers;
	}

	@Override
	public double getToaTimeByUserId(int userId) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		double toaTime=0;
		String sql="select sum(ap_time) as toa_time from apuser where user_id="+userId;
		
		try {
			ps=conn.prepareStatement(sql);
			rs=query(ps);
			if(rs.next())
			{
				toaTime=rs.getDouble("toa_time");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return toaTime;
	}
}
