package bts.c.p001.DAO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.b.p001.VO.B_P001VO;
import bts.c.p001.vo.C_P001VO;


@Repository("c_p001DAO")
public class C_P001DAOImpl implements C_P001DAO{
	@Autowired
	private SqlSession sqlSession;

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
		public List<C_P001VO> selectCheckList() throws DataAccessException {
			List<C_P001VO> list = sqlSession.selectList("mapper.myPage.selectCheckList");
			return list;
		}
		public void deleteMemberList(String member_id) throws DataAccessException {
			//String result = sqlSession.selectList("mapper.myPage.deleteMemberList",member_id);
			sqlSession.delete("mapper.myPage.deleteMemberList",member_id);
			//return result;
		}
		public void insertCheckMemberList(List<C_P001VO> member_id) throws DataAccessException {
//			String result = sqlSession.selectList("mapper.myPage.insertCheckMemberList",member_id);
			sqlSession.insert("mapper.myPage.insertCheckMemberList",member_id);
//			return result;
		}
		public List<C_P001VO> selectCheckBoxList(String member_id) throws DataAccessException {
			List<C_P001VO> list = sqlSession.selectList("mapper.myPage.selectCheckBoxList",member_id);
			return list;
		}
		@Override
		public B_P001VO selectMember(String member_id) {
			B_P001VO b_p001VO = sqlSession.selectOne("mapper.myPage.selectMember",member_id);
			return b_p001VO;
		}
	}


