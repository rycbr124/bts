package bts.f.p001_3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import bts.d.p001_4.vo.D_P001_4VO;
import bts.f.p001_3.dao.F_P001_3DAO;
import bts.f.p001_3.vo.F_P001_3VO;
import bts.f.p001_3.vo.F_P001_3VO_2;
import bts.f.p001_3.vo.F_P001_3VO_3;

@Service("f_p001_3Service")
public class F_P001_3ServiceImpl implements F_P001_3Service{
	@Autowired
	F_P001_3DAO f_p001_3DAO;	
	
	@Override
	public void insertArticle(F_P001_3VO f_p001_3VO) throws DataAccessException{
		f_p001_3DAO.insertArticle(f_p001_3VO);
	}
	
	@Override
	public void insertTagList(List<F_P001_3VO_2> list) throws DataAccessException{
		f_p001_3DAO.insertTagList(list);
	}	

	@Override
	public void insertAnswer(F_P001_3VO_3 f_p001_3VO_3) throws DataAccessException{
		f_p001_3DAO.insertAnswer(f_p001_3VO_3);
	}		

	@Override
	public void deleteReviewContents(F_P001_3VO f_p001_3VO) throws DataAccessException {
		f_p001_3DAO.deleteReviewContents(f_p001_3VO);
		f_p001_3DAO.deleteReviewAnswer(f_p001_3VO);
		f_p001_3DAO.deleteTagList(f_p001_3VO);
	}
	
	@Override
	public int deleteAnswer(String answer_no) throws DataAccessException{
		int result = f_p001_3DAO.deleteAnswer(answer_no);
		return result;
	}	
	
	@Override
	public void updateArticle(F_P001_3VO f_p001_3VO) throws DataAccessException {
		f_p001_3DAO.updateArticle(f_p001_3VO);
	}
	
	@Override
	public void updateTagList(List<F_P001_3VO_2> updateTagList,F_P001_3VO f_p001_3VO) throws DataAccessException{
		f_p001_3DAO.deleteTagList(f_p001_3VO);
		f_p001_3DAO.insertTagList(updateTagList);
	}	
	
	@Override
	public String selectReviewTotal() throws DataAccessException{
		String totalCount = f_p001_3DAO.selectReviewTotal();
		return totalCount;
	}
	
	@Override
	public String selectReviewTotal(Map<String, String> searchMap) throws DataAccessException{
		String totalCount = f_p001_3DAO.selectReviewTotal(searchMap);
		return totalCount;
	}

	@Override
	public String selectCommentTotal(Map<String,String> searchMap) throws DataAccessException{
		String totalCount = f_p001_3DAO.selectCommentTotal(searchMap);
		return totalCount;
	}	
	
	@Override
	public List<F_P001_3VO> selectReviewList(Map<String,String> searchMap) throws DataAccessException{
		List<F_P001_3VO> list = f_p001_3DAO.selectReviewList(searchMap);			
		for(int i=0; i<list.size();i++) {
			F_P001_3VO vo = list.get(i);
			vo.setArticle_cd(searchMap.get("article_cd"));
			List<String> tagList = new ArrayList<>();
			tagList = f_p001_3DAO.selectTagList(vo);
			vo.setTag_list(tagList);
		}
		return list;
	}
	
	@Override
	public F_P001_3VO selectReviewContents(Map<String,String> searchMap) throws DataAccessException{
		F_P001_3VO f_p001_3VO = f_p001_3DAO.selectReviewContents(searchMap);
		f_p001_3VO.setArticle_cd(searchMap.get("article_cd"));
		List<String> tagList = new ArrayList<>();
		tagList = f_p001_3DAO.selectTagList(f_p001_3VO);
		f_p001_3VO.setTag_list(tagList);
		return f_p001_3VO;
	}
	
	@Override
	public List<F_P001_3VO_3> selectAnswerList(Map<String,String> searchMap) throws DataAccessException{
		List<F_P001_3VO_3> list = f_p001_3DAO.selectAnswerList(searchMap);
		return list;
	}

	@Override
	public String selectArticleCd(String menu) throws DataAccessException{
		String article_cd = f_p001_3DAO.selectArticleCd(menu);
		return article_cd;
	}

	@Override
	public int selectRecommendTotal(Map<String, String> searchMap) throws DataAccessException{
		int totalCount = f_p001_3DAO.selectRecommendTotal(searchMap);
		return totalCount;
	}

	@Override
	public int selectPlanTotal(Map<String, String> searchMap) throws DataAccessException{
		int totalCount = f_p001_3DAO.selectPlanTotal(searchMap);
		return totalCount;
	}

	@Override
	public List<Map<String, String>> selectRecommend(Map<String, String> searchMap) throws DataAccessException{
		List<Map<String, String>> list = f_p001_3DAO.selectRecommend(searchMap);
		return list;
	}

	@Override
	public List<D_P001_4VO> selectPlan(Map<String, String> searchMap) throws DataAccessException{
		List<D_P001_4VO> list = f_p001_3DAO.selectPlan(searchMap);
		return list;
	}

}
