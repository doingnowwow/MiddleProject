package kr.or.ddit.view.mypage2.hotel.center.insertSalse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class mypage_hotel_center_insertSalse_main extends Application{
	
	static AnchorPane root;
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = FXMLLoader.load(getClass().getResource("mypage_hotel_center_insertSalse.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("확인중");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
