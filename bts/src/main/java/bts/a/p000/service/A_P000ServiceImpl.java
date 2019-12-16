package bts.a.p000.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.a.p000.dao.A_P000DAO;
import bts.e.p001.VO.E_P001VO;
import bts.f.p001_3.vo.F_P001_3VO;
import bts.i.p002.VO.I_P002VO_1;

@Service("a_p000Service")
public class A_P000ServiceImpl implements A_P000Service{
	@Autowired
	A_P000DAO a_p000DAO;

	@Override
	public int countMember() throws Exception {
		return a_p000DAO.countMember();
	}

	@Override
	public int countContact() throws Exception {
		return a_p000DAO.countContact();
	}

	@Override
	public int countReport() throws Exception {
		return a_p000DAO.countReport();
	}

	@Override
	public long countAnswer() throws Exception {
		int contact = a_p000DAO.countContact();
		int report = a_p000DAO.countReport();
		int total = contact + report;
	
		int contactYes = a_p000DAO.countContactY();
		int reportYes = a_p000DAO.countReportY();
		int done = contactYes + reportYes;

		double resultPer = (double)done/(double)total * 100;
		long result = Math.round(resultPer);
		
		return result;
	}

	@Override
	public Map<String, String> countWrite() throws Exception {
		int review = a_p000DAO.countReview();
		int plan = a_p000DAO.countPlan();
		int acc = a_p000DAO.countAccompany();
		int total = review + plan + acc;
		
		double review_result = (double)review/(double)total*100;
		double plan_result = (double)plan/(double)total*100;
		double acc_result = (double)acc/(double)total*100;
		
		long resultReview = Math.round(review_result);
		long resultPlan = Math.round(plan_result);
		long resultAcc = Math.round(acc_result);
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("review", String.valueOf(resultReview));
		resultMap.put("plan", String.valueOf(resultPlan));
		resultMap.put("accompany", String.valueOf(resultAcc));
			
		return resultMap;
	}
	@Override
	public List<I_P002VO_1> selectPlanner()throws Exception{
		return a_p000DAO.selectPlanner();
	}
	@Override
	public List<F_P001_3VO> selectArticle()throws Exception{
		return a_p000DAO.selectArticle();
	}
	@Override
	public List<E_P001VO> selectAccompany()throws Exception{
		return a_p000DAO.selectAccompany();
	}
}
