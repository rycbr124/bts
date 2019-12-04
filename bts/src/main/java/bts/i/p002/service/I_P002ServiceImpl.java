package bts.i.p002.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.e.p001.VO.PagingVO;
import bts.i.p002.VO.I_P002VO_1;
import bts.i.p002.VO.I_P002VO_2;
import bts.i.p002.VO.I_P002VO_3;
import bts.i.p002.dao.I_P002DAO;

@Service("i_p002Service")
public class I_P002ServiceImpl implements I_P002Service{
	@Autowired
	I_P002DAO i_p002DAO;
	
	@Override
	public Map<String,List<String>> planList(String member_id)throws Exception{
		Map<String, List<String>> result = new HashMap<>();
		List<String> planList = i_p002DAO.planList(member_id);
		
		result.put("plan_no", planList);
		
		return result;
	}
	@Override
	public Integer listCount(String member_id)throws Exception{
		return i_p002DAO.paging(member_id);
	}
	@Override
	public Map<String, List<String>> planRoot(String plan_no)throws Exception{
		Map<String, List<String>> resultRoot = new HashMap<>();
		List<String> planRoot = i_p002DAO.planRoot(plan_no);
		
		resultRoot.put("plan_root", planRoot);
		
		return resultRoot;
	}
	@Override
	public Map<String,List<String>> planner(String plan_no)throws Exception{
		Map<String,List<String>> resultPlanner = new HashMap<>();
		List<String> planner = i_p002DAO.planner(plan_no);
		
		resultPlanner.put("planner",planner);
		
		return resultPlanner;
	}
	@Override
	public void insertPlan(I_P002VO_1 i_p002VO_1,List<I_P002VO_2>contentVO , List<I_P002VO_3> list)throws Exception{
		String seq = i_p002DAO.selectSeq(i_p002VO_1, contentVO, list);
		i_p002VO_1.setPlan_no(seq);
		for(int i=0; i<contentVO.size();i++) {
			contentVO.get(i).setPlan_no(seq);
		}
		for(int j=0; j<list.size();j++) {
			list.get(j).setPlan_no(seq);			
		}
		i_p002DAO.insertPlan(i_p002VO_1,contentVO,list);
	}
	@Override
	public void delPlan(String plan_no)throws Exception{
		i_p002DAO.delPlan(plan_no);
	}
	@Override
	public Map<String,List<String>> wishList(String member_id)throws Exception{
		Map<String,List<String>> selectWishList = new HashMap<>();
		List<String> wishList = i_p002DAO.wishList(member_id);
		selectWishList.put("wishList", wishList);
		
		return selectWishList;
	}
	@Override
	public Map<String,List<String>> tagList(String plan_no)throws Exception{
		Map<String,List<String>> tagList = new HashMap<>();
		List<String> tag = i_p002DAO.tagList(plan_no);
		
		tagList.put("tagList", tag);
		return tagList;
	}
	@Override
	public void updateDesc(I_P002VO_2 i_p002VO_2)throws Exception{
		i_p002DAO.updateDesc(i_p002VO_2);
	}
}
