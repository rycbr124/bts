package bts.b.p003.DAO;

import org.apache.ibatis.session.SqlSession;

import bts.b.p001.VO.B_P001VO;

public interface B_P003DAO {
	
	public String find_id(String email) throws Exception;
	public int update_pw(B_P001VO p001VO) throws Exception;
	
}
