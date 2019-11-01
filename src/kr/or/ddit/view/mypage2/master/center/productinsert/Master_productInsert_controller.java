package kr.or.ddit.view.mypage2.master.center.productinsert;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.prod.ProdService;
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.vo.ComReservCheckVO;
import kr.or.ddit.vo.DogSaleAddVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;
import javafx.scene.control.TextArea;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Pagination;
import javafx.scene.control.ChoiceBox;

public class Master_productInsert_controller implements Initializable{

	// 상품 등록
	@FXML TextField prodNameTF;
	@FXML TextField prodCntTF;
	@FXML TextField prodPriceTF;
	@FXML TextArea prodDetailTA;
	@FXML ChoiceBox<String> prodGuCB;
	@FXML TextField prodManuTF;
	@FXML Button cancelBtn;
	@FXML Button confirmBtn;
	
	// 상품 검색
	@FXML Button prodFindBtn;
	@FXML TextField prodFindTF;
	
	// 상품 테이블 뷰
	@FXML TableView<ProdVO> prodTable;
	@FXML TableColumn<ProdVO, Integer> prodIdCol;
	@FXML TableColumn<ProdVO, String> prodNameCol;
	@FXML TableColumn<ProdVO, Integer> prodStockCol;
	@FXML TableColumn<ProdVO, Integer> prodCostCol;
	@FXML TableColumn<ProdVO, String> prodGuCol;
	@FXML TableColumn<ProdVO, String> prodInfoCol;
	@FXML TableColumn<ProdVO, String> prodComCol;
	@FXML Pagination pnation;
	
	// 상품 수정, 삭제
	@FXML Button prodModiBtn;
	@FXML Button prodDelBtn;
	
	// 이미지 삽입
	@FXML TextField fileAddTF;
	@FXML Button fileAddBtn;
	
	private ObservableList<ProdVO> AllTableData, currentPageData;
	private int from , to , itemsForPage;
	int totPageCnt = 0;
	
	private ArrayList<ProdVO> prod_list = new ArrayList<>();
	private ProdService ps;
	private Registry reg;
	
	// 로그인 마스터 정보 받기
	MemberVO mv = LoginSession.session;
	int master = mv.getMem_no();
	
