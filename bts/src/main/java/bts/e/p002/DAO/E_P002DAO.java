package bts.e.p002.DAO;

import org.springframework.dao.DataAccessException;

import bts.e.p002.VO.E_P002VO;
import bts.e.p003.VO.E_P003VO;

public interface E_P002DAO {
	public void accDelete(int article_no) throws DataAccessException;
	public void accTagDel(int article_no) throws DataAccessException;
	public void accUpdate(E_P003VO e_p003VO) throws DataAccessException;
	public void accReq(E_P002VO e_p002VO) throws DataAccessException;
}
