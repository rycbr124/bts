package bts.c.p004.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.c.p004.dao.C_P004DAO;
import bts.c.p004.vo.C_P004VO;

@Service("c_p004Service")
public class C_P004ServiceImpl implements C_P004Service{
	@Autowired
	C_P004VO c_p004VO;
	@Autowired
	C_P004DAO c_p004DAO;
	
	@Override
	public void addQuestion(C_P004VO c_p004VO)throws Exception{
		String seq = c_p004DAO.questionSeq(c_p004VO);
		c_p004VO.setContact_no(seq);
		c_p004DAO.addQuestion(c_p004VO);
	}

}
