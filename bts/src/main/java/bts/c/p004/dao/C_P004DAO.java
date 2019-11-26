package bts.c.p004.dao;

import bts.c.p004.vo.C_P004VO;

public interface C_P004DAO {
	public void addQuestion(C_P004VO c_p004VO)throws Exception;
	public String questionSeq(C_P004VO c_p004VO)throws Exception;
}
