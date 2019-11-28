package bts.e.p003.VO;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("e_p003VO_2")
@Scope("prototype")
public class E_P003VO_2 {
	private int article_no;
	private String tag_name;
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
	
	
}
