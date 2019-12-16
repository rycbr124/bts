package bts.c.p007.service;

import java.util.List;

import bts.c.p007.VO.C_P007VO;
import bts.e.p001.VO.PagingVO;
import bts.e.p001.VO.PagingVO2;

public interface C_P007Service {
	public List<C_P007VO> myAccList(PagingVO pagingVO) throws Exception;
	public Integer listCount(String member_id) throws Exception;
	
	public List<C_P007VO> imAccList(PagingVO2 pagingVO2) throws Exception;
	public Integer imAccCount(String member_id) throws Exception;
}
