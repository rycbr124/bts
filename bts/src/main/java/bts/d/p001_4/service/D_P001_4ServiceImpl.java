package bts.d.p001_4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.d.p001_4.dao.D_P001_4DAO;
import bts.d.p001_4.vo.D_P001_4VO;
import bts.d.p001_4.vo.D_P001_4VO_2;

@Service("d_p001_4Service")
public class D_P001_4ServiceImpl implements D_P001_4Service{
	@Autowired
	D_P001_4DAO d_p001_4DAO;

	@Override
	public List<D_P001_4VO> searchArticle() throws Exception {	
		return d_p001_4DAO.searchArticle();
	}

	@Override
	public List<D_P001_4VO> contentsArticle(String article_no) throws Exception {
		return d_p001_4DAO.contentsArticle(article_no);
	}

	@Override
	public List<D_P001_4VO_2> detailPlanner() throws Exception {
		return d_p001_4DAO.detailPlanner();
	}

}
