package kr.or.ddit.view.mypage2.human.center.myact;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.dogBoard.DogBoardService;
import kr.or.ddit.service.horeview.HoReviewService;
import kr.or.ddit.vo.DogBoardVO;
import kr.or.ddit.vo.HotelReviewVO;
import kr.or.ddit.vo.MemberVO;

// 앙 도영이가 작업중이에요~ >< 만지지 마슈 
public class mypage_human_center_myact_controller implements Initializable {

	@FXML
	DatePicker forwardDate;
	@FXML
	DatePicker backwardDate;
	@FXML
	Button dateSearchBtn;
	@FXML
	TextField detailSearchTF;
	@FXML
	Button detailSearchBtn;
	@FXML
	TableView<HotelReviewVO> hotelCommentTable;
	@FXML
	TableColumn<HotelReviewVO, String> dateCol;
	@FXML
	TableColumn<HotelReviewVO, String> contentCol;
	@FXML
	TableColumn<HotelReviewVO, String> h_nameCol;
	@FXML
	TableColumn<HotelReviewVO, Integer> starCol;
	@FXML
	Pagination pnation;

	// ---- 분양 리뷰 테이블 ----
	@FXML TextField detailSearchTF2;
	@FXML Button detailSearchBtn2;
	@FXML TableView<DogBoardVO> hotelCommentTable2;
	@FXML TableColumn<DogBoardVO, Integer> No_sale;
	@FXML TableColumn<DogBoardVO, String> writer;
	@FXML TableColumn<DogBoardVO, String> dateCol2;
	@FXML TableColumn<DogBoardVO, String> titleCol2;
	@FXML TableColumn<DogBoardVO, String> contentCol2;
	@FXML TableColumn<DogBoardVO, String> h_nameCol2;
	@FXML DatePicker forwardDate2;
	@FXML DatePicker backwardDate2;
	@FXML Button dateSearchBtn2;
	@FXML Pagination pnation2;
	
	
	

	// 호텔 리뷰 //분양 리뷰
	private Registry reg;

	private HoReviewService hrs;
	private DogBoardService dbs;

	// 페이지네이션
	private int from, to, itemsForPage;
	private ObservableList<HotelReviewVO> allTableData, currentPageData;

	private int from2, to2, itemsForPage2;
	private ObservableList<DogBoardVO> allTableData2, currentPageData2;

	// 글 전체 정보를 담는 리스트
	public static ArrayList<HotelReviewVO> ho_list = new ArrayList<>();
	private ArrayList<HotelReviewVO> check_list = new ArrayList<>();

	public static ArrayList<DogBoardVO> ds_list = new ArrayList<>();
	private ArrayList<DogBoardVO> chk_list = new ArrayList<>();

	// 로그인 사용자 정보 저장
	MemberVO mv = LoginSession.session;
	HotelReviewVO hv = new HotelReviewVO();
	DogBoardVO dv = new DogBoardVO();
	int user = 0;

	// 선택한 글번호 저장하는 변수
	String click_save = null;

