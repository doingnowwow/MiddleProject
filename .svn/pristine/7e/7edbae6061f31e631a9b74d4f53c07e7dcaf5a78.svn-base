package kr.or.ddit.view.main.salse.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.dogSale.DogSaleService;
import kr.or.ddit.util.DataModel;
import kr.or.ddit.vo.DogSaleVO;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;



public class main_petsale_controller implements Initializable{


	@FXML Label hotel1_text;
	@FXML Label hotel2_text;
	@FXML Label hotel3_text;
	@FXML Label hotel4_text;
	@FXML Label hotel5_text;
	@FXML Label hotel6_text;
	@FXML Label hotel7_text;
	@FXML Label hotel8_text;
	@FXML Label hotel9_text;
	@FXML Label hotel10_text;
	@FXML Label hotel11_text;
	@FXML Label hotel12_text;
	
	@FXML ImageView saledog1_img;
	@FXML ImageView saledog2_img;
	@FXML ImageView saledog3_img;
	@FXML ImageView saledog4_img;
	@FXML ImageView saledog5_img;
	@FXML ImageView saledog6_img;
	@FXML ImageView saledog7_img;
	@FXML ImageView saledog8_img;
	@FXML ImageView saledog9_img;
	@FXML ImageView saledog10_img;
	@FXML ImageView saledog11_img;
	@FXML ImageView saledog12_img;

	
	@FXML ComboBox<String> dogGuCbx;
	@FXML TextField serchTF;
	@FXML Button searchBtn;
	@FXML ComboBox<String> dog_gen;
	@FXML ComboBox<String> dog_kd;


	
	private List<DogSaleVO> list = new ArrayList<>();
	private ObservableList<DogSaleVO> AllTableData;
	
	Registry reg;
	DogSaleService ds;

	public main_petsale_controller() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	//	ObservableList<String> oderList = FXCollections.observableArrayList("-전체-", "별점▲", "별점▼");
	
		
		DogSaleVO vo = new DogSaleVO();
		DogSaleVO dogSaleVO = new DogSaleVO();
		
		// 콤보박스 셋팅
		ObservableList<String> genList = FXCollections.observableArrayList("-전체-", "여아", "남아");
		ObservableList<String> kindList = FXCollections.observableArrayList("-전체-", "소형견", "중형견","대형견");
		ObservableList<String> allGuList = FXCollections.observableArrayList("-전체-", "호텔명", "품종");
		dog_gen.setItems(genList);
		dog_kd.setItems(kindList);
		dogGuCbx.setItems(allGuList);
		
		dog_gen.setValue("-전체-");
		dog_kd.setValue("-전체-");
		dogGuCbx.setValue("-전체-");
		
		//Registry F2눌러 도큐먼트를 보면 서버와 클라이언트간의 연결을 돕는 뭔가를 하는 듯하다.
		
