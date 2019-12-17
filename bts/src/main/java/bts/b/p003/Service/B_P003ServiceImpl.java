package bts.b.p003.Service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.b.p001.DAO.B_P001DAO;
import bts.b.p001.Service.B_P001Service;
import bts.b.p001.VO.B_P001VO;
import bts.b.p003.DAO.B_P003DAO;

@Service("b_p003Service")
public class B_P003ServiceImpl implements B_P003Service {

	@Autowired
	B_P003DAO p003DAO;
	@Autowired
	B_P001Service p001Service;
	@Autowired
	B_P001DAO p001DAO;

	@Override
	public String find_id(HttpServletResponse response, String email) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = p003DAO.find_id(email);
		
		if (id == null) {
			out.print("<script>");
			out.print("alert('가입된 아이디가 없습니다.');");
			out.print("history.go(-1);");
			out.print("</script>");
			out.close();
			return null;
		} else {
			out.print("<script>");
			out.print("alert('BTS : ID는 " + id + " 입니다.');");
			out.print("history.go(-1);");
			out.print("</script>");
			out.close();
			return id;
		}
	
	}

	@Override
	public void find_pw(HttpServletResponse response, B_P001VO d001vo) throws Exception {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		if (p001DAO.check_id(d001vo.getMember_id()) == 0) {
			out.print("<script>");
			out.print("alert('아이디를 확인하세요');");
			out.print("history.go(-1)");
			out.print("</script>");
			out.close();
		} else if (p001DAO.check_email(d001vo.getEmail()) == 0) {
			out.print("<script>");
			out.print("alert('이메일를 확인하세요');");
			out.print("history.go(-1)");
			out.print("</script>");
			out.close();
		} else {
			String pw = "";
			for (int i = 0; i < 10; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			d001vo.setPassword(pw);
			p003DAO.update_pw(d001vo);
			send_mail(d001vo, "find_pw");
			out.print("<script>");
			out.print("alert('이메일로 임시 비밀번호를 발송했습니다.');");
			out.print("history.go(-1)");
			out.print("</script>");
			out.close();
		}

	}

	@Override
	public void send_mail(B_P001VO p001vo, String div) throws Exception {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com";
		String hostSMTPid = "florida223@naver.com";
		String hostSMTPpwd = "ijhwngml1231!";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "florida223@naver.com";
		String fromName = "BestTravelSeoul";
		String subject = "";
		String msg = "";

		if (div.equals("find_pw")) {
			subject = "BTS 임시 비밀번호 입니다.";
			msg += "<body style='border:1px solid #EAEAEA;'>";
			msg += "<div style='background-color:#EAEAEA;padding-top:50px; width:500px; margin-left:500px; height:500px;font-family: '돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;'>";
			msg += "<img src='http://localhost:8088/bts/resources/image/BTS_logo_black.png' width='200' height='100px' style='margin-left:190px;'><br>";
			msg += "<h1 style='text-align:center;'>Best Travel Seoul</h1><br><br>";
			msg += "<div style='padding-left:10px;padding-bottom:10px;'>";
			msg += "<h2 style='text-align:center;'>BTS에서 보낸 메일입니다.</h2><br><br>";
			msg += "<p style='text-align:center;'> 회원님의 임시 비밀번호를 발송했습니다.</p>";
			msg += "<p style='text-align:center;'>"+p001vo.getMember_id() +"님의 임시 비밀번호는 : </p><div style='text-align:center;'><span style='color:#F15F5F;'>" 
			+ p001vo.getPassword()+"</span><span> 입니다.</span></div>";
			msg += "<p style='text-align:center;'> 마이페이지에서 비밀번호를 변경해주세요.</p>";
			msg += "</div></div></body>";
		}
		// 받는 사람 E-Mail 주소
		String mail = p001vo.getEmail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587);

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}

	}

}
