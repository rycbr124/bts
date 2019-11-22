package bts.f.p001_3.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import bts.f.p001_3.vo.F_P001_3VO;
import bts.f.p001_3.vo.F_P001_3VO_2;

public interface F_P001_3DAO {
	public void insertArticle(F_P001_3VO f_p001_3VO) throws DataAccessException;
	public void insertTagList(List<F_P001_3VO_2> list) throws DataAccessException;
	public String selectReviewTotal() throws DataAccessException;
	public List<F_P001_3VO> selectReviewList(Map<String,Integer> searchMap) throws DataAccessException;
	public List<String> selectTagList(F_P001_3VO f_p001_3VO) throws DataAccessException;
	public F_P001_3VO selectReviewContents(Map<String,String> searchMap) throws DataAccessException;
}
