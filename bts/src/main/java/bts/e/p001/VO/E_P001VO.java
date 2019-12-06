package bts.e.p001.VO;

import java.sql.Date;

import org.springframework.stereotype.Component;

//ACCOMPANY_LIST
@Component("e_p001VO")
public class E_P001VO {
	private String gender;
	private int age;
	private String traffic;
	private String whlrs_no;
	private int article_no;
	private String article_cd;
	private String acc_title;
	private String tag;
	private String member_id;
	private String content;
	private String incln_cd;
	private Date register_date;
	private int viewcnt;
	private String thumb;
	private String nick_name;
	
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTraffic() {
		return traffic;
	}
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
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
	public String getAcc_title() {
		return acc_title;
	}
	public void setAcc_title(String acc_title) {
		this.acc_title = acc_title;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIncln_cd() {
		return incln_cd;
	}
	public void setIncln_cd(String incln_cd) {
		this.incln_cd = incln_cd;
	}
	
	@Override
	public String toString() {
		return "E_P001VO [article_no= "+ article_no + ", acc_title= "+ acc_title +" ,register_date= "+register_date +", member_id= "+ member_id+"]";
	}
	
}
