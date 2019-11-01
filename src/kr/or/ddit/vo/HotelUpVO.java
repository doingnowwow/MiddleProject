package kr.or.ddit.vo;

import java.io.Serializable;

public class HotelUpVO implements Serializable{

	private String hotel_name; // 호텔 이름
	private String hotel_addr1; // 주소1
	private String hotel_addr2; // 주소2
	private String hotel_tel; // 전화번호
	private String img; // 사진
	private String hotel_intro; // 소개
	private int com_no; //사업자회원번호
	private int hotel_no;
	
	
	
	public HotelUpVO(String hotel_name, String hotel_addr1, String hotel_addr2, String hotel_tel, String img,
			String hotel_intro, int com_no, int hotel_no) {
		super();
		this.hotel_name = hotel_name;
		this.hotel_addr1 = hotel_addr1;
		this.hotel_addr2 = hotel_addr2;
		this.hotel_tel = hotel_tel;
		this.img = img;
		this.hotel_intro = hotel_intro;
		this.com_no = com_no;
		this.hotel_no = hotel_no;
	}
	
	
	
	@Override
	public String toString() {
		return "HotelUpVO [hotel_name=" + hotel_name + ", hotel_addr1=" + hotel_addr1 + ", hotel_addr2=" + hotel_addr2
				+ ", hotel_tel=" + hotel_tel + ", img=" + img + ", hotel_intro=" + hotel_intro + ", com_no="
				+ com_no + ", hotel_no=" + hotel_no + "]";
	}



	public HotelUpVO() {
		super();
	}


	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public String getHotel_addr1() {
		return hotel_addr1;
	}
	public void setHotel_addr1(String hotel_addr1) {
		this.hotel_addr1 = hotel_addr1;
	}
	public String getHotel_addr2() {
		return hotel_addr2;
	}
	public void setHotel_addr2(String hotel_addr2) {
		this.hotel_addr2 = hotel_addr2;
	}
	public String getHotel_tel() {
		return hotel_tel;
	}
	public void setHotel_tel(String hotel_tel) {
		this.hotel_tel = hotel_tel;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getHotel_intro() {
		return hotel_intro;
	}
	public void setHotel_intro(String hotel_intro) {
		this.hotel_intro = hotel_intro;
	}
	public int getCom_no() {
		return com_no;
	}
	public void setCom_no(int com_no) {
		this.com_no = com_no;
	}



	public int getHotel_no() {
		return hotel_no;
	}



	public void setHotel_no(int hotel_no) {
		this.hotel_no = hotel_no;
	}

	


	
}
