package kr.or.ddit.view.mypage2.hotel.center.cominfo_detail;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage; 

// 한선 작업중 하하완료
public class myhotel_center_cominfo_detail_main extends Application{
	static AnchorPane root;
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = FXMLLoader.load(getClass().getResource("hotelmypage_center_cominfo_detail.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("확인중");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
