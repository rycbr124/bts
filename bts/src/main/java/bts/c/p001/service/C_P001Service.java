package bts.c.p001.service;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import bts.b.p001.VO.B_P001VO;

public interface C_P001Service {
	public void updateMember(B_P001VO d001vo) throws Exception;
	
		//이미지 업로드
		public void updateimage(B_P001VO d001vo);
		
		//패스워드 체크
		public String passCheck(String password);
		
		//회원 탈퇴
		public void secession(B_P001VO d001vo,HttpSession session);
		
		
}
