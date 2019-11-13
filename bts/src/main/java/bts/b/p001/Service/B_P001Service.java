package bts.b.p001.Service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import bts.b.p001.VO.B_P001VO;
import bts.b.p001.VO.KakaoVO;
import bts.b.p001.VO.NaverVO;

public interface B_P001Service {
	public void addMember(B_P001VO d001VO) throws Exception;
	public String overlapped(String id) throws Exception;
	public B_P001VO login(Map  loginMap) throws Exception;
	public void kakaoInsert(B_P001VO kakaoVO) throws Exception;
	public void naverInsert(NaverVO naverVO) throws Exception;
	public B_P001VO overlappedEmail(Map emailMap) throws Exception;
	public void check_id(String id, HttpServletResponse response) throws Exception;
	public void check_email(String email, HttpServletResponse response) throws Exception;
	
}
