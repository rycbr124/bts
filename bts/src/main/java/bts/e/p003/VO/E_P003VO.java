package bts.e.p003.VO;

import java.sql.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("e_p003VO")
@Scope("prototype")
public class E_P003VO {
	private int article_no;
	private int article_cd;
	private String acc_title;
	private String gender;
	private String age;
	private String traffic;
	private String whlrs_no;
	private String member_id;
	private Date register_date;
	private String content;
	private String thumb;
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	private List<String> tag_list;
	
	public List<String> getTag_list() {
		return tag_list;
	}
	public void setTag_list(List<String> tag_list) {
		this.tag_list = tag_list;
	}
	public int getArticle_no() {
		return article_no;
	}
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}
	public int getArticle_cd() {
		return article_cd;
	}
	public void setArticle_cd(int article_cd) {
		this.article_cd = article_cd;
	}
	public String getAcc_title() {
		return acc_title;
	}
	public void setAcc_title(String acc_title) {
		this.acc_title = acc_title;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	public String getWhlrs_no() {
		return whlrs_no;
	}
	public void setWhlrs_no(String whlrs_no) {
		this.whlrs_no = whlrs_no;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
