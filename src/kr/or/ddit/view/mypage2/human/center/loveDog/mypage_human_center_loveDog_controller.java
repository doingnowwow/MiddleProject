package kr.or.ddit.view.mypage2.human.center.loveDog;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.like.LikeService;
import kr.or.ddit.vo.DogSaleAddVO;
import kr.or.ddit.vo.LikeVO;
import kr.or.ddit.vo.MemberVO;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class mypage_human_center_loveDog_controller implements Initializable{

	@FXML TableView<LikeVO> table_like;
	@FXML TableColumn<LikeVO,Integer> col_no;
	@FXML TableColumn<LikeVO,String> col_name;
	@FXML TableColumn<LikeVO,String> col_gu;
	@FXML TableColumn<LikeVO,String> col_gen;
//	@FXML TableColumn<LikeVO,ImageView> col_pic;
	@FXML Button delete;
	// 페이지네이션
		private int from, to, itemsForpages;
		private ObservableList<LikeVO> allTableData, currentPageData;
		int totPageCnt = 0;
	
	private Registry reg;
	private LikeService ls;
	
	//찜 내용 담는 리스트
	private static List<LikeVO> like_list = new ArrayList<>();
	
	LikeVO lv;
	int likeNum =0;
	
	private LikeVO vo = new LikeVO();
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		MemberVO memin = LoginSession.session;
		int memno = memin.getMem_no();
		String memid = memin.getMem_id();
		
		//서버연결
		try {
			reg = LocateRegistry.getRegistry("localhost",8888);
			ls = (LikeService) reg.lookup("LikeService");
			
		}catch(RemoteException e) {
			e.printStackTrace();
		}catch(NotBoundException e) {
			e.printStackTrace();
		}
		vo.setMem_no(memno);
		
		col_no.setCellValueFactory(new PropertyValueFactory<>("dlike_no"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("dog_name"));
		col_gen.setCellValueFactory(new PropertyValueFactory<>("dog_gender"));
		col_gu.setCellValueFactory(new PropertyValueFactory<>("dog_gu"));
		//col_pic.setCellValueFactory(new PropertyValueFactory<LikeVO,ImageView>("dog_picture"));
		
		allTableData = FXCollections.observableArrayList();
		
		//가져온 찜목록 출력
		try {
			vo.setMem_no(memno);
//			like_list = (ArrayList<LikeVO>) ls.dlikelist(15);
			like_list = (ArrayList<LikeVO>) ls.dlikelist(vo);
			
		}catch(RemoteException e) {
			e.printStackTrace();
		}
		
		
		
		allTableData.setAll(like_list);
//		allTableData.add(vo);
		table_like.setItems(allTableData);
		
		
		delete.setOnAction(e->{
			//delete하기
			if(table_like.getSelectionModel().isEmpty()) {
				errMsg("작업오류","삭제할 자료를 선택해주세요");
				return;
			}
			
			LikeVO dlike = table_like.getSelectionModel().getSelectedItem();
			dlike.setMem_no(memno);
			try {
				int cnt = ls.deleteDlike(dlike);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			infoMsg("작업결과", "강아지 "+col_name.getText()+"를 삭제했습니다");
			
			//테이블 리스트에 추가
			col_no.setCellValueFactory(new PropertyValueFactory<>("dlike_no"));
			col_name.setCellValueFactory(new PropertyValueFactory<>("dog_name"));
			col_gen.setCellValueFactory(new PropertyValueFactory<>("dog_gender"));
			col_gu.setCellValueFactory(new PropertyValueFactory<>("dog_gu"));
			
			allTableData = FXCollections.observableArrayList();
			
			try {
			
				like_list = (ArrayList<LikeVO>) ls.dlikelist(vo);
//				like_list = (ArrayList<LikeVO>) ls.dlikelist(15);
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
			
			allTableData.setAll(like_list);
			allTableData.add(vo);
			
			table_like.setItems(allTableData);
			
		
		});
		
		
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

}


















