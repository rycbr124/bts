package bts.d.p001_4.service;

import java.util.List;

import org.springframework.stereotype.Service;

import bts.d.p001_4.vo.D_P001_4VO;
import bts.d.p001_4.vo.D_P001_4VO_2;
import bts.d.p001_4.vo.D_P001_4VO_3;

@Service("d_p001_4Service")
public interface D_P001_4Service {
	public List<D_P001_4VO> searchArticle() throws Exception;
	public List<D_P001_4VO> contentsArticle(String plan_no) throws Exception;
	public List<D_P001_4VO_2> detailPlanner(String plan_no) throws Exception;
	public List<D_P001_4VO_3> searchTag(String plan_no) throws Exception;
	public void insertContent(List<D_P001_4VO_2> voList) throws Exception;
	public List<D_P001_4VO> selectMyplan(String member_id) throws Exception;
	public void deletePlan(String plan_no) throws Exception;
}
