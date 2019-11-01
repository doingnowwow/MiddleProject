package kr.or.ddit.view.mypage2.hotel.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.view.mypage2.hotel.center.cominfo.myhotel_center_cominfo_controller;

public class hotelmypage_main_controller implements Initializable {

	@FXML
	ScrollPane main_scroll;
	@FXML
	BorderPane main_boder;

	// left
	@FXML
	ImageView left;
	@FXML
	ImageView hotelimg;
	@FXML
	Button com_info;
	@FXML 
	Button hotel_detail ;
	@FXML
	Button hotel_book;
	@FXML
	Button dogsale_insert;
	@FXML
	Button dogsale_status;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		LoginSession.hotelMain = this;
		
		try {
			myHotelInfoLink();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 사업자 마이페이지 좌측메뉴 링크 기능zzzz
	static AnchorPane linkRoot;
	static ScrollPane scrollRoot;

	@FXML // 사업자 정보수정 이동
	public void myHotelInfoLink() throws IOException {

/*		FXMLLoader detailLink = new FXMLLoader(getClass().getResource("../center/cominfo/hotelmypage_cominfo.fxml"));
		linkRoot = detailLink.load();
		myhotel_center_cominfo_controller maincontroller = detailLink.getController();
		maincontroller.setMain(this);*/

		linkRoot = FXMLLoader.load(getClass().getResource("../center/cominfo/hotelmypage_cominfo.fxml"));
		main_boder.setCenter(linkRoot);

	}

	@FXML // 사업자 정보수정 상세로 이동
	public void myHotelInfoLinkDetail() throws IOException {
		linkRoot = FXMLLoader.load(getClass().getResource("../center/cominfo_detail/hotelmypage_center_cominfo_detail.fxml"));
		main_boder.setCenter(linkRoot);
	}

	//새로만든메서드
	//사업자 호텔 등록 페이지로 이동 
	@FXML
	public void hotelManager() throws IOException {
		scrollRoot = FXMLLoader.load(getClass().getResource("../center/manage/hotel_manage_scroll.fxml"));
		main_boder.setCenter(scrollRoot);
	}
	
	
	@FXML // 호텔 예약현황
	public void hotelReservLink() throws IOException {
		linkRoot = FXMLLoader.load(getClass().getResource("../center/reserv/hotel_center_reserv.fxml"));
		main_boder.setCenter(linkRoot);
	}

	@FXML // 분양 등록관리
	public void salesInsertLink() throws IOException {
		linkRoot = FXMLLoader.load(getClass().getResource("../center/insertSalse/mypage_hotel_center_insertSalse.fxml"));
		main_boder.setCenter(linkRoot);
	}

	@FXML // 분양 신청현황
	public void salesReservLink() throws IOException {
		linkRoot = FXMLLoader.load(getClass().getResource("../center/requeSelse/mypage_hotel_center_requeSalse.fxml"));
		main_boder.setCenter(linkRoot);
	}

}
