package bts.c.p002.Service;

import java.util.List;

import bts.c.p007.VO.C_P007VO;
import bts.e.p001.VO.PagingVO;
import bts.e.p001.VO.PagingVO2;
import bts.e.p001.VO.PagingVO3;
import bts.f.p001_3.vo.F_P001_3VO;
import bts.i.p002.VO.I_P002VO_1;

public interface C_P002Service {
	public List<F_P001_3VO> reviewList(PagingVO pagingVO) throws Exception;
	public Integer reviewListCount(String member_id) throws Exception;
	
	public List<I_P002VO_1> planList(PagingVO3 pagingVO3) throws Exception;
	public Integer planListCount(String member_id) throws Exception;
	
	public List<C_P007VO> accList(PagingVO2 pagingVO2) throws Exception;
	public Integer accListCount(String member_id) throws Exception;
}
