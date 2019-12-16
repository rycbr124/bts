package bts.a.p002.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import bts.a.p002.service.A_P002Service;
import bts.a.p002.vo.A_P002VO_1;
import bts.common.report.service.ReportService;
import bts.common.report.vo.PnishVO;
import bts.common.report.vo.ReportVO;
import bts.f.p001_3.vo.F_P001_3VO_3;

@Controller("a_p002")
@RequestMapping(value="/admin/report")
public class A_P002ControllerImpl implements A_P002Controller{
	@Autowired
	A_P002Service a_p002Service;
	
	@Autowired
	ReportService repService;
	
	private static final String reviewName="review";
	private static final String commentName="comment";
	private static final String planName="plan";
	private static final String accompanyName="accompany";	
	
	@Autowired
	Provider<ReportVO> reportProvider;	
	
	@Override
	@RequestMapping(value="/pnish")
	public ModelAndView showPnishList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/a/p002/d001");
		return mav;
	}
	
	@RequestMapping(value="/pnish/search")
	@ResponseBody
	public Map<String, Object> selectPnishList(@RequestParam(value="p_name",required=false) String p_name,HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<PnishVO> data = a_p002Service.selectPnishList(p_name);		
		resultMap.put("Data", data);
		return resultMap;
	}

	@RequestMapping(value="/pnish/save")
	@ResponseBody
	public Map<String, Object> savePnishList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String[]> dataMap = new HashMap<String, String[]>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, String> result = new HashMap<String, String>();
		
		dataMap = request.getParameterMap();
		try {
			a_p002Service.savePnishList(dataMap);	
			result.put("Code","0");
			result.put("Message","저장되었습니다");
		}catch(Exception e) {
			result.put("Code","-1");
			result.put("Message","저장에 실패하였습니다");
			e.printStackTrace();
		}
		resultMap.put("Result", result);  
		return resultMap;
	}	

	@RequestMapping(value="/history")
	public ModelAndView showHistory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/a/p002/d002");
		List<String> pnish_combo = a_p002Service.selectPnishName();
		mav.addObject("pnish_combo",String.join("|", pnish_combo));
		System.out.println("===============>"+String.join("|", pnish_combo));
		return mav;
	}	
	
	@RequestMapping(value="/history/search")
	@ResponseBody
	public Map<String, Object> selectHistoryList(@RequestParam(value="p_id",required=false) String p_id,HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("p_id", p_id);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<A_P002VO_1> data = a_p002Service.selectHistoryList(searchMap);		
		resultMap.put("Data", data);
		return resultMap;
	}

	@RequestMapping(value="/history/save")
	@ResponseBody
	public Map<String, Object> saveHistoryList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String[]> dataMap = new HashMap<String, String[]>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, String> result = new HashMap<String, String>();
		
		dataMap = request.getParameterMap();
		try {
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("=================>"+mapper.writeValueAsString(dataMap));
			a_p002Service.saveHistoryList(dataMap);	
			result.put("Code","0");
			result.put("Message","저장되었습니다");
		}catch(Exception e) {
			result.put("Code","-1");
			result.put("Message","저장에 실패하였습니다");
			e.printStackTrace();
		}
		resultMap.put("Result", result);  
		return resultMap;
	}		
	
	@RequestMapping(value="/list")
	public ModelAndView showReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/a/p002/d003");
		return mav;
	}	
	
	@RequestMapping(value="/list/search")
	@ResponseBody
	public Map<String, Object> selectReportList(@RequestParam(value="p_title",required=false) String p_title,HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("p_title", p_title);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ReportVO> data = a_p002Service.selectReportList(searchMap);		
		resultMap.put("Data", data);
		return resultMap;
	}

	@RequestMapping(value="/list/contents")
	public ModelAndView showReportContents(@RequestParam(value="report_no",required=false) int report_no,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/a/p002/d004");
		ReportVO result = a_p002Service.selectReportContent(report_no);
		mav.addObject("detailInfo",result);
		if(result.getReport_at().equals("N")) {		
			List<PnishVO> pnish = repService.selectPnishList();
			mav.addObject("pnish",pnish);
		}else {
			A_P002VO_1 a_p002VO_1 = a_p002Service.selectReportResult(report_no);
			mav.addObject("pnishResult",a_p002VO_1);
		}
		return mav;
	}	

	@RequestMapping(value="/list/contents/target")
	public String showReportTarget(@RequestParam(value="report_se") String report_se,@RequestParam(value="contents_cd") String contents_cd
			,RedirectAttributes redirect, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="redirect:";
		String menu_name = a_p002Service.selectMenuName(report_se);
		if(menu_name.equals(reviewName)) {
			url+=makeReviewForm(redirect,contents_cd);
		}else if(menu_name.equals(accompanyName)){
			url+=makeAccForm(redirect,contents_cd);
		}else if(menu_name.equals(planName)){
			url+=makePlanForm(redirect,contents_cd);
		}else if(menu_name.equals(commentName)){	
			F_P001_3VO_3 ansResult = a_p002Service.selectAnswerInfo(contents_cd);
			String contents_name = ansResult.getMenu_name();
			if(contents_name.equals(reviewName)) {
				url+=makeReviewForm(redirect,Integer.toString(ansResult.getArticle_no()));
			}else if(contents_name.equals(planName)) {
				url+=makePlanForm(redirect,Integer.toString(ansResult.getArticle_no()));
			}else {
				url+="/error/404";							
			}
		}else {
			url+="/error/404";			
		}
		return url;
	}	

	@RequestMapping(value="/list/contents/reject")
	@ResponseBody
	public String insertReportReject (@ModelAttribute A_P002VO_1 a_p002VO_1,HttpServletRequest request, HttpServletResponse response) throws Exception {
		a_p002Service.updateReportEnd(a_p002VO_1.getReport_no());
		return "true";
	}	
	
	@RequestMapping(value="/list/contents/save")
	@ResponseBody
	public String insertPnishHistory (@ModelAttribute A_P002VO_1 a_p002VO_1,HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(a_p002VO_1.getPnish_type()==3) {
			Calendar cal = getMidnight();
			a_p002VO_1.setBegin_date(new Timestamp(cal.getTimeInMillis()));
			cal.set(9999, 11, 31);
			a_p002VO_1.setEnd_date(new Timestamp(cal.getTimeInMillis()));
		}else {
			int day_cnt = Integer.parseInt(a_p002VO_1.getDay_cnt());
			Calendar cal = getMidnight();
			a_p002VO_1.setBegin_date(new Timestamp(cal.getTimeInMillis()));
			cal.add(Calendar.DATE, day_cnt+1);
			a_p002VO_1.setEnd_date(new Timestamp(cal.getTimeInMillis()));
		}
		a_p002Service.insertPnishHistory(a_p002VO_1);
		a_p002Service.updateReportEnd(a_p002VO_1.getReport_no());
		return "true";
	}
	
	private Calendar getMidnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		return cal;
	}
	
	private String makeReviewForm(RedirectAttributes redirect,String article) {
		String url="/community/review/contents";
		redirect.addAttribute("article",article);
		return url;
	}

	private String makeAccForm(RedirectAttributes redirect,String article_no) {
		String url="/accompany/accView";
		String writer_id = a_p002Service.selectAccWriter(article_no);
		redirect.addAttribute("article_no",article_no);
		redirect.addAttribute("member_id",writer_id);
		return url;
	}
	
	private String makePlanForm(RedirectAttributes redirect,String plan_no) {
		String url="/community/plan_contents";
		redirect.addAttribute("plan_no",plan_no);
		return url;
	}	
}
