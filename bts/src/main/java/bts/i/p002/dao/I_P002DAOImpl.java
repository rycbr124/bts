package bts.i.p002.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import bts.i.p002.VO.I_P002VO_1;
import bts.i.p002.VO.I_P002VO_2;
import bts.i.p002.VO.I_P002VO_3;
@Repository("i_p002DAO")
public class I_P002DAOImpl implements I_P002DAO{
	@Autowired
	private SqlSession sqlSession;
	@Override
	public List<String> planList()throws Exception{
		List<String> planList = new ArrayList<>();
		planList = sqlSession.selectList("i.p002.planList");
		return planList;
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
		System.out.println("sequence : " + sequence);
		return sequence;
	}
}
