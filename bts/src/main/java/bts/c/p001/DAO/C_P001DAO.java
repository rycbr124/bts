package bts.c.p001.DAO;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;

import bts.b.p001.VO.B_P001VO;

public interface C_P001DAO {

	//회원정보
	//	B_P001VO selectMember(String memberId) throws DataAccessException;
	
	//회원정보 수정
		void updateMember(B_P001VO d001VO) throws DataAccessException;
	
	//프로필사진 수정
		void updateImage(B_P001VO d001VO) throws DataAccessException;

		//탈퇴
		String passCheck(String password)throws DataAccessException;

		void secession(B_P001VO d001vo, HttpSession session)throws DataAccessException;
	
	//회원탈퇴
//		String deleteMember(String memberId) throws DataAccessException;
		
	
}
