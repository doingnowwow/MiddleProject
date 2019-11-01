package kr.or.ddit.view.petSaleDetail;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXButton;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import kr.or.ddit.util.AlertMsg;

public class PetSaleDetailController implements Initializable{

	@FXML AnchorPane AnchorPane;
	@FXML JFXButton faceBookBtn;  //
	@FXML JFXButton instargramBtn;  //
	@FXML JFXButton kakaoBtn;  //
	@FXML JFXButton lowFaceBookBtn;  //
	@FXML JFXButton lowInstagramBtn;  //
	@FXML JFXButton lowKakaoBtn;  //
	@FXML JFXButton barHotelBtn;  //
	@FXML JFXButton barPetSaleBtn;
	@FXML JFXButton barShopBtn;
	@FXML JFXButton barQnAbtn;  //
	@FXML JFXButton LogoBtn;  //
	@FXML Button logout;  //
	@FXML Button myPage;  //
	@FXML ImageView puppyPic1;
	@FXML ImageView puppyPic2;
	@FXML ImageView puppyPic3;
	@FXML ImageView puppyPic4;
	@FXML Label puppyName;
	@FXML Label puppyBreed;
	@FXML Label puppyAge;
	@FXML JFXButton companyInfo;
	@FXML JFXButton question;
	@FXML Tab pictureTab;
	@FXML AnchorPane pictureArncoPane;
	@FXML ImageView picTabPic1;
	@FXML ImageView picTabPic2;
	@FXML ImageView picTabPic3;
	@FXML ImageView picTabPic4;
	@FXML Tab saleInfoTab;
	@FXML AnchorPane saleConfirmAnchorPane;
	@FXML Pane locationPane;
	@FXML Pane addInfoPane;
	@FXML Pane saleConfirmPane;
	
	@FXML public void mainView() throws IOException {
		AnchorPane.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/clientMain/MainHotelPageController.fxml")); 
		AnchorPane.getChildren().add(root);
	}
	@FXML public void qnaView() throws IOException {
		AnchorPane.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/qnaBoard/QandA.fxml")); 
		AnchorPane.getChildren().add(root);
	}
	@FXML public void myPageView() throws IOException {
		AnchorPane.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/mypage_home/mypage_home.fxml")); 
		AnchorPane.getChildren().add(root);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		faceBookBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		instargramBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		kakaoBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		
		 final WebView browser = new WebView();
	     final WebEngine webEngine = browser.getEngine();
	     
	     faceBookBtn.setOnAction(e -> {
				try {
					Desktop.getDesktop().browse(new URI("https://ko-kr.facebook.com/FacebookKorea/"));
				}catch(IOException i) {
					i.printStackTrace();
				}catch(URISyntaxException t) {
					t.printStackTrace();
				}
			});
			
	     instargramBtn.setOnAction(e -> {
		        try {
					Desktop.getDesktop().browse(new URI("https://www.instagram.com/?hl=ko"));
				}catch(IOException i) {
					i.printStackTrace();
				}catch(URISyntaxException t) {
					t.printStackTrace();
				}
			});
			
	     kakaoBtn.setOnAction(e -> {
				try {
					Desktop.getDesktop().browse(new URI("https://www.kakaocorp.com/service/KakaoTalk"));
				}catch(IOException i) {
					i.printStackTrace();
				}catch(URISyntaxException t) {
					t.printStackTrace();
				}
			});
		     
	    lowFaceBookBtn.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI("https://ko-kr.facebook.com/FacebookKorea/"));
			}catch(IOException i) {
				i.printStackTrace();
			}catch(URISyntaxException t) {
				t.printStackTrace();
			}
		});
	    
	    lowInstagramBtn.setOnAction(e -> {
	        try {
				Desktop.getDesktop().browse(new URI("https://www.instagram.com/?hl=ko"));
			}catch(IOException i) {
				i.printStackTrace();
			}catch(URISyntaxException t) {
				t.printStackTrace();
			}
		});
	    
	    lowKakaoBtn.setOnAction(e -> {
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
	    
	    barHotelBtn.setOnAction(e->{
	    	try {
				mainView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    
	    barQnAbtn.setOnAction(e -> {
	    	try {
				qnaView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    
	    logout.setOnAction(e -> {
	    	try {
				mainView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	AlertMsg.info("로그아웃하셨습니다.");
	    });
	    
	    myPage.setOnAction(e -> {
	    	try {
				myPageView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
		
		
	}
	
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
