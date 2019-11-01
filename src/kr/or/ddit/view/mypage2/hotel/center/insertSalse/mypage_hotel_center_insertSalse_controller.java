package kr.or.ddit.view.mypage2.hotel.center.insertSalse;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.dogSaleAdd.DogSaleAddService;
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.vo.ComVO;
import kr.or.ddit.vo.DogSaleAddVO;
import javafx.scene.image.ImageView;


//서현작업중 검색기능은 없애주면 안되나여 전 자신이 없...
public class mypage_hotel_center_insertSalse_controller implements Initializable{

	@FXML TextField title;
	@FXML TextField dog_name;
	@FXML TextField dog_gu;
	@FXML TextField dog_size;
	@FXML TextField dog_gen;
	@FXML TextField dog_bir;
	@FXML TextField dog_detail;
	@FXML Button commit;
	@FXML Button cancel;
	@FXML Button photo;
	@FXML TableView<DogSaleAddVO> table_check;
	@FXML TableColumn<DogSaleAddVO, Integer> col_no;
	@FXML TableColumn<DogSaleAddVO, String> col_name;
	@FXML TableColumn<DogSaleAddVO, String> col_gu;
	@FXML TableColumn<DogSaleAddVO, String> col_size;
	@FXML TableColumn<DogSaleAddVO, String> col_gen;
	@FXML TableColumn<DogSaleAddVO, String> col_bir;
	@FXML TableColumn<DogSaleAddVO, String> col_detail;
	@FXML TableColumn<DogSaleAddVO, String> col_detail1;
	@FXML Pagination page;
	@FXML TextArea textarea;
	@FXML Button modify;
	@FXML Button delete;
	@FXML ImageView imgview; 
	@FXML ImageView page2;

	private ObservableList<DogSaleAddVO> AllTableData, currentPageData;
	private int from , to , itemsForPage;
	
	private ArrayList<DogSaleAddVO> list = new ArrayList<>();
	private DogSaleAddService dAs;
	private Registry reg;
	
	//로그인 유저 정보 받기
		ComVO cv = LoginSession.comsession;
		int user = cv.getCom_no();
		
