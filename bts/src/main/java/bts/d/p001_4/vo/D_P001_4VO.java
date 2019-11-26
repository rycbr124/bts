package bts.d.p001_4.vo;

import org.springframework.stereotype.Component;

@Component("d_p001_4VO")
public class D_P001_4VO {
	private String rownum;
	private String member_id;
	private String title;
	private String plan_no;
	private String register_date;
	private String person_se;
	private String range_date;
	private String view_cnt;
	private String open_val;
	
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPlan_no() {
		return plan_no;
	}
	public void setPlan_no(String plan_no) {
		this.plan_no = plan_no;
	}
	public String getRegister_date() {
		return register_date;
	}
	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}
	public String getPerson_se() {
		return person_se;
	}
	public void setPerson_se(String person_se) {
		this.person_se = person_se;
	}
	public String getRange_date() {
		return range_date;
	}
	public void setRange_date(String range_date) {
		this.range_date = range_date;
	}
	public String getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(String view_cnt) {
		this.view_cnt = view_cnt;
	}
	public String getOpen_val() {
		return open_val;
	}
	public void setOpen_val(String open_val) {
		this.open_val = open_val;
	}
	
}
