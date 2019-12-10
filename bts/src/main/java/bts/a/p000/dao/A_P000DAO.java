package bts.a.p000.dao;

import org.springframework.dao.DataAccessException;

public interface A_P000DAO {
	public int countMember() throws DataAccessException;
	public int countContact() throws DataAccessException;
	public int countReport() throws DataAccessException;
	public int countContactY() throws DataAccessException;
	public int countReportY() throws DataAccessException;
	public int countPlan() throws DataAccessException;
	public int countReview() throws DataAccessException;
	public int countAccompany() throws DataAccessException;
}
