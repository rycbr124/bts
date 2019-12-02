package bts.i.p002.dao;

import java.util.List;

import bts.i.p002.VO.I_P002VO_1;
import bts.i.p002.VO.I_P002VO_2;
import bts.i.p002.VO.I_P002VO_3;

public interface I_P002DAO {
	public List<String> planList(String member_id)throws Exception;
	public Integer paging(String member_id)throws Exception;
	public List<String> planRoot(String plan_no)throws Exception;
	public List<String> planner(String plan_no)throws Exception;
	public void insertPlan(I_P002VO_1 i_p002VO_1,List<I_P002VO_2>contentVO,List<I_P002VO_3> list)throws Exception;
	public String selectSeq(I_P002VO_1 i_p002VO_1 ,List<I_P002VO_2>contentVO,List<I_P002VO_3> list)throws Exception;
	public void delPlan(String plan_no)throws Exception;
	public List<String> wishList(String member_id)throws Exception;
	public List<String> tagList(String plan_no)throws Exception;
	public void updateDesc(I_P002VO_2 i_P002VO_2)throws Exception;
}
