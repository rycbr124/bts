package bts.c.p003.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import bts.e.p001.VO.PagingVO;

public interface C_P003Controller {
	public ModelAndView myReservList(PagingVO pagingVO, String nowPage, String cntPerPage, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public Map<String, Object> reservCancle(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
