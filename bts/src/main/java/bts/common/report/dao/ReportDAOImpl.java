package bts.common.report.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("reportDAO")
public class ReportDAOImpl implements ReportDAO{
	@Autowired
	SqlSession sqlSession;

	@Override
	public String selectMenuCd(String name) {
		String result=sqlSession.selectOne("mapper.report.selectMenuCd",name);
		return result;
	}
}
