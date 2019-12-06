package bts.c.p002.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import bts.e.p001.VO.PagingVO;
import bts.e.p001.VO.PagingVO2;
import bts.e.p001.VO.PagingVO3;

public interface C_P002Controller {
	
	public ModelAndView myBoardList(PagingVO pagingVO,PagingVO2 pagingVO2, PagingVO3 pagingVO3
			,String nowPage, String cntPerPage
			,String nowPage2, String cntPerPage2
			,String nowPage3, String cntPerPage3
			,HttpServletRequest request, HttpServletResponse response) throws Exception;
}
