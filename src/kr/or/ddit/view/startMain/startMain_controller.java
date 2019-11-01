package kr.or.ddit.view.startMain;

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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.clientMain.MainHotelPageController.MyButtonEventHandler;
import kr.or.ddit.service.advertisement.AdvertisementService;
import kr.or.ddit.service.chat.ChatClientImpl;
import kr.or.ddit.service.chat.ChatController;
import kr.or.ddit.service.chat.Main;
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.view.chatbot.ChatbotController;
import kr.or.ddit.view.login.LoginController;
import kr.or.ddit.vo.AdvertisementVO;
import kr.or.ddit.vo.ComVO;
import kr.or.ddit.vo.MemberVO;

public class startMain_controller implements Initializable {

	public @FXML BorderPane mainBorder;
	// top
	@FXML
	Button face; //
	@FXML
	Button instar; //
	@FXML
	Button kakao; //
	@FXML
	Button home;
	@FXML
	Button login; //
	@FXML
	Button join; //
	@FXML
	Button mainmenu1; //
	@FXML
	Button mainmenu2;
	@FXML
	Button mainmenu3;
	@FXML
	Button mainmenu4;
	// bottom

	@FXML
	Button link2;
	@FXML
	Button chat; //
	@FXML
	Button PuppyBot; // 챗봇연결하기

	private Registry reg;

	private AdvertisementService ads;

	List<AdvertisementVO> ad_list = new ArrayList<>();

	MemberVO mv = LoginSession.session;
	ComVO cv = LoginSession.comsession;
	int user = 0;
	int comuser = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		/**
		 * 이동용 구문작성 - 승재
		 */
		LoginSession.startMain = this;

		/**
		 * Rmi 컨트롤러 구문 작성
		 */
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// 첫화면 표출
		try {
			HotelLink();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ads = (AdvertisementService) reg.lookup("adver");
			popupAd();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		/* user = mv.getMem_no(); */
		/* comuser = cv.getCom_no(); */

		face.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		instar.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		kakao.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());

		final WebView browser = new WebView();
		final WebEngine webEngine = browser.getEngine();

