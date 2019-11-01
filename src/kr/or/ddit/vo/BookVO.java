package kr.or.ddit.vo;

import java.io.Serializable;

public class BookVO implements Serializable {
  private int book_no; //호텔예약번호
  private String book_person ; //예약자 이름
  private String book_date; //예약한날짜
  private String book_checkin;// 체크인 날짜
  private String book_checkout; // 체크아웃 날짜
  private String book_state; //예약상태
  private String book_check; // 결제여부
  private int book_dog_cnt;// 예약강아지수
  private int book_price; //금액
  private int mem_no; //회원번호
  private int hotel_no ; // 호텔번호
  private String hotel_name;  // 호텔이름
  private String dateGu; // 해당날짜 기간구분
  private String sDate; // 입실일
  private String eDate; // 퇴실일
  private String book_ok_date; // 퇴실일
  private String use_yn; // 사용여부
  private String book_request; // 요청사항
  
  
  public String getBook_ok_date() {
	return book_ok_date;
  }

  public void setBook_ok_date(String book_ok_date) {
	this.book_ok_date = book_ok_date;
  }
  
  @Override
public String toString() {
	return "BookVO [book_no=" + book_no + ", book_person=" + book_person + ", book_date=" + book_date
			+ ", book_checkin=" + book_checkin + ", book_checkout=" + book_checkout + ", book_state=" + book_state
			+ ", book_check=" + book_check + ", book_dog_cnt=" + book_dog_cnt + ", book_price=" + book_price
			+ ", mem_no=" + mem_no + ", hotel_no=" + hotel_no + ", hotel_name= " + hotel_name + "]";
}

public BookVO() {
	  
  }
  
public BookVO(int book_no, String book_person, String book_date, String book_checkin, String book_checkout,
		String book_state, String book_check, int book_dog_cnt, int book_price, int mem_no, int hotel_no, String hotel_name,
		String dateGu, String sDate, String eDate) {
	super();
	this.book_no = book_no;
	this.book_person = book_person;
	this.book_date = book_date;
	this.book_checkin = book_checkin;
	this.book_checkout = book_checkout;
	this.book_state = book_state;
	this.book_check = book_check;
	this.book_dog_cnt = book_dog_cnt;
	this.book_price = book_price;
	this.mem_no = mem_no;
	this.hotel_no = hotel_no;
	this.hotel_name = hotel_name;
	this.dateGu = dateGu;
	this.sDate = sDate;
	this.eDate = eDate;
}




public String getDateGu() {
	return dateGu;
}

public void setDateGu(String dateGu) {
	this.dateGu = dateGu;
}

public String getsDate() {
	return sDate;
}

public void setsDate(String sDate) {
	this.sDate = sDate;
}

public String geteDate() {
	return eDate;
}

public void seteDate(String eDate) {
	this.eDate = eDate;
}

public String getHotel_name() {
	return hotel_name;
}

public void setHotel_name(String hotel_name) {
	if(hotel_name == null) {
		hotel_name = "";
	}
	this.hotel_name = hotel_name;
}

public int getBook_no() {
	return book_no;
}
public void setBook_no(int book_no) {
	this.book_no = book_no;
}
public String getBook_person() {
	return book_person;
}
public void setBook_person(String book_person) {
	this.book_person = book_person;
}
public String getBook_date() {
	return book_date;
}
public void setBook_date(String book_date) {
	this.book_date = book_date;
}
public String getBook_checkin() {
	return book_checkin;
}
public void setBook_checkin(String book_checkin) {
	this.book_checkin = book_checkin;
}
public String getBook_checkout() {
	return book_checkout;
}
public void setBook_checkout(String book_checkout) {
	this.book_checkout = book_checkout;
}
public String getBook_state() {
	return book_state;
}
public void setBook_state(String book_state) {
	this.book_state = book_state;
}
public String getBook_check() {
	return book_check;
}
public void setBook_check(String book_check) {
	this.book_check = book_check;
}
public int getBook_dog_cnt() {
	return book_dog_cnt;
}
public void setBook_dog_cnt(int book_dog_cnt) {
	this.book_dog_cnt = book_dog_cnt;
}
public int getBook_price() {
	return book_price;
}
public void setBook_price(int book_price) {
	this.book_price = book_price;
}
public int getMem_no() {
	return mem_no;
}
public void setMem_no(int mem_no) {
	this.mem_no = mem_no;
}
public int getHotel_no() {
	return hotel_no;
}
public void setHotel_no(int hotel_no) {
	this.hotel_no = hotel_no;
}

public String getUse_yn() {
	return use_yn;
}

public void setUse_yn(String use_yn) {
	this.use_yn = use_yn;
}

public String getBook_request() {
	return book_request;
}

public void setBook_request(String book_request) {
	this.book_request = book_request;
}
  

  
  

}