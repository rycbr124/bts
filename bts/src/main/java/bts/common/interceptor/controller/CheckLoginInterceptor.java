package bts.common.interceptor.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import bts.a.p002.vo.A_P002VO_1;
import bts.b.p001.VO.B_P001VO;
import bts.common.interceptor.service.InterceptorService;

public class CheckLoginInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	InterceptorService InterceptorService;
	
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
		}else {
			B_P001VO member = (B_P001VO) obj2;
			String member_id = member.getMember_id();
			A_P002VO_1 result = InterceptorService.checkPnishAt(member_id);
			if(result!=null) {				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String end_date = format.format(result.getEnd_date());
				if(end_date.substring(0,end_date.indexOf("-")).equals("9999")) {
					end_date="영구 정지";
				}
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.write("<script>alert('제재중인 아이디입니다. \\n사유 : "+result.getPnish_desc()
						+"\\n정지기간 : "+format.format(result.getBegin_date())+" ~ "+end_date
						+"'); window.location.href='"+request.getContextPath()+"/main/main';</script>");
				return false;
			}
		}
		return true;
	}
}
