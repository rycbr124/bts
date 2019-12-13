 package bts.z.p000.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		
		int length = accompany.size();
		System.out.println("1212 : " + length);
		System.out.println("3434 : " + accompany);
		
		Map newMap = new HashMap<>();
		for(int i = 0; i < length; i++) {
			newMap.put(i + "번째", accompany.get(i));
			System.out.println(newMap.get(i + "번째"));
		}
		
		return bestAccompany;
	}

	@Override
	public Map<String, List<String>> searchIcon() throws Exception {
		List<String> icon = new ArrayList<>();
		Map<String, List<String>> searchIcon = new HashMap<>();
		icon = z_p000DAO.searchIcon();
		searchIcon.put("searchIcon", icon);
		return searchIcon;
	}
}
