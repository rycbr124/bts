package bts.c.p004.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bts.c.p004.vo.C_P004VO;

public interface C_P004Service {
	public void addQuestion(C_P004VO c_p004VO)throws Exception;
}
