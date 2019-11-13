package bts.b.p001.DAO;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.b.p001.VO.B_P001VO;
import bts.b.p001.VO.KakaoVO;
import bts.b.p001.VO.NaverVO;

@Repository("b_p001DAO")
public class B_P001DAOImpl implements B_P001DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertNewMember(B_P001VO d001vo) throws DataAccessException {
		sqlSession.insert("mapper.member.insertNewMember",d001vo);
		
	}

	@Override
	public String selectOverlappedID(String id) throws DataAccessException {
		String result = sqlSession.selectOne("mapper.member.selectOverlappedID",id);
		return result;
	}

	@Override
	public B_P001VO login(Map loginMap) throws DataAccessException {
		B_P001VO d001VO =(B_P001VO)sqlSession.selectOne("mapper.member.login",loginMap);
		return d001VO;
	}

	@Override
	public void kakaoNewMember(B_P001VO kakaoVO) throws DataAccessException {
		sqlSession.insert("mapper.member.kakaoInsert",kakaoVO);
	}

	@Override
	public void naverNewMember(NaverVO naverVO) throws DataAccessException {
		sqlSession.insert("mapper.member.naverInsert",naverVO);
		
	}

	@Override
	public B_P001VO selectOverlappedEmail(Map emailMap) throws DataAccessException {
		B_P001VO d001VO = (B_P001VO)sqlSession.selectOne("mapper.member.selectOverlappedEmail",emailMap);
		return d001VO;
	}

	@Override
	public int check_id(String id) throws Exception {
		return sqlSession.selectOne("mapper.member.check_id",id);
	}

	@Override
	public int check_email(String email) throws Exception {
		return sqlSession.selectOne("mapper.member.check_email",email);
	}


}
