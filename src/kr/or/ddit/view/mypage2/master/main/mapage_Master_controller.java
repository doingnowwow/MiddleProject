package kr.or.ddit.view.mypage2.master.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.view.mypage2.master.center.info_main.Master_info_main_controller;

public class mapage_Master_controller implements Initializable {

	@FXML ScrollPane master_main_scroll;
	@FXML BorderPane masterInfoLinkRoot;
	@FXML Button info_main;
	@FXML Button memAdmin;
	@FXML Button blacklist;
	@FXML Button shopInfo;
	@FXML Button productInsert;
	@FXML Button shipping;
	@FXML Button advertiseBtn;
	
	
	//사업자 마이페이지 좌측메뉴 링크 기능
	static AnchorPane masterlinkRoot;
	@FXML Button memchart;


	@FXML //관리자 정보수정  // 다함
	public void myMasterInfoLink() throws IOException {
		FXMLLoader dd = new FXMLLoader(getClass().getResource("../center/info_main/Master_info_main.fxml"));
		masterlinkRoot = dd.load();
		Master_info_main_controller co = dd.getController();
		co.setMainCon(this);
		
//		masterlinkRoot = FXMLLoader.load(getClass().getResource("../center/info_main/Master_info_main.fxml"));
		masterInfoLinkRoot.setCenter(masterlinkRoot);
	}
	@FXML //관리자 정보수정 상세  // 다함
	public void myMasterInfoDetailLink() throws IOException {
		masterlinkRoot = FXMLLoader.load(getClass().getResource("../center/info_detail/Master_info_detail.fxml"));
		masterInfoLinkRoot.setCenter(masterlinkRoot);
	}
	@FXML //회원 및 사업장 관리  // 다함
	public void masterMemadminLink() throws IOException {
		masterlinkRoot = FXMLLoader.load(getClass().getResource("../center/memberadmin/Master_memberadmin.fxml"));
		masterInfoLinkRoot.setCenter(masterlinkRoot);
	}
	@FXML //블랙리스트 관리
	public void masterBlacklistLink() throws IOException {
		masterlinkRoot = FXMLLoader.load(getClass().getResource("../center/blacklist/Master_blacklist.fxml"));
		masterInfoLinkRoot.setCenter(masterlinkRoot);
	}
	@FXML //판매정보관리
	public void masterShopinfoLink() throws IOException {
		masterlinkRoot = FXMLLoader.load(getClass().getResource("../center/shopinfo/Master_shopinfo.fxml"));
		masterInfoLinkRoot.setCenter(masterlinkRoot);
	}
	@FXML //물품등록관리
	public void masterProductinsertLink() throws IOException {
		masterlinkRoot = FXMLLoader.load(getClass().getResource("../center/productinsert/Master_productInsert.fxml"));
		masterInfoLinkRoot.setCenter(masterlinkRoot);
	}

	
	@FXML //광고 관리
	public void masteradvertiseLink() throws IOException {
		masterlinkRoot = FXMLLoader.load(getClass().getResource("../center/advertisement/Advertise.fxml"));
		masterInfoLinkRoot.setCenter(masterlinkRoot);
	}
	
	@FXML //차트조회
	public void masterMemberChartLink() throws IOException {
		masterlinkRoot = FXMLLoader.load(getClass().getResource("../center/allmemberChart/MemberChart.fxml"));
		masterInfoLinkRoot.setCenter(masterlinkRoot);
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LoginSession.masterMain = this;
		
		try {
			myMasterInfoLink();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
		

}
