package bts.i.p002.VO;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("i_p002VO_1")
public class I_P002VO_1 {
	private String plan_no;
	private String member_id;
	private String title;
	private String register_date;
	private String person_se; 
	private String range_date;
	private String open_val;
	private String rownum;
	
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	public String getOpen_val() {
		return open_val;
	}
	public void setOpen_val(String open_val) {
		this.open_val = open_val;
	}
	public String getPlan_no() {
		return plan_no;
	}
	public void setPlan_no(String plan_no) {
		this.plan_no = plan_no;
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

	
	
}
