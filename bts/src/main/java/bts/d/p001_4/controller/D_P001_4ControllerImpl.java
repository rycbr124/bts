package bts.d.p001_4.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import bts.b.p001.VO.B_P001VO;
import bts.d.p001_4.service.D_P001_4Service;
import bts.d.p001_4.vo.D_P001_4VO;
import bts.d.p001_4.vo.D_P001_4VO_2;

@Controller("d_p001_4")
@RequestMapping(value="/community")
public class D_P001_4ControllerImpl implements D_P001_4Controller{
	
	@Autowired
	D_P001_4Service d_p001_4Service;
	
	@Autowired
	B_P001VO b_p001VO;
	
	@Autowired
	D_P001_4VO d_p001_4VO;
	
	@Autowired
	Provider<D_P001_4VO_2> d_p001_4VO_2;

	@Override
	@RequestMapping(value="/plan_list" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<D_P001_4VO> listArticle = d_p001_4Service.searchArticle();
		ModelAndView mav = new ModelAndView("/d/p001_4/d001");
		mav.addObject("listArticle", listArticle);
		return mav;
		
	}

	@Override
	@RequestMapping(value="/plan_contents" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView contentsArticle(@RequestParam String plan_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<D_P001_4VO> result = d_p001_4Service.contentsArticle(plan_no);
		List<D_P001_4VO_2> detailPlanner = d_p001_4Service.detailPlanner(plan_no);
		ModelAndView mav = new ModelAndView("/d/p001_4/d002");
		mav.addObject("result", result);
		mav.addObject("detailPlanner", detailPlanner);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/plan_write" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView writeArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO)session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		List<D_P001_4VO> myPlan = d_p001_4Service.selectMyplan(member_id);
		
		List<D_P001_4VO_2> detailPlanner = d_p001_4Service.detailPlanner("109");
		ModelAndView mav = new ModelAndView("/d/p001_4/d003");
		mav.addObject("detailPlanner", detailPlanner);
		mav.addObject("myPlan", myPlan);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/load_plan" ,method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String loadPlanner(@RequestParam("plan_no") String plan_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<D_P001_4VO_2> detailPlanner = d_p001_4Service.detailPlanner(plan_no);
		//List<D_P001_4VO_3> searchTag = d_p001_4Service.searchTag(plan_no);
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writeValueAsString(detailPlanner);
		//String jsonTag = mapper.writeValueAsString(searchTag);
		
		System.out.println("플랜넘버 : " + plan_no);
		System.out.println("결과값 : " + jsonResult);
		
		return jsonResult;
	}
	
	
	@Override
	@RequestMapping(value="/plan_save" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView saveArticle(@RequestParam Map<String, String> result, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String size = request.getParameter("length");
		int length = Integer.parseInt(size); 
			
		List<String> desc = new ArrayList<String>();
		List<String> id = new ArrayList<String>();
		List<D_P001_4VO_2> voList = new ArrayList<D_P001_4VO_2>();
		
		for(int i = 0; i < length; i++) {
			desc.add(i, result.get("content" + i));
			id.add(i, result.get("content_id" + i));
		};
		System.out.println("p_no : " + result.get("p_no"));
		for(int i = 0; i < desc.size(); i++) {
			D_P001_4VO_2 vo = d_p001_4VO_2.get(); 
			vo.setPlan_no(result.get("p_no"));
			vo.setContent_id(id.get(i));
			vo.setPlan_desc(desc.get(i));
			
			voList.add(i, vo);
		}
		d_p001_4Service.insertContent(voList);
		
		ModelAndView mav = new ModelAndView("/d/p001_4/d004");
		
		return mav;
	}


}
