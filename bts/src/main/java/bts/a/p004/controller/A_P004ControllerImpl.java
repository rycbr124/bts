package bts.a.p004.controller;

import java.util.HashMap;
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

import bts.a.p004.service.A_P004Service;
import bts.a.p004.vo.A_P004VO;
import bts.b.p001.VO.B_P001VO;
import bts.c.p004.vo.C_P004VO;

@Controller("a_p004")
@RequestMapping(value="/admin")
public class A_P004ControllerImpl implements A_P004Controller{
	@Autowired
	A_P004VO a_p004VO;
	@Autowired
	C_P004VO c_p004VO;
	@Autowired
	B_P001VO b_p001VO;
	@Autowired
	A_P004Service a_p004Service;
	
	@Override
	@RequestMapping(value="/question",method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView question(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ModelAndView mav = new ModelAndView("/a/p004/d001");
		return mav;
	}
	@Override
	@RequestMapping(value="/searchContact", method= {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody public Map<String, Object> searchContact(@RequestParam(value="p_id",required=false) String p_id,HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String,String> searchMap = new HashMap<String, String>();
		Map<String, Object> selectMap = new HashMap<String, Object>();
		searchMap.put("member_id", p_id);
		
		List<C_P004VO> questionList = a_p004Service.searchContact(searchMap);
		
		selectMap.put("Data", questionList);
		return selectMap;
	}
	@Override
	@RequestMapping(value="/question_answer")
	@ResponseBody public Map<String,Object> questionAnswer(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String contact_no = request.getParameter("contact_no");
		c_p004VO.setContact_no(contact_no);
		Map<String,Object> result = new HashMap<>();
		List<C_P004VO> questionAnswer = a_p004Service.questionAnswer(contact_no);
		List<A_P004VO> selectAnswer = a_p004Service.selectAnswer(contact_no);
		System.out.println(questionAnswer);
		System.out.println(selectAnswer);
		result.put("questionInfo", questionAnswer);
		result.put("answerInfo", selectAnswer);
		
		System.out.println(result);
		return result;
	}
	@Override
	@RequestMapping(value="/addAnswer")
	public void addAnswer(HttpServletRequest request, HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO) session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		a_p004VO.setMember_id(member_id);
		String contact_no = request.getParameter("contact_no");
		String answer = request.getParameter("answer_content");
		a_p004VO.setContact_no(contact_no);
		a_p004VO.setContents(answer);
		
		a_p004Service.addAnswer(a_p004VO);
		response.sendRedirect("/bts/admin/question");
	}
}
