package bts.h.p001.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.e.p001.VO.PagingVO;
import bts.h.p001.dao.H_P001DAO;
import bts.h.p001.vo.H_P001VO;

@Service("h_p001Service")
public class H_P001ServiceImpl implements H_P001Service{

	@Autowired
	H_P001DAO h_p001DAO;
	
	@Override
	public List<H_P001VO> hotelList() throws Exception {
		
		return h_p001DAO.hotelList();
	}

	@Override
	public Integer hotelListCount() {

		return h_p001DAO.hotelListCount();
	}

	@Override
	public List<H_P001VO> guestList() {
		
		return h_p001DAO.guestList();
	}

	@Override
	public Integer guestListCount() {
		
		return h_p001DAO.guestListCount();
	}
	
	@Override
	public List<H_P001VO> motelList() {
		
		return h_p001DAO.motelList();
	}

	@Override
	public Integer motelListCount() {
		
		return h_p001DAO.motelListCount();
	}

	@Override
	public H_P001VO hotelResult(String lodging_id) throws Exception {

		return h_p001DAO.hotelResult(lodging_id);
	}

	@Override
	public List<H_P001VO> roomInfoResult(String lodging_id) throws Exception {

		return h_p001DAO.roomInfoResult(lodging_id);
	}

	

}
