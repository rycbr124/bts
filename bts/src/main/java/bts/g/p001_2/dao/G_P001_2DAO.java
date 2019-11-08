package bts.g.p001_2.dao;

import java.util.List;


import org.springframework.dao.DataAccessException;

import bts.g.p001_2.vo.G_P001_2VO;

public interface G_P001_2DAO {
	public List<String> searchUpperCategory() throws DataAccessException;
	public List<String> searchCourseCategory() throws DataAccessException;
	public List<String> searchCategory() throws DataAccessException;
 
	public boolean findWishlist(G_P001_2VO g_p001_2VO) throws DataAccessException;
	public void insertWishlist(G_P001_2VO g_p001_2VO) throws DataAccessException;
}
