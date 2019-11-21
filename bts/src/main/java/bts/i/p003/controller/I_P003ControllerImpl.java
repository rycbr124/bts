package bts.i.p003.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import bts.b.p001.VO.B_P001VO;
import bts.i.p002.VO.I_P002VO_1;
import bts.i.p002.VO.I_P002VO_2;
import bts.i.p002.VO.I_P002VO_3;
import bts.i.p002.service.I_P002Service;

@Controller("i_p003")
@RequestMapping(value="/plan_detail")
public class I_P003ControllerImpl implements I_P003Controller{
	@Autowired
	B_P001VO b_p001VO;
	@Autowired
	I_P002VO_1 i_p002VO_1;
	@Autowired
	I_P002VO_2 i_p002VO_2;
	@Autowired
	I_P002VO_3 i_p002VO_3;
	@Autowired
	I_P002Service i_p002Service; 
	
	
	@Override
	@RequestMapping(value="/plan_detail", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView I_P003_D001(HttpServletRequest request, HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO)session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		i_p002VO_1.setMember_id(member_id);
		String plan_no = request.getParameter("plan_no");
		ModelAndView mav = new ModelAndView("/i/p003/d001");
		Map<String, List<String>> planRoot = i_p002Service.planRoot(plan_no);
		//VO러 플래너 받기
		i_p002VO_2.setPlan_no(plan_no);
		
		Map<String,List<String>> planner = i_p002Service.planner(plan_no);
		JSONObject rootObject = new JSONObject(planRoot);
		JSONObject plannerObj = new JSONObject(planner);
		mav.addObject("root", rootObject.toJSONString());
		mav.addObject("planner", plannerObj.toJSONString());
		return mav;
		
		
	}
	@Override
	@RequestMapping(value="/plan_modify", method= {RequestMethod.POST,RequestMethod.GET})
	public void modify(HttpServletRequest request, HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO)session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		i_p002VO_1.setMember_id(member_id);
		String plan_no = request.getParameter("plan_no");
		i_p002VO_1.setPlan_no(plan_no);
		i_p002VO_2.setPlan_no(plan_no);
		i_p002VO_3.setPlan_no(plan_no);
		
		Map<String,List<String>> planner_info = i_p002Service.planner(plan_no);
		Map<String,List<String>> detail_info = i_p002Service.planRoot(plan_no);
		Map<String,List<String>> tagList = i_p002Service.tagList(plan_no);
		JSONObject planner_obj = new JSONObject(planner_info);
		JSONObject detail_obj = new JSONObject(detail_info);
		JSONObject tag_obj = new JSONObject(tagList);
		
		String planner = planner_obj.toJSONString();
		String detail = detail_obj.toJSONString();
		String tag = tag_obj.toJSONString();
		
		request.setAttribute("detailInfo", detail);
		request.setAttribute("plannerInfo", planner);
		request.setAttribute("tag_list", tag);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/plan/plan");
		dispatcher.forward(request, response);
		
	}
}
