package bts.c.p003.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("c_p003vo")
public class C_P003VO {
	private int resve_no;
	private String lodging_image;
	private String name;
	private Date resve_date;
	private String amount;
	private String in_date;
	private String out_date;
	private String room_type;
	private String member_id;
	private String cancle_at;
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
	public int getResve_no() {
		return resve_no;
	}
	public void setResve_no(int resve_no) {
		this.resve_no = resve_no;
	}
	public String getLodging_image() {
		return lodging_image;
	}
	public void setLodging_image(String lodging_image) {
		this.lodging_image = lodging_image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	

}
