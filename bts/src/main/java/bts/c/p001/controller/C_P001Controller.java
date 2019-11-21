package bts.c.p001.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;

public interface C_P001Controller {
	public ModelAndView pageInit(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 회원탈퇴 페이지
	public String secession();
	
	// 회원탈퇴
	public ModelAndView secessionProc(B_P001VO b_p001VO, HttpSession session);
	
	// 패스워드 체크
	public String passCheck(String password, HttpServletRequest request, HttpServletResponse response);

	
}
