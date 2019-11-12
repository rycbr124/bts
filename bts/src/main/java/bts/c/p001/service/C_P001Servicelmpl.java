package bts.c.p001.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bts.b.p001.VO.B_P001VO;
import bts.c.p001.DAO.C_P001DAO;

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
	public String passCheck(String password) {
		String result = c_p001DAO.passCheck(d001vo.getPassword());
		return result;
	}

	

	
	
	
	
}
