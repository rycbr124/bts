package bts.g.p001_2.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.g.p001_2.dao.P001_2DAO;

@Service("p001_2Service")
public class P001_2ServiceImpl implements P001_2Service{
	@Autowired
	P001_2DAO p001_2DAO;
	
	@Override
	public Map<String, List<String>> searchCategory(String input) throws Exception {
		Map<String, List<String>> result = new HashMap<>(); 
		List<String> upperCategory = p001_2DAO.searchUpperCategory(input);
		List<String> category = p001_2DAO.searchCategory();
		
		result.put("upper", upperCategory);
		result.put("lower", category);
		
		return result;
	}

}
