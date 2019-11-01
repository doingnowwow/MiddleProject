package kr.or.ddit.vo;

import java.io.Serializable;

public class BuyVO implements Serializable {
  private int buy_no; //구매번호
  private int buy_mileage; // 사용한 마일리지
  private boolean buy_state; // 환불 상태
  private int buy_price; // 금액
  private int mem_no; // 회원번호 
  private String buy_check; // 결제여부

  @Override
	public String toString() {
		return "BuyVO [buy_no=" + buy_no
						+ ", buy_mileage=" + buy_mileage
						+ ", buy_state=" + buy_state
						+ ", buy_price=" + buy_price
						+ ", mem_no=" + mem_no
						+ ", buy_check=" + buy_check + "]";		
	
		  }
		
		public int getBuy_no() {
			return buy_no;
		}
		
		public void setBuy_no(int buy_no) {
			this.buy_no = buy_no;
		}
		
		public int getBuy_mileage() {
			return buy_mileage;
		}
		
		public void setBuy_mileage(int buy_mileage) {
			this.buy_mileage = buy_mileage;
		}
		
		public boolean isBuy_state() {
			return buy_state;
		}
		
		public void setBuy_state(boolean buy_state) {
			this.buy_state = buy_state;
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
		
}
