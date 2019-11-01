package kr.or.ddit.view.mypage2.master.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class mapage_Master_main_main extends Application {
	static ScrollPane root;
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = FXMLLoader.load(getClass().getResource("mypage_Master_main.fxml"));
		
		Scene scene = new Scene(root); 
		primaryStage.setTitle("확인중");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
