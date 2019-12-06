package bts.a.p001.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface A_P001Controller {
	public ModelAndView inclnAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public Map<String, Object> searchIncln(@RequestParam("group_desc") String group_desc, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public Map saveIncln(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView updateIncln(HttpServletRequest request, HttpServletResponse response) throws Exception;
}

