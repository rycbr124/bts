package bts.h.p001.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bts.e.p001.VO.PagingVO;
import bts.h.p001.service.H_P001Service;
import bts.h.p001.vo.H_P001VO;


@Controller("h_p001")
@RequestMapping(value = "/resve")
public class H_P001ControllerImpl implements H_P001Controller {
	
	@Autowired
	H_P001Service h_p001Service;
	@Autowired
	H_P001VO h_p001VO;
	
	@Override
	@RequestMapping(value = "/Info", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView pageInit(PagingVO pagingVO,
			@RequestParam(value="nowPage", required=false) String nowPage, 
			@RequestParam(value="cntPerPage" , required=false)String cntPerPage,  HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ModelAndView mav = new ModelAndView("/h/p001/d001");
		int total = h_p001Service.hotelListCount();
		
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		}else if(nowPage == null) {
			nowPage = "1";
		}else if(cntPerPage == null) {
			cntPerPage = "5";
		}
		
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		mav.addObject("paging",pagingVO);
		List<H_P001VO> hotelList = h_p001Service.hotelList(pagingVO);
		
		mav.addObject("hotelList", hotelList);
		
		return mav;
	}
}
