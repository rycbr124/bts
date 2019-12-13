package bts.c.p005.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import bts.g.p001_2.vo.G_P001_2VO;

public interface C_P005DAO {
	public List<G_P001_2VO> searchWishlist(String member_id) throws DataAccessException;

}
