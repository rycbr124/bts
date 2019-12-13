package bts.c.p006.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import bts.b.p001.VO.B_P001VO;
import bts.c.p006.dao.C_P006DAO;
import bts.c.p006.vo.C_P006VO;

@Service("c_p006Service")
public class C_P006ServiceImpl implements C_P006Service{
	@Autowired
	C_P006DAO c_p006DAO;
	
	@Override
	public List<B_P001VO> selectMemberList(Map<String,String> searchMap) throws DataAccessException {
		List<B_P001VO> list=c_p006DAO.selectMemberList(searchMap);		
		return list;
	}
	
	@Override
	public List<C_P006VO> selectMessageList(C_P006VO c_p006VO) throws DataAccessException{
		List<C_P006VO> list=c_p006DAO.selectMessageList(c_p006VO);
		return list;
	}

	@Override
	public void insertMessage(C_P006VO c_p006VO) throws DataAccessException{
		c_p006DAO.insertMessage(c_p006VO);
	}
	
	@Override
	public List<B_P001VO> selectSearchList(Map<String,String> searchMap) throws DataAccessException{
		List<B_P001VO> list=c_p006DAO.selectSearchList(searchMap);		
		return list;
	}
	
}
