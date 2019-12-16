package bts.a.p002.dao;

import java.util.List;
import java.util.Map;

import bts.a.p002.vo.A_P002VO_1;
import bts.common.report.vo.PnishVO;
import bts.common.report.vo.ReportVO;
import bts.f.p001_3.vo.F_P001_3VO_3;

public interface A_P002DAO {

	List<PnishVO> selectPnishList(String p_name);

	void insertPnish(Map<String, String> row);

	void updatePnish(Map<String, String> row);

	void deletePnish(Map<String, String> row);

	List<ReportVO> selectReportList(Map<String, String> searchMap);

	ReportVO selectReportContent(int report_no);

	String selectMenuName(String report_se);

	String selectAccWriter(String article_no);

	F_P001_3VO_3 selectAnswerInfo(String report_se);

	void insertPnishHistory(A_P002VO_1 a_p002VO_1);

	void updateReportEnd(int report_no);

	A_P002VO_1 selectCommentContents(int report_no);

	List<A_P002VO_1> selectHistoryList(Map<String, String> searchMap);

	List<String> selectPnishName();

	void insertHistory(Map<String, String> row);

	void updateHistory(Map<String, String> row);

	void deleteHistory(Map<String, String> row);

}
