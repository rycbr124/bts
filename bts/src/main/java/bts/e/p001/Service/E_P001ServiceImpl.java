package bts.e.p001.Service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import bts.e.p001.DAO.E_P001DAO;
import bts.e.p001.VO.E_P001VO;
import bts.e.p001.VO.PagingVO;

@Service("e_p001Service")
@Transactional(propagation= Propagation.REQUIRED)
public class E_P001ServiceImpl implements E_P001Service {
	SqlSession sqlSession;
	@Autowired
	E_P001DAO e_p001DAO;
	

	@Override
	public List selectAllEventList(PagingVO vo) throws DataAccessException {
		List<E_P001VO> eventList = null;
		eventList = sqlSession.selectList("mapper.accompany.selectAccompany", vo);
		System.out.println("eventList" + eventList);
		return eventList;
	}

	@Override
	public List<E_P001VO> selectAccompanyList(PagingVO pagingVO) throws DataAccessException {
		return e_p001DAO.selectAccompanyList(pagingVO);
	}

	@Override
	public Integer listCount() {
		return e_p001DAO.pageCount();
	}
}
