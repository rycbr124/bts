package bts.a.p002.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import bts.a.p002.service.A_P002Service;
import bts.common.report.vo.PnishVO;
import bts.common.report.vo.ReportVO;
import bts.f.p001_3.vo.F_P001_3VO;

@Controller("a_p002")
@RequestMapping(value="/admin/report")
public class A_P002ControllerImpl implements A_P002Controller{
	@Autowired
	A_P002Service a_p002Service;
	
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
		return mav;
	}	
	
	@RequestMapping(value="/list")
	public ModelAndView showReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/a/p002/d003");
		return mav;
	}	

	@RequestMapping(value="/list/contents")
	public ModelAndView showReportContents(@RequestParam(value="report_no",required=false) int report_no,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/a/p002/d004");
		System.out.println("===================>"+report_no);
		ReportVO result = a_p002Service.selectReportContent(report_no);
		mav.addObject("detailInfo",result);
		return mav;
	}	

	@RequestMapping(value="/list/contents/target")
	@ResponseBody
	public String showReportTarget(@RequestParam(value="report_se") String report_se,@RequestParam(value="contents_cd") String contents_cd
			,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String menu_name = a_p002Service.selectMenuName(report_se);
		if(menu_name.equals(reviewName)) {
			return request.getContextPath()+"/community/plan_contents";
		}else if(menu_name.equals(accompanyName)){
			return request.getContextPath()+"/accompany/accView";
		}else if(menu_name.equals(planName)){
			return request.getContextPath()+"/community/review/contents";			
		}else if(menu_name.equals(commentName)){	
			//String contents_name = a_p002Service.selectAnswerInfo(contents_cd);		
		}
		return "/error/404";
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
}
