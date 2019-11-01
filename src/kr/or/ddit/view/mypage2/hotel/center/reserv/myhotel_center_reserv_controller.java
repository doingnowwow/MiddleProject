package kr.or.ddit.view.mypage2.hotel.center.reserv;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.book.BookService;
import kr.or.ddit.service.comReservCheck.ReservCheckService;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.ComReservCheckVO;
import kr.or.ddit.vo.ComVO;
import kr.or.ddit.vo.HotelReviewVO;
import kr.or.ddit.vo.HotelVO;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;

public class myhotel_center_reserv_controller implements Initializable{

//	@FXML TextField smallTF;
//	@FXML TextField middleTF;
//	@FXML TextField bigTF;
//	@FXML Label sellsumLabel;
//	@FXML Pagination pnation;


//	@FXML Button dateBtn;
	
	@FXML TableView<ComReservCheckVO> infoTable;
	@FXML TableColumn<ComReservCheckVO,String> reservDayCol; //
	@FXML TableColumn<ComReservCheckVO,Integer> dogCntCol;  
	@FXML TableColumn<ComReservCheckVO, Integer> priceCol;  //
	@FXML TableColumn<ComReservCheckVO, String> AppDayCol; 
	@FXML TableColumn<ComReservCheckVO, String> telCol;
	@FXML TableColumn<ComReservCheckVO, ComboBox<String>> statusCol;
	@FXML Pagination pnation;
	@FXML Label sellsumLabel;
	@FXML ChoiceBox<String> yearPick;
	@FXML ChoiceBox<String > monthPick;
	@FXML Button dateBtn;

	// 사업주 예약 확인
	private Registry reg;
	private ReservCheckService rcs;
	
	// 페이지네이션
	private int from, to, itemsForpages;
	private ObservableList<ComReservCheckVO> allTableData, currentPageData;
	
	// 예약 정보를 담는 리스트
	private static List<ComReservCheckVO> book_list = new ArrayList<>();
	private ArrayList<ComReservCheckVO> check_list = new ArrayList<>();
	
	// 로그인한 사업주 정보 저장
	ComVO comvo = LoginSession.comsession;
	ComReservCheckVO cv = new ComReservCheckVO();
	int comUser = 0;
	
	// 선택한 글 번호 저장
	String click_save = null;
	
	int totPageCnt = 0;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> bYear = 
	            FXCollections.observableArrayList(
	                  "1999", "2000", "2001", "2002", "2003", "2004", "2005", 
	                  "2006", "2007", "2008", "2009", "2010", "2011",
	                  "2012", "2013", "2014", "2015", "2016", "2017", "2018", 
	                  "2019", "2020", "2021", "2022", "2023", "2024"
	                  );
	      yearPick.setItems(bYear);
	      
	      ObservableList<String> bmonth = 
	            FXCollections.observableArrayList(
	                  "01","02","03","04","05","06","07","08","09","10","11","12"
	                  );
	      monthPick.setItems(bmonth);
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			rcs = (ReservCheckService) reg.lookup("ReservCheck");
		}catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		// 예약한 정보를 모두 출력
		try {
			
			comUser = comvo.getCom_no();
			cv.setCom_no(comUser);  // 하드 코딩됨 확인용임 바꿔야함 위에 주석 처리풀기
			
			book_list = (ArrayList<ComReservCheckVO>) rcs.getAllBookCheck(cv);
		}catch ( RemoteException e ) {
			e.printStackTrace();
		}
		
		reservDayCol.setCellValueFactory(new PropertyValueFactory<>("book_checkin"));
		dogCntCol.setCellValueFactory(new PropertyValueFactory<>("book_dog_cnt"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("book_price"));
		AppDayCol.setCellValueFactory(new PropertyValueFactory<>("book_date"));
		telCol.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("book_state"));
		
		// 매출액 넣어주는 부분
		int sumMoney = 0;
		for(int i = 0 ; i < book_list.size() ; i++) {
			sumMoney += Integer.parseInt(book_list.get(i).getBook_price());
		}
		sellsumLabel.setText(""+sumMoney);
		//여기까지
		
		allTableData = FXCollections.observableArrayList();
		allTableData.setAll(book_list);
		infoTable.setItems(allTableData);
		
		itemsForpages = 20;
		
		totPageCnt = allTableData.size()%itemsForpages == 0
				? allTableData.size()/itemsForpages
				: allTableData.size()/itemsForpages + 1;
		
		pnation.setPageCount(totPageCnt);
		pnation.setPageFactory(this::createPage);
		
		// choiceBox에서 년도와 월 가져오기
		
		dateBtn.setOnAction(e -> {
			
			String year = yearPick.getValue().toString();
			String month = monthPick.getValue().toString();
			try {
				cv.setCom_no(1234567891);
				cv.setBook_checkin(year+"/"+month);
				
				book_list = rcs.getAllBookByMonth(cv);
			}catch ( RemoteException e1) {
				e1.printStackTrace();
			}
			
			reservDayCol.setCellValueFactory(new PropertyValueFactory<>("book_checkin"));
			dogCntCol.setCellValueFactory(new PropertyValueFactory<>("book_dog_cnt"));
			priceCol.setCellValueFactory(new PropertyValueFactory<>("book_price"));
			AppDayCol.setCellValueFactory(new PropertyValueFactory<>("book_date"));
			telCol.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
			statusCol.setCellValueFactory(new PropertyValueFactory<>("book_state"));
			
			//여기부터
			int sumMonth = 0;
			for(int i = 0 ; i < book_list.size() ; i++) {
				sumMonth += Integer.parseInt(book_list.get(i).getBook_price());
			}
			sellsumLabel.setText(""+sumMonth);
			//여기까지
			allTableData = FXCollections.observableArrayList();
			
			allTableData.setAll(book_list);
			
			infoTable.setItems(allTableData);
			
			itemsForpages = 20;
			
			totPageCnt = allTableData.size()%itemsForpages == 0
					? allTableData.size()/itemsForpages
							: allTableData.size()/itemsForpages + 1;
					
					pnation.setPageCount(totPageCnt);
					pnation.setPageFactory(this::createPage);
		});
		
		
	
	
	
	
	
	}
	
	// 페이지네이션용 메서드
		private Node createPage(int pageIndex) {
			from = pageIndex * itemsForpages;
			to = from + itemsForpages -1 ;
			infoTable.setItems(getTableViewData(from, to));
			
			return infoTable;
		}
		
		// 페이지네이션용 메서드
		private ObservableList<ComReservCheckVO> getTableViewData(int from, int to) {
			currentPageData = FXCollections.observableArrayList();
			int totSize = allTableData.size();
			for( int i = from; i <= to && i < totSize; i++) {
				currentPageData.add(allTableData.get(i));
			}
			return currentPageData;
		}


		

	


}
