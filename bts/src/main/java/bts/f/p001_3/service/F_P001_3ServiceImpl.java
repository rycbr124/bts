package bts.f.p001_3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import bts.f.p001_3.dao.F_P001_3DAO;
import bts.f.p001_3.vo.F_P001_3VO;
import bts.f.p001_3.vo.F_P001_3VO_2;

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
	public String selectReviewTotal() throws DataAccessException{
		String totalCount = f_p001_3DAO.selectReviewTotal();
		return totalCount;
	}
	
	@Override
	public List<F_P001_3VO> selectReviewList(Map<String,Integer> searchMap) throws DataAccessException{
		List<F_P001_3VO> list = f_p001_3DAO.selectReviewList(searchMap);			
		for(int i=0; i<list.size();i++) {
			F_P001_3VO vo = list.get(i);
			List<String> tagList = new ArrayList<>();
			tagList = f_p001_3DAO.selectTagList(vo);
			vo.setTag_list(tagList);
		}
		return list;
	}
	
	@Override
	public F_P001_3VO selectReviewContents(Map<String,String> searchMap) throws DataAccessException{
		F_P001_3VO f_p001_3VO = f_p001_3DAO.selectReviewContents(searchMap);
		List<String> tagList = new ArrayList<>();
		tagList = f_p001_3DAO.selectTagList(f_p001_3VO);
		f_p001_3VO.setTag_list(tagList);
		return f_p001_3VO;
	}
}
