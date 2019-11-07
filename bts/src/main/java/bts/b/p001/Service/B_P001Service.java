package bts.b.p001.Service;

import java.util.HashMap;
import java.util.Map;

import bts.b.p001.VO.B_P001VO;
import bts.b.p001.VO.KakaoVO;
import bts.b.p001.VO.NaverVO;

public interface B_P001Service {
	public void addMember(B_P001VO d001VO) throws Exception;
	public String overlapped(String id) throws Exception;
	public B_P001VO login(Map  loginMap) throws Exception;
	public void kakaoInsert(KakaoVO kakaoVO) throws Exception;
	public void naverInsert(NaverVO naverVO) throws Exception;
}
