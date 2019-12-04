package bts.c.p004.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.protocol.HTTP;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bts.e.p001.VO.PagingVO;

public interface C_P004Controller {
	public ModelAndView questionMain(PagingVO pagingVO,@RequestParam(value="nowPage",required=false)String nowPage,@RequestParam(value="cntPerPage",required=false)String cntPerPage,HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ModelAndView questionWrite(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public void addQuestion(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ModelAndView questionDetail(HttpServletRequest request, HttpServletResponse response)throws Exception;
}