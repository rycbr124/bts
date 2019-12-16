package bts.h.p001.vo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("h_p001VO")
@Scope("prototype")
public class H_P001VO {
	private String lodging_id;
	private String description;
	private String address;
	private String resve_cnt;
	private String lodging_se;
	private String room_no;
	private String room_type;
	private String lodging_image;
	private String name;
	private String res_type;
	private int price;
	private int whlrs_no;
	private String rn;
	
	
	
	public String getLodging_id() {
		return lodging_id;
	}
	public void setLodging_id(String lodging_id) {
		this.lodging_id = lodging_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getResve_cnt() {
		return resve_cnt;
	}
	public void setResve_cnt(String resve_cnt) {
		this.resve_cnt = resve_cnt;
	}
	public String getLodging_se() {
		return lodging_se;
	}
	public void setLodging_se(String lodging_se) {
		this.lodging_se = lodging_se;
	}
	public String getRoom_no() {
		return room_no;
	}
	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public String getLodging_image() {
		return lodging_image;
	}
	public void setLodging_image(String lodging_image) {
		this.lodging_image = lodging_image;
	}
	public int getWhlrs_no() {
		return whlrs_no;
	}
	public void setWhlrs_no(int whlrs_no) {
		this.whlrs_no = whlrs_no;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRn() {
		return rn;
	}
	public void setRn(String rn) {
		this.rn = rn;
	}
	public String getRes_type() {
		return res_type;
	}
	public void setRes_type(String res_type) {
		this.res_type = res_type;
	}

	
	
	
}
