package bts.i.p002.VO;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("i_p002vo_1")
public class I_P002VO_1 {
	private String paln_no;
	private String member_id;
	private String title;
	private Date register_date;
	private String person_se; 
	private String start_date;
	private String end_date;
	public String getPaln_no() {
		return paln_no;
	}
	public void setPaln_no(String paln_no) {
		this.paln_no = paln_no;
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
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	public String getPerson_se() {
		return person_se;
	}
	public void setPerson_se(String person_se) {
		this.person_se = person_se;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	
}
