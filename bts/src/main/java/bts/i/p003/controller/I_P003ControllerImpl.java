package bts.i.p003.controller;

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
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;
import bts.i.p002.VO.I_P002VO_1;
import bts.i.p002.VO.I_P002VO_2;
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
		JSONObject rootObject = new JSONObject(planRoot);
		mav.addObject("root", rootObject.toJSONString());
		return mav;
		
	}
}
