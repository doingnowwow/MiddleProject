package kr.or.ddit.vo;

import java.io.Serializable;

public class AdminVO implements Serializable {
	private int admin_no; //관리자번호
	private String admin_id; //아이디
	private String admin_pw; //비밀번호
	private String admin_addr1; //주소1
	private String admin_addr2; //주소2
	private String admin_email; //이메일
	private String admin_tel; //핸드폰번호
	
	
	@Override
	public String toString() {
		return "AdminVO [admin_no=" + admin_no
						+ ", admin_id=" + admin_id
						+ ", admin_pw=" + admin_pw
						+ ", admin_addr1=" + admin_addr1
						+ ", admin_addr2=" + admin_addr2
						+ ", admin_email=" + admin_email
						+ ", admin_tel=" + admin_tel + "]";
	
	}
	
	public AdminVO() {
		
	}

	public int getAdmin_no() {
		return admin_no;
	}

	public void setAdmin_no(int admin_no) {
		this.admin_no = admin_no;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_pw() {
		return admin_pw;
	}

	public void setAdmin_pw(String admin_pw) {
		this.admin_pw = admin_pw;
	}

	public String getAdmin_addr1() {
		return admin_addr1;
	}

	public void setAdmin_addr1(String admin_addr1) {
		this.admin_addr1 = admin_addr1;
	}

	public String getAdmin_addr2() {
		return admin_addr2;
	}

	public void setAdmin_addr2(String admin_addr2) {
		this.admin_addr2 = admin_addr2;
	}

	public String getAdmin_email() {
		return admin_email;
	}

	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}

	public String getAdmin_tel() {
		return admin_tel;
	}

	public void setAdmin_tel(String admin_tel) {
		this.admin_tel = admin_tel;
	}



	
	
	
	
	
}
