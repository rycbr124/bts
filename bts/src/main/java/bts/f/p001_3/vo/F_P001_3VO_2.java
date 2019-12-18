package bts.f.p001_3.vo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("f_p001_3VO_2")
@Scope("prototype")
public class F_P001_3VO_2 {
	private String article_cd;
	private int article_no;
	private String tag_name;
	private int tag_order;
	
	public String getArticle_cd() {
		return article_cd;
	}
	public void setArticle_cd(String article_cd) {
		this.article_cd = article_cd;
	}
	public int getArticle_no() {
		return article_no;
	}
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	public int getTag_order() {
		return tag_order;
	}
	public void setTag_order(int tag_order) {
		this.tag_order = tag_order;
	}
	
	
	
}
