package bts.c.p001.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import bts.b.p001.VO.B_P001VO;
import bts.c.p001.vo.C_P001VO;

public interface C_P001Service {
	public void updateMember(B_P001VO d001vo) throws Exception;
	
		//이미지 업로드
		public void updateimage(B_P001VO d001vo);
		
		//패스워드 체크
		public String passCheck(Map<String,String> searchData);
		
		//회원 탈퇴
		public void secession(B_P001VO d001vo,HttpSession session);
		
		public List<C_P001VO> selectInclnList();
}
