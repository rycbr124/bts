package bts.common.interceptor.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.common.interceptor.dao.InterceptorDAO;

@Service("InterceptorService")
public class InterceptorServiceImpl implements InterceptorService{
	@Autowired
	InterceptorDAO interceptorDAO;
	
	@Override
	public boolean hasAuth(String member_id, String menu_url) throws Exception {
		String menu_cd = interceptorDAO.selectMenuCd(menu_url);
		if(menu_cd==null) {
			return false;
		}
		Map<String,String> searchMap = new HashMap<>();
		searchMap.put("member_id", member_id);
		searchMap.put("menu_cd", menu_cd);		
		int authCount = interceptorDAO.selectAuthCount(searchMap);
		if(authCount<=0) {
			return false;
		}
		return true;
	}

}
