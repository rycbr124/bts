package bts.b.p001.Service;

import java.util.Map;

import bts.b.p001.VO.B_P001VO;

public interface B_P001Service {
	public void addMember(B_P001VO d001VO) throws Exception;
	public String overlapped(String id) throws Exception;
	public B_P001VO login(Map  loginMap) throws Exception;
}
