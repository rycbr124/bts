package bts.c.p006.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import bts.b.p001.VO.B_P001VO;
import bts.c.p006.vo.C_P006VO;

public interface C_P006Service {
	public List<B_P001VO> selectMemberList(String id) throws DataAccessException;
	public List<C_P006VO> selectMessageList(C_P006VO c_p001vo) throws DataAccessException;
}
