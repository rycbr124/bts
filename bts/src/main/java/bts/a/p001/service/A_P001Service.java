package bts.a.p001.service;

import java.util.List;
import java.util.Map;

import bts.a.p001.vo.A_P001VO;

public interface A_P001Service {
	public List<A_P001VO> searchIncln(Map<String, String> searchMap) throws Exception;
	public void saveData(Map<String, String[]> dataMap) throws Exception;

}
