package kr.or.ddit.view.main.hotel.center.hoteldetailpage;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.horeview.HoReviewService;
import kr.or.ddit.service.hotel.HotelService;
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.HotelReviewVO;
import kr.or.ddit.vo.HotelVO;
import kr.or.ddit.vo.MemberVO;



public class main_hotel_detailpage_controller implements Initializable{
	
	@FXML Label ho_telLabel;
	@FXML Label ho_addrLabel;
	@FXML Label ho_nameLabel;
	@FXML Label moneyLabel;
	@FXML ImageView hotel_img;
	@FXML TextArea hotelInfoTA;
	@FXML TextArea roomInfoTA;
	
	@FXML DatePicker bookst_date;
	@FXML DatePicker bookend_date;
	@FXML Button book_btn;
	@FXML Button dateConfirmBtn;
	
	@FXML TableView<HotelReviewVO> review_tb;
	@FXML TableColumn<HotelReviewVO, String> contentCol;
	@FXML TableColumn<HotelReviewVO, String> writerCol;
	@FXML TableColumn<HotelReviewVO, String> writeDateCol;
	@FXML TableColumn<HotelReviewVO, String> starCol;
	@FXML TextField reviewTF;
	@FXML ChoiceBox<String> restarCB;
	@FXML Button reviewBtn;
	@FXML Pagination pnation;
	@FXML ImageView mapImg; //호텔지도.이미지
	
	// 선택한 예약 정보 저장 -- 승재
	BookVO saveBookInfo = LoginSession.bookssion;

	// 댓글 
	private ArrayList<HotelReviewVO> hrlist = new ArrayList<>();
	private HotelReviewVO rvo = new HotelReviewVO();

	// 페이지네이션
	private int from, to, itemsForpages;
	private ObservableList<HotelReviewVO> allTableData, currentPageData;
	int totPageCnt = 0;
	
	
	private Registry reg;
	private HotelService hs;
	private HoReviewService hrs;
	
	// 호텔 정보를 담는 리스트
	private static List<HotelVO> hotel_list  = new ArrayList<>();
	
	// 선택한 호텔 정보 저장
	HotelVO hocho;
	HotelVO hv;
	int hoNum = 0;
	
	// 가격 정보를 담는 놈 --승재
	int sum;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		moneyLabel.setText("");
		hocho = LoginSession.hosession;
		int hono = hocho.getHotel_no();
		HotelReviewVO hoho = new HotelReviewVO();
		hoho.setHotel_no(hono);
		MemberVO memin = LoginSession.session;
		int memno = memin.getMem_no();
		String memid = memin.getMem_id();
		
