package bts.c.p002.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bts.c.p002.DAO.C_P002DAO;
import bts.c.p007.VO.C_P007VO;
import bts.e.p001.VO.PagingVO;
import bts.e.p001.VO.PagingVO2;
import bts.e.p001.VO.PagingVO3;
import bts.f.p001_3.dao.F_P001_3DAO;
import bts.f.p001_3.vo.F_P001_3VO;
import bts.i.p002.VO.I_P002VO_1;

@Service("c_p002Service")
public class C_P002ServiceImpl implements C_P002Service{

	@Autowired
	C_P002DAO c_p002DAO;
	
	@Autowired
	F_P001_3DAO f_p001_3DAO;
	
	@Override
	public List<F_P001_3VO> reviewList(PagingVO pagingVO) throws Exception {

		return c_p002DAO.reviewList(pagingVO);
	}

	@Override
	public Integer reviewListCount(String member_id) throws Exception {
		return c_p002DAO.reviewListCount(member_id);
	}

	@Override
	public List<I_P002VO_1> planList(PagingVO3 pagingVO3) throws Exception {
		return c_p002DAO.planList(pagingVO3);
	}

	@Override
	public Integer planListCount(String member_id) throws Exception {
		return c_p002DAO.planListCount(member_id);
	}

	@Override
	public List<C_P007VO> accList(PagingVO2 pagingVO2) throws Exception {
		return c_p002DAO.accList(pagingVO2);
	}

	@Override
	public Integer accListCount(String member_id) throws Exception {
		return c_p002DAO.accListCount(member_id);
	}

}
