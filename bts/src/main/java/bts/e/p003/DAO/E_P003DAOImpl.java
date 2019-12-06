package bts.e.p003.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.e.p003.VO.E_P003VO;
import bts.e.p003.VO.E_P003VO_2;


@Repository("e_p003DAO")
public class E_P003DAOImpl implements E_P003DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertAcc(E_P003VO e_p003VO) throws DataAccessException {
		sqlSession.insert("mapper.accompany.accInsert",e_p003VO);
	}

	@Override
	public void insertTag(List<E_P003VO_2> list) throws DataAccessException {
		sqlSession.insert("mapper.accompany.tagInsert",list);
	}

}
