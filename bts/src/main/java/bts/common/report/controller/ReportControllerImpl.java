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
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;
import bts.common.report.service.ReportService;
import bts.common.report.vo.PnishVO;
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
	private static final String commentName="comment";
	
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
		makeReportForm(reviewName,vo,mav);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/write", method= {RequestMethod.POST,RequestMethod.GET})
	public String reportSubmit(@ModelAttribute("member_id") String reporter_id,@ModelAttribute ReportVO reportVO,HttpServletRequest request, HttpServletResponse response)throws Exception{
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
		makeReportForm(commentName,vo,mav);
		return mav;
	}	
	
	private void makeReportForm(String se_name,ReportVO vo,ModelAndView mav) {
		String report_se=repService.selectMenuCd(se_name);
		vo.setReport_se(report_se);
		List<PnishVO> pnish = repService.selectPnishList();
		mav.addObject("initValue",vo);
		mav.addObject("pnish",pnish);
	}
}
