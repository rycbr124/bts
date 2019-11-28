package bts.a.p001.vo;

import org.springframework.stereotype.Component;

@Component("a_p001VO")
public class A_P001VO {
	private String incln_cd;
	private String name;
	private String group_name;
	private String group_desc;
	public String getIncln_cd() {
		return incln_cd;
	}
	public void setIncln_cd(String incln_cd) {
		this.incln_cd = incln_cd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getGroup_desc() {
		return group_desc;
	}
	public void setGroup_desc(String group_desc) {
		this.group_desc = group_desc;
	}
	
	

}
