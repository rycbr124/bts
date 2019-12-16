package bts.f.p001_3.vo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("f_p001_3VO")
@Scope("prototype")
public class F_P001_3VO {
	private int article_no;
	private Timestamp register_date;
	private String title;
	private String contents;
	private String member_id;
	private String profile_image;
	private String member_type;	
	private String article_cd;
	private String thumbnail_img;
	private String refer_link;
	private String refer_title;
	private List<String> tag_list;

	public String getRefer_link() {
		return refer_link;
	}
	public void setRefer_link(String refer_link) {
		this.refer_link = refer_link;
	}
	public String getRefer_title() {
		return refer_title;
	}
	public void setRefer_title(String refer_title) {
		this.refer_title = refer_title;
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
	public Timestamp getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Timestamp register_date) {
		this.register_date = register_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
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
	public String getThumbnail_img() {
		return thumbnail_img;
	}
	public void setThumbnail_img(String thumbnail_img) {
		this.thumbnail_img = thumbnail_img;
	}
	
	
}
