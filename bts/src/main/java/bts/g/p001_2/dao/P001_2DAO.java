package bts.g.p001_2.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import bts.g.p001_2.vo.P001_2VO;

public interface P001_2DAO {
	public List<P001_2VO> searchUpperCategory(String input) throws DataAccessException;
	public List<P001_2VO> searchCategory() throws DataAccessException;
}
