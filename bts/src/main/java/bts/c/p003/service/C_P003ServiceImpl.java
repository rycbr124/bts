package bts.c.p003.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.c.p003.dao.C_P003DAO;
import bts.c.p003.vo.C_P003VO;
import bts.e.p001.VO.PagingVO;

@Service("c_p003Service")
public class C_P003ServiceImpl implements C_P003Service{
	@Autowired
	C_P003DAO c_p003DAO;
	
	@Override
	public List<C_P003VO> myReservList(PagingVO pagingVO) throws Exception {
		
		return c_p003DAO.myReservList(pagingVO);
	}

	@Override
	public Integer myReservCount(String member_id) {

		return c_p003DAO.myReservCount(member_id);
	}

	@Override
	public void cancle(Map<String, Object> dataMap) {
		c_p003DAO.cancle(dataMap);
		
	}

}
