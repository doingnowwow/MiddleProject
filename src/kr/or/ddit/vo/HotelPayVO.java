package kr.or.ddit.vo;

import java.io.Serializable;

public class HotelPayVO implements Serializable{
	private String ho_pay_no; //결제번호
	private String ho_pay_card; //카드명
	private String ho_pay_account; //계좌번호
	private String ho_pay_date; //결제일
	private String ho_pay_bank; //거래은행
	private int book_no; //호텔예약번호

	
	@Override
	public String toString() {
		return "PayVO [ho_pay_no=" + ho_pay_no
						+ ", ho_pay_card=" + ho_pay_card
						+ ", ho_pay_account=" + ho_pay_account
						+ ", ho_pay_date=" + ho_pay_date
						+ ", ho_pay_bank=" + ho_pay_bank
						+ ", book_no=" + book_no + "]";
						
		
	}


	public String getHo_pay_no() {
		return ho_pay_no;
	}


	public void setHo_pay_no(String ho_pay_no) {
		this.ho_pay_no = ho_pay_no;
	}


	public String getHo_pay_card() {
		return ho_pay_card;
	}


	public void setHo_pay_card(String ho_pay_card) {
		this.ho_pay_card = ho_pay_card;
	}


	public String getHo_pay_account() {
		return ho_pay_account;
	}


	public void setHo_pay_account(String ho_pay_account) {
		this.ho_pay_account = ho_pay_account;
	}


	public String getHo_pay_date() {
		return ho_pay_date;
	}


	public void setHo_pay_date(String ho_pay_date) {
		this.ho_pay_date = ho_pay_date;
	}


	public String getHo_pay_bank() {
		return ho_pay_bank;
	}


	public void setHo_pay_bank(String ho_pay_bank) {
		this.ho_pay_bank = ho_pay_bank;
	}


	public int getBook_no() {
		return book_no;
	}


	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}

	

	
}
