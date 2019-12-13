package bts.common.interceptor.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import bts.b.p001.VO.B_P001VO;
import bts.common.interceptor.service.InterceptorService;

public class CheckRoleInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	InterceptorService InterceptorService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		HttpSession session = request.getSession();
		B_P001VO b_p001VO = (B_P001VO) session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		String menu_url = request.getRequestURI().substring(request.getContextPath().length());
		boolean result = InterceptorService.hasAuth(member_id,menu_url);
		if(!result) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write("<script>alert('접근 권한이 없습니다.'); window.location.href='"+request.getContextPath()+"/main/main';</script>");			
		}
		return result;
	}
	
}
