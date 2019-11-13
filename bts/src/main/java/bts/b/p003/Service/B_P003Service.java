package bts.b.p003.Service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import bts.b.p001.VO.B_P001VO;


public interface B_P003Service {
	public String find_id(HttpServletResponse response, String email) throws Exception;
	public void find_pw(HttpServletResponse response, B_P001VO d001vo) throws Exception;
	public void send_mail(B_P001VO p001VO, String div) throws Exception;

}
