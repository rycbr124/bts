package bts.d.p001_4.vo;

import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component("d_p001_4VO_5")
public class D_P001_4VO_5 {
	private int answer_no;
	private int article_no;
	private String article_cd;
	private String answer_desc;
	private String member_id;
	private String profile_image;
	private String member_type;
	private Timestamp register_date;
	public int getAnswer_no() {
		return answer_no;
	}
	public void setAnswer_no(int answer_no) {
		this.answer_no = answer_no;
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
	public String getAnswer_desc() {
		return answer_desc;
	}
	public void setAnswer_desc(String answer_desc) {
		this.answer_desc = answer_desc;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	public String getMember_type() {
		return member_type;
	}
	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}
	public Timestamp getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Timestamp register_date) {
		this.register_date = register_date;
	}

	
}