		// 서버 연결
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			hs = (HotelService) reg.lookup("hotelService");
			hrs = (HoReviewService) reg.lookup("hotelReview");
		}catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
		// 가져온 호텔 정보를 출력
		try {
			hoNum = hocho.getHotel_no();
//			hv.setHotel_no(hoNum);
			hotel_list = hs.getSelectedHotel(hocho);
		}catch(RemoteException e) {
			e.printStackTrace();
		}
		
		String hoName = hotel_list.get(0).getHotel_name();
		ho_nameLabel.setText(hoName);
		
		String ho_Tel = hotel_list.get(0).getHotel_tel();
		ho_telLabel.setText(ho_Tel);
		
		String ho_addr1 = hotel_list.get(0).getHotel_addr1();
		String ho_addr2 = hotel_list.get(0).getHotel_addr2();
		ho_addrLabel.setText(ho_addr1 + " / " + ho_addr2);
		
		String hoInfo = hotel_list.get(0).getHotel_intro();
		hotelInfoTA.setText(hoInfo);
		
		String hoimage = hotel_list.get(0).getImg();
		hotel_img.setImage(new Image(hoimage));
		
		String mapimage = hotel_list.get(0).getHotel_map();
		mapImg.setImage(new Image(mapimage));
		
		ObservableList<String> star = 
	            FXCollections.observableArrayList(
	                  "5","4","3","2","1"
	                  );
		restarCB.setItems(star);
		restarCB.setValue("5");
		
		
		// 댓글 나열하기
		try {
			hoNum = hocho.getHotel_no();
			rvo.setHotel_no(hoNum);
			hrlist = (ArrayList<HotelReviewVO>) hrs.selectReviewHotel(rvo);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		
		allTableData = FXCollections.observableArrayList(hrlist);
		
		allTableData.setAll(hrlist);
//		allTableData.add(rvo);
		
		review_tb.setItems(allTableData);
		
		contentCol.setCellValueFactory(new PropertyValueFactory<>("hore_content"));
		writerCol.setCellValueFactory(new PropertyValueFactory<>("hore_writer"));
		writeDateCol.setCellValueFactory(new PropertyValueFactory<>("hore_date"));
		starCol.setCellValueFactory(new PropertyValueFactory<>("hore_star"));
		
		itemsForpages = 6;
		
		totPageCnt = allTableData.size()%itemsForpages == 0
				? allTableData.size()/itemsForpages
				: allTableData.size()/itemsForpages + 1;
		
		pnation.setPageCount(totPageCnt);
		pnation.setPageFactory(this::createPage);
		
//		Date date = new Date();
//		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd
//		
//		
//		  = writeDateCol.getText();
		dateConfirmBtn.setOnAction(e -> {
			LocalDate bs = bookst_date.getValue();
			LocalDate be = bookend_date.getValue();
			
			String chin = formatChange(bs);
			String chout = formatChange(be);

			Period getMinus = Period.between(bs, be);
			
			int cnt = getMinus.getDays();
			int cost = hotel_list.get(0).getHotel_cost();
			
			sum = cnt * cost;
			
			
			
			moneyLabel.setText(sum + "");
			
		});
		
		String view_content = reviewTF.getText();
		reviewBtn.setOnAction(e -> {
			int hotelno = hotel_list.get(0).getHotel_no();
			String content = reviewTF.getText();
			String starre = restarCB.getValue();
			SimpleDateFormat format = new SimpleDateFormat ("yyyy/MM/dd");
			Date time = new Date();
			String sysdate = format.format(time);
					
//			allTableData.add(new HotelReviewVO(content, memid, sysdate));
			
			HotelReviewVO hr3 = new HotelReviewVO();
			hoNum = hocho.getHotel_no();
			hr3.setMem_no(memno);
			hr3.setHotel_no(hoNum);
			hr3.setHore_writer(memid);
			hr3.setHore_star(starre);
			if(reviewTF.getText() != null) {
				hr3.setHore_content(reviewTF.getText());
			}else {
				AlertMsg.caution("실패, 내용을 입력하세요!");
			}
			
			try {
				hoNum = hocho.getHotel_no();
				rvo.setHotel_no(hoNum);
				hrs.insertHotelReview(hr3);
				hrlist = (ArrayList<HotelReviewVO>) hrs.selectReviewHotel(rvo);
			}catch(RemoteException e3) {
				e3.printStackTrace();
			}
			allTableData = FXCollections.observableArrayList(hrlist);
			
			allTableData.setAll(hrlist);
			
			review_tb.setItems(allTableData);
			
			contentCol.setCellValueFactory(new PropertyValueFactory<>("hore_content"));
			writerCol.setCellValueFactory(new PropertyValueFactory<>("hore_writer"));
			writeDateCol.setCellValueFactory(new PropertyValueFactory<>("hore_date"));
			starCol.setCellValueFactory(new PropertyValueFactory<>("hore_star"));
			
			reviewTF.clear();
			
			itemsForpages = 6;
			
			totPageCnt = allTableData.size()%itemsForpages == 0
					? allTableData.size()/itemsForpages
					: allTableData.size()/itemsForpages + 1;
			
			pnation.setPageCount(totPageCnt);
			pnation.setPageFactory(this::createPage);
			
		});
				
		book_btn.setOnAction(e -> {
			// 여기에 결제 페이지로 넘어가게 만들어야함
			AlertMsg.confirm("결제페이지로 넘어갑니다^^");
			
			LocalDate bs = bookst_date.getValue();
			LocalDate be = bookend_date.getValue();
			String chin = formatChange(bs);
			String chout = formatChange(be);
			
			
			saveBookInfo.setBook_checkin(chin); // 체크인 날짜
			saveBookInfo.setBook_checkout(chout); // 체크아웃 날짜
			saveBookInfo.setBook_price(sum); //금액
			saveBookInfo.setHotel_no(hoNum); // 호텔번호

			try {
				LoginSession.startMain.HotelPaycheckLink();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		});
		
			 
	}

	// datepicker 포맷바꿔주기
	public String formatChange(LocalDate ld) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    	String rtn = ld.format(dtf);
    	return rtn;
	}
	// 페이지네이션용 메서드
	private Node createPage(int pageIndex) {
		from = pageIndex * itemsForpages;
		to = from + itemsForpages -1 ;
		review_tb.setItems(getTableViewData(from, to));
		
		return review_tb;
	}
	
	// 페이지네이션용 메서드
	private ObservableList<HotelReviewVO> getTableViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList();
		int totSize = allTableData.size();
		for( int i = from; i <= to && i < totSize; i++) {
			currentPageData.add(allTableData.get(i));
		}
		return currentPageData;
	}




}
