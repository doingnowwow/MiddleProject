package kr.or.ddit.view.mypage_home;

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
import javafx.scene.layout.Pane;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.book.BookService;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.MemberVO;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;
import javafx.scene.control.Pagination;

public class MyPage_BookCheckController implements Initializable{

	@FXML Pane bookCheckPane;
	@FXML TableView<BookVO> bookTable;
	@FXML TableColumn<BookVO, Integer> btc1; 
	@FXML TableColumn<BookVO, String> btc2;
	@FXML TableColumn<BookVO, Integer> btc3;
	@FXML TableColumn<BookVO, Integer> btc4;
	@FXML TableColumn<BookVO, Integer> btc5;
	@FXML TableColumn<BookVO, String> btc6;
	@FXML TableColumn<BookVO, String> btc7;
	@FXML TableColumn<BookVO, String> btc8;
	@FXML TableColumn<BookVO, String> btc9;
	@FXML TableColumn<BookVO, String> btc10;
	@FXML Pagination bpn;
	
	private ObservableList<BookVO> AllTableData, currentPageData;
	private int from, to, itemsForPage;

	private ArrayList<BookVO> list = new ArrayList<>();
	private BookService bs;
	
	//로그인 유저 정보 받기
	MemberVO mv = LoginSession.session;
	int user = mv.getMem_no();
	
	// VO 객체 생성
	BookVO bv = new BookVO();

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<BookVO> blist = new ArrayList<>();
		
		Registry reg;
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			bs = (BookService) reg.lookup("bookService");	
   	 		System.out.println("rmi 성공!!!");
   	 	}catch (RemoteException e) {
			e.printStackTrace();
		}catch(NotBoundException e) {
			e.printStackTrace();
		}
   	 	
   	 	//나의 정보 예약현황 테이블 가져오기
   	 	btc1.setCellValueFactory(new PropertyValueFactory<>("book_no"));
   	 	btc2.setCellValueFactory(new PropertyValueFactory<>("book_date"));
   	 	btc3.setCellValueFactory(new PropertyValueFactory<>("book_state"));
   	 	btc4.setCellValueFactory(new PropertyValueFactory<>("book_price"));
   	 	btc5.setCellValueFactory(new PropertyValueFactory<>("book_dog_cnt"));
   	 	btc6.setCellValueFactory(new PropertyValueFactory<>("book_person"));
   	 	btc7.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
   	 	btc8.setCellValueFactory(new PropertyValueFactory<>("book_checkin"));
   	 	btc9.setCellValueFactory(new PropertyValueFactory<>("book_checkout"));
   	 	btc10.setCellValueFactory(new PropertyValueFactory<>("book_check"));
   	 	
   	 	AllTableData = FXCollections.observableArrayList(blist);
   	 	
   	 	System.out.println("테이블 테스트");
   	 	
   	 	// 로그인 유저 정보 셋팅
   	 	user = mv.getMem_no();
   	 	bv.setMem_no(user);
   	 	
   	 	try {
   	 		list = (ArrayList<BookVO>) bs.selectMyBook(user);
   	 	}catch(RemoteException e) {
   	 		e.printStackTrace();
   	 	}
   	 	AllTableData.setAll(list);
   	 	
   	 	bookTable.setItems(AllTableData);
   	 	
   	 	System.out.println("selectMyBook테스트");
   	 	
   	 	itemsForPage = 10; // 한페이지에 보여줄 항목수 설정
   	 	int totPageCount = AllTableData.size()%itemsForPage == 0 ? 
   			 AllTableData.size()/itemsForPage : AllTableData.size()/itemsForPage + 1;
   	 
   		bpn.setPageCount(totPageCount); // 페이지네이션 셋 숫자를
   		bpn.setPageFactory(this::createPage);
			
		}
	/**
	 * 페이징 처리를 위한 팩토리 메서드(메서드 참조용)
	 * @param pageIndex
	 * @return
	 */
	private Node createPage(int pageIndex){
		
		from = pageIndex * itemsForPage;
		to = from + itemsForPage - 1;
		bookTable.setItems(getTableViewData(from, to));
		
		return bookTable;
	}
	
	/**
	 * TableView에 채워줄 데이터를 가져오는 함수
	 * @param from
	 * @param to
	 * @return
	 */
	private ObservableList<BookVO> getTableViewData(int from, int to){
		
		currentPageData = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int totSize = AllTableData.size();
		for(int i = from; i <= to && i <totSize; i++){
		
			currentPageData.add(AllTableData.get(i));
		}
		
		return currentPageData;
	}
	}
	
