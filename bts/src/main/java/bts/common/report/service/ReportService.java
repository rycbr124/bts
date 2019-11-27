package bts.common.report.service;

import java.util.List;

import bts.common.report.vo.PnishVO;
import bts.common.report.vo.ReportVO;

public interface ReportService {
	String selectMenuCd(String name);

	int insertReport(ReportVO reportVO);

	List<PnishVO> selectPnishList();
}
