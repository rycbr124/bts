package bts.g.p001_2.service;

import java.util.List;
import java.util.Map;

import bts.g.p001_2.vo.P001_2VO;

public interface P001_2Service {
	public Map<String, List<P001_2VO>> searchCategory(String input) throws Exception;
}
