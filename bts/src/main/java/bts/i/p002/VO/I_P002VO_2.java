package bts.i.p002.VO;

import org.springframework.stereotype.Component;

@Component("i_p002vo_2")
public class I_P002VO_2 {
	private String plan_no;
	private String item_no;
	private String content_id;
	public String getPlan_no() {
		return plan_no;
	}
	public void setPlan_no(String plan_no) {
		this.plan_no = plan_no;
	}
	public String getItem_no() {
		return item_no;
	}
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	public String getContent_id() {
		return content_id;
	}
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
	
	
}
