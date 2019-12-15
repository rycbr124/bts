package bts.h.p002.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.h.p001.vo.H_P001VO;
import bts.h.p002.DAO.H_P002DAO;
import bts.h.p002.VO.H_P002VO;

@Service("h_p002Service")
public class H_P002ServiceImpl implements H_P002Service{
	@Autowired
	H_P002DAO h_p002DAO;
	
	@Override
	public void insertResve(H_P002VO h_p002VO) throws Exception {
		
		h_p002DAO.insertResve(h_p002VO);
	}

	@Override
	public void updateRoomtype(H_P001VO h_p001VO) throws Exception {
		
		h_p002DAO.updateRoomtype(h_p001VO);
	}

}
