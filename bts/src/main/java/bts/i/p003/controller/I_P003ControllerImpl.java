package bts.i.p003.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import bts.b.p001.VO.B_P001VO;
import bts.i.p002.VO.I_P002VO_1;
import bts.i.p002.VO.I_P002VO_2;
import bts.i.p002.VO.I_P002VO_3;
import bts.i.p002.service.I_P002Service;

@Controller("i_p003")
@RequestMapping(value="/plan_detail")
public class I_P003ControllerImpl implements I_P003Controller{
	@Autowired
	B_P001VO b_p001VO;
	@Autowired
	I_P002VO_1 i_p002VO_1;
	@Autowired
	I_P002VO_2 i_p002VO_2;
	@Autowired
	I_P002VO_3 i_p002VO_3;
	@Autowired
	I_P002Service i_p002Service; 
	
	
	@Override
	@RequestMapping(value="/plan_detail", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView I_P003_D001(HttpServletRequest request, HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO)session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		i_p002VO_1.setMember_id(member_id);
		String plan_no = request.getParameter("plan_no");
		ModelAndView mav = new ModelAndView("/i/p003/d001");
		Map<String, List<String>> planRoot = i_p002Service.planRoot(plan_no);
		//VO러 플래너 받기
		i_p002VO_2.setPlan_no(plan_no);
		
		Map<String,List<String>> planner = i_p002Service.planner(plan_no);
		JSONObject rootObject = new JSONObject(planRoot);
		JSONObject plannerObj = new JSONObject(planner);
		mav.addObject("root", rootObject.toJSONString());
		mav.addObject("planner", plannerObj.toJSONString());
		mav.addObject("uri",request.getRequestURI());
		return mav;
		
		
	}
	@Override
	@RequestMapping(value="/plan_modify", method= {RequestMethod.POST,RequestMethod.GET})
	public void modify(HttpServletRequest request, HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO)session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		i_p002VO_1.setMember_id(member_id);
		String plan_no = request.getParameter("plan_no");
		i_p002VO_1.setPlan_no(plan_no);
		i_p002VO_2.setPlan_no(plan_no);
		i_p002VO_3.setPlan_no(plan_no);
		
		Map<String,List<String>> planner_info = i_p002Service.planner(plan_no);
		Map<String,List<String>> detail_info = i_p002Service.planRoot(plan_no);
		Map<String,List<String>> tagList = i_p002Service.tagList(plan_no);
		JSONObject planner_obj = new JSONObject(planner_info);
		JSONObject detail_obj = new JSONObject(detail_info);
		JSONObject tag_obj = new JSONObject(tagList);
		
		String planner = planner_obj.toJSONString();
		String detail = detail_obj.toJSONString();
		String tag = tag_obj.toJSONString();
		
		request.setAttribute("detailInfo", detail);
		request.setAttribute("plannerInfo", planner);
		request.setAttribute("tag_list", tag);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/plan/plan");
		dispatcher.forward(request, response);	
	}
	@Override
	@RequestMapping(value="/modify", method= {RequestMethod.POST,RequestMethod.GET})
	public void planModify(@RequestParam Map<String, String> result,HttpServletRequest request, HttpServletResponse response)throws Exception{

		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO) session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		i_p002VO_1.setMember_id(member_id);
		request.setCharacterEncoding("utf-8");
		String plan_no = request.getParameter("plan_no");
		ObjectMapper mapper = new ObjectMapper();
		String titleId = (String) result.get("title");
		String daterange =(String) result.get("daterange"); 
		String personnel = (String) result.get("personnel");
		System.out.println("CONTROLLER : "+ plan_no);
		String tag = (String) result.get("tag_value");
		String[] tagArray = tag.split(",");
		List<I_P002VO_3> voArray = new ArrayList<>();
		
		for(int i=0;i<tagArray.length;i++ ) {
			I_P002VO_3 i_p002VO_3 = new I_P002VO_3();
			i_p002VO_3.setTag_name(tagArray[i]);
			voArray.add(i, i_p002VO_3);
		}
		String contentid = result.get("detail_information");
		TreeMap<String,ArrayList<Integer>> contentArray = mapper.readValue(contentid, TreeMap.class);
		List<I_P002VO_2> contentVO = new ArrayList<>();
		for(Map.Entry<String, ArrayList<Integer>> entry : contentArray.entrySet()) {
			for(int j=0; j<entry.getValue().size();j++) {
				System.out.println(entry.getValue().getClass().getName());
				I_P002VO_2 i_p002VO_2 = new I_P002VO_2();
				i_p002VO_2.setDay_no(entry.getKey());
				String id = Integer.toString(entry.getValue().get(j));
				i_p002VO_2.setContent_id(id);
				contentVO.add(i_p002VO_2);
			}
		}
		i_p002VO_1.setTitle(titleId);
		i_p002VO_1.setPerson_se(personnel);
		i_p002VO_1.setRange_date(daterange);
		i_p002Service.delPlan(plan_no);
		i_p002Service.insertPlan(i_p002VO_1,contentVO,voArray);
		response.sendRedirect("/bts/planner/planner");
	}
	@Override
	@RequestMapping(value="/update_desc", method= {RequestMethod.POST, RequestMethod.GET})
	public void updateDesc(HttpServletRequest request, HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO) session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		String plan_no = request.getParameter("plan_no");
		String detail_no = request.getParameter("detail_no");
		String desc = request.getParameter("plan_desc");
	
		i_p002VO_1.setMember_id(member_id);
		i_p002VO_2.setDetail_no(detail_no);
		i_p002VO_2.setPlan_desc(desc);
		
		i_p002Service.updateDesc(i_p002VO_2);
		response.sendRedirect("/bts/plan_detail/plan_detail?plan_no="+plan_no);
	}
}