	// VO 객체 생성
	private	DogSaleAddVO vo= new DogSaleAddVO();
	

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost",8888);
			dAs = (DogSaleAddService)reg.lookup("DogSaleAddService");
		}catch(RemoteException e) {
			e.printStackTrace();
		}catch(NotBoundException e) {
			e.printStackTrace();
		}
		
		//테이블 리스트에 추가
		col_no.setCellValueFactory(new PropertyValueFactory<>("dog_no"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("dog_name"));
		col_gu.setCellValueFactory(new PropertyValueFactory<>("dog_gu"));
		col_size.setCellValueFactory(new PropertyValueFactory<>("dog_size"));
		col_gen.setCellValueFactory(new PropertyValueFactory<>("dog_gender"));
		col_bir.setCellValueFactory(new PropertyValueFactory<>("dog_bir"));
		col_detail.setCellValueFactory(new PropertyValueFactory<>("dog_intro"));
		col_detail1.setCellValueFactory(new PropertyValueFactory<>("dog_infodetail"));
		
		AllTableData = FXCollections.observableArrayList();
		
		try {
//			list = (ArrayList<DogSaleAddVO>) dAs.AllSaleAddList(vo.getCom_no());
			list = (ArrayList<DogSaleAddVO>) dAs.AllSaleAddList(1234567893);
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}
		
		AllTableData.setAll(list);
		AllTableData.add(vo);
		
		table_check.setItems(AllTableData);
		
		String dtitle = title.getText().trim();
		String name = dog_name.getText().trim();
		String gu = dog_gu.getText().trim();
		String size = dog_size.getText().trim();
		String gen = dog_gen.getText().trim();
		String bir = dog_bir.getText().trim();
		String detail = dog_detail.getText().trim();
		String detail2 = textarea.getText().trim();
		
		List<DogSaleAddVO> dlist = new ArrayList<>();
		
		commit.setOnAction(e->{
			
			vo.setTitle(dtitle);
			vo.setDog_name(dog_name.getText().trim());
			vo.setDog_gu(dog_gu.getText().trim());
			vo.setDog_size(dog_size.getText().trim());
			vo.setDog_gender(dog_gen.getText().trim());
			vo.setDog_bir(dog_bir.getText().trim());
			vo.setDog_intro(dog_detail.getText().trim());
			vo.setDog_infodetail(textarea.getText().trim());
			
			int m_cnt =0;
			
			try {
				vo.setCom_no(1234567893);
//				vo.setCom_no(Session.comsession.getCom_no);
				m_cnt = dAs.insertDogSaleAdd(vo);
			}catch(RemoteException e1) {
				e1.printStackTrace();
			}
			
			
			if(m_cnt >0	) {
				AlertMsg.info("분양 강아지 등록 완료~");
				Stage sta = (Stage)commit.getScene().getWindow();

			}else {
				AlertMsg.caution("강아지 등록 실패...");
			}
			
			//테이블 리스트에 추가
			col_no.setCellValueFactory(new PropertyValueFactory<>("dog_no"));
			col_name.setCellValueFactory(new PropertyValueFactory<>("dog_name"));
			col_gu.setCellValueFactory(new PropertyValueFactory<>("dog_gu"));
			col_size.setCellValueFactory(new PropertyValueFactory<>("dog_size"));
			col_gen.setCellValueFactory(new PropertyValueFactory<>("dog_gender"));
			col_bir.setCellValueFactory(new PropertyValueFactory<>("dog_bir"));
			col_detail.setCellValueFactory(new PropertyValueFactory<>("dog_intro"));
			col_detail1.setCellValueFactory(new PropertyValueFactory<>("dog_infodetail"));
			
			AllTableData = FXCollections.observableArrayList();
			
			try {
//				list = (ArrayList<DogSaleAddVO>) dAs.AllSaleAddList(vo.getCom_no());
				list = (ArrayList<DogSaleAddVO>) dAs.AllSaleAddList(1234567893);
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
			
			AllTableData.setAll(list);
			AllTableData.add(vo);
			
			table_check.setItems(AllTableData);
			
			dog_name.clear();
			dog_gu.clear();
			dog_size.clear();
			dog_gen.clear();
			dog_bir.clear();
			dog_detail.clear();
			textarea.clear();
			
			itemsForPage = 5; // 한페이지에 보여줄 항목 수 설정
			int totPageCount = AllTableData.size()%itemsForPage == 0?
					AllTableData.size()/itemsForPage :
						AllTableData.size()/itemsForPage +1;
					
			page.setPageCount(totPageCount); // 전체 페이지수 설정
					
			page.setPageFactory(pageIndex->{
				
				from = pageIndex*itemsForPage;
				to = from + itemsForPage -1;
				
				table_check.setItems(getTableViewData(from, to));
				
				return table_check;
			});
			
		});
		
		cancel.setOnAction(e->{
			AlertMsg.info("취소되었습니다");
			Stage sta = (Stage)cancel.getScene().getWindow();
			sta.close();
		});
/**
  *  table에서 값 받는거 여기부터야~
  */
		
		table_check.setOnMouseClicked(e->{
			//TableView에서 선택한 줄의 데이터를 얻는다.
			DogSaleAddVO dooog = table_check.getSelectionModel().getSelectedItem();
			
			dog_name.setText(dooog.getDog_name());
			dog_gu.setText(dooog.getDog_gu());
			dog_size.setText(dooog.getDog_size());
			dog_gen.setText(dooog.getDog_gender());
			dog_bir.setText(dooog.getDog_bir());
			dog_detail.setText(dooog.getDog_intro());
			textarea.setText(dooog.getDog_infodetail());
			
			
		});
		
		delete.setOnAction(e->{
			//delete하기
			if(table_check.getSelectionModel().isEmpty()) {
				errMsg("작업오류","삭제할 자료를 선택해주세요");
				return;
			}
			
			DogSaleAddVO petDog = table_check.getSelectionModel().getSelectedItem();
			try {
				int cnt = dAs.deleteDogSaleAdd(petDog);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			infoMsg("작업결과", "강아지 "+dog_name.getText()+"를 삭제했습니다");
			
			//테이블 리스트에 추가
			col_no.setCellValueFactory(new PropertyValueFactory<>("dog_no"));
			col_name.setCellValueFactory(new PropertyValueFactory<>("dog_name"));
			col_gu.setCellValueFactory(new PropertyValueFactory<>("dog_gu"));
			col_size.setCellValueFactory(new PropertyValueFactory<>("dog_size"));
			col_gen.setCellValueFactory(new PropertyValueFactory<>("dog_gender"));
			col_bir.setCellValueFactory(new PropertyValueFactory<>("dog_bir"));
			col_detail.setCellValueFactory(new PropertyValueFactory<>("dog_intro"));
			col_detail1.setCellValueFactory(new PropertyValueFactory<>("dog_infodetail"));
			
			AllTableData = FXCollections.observableArrayList();
			
			try {
//				list = (ArrayList<DogSaleAddVO>) dAs.AllSaleAddList(vo.getCom_no());
				list = (ArrayList<DogSaleAddVO>) dAs.AllSaleAddList(1234567893);
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
			
			AllTableData.setAll(list);
			AllTableData.add(vo);
			
			table_check.setItems(AllTableData);
			
			//입력창 초기화
			dog_name.clear();
			dog_gu.clear();
			dog_size.clear();
			dog_gen.clear();
			dog_bir.clear();
			dog_detail.clear();
			textarea.clear();
			
		});
		
		modify.setOnAction(e->{
			if(dog_name.getText().isEmpty()
				|| dog_gu.getText().isEmpty()
				|| dog_size.getText().isEmpty()
				|| dog_gen.getText().isEmpty()
				|| dog_bir.getText().isEmpty()
				|| dog_detail.getText().isEmpty()
				|| textarea.getText().isEmpty()) {
				
				errMsg("작업 오류", "빈 항목이 있습니다.");
				return;
			}
			
			if(!Pattern.matches("^[0-9]+$", dog_bir.getText())) {
				errMsg("오류", "생일은 정수로 입력하세요");
				dog_bir.requestFocus(); //해당객체에 focus주기
				
				return;
			}
			
			DogSaleAddVO pet2 = table_check.getSelectionModel().getSelectedItem();
			
			pet2.setDog_name(dog_name.getText());
			pet2.setDog_gu(dog_gu.getText());
			pet2.setDog_size(dog_size.getText());
			pet2.setDog_gender(dog_gen.getText());
			pet2.setDog_bir(dog_bir.getText());
			pet2.setDog_intro(dog_detail.getText());
			pet2.setDog_infodetail(textarea.getText());

			int cnt = 0;
			try {
				cnt = dAs.updateDogSaleAdd(pet2);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			if(cnt == 0) {
				infoMsg("작업결과", dog_name.getText()+"수정실패!");
			}else {
				infoMsg("작업결과", dog_name.getText()+"수정완료!");
			}
			//테이블 리스트에 추가
			col_no.setCellValueFactory(new PropertyValueFactory<>("dog_no"));
			col_name.setCellValueFactory(new PropertyValueFactory<>("dog_name"));
			col_gu.setCellValueFactory(new PropertyValueFactory<>("dog_gu"));
			col_size.setCellValueFactory(new PropertyValueFactory<>("dog_size"));
			col_gen.setCellValueFactory(new PropertyValueFactory<>("dog_gender"));
			col_bir.setCellValueFactory(new PropertyValueFactory<>("dog_bir"));
			col_detail.setCellValueFactory(new PropertyValueFactory<>("dog_intro"));
			col_detail1.setCellValueFactory(new PropertyValueFactory<>("dog_infodetail"));
			
			AllTableData = FXCollections.observableArrayList();
			
			try {
//				list = (ArrayList<DogSaleAddVO>) dAs.AllSaleAddList(vo.getCom_no());
				list = (ArrayList<DogSaleAddVO>) dAs.AllSaleAddList(1234567893);
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
			
			AllTableData.setAll(list);
			AllTableData.add(vo);
			
			table_check.setItems(AllTableData);
			
			//입력창 초기화
			dog_name.clear();
			dog_gu.clear();
			dog_size.clear();
			dog_gen.clear();
			dog_bir.clear();
			dog_detail.clear();
			textarea.clear();
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
	/*public void fileChoose() {
		FileChooser fs = new FileChooser();
	}*/
	
	/**
	 * TableView에 채워줄 데이터 가져오는 메서드
	 * @param from
	 * @param to
	 * @return
	 */
	
	protected ObservableList<DogSaleAddVO> getTableViewData(int from, int to) {
		
		currentPageData = FXCollections.observableArrayList();//초기화
		int totSize = AllTableData.size();
		
		for(int i = from; i<=to && i<totSize; i++) {
			currentPageData.add(AllTableData.get(i));
		}
		return currentPageData;
	}


}






























