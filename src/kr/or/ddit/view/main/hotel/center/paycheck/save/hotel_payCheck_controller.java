package kr.or.ddit.view.main.hotel.center.paycheck.save;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.book.BookService;
import kr.or.ddit.service.com.ComService;
import kr.or.ddit.service.hotel.HotelService;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.BookingInfoVO;
import kr.or.ddit.vo.ComVO;
import kr.or.ddit.vo.HotelVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PointVO;
import kr.or.ddit.vo.ResBookVO;

//승재 백업용 저장
public class hotel_payCheck_controller implements Initializable {

	@FXML ImageView hotel_Rimg;
	@FXML Text hotel_Rname;
	@FXML Text hotel_Raddr;
	@FXML TextField save_mileage;
	@FXML TextField hotelUse_mileage;
	@FXML TextField res_maileage;
	@FXML Text reserv_info;
	@FXML Text hotelreserv_respay;
	@FXML Button reservButton;
	@FXML CheckBox chk_saveMaileage;
	@FXML Label status;
	
	//결제 상세정보
	@FXML TextField hotel_name;
	@FXML TextField hotel_addr;
	@FXML TextField hotel_tel;
	@FXML TextField hotel_email;
	@FXML TextField reserv_dogNum;
	@FXML TextField reserv_contents;
	@FXML RadioButton credit;
	@FXML RadioButton bank;
	

	// 네트워크 관련
	private Registry reg;
	HotelService cs_hotel;
	ComService cs_com;
	BookService cs_book;
	
	
	// 가져오기용 VO
	BookVO book = LoginSession.bookssion;
	MemberVO memInfo = LoginSession.session;
	HotelVO hotel = new HotelVO();
	List<HotelVO> hotelInfo;
	ComVO com = new ComVO();
	ComVO comInfo;
	
	// 저장용 VO
	BookVO saveBook = new BookVO();
	BookingInfoVO saveBookInfo = new BookingInfoVO();
	PointVO savePoint = new PointVO();
	int saveHotelReserv_no; // 저장용 호텔번호
	
	// 호텔 내용 삽입
	int hotelNum; // 검색용
	String hotelName;
	String add1;
	String add2;
	String hotelAddr;
	String hotelTel;
	String hotelEmail;
	
	// 마일리지 내용 삽입
	int mileage; //현재 마일리지
	String Mileagetxt;
	
	String use_Mileagetxt; // 사용 마일리지
	int use_Mileage; 
	
	int save_Mileage; // 저장될 최종 마일리지
	int insertMileage; // 적립금
	int res_insertMileage; //최종 적립 마일리지
	String save_Mileagetxt;
	String saveDate;
	
	
	// 가격 내용 삽입
	int hotelPrice;
	
	int res_hotelPrice;
	String res_hotelPricetxt;
	
	// 예약 기간
	String sDate;
	String eDate;
	
	// 요청사항
	String dogNumtxt; // 예약견 수
	int dogNum; 
	String request;
	
	// 결제여부
	String book_check;
	
	// 마일리지 사용 여부
	Boolean checkMileage;
	
	// 결제완료 화면으로 넘기기위한 객체 생성
	ResBookVO save_resBook = LoginSession.resBookssion;
	


	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		checkMileage = false;
		
		System.out.println(
				LoginSession.session.getMem_no() + ", "
				+ LoginSession.session.getMem_id()+ ", "
				+ LoginSession.session.getMem_pw()+ ", "
				+ LoginSession.session.getMem_name()+ ", "
				+ LoginSession.session.getMem_tel()+ ", "
				+ LoginSession.session.getMem_addr1()+ ", "
				+ LoginSession.session.getMem_addr2()+ ", "
				+ LoginSession.session.getMem_point()+ ", "
				+ LoginSession.session.getMem_level()
				);
		
		
		hotelNum = book.getHotel_no();
		hotelPrice = book.getBook_price();
		sDate = book.getBook_checkin();
		eDate = book.getBook_checkout();
		
		

		//DB연결
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			cs_hotel = (HotelService) reg.lookup("hotelService");
			cs_com = (ComService) reg.lookup("comservice");
			cs_book = (BookService) reg.lookup("bookService");
//			cs_Book = (BookService) reg.lookup("bookService");
//			 이제부터는 불러온 객체의 메서드를 호출해서 사용할 수 있다.
			//hotelInfo = cs_hotel.hoNoSearch(hotel);
			saveHotelReserv_no = cs_book.selectMaxBook_no() + 1;
			//saveHotelReserv_no = cs
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		System.out.println(saveHotelReserv_no + "숫자불렀다");
		
		
		
