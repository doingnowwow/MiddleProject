package kr.or.ddit.view.mypage2.master.center.advertisement;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.service.advertisement.AdvertisementService;
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.vo.AdvertisementVO;

public class AdvertisementController implements Initializable{
	
	@FXML TableView<AdvertisementVO> adTable;
	@FXML TableColumn<AdvertisementVO, Integer> noCol;
	@FXML TableColumn<AdvertisementVO, String> comCol;
	@FXML TableColumn<AdvertisementVO, String> contentCol;
	
	@FXML TextField comTF;
	@FXML TextField contentTF;
	
	@FXML Button addBtn;
	@FXML Button updateBtn;
	@FXML Button deleteBtn;
	
	// 서버 연결
	private AdvertisementService ads;
	private Registry reg;
	
	// 광고 정보를 담는 리스트
	private static List<AdvertisementVO> list = new ArrayList<>();
	private ArrayList<AdvertisementVO> check_list = new ArrayList<>();
	
	private ObservableList<AdvertisementVO> AllTableData;
	private AdvertisementVO adv = new AdvertisementVO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			ads = (AdvertisementService) reg.lookup("adver");
			list = ads.showAllAd();
		}catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		noCol.setCellValueFactory(new PropertyValueFactory<>("ad_no"));
		comCol.setCellValueFactory(new PropertyValueFactory<>("ad_name"));
		contentCol.setCellValueFactory(new PropertyValueFactory<>("ad_content"));
		
		AllTableData = FXCollections.observableArrayList();
		AllTableData.setAll(list);
		adTable.setItems(AllTableData);
		
		// 선택한 줄의 데이터 얻기
		adTable.setOnMouseClicked(e -> {
			AdvertisementVO av = adTable.getSelectionModel().getSelectedItem();
			
			comTF.setText(av.getAd_name());
			contentTF.setText(av.getAd_content());
		});
		
		// 광고 등록
		addBtn.setOnAction(e -> {
			adv.setAd_name(comTF.getText().trim());
			adv.setAd_content(contentTF.getText().trim());
			
			int cnt = 0;
			
			try {
				cnt = ads.insertAd(adv);
			}catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			if(cnt > 0 ) {
				AlertMsg.info("광고 등록 완료");
			}else {
				AlertMsg.caution("광고 등록 실패");
			}
			
			noCol.setCellValueFactory(new PropertyValueFactory<>("ad_no"));
			comCol.setCellValueFactory(new PropertyValueFactory<>("ad_name"));
			contentCol.setCellValueFactory(new PropertyValueFactory<>("ad_content"));
			
			try {
				list = ads.showAllAd();
			}catch (RemoteException e3) {
				e3.printStackTrace();
			}
			
			AllTableData = FXCollections.observableArrayList();
			AllTableData.setAll(list);
			adTable.setItems(AllTableData);
			
		});
		
		// 광고 수정
		updateBtn.setOnAction(e -> {
			if(comTF.getText().isEmpty() || contentTF.getText().isEmpty()) {
				AlertMsg.caution("빈항목이 있습니다.");
				return;
			}
			
			AdvertisementVO adv2 = adTable.getSelectionModel().getSelectedItem();
			
			adv2.setAd_name(comTF.getText().trim());
			adv2.setAd_content(contentTF.getText().trim());
			
			int cnt = 0;
			
			try {
				cnt = ads.modifyAd(adv2);
			}catch (RemoteException e2) {
				e2.printStackTrace();
			}
			
			if( cnt != 0) {
				AlertMsg.info(comTF.getText().trim() + "회사의 광고 수정 완료!");
			}else {
				AlertMsg.caution(comTF.getText().trim() + "회사의 광고 수정 실패!");
			}
			
			noCol.setCellValueFactory(new PropertyValueFactory<>("ad_no"));
			comCol.setCellValueFactory(new PropertyValueFactory<>("ad_name"));
			contentCol.setCellValueFactory(new PropertyValueFactory<>("ad_content"));
			try {
				list = ads.showAllAd();
			}catch (RemoteException e3) {
				e3.printStackTrace();
			}
			AllTableData = FXCollections.observableArrayList();
			AllTableData.setAll(list);
			adTable.setItems(AllTableData);
		});
		
		// 광고 삭제
		deleteBtn.setOnAction(e -> {
			if(adTable.getSelectionModel().isEmpty()) {
				AlertMsg.caution("삭제할 자료를 선택해 주세요.");
				return;
			}
			
			AdvertisementVO adv3 = adTable.getSelectionModel().getSelectedItem();
			
			try {
				int cnt = ads.deleteAd(adv3);
			}catch (RemoteException e4) {
				e4.printStackTrace();
			}
			
			AlertMsg.info(comTF.getText()+ "회사의 광고를 삭제했습니다.");
			
			noCol.setCellValueFactory(new PropertyValueFactory<>("ad_no"));
			comCol.setCellValueFactory(new PropertyValueFactory<>("ad_name"));
			contentCol.setCellValueFactory(new PropertyValueFactory<>("ad_content"));
			try {
				list = ads.showAllAd();
			}catch (RemoteException e3) {
				e3.printStackTrace();
			}
			AllTableData = FXCollections.observableArrayList();
			AllTableData.setAll(list);
			adTable.setItems(AllTableData);
		});
		
		
		
	}
}
