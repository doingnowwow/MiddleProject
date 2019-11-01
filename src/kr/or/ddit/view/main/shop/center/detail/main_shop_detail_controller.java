package kr.or.ddit.view.main.shop.center.detail;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.cart2.Cart2Service;
import kr.or.ddit.service.prod.ProdService;
import kr.or.ddit.service.shopReview.ShopReviewService;
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.view.main.shop.main.main_shop_controller;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.HotelReviewVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.ShopReviewVO;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;

public class main_shop_detail_controller implements Initializable{

	@FXML ImageView img_main;
	@FXML TextArea prod_detail_text;
	@FXML TextArea prod_com_text;
	@FXML TableView<ShopReviewVO> table;
	@FXML TableColumn<ShopReviewVO ,String> col_star;
	@FXML TableColumn<ShopReviewVO ,String> col_content;
	@FXML TableColumn<ShopReviewVO,String> col_writer;
	@FXML TableColumn<ShopReviewVO,String> col_date;
	@FXML Button btn_insert;
	@FXML Label title_lab;
	@FXML ChoiceBox<Integer> select_num;
	@FXML TextField text_review;
	@FXML Label select_num_lab;
	@FXML Button pay_btn;
	@FXML Button cart_btn;
	@FXML Label price_btn;
	@FXML TextField text_star;
	@FXML ImageView img_detail;
	@FXML ComboBox<String> combo_star;
	@FXML Pagination page;

	main_shop_controller prodselect;
	
	private String url1 = "/kr/or/ddit/view/prodImg/";
	private String url2 = ".jpg";
	
	private static  ArrayList<ImageView> ivList = new ArrayList<>();
	
	Cart2Service cs;
	
	//댓글
	private ArrayList<ShopReviewVO> srlist = new ArrayList<>();
	private ShopReviewVO srvo = new ShopReviewVO();
	
	
	// 페이지네이션
		private int from, to, itemsForpages;
		private ObservableList<ShopReviewVO> allTableData, currentPageData;
		int totPageCnt = 0;
	
	private Registry reg;
	private ProdService ps;
	private ShopReviewService srs;
	
	// 상품 정보를 담는 리스트
	private static List<ProdVO> prod_list = new ArrayList<>();
	
	// 선택한 상품 정보 저장
	ProdVO pcho;
	ProdVO pvo = new ProdVO();
	int prodNum = 0;
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		pcho = LoginSession.prodsssion;
		int prno = pcho.getProd_id();
		ShopReviewVO soho = new ShopReviewVO();
		soho.setProd_id(prno);
		MemberVO memin = LoginSession.session;
		int memno = memin.getMem_no();
		String memid = memin.getMem_id();
//		String star = soho.getRe_star();
		
		// 서버 연결
		
		try {
			reg = LocateRegistry.getRegistry("localhost",8888);
			ps = (ProdService) reg.lookup("shopMain");
			srs = (ShopReviewService) reg.lookup("shopReviewService");
			cs = (Cart2Service)reg.lookup("cart2Service");  
		}catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		ObservableList<Integer> num = 
	               FXCollections.observableArrayList(
	                    1,2,3,4,5,6,7,8,9,10
	                     );
	         select_num.setItems(num);
	         select_num.setValue(1);
		
		//가져온 상품 정보 출력 
		try { 
			prodNum = pcho.getProd_id();
			pcho.setProd_id(prodNum);
			prod_list = ps.getSelectedProd(pcho);
			
		}catch(RemoteException e) {
			e.printStackTrace();
		}
		
		//사진 넣어야해요 한선이 헬프
		
		
		String prodName = prod_list.get(0).getProd_name();
		title_lab.setText(prodName);
		
		int prodcost = prod_list.get(0).getProd_cost();
		price_btn.setText(Integer.toString(prodcost));
		
		String prodInfo = prod_list.get(0).getProd_info();
		prod_detail_text.setText(prodInfo);
		
		String prodComInfo = prod_list.get(0).getProd_com_info();
		prod_com_text.setText(prodComInfo);
		
		String shopimage = prod_list.get(0).getProd_img();
		img_main.setImage(new Image(shopimage));
		
		String shopimagedetail = prod_list.get(0).getProd_datil_img();
		img_detail.setImage(new Image(shopimagedetail));
		
		ObservableList<String> star1 = 
	            FXCollections.observableArrayList(
	                  "5","4","3","2","1"
	                  );
		combo_star.setItems(star1);
		combo_star.setValue("5");
		
		//댓글 나열하기
		
