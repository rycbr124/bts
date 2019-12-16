package bts.a.p000.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import bts.e.p001.VO.E_P001VO;
import bts.f.p001_3.vo.F_P001_3VO;
import bts.i.p002.VO.I_P002VO_1;

public interface A_P000DAO {
	public int countMember() throws DataAccessException;
	public int countContact() throws DataAccessException;
	public int countReport() throws DataAccessException;
	public int countContactY() throws DataAccessException;
	public int countReportY() throws DataAccessException;
	public int countPlan() throws DataAccessException;
	public int countReview() throws DataAccessException;
	public int countAccompany() throws DataAccessException;
	public List<I_P002VO_1> selectPlanner()throws DataAccessException;
	public List<F_P001_3VO> selectArticle()throws DataAccessException;
	public List<E_P001VO> selectAccompany()throws DataAccessException;
}
