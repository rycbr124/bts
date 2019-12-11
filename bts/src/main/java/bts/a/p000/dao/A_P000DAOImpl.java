package bts.a.p000.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository("a_p000DAO")
public class A_P000DAOImpl implements A_P000DAO{
	@Autowired
	private SqlSession sqlSession;	
	
	@Override
	public int countMember() throws DataAccessException {
		return sqlSession.selectOne("a.p000.countMember");
	}

	@Override
	public int countContact() throws DataAccessException {
		return sqlSession.selectOne("a.p000.countContact");
	}

	@Override
	public int countReport() throws DataAccessException {
		return sqlSession.selectOne("a.p000.countReport");
	}

	@Override
	public int countContactY() throws DataAccessException {
		return sqlSession.selectOne("a.p000.countContactY");
	}

	@Override
	public int countReportY() throws DataAccessException {
		return sqlSession.selectOne("a.p000.countReportY");
	}

	@Override
	public int countPlan() throws DataAccessException {
		return sqlSession.selectOne("a.p000.countPlan");
	}

	@Override
	public int countReview() throws DataAccessException {
		return sqlSession.selectOne("a.p000.countReview");
	}

	@Override
	public int countAccompany() throws DataAccessException {
		return sqlSession.selectOne("a.p000.countAccompany");
	}
	

}
