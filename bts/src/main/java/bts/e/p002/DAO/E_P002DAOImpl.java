package bts.e.p002.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.e.p002.VO.E_P002VO;
import bts.e.p003.VO.E_P003VO;

@Repository("e_p002DAO")
public class E_P002DAOImpl implements E_P002DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void accDelete(int article_no) throws DataAccessException {
		sqlSession.selectOne("mapper.accompany.accompanyDel", article_no);
	}

	@Override
	public void accTagDel(int article_no) throws DataAccessException {
		sqlSession.selectOne("mapper.accompany.accTagDel",article_no);
	}

	@Override
	public void accUpdate(E_P003VO e_p003VO) throws DataAccessException {
		sqlSession.update("mapper.accompany.accUpdate",e_p003VO);	
	}

	@Override
	public void accReq(E_P002VO e_p002VO) throws DataAccessException {
		sqlSession.insert("mapper.accompany.accompanyReq", e_p002VO);
	}

}
