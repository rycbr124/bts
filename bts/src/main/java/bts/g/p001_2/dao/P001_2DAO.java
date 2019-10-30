package bts.g.p001_2.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;


public interface P001_2DAO {
	public List<String> searchUpperCategory(String input) throws DataAccessException;
	public List<String> searchCategory() throws DataAccessException;
}
