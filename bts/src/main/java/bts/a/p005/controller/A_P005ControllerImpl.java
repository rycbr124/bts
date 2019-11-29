package bts.a.p005.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bts.a.p005.service.A_P005Service;
import bts.a.p005.vo.A_P005VO;

@Controller("a_p005")
@RequestMapping(value="/admin")
public class A_P005ControllerImpl implements A_P005Controller{
   @Autowired
   A_P005Service a_p005Service;
   
   @Autowired
   A_P005VO a_p005VO;

   @Override
   @RequestMapping(value="/mainMember" ,method={RequestMethod.POST,RequestMethod.GET})
   public ModelAndView memberAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ModelAndView mav = new ModelAndView("/a/p005/d001");
      return mav;
   }


   @Override
   @RequestMapping(value="/searchMember" ,method={RequestMethod.POST,RequestMethod.GET})
   @ResponseBody
   public Map<String, Object> searchMember(@RequestParam("p_id") String p_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      Map<String, String> searchMap = new HashMap<String, String>(); // 검색결과
      Map<String, Object> resultMap = new HashMap<String, Object>(); // 조회결과
      searchMap.put("member_id", p_id);
      
      List<A_P005VO> result = a_p005Service.searchMember(searchMap);
      
      resultMap.put("Data", result);
      
      return resultMap;
   }

   @Override
   @RequestMapping(value="/saveMember" ,method={RequestMethod.POST,RequestMethod.GET})
   public Map saveMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
         a_p005Service.saveData(dataMap);   
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
   @RequestMapping(value="/updateMember" ,method={RequestMethod.POST,RequestMethod.GET})
   public ModelAndView memberUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ModelAndView mav = new ModelAndView("/a/p005/d002");
      return mav;
   }

}


