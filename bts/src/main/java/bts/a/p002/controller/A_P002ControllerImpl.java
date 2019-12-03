package bts.a.p002.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bts.a.p002.service.A_P002Service;
import bts.common.report.vo.PnishVO;

@Controller("a_p002")
@RequestMapping(value="/admin/report")
public class A_P002ControllerImpl implements A_P002Controller{
	@Autowired
	A_P002Service a_p002Service;
	
	@Override
	@RequestMapping(value="/pnish")
	public ModelAndView adminReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	public Map<String, Object> savePnishList(@RequestParam Map<String,String[]> dataMap,HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, String> result = new HashMap<String, String>();
		try {
			System.out.println("=============>"+dataMap);
//			a_p002Service.savePnishList(dataMap);	
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
	
}
