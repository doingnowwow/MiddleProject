package kr.or.ddit.view.mypage2.master.center.memberadmin;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.service.memberSearch.MemberSearchService;
import kr.or.ddit.vo.ComVO;
import kr.or.ddit.vo.HotelReviewVO;
import kr.or.ddit.vo.MemDogVO;
import kr.or.ddit.vo.MemberSearchVO;
import kr.or.ddit.vo.MemberVO;
import javafx.scene.Node;
import javafx.scene.control.Button;

// 도영이가 작업중 ><
public class Master_memberadmin_controller implements Initializable{

	@FXML TableView<MemberSearchVO> comTable;
	@FXML TableColumn<MemberSearchVO, Integer> comidCol;
	@FXML TableColumn<MemberSearchVO, String> comemailCol;
	@FXML TableColumn<MemberSearchVO, String> comnameCol;
	@FXML TableColumn<MemberSearchVO, String> honameCol;                                             
	@FXML TableColumn<MemberSearchVO, String> comtelCol;                                             
	@FXML TableColumn<MemberSearchVO, String> hotelCol;                                              
	@FXML TableColumn<MemberSearchVO, String> hoinfoCol;                                             
	@FXML TableView<MemberSearchVO> memTable;                                                     
	@FXML TableColumn<MemberSearchVO, Integer> memidCol;                                          
	@FXML TableColumn<MemberSearchVO, String> mememailCol;                                        
	@FXML TableColumn<MemberSearchVO, String> memnameCol;                                         
	@FXML TableColumn<MemberSearchVO, String> memtelCol;                                          
	@FXML TableColumn<MemberSearchVO, String> memadd1Col;                                         
	@FXML TableColumn<MemberSearchVO, String> memadd2Col;                                         
	@FXML TableColumn<MemberSearchVO, Integer> mempointCol;                                       
	@FXML TableColumn<MemberSearchVO, String> memgradeCol;                                        
	@FXML Pagination mempnation;
	@FXML TextField memSearTF;
	@FXML Button memSearBtn;
	@FXML TableView<MemberSearchVO> petTable;
	@FXML TableColumn<MemberSearchVO, Integer> petmemidCol;
	@FXML TableColumn<MemberSearchVO, String> petnameCol;
	@FXML TableColumn<MemberSearchVO, String> petguCol;
	@FXML TableColumn<MemberSearchVO, String> petgenCol;
	@FXML TableColumn<MemberSearchVO, String> petsizeCol;
	@FXML TableColumn<MemberSearchVO, String> petbirCol;
	@FXML TableColumn<MemberSearchVO, String> petinfoCol;
	@FXML Pagination petpnation;
	@FXML Button petsearbtn;
	@FXML TextField petsearTF;
	@FXML Pagination compnation;
	@FXML TextField comsearTF;
	@FXML Button comsearbtn;
	
	private Registry reg;
	private MemberSearchService mss;
	
	// 페이지네이션
	private int from, to, itemsForpages;
	private ObservableList<MemberSearchVO> allTableData1, currentPageData1;
	private ObservableList<MemberSearchVO> allTableData2, currentPageData2;
	private ObservableList<MemberSearchVO> allTableData3, currentPageData3;
	
	// 정보를 담은 리스트
	private static List<MemberSearchVO> mem_list = new ArrayList<>();
	private static List<MemberSearchVO> pet_list = new ArrayList<>();
	private static List<MemberSearchVO> com_list = new ArrayList<>();
	
	// 로그인한 관리자 정보 저장
	// 관리자 머임...?
	MemberSearchVO ms = new MemberSearchVO();
	int master = 0;
	
