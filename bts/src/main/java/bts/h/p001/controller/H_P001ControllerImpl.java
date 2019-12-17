package bts.h.p001.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSpinnerUI;

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
			cntPerPage = "6";
		}else if(nowPage == null) {
			nowPage = "1";
		}else if(cntPerPage == null) {
			cntPerPage = "6";
		}
		
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		mav.addObject("paging",pagingVO);
		
		
		
		List<H_P001VO> hotelList = h_p001Service.hotelList();
		
		List<H_P001VO> guestList = h_p001Service.guestList();
		
		List<H_P001VO> motelList = h_p001Service.motelList();
		
		
		
		mav.addObject("hotelList", hotelList);
		
		mav.addObject("guestList", guestList);
		
		mav.addObject("motelList", motelList);
		
		return mav;
	}

	@Override
	@RequestMapping(value="/hotelView", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView hotelView(@RequestParam("lodging_id")String lodging_id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView("/h/p001/d002");
		H_P001VO hotelResult = h_p001Service.hotelResult(lodging_id);
		List<H_P001VO> roomResult = h_p001Service.roomInfoResult(lodging_id);
		String lodging_se = hotelResult.getLodging_se();
		
		List<String> descriptionList = new ArrayList<>();
		if(lodging_se.equals( "HT") || lodging_se.equals("GH")) {
			String[] discriptions = hotelResult.getDescription().split("\\.");
			for(int i=0; i< discriptions.length; i++) {
				descriptionList.add(discriptions[i]);
			}
			System.out.println("descriptionListttt: "+descriptionList);
		}
		else if(lodging_se.equals("MT")){
			String[] discription = hotelResult.getDescription().split("\\*");		
			for(int i=0; i< discription.length; i++) {
				System.out.println(discription[i]);
				descriptionList.add(discription[i]);
			}
			System.out.println("descriptionListttt: "+descriptionList);
		}
		
		mav.addObject("descriptionList",descriptionList);
		mav.addObject("roomResult",roomResult);
		mav.addObject("hotelResult",hotelResult);
		return mav;
	}
}
