package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Date;

public class PointVO implements Serializable{
	
	private int point_no; // 포인트번호
	private int mem_no; // 회원번호
	private int point_money; // 적립금
	private int point_use; //사용 적립금
	private String point_use_date; // 사용날짜
	private String point_save_date; //적립날짜
	private String point_contents; //포인트 적립 이유
	private int point_mypoint; //총 포인트 내역
	private String gen_date; //총날짜
	private Date use_date;
	private Date save_date;

	
	
	public int getPoint_no() {
		return point_no;
	}
	public void setPoint_no(int point_no) {
		this.point_no = point_no;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public int getPoint_money() {
		return point_money;
	}
	public void setPoint_money(int point_money) {
		this.point_money = point_money;
	}
	public int getPoint_use() {
		return point_use;
	}
	public void setPoint_use(int point_use) {
		this.point_use = point_use;
	}
	public String getPoint_use_date() {
		return point_use_date;
	}
	public void setPoint_use_date(String point_use_date) {
		this.point_use_date = point_use_date;
	}
	public String getPoint_save_date() {
		return point_save_date;
	}
	public void setPoint_save_date(String point_save_date) {
		this.point_save_date = point_save_date;
	}
	public String getPoint_contents() {
		return point_contents;
	}
	public void setPoint_contents(String point_contents) {
		this.point_contents = point_contents;
	}
	public int getPoint_mypoint() {
		return point_mypoint;
	}
	public void setPoint_mypoint(int point_mypoint) {
		this.point_mypoint = point_mypoint;
	}
	public String getGen_date() {
		return gen_date;
	}
	public void setGen_date(String gen_date) {
		this.gen_date = gen_date;
	}
	
	public Date getUse_date() {
		return use_date;
	}
	public void setUse_date(Date use_date) {
		this.use_date = use_date;
	}
	public Date getSave_date() {
		return save_date;
	}
	public void setSave_date(Date save_date) {
		this.save_date = save_date;
	}
	@Override
	public String toString() {
		return "PointVO ["+ "point_no=" + point_no
						+ "mem_no=" + mem_no
						+ ", point_money=" + point_money
						+ ", point_use=" + point_use
						+ ", point_use_date=" + point_use_date
						+ ", point_save_date=" + point_save_date + "]";
	}






	
	
	
	
}
