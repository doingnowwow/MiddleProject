package kr.or.ddit.view.petSale;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Pagination;

public class PetSaleMainPageController implements Initializable{

	@FXML AnchorPane changeView;
	@FXML JFXButton topFacebookBtn;  //
	@FXML JFXButton topInstagramBtn;  //
	@FXML JFXButton topKakaoBtn;  //
	@FXML JFXButton barHotelBtn;
	@FXML JFXButton barPetSaleBtn;  //
	@FXML JFXButton barShopBtn;
	@FXML JFXButton barQnABtn;  //
	@FXML JFXButton topLogoBtn;  //
	@FXML JFXButton lowCompanyInfoBtn; 
	@FXML JFXButton lowQuestionBtn; 
	@FXML JFXButton lowFaceBookBtn;  //
	@FXML JFXButton lowInstagramBtn; //
	@FXML JFXButton lowKakaoBtn;  // 
	@FXML Button petSaleLogin;  //
	@FXML Button petSaleJoin;  //
	@FXML ImageView image1;
	@FXML ImageView image2;
	@FXML ImageView image3;
	@FXML ImageView image4;
	@FXML ImageView image5;
	@FXML ImageView image6;
	@FXML ImageView image7;
	@FXML ImageView image8;
	@FXML ImageView image9;
	@FXML ImageView image10;
	@FXML ImageView image11;
	@FXML ImageView image12;
	@FXML Pagination PageNation;
	@FXML JFXButton image1Btn;
	@FXML JFXButton image2Btn;
	@FXML JFXButton image3Btn;
	@FXML JFXButton image4Btn;
	@FXML JFXButton image5Btn;
	@FXML JFXButton image6Btn;
	@FXML JFXButton image7Btn;
	@FXML JFXButton image8Btn;
	@FXML JFXButton image9Btn;
	@FXML JFXButton image10Btn;
	@FXML JFXButton image11Btn;
	@FXML JFXButton image12Btn;
	
	@FXML public void mainView() throws IOException {
		changeView.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/clientMain/MainHotelPageController.fxml")); 
		changeView.getChildren().add(root);
	}
	@FXML public void petSaleMainPageView() throws IOException {
		changeView.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/petSale/PetSaleMainPage_Home.fxml"));
		changeView.getChildren().add(root);
	}
	@FXML public void petSaleDetailView() throws IOException {
		changeView.getChildren().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/petSaleDetail/petSaleDetailPage.fxml"));
		changeView.getChildren().add(root);
	}

	@FXML public void loginView() throws IOException {
		Pane root1 = new Pane();
		root1 = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/login/Login_all.fxml"));
		root1.setPrefSize(300, 350);
		
		Parent content = root1;
		
		// create scene containing the content
		Scene scene = new Scene(content);
		
		Stage window = new Stage();
		window.setScene(scene);
		window.setTitle("로그인");
		
		// make window visible
		window.show();
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {


		
		topFacebookBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		topInstagramBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		topKakaoBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		
		 final WebView browser = new WebView();
	     final WebEngine webEngine = browser.getEngine();
	     
	     barQnABtn.setOnAction(e->{
	    	 Parent change;
	         try {
	            change = FXMLLoader.load(getClass().getResource("/kr/or/ddit/clientMain/Q&A.fxml"));
	            Scene scene = new Scene(change);
	            Stage primaryStage = (Stage)barQnABtn.getScene().getWindow();
	            primaryStage.setScene(scene);
	         } catch (IOException e1) {
	            e1.printStackTrace();
	         }
	     });
	     
	     barPetSaleBtn.setOnAction(e -> {
	    	 try {
				petSaleMainPageView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     });
		

	     topFacebookBtn.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI("https://ko-kr.facebook.com/FacebookKorea/"));
			}catch(IOException i) {
				i.printStackTrace();
			}catch(URISyntaxException t) {
				t.printStackTrace();
			}
		});
		
	     topInstagramBtn.setOnAction(e -> {
	        try {
				Desktop.getDesktop().browse(new URI("https://www.instagram.com/?hl=ko"));
			}catch(IOException i) {
				i.printStackTrace();
			}catch(URISyntaxException t) {
				t.printStackTrace();
			}
		});
		
	     topKakaoBtn.setOnAction(e -> {
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
		
	    topLogoBtn.setOnAction(e -> {
	    	try {
				mainView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }); 
	     
	    petSaleLogin.setOnAction(e -> {
			try {
				loginView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});  
	    
	    
	    petSaleJoin.setOnAction(e -> {
	    	
	    });
	    
	    image1Btn.setOnAction(e -> {
	    	try {
				petSaleDetailView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    image2Btn.setOnAction(e -> {
	    	try {
				petSaleDetailView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    image3Btn.setOnAction(e -> {
	    	try {
				petSaleDetailView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    image4Btn.setOnAction(e -> {
	    	try {
				petSaleDetailView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    image5Btn.setOnAction(e -> {
	    	try {
				petSaleDetailView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    image6Btn.setOnAction(e -> {
	    	try {
				petSaleDetailView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    image7Btn.setOnAction(e -> {
	    	try {
				petSaleDetailView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    image8Btn.setOnAction(e -> {
	    	try {
				petSaleDetailView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    image9Btn.setOnAction(e -> {
	    	try {
				petSaleDetailView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    image10Btn.setOnAction(e -> {
	    	try {
				petSaleDetailView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    image11Btn.setOnAction(e -> {
	    	try {
				petSaleDetailView();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    image12Btn.setOnAction(e -> {
	    	try {
				petSaleDetailView();
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






















