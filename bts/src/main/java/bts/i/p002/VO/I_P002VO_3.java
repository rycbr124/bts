package bts.i.p002.VO;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("i_p002VO_3")
public class I_P002VO_3 {
	private String plan_no;
	private String tag_name;
	public String getPlan_no() {
		return plan_no;
	}
	public void setPlan_no(String plan_no) {
		this.plan_no = plan_no;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}


	
}
