package bts.a.p002.service;

import java.util.List;

import bts.common.report.vo.PnishVO;

public interface A_P002Service {

	List<PnishVO> selectPnishList(String p_name);

}
