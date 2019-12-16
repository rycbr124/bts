package bts.d.p001_4.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.d.p001_4.dao.D_P001_4DAO;
import bts.d.p001_4.vo.D_P001_4VO;
import bts.d.p001_4.vo.D_P001_4VO_2;
import bts.d.p001_4.vo.D_P001_4VO_3;
import bts.d.p001_4.vo.D_P001_4VO_5;
import bts.e.p001.VO.PagingVO;

@Service("d_p001_4Service")
public class D_P001_4ServiceImpl implements D_P001_4Service{
	@Autowired
	D_P001_4DAO d_p001_4DAO;

	@Override
	public List<D_P001_4VO> searchArticle(PagingVO pagingVO, String category, String searchResult) throws Exception {	
		return d_p001_4DAO.searchArticle(pagingVO, category, searchResult);
	}
	
	@Override
	public List<D_P001_4VO> contentsArticle(String plan_no) throws Exception {
		return d_p001_4DAO.contentsArticle(plan_no);
	}
	
	@Override
	public List<D_P001_4VO_2> detailPlanner(String plan_no) throws Exception {
		return d_p001_4DAO.detailPlanner(plan_no);
	}

	@Override
	public List<D_P001_4VO_3> searchTag(String plan_no) throws Exception {
		return d_p001_4DAO.searchTag(plan_no);
	}
	@Override
	public void insertContent(List<D_P001_4VO_2> voList) throws Exception {
		d_p001_4DAO.insertContent(voList);
	}

	@Override
	public List<D_P001_4VO> selectMyplan(String member_id) throws Exception {
		return d_p001_4DAO.selectMyplan(member_id);
	}

	@Override
	public void deletePlan(String plan_no) throws Exception {
		d_p001_4DAO.deletePlan(plan_no);		
	}

	@Override
	public void increaseCnt(String plan_no) throws Exception {
		d_p001_4DAO.increaseCnt(plan_no);
		
	}

	@Override
	public void updateContent(List<D_P001_4VO_2> voList) throws Exception {
		d_p001_4DAO.updateContent(voList);
		
	}

	@Override
	public Integer listCount(String category, String searchResult) throws Exception {
		return d_p001_4DAO.pageCount(category, searchResult);
	}

	@Override
	public List<D_P001_4VO_5> selectAnswerList(Map<String, String> searchMap) throws Exception {
		return d_p001_4DAO.selectAnswerList(searchMap);
	}

	@Override
	public String selectArticleCd(String menuname) throws Exception {
		return d_p001_4DAO.selectArticleCd(menuname);
	}

	@Override
	public String selectCommentTotal(Map<String, String> searchMap) throws Exception {
		return d_p001_4DAO.selectCommentTotal(searchMap);
	}

	@Override
	public void insertAnswer(D_P001_4VO_5 d_p001_4VO_5) throws Exception {
		d_p001_4DAO.insertAnswer(d_p001_4VO_5);
	}

	@Override
	public int deleteAnswer(String answer_no) throws Exception {
		return d_p001_4DAO.deleteAnswer(answer_no);
	}

	@Override
	public List<String> findContentId() throws Exception {
		return d_p001_4DAO.findContentId();
	}

	@Override
	public List<D_P001_4VO> searchTitle(String searchResult) throws Exception {
		return d_p001_4DAO.searchTitle(searchResult);
	}

}
