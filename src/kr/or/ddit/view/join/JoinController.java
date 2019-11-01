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

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import kr.or.ddit.api.Sendmail;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.join.IJoinService;
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.vo.MemberVO;

public class JoinController implements Initializable{
	//화면 탑부분
	
	@FXML Button humanJoinCheck;  // 가입 버튼 
	@FXML Button cancle;  // 취소 버튼 
	
	@FXML TextField joinHumanId;
	@FXML TextField joinHumanPass;
	@FXML TextField joinHumanPasscheck;
	@FXML TextField joinHumanName;
	@FXML TextField joinHumanPhon;
	@FXML TextField joinHumanAddr1;
	@FXML TextField joinHumanAddr2;
	@FXML TextField joinHumanEmail;
	@FXML Button emailCheck;

	@FXML Button idCheck1;
	//상태표시
	@FXML Label state;

	@FXML ToggleGroup size;
	@FXML ToggleGroup gender;
	@FXML BorderPane border;
	@FXML AnchorPane anchor;
	
	
//	public void radioSelect(ActionEvent event) {
//		if(joinHumanDogSize1.isSelected()) {
//			dogSizeSelect = joinHumanDogSize1.
//		}else if(joinHumanDogSize2.isSelected()) {
//			dogSizeSelect = joinHumanDogSize2.getText();
//		}else if(joinHumanDogSize3.isSelected()) {
//			dogSizeSelect = joinHumanDogSize3.getText();
//		}
//	}
	
	
//	public void radioGender(ActionEvent event) {
//		if(wDog.isSelected()) {
//			dogGenderSelect = wDog.getText();
//		}else if(mDog.isSelected()) {
//			dogGenderSelect = mDog.getText();
//		}
//	}
	
	private Registry reg;
	private IJoinService ijoin;
	
	List<MemberVO> m_list = new ArrayList<>();
	private MemberVO mv = new MemberVO();

	int chk_cnt = 0;
	int pattern_cnt = 0;
	int no_cnt = 0;
	
	//필수 회원정보 오류조사
	private boolean joinChk = true;
	@FXML RadioButton joinHuman;
	@FXML RadioButton joinHotel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//일반회원 가입정보(사람)
		String id = joinHumanId.getText().trim(); 
		String pass = joinHumanPass.getText().trim();
		String pass2 = joinHumanPasscheck.getText().trim();
		String name = joinHumanName.getText().trim();
		
		String addr = joinHumanAddr1.getText().trim();
		String addr2 = joinHumanAddr2.getText().trim();
		String email = joinHumanEmail.getText().trim();
		
		//phonCheck 폰번호 유효성검사
		//emailCheck 이메일 유효성검사
		
		state.setStyle("-fx-text-fill: red;");
		
		ToggleGroup radioT= new ToggleGroup();
		joinHotel.setToggleGroup(radioT);
		joinHuman.setToggleGroup(radioT);
		
