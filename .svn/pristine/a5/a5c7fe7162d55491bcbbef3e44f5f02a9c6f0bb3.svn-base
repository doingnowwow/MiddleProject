package kr.or.ddit.view.mypage2.human.center.reserv;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.book.BookService;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.HotelReviewVO;
import kr.or.ddit.vo.MemberVO;
import javafx.scene.control.Label;


public class mypage_human_center_reserv_controller implements Initializable{
	

private ArrayList<BookVO> list = new ArrayList<>(); 
private ObservableList<BookVO> allTableData, currentPageData;
	
	
private Registry reg;
private BookService bookService;

@FXML Button todayActBtn;
@FXML Button weekActBtn;
@FXML Button monthActBtn;
@FXML Button sixMonthActBtn;
@FXML TextField detailSearchTF;
@FXML Button detailSearchBtn;
@FXML TableView<BookVO> hotelBookTb;
@FXML TableColumn<BookVO, Integer> bookNo;
@FXML TableColumn<BookVO, String> bookDate;
@FXML TableColumn<BookVO, String> hotelNm;
@FXML TableColumn<BookVO, String> chkIn;
@FXML TableColumn<BookVO, String> chkOut;
@FXML TableColumn<BookVO, Integer> payCost;
@FXML TableColumn<BookVO, String> chkPay;
@FXML Pagination pagenation;
@FXML DatePicker endDate;
@FXML DatePicker startDate;
@FXML Label test;
@FXML Button DelBBtn;
@FXML TextField delBNo;


private int from, to, itemsForPage, totPageCount;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//rmi연결
		BookVO vo = new BookVO();
	    try {   
	       reg = LocateRegistry.getRegistry("localhost", 8888);
	       bookService = (BookService) reg.lookup("bookService");
	       selectList(vo);
	    }catch (RemoteException e) {
	       System.out.println("실패 ㅋ");
	       e.printStackTrace();
	    }catch(NotBoundException e) {
	       e.printStackTrace();
	    }
	    
		
		// 로그인 사용자 정보 저장
		MemberVO mv = LoginSession.session;
		HotelReviewVO hv = new HotelReviewVO();
		int user = 0;
	   
		
		int mNo  = mv.getMem_no();
        
	    detailSearchBtn.setOnAction(e->{
	    	LocalDate sld = startDate.getValue();
	    	LocalDate eld = endDate.getValue();
	    	
	    	String sDate = formatChange(sld);
	    	String eDate = formatChange(eld);
	    	String searchNm = detailSearchTF.getText();
	    	
	    	vo.setsDate(sDate);
	    	vo.seteDate(eDate);
	    	vo.setHotel_name(searchNm);
	    	
	    	selectList(vo);
	    });
	    
	    
	    
	    todayActBtn.setOnAction(e->{
	    	LocalDate toDayVal = LocalDate.now();
	    
	    	endDate.setValue(toDayVal);
	    	startDate.setValue(toDayVal);
	    	
	    	System.out.println(formatChange(toDayVal));
	    	System.out.println(formatChange(toDayVal));
	    	
	    	vo.setsDate(formatChange(toDayVal));
	    	vo.seteDate(formatChange(toDayVal));
	    	
//	    	vo.setDateGu("week");
	 /*   	vo.setDateGu("today");
	    	selectList(vo);*/
	    	
	    	selectList(vo);
		    
	    });
	    
	   
	    
	    
	    weekActBtn.setOnAction(e->{
	    	LocalDate toDayVal = LocalDate.now();
	    	LocalDate dayVal = toDayVal.minusWeeks(1); 	//1주전
	    	
	    	endDate.setValue(toDayVal);
	    	startDate.setValue(dayVal);
	    	
	    	System.out.println(formatChange(toDayVal));
	    	System.out.println(formatChange(dayVal));
	    	
	    	vo.setsDate(formatChange(dayVal));
	    	vo.seteDate(formatChange(toDayVal));
	    	
//	    	vo.setDateGu("week");
	    	
	    	selectList(vo);
	    });
	    
