package bts.a.p000.service;

import java.util.List;
import java.util.Map;

import bts.e.p001.VO.E_P001VO;
import bts.f.p001_3.vo.F_P001_3VO;
import bts.i.p002.VO.I_P002VO_1;

public interface A_P000Service {
	public int countMember() throws Exception;
	public int countContact() throws Exception;
	public int countReport() throws Exception;
	public long countAnswer() throws Exception;
	public Map<String, String> countWrite() throws Exception;
	public List<I_P002VO_1> selectPlanner()throws Exception;
	public List<F_P001_3VO> selectArticle()throws Exception;
	public List<E_P001VO> selectAccompany()throws Exception;
}
