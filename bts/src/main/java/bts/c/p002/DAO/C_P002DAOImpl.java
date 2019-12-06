package bts.c.p002.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.c.p007.VO.C_P007VO;
import bts.e.p001.VO.PagingVO;
import bts.e.p001.VO.PagingVO2;
import bts.e.p001.VO.PagingVO3;
import bts.f.p001_3.vo.F_P001_3VO;
import bts.i.p002.VO.I_P002VO_1;

@Repository("c_p002DAO")
public class C_P002DAOImpl implements C_P002DAO{
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<F_P001_3VO> reviewList(PagingVO pagingVO) throws DataAccessException {		
		return sqlSession.selectList("mapper.myPage.myReview",pagingVO);
	}

	@Override
	public Integer reviewListCount(String member_id) {
		return sqlSession.selectOne("mapper.myPage.reviewListCount",member_id);
	}

	@Override
	public List<I_P002VO_1> planList(PagingVO3 pagingVO3) throws DataAccessException {
		return sqlSession.selectList("mapper.myPage.myPlan",pagingVO3);
	}

	@Override
	public Integer planListCount(String member_id) {
		return sqlSession.selectOne("mapper.myPage.planListCount",member_id);
	}

	@Override
	public List<C_P007VO> accList(PagingVO2 pagingVO2) throws DataAccessException {
		return sqlSession.selectList("mapper.myPage.myAcc",pagingVO2);
	}

	@Override
	public Integer accListCount(String member_id) {
		return sqlSession.selectOne("mapper.myPage.myaccListCount",member_id);
	}

}
