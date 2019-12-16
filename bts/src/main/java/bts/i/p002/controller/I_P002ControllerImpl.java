package bts.i.p002.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import bts.b.p001.VO.B_P001VO;
import bts.g.p001_2.vo.G_P001_2VO;
import bts.i.p002.VO.I_P002VO_1;
import bts.i.p002.VO.I_P002VO_2;
import bts.i.p002.VO.I_P002VO_3;
import bts.i.p002.service.I_P002Service;

@Controller("i_p002")
@RequestMapping(value = "/plan")
public class I_P002ControllerImpl implements I_P002Controller {
	@Autowired
	B_P001VO b_p001VO;
	@Autowired
	I_P002VO_1 i_p002VO_1;
	@Autowired
	I_P002VO_2 i_p002VO_2;
	@Autowired
	I_P002VO_3 i_p002VO_3;
	@Autowired
	G_P001_2VO g_p001_2VO; 
	@Autowired
	I_P002Service i_p002Service;

	@Override
	@RequestMapping(value = "/plan", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView I_P002_D001(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO) session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		i_p002VO_1.setMember_id(member_id);
		ModelAndView mav = new ModelAndView("/i/p002/d001");
		mav.addObject("uri",request.getRequestURI());
		return mav;
	}

	
	@Override
	@InitBinder
	@RequestMapping(value = "/insert_plan", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody void planInsert(@RequestParam Map<String, String> result,HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO) session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		i_p002VO_1.setMember_id(member_id);
		request.setCharacterEncoding("utf-8");
		
		ObjectMapper mapper = new ObjectMapper();
		String titleId = (String) result.get("title");
		String daterange =(String) result.get("daterange"); 
		String personnel = (String) result.get("personnel");
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
		i_p002Service.insertPlan(i_p002VO_1,contentVO,voArray);
		response.sendRedirect("/bts/planner/planner");
	}
	@Override
	@RequestMapping(value="/select_wishList",method= {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String selectWishList(HttpServletRequest request, HttpServletResponse response)throws Exception{
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO)session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		g_p001_2VO.setMember_id(member_id);
		i_p002Service.wishList(member_id);
		Map<String,List<String>> wishList = i_p002Service.wishList(member_id);
		JSONObject wishListObj = new JSONObject(wishList);
		String wish = wishListObj.toJSONString();
		System.out.println(wish);
		return wish;
	}
}
			