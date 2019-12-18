package bts.c.p007.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bts.c.p007.VO.C_P007VO;
import bts.c.p007.service.C_P007Service;
import bts.e.p001.VO.PagingVO;
import bts.e.p001.VO.PagingVO2;

@Controller("c_p007Controller")
@RequestMapping("/my/accompany")
public class C_P007ControllerImpl implements C_P007Controller{
	@Autowired
	C_P007Service c_p007Service;
	@Autowired
	C_P007VO c_p007VO;

	@Override
	@RequestMapping(value="/accList", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView myAccList(PagingVO pagingVO
			,@RequestParam(value="nowPage", required=false) String nowPage
			,@RequestParam(value="cntPerPage", required=false) String cntPerPage
			,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/c/p007/d001");
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
		System.out.println("member_idddddddddddddddd:" + member_id);
		
		int total = c_p007Service.listCount(member_id);
		int total2 = c_p007Service.imAccCount(member_id);
		
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		}else if(nowPage == null) {
			nowPage = "1";
		}else if(cntPerPage == null) {
			cntPerPage = "5";
		}
		
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		c_p007VO.setMember_id(member_id);
		pagingVO.setMember_id(member_id);
		List<C_P007VO> list = c_p007Service.myAccList(pagingVO);
		System.out.println("mypagelistttttttttttt:" + list);
		mav.addObject("paging",pagingVO);
		mav.addObject("accList", list);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/accList2", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView myAccList2(PagingVO2 pagingVO2
			,@RequestParam(value="nowPage2", required=false) String nowPage2
			,@RequestParam(value="cntPerPage2", required=false) String cntPerPage2
			,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/c/p007/d002");
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
		System.out.println("member_idddddddddddddddd:" + member_id);		
		int total2 = c_p007Service.imAccCount(member_id);
			
		if(nowPage2 == null && cntPerPage2 == null) {
			nowPage2 = "1";
			cntPerPage2 = "5";
		}else if(nowPage2 == null) {
			nowPage2 = "1";
		}else if(cntPerPage2 == null) {
			cntPerPage2 = "5";
		}
			
		pagingVO2 = new PagingVO2(total2, Integer.parseInt(nowPage2),Integer.parseInt(cntPerPage2));
		c_p007VO.setMember_id(member_id);	
		pagingVO2.setMember_id(member_id);
		List<C_P007VO> myList = c_p007Service.imAccList(pagingVO2);
		mav.addObject("paging2",pagingVO2);
		mav.addObject("myList",myList);
		return mav;
	}
	
	
}
