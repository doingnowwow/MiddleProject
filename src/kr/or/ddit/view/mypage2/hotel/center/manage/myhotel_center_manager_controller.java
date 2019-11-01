package kr.or.ddit.view.mypage2.hotel.center.manage;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.com.ComService;
import kr.or.ddit.service.hotelUp.HotelUpService;
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.vo.ComVO;
import kr.or.ddit.vo.DogSaleAddVO;
import kr.or.ddit.vo.HotelUpVO;


public class myhotel_center_manager_controller implements Initializable{

//	@FXML ScrollPane hotel_manage_scroll;
//	
//	@FXML ImageView imgView;
//	@FXML Button file_img;
//	@FXML TextField file_info;
//	@FXML TextField ho_name;
//	@FXML TextField  com_no;
//	@FXML TextField addr1;
//	@FXML TextField addr2;
//	@FXML TextField hotel_tel;
//	@FXML TextArea hotel_intro;
//	@FXML Button file_detail;
//	@FXML TextField file_detail_info;
//	@FXML Button modify;
//	@FXML Button commit; // 등록버튼
//	@FXML ImageView imgView2;
	
	@FXML ScrollPane hotel_manage_scroll;
	
	@FXML ImageView imgView2;
	@FXML TextArea hotel_intro;
	@FXML TextField hotel_tel;
	@FXML TextField addr2;
	@FXML TextField addr1;
	@FXML TextField com_no;
	@FXML TextField ho_name;
	@FXML Button commit;
	@FXML Button modify;
	@FXML TextField file_detail_info;

	
	private Registry reg;
	private HotelUpService hs;
	private ComService cs;
	ComVO cv = LoginSession.comsession;
//	String id = vo.getCom_id(); // 아이디 가져오기
	
	
	List<HotelUpVO> h_list = new ArrayList<>();
	private HotelUpVO vo = new HotelUpVO();
	
	int chk_cnt = 0;
	int pattern_cnt = 0;
	int no_cnt = 0;





	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//db연결
		try {
			reg = LocateRegistry.getRegistry("localhost",8888);
			hs = (HotelUpService)reg.lookup("HotelUpService");
		}catch(RemoteException e) {
			e.printStackTrace();
		}catch(NotBoundException e) {
			e.printStackTrace();
		}
		
		vo.setCom_no(1234567893);
		//vo.setCom_no(com_no);
		
		
		
