package bts.c.p004.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

@Component("i_p004VO")
public class C_P004VO {
	private String contact_no;
	private String title;
	private String contents;
	private String answer_at;
	private String member_id;
	private String contact_date;
	private String contact_type;
	private String rownum;
	
	
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getContact_date() {
		return contact_date;
	}
	public void setContact_date(String contact_date) {
		this.contact_date = contact_date;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getAnswer_at() {
		return answer_at;
	}
	public void setAnswer_at(String answer_at) {
		this.answer_at = answer_at;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getContact_type() {
		return contact_type;
	}
	public void setContact_type(String contact_type) {
		this.contact_type = contact_type;
	}
	
	
	public String toString() {
        return String.format(contents);
    }
}
