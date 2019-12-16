package bts.e.p001.DAO;

import java.util.List;

import org.springframework.dao.DataAccessException;

import bts.c.p001.vo.C_P001VO;
import bts.e.p001.VO.E_P001VO;
import bts.e.p001.VO.PagingVO;
import bts.e.p003.VO.E_P003VO_2;

public interface E_P001DAO {
	public List<E_P001VO> selectAccompanyList(PagingVO pagingVO, String category, String searchResult) throws DataAccessException;
	public Integer pageCount();
	public void updateViewcnt(int article_no) throws Exception;
	public E_P001VO accView(int article_no) throws Exception;
	public List<C_P001VO> inclnView(String member_id) throws Exception;
	public List<E_P003VO_2> selectTag(int article_no) throws Exception;
}
