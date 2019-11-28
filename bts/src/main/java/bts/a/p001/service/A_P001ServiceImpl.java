package bts.a.p001.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.a.p001.dao.A_P001DAO;
import bts.a.p001.vo.A_P001VO;

@Service("a_p001Service")
public class A_P001ServiceImpl implements A_P001Service{
	@Autowired
	A_P001DAO a_p001DAO;

	@Override
	public List<A_P001VO> searchIncln(Map<String, String> searchMap) throws Exception {
		return a_p001DAO.searchIncln(searchMap);
	}

	@Override
	public void saveData(Map<String, String[]> dataMap) throws Exception {
		String[] status = dataMap.get("STATUS");
		int length = status.length; // row수
		int i = 0;
		
		for(String str : status) {
			Map<String, String> row = getRow(dataMap, length, i); //현재 Index의 Row Map
			if("I".equals(str)) { // 추가
				a_p001DAO.insertData(row);
			}else if("U".equals(str)) { // 수정
				a_p001DAO.updateData(row);
			}else if("D".equals(str)) { // 삭제
				a_p001DAO.deleteData(row);
			}
			i++;
		}
		
	}
	
	private Map getRow(Map<String, String[]> dataMap, int length, int index) {
		Map<String, String> row = new HashMap<String, String>();
		for(String name : dataMap.keySet()) {
			String[] data = dataMap.get(name);
			if(length == data.length) {
				row.put(name, data[index]);
			}
		}		
		return row;
	}
	

}
