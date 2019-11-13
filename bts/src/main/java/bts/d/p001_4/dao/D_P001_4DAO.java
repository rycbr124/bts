package bts.d.p001_4.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import bts.d.p001_4.vo.D_P001_4VO;
import bts.d.p001_4.vo.D_P001_4VO_2;

public interface D_P001_4DAO {
	public List<D_P001_4VO> searchArticle() throws DataAccessException;
	public List<D_P001_4VO> contentsArticle(String article_no) throws DataAccessException;
	public List<D_P001_4VO_2> detailPlanner() throws DataAccessException;

}
