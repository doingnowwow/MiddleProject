package kr.or.ddit.vo;

import java.io.Serializable;

public class ShoppingVO implements Serializable {

	private String img; // 상품이미지

	private int prod_id; // 상품번호
	private String prod_name; // 상품명
	private int prod_cost; // 단가
	private int amount; // 수량
	private int sum; // prod_cost * amount 단가*수량

	public ShoppingVO() {
		super();
	}

	public ShoppingVO(String img, int prod_id, String prod_name, int prod_cost, int amount, int sum) {
		super();
		this.img = img;
		this.prod_id = prod_id;
		this.prod_name = prod_name;
		this.prod_cost = prod_cost;
		this.amount = amount;
		this.sum = sum;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getProd_id() {
		return prod_id;
	}

	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public int getProd_cost() {
		return prod_cost;
	}

	public void setProd_cost(int prod_cost) {
		this.prod_cost = prod_cost;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "ProdVO [prod_id=" + prod_id + ", prod_name=" + prod_name + ", prod_cost=" + prod_cost + ", amount="
				+ amount + ", sum=" + sum + "]";
	}
}
