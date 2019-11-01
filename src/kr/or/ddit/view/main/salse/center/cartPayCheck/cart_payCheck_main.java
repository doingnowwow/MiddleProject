package kr.or.ddit.view.main.salse.center.cartPayCheck;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class cart_payCheck_main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		AnchorPane root = FXMLLoader.load(getClass().getResource("cart_payCheck.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("title");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
