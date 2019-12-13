package bts.a.p002.dao;

import java.util.List;
import java.util.Map;

import bts.common.report.vo.PnishVO;
import bts.common.report.vo.ReportVO;

public interface A_P002DAO {

	List<PnishVO> selectPnishList(String p_name);

	void insertPnish(Map<String, String> row);

	void updatePnish(Map<String, String> row);

	void deletePnish(Map<String, String> row);

	List<ReportVO> selectReportList(Map<String, String> searchMap);

	ReportVO selectReportContent(int report_no);

	String selectMenuName(String report_se);

	String selectAccWriter(String article_no);

	String selectAnswerInfo(String report_se);

}
