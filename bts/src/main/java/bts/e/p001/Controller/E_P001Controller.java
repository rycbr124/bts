package bts.e.p001.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import bts.e.p001.VO.PagingVO;

public interface E_P001Controller {
	public ModelAndView e_p001_d001(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ModelAndView accWrite(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ModelAndView listEvent(PagingVO vo, String nowPage, String cntPerPage, HttpServletRequest request,
	         HttpServletResponse response) throws Exception;
}
