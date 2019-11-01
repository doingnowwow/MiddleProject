package kr.or.ddit.view.ShopReview;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.shopReview.ShopReviewService;
import kr.or.ddit.vo.ShopReviewVO;

public class ShopReviewController implements Initializable {

	@FXML
	private TableColumn<ShopReviewVO, String> review_no;
	@FXML
	private TableColumn<ShopReviewVO, String> view_writer;
	@FXML
	private TableColumn<ShopReviewVO, String> view_contetn;
	@FXML
	private TableColumn<ShopReviewVO, String> veiw_star;
	@FXML
	private TableColumn<ShopReviewVO, Date> date;
	@FXML
	private TableColumn<ShopReviewVO, Integer> mem_no;
	@FXML
	private TextArea content;
	@FXML
	private TextField writer;
	@FXML
	private TextField star;
	@FXML
	Button okBtn;
	@FXML
	Button updateBtn;
	@FXML
	Button deleteBtn;
	@FXML
	TableView<ShopReviewVO> tableView;

	ShopReviewService sp;

	private ObservableList<ShopReviewVO> AllTableData;

	private ShopReviewVO vo = new ShopReviewVO();
	
	
	public ShopReviewController() {

	}

	public static ArrayList<ShopReviewVO> list = new ArrayList<>();

	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<ShopReviewVO> data = FXCollections.observableArrayList();

		// 서버의 DB연결하기 .........실패..ㅠㅠ
		// 성공했는데 평점 날짜 안넘어옴 ..

		Registry reg;

		try {
			System.out.println("어디까지 넘어오니?");
			reg = LocateRegistry.getRegistry("localhost", 8888);
			sp = (ShopReviewService) reg.lookup("shopReviewService");
			System.out.println("불러오나?");
			list = (ArrayList<ShopReviewVO>) sp.ShopReviewAll();
			System.out.println("확인해보자");

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		
		

		AllTableData = FXCollections.observableArrayList(list);

		AllTableData.setAll(list);
		AllTableData.add(vo);
		
		tableView.setItems(AllTableData);

		review_no.setCellValueFactory(new PropertyValueFactory<>("re_no"));
		view_writer.setCellValueFactory(new PropertyValueFactory<>("re_writer"));
		view_contetn.setCellValueFactory(new PropertyValueFactory<>("re_cont"));
		veiw_star.setCellValueFactory(new PropertyValueFactory<>("re_star"));
		date.setCellValueFactory(new PropertyValueFactory<>("re_date"));
		mem_no.setCellValueFactory(new PropertyValueFactory<>("mem_no"));

		// String re_no=review_no.getText();
		String view_wirter = writer.getText();
		String view_contetn = content.getText();
		String veiw_star = star.getText();

		// SimpleDateFormat a = new SimpleDateFormat("yyyy-mm-dd");

		// String veiw_date = a.format(date);
		// String mem_no = mem_no.get

		
	
		
		// 등록버튼클릭
		okBtn.setOnAction(e -> {

			AllTableData.add(new ShopReviewVO(writer.getText(), content.getText(), star.getText()));

			ShopReviewVO sv = new ShopReviewVO();
			if(writer.getText() != null) {
				sv.setRe_writer(writer.getText());
				
			}else {
				errMsg("실패","작성자를 입력해주세요.");
				return;
			}
			if(content.getText()!=null) {
				sv.setRe_cont(content.getText());
			}else {
				errMsg("실패","내용을 입력햊쉐요!");
				return;
			}
			
			if(star.getText()!=null) {
				sv.setRe_star(star.getText());
			}else {
				errMsg("실패","평점을 입력해주세요");
				return;
			}


			/*
			 * try { sp.insertShopReveiw(sv); }catch(Exception z) { z.printStackTrace(); }
			 */

			// list.add(new ShopReviewVO(view_wirter,view_contetn,veiw_star));
			//
			// tableView.setItems(AllTableData);
			try {
				sp.insertShopReveiw(new ShopReviewVO(writer.getText(), content.getText(), star.getText()));
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			content.clear();
			writer.clear();
			star.clear();

		});

		// 수정버튼클릭
		updateBtn.setOnAction(e -> {
			
			
		/*	writer.setText(view_wirter);			
			content.setText(view_contetn);*/
		//	star.setText(view_star);
			
			
			AllTableData.set(tableView.getSelectionModel().getSelectedIndex(),
					new ShopReviewVO(writer.getText(), content.getText(), star.getText()));

			content.clear();
			writer.clear();
			star.clear();
			
			infoMsg("수정완료", "수정완료^^");

			// new ShopReviewVO(new ShopReviewVO(wirter.getText(), content.getText(),
			// star.getText()));

			// list.add(new ShopReviewVO(view_wirter,view_contetn,veiw_star));

			tableView.setItems(AllTableData);

		});

		// 삭제버튼클릭
		deleteBtn.setOnAction(e -> {
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("확인하세요!");
			alert.setHeaderText("정말 취소하시겠습니까?");
			alert.setContentText("취소하겟다구요?");
			
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get() == ButtonType.OK) {
				
				AllTableData.remove(tableView.getSelectionModel().getFocusedIndex());
				
				// ... user chose OK
			} else {
				// ... user chose CANCEL or closed the dialog
			}
			
			
			

			
		});

	}

	// 확인 알림메시지
	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("알림");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}
	
	// 에러 알림메시지
	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

}
