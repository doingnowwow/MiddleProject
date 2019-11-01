package kr.or.ddit.vo;

import java.io.Serializable;


public class DogSaleAddVO implements Serializable{

	private String dog_no; //애견번호
	private int com_no; //사업자번호
	private String dog_gu; // 애견 종
	private String dog_name; // 애견이름
	private String dog_gender; // 애견성별
	private String dog_size; // 애견 종류
	private String dog_intro; // 애견상세정보
	private String dog_bir; // 애견생일
	private String com_id;// 사업자아이디
	private String dog_picture; //애견사진
	private String chkdog_sale ;//애견분양여부
	private String title; // 제목
	private String dog_infodetail; // 더 자세한 상세정보
	
	
	
	
	
	


	
	
	

	@Override
	public String toString() {
		return "DogSaleAddVO [dog_no=" + dog_no + ", com_no=" + com_no + ", dog_gu=" + dog_gu + ", dog_name=" + dog_name
				+ ", dog_gender=" + dog_gender + ", dog_size=" + dog_size + ", dog_intro=" + dog_intro + ", dog_bir="
				+ dog_bir + ", com_id=" + com_id + ", dog_picture=" + dog_picture + ", chkdog_sale=" + chkdog_sale
				+ ", title=" + title + ", dog_infodetail=" + dog_infodetail + "]";
	}




	public DogSaleAddVO(String dog_no, int com_no, String dog_gu, String dog_name, String dog_gender, String dog_size,
			String dog_intro, String dog_bir, String com_id, String dog_picture, String chkdog_sale, String title,
			String dog_infodetail) {
		super();
		this.dog_no = dog_no;
		this.com_no = com_no;
		this.dog_gu = dog_gu;
		this.dog_name = dog_name;
		this.dog_gender = dog_gender;
		this.dog_size = dog_size;
		this.dog_intro = dog_intro;
		this.dog_bir = dog_bir;
		this.com_id = com_id;
		this.dog_picture = dog_picture;
		this.chkdog_sale = chkdog_sale;
		this.title = title;
		this.dog_infodetail = dog_infodetail;
	}
	
	public DogSaleAddVO(String dog_gu, String dog_name, String dog_gender, String dog_size,
			String dog_intro, String dog_bir,
			String dog_infodetail) {
		super();
		
		this.dog_gu = dog_gu;
		this.dog_name = dog_name;
		this.dog_gender = dog_gender;
		this.dog_size = dog_size;
		this.dog_intro = dog_intro;
		this.dog_bir = dog_bir;
		
		this.dog_infodetail = dog_infodetail;
	}


	public String getDog_infodetail() {
		return dog_infodetail;
	}




	public void setDog_infodetail(String dog_infodetail) {
		this.dog_infodetail = dog_infodetail;
	}




	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DogSaleAddVO() {
		
	}
	




	public String getDog_no() {
		return dog_no;
	}
	public void setDog_no(String dog_no) {
		this.dog_no = dog_no;
	}
	public int getCom_no() {
		return com_no;
	}
	public void setCom_no(int com_no) {
		this.com_no = com_no;
	}
	public String getDog_gu() {
		return dog_gu;
	}
	public void setDog_gu(String dog_gu) {
		this.dog_gu = dog_gu;
	}
	public String getDog_name() {
		return dog_name;
	}
	public void setDog_name(String dog_name) {
		this.dog_name = dog_name;
	}
	public String getDog_gender() {
		return dog_gender;
	}
	public void setDog_gender(String dog_gender) {
		this.dog_gender = dog_gender;
	}
	public String getDog_size() {
		return dog_size;
	}
	public void setDog_size(String dog_size) {
		this.dog_size = dog_size;
	}
	public String getDog_intro() {
		return dog_intro;
	}
	public void setDog_intro(String dog_intro) {
		this.dog_intro = dog_intro;
	}
	public String getDog_bir() {
		return dog_bir;
	}
	public void setDog_bir(String dog_bir) {
		this.dog_bir = dog_bir;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getDog_picture() {
		return dog_picture;
	}
	public void setDog_picture(String dog_picture) {
		this.dog_picture = dog_picture;
	}
	public String getChkdog_sale() {
		return chkdog_sale;
	}
	public void setChkdog_sale(String chkdog_sale) {
		this.chkdog_sale = chkdog_sale;
	}
	
	
}
