package bts.b.p001.VO;


public class KakaoVO {

	private String member_id;

	private String nick_name;

	private String profile_image;
	
	private String email;
	
	private String member_type;


	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}


	public String getMember_Id() {
		return member_id;
	}

	public void setMember_Id(String member_id) {
		this.member_id = member_id;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMember_type() {
		return member_type;
	}

	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}
	
	

	
}
