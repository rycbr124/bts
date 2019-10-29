package bts.g.p001_2.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.g.p001_2.vo.P001_2VO;

@Repository("p001_2DAO")
public class P001_2DAOImpl implements P001_2DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<P001_2VO> searchUpperCategory(String input) throws DataAccessException {
		List<P001_2VO> upper_category = new ArrayList<>();
		upper_category = sqlSession.selectList("g.p001_2.selectUpperCategory", input);
		return upper_category;
	}

	@Override
	public List<P001_2VO> searchCategory() throws DataAccessException {
		List<P001_2VO> category = new ArrayList<>();
		category = sqlSession.selectList("g.p001_2.selectCategory");
		return category;
	}
	


}
