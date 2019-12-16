package bts.h.p001.service;

import java.util.List;

import bts.e.p001.VO.PagingVO;
import bts.h.p001.vo.H_P001VO;

public interface H_P001Service {
	public List<H_P001VO> hotelList() throws Exception;
	public Integer hotelListCount();
	
	public List<H_P001VO> guestList();
	public Integer guestListCount();
	
	public List<H_P001VO> motelList();
	public Integer motelListCount();
	
	public H_P001VO hotelResult(String lodging_id) throws Exception;
	public List<H_P001VO> roomInfoResult(String lodging_id) throws Exception;
}
