package bts.common.interceptor.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface InterceptorDAO {

	String selectMenuCd(String menu_url) throws DataAccessException;

	int selectAuthCount(Map<String, String> searchMap) throws DataAccessException;

}
