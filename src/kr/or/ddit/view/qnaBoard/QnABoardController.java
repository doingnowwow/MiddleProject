package kr.or.ddit.view.qnaBoard;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.ddit.service.qna.QnAService;
import kr.or.ddit.vo.QnABoardVO;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Pagination;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class QnABoardController implements Initializable{
	private ArrayList<QnABoardVO> list = new ArrayList<>(); 
	private ObservableList<QnABoardVO> allTableData, currentPageData;
	private QnAService qnaservice;
	private QnABoardVO vo = new QnABoardVO();
	@FXML Button qnawirte;
	@FXML TableColumn<QnABoardVO, Integer> QA_NO;
	@FXML TableColumn<QnABoardVO, String> QA_WRITER;
	@FXML TableColumn<QnABoardVO, String> QA_TITLE;
	@FXML TableView<QnABoardVO> qnatable;
	@FXML 
	   private Pagination pagination;
	   
	   private int from, to, itemsForPage;
	   private Registry reg;
	@FXML Button qnawrite;
	@FXML Label total_number;
	

	 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		 //rmi연결
	      try {   
	         reg = LocateRegistry.getRegistry("localhost", 8888);
	         qnaservice = (QnAService) reg.lookup("qnaservice");
	         System.out.println("연결 성공");
	         
	      }catch (RemoteException e) {
	         System.out.println("실패 ㅋ");
	         e.printStackTrace();
	      }catch(NotBoundException e) {
	         e.printStackTrace();
	      }

		qnawirte.setOnAction(e->{
	    	 Parent change = null;
	         try {
	            change = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/qnaBoard/QandAWrite.fxml"));
	            Scene scene = new Scene(change);
	            Stage primaryStage = (Stage)qnawirte.getScene().getWindow();
	            primaryStage.setScene(scene);
	         } catch (IOException e1) {
	            e1.printStackTrace();
	            
	         }
	    
	     });
		
		
	      //테이블에 리스트 추가
		QA_NO.setCellValueFactory(new PropertyValueFactory<>("qa_no"));
		QA_WRITER.setCellValueFactory(new PropertyValueFactory<>("qa_writer"));
		QA_TITLE.setCellValueFactory(new PropertyValueFactory<>("qa_title"));
	
	   
	      allTableData = FXCollections.observableArrayList();
	      
	      try {
	         list = (ArrayList<QnABoardVO>) qnaservice.selectAllQnA(vo.getQa_no());
	      } catch (RemoteException e2) {
	         e2.printStackTrace();
	      }
	      
	      allTableData.setAll(list);
	      allTableData.add(vo);
	      
	      qnatable.setItems(allTableData);
	      
	      itemsForPage = 10; //한페이지에 보여줄 항목 수 설정
	      int totPageCount = allTableData.size()%itemsForPage == 0?
	            allTableData.size() / itemsForPage :
	            allTableData.size()/itemsForPage+1;
	            
	      pagination.setPageCount(totPageCount); //전체페이지 수 설정
	      
	      pagination.setPageFactory(new Callback<Integer, Node>(){

	         @Override
	         public Node call(Integer pageIndex) {
	            from = pageIndex * itemsForPage;
	            to = from + itemsForPage -1;
	            qnatable.setItems(getTableViewData(from, to));
	            return qnatable;
	         }

			private ObservableList<QnABoardVO> getTableViewData(int from, int to) {
				currentPageData = FXCollections.observableArrayList(); //초기화
			      int totSize = allTableData.size();
			         
			      for(int i=from; i<=to && i<totSize; i++) {
			         currentPageData.add(allTableData.get(i));
			      }
			      return currentPageData;
			}
	      });
	      
		
		
		
	}

	


}
