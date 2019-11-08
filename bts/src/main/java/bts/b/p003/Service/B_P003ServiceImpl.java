package bts.b.p003.Service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.b.p003.DAO.B_P003DAO;

@Service("b_p003Service")
public class B_P003ServiceImpl implements B_P003Service{

	@Autowired
	B_P003DAO p003DAO;
	
	@Override
	public String find_id(HttpServletResponse response, String email) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = p003DAO.find_id(email);
		if(id == null) {
			out.print("<script>");
			out.print("alert('가입된 아이디가 없습니다.');");
			out.print("history.go(-1);");
			out.print("</script>");
			out.close();
			return null;
		}else {
			out.print("<script>");
			out.print("alert('BTS : ID는 "+id+" 입니다.');");
			out.print("history.go(-1);");
			out.print("</script>");
			out.close();
			return id;
		}
		
	}

}
