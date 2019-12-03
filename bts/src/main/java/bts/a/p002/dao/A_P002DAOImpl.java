package bts.a.p002.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bts.common.report.vo.PnishVO;

@Repository("a_p002DAO")
public class A_P002DAOImpl implements A_P002DAO{
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<PnishVO> selectPnishList(String p_name) {
		List<PnishVO> list = sqlSession.selectList("mapper.a_p002.selectPnishList",p_name.trim());
		return list;
	}
	
}
