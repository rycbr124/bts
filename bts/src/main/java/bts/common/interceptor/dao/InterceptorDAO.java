package bts.common.interceptor.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import bts.a.p002.vo.A_P002VO_1;

public interface InterceptorDAO {

	String selectMenuCd(String menu_url) throws DataAccessException;

	int selectAuthCount(Map<String, String> searchMap) throws DataAccessException;

	A_P002VO_1 checkPnishAt(String member_id);

}
