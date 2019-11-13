package bts.i.p002.VO;

import org.springframework.stereotype.Component;

@Component("i_p002VO_2")
public class I_P002VO_2 {
	private String plan_no;
	private String day_no;
	private String content_id;
	
	public String getPlan_no() {
		return plan_no;
	}
	public void setPlan_no(String plan_no) {
		this.plan_no = plan_no;
	}
	public String getDay_no() {
		return day_no;
	}
	public void setDay_no(String day_no) {
		this.day_no = day_no;
	}
	public String getContent_id() {
		return content_id;
	}
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
	
	
}
