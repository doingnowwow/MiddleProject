package kr.or.ddit.view.mypage_home;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Pagination;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.book.BookService;
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.vo.BookVO;
import kr.or.ddit.vo.MemberVO;

public class MyPageHomeController implements Initializable{
	@FXML JFXButton TopFacebookBtn;  //
	@FXML JFXButton TopInstagramBtn;  //
	@FXML JFXButton TopKakaoBtn;  //
	@FXML JFXButton LogoBtn;  //
	@FXML Button LogoutBtn;  //
	@FXML Button MyPageBtn;
	@FXML JFXButton barHotelBtn;  //
	@FXML JFXButton barPetSaleBtn;  //
	@FXML JFXButton barShopBtn;  //
	@FXML JFXButton barQnABtn;  //
	@FXML Button ModifyBtn;  //
	@FXML Button PetInfoCheckBtn;  //
	@FXML JFXButton ShoppingBagBtn;
	@FXML JFXButton ShippingBtn;  //
	@FXML JFXButton HotelBookingBtn;  //
	@FXML JFXButton CancelBookingBtn;  //
	@FXML JFXButton ChoicePetBtn;  //
	@FXML JFXButton PostCheckBtn;  //
	@FXML TextField IdField;    
	@FXML PasswordField PWCheckField;
	@FXML JFXButton ConfirmBtn;  //
	@FXML JFXButton MyActivityBtn;  //
	@FXML AnchorPane AnchorPane;
	@FXML ImageView image1;
	@FXML Pane ChangePage;
	
	 
	@FXML public void logoutView() throws IOException {
		AnchorPane.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/clientMain/HotelMain.fxml")); 
		AnchorPane.getChildren().add(root);
	}
	
	@FXML public void mainView() throws IOException {
		AnchorPane.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/clientMain/HotelMain.fxml")); 
		AnchorPane.getChildren().add(root);
	}
	
	@FXML public void mypageView() throws IOException {
		AnchorPane.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/mypage_home/mypage_home.fxml")); 
		AnchorPane.getChildren().add(root);
	}
	
	@FXML public void petSaleView() throws IOException {  // 펫분양 메인 페이지 만들고 수정하기
		AnchorPane.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/petSale/PetSaleMainPage_Home.fxml")); 
		AnchorPane.getChildren().add(root);
	}
	
	@FXML public void shopView() throws IOException {  // 쇼핑몰 메인 페이지 만들고 수정하기
		AnchorPane.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/???.fxml")); 
		AnchorPane.getChildren().add(root);
	}
	
	@FXML public void qnaView() throws IOException {  // QnA 메인 페이지 만들고 수정하기
		AnchorPane.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/???.fxml")); 
		AnchorPane.getChildren().add(root);
	}
	
	@FXML public void petInfoView() throws IOException {  // 펫정보 페이지 만들고 수정하기
		ChangePage.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/???.fxml")); 
		ChangePage.getChildren().add(root);
	}
	
	@FXML public void shopBagView() throws IOException {  // 나의 장바구니 페이지 만들고 수정하기
		ChangePage.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/???.fxml")); 
		ChangePage.getChildren().add(root);
	}
	
	@FXML public void shippingView() throws IOException {  // 배송조회 페이지 만들고 수정하기
		ChangePage.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/???.fxml")); 
		ChangePage.getChildren().add(root);
	}
	
	@FXML public void hotelBookView() throws IOException {  // 호텔 예약확인 페이지 만들고 수정하기
		ChangePage.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/mypage_home/mypage_bookCheck.fxml")); 
		ChangePage.getChildren().add(root);
	}
	
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
		String id = IdField.getText();
   	 	String pass = PWCheckField.getText();
   	 	
   	 	boolean memCheck; 
   	 	boolean chk = true;
   	 	List<BookVO> blist = new ArrayList<>();
   	 	
