package bts.e.p001.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


import bts.e.p001.VO.E_P001VO;
import bts.e.p001.VO.PagingVO;

@Repository("e_p001DAO")
public class E_P001DAOImpl implements E_P001DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<E_P001VO> selectAccompanyList(PagingVO pagingVO) throws DataAccessException {
		return sqlSession.selectList("mapper.accompany.accList",pagingVO);
	}

	@Override
	public Integer pageCount(){
		return sqlSession.selectOne("mapper.accompany.listCount");
	}
	
	
	@Override
	public void updateViewcnt(int article_no) throws Exception {
		sqlSession.update("mapper.accompany.updateViewcnt",article_no);
	}

	@Override
	public E_P001VO accView(int article_no) throws Exception {
		return sqlSession.selectOne("mapper.accompany.accView",article_no);
	}

}
