package kr.or.ddit.vo;
import java.io.Serializable;

/**
 * 커뮤니티 게시판 VO입니다!!
 * 필요하면 복사해가서 필요한 변수들만 사용하시면 됩니다!!
 * @author PC23
 *
 */
public class BoardVO implements Serializable{
	private int bd_no;			//게시글 번호 not null pk
	private String bd_title; 	//제목 not null
	private String bd_content;	//내용 not null
	private String bd_date; 	//작성일 not null
	private int bd_open;		//조회수
	private int bd_up;			//추천수
	private int bd_wrn_num;		//신고
	private String mem_id;		//회원아이디 not null fk
	
	public BoardVO() {
		
	}
	
	public BoardVO(int bd_no, String bd_title, String bd_content, String bd_date, int bd_open, int bd_up,
			int bd_wrn_num, String mem_id) {
		super();
		this.bd_no = bd_no;
		this.bd_title = bd_title;
		this.bd_content = bd_content;
		this.bd_date = bd_date;
		this.bd_open = bd_open;
		this.bd_up = bd_up;
		this.bd_wrn_num = bd_wrn_num;
		this.mem_id = mem_id; //작성자
	}
	
	public int getBd_no() {
		return bd_no;
	}

	public void setBd_no(int bd_no) {
		this.bd_no = bd_no;
	}

	public String getBd_title() {
		return bd_title;
	}

	public void setBd_title(String bd_title) {
		this.bd_title = bd_title;
	}

	public String getBd_content() {
		return bd_content;
	}

	public void setBd_content(String bd_content) {
		this.bd_content = bd_content;
	}

	public String getBd_date() {
		return bd_date;
	}

	public void setBd_date(String bd_date) {
		this.bd_date = bd_date;
	}

	public int getBd_open() {
		return bd_open;
	}

	public void setBd_open(int bd_open) {
		this.bd_open = bd_open;
	}

	public int getBd_up() {
		return bd_up;
	}

	public void setBd_up(int bd_up) {
		this.bd_up = bd_up;
	}

	public int getBd_wrn_num() {
		return bd_wrn_num;
	}

	public void setBd_wrn_num(int bd_wrn_num) {
		this.bd_wrn_num = bd_wrn_num;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
}
