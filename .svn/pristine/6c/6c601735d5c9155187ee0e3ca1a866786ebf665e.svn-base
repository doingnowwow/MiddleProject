package kr.or.ddit.view.mypage2.hotel.center.cominfo;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.com.ComService;
import kr.or.ddit.view.mypage2.hotel.main.hotelmypage_main_controller;
import kr.or.ddit.vo.ComVO;

//한선이작업중임...완료20190930
public class myhotel_center_cominfo_controller implements Initializable{
	
	@FXML TextField id_input;
	@FXML PasswordField pass_input;
	@FXML Button commit;
	@FXML Label chk;
	
	
	private Registry reg;
	private ComService cs;
	ComVO vo = LoginSession.comsession;

	String si = vo.getCom_id(); //아이디 가져오기
	
	//String id = null; // 시험하기위해 null 로 잡기 
	//	ComVO pw = null;
	
/*	public hotelmypage_main_controller main;

	public hotelmypage_main_controller getMain() {
		return main;
	}

	public void setMain(hotelmypage_main_controller main) {
		this.main = main;
	}
*/
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// DB연결하는 부분
		try {
			reg = LocateRegistry.getRegistry("localhost",8888);
			cs = (ComService) reg.lookup("comservice")	;
		
		}catch(RemoteException e) {
			e.printStackTrace();
		}catch(NotBoundException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		ComVO cvo = new ComVO();

		// 검사때는 아래 주석 풀어야함 !

		 cvo.setCom_id(si);

//		cvo.setCom_id("hotel"); // 시험용
		id_input.setText(cvo.getCom_id());
		
		
	
	/*	
	 * 버튼 클릭을 따로 메서드로 빼서 할 예정이라 람다식 바꿉니다 ~ 
		boolean chk2 = false;
		//조건을 통한 상세정보 이동
		commit.setOnAction(e->{
			id_input.getText(); //입력한 아이디정보
			pass_input.getText(); //입력한 비밀번호정보
			
			//조건
			
			if(chk2){
				chk.setText("로그인성공");
			}
			
			
			
		});*/
		
		
		
		
	}
	
	
	public void ceckPw(ActionEvent event) throws IOException  {
		ComVO cvo = new ComVO();
		cvo.setCom_id("hotel");
		cvo = cs.getCom_pw(cvo);
		
		String pw = cvo.getCom_pw();
		
		if(pass_input.getText().equals(pw)) {
			
			
			infoMsg("안녕하세요", "성공");
			LoginSession.hotelMain.myHotelInfoLinkDetail();
		
			

			
			
		}else {
			
			errMsg("실패", "비밀번호를 다시 확인해주세요");
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
