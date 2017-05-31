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

import com.esh.dao.SuggestionDao;
import com.esh.entity.Suggestion;
import com.esh.utils.Packager;

@Repository("suggestionDao")
public class SuggestionDaoImpl extends JDBCBase implements SuggestionDao {

	Logger logger=LoggerFactory.getLogger(getClass().getName());
	
	@Override
	public List<Suggestion> getSuggestionByDiseaseId(int diseaseId) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Suggestion> suggestions=new ArrayList<Suggestion>();
		String sql="select * from suggestion where d_id="+diseaseId;
		try {
			ps=conn.prepareStatement(sql);
			rs=query(ps);
			while(rs.next())
			{
				suggestions.add(Packager.PackagerSuggestion(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			JDBCUtil.close(rs, ps, conn);
			logger.info(suggestions==null?"不存在对应推荐信息集合，位置："+this.getClass().getName():"获取推荐信息集合成功");
		}
		return suggestions;
	}

}
