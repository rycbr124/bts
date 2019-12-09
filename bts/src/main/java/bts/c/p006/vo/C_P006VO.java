package bts.c.p006.vo;

import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("c_p006VO")
@Scope("prototype")
public class C_P006VO {
	private int item_no;
	private String sender;
	private String receiver;
	private String contents;
	private Timestamp writing_date;
	private String accompany_at;
	private String me_at;
	
	public C_P006VO(){
	
	}
	
	public String getAccompany_at() {
		return accompany_at;
	}

	public void setAccompany_at(String accompany_at) {
		this.accompany_at = accompany_at;
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
	public Timestamp getWriting_date() {
		return writing_date;
	}
	public void setWriting_date(Timestamp writing_date) {
		this.writing_date = writing_date;
	}		
	
	
}
