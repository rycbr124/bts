package bts.g.p001_2.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


@Repository("g_p001_2DAO")
public class G_P001_2DAOImpl implements G_P001_2DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<String> searchUpperCategory(String input) throws DataAccessException {
		List<String> upper_category = new ArrayList<>();
		upper_category = sqlSession.selectList("g.p001_2.selectUpperCategory", input);
		return upper_category;
	}

	@Override
	public List<String> searchCategory() throws DataAccessException {
		List<String> category = new ArrayList<>();
		category = sqlSession.selectList("g.p001_2.selectCategory");
		return category;
	}
	


}