		try {
			h_list = hs.selectHotelList(vo);
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(h_list ==null) {
			return;
		}
		
		
		
		String name = ho_name.getText().trim();
		String com = com_no.getText().trim();
		String hoaddr1 = addr1.getText().trim();
		String hoaddr2 = addr2.getText().trim();
		String hoTel = hotel_tel.getText().trim();
		String intro = hotel_intro.getText().trim();
		
		int hono = h_list.get(0).getHotel_no();
			
		ho_name.setText(h_list.get(0).getHotel_name());
		com_no.setText(h_list.get(0).getCom_no()+"");
		addr1.setText(h_list.get(0).getHotel_addr1());
		addr2.setText(h_list.get(0).getHotel_addr2());
		hotel_tel.setText(h_list.get(0).getHotel_tel());
		hotel_intro.setText(h_list.get(0).getHotel_intro());
		
		
		commit.setOnAction(e->{
		
			vo.setHotel_name(ho_name.getText().trim());
			vo.setHotel_addr1(addr1.getText().trim());
			vo.setHotel_addr2(addr2.getText().trim());
			vo.setHotel_tel(hotel_tel.getText().trim());
			vo.setHotel_intro(hotel_intro.getText().trim());
			
			
			
			int m_cnt =0;

			
			try {
			//	vo.setCom_no(1234567893);
				int num = cv.getCom_no();
				vo.setCom_no(num);
				m_cnt = hs.insertHotelUp(vo);
			}catch(RemoteException e1) {
				e1.printStackTrace();
			}
			
			if(m_cnt > 0) {
				AlertMsg.info("호텔 등록되였습니다!");
				Stage sta =(Stage)commit.getScene().getWindow();
				sta.close();
			}else {
				AlertMsg.caution("호텔등록 실패 ");
			}
			
			
		});
		
		modify.setOnAction(e->{
			if(ho_name.getText().isEmpty()
				|| com_no.getText().isEmpty()
				|| addr1.getText().isEmpty()
				|| addr2.getText().isEmpty()
				|| hotel_tel.getText().isEmpty()
				|| hotel_intro.getText().isEmpty()) {
				
			
				errMsg("작업 오류", "빈 항목이 있습니다.");
				return;
			}
			
			if(!Pattern.matches("^[0-9]+$", com_no.getText())) {
				errMsg("오류", "생일은 정수로 입력하세요");
				com_no.requestFocus(); //해당객체에 focus주기
				
				return;
			}
			
			HotelUpVO ho2 = new HotelUpVO();
			
			ho2.setHotel_name(ho_name.getText());
			ho2.setHotel_addr1(addr1.getText());
			ho2.setHotel_addr2(addr2.getText());
			ho2.setHotel_tel(hotel_tel.getText());
			ho2.setHotel_intro(hotel_intro.getText());
			ho2.setCom_no(Integer.parseInt(com_no.getText()));
			ho2.setHotel_no(hono);

			int cnt = 0;
			try {
				cnt = hs.updateHotelUp(ho2);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			if(cnt == 0) {
				errMsg("작업결과", ho_name.getText()+"수정실패!");
			}else {
				infoMsg("작업결과", ho_name.getText()+"수정완료!");
			}
		});
		
		
		
		
//		cancel.setOnAction(e->{
//			AlertMsg.info("취소되었습니다");
//			Stage sta = (Stage)cancel.getScene().getWindow();
//			sta.close();
//		});
		
		/*file_img.setOnAction(e ->{
			FileChooser chooser = new FileChooser();
			
			chooser.getExtensionFilters().addAll(
					new ExtensionFilter("Image Files","*.png,*.jpg,*.gif"));
			
			
		});*/
		
		
		
	}
	public void fileChoose() {
		// 사진 선택 창
		FileChooser chooser = new FileChooser();
		chooser.setTitle("이미지 선택");

		chooser.setInitialDirectory(new File("C:/"));// default디렉토리 설정
		// 선택한 파일 정보 추출
		// 확장자 제한
		ExtensionFilter imgType = new ExtensionFilter("image File","*.png","*.jpg","*.gif");
		chooser.getExtensionFilters().add(imgType);
		
		File selectedFile = chooser.showOpenDialog(null);
		
		System.out.println(selectedFile);
		
//		//파일을 InputStream으로 읽어옴
//		try {
//			//파일읽어오기
//			FileInputStream fis = new FileInputStream(selectedFile);
//			BufferedInputStream bis = new BufferedInputStream(fis);
//			//이미지 생성하기
//			Image img = new Image(bis);
//			//이미지 띄우기
//			imgView.setImage(img); 
//		}catch(FileNotFoundException e) {
//			e.printStackTrace();
//		}
	}
		public void fileChoose2() {
			// 사진 선택 창
			FileChooser chooser2 = new FileChooser();
			chooser2.setTitle("호텔 이미지선택");

			chooser2.setInitialDirectory(new File("C:/"));// default디렉토리 설정
			// 선택한 파일 정보 추출
			// 확장자 제한
			ExtensionFilter imgType2 = new ExtensionFilter("image File","*.png","*.jpg","*.gif");
			chooser2.getExtensionFilters().add(imgType2);
			
			File selectedFile2 = chooser2.showOpenDialog(null);
			
			System.out.println(selectedFile2);
			
			//파일을 InputStream으로 읽어옴
			try {
				//파일읽어오기
				FileInputStream fis = new FileInputStream(selectedFile2);
				BufferedInputStream bis = new BufferedInputStream(fis);
				//이미지 생성하기
				Image img2 = new Image(bis);
				//이미지 띄우기
				imgView2.setImage(img2); 
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
	}
	
		
		public void errMsg(String headerText, String msg) {
			Alert errAlert = new Alert(AlertType.ERROR);
			errAlert.setTitle("오류");
			errAlert.setHeaderText(headerText);
			errAlert.setContentText(msg);
			errAlert.showAndWait();
		}
		
		public void infoMsg(String headerText, String msg) {
			Alert infoMsg = new Alert(AlertType.INFORMATION);
			infoMsg.setTitle("정보 확인");
			infoMsg.setHeaderText(headerText);
			infoMsg.setContentText(msg);
			infoMsg.showAndWait();
		}
	//등록 누르면 다시 화면단 바뀌게
	public void myHotelInfo() throws IOException {
		commit = FXMLLoader.load(getClass().getResource("myhotel_center_manage.fxml"));
		
	}
	
	


}
