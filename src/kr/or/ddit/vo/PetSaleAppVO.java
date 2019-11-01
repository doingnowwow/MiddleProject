package kr.or.ddit.vo;

import java.io.Serializable;

public class PetSaleAppVO implements Serializable{

	private String sale_no; // 분양번호
	private int dog_no; // 애견번호
	private int com_no;  // 사업자 번호
	private int seq_no; // 분양 신청 순번
	private int mem_no; // 회원 번호
	private String mem_name; // 회원 이름
	private String dog_gu; // 애견 종
	private String mem_tel; // 회원 전화번호
	private String dog_name; // 애견 이름
	private String accp_yn; // 승인 여부
	private String req_date; // 신청 날짜
	
	public PetSaleAppVO(String sale_no, int dog_no, int com_no, int seq_no,
			int mem_no, String mem_name, String dog_gu, String mem_tel, String dog_name,
			String accp_yn, String req_date) {
		super();
		this.sale_no = sale_no;
		this.dog_no = dog_no;
		this.com_no = com_no;
		this.seq_no = seq_no;
		this.mem_no = mem_no;
		this.mem_name = mem_name;
		this.dog_gu = dog_gu;
		this.mem_tel = mem_tel;
		this.dog_name = dog_name;
		this.accp_yn = accp_yn;
		this.req_date = req_date;
	}
	public PetSaleAppVO() {
		super();
	}
	@Override
	public String toString() {
		return "PetSaleAppVO [sale_no=" + sale_no + ", dog_no=" + dog_no
				+ ", com_no=" + com_no + ", seq_no=" + seq_no + ", mem_no="
				+ mem_no + ", mem_name=" + mem_name + ", dog_gu=" + dog_gu + ", mem_tel=" + mem_tel
				+ ", dog_name=" + dog_name + ", accp_yn=" + accp_yn
				+ ", req_date=" + req_date + "]";
	}
	public String getSale_no() {
		return sale_no;
	}
	public void setSale_no(String sale_no) {
		this.sale_no = sale_no;
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
	public int getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(int seq_no) {
		this.seq_no = seq_no;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getDog_gu() {
		return dog_gu;
	}
	public void setDog_gu(String dog_gu) {
		this.dog_gu = dog_gu;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getDog_name() {
		return dog_name;
	}
	public void setDog_name(String dog_name) {
		this.dog_name = dog_name;
	}
	public String getAccp_yn() {
		return accp_yn;
	}
	public void setAccp_yn(String accp_yn) {
		this.accp_yn = accp_yn;
	}
	public String getReq_date() {
		return req_date;
	}
	public void setReq_date(String req_date) {
		this.req_date = req_date;
	}
	
	

}