package bts.d.p001_4.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import bts.d.p001_4.vo.D_P001_4VO;
import bts.d.p001_4.vo.D_P001_4VO_2;
import bts.d.p001_4.vo.D_P001_4VO_3;
import bts.d.p001_4.vo.D_P001_4VO_5;
import bts.e.p001.VO.PagingVO;

public interface D_P001_4DAO {
	public List<D_P001_4VO> searchArticle(PagingVO pagingVO, String category, String searchResult) throws DataAccessException;
	public List<D_P001_4VO> contentsArticle(String plan_no) throws DataAccessException;
	public List<D_P001_4VO_2> detailPlanner(String plan_no) throws DataAccessException;
	public List<D_P001_4VO_3> searchTag(String plan_no) throws DataAccessException;
	public void insertContent(List<D_P001_4VO_2> voList) throws DataAccessException;
	public List<D_P001_4VO> selectMyplan(String member_id) throws DataAccessException;
	public void deletePlan(String plan_no) throws DataAccessException;
	public void increaseCnt(String plan_no) throws DataAccessException;
	public void updateContent(List<D_P001_4VO_2> voList) throws DataAccessException;
	public Integer pageCount(String category, String searchResult) throws DataAccessException;
	public List<D_P001_4VO_5> selectAnswerList(Map<String,String> searchMap) throws DataAccessException;
	public String selectArticleCd(String menuname) throws DataAccessException;
	public String selectCommentTotal(Map<String, String> searchMap) throws DataAccessException;
	public void insertAnswer(D_P001_4VO_5 d_p001_4VO_5) throws DataAccessException;
	public int deleteAnswer(String answer_no) throws DataAccessException;
	public List<String> findContentId() throws DataAccessException;
	public List<D_P001_4VO> searchTitle(String searchResult) throws DataAccessException;
}
