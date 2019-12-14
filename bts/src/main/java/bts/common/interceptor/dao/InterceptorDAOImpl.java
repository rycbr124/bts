package bts.common.interceptor.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

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

}
