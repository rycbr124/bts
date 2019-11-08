package bts.g.p001_2.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.g.p001_2.vo.G_P001_2VO;


@Repository("g_p001_2DAO")
public class G_P001_2DAOImpl implements G_P001_2DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<String> searchUpperCategory() throws DataAccessException {
		List<String> upper_category = new ArrayList<>();
		upper_category = sqlSession.selectList("g.p001_2.selectUpperCategory");
		return upper_category;
	}
	
	@Override
	public List<String> searchCourseCategory() throws DataAccessException {
		List<String> course_category = new ArrayList<>();
		course_category = sqlSession.selectList("g.p001_2.selectCourseCategory");
		return course_category;
	}

	@Override
	public List<String> searchCategory() throws DataAccessException {
		List<String> category = new ArrayList<>();
		category = sqlSession.selectList("g.p001_2.selectCategory");
		return category;
	}
	
	@Override
	public boolean findWishlist(G_P001_2VO g_p001_2VO) throws DataAccessException {
		String result = sqlSession.selectOne("g.p001_2.findWishlist", g_p001_2VO);
		return Boolean.parseBoolean(result);
	}

	@Override
	public void insertWishlist(G_P001_2VO g_p001_2VO) throws DataAccessException {
		sqlSession.insert("g.p001_2.insertWishlist", g_p001_2VO);	
	}


	


}
