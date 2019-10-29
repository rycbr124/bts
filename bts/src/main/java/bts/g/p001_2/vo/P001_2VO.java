package bts.g.p001_2.vo;

import org.springframework.stereotype.Component;

@Component("p001_2VO")
public class P001_2VO {
	private String category_cd;
	private String name;
	private String upper_category_cd;
	
	public String getCategory_cd() {
		return category_cd;
	}
	public void setCategory_cd(String category_cd) {
		this.category_cd = category_cd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUpper_category_cd() {
		return upper_category_cd;
	}
	public void setUpper_category_cd(String upper_category_cd) {
		this.upper_category_cd = upper_category_cd;
	}
	
	

}
