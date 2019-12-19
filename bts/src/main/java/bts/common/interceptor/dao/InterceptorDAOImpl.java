package bts.common.interceptor.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.a.p002.vo.A_P002VO_1;

@Repository("interceptorDAO")
public class InterceptorDAOImpl implements InterceptorDAO{
	@Autowired
	private SqlSession sqlSession;
	

	@Override
	public String selectMenuCd(String menu_url) throws DataAccessException{
		String result = sqlSession.selectOne("mapper.role.selectMenuCd",menu_url);
		return result;
	}

	@Override
	public int selectAuthCount(Map<String, String> searchMap) throws DataAccessException{
		int result = sqlSession.selectOne("mapper.role.selectAuthCount",searchMap);
		return result;
	}

	@Override
	public A_P002VO_1 checkPnishAt(String member_id) {
		A_P002VO_1 result = sqlSession.selectOne("mapper.role.checkPnishAt",member_id);
		return result;
	}

}
