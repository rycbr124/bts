package bts.z.p000.controller;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.Controller.B_P001ControllerImpl;
import bts.z.p000.service.Z_P000Service;


@Controller("z_p000")
@RequestMapping(value="/main")
public class Z_P000ControllerImpl implements Z_p000Controller{
	@Autowired
	Z_P000Service z_p000Service;
	
	@Override
	@RequestMapping(value="/main" , method= {RequestMethod.POST , RequestMethod.GET})
	public ModelAndView Z_P000_D001(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map<String,List<String>> mainAccompany = z_p000Service.mainAccompany();
		JSONObject bestAccompany = new JSONObject(mainAccompany);
		
		//Map<String, List<String>> searchIcon = z_p000Service.searchIcon();
		//JSONObject iconList = new JSONObject(searchIcon);
		
		ModelAndView mav = new ModelAndView("/z/p000/d001");
		mav.addObject("bestAccompany",bestAccompany);
		//mav.addObject("searchIcon", iconList);
		
		return mav;
	}		
}
