package kr.or.ddit.vo;

import java.io.Serializable;

public class ProdVO implements Serializable {
	private int prod_id; // 상품번호
	private String prod_name; // 상품명
	private String prod_gu; // 상품분류
	private int prod_sock; // 재고량
	private int prod_cost; // 단가
	private String prod_info; // 상품상세정보
	private String prod_com_info; // 상품회사정보
	private String prod_img; // 상품이미지
	private int mem_no; // 회원번호

	private String prod_datil_img; // 상품상세정보이미지

	public String getProd_datil_img() {
		return prod_datil_img;
	}

	public void setProd_datil_img(String prod_datil_img) {
		this.prod_datil_img = prod_datil_img;
	}

	private String mem_id;

	public String getProd_img() {
		return prod_img;
	}

	public void setProd_img(String prod_img) {
		this.prod_img = prod_img;
	}

	public ProdVO() {
		super();
	}

	public ProdVO(int prod_id, String prod_name, String prod_gu, int prod_sock, int prod_cost, String prod_info,
			String prod_com_info, String prod_img) {
		super();
		this.prod_id = prod_id;
		this.prod_name = prod_name;
		this.prod_gu = prod_gu;
		this.prod_sock = prod_sock;
		this.prod_cost = prod_cost;
		this.prod_info = prod_info;
		this.prod_com_info = prod_com_info;
		this.prod_img = prod_img;
	}

	@Override
	public String toString() {
		return "ProdVO [prod_id=" + prod_id + ", prod_name=" + prod_name + ", prod_gu=" + prod_gu + ", prod_sock="
				+ prod_sock + ", prod_cost=" + prod_cost + ", prod_info=" + prod_info + ", prod_com_info="
				+ prod_com_info + "]";
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
		if (prod_name == null) {
			prod_name = "";
		}
		this.prod_name = prod_name;
	}

	public String getProd_gu() {
		return prod_gu;
	}

	public void setProd_gu(String prod_gu) {
		this.prod_gu = prod_gu;
	}

	public int getProd_sock() {
		return prod_sock;
	}

	public void setProd_sock(int prod_sock) {
		this.prod_sock = prod_sock;
	}

	public int getProd_cost() {
		return prod_cost;
	}

	public void setProd_cost(int prod_cost) {
		this.prod_cost = prod_cost;
	}

	public String getProd_info() {
		return prod_info;
	}

	public void setProd_info(String prod_info) {
		this.prod_info = prod_info;
	}

	public String getProd_com_info() {
		return prod_com_info;
	}

	public void setProd_com_info(String prod_com_info) {
		this.prod_com_info = prod_com_info;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

}
