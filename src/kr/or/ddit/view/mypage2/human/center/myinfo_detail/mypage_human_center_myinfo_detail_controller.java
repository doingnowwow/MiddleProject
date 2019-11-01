package kr.or.ddit.view.mypage2.human.center.myinfo_detail;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.memDogInfo.MemDogInfoService;
import kr.or.ddit.service.memInfoUpdate.MemInfoUpdateService;
import kr.or.ddit.vo.MemDogVO;
import kr.or.ddit.vo.MemberVO;

// 김한선 작업중 . .. ...20190930
public class mypage_human_center_myinfo_detail_controller implements Initializable {

	@FXML
	TextField name;
	@FXML
	TextField id;
	@FXML
	PasswordField mem_pass;
	@FXML
	PasswordField mem_pass2;
	@FXML
	TextField birth;
	@FXML
	TextField tel;
	@FXML
	TextField addr1;
	@FXML
	TextField addr2;
	@FXML
	TextField email;
	@FXML
	TextField dogname;
	@FXML
	TextField dog_mix;
	@FXML
	TextField dog_big;
	@FXML
	RadioButton dog_f;
	@FXML
	RadioButton dog_m;
	@FXML
	TextField dog_birth;
	@FXML
	TextField dog_detail;

	private Registry reg;
	private MemInfoUpdateService ms;
	private MemDogInfoService mds;

	MemberVO vo = LoginSession.session;
	String si = vo.getMem_id();
	@FXML
	Button cancel;
	@FXML
	Button memupdate;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			ms = (MemInfoUpdateService) reg.lookup("memInfoService");
			mds = (MemDogInfoService) reg.lookup("memDogInfo");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		MemberVO mvo = new MemberVO();
		MemDogVO dvo = new MemDogVO();
//		mvo.setMem_id("java"); // 시험용
//		dvo.setMem_id("java");// 시험용

		 mvo.setMem_id(si);
		 dvo.setMem_id(si);

		try {
			
				
				mvo = ms.getDefaultInfo(mvo);
				dvo = mds.getDogInfo(dvo);
				name.setText(mvo.getMem_name());
				id.setText(mvo.getMem_id());
				mem_pass.setText(mvo.getMem_pw());
				mem_pass2.setText(mvo.getMem_pw());
				birth.setText("00000000");
				tel.setText(mvo.getMem_tel());
				addr1.setText(mvo.getMem_addr1());
				addr2.setText(mvo.getMem_addr2());
				email.setText(mvo.getMem_email());
				
				if(dvo!= null) {
				dogname.setText(dvo.getMem_dog_name());
				// 도그 젠더가필요하다
				dog_mix.setText(dvo.getMem_dog_gu());
				dog_big.setText(dvo.getMem_dog_size());
				dog_birth.setText(dvo.getMem_dog_bir());
				dog_detail.setText(dvo.getMem_dog_intro());
				
				
				ToggleGroup group = new ToggleGroup();
				dog_f.setToggleGroup(group);
				dog_m.setToggleGroup(group);
				
				if (dvo.getMem_dog_gender().equals("여아") || dvo.getMem_dog_gender().equals("여")) {
					group.selectToggle(dog_f);
				} else if (dvo.getMem_dog_gender().equals("남아") || dvo.getMem_dog_gender().equals("남")) {
					group.selectToggle(dog_m);
				} else {
					group.selectToggle(null);
				}
				
			}
		
		
		/*else {
				mvo = ms.getDefaultInfo(mvo);
			
				name.setText(mvo.getMem_name());
				id.setText(mvo.getMem_id());
				mem_pass.setText(mvo.getMem_pw());
				mem_pass2.setText(mvo.getMem_pw());
				birth.setText("00000000");
				tel.setText(mvo.getMem_tel());
				addr1.setText(mvo.getMem_addr1());
				addr2.setText(mvo.getMem_addr2());
				email.setText(mvo.getMem_email());
			}*/

		} catch (RemoteException e) {
			e.printStackTrace();
		}

		
		
		
		
