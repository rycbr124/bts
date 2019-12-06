package bts.h.p001.service;

import java.util.List;

import bts.e.p001.VO.PagingVO;
import bts.h.p001.vo.H_P001VO;

public interface H_P001Service {
	public List<H_P001VO> hotelList(PagingVO pagingVO) throws Exception;
	public Integer hotelListCount();
}
