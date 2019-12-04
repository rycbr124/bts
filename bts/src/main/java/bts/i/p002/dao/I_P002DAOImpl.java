package bts.i.p002.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.e.p001.VO.PagingVO;
import bts.i.p002.VO.I_P002VO_1;
import bts.i.p002.VO.I_P002VO_2;
import bts.i.p002.VO.I_P002VO_3;
@Repository("i_p002DAO")
public class I_P002DAOImpl implements I_P002DAO{
	@Autowired
	private SqlSession sqlSession;
	@Override
	public List<String> planList(String member_id)throws Exception{
		List<String> planList = new ArrayList<>();
		planList = sqlSession.selectList("i.p002.planList", member_id);
		return planList;
	}
	@Override
	public Integer paging(String member_id)throws DataAccessException{
		return sqlSession.selectOne("i.p002.pageCount",member_id);
	}
	@Override
	public List<String> planRoot(String plan_no)throws Exception{
		List<String> planRoot = new ArrayList<>();
		planRoot = sqlSession.selectList("i.p002.planRoot",plan_no);
				 
		return planRoot;
	}
	@Override
	public List<String> planner(String plan_no)throws Exception{
		List<String> planner_val = new ArrayList<>();
		planner_val = sqlSession.selectList("i.p002.planNo", plan_no);
		return planner_val;
	}
	@Override
	public void insertPlan(I_P002VO_1 i_p002VO_1,List<I_P002VO_2> contentVO,List<I_P002VO_3> list)throws DataAccessException{
		sqlSession.insert("i.p002.addPlan",i_p002VO_1);
		sqlSession.insert("i.p002.detailPlan",contentVO);
		sqlSession.insert("i.p002.planTag",list);
		
	}

	@Override
	public String selectSeq(I_P002VO_1 i_p002VO_1,List<I_P002VO_2> contentVO,List<I_P002VO_3> list)throws DataAccessException{
		String sequence = sqlSession.selectOne("i.p002.seq");
		return sequence;
	}
	@Override
	public void delPlan(String plan_no)throws Exception{
		sqlSession.delete("i.p002.deletePlanner",plan_no);
		sqlSession.delete("i.p002.deletePlanner_detail",plan_no);
		sqlSession.delete("i.p002.deleteTag_planner",plan_no);
		System.out.println("dao확인 : " + plan_no);
	}
	@Override
	public List<String> wishList(String member_id)throws Exception{
		List<String> wishList = new ArrayList<>();
		wishList = sqlSession.selectList("i.p002.wishList",member_id);
		
		return wishList;
	}
	@Override
	public List<String> tagList(String plan_no)throws Exception{
		 List<String> tagList = new ArrayList<>();
		 tagList = sqlSession.selectList("i.p002.tagList",plan_no);
		
		return tagList;
	}
	@Override
	public void updateDesc(I_P002VO_2 i_p002VO_2)throws Exception{
		sqlSession.update("i.p002.updateDesc", i_p002VO_2);
	}
}
