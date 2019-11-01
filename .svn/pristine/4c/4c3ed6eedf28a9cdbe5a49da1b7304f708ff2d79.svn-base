package kr.or.ddit.view.advertisement;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.service.advertisement.AdvertisementService;
import kr.or.ddit.vo.AdvertisementVO;
import kr.or.ddit.vo.ComReservCheckVO;

public class AdvertisementController implements Initializable{
	
	@FXML TableView<AdvertisementVO> adTable;
	@FXML TableColumn<AdvertisementVO, String> ad_nameCol;
	@FXML TableColumn<AdvertisementVO, String> ad_contentCol;
	
	// 서버 연결
	private Registry reg;
	private AdvertisementService ads;
	
	// 광고 정보를 담는 리스트
	private static ArrayList<AdvertisementVO> list = new ArrayList<>();
	private ObservableList<AdvertisementVO> allTableData;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			ads = (AdvertisementService) reg.lookup("adver");
		}catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		
		// 광고 정보를 모두 출력
		try {
			list = (ArrayList<AdvertisementVO>) ads.showAllAd();
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		
		ad_nameCol.setCellValueFactory(new PropertyValueFactory<>("ad_name"));
		ad_contentCol.setCellValueFactory(new PropertyValueFactory<>("ad_content"));
		
		allTableData = FXCollections.observableArrayList();
		allTableData.setAll(list);
		adTable.setItems(allTableData);
		
	}
}
