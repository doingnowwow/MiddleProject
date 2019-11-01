package kr.or.ddit.vo;

import java.io.Serializable;

public class ComReservCheckVO implements Serializable{

	private int com_no;  // 사업주 번호
	private int hotel_no;  // 호텔 번호
	private int book_no;  // 예약 번호
	private String hotel_name;  // 호텔이름
	private String book_person;  // 예약자 이름
	private String book_checkin; // 체크인 날짜
	private int book_dog_cnt;  // 예약하는 개 숫자
	private String book_price; // 예약 금액
	private String book_date;  // 예약한 날짜
	private String mem_tel; // 회원 전화번호
	private String book_state; // 예약 상태
	private String choice_date; // 선택한 날짜
	
	
	@Override
	public String toString() {
		return "ComReservCheckVO [com_no=" + com_no + ", hotel_no=" + hotel_no
				+ ", book_no=" + book_no + ", hotel_name=" + hotel_name
				+ ", book_person=" + book_person + ", book_checkin="
				+ book_checkin + ", book_price=" + book_price + ", book_date="
				+ book_date + ", mem_tel=" + mem_tel + ", book_state="
				+ book_state + ",choice_date=" + choice_date + "]";
	}
	
	public ComReservCheckVO(int com_no, int hotel_no, int book_no,
			String hotel_name, String book_person, String book_checkin,
			String book_price, String book_date, String mem_tel,
			String book_state, String choice_date) {
		super();
		this.com_no = com_no;
		this.hotel_no = hotel_no;
		this.book_no = book_no;
		this.hotel_name = hotel_name;
		this.book_person = book_person;
		this.book_checkin = book_checkin;
		this.book_price = book_price;
		this.book_date = book_date;
		this.mem_tel = mem_tel;
		this.book_state = book_state;
		this.choice_date = choice_date;
	}
	
	public ComReservCheckVO() {
		super();
	}
	
	public int getBook_dog_cnt() {
		return book_dog_cnt;
	}

	public void setBook_dog_cnt(int book_dog_cnt) {
		this.book_dog_cnt = book_dog_cnt;
	}

	public String getChoice_date() {
		return choice_date;
	}

	public void setChoice_date(String choice_date) {
		this.choice_date = choice_date;
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
	public int getBook_no() {
		return book_no;
	}
	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public String getBook_person() {
		return book_person;
	}
	public void setBook_person(String book_person) {
		this.book_person = book_person;
	}
	public String getBook_checkin() {
		return book_checkin;
	}
	public void setBook_checkin(String book_checkin) {
		this.book_checkin = book_checkin;
	}
	public String getBook_price() {
		return book_price;
	}
	public void setBook_price(String book_price) {
		this.book_price = book_price;
	}
	public String getBook_date() {
		return book_date;
	}
	public void setBook_date(String book_date) {
		this.book_date = book_date;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getBook_state() {
		return book_state;
	}
	public void setBook_state(String book_state) {
		this.book_state = book_state;
	}
	
	
	
	
	
}
