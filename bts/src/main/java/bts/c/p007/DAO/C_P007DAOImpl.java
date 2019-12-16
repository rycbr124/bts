package bts.c.p007.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.c.p007.VO.C_P007VO;
import bts.e.p001.VO.PagingVO;
import bts.e.p001.VO.PagingVO2;

@Repository("c_p007DAO")
public class C_P007DAOImpl implements C_P007DAO{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<C_P007VO> myAccList(PagingVO pagingVO) throws DataAccessException {
		
		return sqlSession.selectList("mapper.myPage.myaccReq",pagingVO);
	}

	@Override
	public Integer listCount(String member_id) {
		return sqlSession.selectOne("mapper.myPage.accListCount",member_id);
	}

	@Override
	public List<C_P007VO> imAccList(PagingVO2 pagingVO2) throws DataAccessException {
	
		return sqlSession.selectList("mapper.myPage.imaccReq",pagingVO2);
	}

	@Override
	public Integer imAccCount(String member_id) {

		return sqlSession.selectOne("mapper.myPage.imaccCount",member_id);
	}

}
