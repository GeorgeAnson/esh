package com.esh.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.esh.dao.AcupointPicDao;
import com.esh.entity.AcupointPicture;
import com.esh.utils.Packager;

@Repository("acupointPicDao")
public class AcupointPicDaoImpl extends JDBCBase implements AcupointPicDao {

	Logger logger=LoggerFactory.getLogger(getClass().getName());
	
	@Override
	public AcupointPicture getAcuPicByPicId(int picId) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		AcupointPicture acupointPicture=null;
		String sql="select * from picture where pic_id="+picId;
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs=query(ps);
			if(rs.next())
			{
				acupointPicture=Packager.PackagerAcupointPic(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			JDBCUtil.close(rs, ps, conn);
			logger.info(acupointPicture==null?"不存在对应的穴位动图，位置："+this.getClass().getName():"获取穴位动图成功");
		}
		return acupointPicture;
	}

}
