package bts.h.p002.VO;


import java.sql.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("h_p002VO")
@Scope("prototype")
public class H_P002VO {
	private int resve_no;
	private Date resve_date;
	private String amount;
	private String cancle_at;
	private String member_id;
	private String room_no;
	private String lodging_id;
	private String in_date;
	private String out_date;
	
	
	
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	public String getOut_date() {
		return out_date;
	}
	public void setOut_date(String out_date) {
		this.out_date = out_date;
	}
	public int getResve_no() {
		return resve_no;
	}
	public void setResve_no(int resve_no) {
		this.resve_no = resve_no;
	}
	public Date getResve_date() {
		return resve_date;
	}
	public void setResve_date(Date resve_date) {
		this.resve_date = resve_date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCancle_at() {
		return cancle_at;
	}
	public void setCancle_at(String cancle_at) {
		this.cancle_at = cancle_at;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getRoom_no() {
		return room_no;
	}
	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}
	public String getLodging_id() {
		return lodging_id;
	}
	public void setLodging_id(String lodging_id) {
		this.lodging_id = lodging_id;
	}

	
}
