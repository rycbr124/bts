package bts.a.p002.vo;

import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("a_p002VO_1")
@Scope("prototype")
public class A_P002VO_1 {
	private Timestamp begin_date;
	private Timestamp end_date;
	private String member_id;
	private int report_no;
	private String pnish_desc;
	
	private String btn_report;
	private int pnish_type;
	private String day_cnt;
	
	public Timestamp getBegin_date() {
		return begin_date;
	}
	public void setBegin_date(Timestamp begin_date) {
		this.begin_date = begin_date;
	}
	public Timestamp getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getReport_no() {
		return report_no;
	}
	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}
	public String getPnish_desc() {
		return pnish_desc;
	}
	public void setPnish_desc(String pnish_desc) {
		this.pnish_desc = pnish_desc;
	}
	public String getBtn_report() {
		return btn_report;
	}
	public void setBtn_report(String btn_report) {
		this.btn_report = btn_report;
	}
	public int getPnish_type() {
		return pnish_type;
	}
	public void setPnish_type(int pnish_type) {
		this.pnish_type = pnish_type;
	}
	public String getDay_cnt() {
		return day_cnt;
	}
	public void setDay_cnt(String day_cnt) {
		this.day_cnt = day_cnt;
	}
	
}
