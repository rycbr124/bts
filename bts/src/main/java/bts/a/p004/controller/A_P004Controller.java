package bts.a.p004.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bts.c.p004.vo.C_P004VO;

public interface A_P004Controller {
	public ModelAndView question(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public Map<String, Object> searchContact(@RequestParam("p_id") String p_id,HttpServletRequest request,HttpServletResponse response)throws Exception;
	public Map<String, Object> questionAnswer(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public void addAnswer(HttpServletRequest request, HttpServletResponse response)throws Exception;
}
