package bts.h.p001.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import bts.e.p001.VO.PagingVO;
import bts.h.p001.vo.H_P001VO;

public interface H_P001Controller {

	public ModelAndView pageInit( PagingVO pagingVO, String nowPage, String cntPerPage,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView hotelView(String lodging_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
