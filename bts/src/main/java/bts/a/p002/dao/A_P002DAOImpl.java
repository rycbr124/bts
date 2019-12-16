package bts.a.p002.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bts.a.p002.vo.A_P002VO_1;
import bts.common.report.vo.PnishVO;
import bts.common.report.vo.ReportVO;
import bts.f.p001_3.vo.F_P001_3VO_3;

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
		sqlSession.update("mapper.a_p002.updatePnish",row);
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

	@Override
	public String selectAccWriter(String article_no) {
		String result = sqlSession.selectOne("mapper.a_p002.selectAccWriter",article_no);
		return result;
	}

	@Override
	public F_P001_3VO_3 selectAnswerInfo(String report_se) {
		F_P001_3VO_3 result = sqlSession.selectOne("mapper.a_p002.selectAnswerInfo",report_se);
		return result;
	}

	@Override
	public void insertPnishHistory(A_P002VO_1 a_p002VO_1) {
		sqlSession.insert("mapper.a_p002.insertPnishHistory",a_p002VO_1);
	}

	@Override
	public void updateReportEnd(int report_no) {
		sqlSession.update("mapper.a_p002.updateReportEnd", report_no);
	}

	@Override
	public A_P002VO_1 selectCommentContents(int report_no) {
		A_P002VO_1 result = sqlSession.selectOne("mapper.a_p002.selectReportResult", report_no);
		return result;
	}

	@Override
	public List<A_P002VO_1> selectHistoryList(Map<String, String> searchMap) {
		List<A_P002VO_1> list = sqlSession.selectList("mapper.a_p002.selectHistoryList",searchMap);
		return list;
	}

	@Override
	public List<String> selectPnishName() {
		List<String> list = sqlSession.selectList("mapper.a_p002.selectPnishName");
		return list;
	}

	@Override
	public void insertHistory(Map<String, String> row) {
		sqlSession.insert("mapper.a_p002.insertHistory",row);
	}

	@Override
	public void updateHistory(Map<String, String> row) {
		sqlSession.update("mapper.a_p002.updateHistory",row);
	}

	@Override
	public void deleteHistory(Map<String, String> row) {
		sqlSession.delete("mapper.a_p002.deleteHistory",row);
	}
	
}
