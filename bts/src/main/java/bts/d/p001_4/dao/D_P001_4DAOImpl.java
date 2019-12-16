package bts.d.p001_4.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.d.p001_4.vo.D_P001_4VO;
import bts.d.p001_4.vo.D_P001_4VO_2;
import bts.d.p001_4.vo.D_P001_4VO_3;
import bts.d.p001_4.vo.D_P001_4VO_5;
import bts.e.p001.VO.PagingVO;

@Repository("d_p001_4DAO")
public class D_P001_4DAOImpl implements D_P001_4DAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<D_P001_4VO> searchArticle(PagingVO pagingVO, String category, String searchResult) throws DataAccessException {
		Map<String, Object> search = new HashMap<>();
		int start = pagingVO.getStart();
		int end = pagingVO.getEnd();
		search.put("start", start);
		search.put("end", end);
		search.put("category", category);
		search.put("searchResult", searchResult);
		return sqlSession.selectList("d.p001_4.searchArticle", search);
	}
	
	@Override
	public List<D_P001_4VO> contentsArticle(String plan_no) throws DataAccessException {
		return sqlSession.selectList("d.p001_4.contentsArticle", plan_no);
	}
	
	@Override
	public List<D_P001_4VO_2> detailPlanner(String plan_no) throws DataAccessException {
		return sqlSession.selectList("d.p001_4.plannerDetail", plan_no);
	}

	@Override
	public List<D_P001_4VO_3> searchTag(String plan_no) throws DataAccessException {
		return sqlSession.selectList("d.p001_4.searchTag", plan_no);
	}

	@Override
	public void insertContent(List<D_P001_4VO_2> voList) throws DataAccessException {
		sqlSession.insert("d.p001_4.insertDesc", voList);
		sqlSession.update("d.p001_4.updateOpen", voList);
		
	}

	@Override
	public List<D_P001_4VO> selectMyplan(String member_id) throws DataAccessException {
		return sqlSession.selectList("d.p001_4.selectMyplan", member_id);
	}

	@Override
	public void deletePlan(String plan_no) throws DataAccessException {
		sqlSession.delete("d.p001_4.deletePlan", plan_no);
	}

	@Override
	public void increaseCnt(String plan_no) throws DataAccessException {
		sqlSession.update("d.p001_4.viewCnt", plan_no);
		
	}

	@Override
	public void updateContent(List<D_P001_4VO_2> voList) throws DataAccessException {
		sqlSession.insert("d.p001_4.insertDesc", voList);
		sqlSession.update("d.p001_4.updateTitle", voList);
		
	}

	@Override
	public Integer pageCount(String category, String searchResult) throws DataAccessException {
		Map<String, String> result = new HashMap<>();
		result.put("category", category);
		result.put("searchResult", searchResult);
		return sqlSession.selectOne("d.p001_4.pageCount", result);
	}

	@Override
	public List<D_P001_4VO_5> selectAnswerList(Map<String, String> searchMap) throws DataAccessException {
		return sqlSession.selectList("d.p001_4.selectAnswerList", searchMap);
	}

	@Override
	public String selectArticleCd(String menuname) throws DataAccessException {
		return sqlSession.selectOne("d.p001_4.selectArticleCd", menuname);
	}

	@Override
	public String selectCommentTotal(Map<String, String> searchMap) throws DataAccessException {
		return sqlSession.selectOne("d.p001_4.selectCommentTotal", searchMap);
	}

	@Override
	public void insertAnswer(D_P001_4VO_5 d_p001_4VO_5) throws DataAccessException {
		sqlSession.insert("d.p001_4.insertAnswer", d_p001_4VO_5);
	}

	@Override
	public int deleteAnswer(String answer_no) throws DataAccessException {
		return sqlSession.delete("d.p001_4.deleteAnswer", answer_no);
	}

	@Override
	public List<String> findContentId() throws DataAccessException {
		return sqlSession.selectList("d.p001_4.findContentId");
	}

	@Override
	public List<D_P001_4VO> searchTitle(String searchResult) throws DataAccessException {
		System.out.println("dao : " + searchResult);
		return sqlSession.selectList("d.p001_4.searchTitle", searchResult);
		
	}
}
