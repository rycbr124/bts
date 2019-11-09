package bts.b.p003.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bts.b.p001.VO.B_P001VO;


@Repository("b_p003DAO")
public class B_P003DAOImpl implements B_P003DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String find_id(String email) throws Exception {
		return sqlSession.selectOne("mapper.member.find_id",email);
	}

	@Override
	@Transactional
	public int update_pw(B_P001VO p001vo) throws Exception {
		
		return sqlSession.update("mapper.member.find_pw",p001vo);
	}

}
