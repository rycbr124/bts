package bts.c.p004.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bts.c.p004.vo.C_P004VO;

@Repository("c_p004DAO")
public class C_P004DAOImpl implements C_P004DAO{
	@Autowired
	private SqlSession QuestionSession;
	
	@Override
	public void addQuestion(C_P004VO c_p004VO)throws Exception{
		QuestionSession.insert("c.p004.addQuestion",c_p004VO);
	}
	@Override
	public String questionSeq(C_P004VO c_p004VO)throws Exception{
		String seq = QuestionSession.selectOne("c.p004.questionSeq");
		
		return seq;
	}
}
