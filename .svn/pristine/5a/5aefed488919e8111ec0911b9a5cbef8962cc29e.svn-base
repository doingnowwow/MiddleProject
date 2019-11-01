package kr.or.ddit.vo;

import java.io.Serializable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CartVO implements Serializable{


	private int mem_no; // 회원번호
	private int prod_id; //상품번호
 	private String cart_no; //구매날짜
	private int cart_qty; //수량
	private int rNum; // 글 번호
	private int cart_id;

	
	private String prod_name;
	private String prod_info;
	private String prod_gu_nm; //상품종류
	private int prod_total_cost; // 상품가격
	
	private BooleanProperty chk;
	
	public CartVO() {
		
	}
	
	public CartVO(int prod_id, String cart_no, int cart_qty, int mem_no, BooleanProperty chk) {
		super();
		this.prod_id = prod_id;
		this.cart_no = cart_no;
		this.cart_qty = cart_qty;
		this.mem_no = mem_no;
		this.chk = chk;
	}
	public int getProd_id() {
		return prod_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public String getCart_no() {
		return cart_no;
	}
	public void setCart_no(String cart_no) {
		this.cart_no = cart_no;
	}
    public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getProd_info() {
		return prod_info;
	}
	public void setProd_info(String prod_info) {
		this.prod_info = prod_info;
	}
	public String getProd_gu_nm() {
		return prod_gu_nm;
	}
	public void setProd_gu_nm(String prod_gu_nm) {
		this.prod_gu_nm = prod_gu_nm;
	}
	public int getProd_total_cost() {
		return prod_total_cost;
	}
	public void setProd_total_cost(int prod_total_cost) {
		this.prod_total_cost = prod_total_cost;
	}
	public int getCart_qty() {
		return cart_qty;
	}
	public void setCart_qty(int cart_qty) {
		this.cart_qty = cart_qty;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public int getrNum() {
		return rNum;
	}

	public void setrNum(int rNum) {
		this.rNum = rNum;
	}

	public BooleanProperty checkProperty() {
		return chk; 
	}
	
	public BooleanProperty getCheck() {
		return chk; 
	}
	
	public void setCheck(Boolean chk) {
	  this.chk = new SimpleBooleanProperty(chk);
	 }

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	
	
}
