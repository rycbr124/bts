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
	public List<H_P001VO> hotelList() throws DataAccessException {
		
		return sqlSession.selectList("mapper.reservation.hotelList");
	}

	@Override
	public Integer hotelListCount() {
		return sqlSession.selectOne("mapper.reservation.hotelListCount");
	}

	@Override
	public List<H_P001VO> guestList() {
		
		return sqlSession.selectList("mapper.reservation.guestList");
	}

	@Override
	public Integer guestListCount() {
		
		return sqlSession.selectOne("mapper.reservation.guestListCount");
	}
	
	@Override
	public List<H_P001VO> motelList() {
		
		return sqlSession.selectList("mapper.reservation.motelList");
	}

	@Override
	public Integer motelListCount() {
		
		return sqlSession.selectOne("mapper.reservation.motelListCount");
	}

	@Override
	public H_P001VO hotelResult(String lodging_id) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.reservation.hotelResult",lodging_id);
	}


	@Override
	public List<H_P001VO> roomInfoResult(String lodging_id) throws DataAccessException {
		
		return sqlSession.selectList("mapper.reservation.roomInfoResult",lodging_id);
	}

	

}