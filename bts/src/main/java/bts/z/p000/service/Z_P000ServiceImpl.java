package bts.z.p000.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.z.p000.dao.Z_P000DAO;

@Service("z_p000Service")
public class Z_P000ServiceImpl implements Z_P000Service{
	@Autowired
	Z_P000DAO z_p000DAO;
	
	@Override
	public Map<String, List<String>> mainAccompany()throws Exception{
		List<String> accompany = new ArrayList<>();
		Map<String,List<String>> bestAccompany = new HashMap<>();
		accompany = z_p000DAO.mainAccompany();
		bestAccompany.put("bestAccompany",accompany);
		return bestAccompany;
	}
}
