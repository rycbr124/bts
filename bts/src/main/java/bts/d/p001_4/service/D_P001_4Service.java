package bts.d.p001_4.service;

import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Service;

import bts.d.p001_4.vo.D_P001_4VO;
import bts.d.p001_4.vo.D_P001_4VO_2;
import bts.d.p001_4.vo.D_P001_4VO_3;
import bts.d.p001_4.vo.D_P001_4VO_5;
import bts.e.p001.VO.PagingVO;
import bts.f.p001_3.vo.F_P001_3VO_3;

@Service("d_p001_4Service")
public interface D_P001_4Service {
	public List<D_P001_4VO> searchArticle(PagingVO pagingVO, String category, String searchResult) throws Exception;
	public List<D_P001_4VO> contentsArticle(String plan_no) throws Exception;
	public List<D_P001_4VO_2> detailPlanner(String plan_no) throws Exception;
	public List<D_P001_4VO_3> searchTag(String plan_no) throws Exception;
	public void insertContent(List<D_P001_4VO_2> voList) throws Exception;
	public List<D_P001_4VO> selectMyplan(String member_id) throws Exception;
	public void deletePlan(String plan_no) throws Exception;
	public void increaseCnt(String plan_no) throws Exception;
	public void updateContent(List<D_P001_4VO_2> voList) throws Exception;
	public Integer listCount(String category, String searchResult) throws Exception;
	public List<D_P001_4VO_5> selectAnswerList(Map<String,String> searchMap) throws Exception;
	public String selectArticleCd(String menuname) throws Exception;
	public String selectCommentTotal(Map<String, String> searchMap) throws Exception;
	public void insertAnswer(D_P001_4VO_5 d_p001_4VO_5) throws Exception;
	public int deleteAnswer(String answer_no) throws Exception;
	public List<String> findContentId() throws Exception;
	public List<D_P001_4VO> searchTitle(String searchResult) throws Exception;
}
