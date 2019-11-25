package bts.a.p005.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import bts.a.p005.vo.A_P005VO;

public interface A_P005DAO {
	public List<A_P005VO> searchMember(Map<String, String> searchMap) throws DataAccessException;

	public void insertData(Map<String, String> row);

	public void updateData(Map<String, String> row);

	public void deleteData(Map<String, String> row);
}
