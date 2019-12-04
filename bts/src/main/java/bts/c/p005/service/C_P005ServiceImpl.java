package bts.c.p005.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.c.p005.dao.C_P005DAO;
import bts.g.p001_2.vo.G_P001_2VO;

@Service("c_p005Service")
public class C_P005ServiceImpl implements C_P005Service{
	@Autowired
	C_P005DAO c_p005DAO;
	
	@Override
	public List<G_P001_2VO> searchWishlist(String member_id) throws Exception {
		return c_p005DAO.searchWishlist(member_id);
	}

}
