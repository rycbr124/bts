package bts.b.p001.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bts.b.p001.DAO.B_P001DAO;
import bts.b.p001.VO.B_P001VO;
import bts.b.p001.VO.KakaoVO;
import bts.b.p001.VO.NaverVO;

@Service("b_p001Service")
@Transactional(propagation = Propagation.REQUIRED)
public class B_P001ServiceImpl implements B_P001Service {
	
	@Autowired
	B_P001DAO d001DAO;

	@Override
	public void addMember(B_P001VO d001vo) throws Exception {
		d001DAO.insertNewMember(d001vo);

	}

	@Override
	public String overlapped(String id) throws Exception {
		return d001DAO.selectOverlappedID(id);
	}

	@Override
	public B_P001VO login(Map loginMap) throws Exception {		
		return d001DAO.login(loginMap);
	}

	@Override
	public void kakaoInsert(KakaoVO kakaoVO) throws Exception {

		d001DAO.kakaoNewMember(kakaoVO);
		
	}

	@Override
	public void naverInsert(NaverVO naverVO) throws Exception {
		d001DAO.naverNewMember(naverVO);
		
	}


}
