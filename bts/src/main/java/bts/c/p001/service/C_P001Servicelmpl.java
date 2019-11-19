package bts.c.p001.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.b.p001.VO.B_P001VO;
import bts.c.p001.DAO.C_P001DAO;
import bts.c.p001.vo.C_P001VO;

@Service("c_p001Service")
public class C_P001Servicelmpl implements C_P001Service{
	@Autowired
	C_P001DAO c_p001DAO;
	@Autowired
	B_P001VO d001vo;
	
	
	@Override
	public void updateMember(B_P001VO d001vo) throws Exception {
		c_p001DAO.updateMember(d001vo);
	}
	
	@Override
	public void secession(B_P001VO d001vo, HttpSession session) {
		c_p001DAO.secession(d001vo,session);
		
	}

	@Override
	public void updateimage(B_P001VO d001vo) {
		c_p001DAO.updateImage(d001vo);
		
	}
	
	@Override
	public String passCheck(Map<String,String> searchData) {
		String result = c_p001DAO.passCheck(searchData);
		return result;
	}

	public List<C_P001VO> selectInclnList(){
		List<C_P001VO> list = c_p001DAO.selectInclnList();
		return list;
	}

	
	
	
	
}
