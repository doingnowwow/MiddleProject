package kr.or.ddit.view.startMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class startMain_main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {

		ScrollPane root = FXMLLoader.load(getClass().getResource("main_topandbottom.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setTitle("♥Puppy Delluna♥");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}