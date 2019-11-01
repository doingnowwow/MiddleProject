package kr.or.ddit.view.main.hotel.main;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.hotel.HotelService;
import kr.or.ddit.util.DataModel;
import kr.or.ddit.view.main.hotel.center.hoteldetailpage.main_hotel_detailpage_controller;
import kr.or.ddit.view.main.hotel.center.hoteldetailpage.main_hotel_detailpage_main;
import kr.or.ddit.vo.HotelVO;
import kr.or.ddit.vo.MemberVO;

public class main_hotel_controller implements Initializable{
	
	//top
	@FXML Button face;
	@FXML Button instar;
	@FXML Button kakao;
	@FXML Button home;
	@FXML Button login;
	@FXML Button join;
	@FXML Button mainmenu1;
	@FXML Button mainmenu2;
	@FXML Button mainmenu3;
	@FXML Button mainmenu4;
	
	//center
	@FXML TextField keysearch;
	@FXML ComboBox<String> selectkind;
	@FXML Button searchButton;
	@FXML TextField whrereTF;
	
	@FXML Button kind1;
	@FXML Button kind2;
	@FXML Button kind3;
	@FXML Button kind4;
	@FXML ImageView hotel1_img;
	@FXML Label hotel1_text;
	@FXML ImageView hotel2_img;
	@FXML Label hotel2_text;
	@FXML ImageView hotel3_img;
	@FXML Label hotel3_text;
	@FXML ImageView hotel4_img;
	@FXML Label hotel4_text;
	@FXML ImageView hotel5_img;
	@FXML Label hotel5_text;
	@FXML ImageView hotel6_img;
	@FXML Label hotel6_text;
	@FXML ImageView hotel7_img;
	@FXML Label hotel7_text;
	@FXML ImageView hotel8_img;
	@FXML Label hotel8_text;
	@FXML ImageView hotel9_img;
	@FXML Label hotel9_text;
	@FXML ImageView hotel10_img;
	@FXML Label hotel10_text;
	@FXML ImageView hotel11_img;
	@FXML Label hotel11_text;
	@FXML ImageView hotel12_img;
	@FXML Label hotel12_text;
	
	//bottom
	@FXML Button link1;
	@FXML Button link2;
	@FXML Button chat;
	@FXML Button bottom_face;
	@FXML Button bottom_instar;
	@FXML Button bottom_kakao;
	
	
	//호텔번호 입력
//	static int hotel_no = 3;

	// 클릭한 호텔 정보
	HotelVO hlog = LoginSession.hosession;
	int hono = 0;
	public static ArrayList<HotelVO> list = new ArrayList<>();
	private ObservableList<HotelVO> AllTableData;
	
	List<HotelVO> hlist = new ArrayList<HotelVO>();
	
	HotelService hotelService;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> oderList = FXCollections.observableArrayList("-전체-", "별점▲", "별점▼");
		selectkind.setItems(oderList);
		selectkind.setValue("-전체-");
		
		HotelVO vo = new HotelVO();
		
		//Registry F2눌러 도큐먼트를 보면 서버와 클라이언트간의 연결을 돕는 뭔가를 하는 듯하다.
		Registry reg;
		try {
			//Registry 통신할 주소(서버)에서 등록되어있는 무언가를 가져오는 듯 하다.
			reg=LocateRegistry.getRegistry("localhost",8888);
			//HotelService 는 Remote를 상속받았기 때문에 lookup의 리턴값을 형변환 가능.
			//서버에서 hotelService를 검색하여 해당 정보를 변수에 넣어주는 듯 하다.
			hotelService = (HotelService) reg.lookup("hotelService");
			//위의 작업으로 인터페이스임에도 불구하고 뭔가 기능을 하는 모습.
			HotelVO hotelVO = new HotelVO();
			hotelVO.setOrder("-전체-");
			selectHotelList(hotelVO);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		try {
			list = (ArrayList<HotelVO>) hotelService.getAllHotelList();
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		System.out.println("size : " + list.size());
		AllTableData = FXCollections.observableArrayList(list);
		
		String hName 	= "";	
		String hImg		= "";	
		int hNo			= 0;
		
		String[] hNames = new String[12];
		String[] hImgs = new String[12];
		int[] hNos = new int[12];
		
		for (int i = 0; i < AllTableData.size(); i++) {
			HotelVO data = AllTableData.get(i);
			
			hName	= data.getHotel_name();
			hImg	= data.getImg();
	//		hNo		= data.getHotel_no();
			hNo		= AllTableData.get(i).getHotel_no();
			
			hNames[i] = hName;
			hImgs[i] = hImg;
			hNos[i] = hNo;
		}
	
		hotel1_text.setText(hNames[0]);
		hotel2_text.setText(hNames[1]);
		hotel3_text.setText(hNames[2]);
		hotel4_text.setText(hNames[3]);
		hotel5_text.setText(hNames[4]);
		hotel6_text.setText(hNames[5]);
		hotel7_text.setText(hNames[6]);
		hotel8_text.setText(hNames[7]);
		hotel9_text.setText(hNames[8]);
		hotel10_text.setText(hNames[9]);
		hotel11_text.setText(hNames[10]);
		hotel12_text.setText(hNames[11]);
		String emptyImg = "\\mainHotel\\empty.jpg";
		hotel1_img.setImage(new Image(hImgs[0]==null ? emptyImg:hImgs[0])); 
		hotel2_img.setImage(new Image(hImgs[1]==null ? emptyImg:hImgs[1])); 
		hotel3_img.setImage(new Image(hImgs[2]==null ? emptyImg:hImgs[2])); 
		hotel4_img.setImage(new Image(hImgs[3]==null ? emptyImg:hImgs[3])); 
		hotel5_img.setImage(new Image(hImgs[4]==null ? emptyImg:hImgs[4])); 
		hotel6_img.setImage(new Image(hImgs[5]==null ? emptyImg:hImgs[5])); 
		hotel7_img.setImage(new Image(hImgs[6]==null ? emptyImg:hImgs[6])); 
		hotel8_img.setImage(new Image(hImgs[7]==null ? emptyImg:hImgs[7])); 
		hotel9_img.setImage(new Image(hImgs[8]==null ? emptyImg:hImgs[8])); 
		hotel10_img.setImage(new Image(hImgs[9]==null ? emptyImg:hImgs[9])); 
		hotel11_img.setImage(new Image(hImgs[10]==null ? emptyImg:hImgs[10]));
		hotel12_img.setImage(new Image(hImgs[11]==null ? emptyImg:hImgs[11])); 
		
		
		hotel1_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[0]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
	    });
	
