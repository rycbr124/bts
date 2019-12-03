package bts.a.p002.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.a.p002.dao.A_P002DAO;
import bts.common.report.vo.PnishVO;

@Service("a_p002Service")
public class A_P002ServiceImpl implements A_P002Service{
	@Autowired
	A_P002DAO a_p002DAO;

	@Override
	public List<PnishVO> selectPnishList(String p_name) {
		List<PnishVO> list = a_p002DAO.selectPnishList(p_name);
		return list;
	}
}
