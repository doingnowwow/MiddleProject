package kr.or.ddit.view.join;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kr.or.ddit.api.Sendmail;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.join.IComJoinService;
import kr.or.ddit.service.join.IJoinService;
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.vo.ComVO;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ComJoinController implements Initializable {

	@FXML TextField joinHotelId;
	@FXML TextField joinHotelPass;
	@FXML TextField joinHotelPassCheck;
	@FXML TextField joinHotelName;
	@FXML TextField joinHotelOwnerName;
	@FXML TextField RegistrationNum;
	@FXML TextField joinHotelTel;
	@FXML Button hotelIdCheck;
	@FXML TextField joinHotelAddr1;
	@FXML TextField joinHotelEmail;
	@FXML Button hotelJoinCheck;
	@FXML Button cancle;
	@FXML TextField joinHotelAddr2;
	@FXML Button hotelEmailCheck;
	@FXML Label state;
	
	private Registry reg;
	private IComJoinService icomJoin;
	
	List<ComVO> c_list = new ArrayList<>();
	private ComVO cv = new ComVO();
	
	int chk_cnt = 0;
	int pattern_cnt = 0;
	int no_cnt = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String id = joinHotelId.getText().trim();
		String pass = joinHotelPass.getText().trim();
		String passChk = joinHotelPassCheck.getText().trim();
		String comName = joinHotelName.getText().trim();
		String ownerName = joinHotelOwnerName.getText().trim();
		String regiNum = RegistrationNum.getText().trim();
		String tel = joinHotelTel.getText().trim();
		String add1 = joinHotelAddr1.getText().trim();
		String add2 = joinHotelAddr1.getText().trim();
		String email = joinHotelEmail.getText().trim();
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			icomJoin = (IComJoinService) reg.lookup("comjoin");
		}catch ( RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		// 아이디 중복체크 버튼 누르기
		hotelIdCheck.setOnAction(e -> {
			String comuser = "^[a-zA-Z0-9]*$";
			boolean result = Pattern.matches(comuser, joinHotelId.getText().trim());
			
			if(result == false) {
				AlertMsg.caution("에러! 아이디는 영어 대소문자와 숫자로 입력해주세요");
				return;
			}else if(joinHotelId.getText().trim().equals("") || joinHotelId.getText().trim() == null) {
				AlertMsg.caution("에러! 아이디를 입력하지 않았습니다!");
				return;
			}
			
			ArrayList<ComVO> comList = new ArrayList<>();
			
			ComVO cv = new ComVO();
			cv.setCom_id(joinHotelId.getText().trim());
			
			try {
				comList = (ArrayList<ComVO>) icomJoin.comSelectId(cv);
			}catch(RemoteException e1) {
				e1.printStackTrace();
			}
			
			if(comList.size() == 0) {
				state.setText("사용하실 수 있는 아디입니다.");
				chk_cnt++;
				pattern_cnt++;
			}else {
				state.setText("이미 사용 중인 아이디입니다!!");
				return;
			}
		});
		
		hotelEmailCheck.setOnAction(e -> {
			ArrayList<ComVO> comList = new ArrayList<>();
			
			ComVO cv = new ComVO();
			cv.setCom_id(joinHotelEmail.getText().trim());
			
			try {
				comList = (ArrayList<ComVO>) icomJoin.comSelectEmail(cv);
			}catch (RemoteException e2) {
				e2.printStackTrace();
			}
			
			if(comList.size() == 0) {
				state.setText("사용하실 수 있는 이메일입니다.");
				chk_cnt++;
				pattern_cnt++;
			}else {
				state.setText("이미 사용 중인 이메일입니다!!");
				return;
			}
		});
		
		joinHotelId.setOnMouseClicked(e -> {
			if(id.isEmpty()) {
				state.setText("아이디를 입력하세요");
				joinHotelId.requestFocus();
			}	
		});
		
		joinHotelPass.setOnMouseClicked(e -> {
			if(pass.isEmpty()) {
				state.setText("비밀번호를 입력하세요");
				joinHotelPass.requestFocus();
			}
		});
		
		joinHotelPassCheck.setOnMouseClicked(e -> {
			if(passChk.isEmpty()) {
				state.setText("비밀번호를 다시 입력해주세요.");
				joinHotelPassCheck.requestFocus();
			}
		});
		
		joinHotelName.setOnMouseClicked(e -> {
			if(comName.isEmpty()) {
				state.setText("호텔이름을 입력해주세요.");
				joinHotelName.requestFocus();
			}
		});
		
		joinHotelOwnerName.setOnMouseClicked(e -> {
			if(ownerName.isEmpty()) {
				state.setText("사업주이름을 입력해주세요.");
				joinHotelOwnerName.requestFocus();
			}
		});
		
		RegistrationNum.setOnMouseClicked(e -> {
			if(regiNum.isEmpty()) {
				state.setText("사업자등록번호를 입력해주세요.");
				RegistrationNum.requestFocus();
			}
		});
		
		joinHotelTel.setOnMouseClicked(e -> {
			if(tel.isEmpty()) {
				state.setText("전화번호를 입력해주세요");
				joinHotelTel.requestFocus();
			}
		});
		
		joinHotelAddr1.setOnMouseClicked(e -> {
			if(add1.isEmpty()) {
				state.setText("주소를 입력해주세요");
				joinHotelAddr1.requestFocus();
			}
		});
		
		joinHotelEmail.setOnMouseClicked(e -> {
			if(email.isEmpty()) {
				state.setText("이메일을 입력하세요");
				joinHotelEmail.requestFocus();
			}
			
		});
		
		joinHotelAddr2.setOnMouseClicked(e -> {
			if(add2.isEmpty()) {
				state.setText("상세 주소를 입력해주세요");
				joinHotelAddr2.requestFocus();
			}
		});
		
		// ---- 여기까지 입력 상태 체크 --------------------
		
		hotelJoinCheck.setOnAction(e -> {
			Pattern pwchk = Pattern.compile(pass);
			Matcher pwchkMatch = pwchk.matcher(passChk);
			boolean result = pwchkMatch.matches();
			if(result) {
				pattern_cnt++;
			}else if(joinHotelPassCheck.getText().equals("") || joinHotelPassCheck.getText() == null) {
				AlertMsg.caution("비밀번호 확인칸을 입력하지 않았습니다!");
				joinHotelPassCheck.requestFocus();
				no_cnt++;
				return;
			}else if(result == false) {
				AlertMsg.caution("비밀번호와 일치하지 않습니다!");
				joinHotelPassCheck.requestFocus();
				return;
			}
			
			if(joinHotelTel.getText().equals("") || joinHotelTel.getText() == null) {
				AlertMsg.caution("전화번호를 입력하지 않았습니다!");
				joinHotelTel.requestFocus();
				no_cnt++;
				return;
			}
			
			if (joinHotelPass.getText().equals("") || joinHotelPass.getText() == null) {
				AlertMsg.caution("비밀번호를 입력하지 않았습니다!");
				joinHotelPass.requestFocus();
				no_cnt++;
				return;
			}
			
			if(joinHotelEmail.getText().equals("") || joinHotelEmail.getText() == null) {
				AlertMsg.caution("이메일을 입력하지 않았습니다!");
				no_cnt++;
				joinHotelEmail.requestFocus();
				return;
			}
			
			if(joinHotelName.getText().equals("") || joinHotelName.getText() == null) {
				AlertMsg.caution("호텔이름을 입력하지 않았습니다!");
				no_cnt++;
				joinHotelName.requestFocus();
				return;
			}
			
			if(joinHotelOwnerName.getText().equals("") || joinHotelOwnerName.getText() == null) {
				AlertMsg.caution("사업주이름을 입력하지 않았습니다!");
				no_cnt++;
				joinHotelOwnerName.requestFocus();
				return;
			}
			
			if(RegistrationNum.getText().equals("") || RegistrationNum.getText() == null) {
				AlertMsg.caution("사업자 등록번호를 입력하지 않았습니다!");
				no_cnt++;
				RegistrationNum.requestFocus();
				return;
			}
			
			if(joinHotelAddr1.getText().equals("") || joinHotelAddr1.getText() == null) {
				AlertMsg.caution("주소를 입력하지 않았습니다!");
				joinHotelAddr1.requestFocus();
				no_cnt++;
				return;
			}
			
			if(joinHotelAddr2.getText().equals("") || joinHotelAddr2.getText() == null) {
				AlertMsg.caution("상세주소를 입력하지 않았습니다!");
				joinHotelAddr2.requestFocus();
				no_cnt++;
				return;
			}
			
			if(chk_cnt == 2 && pattern_cnt == 3 && no_cnt == 0) {
				int ho_cnt = 0;
				
				cv.setCom_no(Integer.parseInt(RegistrationNum.getText().trim()));
				cv.setCom_id(joinHotelId.getText().trim());
				cv.setCom_pw(joinHotelPass.getText().trim());
				cv.setCom_name(joinHotelName.getText().trim());
				cv.setCom_addr1(joinHotelAddr1.getText().trim());
				cv.setCom_addr2(joinHotelAddr2.getText().trim());
				cv.setCom_tel(joinHotelTel.getText().trim());
				cv.setCom_ceo(joinHotelOwnerName.getText().trim());
				cv.setCom_email(joinHotelEmail.getText().trim());
				
				try {
					ho_cnt = icomJoin.insertCom(cv);
					for(float i = 0; i <100000f; i++) {
					}
				}catch (RemoteException e1) {
					e1.printStackTrace();
				}
				if(ho_cnt > 0) {
					AlertMsg.info("회원가입 되셨습니다!! 퍼피델루나에 오신걸 환영합니다!!");
					Stage sta = (Stage) hotelJoinCheck.getScene().getWindow();
					
					Sendmail mail = new Sendmail();
					mail.setrecipient(joinHotelEmail.getText().trim());
					mail.setsubject(joinHotelName.getText().trim() + "님의 퍼피 델루나 가입을 환영합니다");
					mail.setbody("환영합니다"+"\r\n" +
							"\r\n" +joinHotelOwnerName.getText().trim()+" 님 회원가입이 완료 되었습니다.\r\n"+
							"\r\n" + " 앞으로 많은 이용 부탁드립니다 이 편지는 영국에서 최초로 시작되어 일 년에 한 바퀴 돌면서 받는 사람에게 행운을 주었고 지금은 당신에게로 옮겨진 이 편지는 4일 안에 당신 곁을 떠나야 합니다.\r\n" + 
							"\r\n" + 
							"이 편지를 포함해서 7통을 행운이 필요한 사람에게 보내 주셔야 합니다. 복사를 해도 좋습니다. 혹 미신이라 하실지 모르지만 사실입니다.\r\n" + 
							"\r\n" + 
							"영국에서 HGXWCH이라는 사람은 1930년에 이 편지를 받았습니다. 그는 비서에게 복사해서 보내라고 했습니다. 며칠 뒤에 복권이 당첨되어 그는 20억을 받았습니다.\r\n" + 
							"\r\n" + 
							"어떤 이는 이 편지를 받았으나 96시간 이내 자신의 손에서 떠나야 한다는 사실을 잊었습니다. 그는 곧 사직되었습니다. 나중에야 이 사실을 알고 7통의 편지를 보냈는데 다시 좋은 직장을 얻었습니다.\r\n" + 
							"\r\n" + 
							"쌀나라의 케네디 대통령은 이 편지를 받았지만 그냥 버렸습니다. 결국 9일 후 그는 암살당했습니다.\r\n" + 
							"\r\n" + 
							"기억해 주세요. 이 편지를 보내면 7년의 행운이 있을 것이고 그렇지 않으면 3년의 불행이 있을 것입니다. 그리고 이 편지를 버리거나 낙서를 해서는 절대로 안됩니다. 7통입니다.\r\n" + 
							"\r\n" + 
							"이 편지를 받은 사람은 행운이 깃들 것입니다. 힘들겠지만 좋은 게 좋다고 생각하세요. 7년의 행운을 빌면서...");
				
					//첨부파일넣기
				//	mail.setFile("D:\\A_TeachingMaterial\\5.MiddleProject\\other\\poto.jpg");
					
					try {
						mail.Send();
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
								
					try {
						LoginSession.startMain.HotelLink();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
			
				}else {
					AlertMsg.caution("회원가입에 실패하였습니다. 정보를 다시 확인해주세요");
				}
			}else {
				AlertMsg.caution("회원가입에 실패하였습니다. 정보를 다시 확인해주세요");
				chk_cnt = 0;
				pattern_cnt = 0;
				no_cnt = 0;
			}
		});
		
		//취소버튼 처리
		cancle.setOnAction(e->{
//			Stage stage = (Stage)cancle.getScene().getWindow();
//			stage.close();
			try {
				LoginSession.startMain.HotelLink();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
	}

}








