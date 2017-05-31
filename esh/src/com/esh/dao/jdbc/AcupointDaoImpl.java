package com.esh.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.esh.dao.AcupointDao;
import com.esh.entity.Acupoint;
import com.esh.utils.Packager;

@Service("acupointDao")
public class AcupointDaoImpl extends JDBCBase implements AcupointDao {

	Logger logger=LoggerFactory.getLogger(getClass().getName());
	
	@Override
	public Acupoint getAcupointByAcupointId(int acupointId) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Acupoint acupoint=null;
		String sql="select * from apoints where ap_id="+acupointId;
		try {
			ps=conn.prepareStatement(sql);
			rs=query(ps);
			if(rs.next())
			{
				acupoint=Packager.PackagerAcupoint(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			JDBCUtil.close(rs, ps, conn);
			logger.info(acupoint==null?"������Ѩλ��ϸ��Ϣ��λ�ã�"+this.getClass().getName():"��ȡѨλ��ϸ��Ϣ�ɹ�");
		}
		return acupoint;
	}

}
