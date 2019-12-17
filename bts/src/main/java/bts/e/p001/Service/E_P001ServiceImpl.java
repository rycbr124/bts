package bts.e.p001.Service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import bts.c.p001.vo.C_P001VO;
import bts.e.p001.DAO.E_P001DAO;
import bts.e.p001.VO.E_P001VO;
import bts.e.p001.VO.PagingVO;
import bts.e.p003.VO.E_P003VO_2;

@Service("e_p001Service")
@Transactional(propagation= Propagation.REQUIRED)
public class E_P001ServiceImpl implements E_P001Service {
	SqlSession sqlSession;
	@Autowired
	E_P001DAO e_p001DAO;
	



	@Override
	public List<E_P001VO> selectAccompanyList(PagingVO pagingVO,String category, String searchResult) throws DataAccessException {
		return e_p001DAO.selectAccompanyList(pagingVO,category,searchResult);
	}

	@Override
	public Integer listCount() {
		return e_p001DAO.pageCount();
	}

	@Override
	public E_P001VO accView(int article_no) throws Exception {
		return e_p001DAO.accView(article_no);
	}

	@Override
	public void updateViewcnt(int article_no) throws Exception {
		e_p001DAO.updateViewcnt(article_no);
	}

	@Override
	public List<C_P001VO> inclnView(String member_id) throws Exception {
		return e_p001DAO.inclnView(member_id);
	}

	@Override
	public List<E_P003VO_2> selectTag(int article_no) throws Exception {
		return e_p001DAO.selectTag(article_no);
	}
}