		try {
			//Registry 통신할 주소(서버)에서 등록되어있는 무언가를 가져오는 듯 하다.
			reg = LocateRegistry.getRegistry("localhost",8888);
			
			//HotelService 는 Remote를 상속받았기 때문에 lookup의 리턴값을 형변환 가능.
			//서버에서 hotelService를 검색하여 해당 정보를 변수에 넣어주는 듯 하다.
			ds = (DogSaleService) reg.lookup("dogsaleService");
			//위의 작업으로 인터페이스임에도 불구하고 뭔가 기능을 하는 모습.
		
			list = ds.getAllDogSale();
			
			System.out.println("size : " + list.size());
			AllTableData = FXCollections.observableArrayList(list);
			
			String dName 	= "";	
			String dImg		= "";	
			int dNo		= 0;
			
			String[] dNames = new String[12];
			String[] dImgs = new String[12];
			int[] dNos = new int[12];
			
			for (int i = 0; i < AllTableData.size(); i++) {
				DogSaleVO data = AllTableData.get(i);
				
				dName = data.getDog_name();
				dImg  = data.getDog_picture();
				dNo	  = data.getDog_no();
				
				dNames[i] = dName;
				dImgs[i] = dImg;
				dNos[i] = dNo;
			}
			
			hotel1_text.setText(dNames[0]);
			hotel2_text.setText(dNames[1]);
			hotel3_text.setText(dNames[2]);
			hotel4_text.setText(dNames[3]);
			hotel5_text.setText(dNames[4]);
			hotel6_text.setText(dNames[5]);
			hotel7_text.setText(dNames[6]);
			hotel8_text.setText(dNames[7]);
			hotel9_text.setText(dNames[8]);
			hotel10_text.setText(dNames[9]);
			hotel11_text.setText(dNames[10]);
			hotel12_text.setText(dNames[11]);
			
			String emptyImg = "\\mainPetSale\\empty.jpg";
			
			saledog1_img.setImage(new Image(dImgs[0]==null ? emptyImg:dImgs[0])); 
			saledog2_img.setImage(new Image(dImgs[1]==null ? emptyImg:dImgs[1])); 
			saledog3_img.setImage(new Image(dImgs[2]==null ? emptyImg:dImgs[2])); 
			saledog4_img.setImage(new Image(dImgs[3]==null ? emptyImg:dImgs[3])); 
			saledog5_img.setImage(new Image(dImgs[4]==null ? emptyImg:dImgs[4])); 
			saledog6_img.setImage(new Image(dImgs[5]==null ? emptyImg:dImgs[5])); 
			saledog7_img.setImage(new Image(dImgs[6]==null ? emptyImg:dImgs[6])); 
			saledog8_img.setImage(new Image(dImgs[7]==null ? emptyImg:dImgs[7])); 
			saledog9_img.setImage(new Image(dImgs[8]==null ? emptyImg:dImgs[8])); 
			saledog10_img.setImage(new Image(dImgs[9]==null ? emptyImg:dImgs[9])); 
			saledog11_img.setImage(new Image(dImgs[10]==null ? emptyImg:dImgs[10]));
			saledog12_img.setImage(new Image(dImgs[11]==null ? emptyImg:dImgs[11])); 
			

			saledog1_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[0]);
				LoginSession.dogsession = dsv;
			try {
				LoginSession.startMain.SaleDetailLink();
			}catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			
			System.out.println(" 돼나???");
			saledog2_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[1]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog3_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[2]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog4_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[3]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog5_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[4]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog6_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[5]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog7_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[6]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog8_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[7]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog9_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[8]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog10_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[9]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog11_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[10]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog12_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[11]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
		}catch(RemoteException e) {
			e.printStackTrace();
		}catch(NotBoundException e ) {
			e.printStackTrace();
		}		
		
