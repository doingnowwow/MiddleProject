package kr.or.ddit.view.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import kr.or.ddit.vo.MemberVO;

public class AdminMainController implements Initializable{
     
	@FXML Button btn_black;
	
	public static MemberVO mv;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btn_black.setOnAction(e->{
	    	 Parent change = null;
	         try {
	            change = FXMLLoader.load(getClass().getResource("black.fxml"));
	            Scene scene = new Scene(change);
	            Stage primaryStage = (Stage)btn_black.getScene().getWindow();
	            primaryStage.setScene(scene);
	         } catch (IOException e1) {
	            e1.printStackTrace();
	            
	         }
	         

	         
	     });
	}
			
	
	
	
}