		hotel2_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[1]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel3_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[2]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel4_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[3]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel5_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[4]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel6_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[5]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel7_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[6]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel8_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[7]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel9_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[8]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel10_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[9]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel11_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[10]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel12_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[11]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		
		searchButton.setOnAction(e->{
	    	
			HotelVO hv = new HotelVO();
	/*		LocalDate chkIn = book_checkin.getValue();
	    	LocalDate chkOut = book_checkout.getValue();
	    	
	    	String sDate = formatChange(chkIn);
	    	String eDate = formatChange(chkOut);*/
	    	
	    	/*vo.setsDate(sDate);
	    	vo.seteDate(eDate);  ㅜㅜ..실패한 기간검색의 잔해.. 아디오스...*/
			
			String order  = selectkind.getValue();
		    hv.setOrder(order);
	
			String searchNm = keysearch.getText();
	    	hv.setHotel_name(searchNm);
	    	
	    	String searchSpot = whrereTF.getText();
	    	hv.setHotel_addr1(searchSpot);
	    	
	    	selectHotelList(hv);
	  });
	}
/*	
	데이트픽커 날짜 형변환해주는 메소드   ....아까워서 못지우겟당..ㅠㅠ
 	public String formatChange(LocalDate ld) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    	String rtn = ld.format(dtf);
    	return rtn;
	}
*/
	public void selectHotelList(HotelVO vo) {
	
		try {
			list = (ArrayList<HotelVO>) hotelService.selectHotelList(vo);
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		System.out.println("size : " + list.size());
		AllTableData = FXCollections.observableArrayList(list);
		
		String hName 	= "";	
		String hImg		= "";	
		int hNo			= 0;
		
		String[] hNames = new String[12];
		String[] hImgs = new String[12];
		int[] hNos = new int[12];
		
		for (int i = 0; i < AllTableData.size(); i++) {
			HotelVO data = AllTableData.get(i);
			
			hName	= data.getHotel_name();
			hImg	= data.getImg();
	//		hNo		= data.getHotel_no();
			hNo		= AllTableData.get(i).getHotel_no();
			
			hNames[i] = hName;
			hImgs[i] = hImg;
			hNos[i] = hNo;
		}
	
		hotel1_text.setText(hNames[0]);
		hotel2_text.setText(hNames[1]);
		hotel3_text.setText(hNames[2]);
		hotel4_text.setText(hNames[3]);
		hotel5_text.setText(hNames[4]);
		hotel6_text.setText(hNames[5]);
		hotel7_text.setText(hNames[6]);
		hotel8_text.setText(hNames[7]);
		hotel9_text.setText(hNames[8]);
		hotel10_text.setText(hNames[9]);
		hotel11_text.setText(hNames[10]);
		hotel12_text.setText(hNames[11]);
		String emptyImg = "\\mainHotel\\empty.jpg";
		hotel1_img.setImage(new Image(hImgs[0]==null ? emptyImg:hImgs[0])); 
		hotel2_img.setImage(new Image(hImgs[1]==null ? emptyImg:hImgs[1])); 
		hotel3_img.setImage(new Image(hImgs[2]==null ? emptyImg:hImgs[2])); 
		hotel4_img.setImage(new Image(hImgs[3]==null ? emptyImg:hImgs[3])); 
		hotel5_img.setImage(new Image(hImgs[4]==null ? emptyImg:hImgs[4])); 
		hotel6_img.setImage(new Image(hImgs[5]==null ? emptyImg:hImgs[5])); 
		hotel7_img.setImage(new Image(hImgs[6]==null ? emptyImg:hImgs[6])); 
		hotel8_img.setImage(new Image(hImgs[7]==null ? emptyImg:hImgs[7])); 
		hotel9_img.setImage(new Image(hImgs[8]==null ? emptyImg:hImgs[8])); 
		hotel10_img.setImage(new Image(hImgs[9]==null ? emptyImg:hImgs[9])); 
		hotel11_img.setImage(new Image(hImgs[10]==null ? emptyImg:hImgs[10]));
		hotel12_img.setImage(new Image(hImgs[11]==null ? emptyImg:hImgs[11])); 
		
		
		hotel1_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[0]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
	    });
	
		hotel2_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[1]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel3_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[2]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel4_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[3]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel5_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[4]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel6_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[5]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel7_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[6]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel8_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[7]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel9_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[8]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel10_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[9]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel11_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[10]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
		hotel12_img.setOnMouseClicked(e->{
			HotelVO hovo = new HotelVO();
			hovo.setHotel_no(hNos[11]);
			LoginSession.hosession = hovo;
			try {
				LoginSession.startMain.HotelDetailLink();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    });
	}
}
