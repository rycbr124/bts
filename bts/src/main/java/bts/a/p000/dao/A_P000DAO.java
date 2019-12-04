package bts.a.p000.dao;

import org.springframework.dao.DataAccessException;

public interface A_P000DAO {
	public int countMember() throws DataAccessException;
	public int countContact() throws DataAccessException;
	public int countReport() throws DataAccessException;
}
