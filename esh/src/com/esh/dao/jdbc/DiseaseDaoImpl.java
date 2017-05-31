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

import com.esh.dao.DiseaseDao;
import com.esh.entity.Disease;
import com.esh.utils.Packager;

@Repository("diseaseDao")
public class DiseaseDaoImpl extends JDBCBase implements DiseaseDao {

	Logger logger=LoggerFactory.getLogger(getClass().getName());
	
	@Override
	public List<Disease> getAllDisease() {
		List<Disease> diseases=new ArrayList<>();
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from disease where d_status not in (-1)";
		try {
			ps=conn.prepareStatement(sql);
			rs=query(ps);
			while(rs.next())
			{
				diseases.add(Packager.PackagerDisease(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			JDBCUtil.close(rs, ps, conn);
			logger.info(diseases==null?"获取病症详细信息集合失败，位置："+this.getClass().getName():"获取病症详细信息集合成功");
		}
		return diseases;
	}

	@Override
	public Disease getDiseaseByDiseaseId(int id) {
		Disease disease=null;
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from disease where d_status not in (-1) and d_id="+id;
		try {
			ps=conn.prepareStatement(sql);
			rs=query(ps);
			if(rs.next())
			{
				disease=Packager.PackagerDisease(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			JDBCUtil.close(rs, ps, conn);
			logger.info(disease==null?"不存在病症详细信息，位置："+this.getClass().getName():"获取病症详细信息成功");
		}
		return disease;
	}

}
