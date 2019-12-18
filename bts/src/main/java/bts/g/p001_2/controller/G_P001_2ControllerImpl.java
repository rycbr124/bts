package bts.g.p001_2.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;
import bts.g.p001_2.service.G_P001_2Service;
import bts.g.p001_2.vo.G_P001_2VO;


@Controller("g_p001_2")
@RequestMapping(value="/recommend")
public class G_P001_2ControllerImpl implements G_P001_2Controller{
   @Autowired
   G_P001_2Service g_p001_2Service;
   
   @Autowired
   B_P001VO b_p001VO;
   
   @Autowired 
   G_P001_2VO g_p001_2VO;

   @Override
   @RequestMapping(value="/recommend_place" ,method={RequestMethod.POST,RequestMethod.GET})
   public ModelAndView P001_D001(HttpServletRequest request, HttpServletResponse response) throws Exception {      
	      Map<String, List<String>> searchResult = g_p001_2Service.searchCategory();
	      JSONObject totaObject = new JSONObject(searchResult);
	      ModelAndView mav = new ModelAndView("/g/p001_2/d001");
	      mav.addObject("result", totaObject.toJSONString());
	      return mav;
	   }

   @Override
   @RequestMapping(value="/recommend_course" ,method={RequestMethod.POST,RequestMethod.GET})
   public ModelAndView P001_D002(HttpServletRequest request, HttpServletResponse response) throws Exception {   
      Map<String, List<String>> searchResult = g_p001_2Service.courseCategory();
      JSONObject totaObject = new JSONObject(searchResult);
      ModelAndView mav = new ModelAndView("/g/p001_2/d002");
      mav.addObject("course", totaObject.toJSONString());   
      return mav;
   }

   @Override
   @RequestMapping(value="/place_detail" ,method={RequestMethod.POST,RequestMethod.GET})
   public ModelAndView P001_D003(@RequestParam(value="contentid",required=false) String contentid, @RequestParam(value="contenttypeid",required=false) String contenttypeid, HttpServletRequest request, HttpServletResponse response) throws Exception {
      System.out.println("place param 값 : " + contentid);
      String message = null;
      String member_id = null;
      
      HttpSession session = request.getSession();
	  b_p001VO = (B_P001VO)session.getAttribute("memberInfo");
	  member_id = b_p001VO.getMember_id();
	   
	  g_p001_2VO.setMember_id(member_id);
	  g_p001_2VO.setContent_id(contentid);

	  boolean command = g_p001_2Service.findWishlist(g_p001_2VO);
      if(command == true) {
    	  message = "exist";
      }else {
    	  message = "not";
      }
      
      ModelAndView mav = new ModelAndView("/g/p001_2/d003");
      mav.addObject("contentid", contentid);
      mav.addObject("contenttypeid", contenttypeid);
      mav.addObject("command", message);
      return mav;
   }

   @Override
   @RequestMapping(value="/course_detail" ,method={RequestMethod.POST,RequestMethod.GET})
   public ModelAndView P001_D004(@RequestParam("contentid") String contentid, @RequestParam("contenttypeid") String contenttypeid, HttpServletRequest request, HttpServletResponse response) throws Exception {
      System.out.println("course param 값 : " + contentid);
      System.out.println("contentType : " + contenttypeid);
      String message = null;
      String member_id = null;
      
      HttpSession session = request.getSession();
	  b_p001VO = (B_P001VO)session.getAttribute("memberInfo");
	  member_id = b_p001VO.getMember_id();
	   
	  g_p001_2VO.setMember_id(member_id);
	  g_p001_2VO.setContent_id(contentid);

	  boolean command = g_p001_2Service.findWishlist(g_p001_2VO);
      if(command == true) {
    	  message = "exist";
      }else {
    	  message = "not";
      }
      
      ModelAndView mav = new ModelAndView("/g/p001_2/d004");
      mav.addObject("contentid", contentid);
      mav.addObject("contenttypeid", contenttypeid);
      mav.addObject("command", message);
      return mav;
   }

   @Override
   @RequestMapping(value="/insert_wishlist" ,method={RequestMethod.POST,RequestMethod.GET}, produces = "text/html; charset=utf8")
   public @ResponseBody String wishList(@RequestParam("contentid") String contentid, @RequestParam("contenttypeid") String contenttypeid, HttpServletRequest request, HttpServletResponse response)
         throws Exception {
	   String message = null;
	   String member_id = null;
	   System.out.println("contentType : " + contenttypeid);
	   

	   try {
		   HttpSession session = request.getSession();
		   b_p001VO = (B_P001VO)session.getAttribute("memberInfo");
		   member_id = b_p001VO.getMember_id();
		   
		   g_p001_2VO.setMember_id(member_id);
		   g_p001_2VO.setContent_id(contentid);

		   boolean command = g_p001_2Service.findWishlist(g_p001_2VO);
		   if(contenttypeid.equals("25")) {
			   if(command == true) {
				   message = "<script>";
				   message += "alert('이미 추가한 명소입니다..');";
				   message += "location.href='/bts/recommend/course_detail?contentid=" + contentid + "&contenttypeid=" + contenttypeid + "'";
				   message += "</script>";
				   return message;
			   }else {
				   g_p001_2Service.insertWishlist(g_p001_2VO);
				   message = "<script>";
				   message += "alert('위시리스트에 추가하였습니다.');";
				   message += "location.href='/bts/recommend/course_detail?contentid=" + contentid + "&contenttypeid=" + contenttypeid + "'";
				   message += "</script>";
				   
				   return message;
				   
			   }
			   
		   }else {
			   if(command == true) {
				   message = "<script>";
				   message += "alert('이미 추가한 명소입니다..');";
				   message += "location.href='/bts/recommend/place_detail?contentid=" + contentid + "&contenttypeid=" + contenttypeid + "'";
				   message += "</script>";
				   return message;
			   }else {
				   g_p001_2Service.insertWishlist(g_p001_2VO);
				   message = "<script>";
				   message += "alert('위시리스트에 추가하였습니다.');";
				   message += "location.href='/bts/recommend/place_detail?contentid=" + contentid + "&contenttypeid=" + contenttypeid + "'";
				   message += "</script>";
				   
				   return message;
				   
			   }
			   
		   }
		   
		   
	   }catch(Exception e){
		   message = "<script>";
		   message += "alert('로그인 후 이용가능합니다.');";
		   message += "history.go(-1)";
		   message += "</script>";
		   
		    
		   return message;
	   }
     
   }
}