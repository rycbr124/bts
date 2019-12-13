package bts.b.p001.Controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;

public interface B_P001Controller {
	public String login(@RequestParam Map<String, String> loginMap,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView b_p001_d001(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ResponseEntity  addMember(@ModelAttribute("member") B_P001VO member,HttpServletRequest request, HttpServletResponse response) throws Exception;

}