		try {
			prodNum = pcho.getProd_id();
			srvo.setProd_id(prodNum);
			srlist = (ArrayList<ShopReviewVO>) srs.selectProdReview(srvo);
		}catch(RemoteException e) {
			e.printStackTrace();
		}
		
		allTableData = FXCollections.observableArrayList(srlist);
		
		allTableData.setAll(srlist);

		table.setItems(allTableData);
		
		col_star.setCellValueFactory(new PropertyValueFactory<>("re_star"));
		col_content.setCellValueFactory(new PropertyValueFactory<>("re_cont"));
		col_writer.setCellValueFactory(new PropertyValueFactory<>("re_writer"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("re_date"));
		
		itemsForpages = 6;
		
		totPageCnt = allTableData.size()%itemsForpages == 0
				? allTableData.size()/itemsForpages
				: allTableData.size()/itemsForpages + 1;
		
		page.setPageCount(totPageCnt);
		page.setPageFactory(this::createPage);
	         
//		
//		String star2 = text_star.getText();
		  
		String view_cont = text_review.getText();
		btn_insert.setOnAction(e->{
			
		  int prodId = prod_list.get(0).getProd_id();
		  String content = text_review.getText();
		  String starre = combo_star.getValue();
		  SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		  Date time = new Date();
		  String sysdate = format.format(time);
		  
		  ShopReviewVO sr3 = new ShopReviewVO();
		  prodNum = pcho.getProd_id();
		  sr3.setMem_no(memno);
		  sr3.setProd_id(prodNum);
		  sr3.setRe_writer(memid);
		  sr3.setRe_star(starre);

		  if(text_review.getText() != null) {
			  sr3.setRe_cont(content);
		  }else {
			  AlertMsg.caution("실패, 내용을 입력하세용");
		  }
		  
		  try {
			  prodNum = pcho.getProd_id();
			  sr3.setProd_id(prodNum);
			  srs.insertShopReveiw2(sr3);
			  srlist = (ArrayList<ShopReviewVO>) srs.selectProdReview(srvo);
		  }catch(RemoteException e3) {
			  e3.printStackTrace();
		  }
		  allTableData = FXCollections.observableArrayList(srlist);
		  
		  allTableData.setAll(srlist);
		  
		  table.setItems(allTableData);
		  
		  col_star.setCellValueFactory(new PropertyValueFactory<>("re_star"));
		  col_content.setCellValueFactory(new PropertyValueFactory<>("re_cont"));
		  col_writer.setCellValueFactory(new PropertyValueFactory<>("re_writer")); 
		  col_date.setCellValueFactory(new PropertyValueFactory<>("re_date"));
		  
		  text_review.clear();
		  
		  itemsForpages = 6;
			
			totPageCnt = allTableData.size()%itemsForpages == 0
					? allTableData.size()/itemsForpages
					: allTableData.size()/itemsForpages + 1;
			
			page.setPageCount(totPageCnt);
			page.setPageFactory(this::createPage);
		  
	  });
		//결제페이지 만들어주셔야해요 ....
		pay_btn.setOnAction(e->{
			
			//결제페이지 넘어가게 만들어야함
			
	});
	
	
		cart_btn.setOnAction(e->{
				
		//	int prodCost = pcho.getProd_cost();
			int memNo = LoginSession.session.getMem_no();
			int prodId = pcho.getProd_id();
			int prodQty	= select_num.getValue();
			
		///////////////// 로그인세션에서 멤버 넘버를 못받아와요 ㅠㅠ
			
			CartVO cvo =  new CartVO();
			
			cvo.setCart_qty(prodQty);
			cvo.setMem_no(memNo);
			cvo.setProd_id(prodId);
			
			System.out.println("memNo = "+ cvo.getMem_no()
					+ " prodId =  " + prodId + " prodQtY = " +  prodQty);
			
			try {
				cs.insertCart(cvo);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			AlertMsg.info("해당 물품이 장바구니에 담겼습니다!");
		
		
			
		});
		
	
	}
	
	// datepicker 포맷바꿔주기
		public String formatChange(LocalDate ld) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    	String rtn = ld.format(dtf);
	    	return rtn;
		}
		// 페이지네이션용 메서드
		private Node createPage(int pageIndex) {
			from = pageIndex * itemsForpages;
			to = from + itemsForpages -1 ;
			table.setItems(getTableViewData(from, to));
			
			return table;
		}
		
		// 페이지네이션용 메서드
		private ObservableList<ShopReviewVO> getTableViewData(int from, int to) {
			currentPageData = FXCollections.observableArrayList();
			int totSize = allTableData.size();
			for( int i = from; i <= to && i < totSize; i++) {
				currentPageData.add(allTableData.get(i));
			}
			return currentPageData;
		}
	
}































