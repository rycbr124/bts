package bts.c.p007.DAO;

import java.util.List;

import org.springframework.dao.DataAccessException;

import bts.c.p007.VO.C_P007VO;
import bts.e.p001.VO.PagingVO;

public interface C_P007DAO {
	public List<C_P007VO> myAccList(PagingVO pagingVO) throws DataAccessException;
	public Integer listCount(String member_id);
}
