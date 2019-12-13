package bts.common.report.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bts.common.report.vo.PnishVO;
import bts.common.report.vo.ReportVO;

@Repository("reportDAO")
public class ReportDAOImpl implements ReportDAO{
	@Autowired
	SqlSession sqlSession;

	@Override
	public String selectMenuCd(String name) {
		String result=sqlSession.selectOne("mapper.report.selectMenuCd",name);
		return result;
	}

	@Override
	public int insertReport(ReportVO reportVO) {
		int result=sqlSession.insert("mapper.report.insertReport",reportVO);
		return result;
	}

	@Override
	public List<PnishVO> selectPnishList() {
		List<PnishVO> list = sqlSession.selectList("mapper.report.selectPnishList");
		return list;
	}

	@Override
	public String selectReviewContents(ReportVO vo) {
		String contents = sqlSession.selectOne("mapper.report.selectReviewContents",vo);
		return contents;
	}

	@Override
	public String selectAccContents(ReportVO vo) {
		String contents = sqlSession.selectOne("mapper.report.selectAccContents",vo);
		return contents;
	}

	@Override
	public List<String> selectPlanContents(ReportVO vo) {
		List<String> contents = sqlSession.selectList("mapper.report.selectPlanContents",vo);
		return contents;
	}

	@Override
	public String selectCommentContents(ReportVO vo) {
		String contents = sqlSession.selectOne("mapper.report.selectCommentContents",vo);
		return contents;
	}
}
