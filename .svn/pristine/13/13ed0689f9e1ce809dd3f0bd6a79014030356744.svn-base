package kr.or.ddit.view.mypage2.master.center.info_detail;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.memInfoUpdate.MemInfoUpdateService;
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.vo.MemberVO;
import javafx.scene.control.Button;

public class Master_info_detail_controller implements Initializable{

	
	@FXML TextField passTF;
	@FXML TextField repassTF;
	@FXML TextField telTF;
	@FXML TextField addr1TF;
	@FXML TextField addr2TF;
	@FXML TextField emailTF;
	@FXML Button confirmBtn;
	
	private Registry reg;
	private MemInfoUpdateService ms;
	
	MemberVO vo = LoginSession.session;
	String mem = vo.getMem_id();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			ms = (MemInfoUpdateService) reg.lookup("memInfoService");
		}catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		MemberVO mvo = new MemberVO();
		mvo.setMem_id(mem);
		
		try {
			mvo = ms.getDefaultInfo(mvo);
			
			passTF.setText(mvo.getMem_pw());
			telTF.setText(mvo.getMem_tel());
			addr1TF.setText(mvo.getMem_addr1());
			addr2TF.setText(mvo.getMem_addr2());
			emailTF.setText(mvo.getMem_email());
			
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
		confirmBtn.setOnAction(e -> {
			MemberVO mvo2 = new MemberVO();
			
			mvo2.setMem_id(mem);
			
			mvo2.setMem_pw(passTF.getText());
			mvo2.setMem_tel(telTF.getText());
			mvo2.setMem_addr1(addr1TF.getText());
			mvo2.setMem_addr2(addr2TF.getText());
			mvo2.setMem_email(emailTF.getText());
			
			if(!(passTF.getText().equals(repassTF.getText()))) {
				AlertMsg.caution("비밀번호를 똑같이 입력해주세요!");
				return;
			}
			
			if(passTF.getText().isEmpty() || repassTF.getText().isEmpty()
				|| telTF.getText().isEmpty() || addr1TF.getText().isEmpty()
				|| addr2TF.getText().isEmpty() || emailTF.getText().isEmpty()) {
				AlertMsg.caution("모든 정보를 입력해주세요!");
			}else {
				try {
					int cnt = ms.updateMember(mvo2);
					if(cnt > 0) {
						AlertMsg.info("내 정보가 수정되었습니다!");
						repassTF.clear();
					}
				}catch (Exception e2) {
					e2.printStackTrace();
					AlertMsg.caution("수정에 실패했습니다.");
				}
			}
		});
		
		
	}
	
	
}









