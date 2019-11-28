package bts.e.p003.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bts.e.p003.DAO.E_P003DAO;
import bts.e.p003.VO.E_P003VO;
import bts.e.p003.VO.E_P003VO_2;

@Service("e_p003Service")
@Transactional(propagation = Propagation.REQUIRED)
public class E_P003ServiceImpl implements E_P003Service{

	@Autowired
	E_P003DAO e_p003DAO;
	
	@Override
	public void insertAcc(E_P003VO e_p003VO) throws Exception {
		e_p003DAO.insertAcc(e_p003VO);
	}

	@Override
	public void insertTag(List<E_P003VO_2> list) throws DataAccessException {
		e_p003DAO.insertTag(list);
		
	}

}
