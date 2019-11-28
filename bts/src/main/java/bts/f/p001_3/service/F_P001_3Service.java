package bts.f.p001_3.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import bts.f.p001_3.vo.F_P001_3VO;
import bts.f.p001_3.vo.F_P001_3VO_2;
import bts.f.p001_3.vo.F_P001_3VO_3;

public interface F_P001_3Service {
	public void insertArticle(F_P001_3VO f_p001_3VO) throws DataAccessException;
	public void insertTagList(List<F_P001_3VO_2> list) throws DataAccessException;
	public void insertAnswer(F_P001_3VO_3 f_p001_3VO_3) throws DataAccessException;
	public int deleteAnswer(String answer_no) throws DataAccessException;
	public void updateArticle(F_P001_3VO vo) throws DataAccessException;
	public void updateTagList(List<F_P001_3VO_2> updateTagList,F_P001_3VO f_p001_3VO) throws DataAccessException;
	public String selectReviewTotal(String article_cd) throws DataAccessException;
	public String selectCommentTotal(Map<String,String> searchMap) throws DataAccessException;
	public List<F_P001_3VO> selectReviewList(Map<String,String> searchMap) throws DataAccessException;
	public F_P001_3VO selectReviewContents(Map<String,String> searchMap) throws DataAccessException;
	public List<F_P001_3VO_3> selectAnswerList(Map<String,String> searchMap) throws DataAccessException;
	public String selectArticleCd(String menu);
}
