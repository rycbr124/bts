package bts.common.report.dao;

import java.util.List;

import bts.common.report.vo.PnishVO;
import bts.common.report.vo.ReportVO;

public interface ReportDAO {

	String selectMenuCd(String name);

	int insertReport(ReportVO reportVO);

	List<PnishVO> selectPnishList();

	String selectReviewContents(ReportVO vo);

	String selectAccContents(ReportVO vo);

	List<String> selectPlanContents(ReportVO vo);

	String selectCommentContents(ReportVO vo);

}
