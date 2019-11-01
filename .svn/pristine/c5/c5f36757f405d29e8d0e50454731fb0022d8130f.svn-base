package kr.or.ddit.view.mypage2.master.center.shopinfo;

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
import javafx.scene.control.TableColumn;
import kr.or.ddit.service.prodPay.ProdPayService;
import kr.or.ddit.service.shopsell.ShopSellService;
import kr.or.ddit.vo.ProdPayVO;
import kr.or.ddit.vo.SellVO;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Master_shopinfo_controller implements Initializable{

	@FXML TableColumn<SellVO, String> prod_gu;
	@FXML TableColumn<SellVO, String> prod_name;
	@FXML TableColumn<SellVO, Integer> cart_qty;
	@FXML TableColumn<SellVO, String> price;
	@FXML TableColumn<SellVO,String> mem_id;
	@FXML TableView <SellVO> tableView;
	
	@FXML BarChart<String, Number> sellcntChart;
	@FXML BorderPane chartset;
	private ObservableList<SellVO> AllTableData;
//	private ObservableList<ProdPayVO> AllTableData;
	
	private SellVO vo = new SellVO	();
//	private ProdPayVO pv = new ProdPayVO();
	
	public Master_shopinfo_controller() {
		
	}
	ShopSellService ss;
//	ProdPayService pps;
	
	public static ArrayList<SellVO> list = new ArrayList<>();
	public static ArrayList<SellVO> cntlist = new ArrayList<>();

	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<SellVO> data = FXCollections.observableArrayList();
		
		Registry reg ;
		
		try {
			reg = LocateRegistry.getRegistry("localhost",8888);
			ss = (ShopSellService) reg.lookup("sell");
//			pps = (ProdPayService) reg.lookup("prodPayService");
			
			list = (ArrayList<SellVO>) ss.sellListAll();
//			list = (ArrayList<ProdPayVO>) pps.getProdPayAllList();
			cntlist = (ArrayList<SellVO>) ss.sellCnt();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		AllTableData = FXCollections.observableArrayList(list);
		
		AllTableData.setAll(list);
		AllTableData.add(vo);
		
		
		tableView.setItems(AllTableData);
		
		
		prod_gu.setCellValueFactory(new PropertyValueFactory<>("prod_gu"));
		prod_name.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
		cart_qty.setCellValueFactory(new PropertyValueFactory<>("cart_qty"));
		price.setCellValueFactory(new PropertyValueFactory<>("sum"));
		mem_id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		
		
		// 여기부터 아래쪽 바차트 ////////////////////////
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		
		BarChart<String, Number> sellcntChart = new BarChart<>(xAxis, yAxis);
		
		sellcntChart.setTitle("판매량");
		xAxis.setLabel("물품");
		yAxis.setLabel("판매량");
		
		
		XYChart.Series<String, Number> ser = new XYChart.Series<>();
		
		for(int i = 0; i < cntlist.size(); i++) {
//			ser.setName(cntlist.get(i).getProd_name());
			ser.getData().add(new XYChart.Data<String, Number>(cntlist.get(i).getProd_name(), cntlist.get(i).getSum()));
		}
		
		sellcntChart.getData().add(ser);
//		sellcntChart.setData(FXCollections.observableArrayList(ser));
		chartset.setCenter(sellcntChart);
		
	}
	

}
