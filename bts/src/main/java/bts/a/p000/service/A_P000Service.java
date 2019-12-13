package bts.a.p000.service;

import java.util.Map;

public interface A_P000Service {
	public int countMember() throws Exception;
	public int countContact() throws Exception;
	public int countReport() throws Exception;
	public long countAnswer() throws Exception;
	public Map<String, String> countWrite() throws Exception;
}
