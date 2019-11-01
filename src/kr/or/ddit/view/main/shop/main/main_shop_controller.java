package kr.or.ddit.view.main.shop.main;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.prod.ProdService;
import kr.or.ddit.vo.DogSaleVO;
import kr.or.ddit.vo.ProdVO;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class main_shop_controller implements Initializable {
	


	
	// 쇼핑몰 서치 작업할게요 -을지-

	@FXML
	ImageView shopimg1;
	@FXML
	Label shopimg1_text1;

	@FXML
	ImageView shopimg2;
	@FXML
	Label shopimg1_text2;

	@FXML
	ImageView shopimg3;
	@FXML
	Label shopimg1_text3;

	@FXML
	ImageView shopimg4;
	@FXML
	Label shopimg1_text4;

	@FXML
	ImageView shopimg5;
	@FXML
	Label shopimg1_text5;

	@FXML
	ImageView shopimg6;
	@FXML
	Label shopimg1_text6;

	@FXML
	ImageView shopimg7;
	@FXML
	Label shopimg1_text7;

	@FXML
	ImageView shopimg8;
	@FXML
	Label shopimg1_text8;

	@FXML
	ImageView shopimg9;
	@FXML
	Label shopimg1_text9;

	@FXML
	ImageView shopimg10;
	@FXML
	Label shopimg1_text10;

	@FXML
	ImageView shopimg11;
	@FXML
	Label shopimg1_text11;
	
	@FXML 
	ImageView shopimg12;
	@FXML
	Label shopimg1_text12;

	@FXML ComboBox<String> proGu;
	@FXML TextField pNmTF;
	@FXML Button searchBtn;
	
	private String url1 = "/kr/or/ddit/view/prodImg/";
	private String url2 = ".jpg";

	
	
	// 클릭한 상품정보
	ProdVO plog = LoginSession.prodsssion;
	int prono = 0;
	public static ArrayList<ProdVO> list = new ArrayList<>();
	private static  ArrayList<ImageView> ivList = new ArrayList<>();
	private static ArrayList<Label> nameList = new ArrayList<>();
	
	private ObservableList<ProdVO> AllTableData;
	
	List<ProdVO> prodList = new ArrayList<>();
	
	private ProdService ps;
	Registry reg;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
		LoginSession.shopMain = this;
		//콤보박스 셋팅
		ObservableList<String> guList = FXCollections.observableArrayList("-전체-", "먹이/사료","생활용품","장난감","기타");
		proGu.setItems(guList);
		proGu.setValue("-전체-");
		// 디비연결부분
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			ps = (ProdService) reg.lookup("shopMain");

			ProdVO prodvo = new ProdVO();
			
			list = (ArrayList<ProdVO>) ps.getAllProd();
			
			System.out.println("size : " + list.size());
			AllTableData = FXCollections.observableArrayList(list);
			
			String pName ="";
			String pImg = "";
			int pId = 0;
			
			String[] pNames= new String[12];
			String[] pImgs = new String[12];
			int[] pIds = new int[12];
			
			for(int i = 0; i < AllTableData.size(); i++) {
				ProdVO pdata = AllTableData.get(i);
				
				pName = pdata.getProd_name();
				pImg = pdata.getProd_img();
				pId = AllTableData.get(i).getProd_id();
						
				pNames[i] = pName;
				pImgs[i] = pImg;
				pIds[i] = pId;
				
			//	pId = pdata.getProd_id();
			//	pIds[i] = pId;
			}

			shopimg1_text1.setText(pNames[0]);
			shopimg1_text2.setText(pNames[1]);
			shopimg1_text3.setText(pNames[2]);
			shopimg1_text4.setText(pNames[3]);
			shopimg1_text5.setText(pNames[4]);
			shopimg1_text6.setText(pNames[5]);
			shopimg1_text7.setText(pNames[6]);
			shopimg1_text8.setText(pNames[7]);
			shopimg1_text9.setText(pNames[8]);
			shopimg1_text10.setText(pNames[9]);
			shopimg1_text11.setText(pNames[10]);
			shopimg1_text12.setText(pNames[11]);
			String emptyImg ="\\mainshop\\empty.jpg";
			
			shopimg1.setImage(new Image(pImgs[0]==null ? emptyImg : pImgs[0]));
			shopimg2.setImage(new Image(pImgs[1]==null ? emptyImg : pImgs[1]));
			shopimg3.setImage(new Image(pImgs[2]==null ? emptyImg : pImgs[2]));
			shopimg4.setImage(new Image(pImgs[3]==null ? emptyImg : pImgs[3]));
			shopimg5.setImage(new Image(pImgs[4]==null ? emptyImg : pImgs[4]));
			shopimg6.setImage(new Image(pImgs[5]==null ? emptyImg : pImgs[5]));
			shopimg7.setImage(new Image(pImgs[6]==null ? emptyImg : pImgs[6]));
			shopimg8.setImage(new Image(pImgs[7]==null ? emptyImg : pImgs[7]));
			shopimg9.setImage(new Image(pImgs[8]==null ? emptyImg : pImgs[8]));
			shopimg10.setImage(new Image(pImgs[9]==null ? emptyImg : pImgs[9]));
			shopimg11.setImage(new Image(pImgs[10]==null ? emptyImg : pImgs[10]));
			shopimg12.setImage(new Image(pImgs[11]==null ? emptyImg : pImgs[11]));
			
			/*
			ivList.add(shopimg1);
			ivList.add(shopimg2);
			ivList.add(shopimg3);
			ivList.add(shopimg4);
			ivList.add(shopimg5);
			ivList.add(shopimg6);
			ivList.add(shopimg7);
			ivList.add(shopimg8);
			ivList.add(shopimg9);
			ivList.add(shopimg10);
			ivList.add(shopimg11);
			
			System.out.println("elqldusruf?");
			
			nameList.add(shopimg1_text1);
			nameList.add(shopimg1_text2);
			nameList.add(shopimg1_text3);
			nameList.add(shopimg1_text4);
			nameList.add(shopimg1_text5);
			nameList.add(shopimg1_text6);
			nameList.add(shopimg1_text7);
			nameList.add(shopimg1_text8);
			nameList.add(shopimg1_text9);
			nameList.add(shopimg1_text10);
			nameList.add(shopimg1_text11);
			
			prodList = new ArrayList<>();
			try {
				prodList=ps.getAllProd();
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int i = 0; i < prodList.size(); i++) {

				ivList.get(i).setImage(new Image(url1 + prodList.get(i).getProd_name().trim() + url2));
				nameList.get(i).setText(prodList.get(i).getProd_name().trim());
				//priceList.get(i).setText((prodList.get(i).getProd_cost()); 가겨넣으려다실패
				
				
			}
			*/
			
			shopimg1.setOnMouseClicked(e -> {
				ProdVO pvo2 = new ProdVO();
				pvo2.setProd_id(pIds[0]);
				LoginSession.prodsssion = pvo2;
				try {
					LoginSession.startMain.ShopDetailLink();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			
			shopimg2.setOnMouseClicked(e -> {
				ProdVO pvo2 = new ProdVO();
				pvo2.setProd_id(pIds[1]);
				LoginSession.prodsssion = pvo2;
				try {
					LoginSession.startMain.ShopDetailLink();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			
			shopimg3.setOnMouseClicked(e -> {
				ProdVO pvo2 = new ProdVO();
				pvo2.setProd_id(pIds[2]);
				LoginSession.prodsssion = pvo2;
				try {
					LoginSession.startMain.ShopDetailLink();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			});
		
			shopimg4.setOnMouseClicked(e -> {
				ProdVO pvo2 = new ProdVO();
				pvo2.setProd_id(pIds[3]);
				LoginSession.prodsssion = pvo2;
				try {
					LoginSession.startMain.ShopDetailLink();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			
			shopimg5.setOnMouseClicked(e -> {
				ProdVO pvo2 = new ProdVO();
				pvo2.setProd_id(pIds[4]);
				LoginSession.prodsssion = pvo2;
				try {
					LoginSession.startMain.ShopDetailLink();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			
			shopimg6.setOnMouseClicked(e -> {
				ProdVO pvo2 = new ProdVO();
				pvo2.setProd_id(pIds[5]);
				LoginSession.prodsssion = pvo2;
				try {
					LoginSession.startMain.ShopDetailLink();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			
			shopimg7.setOnMouseClicked(e -> {
				ProdVO pvo2 = new ProdVO();
				pvo2.setProd_id(pIds[6]);
				LoginSession.prodsssion = pvo2;
				try {
					LoginSession.startMain.ShopDetailLink();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			
			shopimg8.setOnMouseClicked(e -> {
				ProdVO pvo2 = new ProdVO();
				pvo2.setProd_id(pIds[7]);
				LoginSession.prodsssion = pvo2;
				try {
					LoginSession.startMain.ShopDetailLink();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			
			shopimg9.setOnMouseClicked(e -> {
				ProdVO pvo2 = new ProdVO();
				pvo2.setProd_id(pIds[8]);
				LoginSession.prodsssion = pvo2;
				try {
					LoginSession.startMain.ShopDetailLink();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			
			shopimg10.setOnMouseClicked(e -> {
				ProdVO pvo2 = new ProdVO();
				pvo2.setProd_id(pIds[9]);
				LoginSession.prodsssion = pvo2;
				try {
					LoginSession.startMain.ShopDetailLink();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			
			shopimg11.setOnMouseClicked(e -> {
				ProdVO pvo2 = new ProdVO();
				pvo2.setProd_id(pIds[10]);
				LoginSession.prodsssion = pvo2;
				try {
					LoginSession.startMain.ShopDetailLink();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			
			shopimg12.setOnMouseClicked(e -> {
				ProdVO pvo2 = new ProdVO();
				pvo2.setProd_id(pIds[11]);
				LoginSession.prodsssion = pvo2;
				try {
					LoginSession.startMain.ShopDetailLink();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			
			searchBtn.setOnAction(e->{
				ProdVO pv = new ProdVO();
						
				String slGu = proGu.getValue();
				
				if(slGu.equals("먹이/사료")) {
					  pv.setProd_gu("A1");
				}else if(slGu.equals("생활용품")){
					  pv.setProd_gu("B1");   	  
				}else if(slGu.equals("장난감")) {
					  pv.setProd_gu("C1"); 
				}else if(slGu.equals("기타")) {
					  pv.setProd_gu("D1");
				};
				System.out.println("pNmTF.getText() = " + pNmTF.getText());
				System.out.println("pNmTF.getText() = " + pv.getProd_name());
				
				pv.setProd_name(pNmTF.getText());
				
			
					selectProdList(pv);
			
			});
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
		/*
		 * AllTableData = FXCollections.observableArrayList(prodlist);
		 * AllTableData.setAll(prodlist); AllTableData.add(vo);
		 */

		/*
		 * shopimg1.setImage(new Image("/kr/or/ddit/view/prodImg/본스틱개껌.jpg")); //
		 * shopimg1.setImage(new Image(url1 +"상품이름"+ url2)); shopimg2.setImage(new
		 * Image("/kr/or/ddit/view/prodImg/natural사료.jpg")); shopimg3.setImage(new
		 * Image("/kr/or/ddit/view/prodImg/now사료.jpg")); shopimg4.setImage(new
		 * Image("/kr/or/ddit/view/prodImg/pure사료.jpg")); shopimg5.setImage(new
		 * Image("/kr/or/ddit/view/prodImg/3m리드줄.jpg")); shopimg6.setImage(new
		 * Image("/kr/or/ddit/view/prodImg/t자용빗.jpg"));
		 */
	public void selectProdList(ProdVO vo) {
		try {
			list = (ArrayList<ProdVO>) ps.getSearchProdList(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		System.out.println("size : " + list.size());
		AllTableData = FXCollections.observableArrayList(list);
		
		String pName ="";
		String pImg = "";
		int pId = 0;
		
		String[] pNames= new String[12];
		String[] pImgs = new String[12];
		int[] pIds = new int[12];
		
		for(int i = 0; i < AllTableData.size(); i++) {
			ProdVO pdata = AllTableData.get(i);
			
			pName = pdata.getProd_name();
			pImg = pdata.getProd_img();
			pId = AllTableData.get(i).getProd_id();
					
			pNames[i] = pName;
			pImgs[i] = pImg;
			pIds[i] = pId;
			
		//	pId = pdata.getProd_id();
		//	pIds[i] = pId;
		}

		shopimg1_text1.setText(pNames[0]);
		shopimg1_text2.setText(pNames[1]);
		shopimg1_text3.setText(pNames[2]);
		shopimg1_text4.setText(pNames[3]);
		shopimg1_text5.setText(pNames[4]);
		shopimg1_text6.setText(pNames[5]);
		shopimg1_text7.setText(pNames[6]);
		shopimg1_text8.setText(pNames[7]);
		shopimg1_text9.setText(pNames[8]);
		shopimg1_text10.setText(pNames[9]);
		shopimg1_text11.setText(pNames[10]);
		shopimg1_text12.setText(pNames[11]);
		String emptyImg ="\\mainshop\\empty.jpg";
		
		shopimg1.setImage(new Image(pImgs[0]==null ? emptyImg : pImgs[0]));
		shopimg2.setImage(new Image(pImgs[1]==null ? emptyImg : pImgs[1]));
		shopimg3.setImage(new Image(pImgs[2]==null ? emptyImg : pImgs[2]));
		shopimg4.setImage(new Image(pImgs[3]==null ? emptyImg : pImgs[3]));
		shopimg5.setImage(new Image(pImgs[4]==null ? emptyImg : pImgs[4]));
		shopimg6.setImage(new Image(pImgs[5]==null ? emptyImg : pImgs[5]));
		shopimg7.setImage(new Image(pImgs[6]==null ? emptyImg : pImgs[6]));
		shopimg8.setImage(new Image(pImgs[7]==null ? emptyImg : pImgs[7]));
		shopimg9.setImage(new Image(pImgs[8]==null ? emptyImg : pImgs[8]));
		shopimg10.setImage(new Image(pImgs[9]==null ? emptyImg : pImgs[9]));
		shopimg11.setImage(new Image(pImgs[10]==null ? emptyImg : pImgs[10]));
		shopimg12.setImage(new Image(pImgs[11]==null ? emptyImg : pImgs[11]));
	}
}
