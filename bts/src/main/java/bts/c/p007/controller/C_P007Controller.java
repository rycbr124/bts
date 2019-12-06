package bts.c.p007.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import bts.e.p001.VO.PagingVO;

public interface C_P007Controller {
	public ModelAndView myAccList(PagingVO pagingVO,String nowPage, String cntPerPage,HttpServletRequest request, HttpServletResponse response) throws Exception;
}
