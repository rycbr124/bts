package bts.z.p000.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository("z_p000DAO")
public class Z_P000DAOImpl implements Z_P000DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<String> mainAccompany()throws DataAccessException{
		return sqlSession.selectList("z.p000.searchAccompany");
	}

	@Override
	public List<String> searchIcon() throws Exception {
		return sqlSession.selectList("z.p000.searchIcon");
	}
}
