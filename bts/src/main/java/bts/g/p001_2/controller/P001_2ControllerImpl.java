package bts.g.p001_2.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bts.g.p001_2.service.P001_2Service;
import bts.g.p001_2.vo.P001_2VO;


@Controller("g_p001_2")
@RequestMapping(value="/recommend")
public class P001_2ControllerImpl implements P001_2Controller{
	@Autowired
	P001_2Service p001_2Service;
	
	@Autowired
	P001_2VO p001_2VO;

	@Override
	@RequestMapping(value="/recommend1" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView G_P001_D001(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");		
		Map<String, List<P001_2VO>> result = p001_2Service.searchCategory("place");
		List<P001_2VO> upperCategory = result.get("upper");
		List<P001_2VO> category = result.get("lower");
		
		for(int i = 0; i < upperCategory.size(); i++) {
			String upper_cd = upperCategory.get(i).getCategory_cd();
			String upper_name = upperCategory.get(i).getName();
			
			for(int j = 0; j < category.size(); j++) {
				String cd = category.get(j).getUpper_category_cd();
				String lower_cd = category.get(j).getCategory_cd();
				String name = category.get(j).getName();
				
			}
		}
		
		ModelAndView mav = new ModelAndView("/g/p001_2/d001");
		return mav;
	}

}
