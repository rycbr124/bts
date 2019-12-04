package bts.c.p005.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;
import bts.c.p005.service.C_P005Service;
import bts.g.p001_2.vo.G_P001_2VO;

@Controller("c_p005")
@RequestMapping(value="/my")
public class C_P005ControllerImpl implements C_P005Controller{
	@Autowired
    B_P001VO b_p001VO;
	
	@Autowired
	C_P005Service c_p005Service;
	
	@Override
	@RequestMapping(value="/wishList" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchWishlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String member_id = null;
		
		HttpSession session = request.getSession();
		b_p001VO = (B_P001VO)session.getAttribute("memberInfo");
		member_id = b_p001VO.getMember_id();
		System.out.println("111111 :" + member_id);
		
		
		List<G_P001_2VO> result = c_p005Service.searchWishlist(member_id);
		ModelAndView mav = new ModelAndView("/c/p005/d001");
		mav.addObject("result", result);
		
		return mav;
	}

}
