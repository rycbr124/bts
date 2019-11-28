package bts.a.p005.service;

import java.util.List;
import java.util.Map;

import bts.a.p005.vo.A_P005VO;

public interface A_P005Service {
	public List<A_P005VO> searchMember(Map<String, String> searchMap) throws Exception;

	public void saveData(Map<String, String[]> dataMap) throws Exception;

}
