package bts.a.p000.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.e.p001.VO.E_P001VO;
import bts.f.p001_3.vo.F_P001_3VO;
import bts.i.p002.VO.I_P002VO_1;

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
	@Override
	public List<I_P002VO_1> selectPlanner()throws DataAccessException{
		return sqlSession.selectList("a.p000.today_plan");
	}
	@Override
	public List<F_P001_3VO> selectArticle()throws DataAccessException{
		return sqlSession.selectList("a.p000.today_review");
	}
	@Override
	public List<E_P001VO> selectAccompany()throws DataAccessException{
		return sqlSession.selectList("a.p000.today_accompany");
	}

}
