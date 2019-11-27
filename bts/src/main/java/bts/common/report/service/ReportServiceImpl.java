package bts.common.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.common.report.dao.ReportDAO;
import bts.common.report.vo.PnishVO;
import bts.common.report.vo.ReportVO;

@Service("reportService")
public class ReportServiceImpl implements ReportService{
	@Autowired
	ReportDAO reportDAO;

	@Override
	public String selectMenuCd(String name) {
		String result=reportDAO.selectMenuCd(name);
		return result;
	}

	@Override
	public int insertReport(ReportVO reportVO) {
		int result=reportDAO.insertReport(reportVO);
		return result;
	}

	@Override
	public List<PnishVO> selectPnishList() {
		List<PnishVO> list = reportDAO.selectPnishList();
		return list;
	}
}