		joinHuman.setSelected(true);
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			ijoin = (IJoinService) reg.lookup("userJoin");
		}catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}

		// 아이디 중복 체크 버튼 누르기
		idCheck1.setOnAction(e -> {
			String iduser = "^[a-zA-Z0-9]*$" ;
			boolean result = Pattern.matches(iduser, id);
			
			if(result == false) {
				AlertMsg.caution("에러! 아이디는 영어 대소문자와 숫자로 입력해주세요");
				return;
			}else if(joinHumanId.getText().trim().equals("") || joinHumanId.getText().trim() == null) {
				AlertMsg.caution("에러! 아이디를 입력하지 않았습니다!");
				return;
			}
			
			// 안돼서 바꿈 ㅅㅂ ㅠ 짜증나 
//			if(joinHumanId.getText().trim().equals("") || joinHumanId.getText().trim() == null) {
//				AlertMsg.caution("에러! 아이디를 입력하지 않았습니다!");
//				return;
//			}else if(result == false) {  
//				AlertMsg.caution("에러! 아이디는 영어 대소문자와 숫자 7~12자리로 입력해주세요");
//				return;
//			}
			
			ArrayList<MemberVO> mList = new ArrayList<>();
			
			MemberVO mv = new MemberVO();
			mv.setMem_id(joinHumanId.getText().trim());
			
			try {
				mList = (ArrayList<MemberVO>) ijoin.selectId(mv);
			}catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			if(mList.size() == 0) {
				state.setText("사용하실 수 있는 아이디입니다.");
				chk_cnt++;
				pattern_cnt++;
			}else {
				state.setText("이미 사용 중인 아이디 입니다!!!");
				return;
			}
		});
		
		emailCheck.setOnAction(e -> {
			
			ArrayList<MemberVO> eList = new ArrayList<>();
			
			MemberVO mv = new MemberVO();
			mv.setMem_email(email);
			
			try {
				eList = (ArrayList<MemberVO>) ijoin.selectEmail(mv);
			}catch(RemoteException e2) {
				e2.printStackTrace();
			}
			
			if(eList.size() == 0) {
				state.setText("사용하실 수 있는 이메일입니다.");
				chk_cnt++;
				pattern_cnt++;
			}else {
				state.setText("이미 사용 중인 이메일 입니다!!!");
				return;
			}
		});
		
		
		//아이디 입력 검사
		joinHumanId.setOnMouseClicked(e -> {
			if(id.isEmpty()) {
				state.setText("아이디를 입력하세요");
				joinHumanId.requestFocus();
				joinChk = false;
			}	
		});
		
		// 패스워드 입력 검사
		joinHumanPass.setOnMouseClicked(e -> {
			if(pass.isEmpty()) {
				state.setText("비밀번호를 입력하세요");
				joinHumanPass.requestFocus();
				joinChk = false;
			}
		});
		
		
		// 패스워드 입력 검사(비밀번호 확인칸)
		joinHumanPasscheck.setOnMouseClicked(e -> {
			if(pass2.isEmpty()) {
				state.setText("비밀번호를 다시 입력해주세요.");
				joinHumanPasscheck.requestFocus();
				joinChk = false;
			}
		});
		
		joinHumanName.setOnMouseClicked(e -> {
			if(name.isEmpty()) {
				state.setText("이름을 입력해주세요.");
				joinHumanName.requestFocus();
				joinChk = false;
			}
		});
		
		// 폰 입력 검사
		String phon = joinHumanPhon.getText().trim();
		joinHumanPhon.setOnMouseClicked(e -> {
			if(phon.isEmpty()) {
				state.setText("전화번호를 입력해주세요");
				joinHumanPhon.requestFocus();
				joinChk = false;
			}
		});
		
		// 주소 입력 검사
		joinHumanAddr1.setOnMouseClicked(e -> {
			if(addr.isEmpty()) {
				state.setText("주소를 입력해주세요");
				joinHumanAddr1.requestFocus();
				joinChk = false;
			}
		});
		
		joinHumanAddr2.setOnMouseClicked(e -> {
			if(addr2.isEmpty()) {
				state.setText("상세주소를 입력해주세요");
				joinHumanAddr2.requestFocus();
				joinChk = false;
			}
		});
		
		// 이메일 입력 검사
		joinHumanEmail.setOnMouseClicked(e -> {
			if(email.isEmpty()) {
				state.setText("이메일을 입력하세요");
				joinHumanEmail.requestFocus();
				joinChk = false;
			}
			
		});
		
		// 여기까지 입력 상태 체크---------------------------------------
		
		
		//가입버튼 처리--------
		humanJoinCheck.setOnAction(e->{
			
			// 비밀번호 확인 = 비밀번호 체크
			Pattern pwchk = Pattern.compile(pass);
			Matcher pwchkMatch = pwchk.matcher(pass2);
			boolean result = pwchkMatch.matches();
			if(result) {
				pattern_cnt++;
			}else if(joinHumanPasscheck.getText().equals("") || joinHumanPasscheck.getText() == null) {
				AlertMsg.caution("비밀번호 확인칸을 입력하지 않았습니다!");
				joinHumanPasscheck.requestFocus();
				no_cnt++;
				return;
			}else if(result == false) {
				AlertMsg.caution("비밀번호와 일치하지 않습니다!");
				joinHumanPasscheck.requestFocus();
				return;
			}
			
			if(joinHumanPhon.getText().equals("") || joinHumanPhon.getText() == null) {
				AlertMsg.caution("전화번호를 입력하지 않았습니다!");
				joinHumanPhon.requestFocus();
				no_cnt++;
				return;
			}
			
			if (joinHumanPass.getText().equals("") || joinHumanPass.getText() == null) {
				AlertMsg.caution("비밀번호를 입력하지 않았습니다!");
				joinHumanPass.requestFocus();
				no_cnt++;
				return;
			}
			
			if(joinHumanEmail.getText().equals("") || joinHumanEmail.getText() == null) {
				AlertMsg.caution("이메일을 입력하지 않았습니다!");
				no_cnt++;
				return;
			}
			
			if(joinHumanName.getText().equals("") || joinHumanName.getText() == null) {
				AlertMsg.caution("이름을 입력하지 않았습니다!");
				no_cnt++;
				return;
			}
			
			if(joinHumanAddr1.getText().equals("") || joinHumanAddr1.getText() == null) {
				AlertMsg.caution("주소를 입력하지 않았습니다!");
				no_cnt++;
				return;
			}
			
			if(joinHumanAddr2.getText().equals("") || joinHumanAddr2.getText() == null) {
				AlertMsg.caution("상세주소를 입력하지 않았습니다!");
				no_cnt++;
				return;
			}
			
			// 이메일 정규화 체크
		  /*else if(!Pattern.matches("^\\w*[.][A-Za-z]{2,3}(.kr)?", email)) {
			state.setText("이메일 입력형식이 올바르지 않습니다.");
			}   */
			
			if(chk_cnt == 2 && pattern_cnt == 3 && no_cnt == 0) {
				int m_cnt = 0;
				int d_cnt = 0;
				

				mv.setMem_id(joinHumanId.getText().trim());
				mv.setMem_name(joinHumanName.getText().trim());
				mv.setMem_pw(joinHumanPass.getText().trim());
				mv.setMem_tel(joinHumanPhon.getText().trim());
				mv.setMem_addr1(joinHumanAddr1.getText().trim());
				mv.setMem_addr2(joinHumanAddr2.getText().trim());
				mv.setMem_email(joinHumanEmail.getText().trim());
				mv.setMem_point(0);
				mv.setMem_level("브론즈");
				
				
				try {
					m_cnt = ijoin.insertMember(mv);
					for(float i = 0; i < 10000000f; i++) {
						
					}
				}catch(RemoteException e1) {
					e1.printStackTrace();
				}
				
				if(m_cnt > 0) {
					AlertMsg.info("회원가입 되셨습니다!! 퍼피델루나에 오신걸 환영합니다!!");
					Stage sta = (Stage) humanJoinCheck.getScene().getWindow();
//					try {
//						LoginSession.startMain.HotelLink();
//					} catch (IOException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
					
					Sendmail mail = new Sendmail();
					mail.setrecipient(joinHumanEmail.getText().trim());
					mail.setsubject(joinHumanName.getText().trim() + "님의 퍼피 델루나 가입을 환영합니다");
					mail.setbody("환영합니다"+"\r\n" +
							"\r\n" +joinHumanName.getText().trim()+" 님 회원가입이 완료 되었습니다.\r\n"+
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
					AlertMsg.caution("회원가입에 실패하였습니다.");
				}
			}else {
				AlertMsg.caution("회원가입에 실패하였습니다. 정보를 다시 확인해주세요");
				chk_cnt = 0;
				pattern_cnt = 0;
				no_cnt = 0;
			}
			
			
			
			
			
		});
		
//		humanJoinCheck.setOnAction(e -> {
//			if(pass.equals(pass2)) {
//				AlertMsg.info("비밀번호 일치입니다.");
//			}else {
//				
//			}
//					
//		});
		
		//취소버튼 처리
		cancle.setOnAction(e->{
			Stage stage = (Stage)cancle.getScene().getWindow();
			stage.close();
			try {
				LoginSession.startMain.HotelLink();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		
	}
	
	@FXML
	public void change() throws IOException {
//		AnchorPane root2 = FXMLLoader.load(getClass().getResource("join_Hotel.fxml"));
//		Checkmain.root.setCenter(root2);
		AnchorPane root2 = FXMLLoader.load(getClass().getResource("join_Hotel.fxml"));
		border.setCenter(root2);
//		Checkmain.root.setCenter(root2);
	}
	
	@FXML
	public void reChange() throws IOException {
//		BorderPane root3 = FXMLLoader.load(getClass().getResource("join_back.fxml"));
//		Checkmain.root.setCenter(root3.getCenter());
		AnchorPane root3 = FXMLLoader.load(getClass().getResource("join_back.fxml"));
		border.setCenter(((BorderPane)(root3.getChildren().get(0))).getCenter());
//		Checkmain.root.setCenter(root3);
	}


}