		/**
		 * 상단부 기능  -- 이미지 및 주소
		 */
		
		//상단부 호텔 이미지
		Image setimg = new Image(getClass().getResourceAsStream("/chkimg/web-20180101142045553930-1560x1397.png"));
		hotel_Rimg.setImage(setimg); //이미지 표출
		
		//상단부 호텔기본정보
		hotel_Rname.setText(hotelName); // 이름 표출
		hotel_Raddr.setText(hotelAddr); // 주소 표출
		
		//상단부 마일리지 정리
		save_mileage.setText(Mileagetxt);
		
		

		//사용 마일리지 입력에 따른 이벤트
		hotelUse_mileage.setOnKeyReleased(e->{
			//동적인 정보 저장
			use_Mileagetxt = hotelUse_mileage.getText().trim();
			use_Mileage = Integer.parseInt(use_Mileagetxt); // 사용 마일리지
			
			if(mileage >= use_Mileage) {
				save_Mileage = sumMaileage(mileage, use_Mileage);
				save_Mileagetxt = Integer.toString(save_Mileage);
				res_maileage.setText(save_Mileagetxt.trim());
			}else {
				res_maileage.setText("0");
				status.setText("마일리지 허용치를 넘겼습니다.");
			}
		});
		
		
		//상단부 가격
		res_hotelPrice = hotelPrice;
		res_hotelPricetxt = Integer.toString(res_hotelPrice) + "원"; //bookvo book_price금액
		hotelreserv_respay.setText(res_hotelPricetxt);
		
		
		//체크버튼을 통한 마일리지 사용
		chk_saveMaileage.setOnAction(e->{
			if(chk_saveMaileage.isSelected()) {
				if(mileage >= use_Mileage) {
					res_hotelPrice = sumMaileage(hotelPrice, use_Mileage);
					res_hotelPricetxt = Integer.toString(res_hotelPrice) + "원"; //bookvo book_price금액
					hotelreserv_respay.setText(res_hotelPricetxt);
				}
				
			} else {
				res_hotelPrice = hotelPrice;
				res_hotelPricetxt = Integer.toString(res_hotelPrice) + "원";
				hotelreserv_respay.setText(res_hotelPricetxt);
			}
		});

	

		/**
		 * 상단부 기능  -- 기간
		 */
		//기간 계산 수식 만들어야함 -- 
		
		String reservInfo = "(" + sDate + "~" + eDate + ")";
		reserv_info.setText(reservInfo);
		
		//기타 사항

		
		/**
		 * 추가 예약 정보 삽입
		 */
		reserv_dogNum.setOnKeyReleased(e->{
			dogNumtxt = reserv_dogNum.getText().trim();
			dogNum = Integer.parseInt(dogNumtxt);
			
		});
		
		reserv_contents.setOnKeyReleased(e->{
			request = reserv_contents.getText().trim();
		});
		
		

		// BOOK 테이블 저장
		saveBook.setBook_no(saveHotelReserv_no); //호텔예약번호  1
		saveBook.setBook_person(memInfo.getCom_name()); ; //예약자 이름 1
		saveBook.setBook_date(sDate); //예약한날짜 1
		saveBook.setBook_checkin(sDate);// 체크인 날짜 1
		saveBook.setBook_checkout(eDate); // 체크아웃 날짜 1
		saveBook.setBook_state("예약"); //예약상태
		saveBook.setBook_check(book_check); // 결제여부 == 제작필요
		saveBook.setBook_dog_cnt(dogNum);// 예약강아지수 1
		saveBook.setBook_price(res_hotelPrice); //금액 1
		saveBook.setMem_no(memInfo.getMem_no()); //회원번호 1
		saveBook.setHotel_no(hotelNum); // 호텔번호 1
		saveBook.setHotel_name(hotelName);  // 호텔이름 1
		saveBook.setsDate(sDate); // 입실일 1
		saveBook.seteDate(eDate); // 퇴실일 1
		
		
		System.out.println("예약 정보"
				+saveBook.getBook_no()
				+saveBook.getBook_person()
				+saveBook.getBook_date()
				+saveBook.getBook_checkin()
				+saveBook.getBook_checkout()
				+saveBook.getBook_state()
				+saveBook.getBook_check()
				+saveBook.getBook_dog_cnt()
				+saveBook.getBook_price()
				+saveBook.getMem_no()
				+saveBook.getHotel_no()
				+saveBook.getHotel_name()
				+saveBook.getsDate()
				+saveBook.geteDate()
				);
		
