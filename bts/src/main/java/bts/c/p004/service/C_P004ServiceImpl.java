package bts.c.p004.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.c.p004.dao.C_P004DAO;
import bts.c.p004.vo.C_P004VO;
import bts.e.p001.VO.PagingVO;

@Service("c_p004Service")
public class C_P004ServiceImpl implements C_P004Service{
	@Autowired
	C_P004VO c_p004VO;
	@Autowired
	C_P004DAO c_p004DAO;
	
	@Override
	public List<C_P004VO> selectQuestion(String member_id, PagingVO pagingVO)throws Exception{
		return c_p004DAO.selectQuestion(member_id,pagingVO);
	}
	@Override
	public Integer listCount(String member_id) throws Exception {
		return c_p004DAO.pageCount(member_id);
	}
	@Override
	public void addQuestion(C_P004VO c_p004VO)throws Exception{
		String seq = c_p004DAO.questionSeq(c_p004VO);
		c_p004VO.setContact_no(seq);
		c_p004DAO.addQuestion(c_p004VO);
	}
	@Override
	public List<C_P004VO> questionDetail(String contact_no)throws Exception{
		return c_p004DAO.questionDetail(contact_no);
	}
	@Override
	public String answerDetail(String contact_no)throws Exception{
		return c_p004DAO.answerDetail(contact_no);
	}
}
