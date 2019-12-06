package bts.a.p001.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.a.p001.vo.A_P001VO;


@Repository("a_p001DAO")
public class A_P001DAOImpl implements A_P001DAO{
	@Autowired
	private SqlSession sqlSession;	
	
	@Override
	public List<A_P001VO> searchIncln(Map<String, String> searchMap) throws DataAccessException {
		return sqlSession.selectList("a.p000.searchIncln", searchMap);
	}

	@Override
	public void insertData(Map<String, String> row) throws DataAccessException {
		sqlSession.insert("a.p000.insertIncln", row);
		
	}

	@Override
	public void updateData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("a.p000.updateIncln", row);
		
	}

	@Override
	public void deleteData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("a.p000.deleteIncln", row);
		
	}

}
