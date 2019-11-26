package bts.common.report.controller;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;
import bts.common.report.service.ReportService;
import bts.common.report.vo.ReportVO;

@Controller("reportController")
@RequestMapping(value="/report")
public class ReportControllerImpl implements ReportController {
	@Autowired
	Provider<B_P001VO> b_p001Pro;	

	@Autowired
	Provider<ReportVO> reportPro;	
	
	@Autowired
	ReportService repService;
	
	private static final String reviewName="review";
	
	@ModelAttribute("member_id")
	public String getMember_id(HttpServletRequest request) {
		HttpSession session = request.getSession();
		B_P001VO b_p001VO = (B_P001VO) session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		return member_id;
	}	
	
	@RequestMapping(value="/article/review", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView reportArticle(@ModelAttribute("member_id") String member_id,HttpServletRequest request, HttpServletResponse response)throws Exception{
		ModelAndView mav = new ModelAndView("/common/report");
		String report_se=repService.selectMenuCd(reviewName);
		System.out.println("===================>"+member_id);
		System.out.println("=========report_se==========>"+report_se);
		System.out.println("===================>"+request.getParameter("contents_cd"));
		ReportVO vo = reportPro.get();
		vo.setMember_id(member_id);
		vo.setReport_se(report_se);
		vo.setContents_cd(request.getParameter("contents_cd"));
		mav.addObject("initValue",vo);
		return mav;
	}
	
	@RequestMapping(value="/comment", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView reportComment(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ModelAndView mav = new ModelAndView("/common/report");
		System.out.println("===================>"+request.getParameter("contents_cd"));
		return mav;
	}
	
}
