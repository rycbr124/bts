package bts.g.p001_2.service;

import java.util.List;
import java.util.Map;

import bts.g.p001_2.vo.G_P001_2VO;


public interface G_P001_2Service {
	public Map<String, List<String>> searchCategory() throws Exception;
	public Map<String, List<String>> courseCategory() throws Exception;
	public boolean findWishlist(G_P001_2VO g_p001_2VO) throws Exception;
	public void insertWishlist(G_P001_2VO g_p001_2VO) throws Exception;
}