	int totPageCnt = 0;
	int totPageCnt2 = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			hrs = (HoReviewService) reg.lookup("hotelReview");
			dbs = (DogBoardService) reg.lookup("dogBoardService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		// 내가 쓴 리뷰 출력~~~
		try {
			user = mv.getMem_no();
			hv.setMem_no(user);

			ho_list = (ArrayList<HotelReviewVO>) hrs.selectRewview(hv);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		dateCol.setCellValueFactory(new PropertyValueFactory<>("hore_date"));
		contentCol.setCellValueFactory(new PropertyValueFactory<>("hore_content"));
		h_nameCol.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
		starCol.setCellValueFactory(new PropertyValueFactory<>("hore_star"));

		allTableData = FXCollections.observableArrayList();
		allTableData.setAll(ho_list);
		hotelCommentTable.setItems(allTableData);

		itemsForPage = 20;

		totPageCnt = allTableData.size() % itemsForPage == 0
				? allTableData.size() / itemsForPage
				: allTableData.size() / itemsForPage + 1;

		pnation.setPageCount(totPageCnt);
		pnation.setPageFactory(this::createPage);

		// 날짜 버튼 눌러서 날짜로 검색
		dateSearchBtn.setOnAction(e -> {
			LocalDate pickForward = forwardDate.getValue();
			LocalDate pickBackward = backwardDate.getValue();

			String fDate = formatChange(pickForward);
			String bDate = formatChange(pickBackward);

			hv.setHopick_date1(fDate);
			hv.setHopick_date2(bDate);

			try {
				user = mv.getMem_no();
				hv.setMem_no(user);

				ho_list = (ArrayList<HotelReviewVO>) hrs.selectMyReviewDate(hv);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

			dateCol.setCellValueFactory(new PropertyValueFactory<>("hore_date"));
			writer.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
			contentCol.setCellValueFactory(new PropertyValueFactory<>("hore_content"));
			h_nameCol.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
			starCol.setCellValueFactory(new PropertyValueFactory<>("hore_star"));

			allTableData = FXCollections.observableArrayList();

			allTableData.setAll(ho_list);

			hotelCommentTable.setItems(allTableData);

			itemsForPage = 20;

			totPageCnt = allTableData.size() % itemsForPage == 0
					? allTableData.size() / itemsForPage
					: allTableData.size() / itemsForPage + 1;

			pnation.setPageCount(totPageCnt);
			pnation.setPageFactory(this::createPage);

		});

		// 검색 창에 단어로 검색
		detailSearchBtn.setOnAction(e -> {
			try {
				String textA = detailSearchTF.getText();

				user = mv.getMem_no();
				hv.setMem_no(user);

				hv.setHotel_name(textA);

				ho_list = (ArrayList<HotelReviewVO>) hrs.selectMyReviewText(hv);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			dateCol.setCellValueFactory(new PropertyValueFactory<>("hore_date"));
			contentCol.setCellValueFactory(new PropertyValueFactory<>("hore_content"));
			h_nameCol.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
			starCol.setCellValueFactory(new PropertyValueFactory<>("hore_star"));

			allTableData = FXCollections.observableArrayList();
			allTableData.setAll(ho_list);
			hotelCommentTable.setItems(allTableData);

		});

		// 분양후기테이블 시작

		DogBoardVO vo = new DogBoardVO();

		try {
			dbs = (DogBoardService) reg.lookup("dogBoardService");

			user = mv.getMem_no();
			dv.setMem_no(user);
			ds_list = (ArrayList<DogBoardVO>) dbs.getMyBoardList(dv);
		} catch (RemoteException e) {
			System.out.println("실패 ㅋ");
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		No_sale.setCellValueFactory(new PropertyValueFactory<>("dogboard_no"));
		writer.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		dateCol2.setCellValueFactory(new PropertyValueFactory<>("dogboard_date"));
		titleCol2.setCellValueFactory(new PropertyValueFactory<>("dogboard_title"));
		contentCol2.setCellValueFactory(new PropertyValueFactory<>("dogboard_content"));
		h_nameCol2.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));

		allTableData2 = FXCollections.observableArrayList();
		allTableData2.setAll(ds_list);
		hotelCommentTable2.setItems(allTableData2);

		itemsForPage2 = 10; // 한페이지에 보여줄 항목 수 설정
		totPageCnt2 = allTableData2.size() % itemsForPage2 == 0
				? allTableData2.size() / itemsForPage2
						: allTableData2.size() / itemsForPage2 + 1;

		pnation2.setPageCount(totPageCnt2); // 전체페이지 수 설정
		pnation2.setPageFactory(this::createPage2);
	}

	// 날짜 데이터 타입 변경
	private String formatChange(LocalDate ld) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String rtn = ld.format(dtf);
		System.out.println("어디까지왔니ㅣㅣㅣㅣㅣ");
		return rtn;

	}

	// 페이지네이션용 메서드
	private Node createPage(int pageIndex) {
		from = pageIndex * itemsForPage;
		to = from + itemsForPage - 1;
		hotelCommentTable.setItems(getTableViewData(from, to));

		return hotelCommentTable;
	}
	
	private Node createPage2(int pageIndex) {
		from2 = pageIndex * itemsForPage2;
		to2 = from2 + itemsForPage2 - 1;
		hotelCommentTable2.setItems(getTableViewData2(from2, to2));
		
		return hotelCommentTable2;
	}

	// 페이지네이션용 메서드
	private ObservableList<HotelReviewVO> getTableViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList();
		int totSize = allTableData.size();
		for (int i = from; i <= to && i < totSize; i++) {
			currentPageData.add(allTableData.get(i));
		}
		return currentPageData;
	}
	
	private ObservableList<DogBoardVO> getTableViewData2(int from2, int to2) {
		currentPageData2 = FXCollections.observableArrayList();
		int totSize2 = allTableData2.size();
		for (int i = from2; i <= to2 && i < totSize2; i++) {
			currentPageData2.add(allTableData2.get(i));
		}
		return currentPageData2;
	}

}
