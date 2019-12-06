package bts.i.p001.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bts.e.p001.VO.PagingVO;

public interface I_P001Controller {
	public ModelAndView I_P001_D001(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public void deletePlan(String plan_no, HttpServletRequest request, HttpServletResponse response)throws Exception;
}
