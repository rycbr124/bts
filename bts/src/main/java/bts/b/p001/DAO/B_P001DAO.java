package bts.b.p001.DAO;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import bts.b.p001.VO.B_P001VO;
import bts.b.p001.VO.KakaoVO;
import bts.b.p001.VO.NaverVO;

public interface B_P001DAO {
	public B_P001VO login(Map loginMap) throws DataAccessException;
	public void insertNewMember(B_P001VO d001VO) throws DataAccessException;
	public String selectOverlappedID(String id) throws DataAccessException;
	public void kakaoNewMember(B_P001VO kakaoVO) throws DataAccessException;
	public void naverNewMember(NaverVO naverVO) throws DataAccessException;
	public B_P001VO selectOverlappedEmail(Map emailMap) throws DataAccessException;
	public int check_id(String id) throws Exception;
	public int check_email(String email) throws Exception;
}
