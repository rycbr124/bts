package bts.h.p001.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import bts.e.p001.VO.PagingVO;
import bts.h.p001.vo.H_P001VO;

public interface H_P001DAO {
	public List<H_P001VO> hotelList() throws DataAccessException;
	public Integer hotelListCount();
	
	public List<H_P001VO> guestList();
	public Integer guestListCount();
	
	public List<H_P001VO> motelList();
	public Integer motelListCount();
	
	public H_P001VO hotelResult(String lodging_id) throws DataAccessException;
	public List<H_P001VO> roomInfoResult(String lodging_id) throws DataAccessException;
	
}