		//구현하다 말았음 ok 버튼 누루며면 이전화면으로 돌아가게 만들어야함! 
		
		cancel.setOnAction(e->{
			
			
			/*try {
				LoginSession.myMain.myHotelInfoLink();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("확인하세요!");
			alert.setHeaderText("정말 취소하시겠습니까?");
			alert.setContentText("취소하겟다구요?");
			
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get() == ButtonType.OK) {
				
				try {
					
					LoginSession.myMain.myHotelInfoLink();
					
					
				} catch (IOException e1) {
				
					e1.printStackTrace();
				}
				
				// ... user chose OK
			} else {
				// ... user chose CANCEL or closed the dialog
			}
			
			
		});
		
		
		
		

	}

	public void updateMem(ActionEvent event) throws RemoteException {

		MemberVO mvo = new MemberVO();

//		mvo.setMem_id("java"); // 시험용

		 mvo.setMem_id(si);

		id.setText(mvo.getMem_id());

		mvo.setMem_name(name.getText());
		mvo.setMem_pw(mem_pass.getText());
		mvo.setMem_pw(mem_pass2.getText());

		/*
		 * if (mem_pass.getText() != mem_pass2.getText()) { errMsg("비밀번호가 일치하는지 확인해주세요",
		 * "비밀번호불일치");
		 * 
		 * }
		 */
		mvo.setMem_tel(tel.getText());
		mvo.setMem_addr1(addr1.getText());
		mvo.setMem_addr2(addr2.getText());
		mvo.setMem_email(email.getText());

		if (name.getText().isEmpty() || mem_pass.getText().isEmpty() || mem_pass2.getText().isEmpty()
				|| tel.getText().isEmpty() || addr1.getText().isEmpty() || addr2.getText().isEmpty()
				|| email.getText().isEmpty()) {
			errMsg("실패", "빈칸이 있습니다 모든 정보를 입력해주세요");
		} else {
			int cnt = ms.updateMember(mvo);
			try {
				if (cnt > 0) {
					infoMsg("성공", "내 정보가 수정되엇습니다.");

				}
			} catch (Exception e) {
				e.printStackTrace();
				errMsg("실패", "알수없는 이유로 실패되엇슴^^");
			}
		}

	
		
		
		

	}

	public void upateMemDog(ActionEvent event) throws RemoteException {
		MemDogVO dvo = new MemDogVO();
		MemberVO mvo = new MemberVO();

		mvo.setMem_id("java"); // 시험용

		// mvo.setMem_id(si);

		dvo.setMem_dog_name(dogname.getText());
		dvo.setMem_dog_gu(dog_mix.getText());
		dvo.setMem_dog_size(dog_big.getText());
		// dvo.setMem_dog_gender(mem_dog_gender);
		dvo.setMem_dog_bir(dog_birth.getText());
		dvo.setMem_dog_intro(dog_detail.getText());

		ToggleGroup group = new ToggleGroup();

		dog_f.setToggleGroup(group);
		dog_m.setToggleGroup(group);
		dog_f.setUserData("여");
		dog_m.setUserData("남");

		if (group.getSelectedToggle().getUserData() != null) {
			String gender = group.getSelectedToggle().getUserData().toString();
			dvo.setMem_dog_gender(gender);
		}

		if (dogname.getText().isEmpty() || dog_mix.getText().isEmpty() || dog_big.getText().isEmpty()
				|| dog_birth.getText().isEmpty() || dog_detail.getText().isEmpty()) {
			errMsg("실패", "빈칸이 있습니다 모든 정보를 입력해주세요");
		} else {
			int cnt = mds.updateDog(dvo);
			try {
				if (cnt > 0) {
					infoMsg("성공", "내 강아지 정보가 수정되었습니다");
				}
			} catch (Exception e) {
				errMsg("실패", "알수없는이유");
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
