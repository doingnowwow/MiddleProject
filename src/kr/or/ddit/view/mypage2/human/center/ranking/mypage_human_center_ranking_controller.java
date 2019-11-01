package kr.or.ddit.view.mypage2.human.center.ranking;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.point.PointService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PointVO;

// 승재 작업 완료
public class mypage_human_center_ranking_controller implements Initializable{
	

	@FXML ImageView img_rank;
	@FXML TableView<PointVO> mileage;
	@FXML TableColumn<PointVO, String> save_contents;
	@FXML TableColumn<PointVO, Integer> save_point;
	@FXML TableColumn<PointVO, String> save_date;
	@FXML TableColumn<PointVO, Integer> use_point;
	@FXML TableColumn<PointVO, String> use_date;
	@FXML TableColumn<PointVO, Integer> sumPoint;
	@FXML Pagination mileage_page;
	@FXML Text rankingRtandard;
	@FXML Text id_rank_name;
	@FXML Text mileage_point;
	
	private Registry reg;
	private PointService cs_point;
	private ObservableList<PointVO> AllPointTableData, currentPageData;
	private ArrayList<PointVO> pointList;
	private String rankpointtxt;
	
	
	int from, to, itemsForPage;
	PointVO memNo_rank = null;

	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// 로그인 membervo 가져오기 (상단부에 필요)
		MemberVO LoginInfo = LoginSession.session;
		
		//pointvo가져오기 (테이블에 필요)
		
		int mem_no = LoginInfo.getMem_no();
		String joinId = LoginInfo.getMem_id().trim();
		String rank = LoginInfo.getMem_level().trim();
		int rankpoint = LoginInfo.getMem_point();
		rankpointtxt = Integer.toString(rankpoint);
		
		//로그인된 사용자 정보 가져오기(가라)
//		int mem_no = 4;
//		String joinId = "hit";
//		String rank = "골드";
//		int rankpoint = 1400;
//		

		//담을 객체 선언
		PointVO memNo_rank = new PointVO();
		pointList = new ArrayList<>();
		memNo_rank.setMem_no(mem_no);
		
//		//DB연결

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			cs_point = (PointService) reg.lookup("pointService");
			// 이제부터는 불러온 객체의 메서드를 호출해서 사용할 수 있다.
			//사용자 검색을 통해 나온 모든 정보 저장(pointVO)
			pointList = (ArrayList<PointVO>) cs_point.searchMem_no(mem_no);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		
		id_rank_name.setText(joinId + "님의 현재 등급입니다.");
		mileage_point.setText(rankpointtxt + "점");
		
		save_contents.setCellValueFactory(new PropertyValueFactory<>("point_contents"));
		save_point.setCellValueFactory(new PropertyValueFactory<>("point_money"));
		save_date.setCellValueFactory(new PropertyValueFactory<>("point_save_date"));
		use_point.setCellValueFactory(new PropertyValueFactory<>("point_use"));
		use_date.setCellValueFactory(new PropertyValueFactory<>("point_use_date"));
		sumPoint.setCellValueFactory(new PropertyValueFactory<>("point_mypoint"));
		
		
		
		//전체 테이블 데이터 생성
		AllPointTableData = FXCollections.observableArrayList(pointList);
		
		itemsForPage = 15; // 한페이지 보여줄 항목 수 설정
		int totPageCount = AllPointTableData.size()%itemsForPage == 0 ? AllPointTableData.size()/itemsForPage : AllPointTableData.size()/itemsForPage + 1;
		mileage_page.setPageCount(totPageCount); // 전체 페이지 수 설정
		
		
		mileage_page.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;
				mileage.setItems(getTableViewData(from, to));
				
				return mileage;
			}
		});
		
		
		
		
		// 기준 포인트 설정부분
		int goldpoint = 1500;
		int silverpoint = 500;
		int bronzepoint = 150;
		
		// 목표포인트까지 남은 정도
		int goalpoint;
		
		//imageview 랭킹표시 -- 
		Image rankimg;
		
		if(rank == "골드") {
			rankimg = new Image(getClass().getResourceAsStream("/rankimg/gold_190x243.gif"));
		
		}else if(rank == "실버") {
			rankimg = new Image(getClass().getResourceAsStream("/rankimg/silver_190x243.gif"));
			goalpoint = goldpoint - rankpoint;
		}else {
			rankimg = new Image(getClass().getResourceAsStream("/rankimg/bronze_190x243.gif"));
			goalpoint = silverpoint - rankpoint; 
		}
		
		//이미지 적용
		img_rank.setImage(rankimg);
		
		// 구매 등급 기준
		rankingRtandard.setText(
			"골드 : " + goldpoint + "이상\n" +
			"실버 : " + bronzepoint + "이상 ~ " + silverpoint + "미만\n" +
			"브론즈 : " + bronzepoint + "이하");
		
	}
	
	/**
	 * TableView에 채워줄 데이터를 가져오는 함수
	 * @param from
	 * @param to
	 * @return
	 */
	protected ObservableList<PointVO> getTableViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int totSize = AllPointTableData.size();
		for(int i = from; i <= to && i <totSize; i++){
			currentPageData.add(AllPointTableData.get(i));
		}
		
		return currentPageData;
	}



}
