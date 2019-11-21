package bts.d.p001_4.vo;

import org.springframework.stereotype.Component;

@Component("d_p001_4VO_4")
public class D_P001_4VO_4 {
	private String answer_no;
	private String answer_desc;
	private String article_no;
	private String member_id;
	private String article_cd;
	public String getAnswer_no() {
		return answer_no;
	}
	public void setAnswer_no(String answer_no) {
		this.answer_no = answer_no;
	}
	public String getAnswer_desc() {
		return answer_desc;
	}
	public void setAnswer_desc(String answer_desc) {
		this.answer_desc = answer_desc;
	}
	public String getArticle_no() {
		return article_no;
	}
	public void setArticle_no(String article_no) {
		this.article_no = article_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getArticle_cd() {
		return article_cd;
	}
	public void setArticle_cd(String article_cd) {
		this.article_cd = article_cd;
	}
	
}
