package bts.common.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.common.report.dao.ReportDAO;

@Service("reportService")
public class ReportServiceImpl implements ReportService{
	@Autowired
	ReportDAO reportDAO;

	@Override
	public String selectMenuCd(String name) {
		String result=reportDAO.selectMenuCd(name);
		return result;
	}
}
