package bts.d.p001_4.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.d.p001_4.vo.D_P001_4VO;
import bts.d.p001_4.vo.D_P001_4VO_2;
import bts.d.p001_4.vo.D_P001_4VO_3;

@Repository("d_p001_4DAO")
public class D_P001_4DAOImpl implements D_P001_4DAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<D_P001_4VO> searchArticle() throws DataAccessException {
		return sqlSession.selectList("d.p001_4.searchArticle");
	}
	
	@Override
	public List<D_P001_4VO> contentsArticle(String plan_no) throws DataAccessException {
		return sqlSession.selectList("d.p001_4.contentsArticle", plan_no);
	}
	
	@Override
	public List<D_P001_4VO_2> detailPlanner(String plan_no) throws DataAccessException {
		return sqlSession.selectList("d.p001_4.plannerDetail", plan_no);
	}

	@Override
	public List<D_P001_4VO_3> searchTag(String plan_no) throws DataAccessException {
		return sqlSession.selectList("d.p001_4.searchTag", plan_no);
	}

	@Override
	public void insertContent(List<D_P001_4VO_2> voList) throws DataAccessException {
		sqlSession.insert("d.p001_4.insertDesc", voList);
		sqlSession.insert("d.p001_4.updateOpen", voList);
		
	}

	@Override
	public List<D_P001_4VO> selectMyplan(String member_id) throws DataAccessException {
		return sqlSession.selectList("d.p001_4.selectMyplan", member_id);
	}

	@Override
	public void deletePlan(String plan_no) throws DataAccessException {
		sqlSession.delete("d.p001_4.deletePlan", plan_no);
		sqlSession.delete("d.p001_4.deleteDetail", plan_no);
	}

}