	    monthActBtn.setOnAction(e->{
	    	LocalDate toDayVal = LocalDate.now();
	    	LocalDate dayVal = toDayVal.minusMonths(1); 	//1달전
	    	
	    	endDate.setValue(toDayVal);
	    	startDate.setValue(dayVal);
	    	
	    	System.out.println(formatChange(toDayVal));
	    	System.out.println(formatChange(dayVal));
	    	
	    	vo.setsDate(formatChange(dayVal));
	    	vo.seteDate(formatChange(toDayVal));
	    	
	   // 	vo.setDateGu("month");
	    	selectList(vo);
	    });
	    
	    sixMonthActBtn.setOnAction(e->{
	    	LocalDate toDayVal = LocalDate.now();
	    	LocalDate dayVal = toDayVal.minusMonths(6); 	//6달전
	    	
	    	endDate.setValue(toDayVal);
	    	startDate.setValue(dayVal);
	    	
	    	System.out.println(formatChange(toDayVal));
	    	System.out.println(formatChange(dayVal));
	    	
	    	vo.setsDate(formatChange(dayVal));
	    	vo.seteDate(formatChange(toDayVal));
	    //	vo.setDateGu("6month");
	    	selectList(vo);
	    });
	    
	    DelBBtn.setOnAction(e->{
	    
	    BookVO bv = new BookVO();
	    //   List <BookVO> dBlist = new ArrayList<BookVO>(); 
	  
	    int dBNo = Integer.parseInt(delBNo.getText());
	    
	    bv.setMem_no(mNo); // 로그인 사용자 정보 넘겨줌
	    
	    bv.setBook_no(dBNo);
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("예약 취소");
		alert.setHeaderText("정말로 예약을 취소하시겠습니까?"
				+ "체크인 날짜 3일전부터는 취소가 불가능합니다.");
		alert.setContentText("확인을 누르시면 취소됩니다.");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.OK){
			try {
				 bookService.deleteBooking(bv);
				
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}   
		} else {
				
			    
		} 
	    
	/*  띄우고싶오요...흑흑  
		Alert alert2 = new Alert(AlertType.WARNING);
		alert2.setTitle("취소실패");
		alert2.setHeaderText("예약을 취소하실수 없습니다.");
		alert2.setContentText("예약취소는 체크인날짜 3일전까지만 가능합니다!");<< 기능은됩니다.
	    */

	    selectList(bv);
	    
	    
	
	    });
	
	}
	public void selectList(BookVO vo) {
		try {
			list = (ArrayList<BookVO>) bookService.getAllBook(vo);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		allTableData = FXCollections.observableArrayList(list);
	    
	    allTableData.setAll(list);
//	    allTableData.add(vo);
	    
	    System.out.println("list = " + list.size());
	    System.out.println("allTableData = " + allTableData.size());
	    hotelBookTb.setItems(allTableData);
	    
	    bookNo.setCellValueFactory(new PropertyValueFactory<>("book_no"));
		bookDate.setCellValueFactory(new PropertyValueFactory<>("book_date"));
		hotelNm.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
		chkIn.setCellValueFactory(new PropertyValueFactory<>("book_checkin"));
		chkOut.setCellValueFactory(new PropertyValueFactory<>("book_checkout"));
		payCost.setCellValueFactory(new PropertyValueFactory<>("book_price"));
		chkPay.setCellValueFactory(new PropertyValueFactory<>("book_check"));
	  
		itemsForPage = 10; //한페이지에 보여줄 항목 수 설정
		totPageCount = allTableData.size()%itemsForPage == 0?
	    allTableData.size() / itemsForPage :
	    allTableData.size()/itemsForPage+1;
	    
	    pagenation.setPageCount(totPageCount); //전체페이지 수 설정
		
		
	    pagenation.setPageFactory(new Callback<Integer, Node>(){
	      	
	    @Override
	    public Node call(Integer pageIndex) {
	       from = pageIndex * itemsForPage;
	       to = from + itemsForPage -1;
	       hotelBookTb.setItems(getTableViewData(from, to));
	       return hotelBookTb;
	    }
		     
	    private ObservableList<BookVO> getTableViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList(); //초기화
		    int totSize = allTableData.size();
		       
		    for(int i=from; i<=to && i<totSize; i++) {
		       currentPageData.add(allTableData.get(i));
		    }
		    return currentPageData;
	}
	  });
	}
	
	
	
	
	public String formatChange(LocalDate ld) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    	String rtn = ld.format(dtf);
    	return rtn;
	}
}
		