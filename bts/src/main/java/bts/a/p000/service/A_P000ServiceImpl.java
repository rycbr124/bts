package bts.a.p000.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.a.p000.dao.A_P000DAO;

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

}
