package bts.g.p001_2.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;
import bts.g.p001_2.service.G_P001_2Service;


@Controller("g_p001_2")
@RequestMapping(value="/recommend")
public class G_P001_2ControllerImpl implements G_P001_2Controller{
	@Autowired
	G_P001_2Service g_p001_2Service;
	
	@Autowired
	B_P001VO b_p001VO;

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
	public ModelAndView P001_D003(@RequestParam("contentid") String contentid, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("place param 값 : " + contentid);
		ModelAndView mav = new ModelAndView("/g/p001_2/d003");
		mav.addObject("contentid", contentid);
		return mav;
	}

	@Override
	@RequestMapping(value="/course_detail" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView P001_D004(@RequestParam("contentid") String contentid, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("course param 값 : " + contentid);
		ModelAndView mav = new ModelAndView("/g/p001_2/d004");
		mav.addObject("contentid", contentid);
		return mav;
	}

	@Override
	@RequestMapping(value="/insert_wishlist" ,method={RequestMethod.POST,RequestMethod.GET})
	public ResponseEntity wishList(@RequestParam("contentid") String contentid, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("111111111112222222222222 Id값 : " + contentid);
		HttpSession session = request.getSession();
		
		b_p001VO = (B_P001VO)session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		System.out.println("33333333333344444444444 Id값 : " + member_id);
		
		return null;
	}
	
	

}