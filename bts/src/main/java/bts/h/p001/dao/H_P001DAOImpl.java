package bts.h.p001.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.e.p001.VO.PagingVO;
import bts.h.p001.vo.H_P001VO;

@Repository("h_p001DAO")
public class H_P001DAOImpl implements H_P001DAO{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<H_P001VO> hotelList(PagingVO pagingVO) throws DataAccessException {
		
		return sqlSession.selectList("mapper.reservation.hotelList",pagingVO);
	}

	@Override
	public Integer hotelListCount() {
		return sqlSession.selectOne("mapper.reservation.hotelListCount");
	}

}
