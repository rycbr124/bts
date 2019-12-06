package bts.c.p004.dao;

import java.util.List;

import bts.c.p004.vo.C_P004VO;
import bts.e.p001.VO.PagingVO;

public interface C_P004DAO {
	public List<C_P004VO> selectQuestion(String member_id,PagingVO pagingVO)throws Exception;
	public Integer pageCount(String member_id)throws Exception;
	public List<C_P004VO> questionDetail(String contact_no)throws Exception;
	public void addQuestion(C_P004VO c_p004VO)throws Exception;
	public String questionSeq(C_P004VO c_p004VO)throws Exception;
	public String answerDetail(String contact_no)throws Exception;
}
