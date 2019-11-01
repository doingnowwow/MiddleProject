package kr.or.ddit.view.mypage2.hotel.center.cominfo_detail;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.com.ComService;
import kr.or.ddit.view.mypage2.hotel.main.hotelmypage_main_controller;
import kr.or.ddit.vo.ComVO;
//dhksfy
public class myhotel_center_cominfo_detail_controller implements Initializable {

	@FXML
	TextField id;
	@FXML
	PasswordField pass;
	@FXML
	PasswordField re_pass;
	@FXML
	TextField ho_name;
	@FXML
	TextField ceo;
	@FXML
	TextField tel;
	@FXML
	TextField addr1;
	@FXML
	TextField addr2;
	@FXML
	TextField email;
	@FXML
	ImageView infoimg;
	@FXML
	ListView<?> filelist;
	@FXML
	Button cancel;
	@FXML
	Button commit;

	@FXML Label chk;
	
	private Registry reg;
	private ComService cs;
	private ObservableList<ComVO> allTableData;
	private hotelmypage_main_controller mainCon;
	

	// 세션(com_id) ? com_no ????

	ComVO vo = LoginSession.comsession;
	String si = vo.getCom_id();

//	 String si = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			cs = (ComService) reg.lookup("comservice");

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		ComVO cvo = new ComVO();
		//검사때는 아래 주석 풀어야함!
		cvo.setCom_id(si);
		
		
//		cvo.setCom_id("hotel"); //시험용

		try {
			cvo = cs.ComgetDefaulInfo(cvo);
			id.setText(cvo.getCom_id());
			pass.setText(cvo.getCom_pw());
			re_pass.setText(cvo.getCom_pw());
			ho_name.setText(cvo.getCom_name());
			ceo.setText(cvo.getCom_ceo());
			tel.setText(cvo.getCom_tel());
			addr1.setText(cvo.getCom_addr1());
			addr2.setText(cvo.getCom_addr2());
			email.setText(cvo.getCom_email());
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		cancel.setOnAction(e -> {
			infoMsg("알림", "이전 페이지로 돌아갑니당.");
			try {
				LoginSession.hotelMain.myHotelInfoLink();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		});

		
		
		
	}

	
	
	public void updateCom(ActionEvent evnet) throws Exception {
		
		ComVO cvo = new ComVO();
		
//		cvo.setCom_id("hotel"); //시험용  아래 주석을 지워야 실행됨
		
		cvo.setCom_id(si); 
		
		id.setText(cvo.getCom_id());
		
		cvo.setCom_pw(pass.getText());
		cvo.setCom_pw(re_pass.getText());
		cvo.setCom_name(ho_name.getText());
		cvo.setCom_ceo(ceo.getText());
		cvo.setCom_tel(tel.getText());
		cvo.setCom_addr1(addr1.getText());
		cvo.setCom_addr2(addr2.getText());
		cvo.setCom_email(email.getText());
		
		
		
	
		/*if (pass.getText() != re_pass.getText()) {
			errMsg("실패", "비밀번호가 일치하지 않습니다");
					
		}
		*/
		
		if (pass.getText().isEmpty() || re_pass.getText().isEmpty() || ho_name.getText().isEmpty()
				
				|| ceo.getText().isEmpty()|| tel.getText().isEmpty()|| addr1.getText().isEmpty()
				|| addr2.getText().isEmpty()|| email.getText().isEmpty()) {
			errMsg("실패", "정보를 모두 입력해라");
		} else {
			int cnt = cs.updateCom(cvo);
			try {
				if (cnt > 0) {
					infoMsg("성공", "내 정보가 수정 되었습니다.");

				}
			} catch (Exception e) {
				errMsg("실패", "오류");
			}
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
