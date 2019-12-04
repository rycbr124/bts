package bts.c.p004.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.protocol.HTTP;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;
import bts.c.p004.service.C_P004Service;
import bts.c.p004.vo.C_P004VO;
import bts.e.p001.VO.PagingVO;

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
	@RequestMapping(value="/questionMain",method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView questionMain(PagingVO pagingVO
			,@RequestParam(value="nowPage",required=false) String nowPage
			,@RequestParam(value="cntPerPage",required=false) String cntPerPage,HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO) session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		c_p004VO.setMember_id(member_id);
		
		int total = c_p004Service.listCount(member_id);
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		}else if(nowPage == null) {
			nowPage = "1";
		}else if(cntPerPage == null) {
			cntPerPage = "5";
		}
		
		ModelAndView mav = new ModelAndView("/c/p004/d001");
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
	
		List<C_P004VO> questionList = c_p004Service.selectQuestion(member_id,pagingVO);
		mav.addObject("paging",pagingVO);
		mav.addObject("questionList",questionList);
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
		c_p004Service.addQuestion(c_p004VO);
		response.sendRedirect("/bts/question/questionMain");
	}
	@Override
	@RequestMapping(value="question_detail")
	public ModelAndView questionDetail(HttpServletRequest request, HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO) session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		c_p004VO.setMember_id(member_id);
		String contact_no = request.getParameter("contact_no");
		c_p004VO.setContact_no(contact_no);
		List<C_P004VO> questionDetail = c_p004Service.questionDetail(contact_no);
		String answerDetail = c_p004Service.answerDetail(contact_no);
		System.out.println(answerDetail);
		ModelAndView mav = new ModelAndView("/c/p004/d002");
		mav.addObject("answerDetail", answerDetail);
		mav.addObject("question_info", questionDetail);
		return mav;
	}
}