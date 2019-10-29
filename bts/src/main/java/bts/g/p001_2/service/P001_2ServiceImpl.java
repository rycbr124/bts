package bts.g.p001_2.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.g.p001_2.dao.P001_2DAO;
import bts.g.p001_2.vo.P001_2VO;

@Service("p001_2Service")
public class P001_2ServiceImpl implements P001_2Service{
	@Autowired
	P001_2DAO p001_2DAO;
	
	@Override
	public Map<String, List<P001_2VO>> searchCategory(String input) throws Exception {
		Map<String, List<P001_2VO>> result = new HashMap<>(); 
		List<P001_2VO> upperCategory = p001_2DAO.searchUpperCategory(input);
		List<P001_2VO> category = p001_2DAO.searchCategory();
		
		result.put("upper", upperCategory);
		result.put("lower", category);
		
		return result;
	}

}
