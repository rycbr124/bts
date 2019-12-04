package bts.a.p002.dao;

import java.util.List;

import bts.common.report.vo.PnishVO;

public interface A_P002DAO {

	List<PnishVO> selectPnishList(String p_name);

}
