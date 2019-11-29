package bts.a.p005.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.a.p005.vo.A_P005VO;

@Repository("a_p005DAO")
public class A_P005DAOImpl implements A_P005DAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<A_P005VO> searchMember(Map<String, String> searchMap) throws DataAccessException {
		return sqlSession.selectList("a.p000.searchMember", searchMap);
	}

	@Override
	public void insertData(Map<String, String> row) {
		sqlSession.update("a.p000.insertMember", row);
		
	}

	@Override
	public void updateData(Map<String, String> row) {
		sqlSession.update("a.p000.updateMember", row);
		
	}

	@Override
	public void deleteData(Map<String, String> row) {
		sqlSession.update("a.p000.deleteMember", row);
		
	}

}
