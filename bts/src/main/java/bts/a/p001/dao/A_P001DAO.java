package bts.a.p001.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import bts.a.p001.vo.A_P001VO;

public interface A_P001DAO {
	public List<A_P001VO> searchIncln(Map<String, String> searchMap) throws DataAccessException;
	public void insertData(Map<String, String> row) throws DataAccessException;
	public void updateData(Map<String, String> row) throws DataAccessException;
	public void deleteData(Map<String, String> row) throws DataAccessException;
}
