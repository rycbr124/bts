package bts.a.p001.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bts.a.p001.service.A_P001Service;
import bts.a.p001.vo.A_P001VO;

@Controller("a_p001")
@RequestMapping(value="/admin")
public class A_P001ControllerImpl implements A_P001Controller{
	@Autowired
	A_P001Service a_p001Service;
	
	
	@Override
	@RequestMapping(value="/inclnMain" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView inclnAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/a/p001/d001");
		return mav;
	}

	@Override
	@RequestMapping(value="/searchIncln" ,method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> searchIncln(String group_desc, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("group_desc", group_desc);
		List<A_P001VO> result = a_p001Service.searchIncln(searchMap);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("Data", result);
		
		return resultMap;
	}

	@Override
	@RequestMapping(value="/saveIncln" ,method={RequestMethod.POST,RequestMethod.GET})
	public Map saveIncln(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String[]> dataMap = new HashMap<String, String[]>(); // 저장 할 Data
		Map<String, Object> resultMap = new HashMap<String, Object>(); // 처리결과
		
		// 저장 Data 추출하기
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String[] values = request.getParameterValues(name );
			dataMap.put(name, values);
		}
		
		Map<String, String> result = new HashMap<String, String>();
		try {
			a_p001Service.saveData(dataMap);	
			result.put("Code","0");
			result.put("Message","저장되었습니다.");
		}catch(Exception e) {
			result.put("Code","-1");
			result.put("Message","저장에 실패하였습니다.");
			e.printStackTrace();
		}
		
		resultMap.put("Result", result);         
        return resultMap;
	}

	@Override
	@RequestMapping(value="/updateIncln" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView updateIncln(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/a/p001/d002");
		return mav;
	}
	
	@RequestMapping(value="/test" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/a/p001/d003");
		return mav;
	}

}
