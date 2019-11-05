package bts.c.p006.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("c_p006VO")
public class C_P006VO {
	private int item_no;
	private String receiver;
	private String sender;
	private String contents;
	private Date writing_date;
	private String me_at;
	
	public C_P006VO(){
	
	}
	
	public String getMe_at() {
		return me_at;
	}

	public void setMe_at(String me_at) {
		this.me_at = me_at;
	}

	public int getItem_no() {
		return item_no;
	}
	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getWriting_date() {
		return writing_date;
	}
	public void setWriting_date(Date writing_date) {
		this.writing_date = writing_date;
	}		
	
	
}
