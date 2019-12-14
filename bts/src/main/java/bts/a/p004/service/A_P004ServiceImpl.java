package bts.a.p004.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.a.p004.dao.A_P004DAO;
import bts.a.p004.vo.A_P004VO;
import bts.c.p004.vo.C_P004VO;

@Service("a_p004Service")
public class A_P004ServiceImpl implements A_P004Service{
	@Autowired
	A_P004DAO a_p004DAO;
	@Override
	public List<C_P004VO> searchContact(Map<String,String> searchMap)throws Exception{
		return a_p004DAO.searchContact(searchMap);
	}
	@Override
	public List<C_P004VO> questionAnswer(String contact_no)throws Exception{
		return a_p004DAO.questionAnswer(contact_no);
	}
	@Override
	public void addAnswer(A_P004VO a_p004VO)throws Exception{
		 a_p004DAO.addAnswer(a_p004VO);
	}
	@Override
	public List<A_P004VO> selectAnswer(String contact_no)throws Exception{
		return a_p004DAO.selectAnswer(contact_no);
	}
}
