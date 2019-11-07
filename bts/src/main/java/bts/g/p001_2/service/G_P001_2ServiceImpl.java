package bts.g.p001_2.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.g.p001_2.dao.G_P001_2DAO;
import bts.g.p001_2.vo.G_P001_2VO;

@Service("g_p001_2Service")
public class G_P001_2ServiceImpl implements G_P001_2Service{
	@Autowired
	G_P001_2DAO g_p001_2DAO;
	
	@Override
	public Map<String, List<String>> searchCategory() throws Exception {
		Map<String, List<String>> result = new HashMap<>(); 
		List<String> upperCategory = g_p001_2DAO.searchUpperCategory();
		List<String> category = g_p001_2DAO.searchCategory();
		
		result.put("upper", upperCategory);
		result.put("lower", category);
		
		return result;
	}

	@Override
	public Map<String, List<String>> courseCategory() throws Exception {
		Map<String, List<String>> result = new HashMap<>(); 
		
		List<String> courseCategory = g_p001_2DAO.searchCourseCategory();
		
		result.put("course", courseCategory);
		
		return result;
	}
	
	@Override
	public boolean findWishlist(G_P001_2VO g_p001_2VO) throws Exception {
		
		return g_p001_2DAO.findWishlist(g_p001_2VO);
	}

	@Override
	public void insertWishlist(G_P001_2VO g_p001_2VO) throws Exception {
		g_p001_2DAO.insertWishlist(g_p001_2VO);
			
	}

	


}
