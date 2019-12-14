package bts.h.p002.DAO;

import org.springframework.dao.DataAccessException;

import bts.h.p001.vo.H_P001VO;
import bts.h.p002.VO.H_P002VO;

public interface H_P002DAO {
	public void insertResve(H_P002VO h_p002VO) throws DataAccessException;
	public void updateRoomtype(H_P001VO h_p001VO) throws DataAccessException;
}
