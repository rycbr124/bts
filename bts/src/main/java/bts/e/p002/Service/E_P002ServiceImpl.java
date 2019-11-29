package bts.e.p002.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.e.p002.DAO.E_P002DAO;
import bts.e.p003.VO.E_P003VO;

@Service("e_p002Service")
public class E_P002ServiceImpl implements E_P002Service{
	@Autowired
	E_P002DAO e_p002DAO;
	
	@Override
	public void accDelete(int article_no) throws Exception {
		e_p002DAO.accDelete(article_no);
	}

	@Override
	public void accTagDel(int article_no) throws Exception {
		e_p002DAO.accTagDel(article_no);
	}

	@Override
	public void accUpdate(E_P003VO e_p003Vo) throws Exception {
		e_p002DAO.accUpdate(e_p003Vo);
	}

}
