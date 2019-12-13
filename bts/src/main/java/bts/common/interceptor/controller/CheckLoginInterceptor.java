package bts.common.interceptor.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CheckLoginInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("isLogOn");
		Object obj2 = session.getAttribute("memberInfo");
		if(obj == null || obj2==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write("<script>alert('로그인 후 이용가능합니다.'); window.location.href='"+request.getContextPath()+"/main/main';</script>");
			return false;
		}
		return true;
	}
}
