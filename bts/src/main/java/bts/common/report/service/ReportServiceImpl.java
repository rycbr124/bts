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

	@Override
	public String selectReviewContents(ReportVO vo) {
		String contents = reportDAO.selectReviewContents(vo);
		return contents;
	}

	@Override
	public String selectAccContents(ReportVO vo) {
		String contents = reportDAO.selectAccContents(vo);
		return contents;
	}

	@Override
	public String selectPlanContents(ReportVO vo) {
		List<String> list = reportDAO.selectPlanContents(vo);
		StringBuffer contents = new StringBuffer();
		for(int i=0;i<(list.size()-1);i++) {
			contents.append(list.get(i));
		}
		return contents.toString();
	}

	@Override
	public String selectCommentContents(ReportVO vo) {
		String contents = reportDAO.selectCommentContents(vo);
		return contents;
	}
}
