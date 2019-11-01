package kr.or.ddit.view.main.salse.center.cartPayCheck;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.cart2.Cart2Service;
import kr.or.ddit.service.member.MemberService;
import kr.or.ddit.service.point.PointService;
import kr.or.ddit.service.prod.ProdService;
import kr.or.ddit.service.prodPay.ProdPayService;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PointVO;
import kr.or.ddit.vo.ProdPayVO;
import javafx.scene.control.Label;

public class cart_payCheck_controller implements Initializable{

	@FXML ImageView prod_img;
	@FXML TextField save_mileage;
	@FXML TextField CartUse_mileage;
	@FXML TextField res_maileage;
	@FXML Text reserv_info;
	@FXML Button buyBtn;
	@FXML RadioButton credit;
	@FXML RadioButton bank;
	@FXML Text prodNmInfo;
	@FXML Text totalCost;
//	@FXML Text cartreserv_respay1;
	@FXML TextField beaSongNm;
	@FXML TextField beaSongAddr;
	@FXML TextArea beaSongInfo;
	
	// 네트워크 관련
	private Registry reg;
	Cart2Service cts;
	ProdService pros;
	ProdPayService pps;
	PointService cs_point;
	MemberService cs_mem;
	
	
	
	// 마일리지 내용 삽입
	String Mileagetxt;
	
	int usePoint; 
	
	int save_Mileage; // 저장될 최종 마일리지
	int insertMileage; // 적립금
	int res_insertMileage; //최종 적립 마일리지
	String save_Mileagetxt;
	String saveDate;
	
	
	// 가격 내용 삽입
	int cartPrice;
	
	int res_cartPrice;
	String res_cartPricetxt;
	
	
	
	String request;
	
	// 가져오기용 VO
	MemberVO memInfo = LoginSession.session;
	List<CartVO> ctInfo;
	List<ProdPayVO> ppInfo;

	
	//?!
	List<CartVO> cartInfo = new ArrayList<CartVO>();
	PointVO pointVO = new PointVO();
	
	int nowPoint = 0;
	

	
	// 마일리지 사용 여부
	boolean checkMileage;
	
	// 결제완료 화면으로 넘기기위한 객체 생성
	ProdPayVO save_resBook = new ProdPayVO();

	
	//
	CartVO cv1 = new CartVO();
	@FXML Label status;
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cv1.setMem_no(memInfo.getMem_no());
//		cv1.setMem_no(1);
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			cts = (Cart2Service) reg.lookup("cart2Service");
			pps = (ProdPayService) reg.lookup("prodPayService");
			cs_point = (PointService) reg.lookup("pointService");
			cs_mem = (MemberService) reg.lookup("memberService");
			
			
	//		 이제부터는 불러온 객체의 메서드를 호출해서 사용할 수 있다.
			//cartInfo = cs_cart.hoNoSearch(cart);
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e2) {
				e2.printStackTrace();
		}
		
		pointVO.setMem_no(LoginSession.session.getMem_no());
		
		try {
			cartInfo = cts.getAllList(cv1);
		} catch (RemoteException e3) {
			e3.printStackTrace();
		} //cartInfo에 정보담아주기

		int cId = cartInfo.get(0).getCart_id();
		
		///////////////////////////////////////////////////////////
		
		ProdPayVO ppvo = new ProdPayVO();
		ppvo.setCart_id(cId);
		ppvo.setMem_no(1);
		List<ProdPayVO> ppList = new ArrayList<ProdPayVO>();
		
		try {
			ppList = pps.getProdPayAllList(ppvo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	// 화면 상 정보 셋팅
		prod_img.setImage(new Image(ppList.get(0).getProd_img()));
		prodNmInfo.setText(ppList.get(0).getProd_name());
		totalCost.setText(ppList.get(0).getSumprice()+"");
		beaSongNm.setText(ppList.get(0).getMem_name());
		beaSongAddr.setText(ppList.get(0).getMem_addr1() + " " + ppList.get(0).getMem_addr2());
		
		///////////////////////////////////////////////////////////
		
		int ttCost = calAllCost(cartInfo);
		nowPoint = LoginSession.session.getMem_point();
		save_mileage.setText(nowPoint+"");
		
		//사용 마일리지 입력에 따른 이벤트
		CartUse_mileage.setOnKeyReleased(e->{
			
//			e.getText();
			String rtn = CartUse_mileage.getText().trim();
			
			if(CartUse_mileage.getText().equals("")) {
				rtn = "0";
				res_maileage.setText(nowPoint + "");
				totalCost.setText(ttCost + "");
				return;
			}
			
			usePoint = new Integer(rtn).intValue();
				
			if(nowPoint >= usePoint) {
				res_maileage.setText(nowPoint - usePoint + "");
				totalCost.setText(ttCost - usePoint + "");
			}else {
				res_maileage.setText("0");
				totalCost.setText(ttCost + "");
				status.setText("마일리지 허용치를 넘겼습니다.");
			}
		});
		
		buyBtn.setOnAction(e -> {
			System.out.println("buyBtn");
			infoMsg("결제", "결제되었습니다!");
			Stage stage = (Stage) buyBtn.getScene().getWindow();
		    stage.close();
		});
	}
	
	public int calAllCost(List<CartVO> list) {
		int allCost = 0;
		
		for (int i = 0; i < list.size(); i++) {
			allCost += list.get(i).getProd_total_cost();
		}
		
		totalCost.setText(allCost+"");
		return allCost;
	}
	
	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("알림");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}
}