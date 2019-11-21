package bts.d.p001_4.vo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("d_p001_4VO_2")
@Scope("prototype")
public class D_P001_4VO_2 {
	private String title;
	private String content_id;
	private String plan_no;
	private String day_no;
	private String plan_desc;
	private String detail_no;
	private String range_date;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent_id() {
		return content_id;
	}
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
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
	public String getPlan_desc() {
		return plan_desc;
	}
	public void setPlan_desc(String plan_desc) {
		this.plan_desc = plan_desc;
	}
	public String getDetail_no() {
		return detail_no;
	}
	public void setDetail_no(String detail_no) {
		this.detail_no = detail_no;
	}
	public String getRange_date() {
		return range_date;
	}
	public void setRange_date(String range_date) {
		this.range_date = range_date;
	}
	
}
