package bts.h.p002.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.h.p001.vo.H_P001VO;
import bts.h.p002.VO.H_P002VO;

@Repository("h_p002DAO")
public class H_P002DAOImpl implements H_P002DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertResve(H_P002VO h_p002VO) throws DataAccessException {
		sqlSession.insert("mapper.reservation.resveInsert",h_p002VO);
	}

	@Override
	public void updateRoomtype(H_P001VO h_p001VO) throws DataAccessException {
		sqlSession.update("mapper.reservation.updateRoomType",h_p001VO);
		
	}

}
