package bts.i.p001.controller;

import javax.inject.Provider;
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
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;
import bts.e.p001.VO.PagingVO;
import bts.i.p002.VO.I_P002VO_1;
import bts.i.p002.VO.I_P002VO_2;
import bts.i.p002.VO.I_P002VO_3;
import bts.i.p002.service.I_P002Service;

@Controller("i_p001")
@RequestMapping(value="/planner")
public class I_P001ControllerImpl implements I_P001Controller {
	@Autowired
	I_P002Service i_p002Service;
	@Autowired
	B_P001VO b_p001VO;
	@Autowired
	I_P002VO_1 i_p002VO_1;
	@Autowired
	I_P002VO_2 i_p002VO_2;
	@Autowired
	I_P002VO_3 i_p002VO_3;
	@Autowired
	Provider<bts.common.PagingVO> pagingProvider;

	
	@Override
	@RequestMapping(value="/planner" , method = {RequestMethod.POST , RequestMethod.GET})
	public ModelAndView I_P001_D001(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO)session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		
		int count = i_p002Service.listCount(member_id);
		Map<String,List<String>> searchPlan = i_p002Service.planList(member_id);
		JSONObject totalObject = new JSONObject(searchPlan);
		ModelAndView mav = new ModelAndView("/i/p001/d001");
		
		mav.addObject("list", totalObject.toJSONString());
		return mav;
	}
	@Override
	@RequestMapping(value="/delete_plan", method= {RequestMethod.POST, RequestMethod.GET})
	public void deletePlan(@RequestParam(value="plan_no",required=false) String plan_no, HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO)session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		i_p002VO_1.setMember_id(member_id);
		i_p002Service.delPlan(plan_no);
		
		response.sendRedirect("/bts/planner/planner");
	}
}
