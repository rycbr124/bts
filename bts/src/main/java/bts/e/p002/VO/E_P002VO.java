package bts.e.p002.VO;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("e_p002VO")
@Scope("prototype")
public class E_P002VO {
	private Date register_date;
	private int article_no;
	private String article_cd;
	private String member_id;
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	public int getArticle_no() {
		return article_no;
	}
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}
	public String getArticle_cd() {
		return article_cd;
	}
	public void setArticle_cd(String article_cd) {
		this.article_cd = article_cd;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	
}
