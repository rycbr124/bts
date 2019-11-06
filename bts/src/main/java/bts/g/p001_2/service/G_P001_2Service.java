package bts.g.p001_2.service;

import java.util.List;
import java.util.Map;


public interface G_P001_2Service {
	public Map<String, List<String>> searchCategory() throws Exception;
	public Map<String, List<String>> courseCategory() throws Exception;
	public Map<String, List<String>> insertWishlist() throws Exception;
	
}
