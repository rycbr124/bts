package bts.a.p002.service;

import java.util.List;
import java.util.Map;

import bts.common.report.vo.PnishVO;
import bts.common.report.vo.ReportVO;

public interface A_P002Service {

	List<PnishVO> selectPnishList(String p_name);

	void savePnishList(Map<String, String[]> dataMap);

	List<ReportVO> selectReportList(Map<String, String> searchMap);

	ReportVO selectReportContent(int report_no);

	String selectMenuName(String report_se);

	String selectAccWriter(String article_no);

	String selectAnswerInfo(String report_se);

}
