package bts.common.report.controller;

import java.util.List;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;
import bts.common.report.service.ReportService;
import bts.common.report.vo.PnishVO;
import bts.common.report.vo.ReportVO;

@Controller("reportController")
@SessionAttributes("reportForm")
@RequestMapping(value="/report")
public class ReportControllerImpl implements ReportController {
	@Autowired
	Provider<B_P001VO> b_p001Pro;	

	@Autowired
	Provider<ReportVO> reportPro;	
	
	@Autowired
	ReportService repService;
	
	private static final String reviewName="review";
	private static final String commentName="comment";
	private static final String planName="plan";
	private static final String accompanyName="accompany";
	
	
	@ModelAttribute("member_id")
	public String getMember_id(HttpServletRequest request) {
		HttpSession session = request.getSession();
		B_P001VO b_p001VO = (B_P001VO) session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		return member_id;
	}	
	
	@RequestMapping(value="/article/review", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView reportArticle(@ModelAttribute ReportVO vo,HttpServletRequest request, HttpServletResponse response)throws Exception{
		ModelAndView mav = new ModelAndView("/common/report");
		String contents=repService.selectReviewContents(vo);
		makeReportForm(reviewName,vo,mav,contents);
		return mav;
	}
	
	@RequestMapping(value="/article/accompany", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView reportAccompany(@ModelAttribute ReportVO vo,HttpServletRequest request, HttpServletResponse response)throws Exception{
		ModelAndView mav = new ModelAndView("/common/report");
		String contents=repService.selectAccContents(vo);
		makeReportForm(accompanyName,vo,mav,contents);
		return mav;
	}

	
	@RequestMapping(value="/article/plan", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView reportPlan(@ModelAttribute ReportVO vo,HttpServletRequest request, HttpServletResponse response)throws Exception{
		ModelAndView mav = new ModelAndView("/common/report");
		String contents=repService.selectPlanContents(vo);
		makeReportForm(planName,vo,mav,contents);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/write", method= {RequestMethod.POST,RequestMethod.GET})
	public String reportSubmit(@ModelAttribute("member_id") String reporter_id,@ModelAttribute("reportForm") ReportVO reportVO,HttpServletRequest request, HttpServletResponse response)throws Exception{
		reportVO.setReporter_id(reporter_id);
		int result = repService.insertReport(reportVO);
		if(result==1) {
			return "true";
		}else {
			return "false";
		}
	}

	@RequestMapping(value="/comment", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView reportComment(@ModelAttribute ReportVO vo,HttpServletRequest request, HttpServletResponse response)throws Exception{
		ModelAndView mav = new ModelAndView("/common/report");
		String contents=repService.selectCommentContents(vo);
		makeReportForm(commentName,vo,mav,contents);
		return mav;
	}	
	
	private void makeReportForm(String se_name,ReportVO vo,ModelAndView mav,String target_contents) {
		String report_se=repService.selectMenuCd(se_name);
		vo.setReport_se(report_se);
		vo.setTarget_contents(target_contents);
		List<PnishVO> pnish = repService.selectPnishList();
		mav.addObject("reportForm",vo);
		mav.addObject("pnish",pnish);
	}
}
