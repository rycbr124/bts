package bts.c.p003.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.c.p003.vo.C_P003VO;
import bts.e.p001.VO.PagingVO;

@Repository("c_p003DAO")
public class C_P003DAOImpl implements C_P003DAO{
	@Autowired
	SqlSession sqlSession;
	
	
	@Override
	public List<C_P003VO> myReservList(PagingVO pagingVO) throws DataAccessException {
		
		return sqlSession.selectList("mapper.myPage.selectMyReservation",pagingVO);
	}


	@Override
	public Integer myReservCount(String member_id) {
		
		return sqlSession.selectOne("mapper.myPage.resListCount",member_id);
	}


	@Override
	public void cancle(Map<String, Object> dataMap) {
		sqlSession.delete("mapper.myPage.cancle", dataMap);
		
	}

}
