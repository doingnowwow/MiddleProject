package kr.or.ddit.view.petSale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PetSaleMain extends Application {

	public static void main(String[] args) {
	launch(args);
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		
	Parent root = FXMLLoader.load(getClass().getResource("PetSaleMainPage_Home.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("분양메인게시판");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
}
