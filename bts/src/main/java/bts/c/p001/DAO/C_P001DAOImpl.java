package bts.c.p001.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import bts.b.p001.VO.B_P001VO;


@Repository("c_p001DAO")
public class C_P001DAOImpl implements C_P001DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public B_P001VO selectMember(String memberId) throws DataAccessException {
		B_P001VO d001VO = (B_P001VO)sqlSession.selectOne("mapper.member.seleteMember",memberId);
		return d001VO;
	}
	
	@Override
	public void updateMember(B_P001VO d001vo) throws DataAccessException {
		sqlSession.update("mapper.member.updateMember",d001vo);
		
	}

	@Override
	public void updateProfile(B_P001VO d001vo) throws DataAccessException {
		sqlSession.update("mapper.Profile.updateProfile", d001vo);
		
	}
	
	//찾아봐 sqlSession.delete 
	@Override
	public String deleteMember(String memberId) throws DataAccessException {
		//String test = sqlSession.delete("mapper.member.deleteMember", memberId);
		int  test = sqlSession.delete("mapper.member.deleteMember", memberId);
		
		return null;
	}

}
