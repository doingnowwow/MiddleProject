package kr.or.ddit.vo;

import java.io.Serializable;

public class MemberSearchVO implements Serializable{
	
	private String com_id;
	private String com_email;
	private String com_name;
	private String hotel_name;                                       
	private String com_tel;                                     
	private String hotel_tel;                                             
	private String hotel_intro;                                            
	              
	private String mem_id;                                        
	private String mem_email;                                        
	private String mem_name;                                       
	private String mem_tel;                                       
	private String mem_addr1;                                      
	private String mem_addr2;                                      
	private int mem_point;                                       
	private String mem_level;                                        
	
	private String mem_dog_name;
	private String mem_dog_gu;
	private String mem_dog_gender;
	private String mem_dog_size;
	private String mem_dog_bir;
	private String mem_dog_intro;
	
	
	public MemberSearchVO() {
		super();
	}
	
	public MemberSearchVO(String com_id, String com_email, String com_name,
			String hotel_name, String com_tel, String hotel_tel,
			String hotel_intro, String mem_id, String mem_email, String mem_name,
			String mem_tel, String mem_addr1, String mem_addr2, int mem_point,
			String mem_level, String mem_dog_name, String mem_dog_gu,
			String mem_dog_gender, String mem_dog_size, String mem_dog_bir,
			String mem_dog_intro) {
		super();
		this.com_id = com_id;
		this.com_email = com_email;
		this.com_name = com_name;
		this.hotel_name = hotel_name;
		this.com_tel = com_tel;
		this.hotel_tel = hotel_tel;
		this.hotel_intro = hotel_intro;
		this.mem_id = mem_id;
		this.mem_email = mem_email;
		this.mem_name = mem_name;
		this.mem_tel = mem_tel;
		this.mem_addr1 = mem_addr1;
		this.mem_addr2 = mem_addr2;
		this.mem_point = mem_point;
		this.mem_level = mem_level;
		this.mem_dog_name = mem_dog_name;
		this.mem_dog_gu = mem_dog_gu;
		this.mem_dog_gender = mem_dog_gender;
		this.mem_dog_size = mem_dog_size;
		this.mem_dog_bir = mem_dog_bir;
		this.mem_dog_intro = mem_dog_intro;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getCom_email() {
		return com_email;
	}
	public void setCom_email(String com_email) {
		this.com_email = com_email;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public String getCom_tel() {
		return com_tel;
	}
	public void setCom_tel(String com_tel) {
		this.com_tel = com_tel;
	}
	public String getHotel_tel() {
		return hotel_tel;
	}
	public void setHotel_tel(String hotel_tel) {
		this.hotel_tel = hotel_tel;
	}
	public String getHotel_intro() {
		return hotel_intro;
	}
	public void setHotel_intro(String hotel_intro) {
		this.hotel_intro = hotel_intro;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getMem_addr1() {
		return mem_addr1;
	}
	public void setMem_addr1(String mem_addr1) {
		this.mem_addr1 = mem_addr1;
	}
	public String getMem_addr2() {
		return mem_addr2;
	}
	public void setMem_addr2(String mem_addr2) {
		this.mem_addr2 = mem_addr2;
	}
	public int getMem_point() {
		return mem_point;
	}
	public void setMem_point(int mem_point) {
		this.mem_point = mem_point;
	}
	public String getMem_level() {
		return mem_level;
	}
	public void setMem_level(String mem_level) {
		this.mem_level = mem_level;
	}
	public String getMem_dog_name() {
		return mem_dog_name;
	}
	public void setMem_dog_name(String mem_dog_name) {
		this.mem_dog_name = mem_dog_name;
	}
	public String getMem_dog_gu() {
		return mem_dog_gu;
	}
	public void setMem_dog_gu(String mem_dog_gu) {
		this.mem_dog_gu = mem_dog_gu;
	}
	public String getMem_dog_gender() {
		return mem_dog_gender;
	}
	public void setMem_dog_gender(String mem_dog_gender) {
		this.mem_dog_gender = mem_dog_gender;
	}
	public String getMem_dog_size() {
		return mem_dog_size;
	}
	public void setMem_dog_size(String mem_dog_size) {
		this.mem_dog_size = mem_dog_size;
	}
	public String getMem_dog_bir() {
		return mem_dog_bir;
	}
	public void setMem_dog_bir(String mem_dog_bir) {
		this.mem_dog_bir = mem_dog_bir;
	}
	public String getMem_dog_intro() {
		return mem_dog_intro;
	}
	public void setMem_dog_intro(String mem_dog_intro) {
		this.mem_dog_intro = mem_dog_intro;
	}
	
	
	
	
	
}
