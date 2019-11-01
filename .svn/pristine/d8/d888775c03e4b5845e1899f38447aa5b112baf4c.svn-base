package kr.or.ddit.clientMain;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.vo.MemberVO;
import javafx.scene.control.ScrollPane;

public class MainHotelPageController implements Initializable{

	@FXML ScrollPane mainHotelScrollPane;
	@FXML AnchorPane mainHotelAnchorPane;
	@FXML ImageView mainHotelBackGroundImage;
	@FXML Button mainFaceBookTopBtn;  //
	@FXML Button mainInstarTopBtn;  // 
	@FXML Button mainKakaoTopBtn;  //
	@FXML Button mainLogoBtn;  //
	@FXML Button mainLoginBtn;  //
	@FXML Button mainJoinBtn;
	@FXML Button mainBarHotelBtn;  // 
	@FXML Button mainBarPetSaleBtn;  // 
	@FXML Button mainBarShopBtn;
	@FXML Button mainBarQnABtn;
	@FXML Button mainSearchBtn;
	@FXML TextField mainKeywordBtn;
	@FXML ChoiceBox mainAlignBtn;
	@FXML DatePicker mainCheckInBtn;
	@FXML DatePicker mainCheckOutBtn;
	@FXML Button mainDelLunaInfoBtn;
	@FXML Button mainQuestionBtn;
	@FXML Button mainFaceBookLowBtn;
	@FXML Button mainInstarLowBtn;
	@FXML Button mainKakaoLowBtn;
	@FXML ImageView mainImage1;
	@FXML ImageView mainImage2;
	@FXML ImageView mainImage3;
	@FXML ImageView mainImage4;
	@FXML ImageView mainImage5;
	@FXML ImageView mainImage6;
	@FXML ImageView mainImage7;
	@FXML ImageView mainImage8;
	@FXML ImageView mainImage12;
	@FXML ImageView mainImage11;
	@FXML ImageView mainImage10;
	@FXML ImageView mainImage9;
	@FXML Pagination mainHotelPageNationBtn;
	@FXML Button mainClassificationBtn1;
	@FXML Button mainClassificationBtn2;
	@FXML Button mainClassificationBtn3;
	@FXML Button mainClassificationBtn4;
	
	private Registry reg;
	
	@FXML public void loginView() throws IOException {
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
	@FXML public void petSaleMainPageView() throws IOException {  
		mainHotelAnchorPane.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/petSale/PetSaleMainPage_Home.fxml")); 
		mainHotelAnchorPane.getChildren().add(root);
	}
	@FXML public void hotelMainPageView() throws IOException {
		mainHotelAnchorPane.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/clientMain/HotelMain.fxml"));
		mainHotelAnchorPane.getChildren().add(root);
	}
	@FXML public void myPageView() throws IOException {
		mainHotelAnchorPane.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/mypage_home/mypage_home.fxml"));
		mainHotelAnchorPane.getChildren().add(root);
	}
	@FXML public void joinView() throws IOException {
		mainHotelAnchorPane.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/join/join_back.fxml"));
		mainHotelAnchorPane.getChildren().add(root);
	}
	
	
	MemberVO mv = LoginSession.session;
	String user = null;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
		}catch(RemoteException e) {
			e.printStackTrace();
		}
		
		mainFaceBookTopBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		mainInstarTopBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		mainKakaoTopBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		
		 final WebView browser = new WebView();
	     final WebEngine webEngine = browser.getEngine();
	      
	     mainBarQnABtn.setOnAction(e->{
	    	 Parent change;
	         try {
	            change = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/qnaBoard/QandA.fxml"));
	            Scene scene = new Scene(change);
	            Stage primaryStage = (Stage)mainBarQnABtn.getScene().getWindow();
	            primaryStage.setScene(scene);
	         } catch (IOException e1) {
	            e1.printStackTrace();	            
	         }
	     });  
		mainFaceBookTopBtn.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI("https://ko-kr.facebook.com/FacebookKorea/"));
			}catch(IOException i) {
				i.printStackTrace();
			}catch(URISyntaxException t) {
				t.printStackTrace();
			}
		});
		
		mainInstarTopBtn.setOnAction(e -> {
	        try {
				Desktop.getDesktop().browse(new URI("https://www.instagram.com/?hl=ko"));
			}catch(IOException i) {
				i.printStackTrace();
			}catch(URISyntaxException t) {
				t.printStackTrace();
			}
		});
		
		mainKakaoTopBtn.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI("https://www.kakaocorp.com/service/KakaoTalk"));
			}catch(IOException i) {
				i.printStackTrace();
			}catch(URISyntaxException t) {
				t.printStackTrace();
			}
		});
		
		mainLoginBtn.setOnAction(e -> {
			
			if(mainLoginBtn.getText().equals("로그인")) {
				try {
					loginView();
				}catch(IOException e1) {
					e1.printStackTrace();
				}
				mainLoginBtn.setText("로그아웃");
				mainJoinBtn.setText("마이페이지");
			}else {
				LoginSession.session = null;
				AlertMsg.confirm("로그아웃되셨습니다.");
				mainLoginBtn.setText("로그인");
				mainJoinBtn.setText("회원가입");
			}
		});
		
		mainJoinBtn.setOnAction(e -> {
			if(mainJoinBtn.getText().equals("회원가입")) {
				try {
					joinView();
				}catch(IOException e1) {
					e1.printStackTrace();
				}
			}else if(mainJoinBtn.getText().equals("마이페이지")) {
				try {
					myPageView();
				}catch(IOException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		
	
	
		
		mainBarPetSaleBtn.setOnAction(e -> {
			try {
				petSaleMainPageView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		mainBarHotelBtn.setOnAction(e -> {
			try {
				hotelMainPageView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	
	
	
	
	
	
	
	
	

}
