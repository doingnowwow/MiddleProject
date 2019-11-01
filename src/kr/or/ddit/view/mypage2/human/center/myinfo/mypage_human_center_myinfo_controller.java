package kr.or.ddit.view.mypage2.human.center.myinfo;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.PasswordField;
import kr.or.ddit.clientMain.LoginSession;

import kr.or.ddit.service.memInfoUpdate.MemInfoUpdateService;

import kr.or.ddit.vo.MemberVO;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class mypage_human_center_myinfo_controller implements Initializable {

	@FXML
	TextField idview;
	@FXML
	PasswordField passchk;
	@FXML
	Button enter;

	private Registry reg;
	private MemInfoUpdateService is;

	MemberVO vo = LoginSession.session;

	String id = vo.getMem_id(); // 아이디가져오기

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// DB 연결하는 부분

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			is = (MemInfoUpdateService) reg.lookup("memInfoService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		MemberVO mvo = new MemberVO();
		
			mvo.setMem_id(id);
//		mvo.setMem_id("java"); // 하드코딩용

		idview.setText(mvo.getMem_id());
		
		
	}

	public void ceckPw(ActionEvent event) throws IOException {
		MemberVO mvo = new MemberVO();

		//시연할때는 이거 주석 풀어야함
		
		mvo.setMem_id(id);
		
//		mvo.setMem_id("java"); // 하드코딩용

		
		
		mvo = is.getPw(mvo);

		String pw = mvo.getMem_pw();

		if (passchk.getText().equals(pw)) {
			infoMsg("안녕하세요", "성공");
			LoginSession.myMain.myHotelInfoDetailLink();

		} else {
			errMsg("실패", "비밀번호가 맞지 않아요 ^^");
		}

	}

	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("알림");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}

	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();

	}

}
