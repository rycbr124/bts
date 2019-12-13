package bts.c.p001.vo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("c_p001vo")
@Scope("prototype")
public class C_P001VO {
	private String member_id;
	private String incln_cd;
	private String group_name;
	private String name;
	private String group_desc;
	private String icon_col;
	private String icon_bla;
	
	
	public String getGroup_desc() {
		return group_desc;
	}
	public void setGroup_desc(String group_desc) {
		this.group_desc = group_desc;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getIncln_cd() {
		return incln_cd;
	}
	public void setIncln_cd(String incln_cd) {
		this.incln_cd = incln_cd;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon_col() {
		return icon_col;
	}
	public void setIcon_col(String icon_col) {
		this.icon_col = icon_col;
	}
	public String getIcon_bla() {
		return icon_bla;
	}
	public void setIcon_bla(String icon_bla) {
		this.icon_bla = icon_bla;
	}

	
}
