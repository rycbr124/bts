package bts.common.interceptor.service;

public interface InterceptorService {

	boolean hasAuth(String member_id, String menu_url) throws Exception;

}
