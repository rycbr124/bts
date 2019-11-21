package bts.d.p001_4.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import bts.d.p001_4.vo.D_P001_4VO;
import bts.d.p001_4.vo.D_P001_4VO_2;
import bts.d.p001_4.vo.D_P001_4VO_3;

public interface D_P001_4DAO {
	public List<D_P001_4VO> searchArticle() throws DataAccessException;
	public List<D_P001_4VO> contentsArticle(String plan_no) throws DataAccessException;
	public List<D_P001_4VO_2> detailPlanner(String plan_no) throws DataAccessException;
	public List<D_P001_4VO_3> searchTag(String plan_no) throws DataAccessException;
	public void insertContent(List<D_P001_4VO_2> voList) throws DataAccessException;
	public List<D_P001_4VO> selectMyplan(String member_id) throws DataAccessException;
	public void deletePlan(String plan_no) throws DataAccessException;
}
