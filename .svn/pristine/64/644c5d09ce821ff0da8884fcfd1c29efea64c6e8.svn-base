package kr.or.ddit.view.admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminMain  extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		//font 넣기 //Font.loadFont(getClass().getResourceAsStream("폰트위치"), 크기);
		Parent root = FXMLLoader.load(getClass().getResource("AdminMain.fxml"));
		Scene scene = new Scene(root);
		// scene에 스타일 시트(css)넣기
		primaryStage.setTitle("퍼피델루나 관리자");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
