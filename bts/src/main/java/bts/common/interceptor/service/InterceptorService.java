package bts.common.interceptor.service;

import bts.a.p002.vo.A_P002VO_1;

public interface InterceptorService {

	boolean hasAuth(String member_id, String menu_url) throws Exception;

	A_P002VO_1 checkPnishAt(String member_id);

}
