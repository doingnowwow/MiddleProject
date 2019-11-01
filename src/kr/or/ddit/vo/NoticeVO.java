package kr.or.ddit.vo;

import java.io.Serializable;

public class NoticeVO implements Serializable{
	private int notice_no; //글 번호
	private int admin_no; // 관리자 번호
	private String notice_date; //날짜
	private String notice_title; // 글 제목
	private String notice_content; // 글 내용
	
	
	
	public NoticeVO() {
	
	}
	
	@Override
	public String toString() {
		return "NoticeVO [notice_no=" + notice_no + ", admin_no=" + admin_no
				+ ", notice_date=" + notice_date + ", notice_title="
				+ notice_title + ", notice_content=" + notice_content + "]";
	}
	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}
	public int getAdmin_no() {
		return admin_no;
	}
	public void setAdmin_no(int admin_no) {
		this.admin_no = admin_no;
	}
	public String getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	
	
	
}
