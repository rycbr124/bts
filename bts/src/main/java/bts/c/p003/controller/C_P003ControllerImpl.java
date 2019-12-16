package bts.c.p003.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bts.c.p003.service.C_P003Service;
import bts.c.p003.vo.C_P003VO;
import bts.e.p001.VO.PagingVO;

@Controller("c_p003Controller")
@RequestMapping("/my/reserv")
public class C_P003ControllerImpl implements C_P003Controller{

	@Autowired
	C_P003Service c_p003Service;
	@Autowired
	C_P003VO c_p003VO;
	
	@Override
	@RequestMapping(value="/reservList", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView myReservList(PagingVO pagingVO, 
			@RequestParam(value="nowPage",required=false) String nowPage,@RequestParam(value="cntPerPage",required=false) String cntPerPage, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/c/p003/d001");
		HttpSession session = request.getSession();
		String member_id = (String)session.getAttribute("member_id");
		
		int total = c_p003Service.myReservCount(member_id);
		
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		}else if(nowPage == null) {
			nowPage = "1";
		}else if(cntPerPage == null) {
			cntPerPage = "5";
		}
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		pagingVO.setMember_id(member_id);
		c_p003VO.setMember_id(member_id);
		
		List<C_P003VO> list = c_p003Service.myReservList(pagingVO);
		mav.addObject("reservList",list);
		mav.addObject("paging",pagingVO);
		
		return mav;
	}
	
	@Override
	   @RequestMapping(value="/reservCancle" ,method = { RequestMethod.GET, RequestMethod.POST})
	   @ResponseBody
	   public Map<String, Object> reservCancle(HttpServletRequest request, HttpServletResponse response) throws Exception{
	      request.setCharacterEncoding("utf-8");
	      
	      String resve_no = request.getParameter("resve_no");
	      
	      Map<String, Object> dataMap = new HashMap<String, Object>();
	      Map<String, Object> resultMap = new HashMap<String, Object>();
	      dataMap.put("resve_no", resve_no);
	      
	      System.out.println("=======================>>"+dataMap.toString());
	      
	      try {
	    	  c_p003Service.cancle(dataMap);
	         resultMap.put("error_yn", "N");   
	      } catch (Exception e) {
	         resultMap.put("error_yn", "Y");
	         resultMap.put("error_text", "�����߻�");
	         e.printStackTrace();
	      }      
	      return resultMap;
	   }
	
	
}
