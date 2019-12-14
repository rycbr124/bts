package bts.a.p004.service;

import java.util.List;
import java.util.Map;

import bts.a.p004.vo.A_P004VO;
import bts.c.p004.vo.C_P004VO;

public interface A_P004Service {
	public List<C_P004VO> searchContact(Map<String, String> searchMap)throws Exception;
	public List<C_P004VO> questionAnswer(String contact_no)throws Exception;
	public void addAnswer(A_P004VO a_p004VO)throws Exception;
	public List<A_P004VO> selectAnswer(String contact_no)throws Exception;
}