	int totPageCnt = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			mss = (MemberSearchService) reg.lookup("memSearch");
		}catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		try {
			mem_list = mss.getAllMember();
			pet_list = mss.getAllMemPet();
			com_list = mss.getAllComMem();
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		
		memidCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		mememailCol.setCellValueFactory(new PropertyValueFactory<>("mem_email"));
		memnameCol.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		memtelCol.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
		memadd1Col.setCellValueFactory(new PropertyValueFactory<>("mem_addr1"));
		memadd2Col.setCellValueFactory(new PropertyValueFactory<>("mem_addr2"));
		mempointCol.setCellValueFactory(new PropertyValueFactory<>("mem_point"));
		memgradeCol.setCellValueFactory(new PropertyValueFactory<>("mem_level"));
		
		allTableData1 = FXCollections.observableArrayList();
		allTableData1.setAll(mem_list);
		memTable.setItems(allTableData1);
		
		petmemidCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		petnameCol.setCellValueFactory(new PropertyValueFactory<>("mem_dog_name"));
		petguCol.setCellValueFactory(new PropertyValueFactory<>("mem_dog_gu"));
		petgenCol.setCellValueFactory(new PropertyValueFactory<>("mem_dog_gender"));
		petsizeCol.setCellValueFactory(new PropertyValueFactory<>("mem_dog_size"));
		petbirCol.setCellValueFactory(new PropertyValueFactory<>("mem_dog_bir"));
		petinfoCol.setCellValueFactory(new PropertyValueFactory<>("mem_dog_intro"));
		
		allTableData2 = FXCollections.observableArrayList();
		allTableData2.setAll(pet_list);
		petTable.setItems(allTableData2);
		

		
		comidCol.setCellValueFactory(new PropertyValueFactory<>("com_id"));
		comemailCol.setCellValueFactory(new PropertyValueFactory<>("com_email"));
		comnameCol.setCellValueFactory(new PropertyValueFactory<>("com_name"));
		honameCol.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
		comtelCol.setCellValueFactory(new PropertyValueFactory<>("com_tel"));
		hotelCol.setCellValueFactory(new PropertyValueFactory<>("hotel_tel"));
		hoinfoCol.setCellValueFactory(new PropertyValueFactory<>("hotel_intro"));
		
		allTableData3 = FXCollections.observableArrayList();
		allTableData3.setAll(com_list);
		comTable.setItems(allTableData3);
		
		itemsForpages = 9;
		totPageCnt = allTableData1.size()%itemsForpages == 0
				? allTableData1.size()/itemsForpages
				: allTableData1.size()/itemsForpages + 1;
		mempnation.setPageCount(totPageCnt);
		mempnation.setPageFactory(this::memcreatePage);
		
		
		itemsForpages = 9;
		totPageCnt = allTableData2.size()%itemsForpages == 0
				? allTableData2.size()/itemsForpages
						: allTableData2.size()/itemsForpages + 1;
				petpnation.setPageCount(totPageCnt);
				petpnation.setPageFactory(this::petcreatePage);
		
				
		itemsForpages = 9;
		totPageCnt = allTableData3.size()%itemsForpages == 0
				? allTableData3.size()/itemsForpages
				: allTableData3.size()/itemsForpages + 1;
		compnation.setPageCount(totPageCnt);
		compnation.setPageFactory(this::comcreatePage);
		
		
		
		
		memSearBtn.setOnAction(e -> {
			String memText = memSearTF.getText();
			
			try {
				ms.setMem_id(memText);
				ms.setMem_name(memText);
				ms.setMem_addr1(memText);
				ms.setMem_addr2(memText);
				ms.setMem_level(memText);
				ms.setMem_tel(memText);
				mem_list = mss.getMemberByTF(ms);
			}catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			memidCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
			mememailCol.setCellValueFactory(new PropertyValueFactory<>("mem_email"));
			memnameCol.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
			memtelCol.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
			memadd1Col.setCellValueFactory(new PropertyValueFactory<>("mem_addr1"));
			memadd2Col.setCellValueFactory(new PropertyValueFactory<>("mem_addr2"));
			mempointCol.setCellValueFactory(new PropertyValueFactory<>("mem_point"));
			memgradeCol.setCellValueFactory(new PropertyValueFactory<>("mem_level"));
			
			allTableData1 = FXCollections.observableArrayList();
			allTableData1.setAll(mem_list);
			memTable.setItems(allTableData1);
			
			itemsForpages = 9;
			totPageCnt = allTableData1.size()%itemsForpages == 0
					? allTableData1.size()/itemsForpages
					: allTableData1.size()/itemsForpages + 1;
			mempnation.setPageCount(totPageCnt);
			mempnation.setPageFactory(this::memcreatePage);
			
		});
		
		petsearbtn.setOnAction(e -> {
			String petText = petsearTF.getText();
			
			try {
				ms.setMem_name(petText);
				ms.setMem_dog_name(petText);
				ms.setMem_dog_gu(petText);
				ms.setMem_dog_gender(petText);
				ms.setMem_dog_size(petText);
				pet_list = mss.getMemPetByTF(ms);
			}catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			petmemidCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
			petnameCol.setCellValueFactory(new PropertyValueFactory<>("mem_dog_name"));
			petguCol.setCellValueFactory(new PropertyValueFactory<>("mem_dog_gu"));
			petgenCol.setCellValueFactory(new PropertyValueFactory<>("mem_dog_gender"));
			petsizeCol.setCellValueFactory(new PropertyValueFactory<>("mem_dog_size"));
			petbirCol.setCellValueFactory(new PropertyValueFactory<>("mem_dog_bir"));
			petinfoCol.setCellValueFactory(new PropertyValueFactory<>("mem_dog_intro"));
			
			allTableData2 = FXCollections.observableArrayList();
			allTableData2.setAll(pet_list);
			petTable.setItems(allTableData2);
			
			itemsForpages = 9;
			totPageCnt = allTableData2.size()%itemsForpages == 0
					? allTableData2.size()/itemsForpages
					: allTableData2.size()/itemsForpages + 1;
			petpnation.setPageCount(totPageCnt);
			petpnation.setPageFactory(this::petcreatePage);
		});
		
		comsearbtn.setOnAction(e -> {
			String comText = comsearTF.getText();
			
			try {
				ms.setCom_name(comText);
				ms.setCom_email(comText);
				ms.setCom_tel(comText);
				ms.setHotel_name(comText);
				ms.setHotel_tel(comText);
				ms.setHotel_intro(comText);
				com_list = mss.getComMemByTF(ms);
			}catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			comidCol.setCellValueFactory(new PropertyValueFactory<>("com_id"));
			comemailCol.setCellValueFactory(new PropertyValueFactory<>("com_email"));
			comnameCol.setCellValueFactory(new PropertyValueFactory<>("com_name"));
			honameCol.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
			comtelCol.setCellValueFactory(new PropertyValueFactory<>("com_tel"));
			hotelCol.setCellValueFactory(new PropertyValueFactory<>("hotel_tel"));
			hoinfoCol.setCellValueFactory(new PropertyValueFactory<>("hotel_intro"));
			
			allTableData3 = FXCollections.observableArrayList();
			allTableData3.setAll(com_list);
			comTable.setItems(allTableData3);
			
			itemsForpages = 9;
			totPageCnt = allTableData3.size()%itemsForpages == 0
					? allTableData3.size()/itemsForpages
					: allTableData3.size()/itemsForpages + 1;
			compnation.setPageCount(totPageCnt);
			compnation.setPageFactory(this::comcreatePage);
		});
	}
	
	
	// 페이지네이션용 메서드
	private Node memcreatePage(int pageIndex) {
		from = pageIndex * itemsForpages;
		to = from + itemsForpages -1 ;
		memTable.setItems(getmemTableViewData(from, to));
		
		return memTable;
		
	}
	private Node petcreatePage(int pageIndex) {
		from = pageIndex * itemsForpages;
		to = from + itemsForpages -1 ;
		petTable.setItems(getpetTableViewData(from, to));
		
		return petTable;
		
	}
	private Node comcreatePage(int pageIndex) {
		from = pageIndex * itemsForpages;
		to = from + itemsForpages -1 ;
		comTable.setItems(getcomTableViewData(from, to));
		
		return comTable;
		
	}
	
	// 페이지네이션용 메서드
	private ObservableList<MemberSearchVO> getmemTableViewData(int from, int to) {
		currentPageData1 = FXCollections.observableArrayList();
		int totSize = allTableData1.size();
		for( int i = from; i <= to && i < totSize; i++) {
			currentPageData1.add(allTableData1.get(i));
		}
		return currentPageData1;
	}
	
	private ObservableList<MemberSearchVO> getpetTableViewData(int from, int to) {
		currentPageData2 = FXCollections.observableArrayList();
		int totSize = allTableData2.size();
		for( int i = from; i <= to && i < totSize; i++) {
			currentPageData2.add(allTableData2.get(i));
		}
		return currentPageData2;
	}
	
	private ObservableList<MemberSearchVO> getcomTableViewData(int from, int to) {
		currentPageData3 = FXCollections.observableArrayList();
		int totSize = allTableData3.size();
		for( int i = from; i <= to && i < totSize; i++) {
			currentPageData3.add(allTableData3.get(i));
		}
		return currentPageData3;
	}
	
		

}
