package bts.c.p003.service;

import java.util.List;
import java.util.Map;

import bts.c.p003.vo.C_P003VO;
import bts.e.p001.VO.PagingVO;

public interface C_P003Service {
	public List<C_P003VO> myReservList(PagingVO pagingVO) throws Exception;
	public Integer myReservCount(String member_id);
	public void cancle(Map<String, Object> dataMap);
}
