package han.gyoul.shop.member.dto;

import org.springframework.stereotype.Repository;

@Repository
public class MemberDTO {
	private int mb_no;
	private String mb_id;
	private String mb_pw;
	private String mb_nickname;
	private String mb_name;
	private String mb_bday;
	private String mb_sex;
	private String mb_address;
	private String mb_mobile;
	private String mb_email;
	private String mb_grade;
	private int mb_zipcode; 
	public int getMb_no() {
		return mb_no;
	}
	public void setMb_no(int mb_no) {
		this.mb_no = mb_no;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMb_pw() {
		return mb_pw;
	}
	public void setMb_pw(String mb_pw) {
		this.mb_pw = mb_pw;
	}
	public String getMb_nickname() {
		return mb_nickname;
	}
	public void setMb_nickname(String mb_nickname) {
		this.mb_nickname = mb_nickname;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getMb_bday() {
		return mb_bday;
	}
	public void setMb_bday(String mb_bday) {
		this.mb_bday = mb_bday;
	}
	public String getMb_address() {
		return mb_address;
	}
	public void setMb_address(String mb_address) {
		this.mb_address = mb_address;
	}
	public String getMb_mobile() {
		return mb_mobile;
	}
	public void setMb_mobile(String mb_mobile) {
		this.mb_mobile = mb_mobile;
	}
	public String getMb_email() {
		return mb_email;
	}
	public void setMb_email(String mb_email) {
		this.mb_email = mb_email;
	}
	public String getMb_grade() {
		return mb_grade;
	}
	public void setMb_grade(String mb_grade) {
		this.mb_grade = mb_grade;
	}
	public String getMb_sex() {
		return mb_sex;
	}
	public void setMb_sex(String mb_sex) {
		this.mb_sex = mb_sex;
	}
	public int getMb_zipcode() {
		return mb_zipcode;
	}
	public void setMb_zipcode(int mb_zipcode) {
		this.mb_zipcode = mb_zipcode;
	}
	@Override
	public String toString() {
		return "MemberDTO [mb_no=" + mb_no + ", mb_id=" + mb_id + ", mb_pw=" + mb_pw + ", mb_nickname=" + mb_nickname
				+ ", mb_name=" + mb_name + ", mb_bday=" + mb_bday + ", mb_sex=" + mb_sex + ", mb_address=" + mb_address
				+ ", mb_mobile=" + mb_mobile + ", mb_email=" + mb_email + ", mb_grade=" + mb_grade + ", mb_zipcode="
				+ mb_zipcode + "]";
	}
	
	
	
	
	
}
