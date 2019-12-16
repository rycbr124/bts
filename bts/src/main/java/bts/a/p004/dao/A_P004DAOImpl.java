package bts.a.p004.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.a.p004.vo.A_P004VO;
import bts.c.p004.vo.C_P004VO;

@Repository("a_p004DAO")
public class A_P004DAOImpl implements A_P004DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<C_P004VO> searchContact(Map<String, String> searchMap)throws DataAccessException{
		return sqlSession.selectList("a.p000.searchContact",searchMap);
	}
	@Override
	public List<C_P004VO> questionAnswer(String contact_no)throws DataAccessException{
		return sqlSession.selectList("a.p000.questionAnswer",contact_no);
	}
	@Override
	public void addAnswer(A_P004VO a_p004VO)throws DataAccessException{
		sqlSession.insert("a.p000.addAnswer",a_p004VO);
		sqlSession.update("a.p000.update_answer_at",a_p004VO);
	}
	@Override
	public List<A_P004VO> selectAnswer(String contact_no)throws DataAccessException{
		return sqlSession.selectList("a.p000.answerInfo",contact_no);
	}
}
