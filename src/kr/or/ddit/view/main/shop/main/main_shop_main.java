package kr.or.ddit.view.main.shop.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class main_shop_main extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		AnchorPane root = FXMLLoader.load(getClass().getResource("main_shop.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("title");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
