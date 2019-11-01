package kr.or.ddit.vo;

import java.io.Serializable;

import javafx.scene.image.ImageView;

public class LikeVO implements Serializable {

	private int mem_no; //회원번호
	private int dog_no;
	private int com_no; //사업자 회원번호
	private int dlike_no;
	private String dog_gu; // 애견 종
	private String dog_name; // 애견이름
	private String dog_gender; // 애견성별
	private String com_id;// 사업자 아이디 
	private ImageView dog_picture; // 강아지 사진
	

	@Override
	public String toString() {
		return "LikeVO [mem_no=" + mem_no + ", dog_no=" + dog_no + ", com_no=" + com_no + ", dog_gu=" + dog_gu
				+ ", dog_name=" + dog_name + ", dog_gender=" + dog_gender + ", com_id=" + com_id + ", dog_picture="
				+ dog_picture + "]";
	}

	public LikeVO(int mem_no, String dog_no, int dlike_no, int com_no, String dog_gu, String dog_name, String dog_gender,
			String com_id, ImageView dog_picture) {
		super();
		this.mem_no = mem_no;
		this.com_no = com_no;
		this.dlike_no = dlike_no;
		this.dog_gu = dog_gu;
		this.dog_name = dog_name;
		this.dog_gender = dog_gender;
		this.com_id = com_id;
		this.dog_picture = dog_picture;
	}
	
	public LikeVO() {
		
	}

	public int getDlike_no() {
		return dlike_no;
	}

	public void setDlike_no(int dlike_no) {
		this.dlike_no = dlike_no;
	}

	public ImageView getDog_picture() {
		return dog_picture;
	}
	
	
	
	
	
	public void setDog_picture(ImageView dog_picture) {
		this.dog_picture = dog_picture;
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

	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	

	public int getDog_no() {
		return dog_no;
	}

	public void setDog_no(int dog_no) {
		this.dog_no = dog_no;
	}

	public int getCom_no() {
		return com_no;
	}

	public void setCom_no(int com_no) {
		this.com_no = com_no;
	}


	
	
	
}
