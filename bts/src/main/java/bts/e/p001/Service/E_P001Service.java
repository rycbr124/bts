package bts.e.p001.Service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import bts.e.p001.VO.E_P001VO;
import bts.e.p001.VO.PagingVO;


public interface E_P001Service {
	 public List selectAllEventList(PagingVO vo) throws DataAccessException;
	 public List<E_P001VO> selectAccompanyList(PagingVO pagingVO) throws DataAccessException;
	 public Integer listCount();
	 public E_P001VO accView(int article_no) throws Exception;
}
