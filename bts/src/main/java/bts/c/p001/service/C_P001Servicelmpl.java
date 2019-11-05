package bts.c.p001.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.b.p001.VO.B_P001VO;
import bts.c.p001.DAO.C_P001DAO;

@Service("c_p001Service")
public class C_P001Servicelmpl implements C_P001Service{
	@Autowired
	C_P001DAO c_p001DAO;
	
	@Override
	public void updateMember(B_P001VO d001vo) throws Exception {
		c_p001DAO.updateMember(d001vo);
	}
}