	private ProdVO vo = new ProdVO();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			ps = (ProdService)reg.lookup("shopMain");
			prod_list = (ArrayList<ProdVO>) ps.getAllProd();
			
		}catch(RemoteException e) {
			e.printStackTrace();
		}catch(NotBoundException e) {
			e.printStackTrace();
		}
		
		ObservableList<String> gu = 
	            FXCollections.observableArrayList(
	                  "종류를 설정해 주세요", "A1","B1","C1","D1"
	                  );
		prodGuCB.setItems(gu);
		prodGuCB.setValue("종류를 설정해 주세요");

		
		prodIdCol.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
		prodNameCol.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
		prodStockCol.setCellValueFactory(new PropertyValueFactory<>("prod_sock"));
		prodCostCol.setCellValueFactory(new PropertyValueFactory<>("prod_cost"));
		prodGuCol.setCellValueFactory(new PropertyValueFactory<>("prod_gu"));
		prodInfoCol.setCellValueFactory(new PropertyValueFactory<>("prod_info"));
		prodComCol.setCellValueFactory(new PropertyValueFactory<>("prod_com_info"));
		
		
		
		AllTableData = FXCollections.observableArrayList();
		AllTableData.setAll(prod_list);
		prodTable.setItems(AllTableData);
		
		itemsForPage = 11;
		
		totPageCnt = AllTableData.size()%itemsForPage == 0
				? AllTableData.size()/itemsForPage
				: AllTableData.size()/itemsForPage + 1;
		pnation.setPageCount(totPageCnt);		
		pnation.setPageFactory(this::createPage);
		
		// 등록 버튼 넣기
		confirmBtn.setOnAction(e -> {
			vo.setProd_name(prodNameTF.getText().trim());
			vo.setProd_cost(Integer.parseInt(prodPriceTF.getText().trim()));
			vo.setProd_sock(Integer.parseInt(prodCntTF.getText().trim()));
			vo.setProd_info(prodDetailTA.getText().trim());
			vo.setProd_gu(prodGuCB.getValue());
			vo.setProd_com_info(prodManuTF.getText().trim());
			
			int m_cnt = 0;
			
			try {
				m_cnt = ps.insertProd(vo);
			}catch(RemoteException e3) {
				e3.printStackTrace();
			}
			
			if(m_cnt > 0) {
				AlertMsg.info("물품 등록 완료");
			}else {
				AlertMsg.caution("물품 등록 실패!!!");
			}
			
			prodIdCol.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
			prodNameCol.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
			prodStockCol.setCellValueFactory(new PropertyValueFactory<>("prod_sock"));
			prodCostCol.setCellValueFactory(new PropertyValueFactory<>("prod_cost"));
			prodGuCol.setCellValueFactory(new PropertyValueFactory<>("prod_gu"));
			prodInfoCol.setCellValueFactory(new PropertyValueFactory<>("prod_info"));
			prodComCol.setCellValueFactory(new PropertyValueFactory<>("prod_com_info"));
			
			try {
				prod_list = (ArrayList<ProdVO>) ps.getAllProd();
			}catch (RemoteException e4) {
				e4.printStackTrace();
			}
			
			AllTableData = FXCollections.observableArrayList();
			AllTableData.setAll(prod_list);
			prodTable.setItems(AllTableData);
			
			itemsForPage = 11;
			
			totPageCnt = AllTableData.size()%itemsForPage == 0
					? AllTableData.size()/itemsForPage
					: AllTableData.size()/itemsForPage + 1;
			pnation.setPageCount(totPageCnt);		
			pnation.setPageFactory(this::createPage);
			
			prodNameTF.clear();
			prodPriceTF.clear();
			prodCntTF.clear();
			prodDetailTA.clear();
			prodManuTF.clear();
			
			prodGuCB.setValue("종류를 설정해 주세요");
		});
		
		prodFindBtn.setOnAction(e -> {
			String prodSear = prodFindTF.getText().trim();
			
			vo.setProd_id(Integer.parseInt(prodSear));
			vo.setProd_name(prodSear);
			vo.setProd_gu(prodSear);
			vo.setProd_com_info(prodSear);
			
			try {
				prod_list = (ArrayList<ProdVO>) ps.SearchProd(vo);
			}catch (RemoteException e4) {
				e4.printStackTrace();
			}
			
			prodIdCol.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
			prodNameCol.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
			prodStockCol.setCellValueFactory(new PropertyValueFactory<>("prod_sock"));
			prodCostCol.setCellValueFactory(new PropertyValueFactory<>("prod_cost"));
			prodGuCol.setCellValueFactory(new PropertyValueFactory<>("prod_gu"));
			prodInfoCol.setCellValueFactory(new PropertyValueFactory<>("prod_info"));
			prodComCol.setCellValueFactory(new PropertyValueFactory<>("prod_com_info"));
		
			AllTableData = FXCollections.observableArrayList();
			AllTableData.setAll(prod_list);
			prodTable.setItems(AllTableData);
			
			itemsForPage = 11;
			
			totPageCnt = AllTableData.size()%itemsForPage == 0
					? AllTableData.size()/itemsForPage
					: AllTableData.size()/itemsForPage + 1;
			pnation.setPageCount(totPageCnt);		
			pnation.setPageFactory(this::createPage);
			
			prodFindTF.clear();
		});
		
		// 테이블에서 선택한 줄의 데이터를 얻는다.
		prodTable.setOnMouseClicked(e -> {
			ProdVO pvo = prodTable.getSelectionModel().getSelectedItem();
			int stock = pvo.getProd_sock();
			
			prodNameTF.setText(pvo.getProd_name());
			prodCntTF.setText(String.valueOf(pvo.getProd_sock()));
			prodPriceTF.setText(String.valueOf(pvo.getProd_cost()));
			prodDetailTA.setText(pvo.getProd_info());
			prodManuTF.setText(pvo.getProd_com_info());
			prodGuCB.setValue(pvo.getProd_gu());
		});
		
		// 상품 수정
		prodModiBtn.setOnAction(e -> {
			if(prodNameTF.getText().isEmpty()
			|| prodCntTF.getText().isEmpty()
			|| prodPriceTF.getText().isEmpty()
			|| prodDetailTA.getText().isEmpty()
			|| prodManuTF.getText().isEmpty()) {
				AlertMsg.caution("빈항목이 있습니다.");
				return;
			}
			
			ProdVO pvo2 = prodTable.getSelectionModel().getSelectedItem();
			
			pvo2.setProd_name(prodNameTF.getText().trim());
			pvo2.setProd_cost(Integer.parseInt(prodPriceTF.getText().trim()));
			pvo2.setProd_sock(Integer.parseInt(prodCntTF.getText().trim()));
			pvo2.setProd_info(prodDetailTA.getText().trim());
			pvo2.setProd_gu(prodGuCB.getValue());
			pvo2.setProd_com_info(prodManuTF.getText().trim());
			
			int cnt = 0;
			try {
				cnt = ps.updateProd(pvo2);
			}catch (RemoteException e5) {
				e5.printStackTrace();
			}
			
			if(cnt != 0) {
				AlertMsg.info(prodNameTF.getText().trim() + "수정 완료!!");
			}else {
				AlertMsg.caution(prodNameTF.getText().trim() + "수정 실패!!!");
			}
			
			prodIdCol.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
			prodNameCol.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
			prodStockCol.setCellValueFactory(new PropertyValueFactory<>("prod_sock"));
			prodCostCol.setCellValueFactory(new PropertyValueFactory<>("prod_cost"));
			prodGuCol.setCellValueFactory(new PropertyValueFactory<>("prod_gu"));
			prodInfoCol.setCellValueFactory(new PropertyValueFactory<>("prod_info"));
			prodComCol.setCellValueFactory(new PropertyValueFactory<>("prod_com_info"));
			
			try {
				prod_list = (ArrayList<ProdVO>) ps.getAllProd();
			}catch (RemoteException e4) {
				e4.printStackTrace();
			}
			
			AllTableData = FXCollections.observableArrayList();
			AllTableData.setAll(prod_list);
			prodTable.setItems(AllTableData);
			
			itemsForPage = 11;
			
			totPageCnt = AllTableData.size()%itemsForPage == 0
					? AllTableData.size()/itemsForPage
					: AllTableData.size()/itemsForPage + 1;
			pnation.setPageCount(totPageCnt);		
			pnation.setPageFactory(this::createPage);
			
			prodNameTF.clear();
			prodPriceTF.clear();
			prodCntTF.clear();
			prodDetailTA.clear();
			prodManuTF.clear();
			
			prodGuCB.setValue("종류를 설정해 주세요");
		});
		
		prodDelBtn.setOnAction(e -> {
			if(prodTable.getSelectionModel().isEmpty()) {
				AlertMsg.caution("삭제할 자료를 선택해주세요");
				return;
			}
			
			ProdVO pdel = prodTable.getSelectionModel().getSelectedItem();
			
			
			try {
				int cnt = ps.deleteProd(pdel);
			}catch (RemoteException e6) {
				e6.printStackTrace();
			}
			
			AlertMsg.info("상품 " + prodNameTF.getText() + "삭제했습니다");
			
			prodIdCol.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
			prodNameCol.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
			prodStockCol.setCellValueFactory(new PropertyValueFactory<>("prod_sock"));
			prodCostCol.setCellValueFactory(new PropertyValueFactory<>("prod_cost"));
			prodGuCol.setCellValueFactory(new PropertyValueFactory<>("prod_gu"));
			prodInfoCol.setCellValueFactory(new PropertyValueFactory<>("prod_info"));
			prodComCol.setCellValueFactory(new PropertyValueFactory<>("prod_com_info"));
			
			try {
				prod_list = (ArrayList<ProdVO>) ps.getAllProd();
			}catch (RemoteException e4) {
				e4.printStackTrace();
			}
			
			AllTableData = FXCollections.observableArrayList();
			AllTableData.setAll(prod_list);
			prodTable.setItems(AllTableData);
			
			itemsForPage = 11;
			
			totPageCnt = AllTableData.size()%itemsForPage == 0
					? AllTableData.size()/itemsForPage
					: AllTableData.size()/itemsForPage + 1;
			pnation.setPageCount(totPageCnt);		
			pnation.setPageFactory(this::createPage);
			
			prodNameTF.clear();
			prodPriceTF.clear();
			prodCntTF.clear();
			prodDetailTA.clear();
			prodManuTF.clear();
			
			prodGuCB.setValue("종류를 설정해 주세요");
		});
		
		
	}
	// 페이지네이션용 메서드
	private Node createPage(int pageIndex) {
		from = pageIndex * itemsForPage;
		to = from + itemsForPage -1 ;
		prodTable.setItems(getTableViewData(from, to));
		
		return prodTable;
	}
	
	// 페이지네이션용 메서드
	private ObservableList<ProdVO> getTableViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList();
		int totSize = AllTableData.size();
		for( int i = from; i <= to && i < totSize; i++) {
			currentPageData.add(AllTableData.get(i));
		}
		return currentPageData;
	}

}