   	 	Registry reg;
   	 	try {
   	 		reg = LocateRegistry.getRegistry("localhost",8888);
   	 		bs = (BookService) reg.lookup("bookService");	
   	 		System.out.println("rmi 성공!!!");
   	 	}catch (RemoteException e) {
			e.printStackTrace();
		}catch(NotBoundException e) {
			e.printStackTrace();
		}
   	 	
   	 	
   	 	
   	 	
   	 	
//   	 	// 로그인 유저 정보 셋팅
//   	 	user = mv.getMem_no();
//   	 	bv.setMem_no(user);
//   	 	
//   	 	try {
//   	 		list = (ArrayList<BookVO>) bs.selectMyBook(user);
//   	 	}catch(RemoteException e) {
//   	 		e.printStackTrace();
//   	 	}
//   	 	AllTableData.setAll(list);
//   	 	
//   	 	bookTable.setItems(AllTableData);
//   	 	
//   	 	System.out.println("selectMyBook테스트");
//   	 	
//   	 	itemsForPage = 10; // 한페이지에 보여줄 항목수 설정
//   	 	int totPageCount = AllTableData.size()%itemsForPage == 0 ? 
//   			 AllTableData.size()/itemsForPage : AllTableData.size()/itemsForPage + 1;
//   	 
//   		bpn.setPageCount(totPageCount); // 페이지네이션 셋 숫자를
//   		bpn.setPageFactory(this::createPage);
//   	 	
   	 	
//		Registry reg;
//		try {
//			reg = LocateRegistry.getRegistry("192.168.207.145", 8888);
//			IMemberService clientInf = (IMemberService) reg.lookup("memberService");
//			// 이제부터는 불러온 객체의 메서드를 호출해서 사용할 수 있다.
//            // 메서드 적기
//			mypageConfirmBtn.setOnAction(e -> {
//		    	 do {
//		    		 chk = clientInf.getMember(id);
//		 			if(chk == false) {
//		 				Alert alertError = new Alert(AlertType.ERROR);
//		 				alertError.setTitle("회원정보수정");
//		 				alertError.setHeaderText("잘못입력하셨습니다. 다시 확인해주세요.");
//		 				alertError.showAndWait();
//		 				mypageIdField.requestFocus();
//		 			}
//		 		}while(chk == false);
//		    	 
//		    	 
//		     }); 
//			memCheck = clientInf.getMember(id);
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		} catch (NotBoundException e) {
//			e.printStackTrace();
//		}

		TopFacebookBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		TopInstagramBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		TopKakaoBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		
		 final WebView browser = new WebView();
	     final WebEngine webEngine = browser.getEngine();
		
	     // facebook link
	     TopFacebookBtn.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI("https://ko-kr.facebook.com/FacebookKorea/"));
			}catch(IOException i) {
				i.printStackTrace();
			}catch(URISyntaxException t) {
				t.printStackTrace();
			}
		});
		
	     // instagram link
	     TopInstagramBtn.setOnAction(e -> {
	        try {
				Desktop.getDesktop().browse(new URI("https://www.instagram.com/?hl=ko"));
			}catch(IOException i) {
				i.printStackTrace();
			}catch(URISyntaxException t) {
				t.printStackTrace();
			}
		});
		
	     // kakaoTalk link
	     TopKakaoBtn.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI("https://www.kakaocorp.com/service/KakaoTalk"));
			}catch(IOException i) {
				i.printStackTrace();
			}catch(URISyntaxException t) {
				t.printStackTrace();
			}
		});
	     
	     
	     LogoBtn.setOnAction(e -> {
	    	 try {
				mainView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     });
	     
	     
	     LogoutBtn.setOnAction(e -> {
	    	 try {
				logoutView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	 AlertMsg.confirm("로그아웃 되셨습니다."); 
	     });
	     
	     
	     MyPageBtn.setOnAction(e -> {
	    	 try {
				mypageView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     });
	     
	     
	     barHotelBtn.setOnAction(e -> {
	    	 try {
				mainView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     }); 
	     
	     
	     barPetSaleBtn.setOnAction(e -> {
	    	 try {
				petSaleView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     });
	     
	     
	     barShopBtn.setOnAction(e -> {
	    	 try {
				shopView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     });
	     
	     
	     barQnABtn.setOnAction(e -> {
	    	 try {
				qnaView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     });
	     
	     
	     ModifyBtn.setOnAction(e -> {  // 회원 정보 수정 
	    	 
	     });
	     
	     
	     PetInfoCheckBtn.setOnAction(e -> {  // 애견 정보 확인
	    	 try {
				petInfoView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     });
	     
	     
	     ShoppingBagBtn.setOnAction(e -> {  // 나의 장바구니 확인
	    	 try {
				shopBagView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     });
	     
	     
	     ShippingBtn.setOnAction(e -> { // 주문 배송 조회
	    	 try {
				shippingView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     });
	     
	     
	     HotelBookingBtn.setOnAction(e -> {  // 호텔 예약 조회
	    	 try {
				hotelBookView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     });
	     
	     
	     CancelBookingBtn.setOnAction(e -> {  // 취소/수정 신청
	    	 
	     });
	     
	     
	     ChoicePetBtn.setOnAction(e -> {  // 찜한 애견
	    	 
	     });
	
	     
	     
	     PostCheckBtn.setOnAction(e -> {  // 내가 쓴 게시물 
	    	 
	     });
	     
	     
	     ConfirmBtn.setOnAction(e -> {  // 확인 버튼
	    	 
	     });
	     
	     
	     MyActivityBtn.setOnAction(e -> {  // 나의 활동 
	    	 
	     });
	}


	/**
	 * 버튼 위로 마우스 올리면 손가락 표시 나타나는 통합 메서드
	 */
	public class MyButtonEventHandler implements EventHandler<MouseEvent> {
	    @Override
	    public void handle( final MouseEvent ME )
	    {
	      Object obj = ME.getSource();
	 
	      /**
	       * 모든 버튼을 포함하는 상위 클래스인 ButtonBase를 사용
	       */
	      ButtonBase button = (ButtonBase) obj;
	      button.setCursor(Cursor.HAND);
	    }
	}

//	/**
//	 * 페이징 처리를 위한 팩토리 메서드(메서드 참조용)
//	 * @param pageIndex
//	 * @return
//	 */
//	private Node createPage(int pageIndex){
//		
//		from = pageIndex * itemsForPage;
//		to = from + itemsForPage - 1;
//		bookTable.setItems(getTableViewData(from, to));
//		
//		return bookTable;
//	}
//	/**
//	 * TableView에 채워줄 데이터를 가져오는 함수
//	 * @param from
//	 * @param to
//	 * @return
//	 */
//	private ObservableList<BookVO> getTableViewData(int from, int to){
//		
//		currentPageData = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
//		int totSize = AllTableData.size();
//		for(int i = from; i <= to && i <totSize; i++){
//		
//			currentPageData.add(AllTableData.get(i));
//		}
//		
//		return currentPageData;
//	}
//	
	
}

