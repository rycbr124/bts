package bts.d.p001_4.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.d.p001_4.vo.D_P001_4VO;

@Repository("d_p001_4DAO")
public class D_P001_4DAOImpl implements D_P001_4DAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<D_P001_4VO> searchArticle() throws DataAccessException {
		
		return sqlSession.selectList("d.p001_4.searchArticle");
	}
}