		face.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI("https://ko-kr.facebook.com/FacebookKorea/"));
			} catch (IOException i) {
				i.printStackTrace();
			} catch (URISyntaxException t) {
				t.printStackTrace();
			}
		});

		instar.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI("https://www.instagram.com/a.day_corgimix/"));
			} catch (IOException i) {
				i.printStackTrace();
			} catch (URISyntaxException t) {
				t.printStackTrace();
			}
		});

		kakao.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI("https://open.kakao.com/o/gBd3lHGb"));
			} catch (IOException i) {
				i.printStackTrace();
			} catch (URISyntaxException t) {
				t.printStackTrace();
			}
		});

		login.setOnAction(e -> {

			if (login.getText().equals("Login")) {
				try {
					loginView();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				login.setText("Logout");
				join.setText("MyPage");
				
			} else {
				LoginSession.ch_down = 0;
				LoginSession.ch_up = 0;
				LoginSession.session = null;
				LoginSession.comsession = null;
				AlertMsg.confirm("로그아웃되셨습니다.");
				login.setText("Login");
				join.setText("Join");
			}
		});

		join.setOnAction(e -> {
			if (join.getText().equals("Join")) {
				try {
					joinView();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (join.getText().equals("MyPage")) {
				try {
					if (LoginSession.comsession != null) {
						hotelMypageLink();
					}

					if (LoginSession.session.getMem_level().equals("마스터")) {
						masterMypageLink();
					} else if (LoginSession.session.getMem_level().equals("브론즈")
							|| LoginSession.session.getMem_level().equals("실버")
							|| LoginSession.session.getMem_level().equals("골드")) {
						humanMypageLink();
					}

				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});

		home.setOnAction(e -> {
			try {
				HotelLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		chat.setOnAction(e -> {
			try {
				chatView();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		PuppyBot.setOnAction(e->{
			try {
				puppyBot();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		});
	}

	@FXML
	public void chatView() throws Exception {

		/*
		 * Parent parent =
		 * FXMLLoader.load(getClass().getResource("/kr/or/ddit/service/chat/chat.fxml"))
		 * ; mainBorder.setCenter(manulinkRoot); Stage stage = new Stage(); Scene scene
		 * = new Scene(parent); stage.setTitle("관리자입니다.무엇이든물어보세요"); // 창제목
		 * stage.setScene(scene);// Stage에 Scene 설정 무대에다가 신을 나오게 하는거
		 * 
		 * ChatClientImpl chatClientImpl = new ChatClientImpl("고갱님", controller,
		 * primaryStage);
		 * 
		 * chatClientImpl.connect(); // RMI 서버에 접속하기
		 * 
		 * stage.show()
		 */;// 창 (Stage)보이기

		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/or/ddit/service/chat/chat.fxml"));

		BorderPane root = (BorderPane) loader.load();

		ChatController controller = loader.getController();

		ChatClientImpl chatClientImpl = new ChatClientImpl("고갱님", controller, stage);

		chatClientImpl.connect(); // RMI 서버에 접속하기

		Scene scene = new Scene(root, 400, 350);
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();

		// mainmenu4.setOnAction(e -> {
		// Parent change;
		// try {
		// change =
		// FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/board/choice.fxml"));
		// Scene scene = new Scene(change);
		// Stage primaryStage = (Stage) mainmenu4.getScene().getWindow();
		// primaryStage.setScene(scene);
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }
		// });

	}

	public void puppyBot() throws IOException {
		
		
		/*Stage stage = new Stage();
		FXMLLoader loader = FXMLLoader.load(getClass().getResource("chat_pop.fxml"));
		
		BorderPane root = (BorderPane) loader.load();
		ChatbotController chatbotcontroller = loader.getController();
		
		stage.setTitle("ChatBot");
		stage.setScene(new Scene(root));
		stage.show();*/
		
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/or/ddit/view/chatbot/chat_pop.fxml"));
		Parent root = loader.load();

		ChatbotController controller = loader.getController();
		
		stage.setTitle("ChatBot");
		stage.setScene(new Scene(root)); 
		stage.show();
		
		

		//FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/or/ddit/view/chatbot/chat_pop.fxml"));

	//	BorderPane root = (BorderPane) loader.load();

		//ChatbotController controller = loader.getController();

	//	Scene scene = new Scene(root, 400, 350);
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	//	stage.setScene(scene);
		//stage.show();
		
		
		
		
	}

	// =====승재 작업본 =====

	// 메인페이지 상단 메뉴 링크 기능
	AnchorPane manulinkRoot;
	ScrollPane manulinkRoot2;
	ScrollPane manulinkRoot3;
	

	/**
	 * 호텔 메인 기능
	 * 
	 * @throws IOException
	 */
	@FXML // 호텔 이동
	public void HotelLink() throws IOException {
		manulinkRoot = FXMLLoader.load(getClass().getResource("../main/hotel/main/main_hotel.fxml"));
		mainBorder.setCenter(manulinkRoot);
	}

	@FXML // 호텔 상세 이동
	public void HotelDetailLink() throws IOException {
		manulinkRoot = FXMLLoader
				.load(getClass().getResource("../main/hotel/center/hoteldetailpage/hotel_detailpage.fxml"));
		mainBorder.setCenter(manulinkRoot);
	}

	@FXML // 호텔 결제 페이지 이동
	public void HotelPaycheckLink() throws IOException {
		manulinkRoot = FXMLLoader
				.load(getClass().getResource("../main/hotel/center/paycheck/hotel_payCheck.fxml"));
		mainBorder.setCenter(manulinkRoot);
	}

	@FXML // 호텔 결제 완료 이동
	public void HotelpaycompleteLink() throws IOException {
		manulinkRoot = FXMLLoader
				.load(getClass().getResource("../main/hotel/center/paycomplete/hotel_paycomplete.fxml"));
		mainBorder.setCenter(manulinkRoot);
	}

	/**
	 * 분양 메인 기능
	 * 
	 * @throws IOException
	 */
	@FXML // 분양이동--
	public void SaleLink() throws IOException {
		manulinkRoot = FXMLLoader.load(getClass().getResource("../main/salse/main/main_petsale.fxml"));
		mainBorder.setCenter(manulinkRoot);
	}

	@FXML // 분양이동--
	public void SaleDetailLink() throws IOException {
		manulinkRoot = FXMLLoader.load(getClass().getResource("../main/salse/center/petsale_detail.fxml"));
		mainBorder.setCenter(manulinkRoot);
	}

	/**
	 * 쇼핑몰 메인 기능
	 * 
	 * @throws IOException
	 */
	@FXML // 쇼핑몰이동--
	public void ShopLink() throws IOException {
		manulinkRoot = FXMLLoader.load(getClass().getResource("../main/shop/main/main_shop.fxml"));
		mainBorder.setCenter(manulinkRoot);
	}

	@FXML // 쇼핑몰디테일 이동
	public void ShopDetailLink() throws IOException {
		manulinkRoot = FXMLLoader.load(getClass().getResource("../main/shop/center/detail/main_shop_detail.fxml"));
		mainBorder.setCenter(manulinkRoot);
	}

	@FXML // 쇼핑몰결제화면 이동
	public void ShopPaycheckLink() throws IOException {
		manulinkRoot = FXMLLoader.load(getClass().getResource("../main/shop/center/paycheck/main_shop_paycheck.fxml"));
		mainBorder.setCenter(manulinkRoot);
	}

	@FXML // 쇼핑몰 결제완료
	public void ShopPaycompleteLink() throws IOException {
		manulinkRoot = FXMLLoader
				.load(getClass().getResource("../main/shop/center/paycomplete/main_shop_paycomplete.fxml"));
		mainBorder.setCenter(manulinkRoot);
	}

	/**
	 * Q&A 메인 기능
	 * 
	 * @throws IOException
	 */
	@FXML // Q&A 이동--
	public void QnALink() throws IOException {
		manulinkRoot = FXMLLoader.load(getClass().getResource("../board/choice.fxml"));
		mainBorder.setCenter(manulinkRoot);
	}

	@FXML // Q&A 일반게시판 이동--
	public void NomarBoardLink() throws IOException {
		manulinkRoot2 = FXMLLoader.load(getClass().getResource("../board/community_Home.fxml"));
		mainBorder.setCenter(manulinkRoot2);
	}

	@FXML // Q&A 리뷰게시판 이동--
	public void ReviewBoardLink() throws IOException {
		manulinkRoot3 = FXMLLoader.load(getClass().getResource("../reviewboard/review_Home.fxml"));
		mainBorder.setCenter(manulinkRoot3);
	}

	@FXML // 회원 가입 이동
	public void joinView() throws IOException {
//		manulinkRoot = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/join/join_back.fxml"));
//		mainBorder.setCenter(manulinkRoot);
		
		AnchorPane root = new AnchorPane();
		root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/join/join_back.fxml"));
		root.setPrefSize(600, 800);
		
		Parent content = root;
		Scene scene = new Scene(content);
		Stage window = new Stage();
		window.setScene(scene);
		window.setTitle("회원가입");
		
		window.show();
		
	}

	@FXML // 로그인 화면 이동
	public void loginView() throws IOException {
		Pane root1 = new Pane();
		root1 = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/login/Login_all.fxml"));
		root1.setPrefSize(300, 350);

		Parent content = root1;

		Scene scene = new Scene(content);

		Stage window = new Stage();
		window.setScene(scene);
		window.setTitle("로그인");

		window.show();
	}

	// 마이페이지 이동
	ScrollPane mylink;

	@FXML // 일반회원 마이페이지 이동
	public void humanMypageLink() throws IOException {
		mylink = FXMLLoader.load(getClass().getResource("../mypage2/human/main/mypage_human_main.fxml"));
		mainBorder.setCenter(mylink);
	}

	@FXML // 사업자 회원 마이페이지 이동
	public void hotelMypageLink() throws IOException {
		mylink = FXMLLoader.load(getClass().getResource("../mypage2/hotel/main/hotelmypage_main.fxml"));
		mainBorder.setCenter(mylink);
	}

	@FXML // 관리자 마이페이지 이동
	public void masterMypageLink() throws IOException {
		mylink = FXMLLoader.load(getClass().getResource("../mypage2/master/main/mypage_Master_main.fxml"));
		mainBorder.setCenter(mylink);
	}

	@FXML // 팝업 광고 띄우기
	public void popupAd() throws IOException {
		Pane root1 = new Pane();
		root1 = FXMLLoader.load(getClass().getResource("../advertisement/advertisement.fxml"));

		Parent content = root1;

		Scene scene = new Scene(content);

		Stage window = new Stage();
		window.setScene(scene);
		window.setTitle("팝업광고");

		window.show();
	}

	/**
	 * 버튼 위로 마우스 올리면 손가락 표시 나타나는 통합 메서드
	 */
	public class MyButtonEventHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(final MouseEvent ME) {
			Object obj = ME.getSource();

			/**
			 * 모든 버튼을 포함하는 상위 클래스인 ButtonBase를 사용
			 */
			ButtonBase button = (ButtonBase) obj;
			button.setCursor(Cursor.HAND);
		}

	}

}
