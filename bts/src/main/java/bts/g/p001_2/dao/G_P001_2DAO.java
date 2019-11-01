package bts.g.p001_2.dao;

import java.util.List;


import org.springframework.dao.DataAccessException;


public interface G_P001_2DAO {
	public List<String> searchUpperCategory() throws DataAccessException;
	public List<String> searchCourseCategory() throws DataAccessException;
	public List<String> searchCategory() throws DataAccessException;
	
}
