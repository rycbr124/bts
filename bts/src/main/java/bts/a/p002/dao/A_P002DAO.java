package bts.a.p002.dao;

import java.util.List;
import java.util.Map;

import bts.common.report.vo.PnishVO;

public interface A_P002DAO {

	List<PnishVO> selectPnishList(String p_name);

	void insertPnish(Map<String, String> row);

	void updatePnish(Map<String, String> row);

	void deletePnish(Map<String, String> row);

}
