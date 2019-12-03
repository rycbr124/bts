package bts.c.p007.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.c.p007.DAO.C_P007DAO;
import bts.c.p007.VO.C_P007VO;
import bts.e.p001.VO.PagingVO;

@Service("c_p007Service")
public class C_P007ServiceImpl implements C_P007Service{
	@Autowired
	C_P007DAO c_p007DAO;
	
	@Override
	public List<C_P007VO> myAccList(PagingVO pagingVO) throws Exception {
		
		return c_p007DAO.myAccList(pagingVO);
	}

	@Override
	public Integer listCount(String member_id) throws Exception {		
		return c_p007DAO.listCount(member_id);
	}

}
