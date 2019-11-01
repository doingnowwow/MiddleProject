package kr.or.ddit.view.mypage2.hotel.center.manage;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;


//서현 작업중
public class myhotel_center_manager_main extends Application{
	static ScrollPane root;
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = FXMLLoader.load(getClass().getResource("hotel_manage_scroll.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("확인중");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
