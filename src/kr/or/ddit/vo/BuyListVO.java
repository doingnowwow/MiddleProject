package kr.or.ddit.vo;

import java.io.Serializable;

public class BuyListVO implements Serializable{
	private int list_no; //구매정보이력번호
	private int buy_price; //금액
	private int mem_no; //회원번호
	private String buy_check; //결제확인
	private int buy_no;//구매번호
	
	 @Override
		public String toString() {
			return "BuyListVO [list_no=" + list_no
							+ ", buy_price=" + buy_price
							+ ", mem_no=" + mem_no
							+ ", buy_check=" + buy_check
							+ ", buy_no=" + buy_no + "]";
			  }

	public int getList_no() {
		return list_no;
	}

	public void setList_no(int list_no) {
		this.list_no = list_no;
	}

	public int getBuy_price() {
		return buy_price;
	}

	public void setBuy_price(int buy_price) {
		this.buy_price = buy_price;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public String getBuy_check() {
		return buy_check;
	}

	public void setBuy_check(String buy_check) {
		this.buy_check = buy_check;
	}

	public int getBuy_no() {
		return buy_no;
	}

	public void setBuy_no(int buy_no) {
		this.buy_no = buy_no;
	}
	
}
