package bts.c.p001.DAO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import bts.b.p001.VO.B_P001VO;
import bts.c.p001.vo.C_P001VO;


@Repository("c_p001DAO")
public class C_P001DAOImpl implements C_P001DAO{
	@Autowired
	private SqlSession sqlSession;
	
//	@Override
//	public B_P001VO selectMember(String memberId) throws DataAccessException {
//		B_P001VO d001VO = (B_P001VO)sqlSession.selectOne("mapper.member.seleteMember",memberId);
//		return d001VO;
//	}
	
	@Override
	public void updateMember(B_P001VO d001vo) throws DataAccessException {
		sqlSession.update("mapper.member.updateMember",d001vo);
		
	}
	//포로필 사진 업로드
	@Override
	public void updateImage(B_P001VO d001vo) throws DataAccessException {
		sqlSession.update("mapper.member.updateProfile",d001vo );	
	}
	
//	//찾아봐 sqlSession.delete 
//	@Override
//	public String deleteMember(String member_id) throws DataAccessException {
//		//String test = sqlSession.delete("mapper.member.deleteMember", memberId);
//		int  test = sqlSession.delete("mapper.member.deleteMember", member_id);
//		
//		return null;
//	}
	
		
		//패스워드 체크
		public String passCheck(Map<String,String> searchData) {
			String result=sqlSession.selectOne("mapper.member.passwordCheck",searchData);

			return result;
		}
		
		//회원탈퇴
		public void secession(B_P001VO d001vo,HttpSession session) {
			
			sqlSession.delete("mapper.member.secession",d001vo);
			//세션 삭제
			session.invalidate();
		}

	
		public List<C_P001VO> selectInclnList() throws DataAccessException {
			List<C_P001VO> list = sqlSession.selectList("mapper.myPage.selectInclnList");
			return list;
		}
	}


