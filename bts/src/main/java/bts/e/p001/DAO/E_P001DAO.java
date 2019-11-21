package bts.e.p001.DAO;

import java.util.List;

import org.springframework.dao.DataAccessException;

import bts.e.p001.VO.E_P001VO;
import bts.e.p001.VO.PagingVO;

public interface E_P001DAO {
	public List<E_P001VO> selectAccompanyList(PagingVO pagingVO) throws DataAccessException;
	public Integer pageCount();
}
