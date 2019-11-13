package bts.c.p006.vo;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("c_p006FormVO")
@Scope("prototype")
public class C_P006FormVO {
	private String header;
	private HashMap<String,Object> body;
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public HashMap<String,Object> getBody() {
		return body;
	}
	public void setBody(HashMap<String,Object> body) {
		this.body = body;
	}
	
}
