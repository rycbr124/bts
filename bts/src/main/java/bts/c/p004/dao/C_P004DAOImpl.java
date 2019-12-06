package bts.c.p004.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import bts.c.p004.vo.C_P004VO;
import bts.e.p001.VO.PagingVO;

@Repository("c_p004DAO")
public class C_P004DAOImpl implements C_P004DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<C_P004VO> selectQuestion(String member_id,PagingVO pagingVO)throws DataAccessException{
		  Map<String,Object> param = new HashMap<>();
		  param.put("member_id", member_id);
		  param.put("start", pagingVO.getStart());
		  param.put("end", pagingVO.getEnd());
		return sqlSession.selectList("c.p004.selectQuestion",param);
	}
	@Override
	public Integer pageCount(String member_id)throws DataAccessException{
		return sqlSession.selectOne("c.p004.listCount",member_id);
	}
	@Override
	public List<C_P004VO> questionDetail(String contact_no)throws DataAccessException{
		return sqlSession.selectList("c.p004.questionDetail", contact_no);
		
	}
	@Override
	public void addQuestion(C_P004VO c_p004VO)throws DataAccessException{
		sqlSession.insert("c.p004.addQuestion",c_p004VO);
	}
	@Override
	public String questionSeq(C_P004VO c_p004VO)throws DataAccessException{
		String seq = sqlSession.selectOne("c.p004.questionSeq");
		
		return seq;
	}
	@Override
	public String answerDetail(String contact_no)throws DataAccessException{
		String detail = sqlSession.selectOne("c.p004.select_answer",contact_no);
		return detail;
	}
}
