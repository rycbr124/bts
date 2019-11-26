package bts.c.p004.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;
import bts.c.p004.service.C_P004Service;
import bts.c.p004.vo.C_P004VO;

@Controller("c_p004")
@RequestMapping(value="/question")
public class C_P004ControllerImpl implements C_P004Controller{
	@Autowired
	B_P001VO b_p001VO;
	@Autowired
	C_P004VO c_p004VO;
	@Autowired
	C_P004Service c_p004Service;
	
	@Override
	@RequestMapping(value="questionMain")
	public ModelAndView questionMain(HttpServletRequest request, HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO) session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		c_p004VO.setMember_id(member_id);
		ModelAndView mav = new ModelAndView("/c/p004/d001");
		return mav;
	}
	@Override
	@RequestMapping(value="question_write")
	public ModelAndView questionWrite(HttpServletRequest request, HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO) session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		c_p004VO.setMember_id(member_id);
		ModelAndView mav = new ModelAndView("/c/p004/d003");
		mav.addObject("member_id",member_id);
		return mav;
	}
	@Override
	@RequestMapping(value="add_question")
	public void addQuestion(HttpServletRequest request, HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO) session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String type = request.getParameter("question_type");
		c_p004VO.setMember_id(member_id);
		c_p004VO.setTitle(title);
		c_p004VO.setContents(content);
		c_p004VO.setContact_type(type);
		System.out.println("아이디 : "+member_id);
		System.out.println("제목 : "+title);
		System.out.println("내용 : "+content);
		System.out.println("타입: "+type);
		c_p004Service.addQuestion(c_p004VO);
		response.sendRedirect("/bts/question/questionMain");
	}
}
