package bts.b.p003.Service;

import javax.servlet.http.HttpServletResponse;


public interface B_P003Service {
	public String find_id(HttpServletResponse response, String email) throws Exception;
}
