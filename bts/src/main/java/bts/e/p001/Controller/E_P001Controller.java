package bts.e.p001.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bts.e.p001.VO.E_P001VO;
import bts.e.p001.VO.PagingVO;

public interface E_P001Controller {
	public ModelAndView accWrite(HttpServletRequest request, HttpServletResponse response)throws Exception;
//	public ModelAndView listEvent(PagingVO vo, String nowPage, String cntPerPage, HttpServletRequest request,
//	         HttpServletResponse response) throws Exception;
	public ModelAndView selectAccompanyList(PagingVO pagingVO, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) throws Exception;
}
