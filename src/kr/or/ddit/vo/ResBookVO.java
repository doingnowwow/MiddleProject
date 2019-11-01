package kr.or.ddit.vo;

import java.io.Serializable;

import javafx.scene.image.Image;

public class ResBookVO implements Serializable {
	
	
	String himg; // 호텔 이미지
	String hName; // 호텔명
	String rPriod; // 예약 기간
	String hAddr; // 호텔 주소
	String cEmail; // 호텔 이메일
	int rPrice; // 최종 예약 가격
	int rNo; //예약번호
	
	public String getHimg() {
		return himg;
	}
	public void setHimg(String himg) {
		this.himg = himg;
	}
	public String gethName() {
		return hName;
	}
	public void sethName(String hName) {
		this.hName = hName;
	}
	public String getrPriod() {
		return rPriod;
	}
	public void setrPriod(String rPriod) {
		this.rPriod = rPriod;
	}
	public String gethAddr() {
		return hAddr;
	}
	public void sethAddr(String hAddr) {
		this.hAddr = hAddr;
	}
	public String getcEmail() {
		return cEmail;
	}
	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
	public int getrPrice() {
		return rPrice;
	}
	public void setrPrice(int rPrice) {
		this.rPrice = rPrice;
	}
	public int getrNo() {
		return rNo;
	}
	public void setrNo(int rNo) {
		this.rNo = rNo;
	}
	
	

}
