package bts.h.p001.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import bts.h.p001.vo.H_P001VO;

public interface H_P001Controller {

	public ModelAndView pageInit( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
}
