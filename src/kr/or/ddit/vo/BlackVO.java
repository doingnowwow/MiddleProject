package kr.or.ddit.vo;

import java.io.Serializable;

public class BlackVO implements Serializable{
	private int mem_no; //회원번호
	private int black_cnt; //파양횟수
	private String mem_email;
	private String mem_tel;
	private String mem_id;
	
	public BlackVO() {
		
	}
	
	@Override
	public String toString() {
		return "BlackVO [mem_no=" + mem_no + ", black_cnt=" + black_cnt + "]";
	}
	
	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_tel() {
		return mem_tel;
	}

	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public int getBlack_cnt() {
		return black_cnt;
	}

	public void setBlack_cnt(int black_cnt) {
		this.black_cnt = black_cnt;
	}
	
	
	
	
}
