package bts.h.p001.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import bts.e.p001.VO.PagingVO;
import bts.h.p001.vo.H_P001VO;

public interface H_P001DAO {
	public List<H_P001VO> hotelList(PagingVO pagingVO) throws DataAccessException;
	public Integer hotelListCount();
}
