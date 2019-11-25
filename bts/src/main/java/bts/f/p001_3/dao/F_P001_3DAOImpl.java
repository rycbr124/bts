package bts.f.p001_3.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.f.p001_3.vo.F_P001_3VO;
import bts.f.p001_3.vo.F_P001_3VO_2;
import bts.f.p001_3.vo.F_P001_3VO_3;

@Repository("f_p001_3DAO")
public class F_P001_3DAOImpl implements F_P001_3DAO{
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void insertArticle(F_P001_3VO f_p001_3VO) throws DataAccessException{
		sqlSession.insert("mapper.f_p001_3.insertArticle",f_p001_3VO);
	}
	
	@Override
	public void insertTagList(List<F_P001_3VO_2> list) throws DataAccessException{
		sqlSession.insert("mapper.f_p001_3.insertTagList",list);
	}

	@Override
	public void insertAnswer(F_P001_3VO_3 f_p001_3VO_3) throws DataAccessException{
		sqlSession.insert("mapper.f_p001_3.insertAnswer",f_p001_3VO_3);
	}	
	
	@Override
	public String selectReviewTotal() throws DataAccessException{
		String totalCount = sqlSession.selectOne("mapper.f_p001_3.selectReviewTotal");
		return totalCount;
	}

	@Override
	public String selectCommentTotal(String article_no) throws DataAccessException{
		String totalCount = sqlSession.selectOne("mapper.f_p001_3.selectCommentTotal",article_no);
		return totalCount;
	}	
	
	@Override
	public List<F_P001_3VO> selectReviewList(Map<String,Integer> searchMap) throws DataAccessException{
		List<F_P001_3VO> list = sqlSession.selectList("mapper.f_p001_3.selectReviewList",searchMap);
		return list;
	}
	
	@Override
	public List<String> selectTagList(F_P001_3VO f_p001_3VO) throws DataAccessException{
		List<String> list = sqlSession.selectList("mapper.f_p001_3.selectTagList",f_p001_3VO);
		return list;
	}
	
	@Override
	public F_P001_3VO selectReviewContents(Map<String,String> searchMap) throws DataAccessException{
		F_P001_3VO f_p001_3VO = sqlSession.selectOne("mapper.f_p001_3.selectReviewContents",searchMap);
		return f_p001_3VO;
	}
	
	@Override
	public List<F_P001_3VO_3> selectAnswerList(Map<String,String> searchMap) throws DataAccessException{
		List<F_P001_3VO_3> list = sqlSession.selectList("mapper.f_p001_3.selectAnswerList",searchMap);
		return list;
	}	
}
