package bts.c.p007.VO;

import java.sql.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("c_p007VO")
@Scope("prototype")
public class C_P007VO {
	private int article_no;
	private String acc_title;
	private String member_id;
	private Date register_date;
	public int getArticle_no() {
		return article_no;
	}
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}
	public String getAcc_title() {
		return acc_title;
	}
	public void setAcc_title(String acc_title) {
		this.acc_title = acc_title;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	
	
}