//		hotel1_img.setOn
		
	searchBtn.setOnAction(e->{
		DogSaleVO dsv2 = new DogSaleVO();
				
		String dogGen = dog_gen.getValue();
		dsv2.setDog_gender(dogGen);
		
		String dogSz = dog_kd.getValue();
		dsv2.setDog_size(dogSz);
		
		if(dogGuCbx.getValue().equals("호텔명")) {
			String hotelnm  = serchTF.getText();
			dsv2.setHotel_name(hotelnm);
			
		}else if(dogGuCbx.getValue().equals("품종")){
			String dogGu =  serchTF.getText();
			dsv2.setDog_gu(dogGu);
			
		}else if(serchTF.getText().trim() == null) {
			
		}
		
		selectListPic(dsv2);
	});

	

	}
		
	// 데이트픽커 날짜 형변환해주는 메소드
	public String formatChange(LocalDate ld) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    	String rtn = ld.format(dtf);
    	return rtn;
	}
	
	public void movePage(Stage stage, String dNo) {
        // 새 스테이지 추가
		
//        Stage newStage = new Stage();
//        Stage stage = (Stage)hotel1_img.getScene().getWindow();
        
        try {
            // 1. 새 스테이지 + 새 레이아웃
            // 새 스테이지 생성 -> 새레이아웃 추가 -> 기존 스테이지 닫기
            // 새 레이아웃 추가
        	
        	DataModel.dsList.add(dNo);
            Parent second = FXMLLoader.load(getClass().getResource("../center/petsale_detail.fxml"));
            // 씬에 레이아웃 추가
            Scene sc = new Scene(second);
            stage.setScene(sc);
            stage.show();
            // 씬을 스테이지에서 상영
//            newStage.setScene(sc);
//            newStage.show();
 
            // 2. 기존 스테이지 + 새 레이아웃
            /* 새로만든 레이아웃을 기존 스테이지에 띄움 */
//            Parent second = FXMLLoader.load(getClass().getResource("second.fxml"));
// 
//            // 씬에 레이아웃 추가
//            Scene sc = new Scene(second);
//            stage.setScene(sc);
//            stage.show();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
	
	public void selectListPic(DogSaleVO vo) {
		
		// 콤보박스 셋팅
		ObservableList<String> genList = FXCollections.observableArrayList("-전체-", "여아", "남아");
		ObservableList<String> kindList = FXCollections.observableArrayList("-전체-", "소형견", "중형견","대형견");
		ObservableList<String> allGuList = FXCollections.observableArrayList("-전체-", "호텔명", "품종");
		dog_gen.setItems(genList);
		dog_kd.setItems(kindList);
		dogGuCbx.setItems(allGuList);
		//Registry F2눌러 도큐먼트를 보면 서버와 클라이언트간의 연결을 돕는 뭔가를 하는 듯하다.
		
		try {
			//Registry 통신할 주소(서버)에서 등록되어있는 무언가를 가져오는 듯 하다.
			reg = LocateRegistry.getRegistry("localhost",8888);
			
			//HotelService 는 Remote를 상속받았기 때문에 lookup의 리턴값을 형변환 가능.
			//서버에서 hotelService를 검색하여 해당 정보를 변수에 넣어주는 듯 하다.
			ds = (DogSaleService) reg.lookup("dogsaleService");
			//위의 작업으로 인터페이스임에도 불구하고 뭔가 기능을 하는 모습.
		
			list = ds.getDogSaleSearchList(vo);
			
			System.out.println("size : " + list.size());
			AllTableData = FXCollections.observableArrayList(list);
			
			String dName 	= "";	
			String dImg		= "";	
			int dNo		= 0;
			
			String[] dNames = new String[12];
			String[] dImgs = new String[12];
			int[] dNos = new int[12];
			
			for (int i = 0; i < AllTableData.size(); i++) {
				DogSaleVO data = AllTableData.get(i);
				
				dName = data.getDog_name();
				dImg  = data.getDog_picture();
				dNo	  = data.getDog_no();
				
				dNames[i] = dName;
				dImgs[i] = dImg;
				dNos[i] = dNo;
			}
			
			hotel1_text.setText(dNames[0]);
			hotel2_text.setText(dNames[1]);
			hotel3_text.setText(dNames[2]);
			hotel4_text.setText(dNames[3]);
			hotel5_text.setText(dNames[4]);
			hotel6_text.setText(dNames[5]);
			hotel7_text.setText(dNames[6]);
			hotel8_text.setText(dNames[7]);
			hotel9_text.setText(dNames[8]);
			hotel10_text.setText(dNames[9]);
			hotel11_text.setText(dNames[10]);
			hotel12_text.setText(dNames[11]);
			
			String emptyImg = "\\mainPetSale\\empty.jpg";
			
			saledog1_img.setImage(new Image(dImgs[0]==null ? emptyImg:dImgs[0])); 
			saledog2_img.setImage(new Image(dImgs[1]==null ? emptyImg:dImgs[1])); 
			saledog3_img.setImage(new Image(dImgs[2]==null ? emptyImg:dImgs[2])); 
			saledog4_img.setImage(new Image(dImgs[3]==null ? emptyImg:dImgs[3])); 
			saledog5_img.setImage(new Image(dImgs[4]==null ? emptyImg:dImgs[4])); 
			saledog6_img.setImage(new Image(dImgs[5]==null ? emptyImg:dImgs[5])); 
			saledog7_img.setImage(new Image(dImgs[6]==null ? emptyImg:dImgs[6])); 
			saledog8_img.setImage(new Image(dImgs[7]==null ? emptyImg:dImgs[7])); 
			saledog9_img.setImage(new Image(dImgs[8]==null ? emptyImg:dImgs[8])); 
			saledog10_img.setImage(new Image(dImgs[9]==null ? emptyImg:dImgs[9])); 
			saledog11_img.setImage(new Image(dImgs[10]==null ? emptyImg:dImgs[10]));
			saledog12_img.setImage(new Image(dImgs[11]==null ? emptyImg:dImgs[11])); 
			

			saledog1_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[0]);
				LoginSession.dogsession = dsv;
			try {
				LoginSession.startMain.SaleDetailLink();
			}catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			
			System.out.println(" 돼나???");
			saledog2_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[1]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog3_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[2]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog4_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[3]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog5_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[4]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog6_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[5]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog7_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[6]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog8_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[7]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog9_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[8]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog10_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[9]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog11_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[10]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
			saledog12_img.setOnMouseClicked(e->{
				DogSaleVO dsv = new DogSaleVO();
				dsv.setDog_no(dNos[11]);
				LoginSession.dogsession = dsv;
				try {
				LoginSession.startMain.SaleDetailLink();
			}	catch(IOException e1) {
				e1.printStackTrace();
			}
		});
		}catch(RemoteException e) {
			e.printStackTrace();
		}catch(NotBoundException e ) {
			e.printStackTrace();
		}		
	}
}




	
	
	


