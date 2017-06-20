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
import com.esh.globle.Constants;
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

	@Override
	public Suggestion getSuggestionByAcupointIdAndDiseaseId(int apId, int did) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Suggestion suggestion=null;
		String sql="select * from suggestion where d_id=? and ap_id=?";
		Object[] parma={
			did,
			apId
		};
		try {
			ps=conn.prepareStatement(sql);
			rs=query(ps, parma);
			while(rs.next())
			{
				suggestion=Packager.PackagerSuggestion(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			JDBCUtil.close(rs, ps, conn);
			logger.info(suggestion==null?"不存在对应推荐信息对象，位置："+this.getClass().getName():"获取推荐信息对象成功");
		}
		return suggestion;
	}

	@Override
	public int updateSuggestion(Suggestion suggestion, Connection conn) {
		int errorCode=Constants.NO_ERROR_EXIST;
		StringBuilder sql=new StringBuilder("update suggestion set qus_sum=?");
		ArrayList<Object> parmas=new ArrayList<Object>();
		parmas.add(suggestion.getSqusRecoSum());
		
		if(suggestion.getAcupoint().getApId()!=0)
		{
			sql.append(", ap_id=?");
			parmas.add(suggestion.getAcupoint().getApId());
		}
		
		if(suggestion.getDisease().getDid()!=0)
		{
			sql.append(", d_id=?");
			parmas.add(suggestion.getDisease().getDid());
		}
		
		if(suggestion.getScontext()!=null)
		{
			sql.append(", s_context=?");
			parmas.add(suggestion.getScontext());
		}
		
		if(suggestion.getSevaluation()!=0)
		{
			sql.append(", s_evaluation=?");
			parmas.add(suggestion.getSevaluation());
		}
		
		if(suggestion.getSname()!=null)
		{
			sql.append(", s_name=?");
			parmas.add(suggestion.getSname());
		}
		
		if(suggestion.getSperiod()!=0)
		{
			sql.append(", s_period=?");
			parmas.add(suggestion.getSperiod());
		}
		
		sql.append(" where s_id=?");
		parmas.add(suggestion.getSid());
		if(!update(sql.toString(), parmas.toArray(), conn))
		{
			errorCode=Constants.UNKNOWN_OPERATION_ERROR;
		}
		return errorCode;
	}

}
