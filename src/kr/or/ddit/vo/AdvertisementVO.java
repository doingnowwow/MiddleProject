package kr.or.ddit.vo;

import java.io.Serializable;

public class AdvertisementVO implements Serializable {
	
	private String ad_no; //광고 코드
	private String ad_name; //광고 업체명
	private String ad_content; //광고 내용
	
	
	
	public AdvertisementVO(String ad_no, String ad_name, String ad_content) {
		super();
		this.ad_no = ad_no;
		this.ad_name = ad_name;
		this.ad_content = ad_content;
	}
	
	public AdvertisementVO() {

	}
	@Override
	public String toString() {
		return "AdvertisementVO [ad_no=" + ad_no + ", ad_name=" + ad_name
				+ ", ad_content=" + ad_content + "]";
	}
	public String getAd_no() {
		return ad_no;
	}
	public void setAd_no(String ad_no) {
		this.ad_no = ad_no;
	}
	public String getAd_name() {
		return ad_name;
	}
	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}
	public String getAd_content() {
		return ad_content;
	}
	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}
	
	
	
	
}
