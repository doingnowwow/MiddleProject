package kr.or.ddit.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertMsg {

	public static void info(String msg) {
		Alert information = new Alert(AlertType.INFORMATION);
		information.setTitle("정보");
		information.setHeaderText(msg);
		
		information.showAndWait();
	}
	
	public static void caution(String msg) {
		Alert warning = new Alert(AlertType.WARNING);
		warning.setTitle("경고");
		warning.setHeaderText(msg);
		warning.showAndWait();
	}
	
	public static ButtonType confirm(String msg) {
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("확인");
		confirm.setHeaderText(msg);
		
		return confirm.showAndWait().get();
	}
}
