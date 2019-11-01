package kr.or.ddit.view.mypage2.master.center.info_main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.memInfoUpdate.MemInfoUpdateService;
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.view.mypage2.master.main.mapage_Master_controller;
import kr.or.ddit.vo.MemberVO;
import javafx.scene.control.TextField;

public class Master_info_main_controller implements Initializable{

	
	public mapage_Master_controller mainCon;
	
	public mapage_Master_controller getMainCon() {
		return mainCon;
	}
	public void setMainCon(mapage_Master_controller mainCon) {
		this.mainCon = mainCon;
	}

	@FXML Button confirmBtn;
	@FXML TextField passTF;
	@FXML TextField idTF;
	
	private Registry reg;
	private MemInfoUpdateService is;
	
	MemberVO vo = LoginSession.session;
	
	String id = vo.getMem_id();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			is = (MemInfoUpdateService) reg.lookup("memInfoService");
		}catch(RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		idTF.setText(id);
		
		confirmBtn.setOnAction(e -> {
			String pw = vo.getMem_pw();
			
			if(passTF.getText().equals(pw)) {
				AlertMsg.info("관리자님 안녕하세요.");
				try {
					LoginSession.masterMain.myMasterInfoDetailLink();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {
				AlertMsg.caution("비밀번호를 다시 확인해주세요!");
			}
		});
	}
	
	
	
	@FXML public void moving() {
		System.out.println("dddd");
//		try {
//			mainCon.masterBlacklistLink();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	

}
