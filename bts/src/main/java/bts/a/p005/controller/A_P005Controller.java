package bts.a.p005.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface A_P005Controller {
	public ModelAndView memberAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public Map<String, Object> searchMember(@RequestParam("p_id") String p_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public Map saveMember(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView memberUpdate(HttpServletRequest request, HttpServletResponse response)throws Exception; 
}
