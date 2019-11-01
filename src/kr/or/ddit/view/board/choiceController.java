package kr.or.ddit.view.board;

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
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.vo.ComVO;
import kr.or.ddit.vo.MemberVO;


public class choiceController implements Initializable {
	

	

	@FXML Button reviewNotice;
	@FXML Button Notice;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		
		
		//일반게시판 이동
		Notice.setOnAction(e->{
			try {
				LoginSession.startMain.NomarBoardLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
//	    	 Parent change;
//	         try {
//	            change = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/board/community_Home.fxml"));
//	            Scene scene = new Scene(change);
//	            Stage primaryStage = (Stage)Notice.getScene().getWindow();
//	            primaryStage.setScene(scene);
//	         } catch (IOException e1) {
//	            e1.printStackTrace();	            
//	         }
	     });  
		
		reviewNotice.setOnAction(e->{
			
			try {
				LoginSession.startMain.ReviewBoardLink();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//	    	 Parent change;
//	         try {
//	            change = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/reviewboard/review_Home.fxml"));
//	            Scene scene = new Scene(change);
//	            Stage primaryStage = (Stage)reviewNotice.getScene().getWindow();
//	            primaryStage.setScene(scene);
//	         } catch (IOException e1) {
//	            e1.printStackTrace();	            
//	         }
	     });
	}
}
		

