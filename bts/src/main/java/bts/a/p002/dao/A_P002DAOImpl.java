package bts.a.p002.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bts.common.report.vo.PnishVO;
import bts.common.report.vo.ReportVO;

@Repository("a_p002DAO")
public class A_P002DAOImpl implements A_P002DAO{
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<PnishVO> selectPnishList(String p_name) {
		List<PnishVO> list = sqlSession.selectList("mapper.a_p002.selectPnishList",p_name.trim());
		return list;
	}

	@Override
	public void insertPnish(Map<String, String> row) {
		sqlSession.insert("mapper.a_p002.insertPnish",row);
	}

	@Override
	public void updatePnish(Map<String, String> row) {
		sqlSession.insert("mapper.a_p002.updatePnish",row);
	}

	@Override
	public void deletePnish(Map<String, String> row) {
		sqlSession.insert("mapper.a_p002.deletePnish",row);		
	}

	@Override
	public List<ReportVO> selectReportList(Map<String, String> searchMap) {
		List<ReportVO> list = sqlSession.selectList("mapper.a_p002.selectReportList",searchMap);
		return list;
	}

	@Override
	public ReportVO selectReportContent(int report_no) {
		ReportVO result = sqlSession.selectOne("mapper.a_p002.selectReportContent",report_no);
		return result;
	}

	@Override
	public String selectMenuName(String report_se) {
		String result = sqlSession.selectOne("mapper.a_p002.selectMenuName",report_se);
		return result;
	}
	
}
