package kr.or.ddit.view.mypage2.hotel.center.requeSelse;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.comReservCheck.ReservCheckService;
import kr.or.ddit.service.petSaleApp.PetSaleAppService;
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.vo.ComVO;
import kr.or.ddit.vo.PetSaleAppVO;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;

public class mypage_hotel_center_requeSalse_controller implements Initializable{

	
	@FXML TableView<PetSaleAppVO> myTable;
	@FXML TableColumn<PetSaleAppVO, String> petnoCol;
	@FXML TableColumn<PetSaleAppVO, String> petnameCol;
	@FXML TableColumn<PetSaleAppVO, String> petguCol;
	@FXML TableColumn<PetSaleAppVO, String> memnameCol;
	@FXML TableColumn<PetSaleAppVO, String> appdayCol;
	@FXML TableColumn<PetSaleAppVO, String> memtelCol;
	@FXML TableColumn<PetSaleAppVO, String> checkCol;
//	@FXML Pagination pnation;
	
	private Registry reg;
	private PetSaleAppService psa;
	
	// 페이지네이션
	private int from, to, itemsForpages;
	private ObservableList<PetSaleAppVO> allTableData, currentPageData;
	
	// 신청 정보를 담은 리스트
	private static List<PetSaleAppVO> app_list = new ArrayList<>();
	private ArrayList<PetSaleAppVO> check_list = new ArrayList<>();
	
	// 로그인한 사업주 정보 저장
	ComVO comvo = LoginSession.comsession;
	PetSaleAppVO pv = new PetSaleAppVO();
	int comUser = 0;
	
	// 선택한 글 저장
	String click_save = null;
	
	int totPageCnt = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			psa = (PetSaleAppService) reg.lookup("petSApp");
		}catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		try {
			comUser = comvo.getCom_no();
			pv.setCom_no(comUser);
//			pv.setCom_no(1234567891); // 하드코딩 적용중
			
			app_list = psa.getAllApp(pv);
		}catch (RemoteException e) {
			e.printStackTrace();
		}
		petnoCol.setCellValueFactory(new PropertyValueFactory<>("dog_no"));
		petnameCol.setCellValueFactory(new PropertyValueFactory<>("dog_name"));
		petguCol.setCellValueFactory(new PropertyValueFactory<>("dog_gu"));
		memnameCol.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		appdayCol.setCellValueFactory(new PropertyValueFactory<>("req_date"));
		memtelCol.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
		checkCol.setCellValueFactory(new PropertyValueFactory<>("accp_yn"));
		addButtonToTable();
		
		allTableData = FXCollections.observableArrayList();
		allTableData.setAll(app_list);
		myTable.setItems(allTableData);
		
//		itemsForpages = 20;
//		
//		totPageCnt = allTableData.size()%itemsForpages == 0
//				? allTableData.size()/itemsForpages
//				: allTableData.size()/itemsForpages + 1;
//		
	}
	
  private void addButtonToTable() {
        TableColumn<PetSaleAppVO, Void> colBtn = new TableColumn("반려버튼");

        Callback<TableColumn<PetSaleAppVO, Void>, TableCell<PetSaleAppVO, Void>> cellFactory = new Callback<TableColumn<PetSaleAppVO, Void>, TableCell<PetSaleAppVO, Void>>() {
            @Override
            public TableCell<PetSaleAppVO, Void> call(final TableColumn<PetSaleAppVO, Void> param) {
                final TableCell<PetSaleAppVO, Void> cell = new TableCell<PetSaleAppVO, Void>() {

                    private final Button btn = new Button("반려");

                    {
                        btn.setOnAction(e -> {
//                        	PetSaleAppVO data = getTableView().getItems().get(6);
                        	PetSaleAppVO pnoData = getTableView().getItems().get(0);
                        	PetSaleAppVO psavo = new PetSaleAppVO();
                        	psavo.setAccp_yn("0");
                        	psavo.setDog_no(pnoData.getDog_no());
                        	System.out.println("버튼을 눌렀습니다.");
                        	try {
                        		int cnt = psa.updatePetInfo(psavo);
                        		
								if(cnt > 0) {
									AlertMsg.info("신청이 반려되었습니다.");
								}
							} catch (RemoteException e1) {
								e1.printStackTrace();
							}
                        	
                        	
                        	
//                            System.out.println("selectedData: " + data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        myTable.getColumns().add(colBtn);

    }

}
