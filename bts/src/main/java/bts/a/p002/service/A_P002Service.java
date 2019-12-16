package bts.a.p002.service;

import java.util.List;
import java.util.Map;

import bts.a.p002.vo.A_P002VO_1;
import bts.common.report.vo.PnishVO;
import bts.common.report.vo.ReportVO;
import bts.f.p001_3.vo.F_P001_3VO_3;

public interface A_P002Service {

	List<PnishVO> selectPnishList(String p_name);

	void savePnishList(Map<String, String[]> dataMap);

	List<ReportVO> selectReportList(Map<String, String> searchMap);

	ReportVO selectReportContent(int report_no);

	String selectMenuName(String report_se);

	String selectAccWriter(String article_no);

	F_P001_3VO_3 selectAnswerInfo(String report_se);

	void insertPnishHistory(A_P002VO_1 a_p002VO_1);

	void updateReportEnd(int report_no);

	A_P002VO_1 selectReportResult(int report_no);

	List<A_P002VO_1> selectHistoryList(Map<String, String> searchMap);

	List<String> selectPnishName();

	void saveHistoryList(Map<String, String[]> dataMap);

}
