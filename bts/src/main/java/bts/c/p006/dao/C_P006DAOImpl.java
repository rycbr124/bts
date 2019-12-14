package bts.c.p006.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.b.p001.VO.B_P001VO;
import bts.c.p006.vo.C_P006VO;

@Repository("c_p006DAO")
public class C_P006DAOImpl implements C_P006DAO{
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<B_P001VO> selectMemberList(Map<String,String> searchMap) throws DataAccessException {
		List<B_P001VO> list=sqlSession.selectList("mapper.message.selectMemberList", searchMap);
		return list;
	}
	
	@Override
	public List<C_P006VO> selectMessageList(C_P006VO c_p006VO) throws DataAccessException{
		List<C_P006VO> list = sqlSession.selectList("mapper.message.selectMessageList", c_p006VO);
		return list;	
	}

	@Override
	public void insertMessage(C_P006VO c_p006VO) throws DataAccessException{
		sqlSession.insert("mapper.message.insertMessage",c_p006VO);
	}
	
	@Override
	public List<B_P001VO> selectSearchList(Map<String,String> searchMap) throws DataAccessException{
		List<B_P001VO> list=sqlSession.selectList("mapper.message.selectSearchList", searchMap);
		return list;
	}
}
	