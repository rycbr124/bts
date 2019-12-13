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
	public List<H_P001VO> hotelList(PagingVO pagingVO) throws Exception {
		
		return h_p001DAO.hotelList(pagingVO);
	}

	@Override
	public Integer hotelListCount() {

		return h_p001DAO.hotelListCount();
	}

}