		// BOOKInfo 테이블 저장
		saveBookInfo.setBook_no(saveHotelReserv_no); //호텔예약번호 1
		saveBookInfo.setHotel_no(hotelNum); //호텔번호 1
		saveBookInfo.setBooking_dognum(dogNum);; //애견수 1
		saveBookInfo.setBooking_request(request); //요청사항 1
		
		System.out.println("예약상세정보" +
				+saveBookInfo.getBook_no()+ ", " //호텔예약번호 1
				+saveBookInfo.getHotel_no()+ ", " //호텔번호 1
				+saveBookInfo.getBooking_dognum()+ ", " //애견수 1
				+saveBookInfo.getBooking_request() //요청사항 1	
				);
	
		
		//포인트 적립
		//사용 및 적립날짜 생성
		SimpleDateFormat sdate = new SimpleDateFormat("yyyy/MM/dd");
		Date sysdate = new Date();
		saveDate = sdate.format(sysdate);

		//마일리지 사용여부 판단 메소드
		if(checkMileage){
			savePoint.setPoint_money(0); // 적립금
			savePoint.setPoint_use(use_Mileage); //사용 적립금
			savePoint.setPoint_use_date(saveDate); // 사용날짜
			savePoint.setPoint_save_date(""); //적립날짜
			savePoint.setPoint_contents("호텔 예약 포인트 사용"); //포인트 적립 이유
		} else{
			insertMileage = res_hotelPrice / 10; // 적립금 계산
			res_insertMileage = insertMileage + mileage; // 적립금 최종
			
			savePoint.setPoint_money(insertMileage); // 적립금
			savePoint.setPoint_use(0); //사용 적립금
			savePoint.setPoint_use_date(""); // 사용날짜
			savePoint.setPoint_save_date(saveDate); //적립날짜
			savePoint.setPoint_contents("호텔 예약 포인트 적립"); //포인트 적립 이유
		}
		
		
		savePoint.setMem_no(memInfo.getMem_no()); // 회원번호
		savePoint.setPoint_mypoint(res_insertMileage); //총 포인트 내역
		savePoint.setGen_date(saveDate);
		
		System.out.println("예약 후 포인트 정보" +
				+savePoint.getMem_no() + ", "// 회원번호
				+savePoint.getPoint_mypoint()+ ", " //총 포인트 내역
				+savePoint.getGen_date()+ ", "
				+savePoint.getPoint_money()+ ", " // 적립금
				+savePoint.getPoint_use()+ ", " //사용 적립금
				+savePoint.getPoint_use_date()+ ", " // 사용날짜
				+savePoint.getPoint_save_date()+ ", " //적립날짜
				+savePoint.getPoint_contents() //포인트 적립 이유
				);
				
		
		save_resBook.setHimg("hotelImg");
		save_resBook.setrNo(saveHotelReserv_no);
		save_resBook.sethName(hotelName);
		save_resBook.sethAddr(hotelAddr);
		save_resBook.setcEmail(hotelEmail);
		save_resBook.setrPrice(res_hotelPrice);
		save_resBook.setrPriod(hotelAddr);
		
		
		
		 //예약버튼 기능
	 	reservButton.setOnAction(e->{

	 		try {
	 			//결제 정보 저장
	 			saveChk();//결제완료 확인창
	 			LoginSession.startMain.HotelpaycompleteLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	 		
	 	});
 	 

	}
	

	
	
	
	//마일리지 계산 메소드
	private int sumMaileage(int myMile, int useMile) {
		int sum;
		sum = myMile - useMile;
		return sum;
	}
	
	//저장확인
	private void saveChk() {
		Alert alertInformation = new Alert(AlertType.INFORMATION);
		alertInformation.setTitle("호텔예약 결제완료");
		alertInformation.setHeaderText("호텔예약 결제완료");
		alertInformation.setContentText("오늘도 이용해주셔서 감사합니다.");
		alertInformation.showAndWait(); // Alert창 보이기
	}
	

}
