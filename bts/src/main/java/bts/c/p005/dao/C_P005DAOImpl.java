package bts.c.p005.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.g.p001_2.vo.G_P001_2VO;

@Repository("c_p005DAO")
public class C_P005DAOImpl implements C_P005DAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<G_P001_2VO> searchWishlist(String member_id) throws DataAccessException {
		return sqlSession.selectList("g.p001_2.searchWishlist", member_id);
	}

}
