package bts.i.p003.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface I_P003Controller {
	public ModelAndView I_P003_D001(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public void modify(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public void planModify(Map<String,String> result,HttpServletRequest request, HttpServletResponse response)throws Exception;
	public void updateDesc(HttpServletRequest request,HttpServletResponse response)throws Exception;
}
