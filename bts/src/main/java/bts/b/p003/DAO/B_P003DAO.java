package bts.b.p003.DAO;

import org.apache.ibatis.session.SqlSession;

public interface B_P003DAO {
	
	public String find_id(String email) throws Exception;
}
