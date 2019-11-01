package kr.or.ddit.view.mypage2.master.center.blacklist;

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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import kr.or.ddit.service.blacklist.BlacklistService;
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.vo.BlackVO;
import kr.or.ddit.vo.PetSaleAppVO;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class Master_blacklist_controller implements Initializable{

	@FXML TableView<BlackVO> table_black;
	@FXML TableColumn<BlackVO, Integer> memnoCol;
	@FXML TableColumn<BlackVO, String> memidCol;
	@FXML TableColumn<BlackVO, Integer> cancelnumCol;
	@FXML TableColumn<BlackVO, String> emailCol;
	@FXML TableColumn<BlackVO, String> telCol;
	@FXML TextField addmemTF;
	@FXML Button addBtn;
	
	// 서버연결
	private Registry reg;
	private BlacklistService bs;
	
	// 블랙리스트 회원 정보를 담는 리스트
	private static ArrayList<BlackVO> list = new ArrayList<>();
	private ObservableList<BlackVO> allTableData;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			bs = (BlacklistService) reg.lookup("black");
			
			list = (ArrayList<BlackVO>) bs.showAllB();
		}catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		// 블랙리스트 회원 전부 출력
		memnoCol.setCellValueFactory(new PropertyValueFactory<>("mem_no"));
		memidCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		cancelnumCol.setCellValueFactory(new PropertyValueFactory<>("black_cnt"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("mem_email"));
		telCol.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
		addButtonToTable();
		
		allTableData = FXCollections.observableArrayList();
		allTableData.setAll(list);
		table_black.setItems(allTableData);
		
		// 블랙 리스트 회원 추가
		addBtn.setOnAction(e -> {
			String memid = addmemTF.getText().trim();
			BlackVO bv2 = new BlackVO();
			bv2.setMem_id(memid);
			
			try {
				list = (ArrayList<BlackVO>) bs.searchB(bv2);
			}catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			// 멤버 no를 추가해야하는데.....멤버 아이디를 가져온다...어떻게 하지...?
			if(list.size() == 0) {
				int cnt = 0;
				try {
					cnt = bs.insertBlack(bv2);
				}catch(RemoteException e2) {
					e2.printStackTrace();
				}
			}else {
				int cnt = 0;
				try {
					cnt = bs.updateBlack(bv2);
					
				}catch (RemoteException e3) {
					e3.printStackTrace();
				}
			}
			
			try {
				list = (ArrayList<BlackVO>) bs.showAllB();
			}catch (RemoteException e3) {
				e3.printStackTrace();
			}
			memnoCol.setCellValueFactory(new PropertyValueFactory<>("mem_no"));
			memidCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
			cancelnumCol.setCellValueFactory(new PropertyValueFactory<>("black_cnt"));
			emailCol.setCellValueFactory(new PropertyValueFactory<>("mem_email"));
			telCol.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
			addButtonToTable();
			
			allTableData = FXCollections.observableArrayList();
			allTableData.setAll(list);
			table_black.setItems(allTableData);
		});
		
		
		
	}
	
	  private void addButtonToTable() {
	        TableColumn<BlackVO, Void> colBtn = new TableColumn("탈퇴");

	        Callback<TableColumn<BlackVO, Void>, TableCell<BlackVO, Void>> cellFactory = new Callback<TableColumn<BlackVO, Void>, TableCell<BlackVO, Void>>() {
	            @Override
	            public TableCell<BlackVO, Void> call(final TableColumn<BlackVO, Void> param) {
	                final TableCell<BlackVO, Void> cell = new TableCell<BlackVO, Void>() {

	                    private final Button btn = new Button("탈퇴");

	                    {
	                        btn.setOnAction(e -> {
	                        	BlackVO bData = getTableView().getItems().get(getIndex());
	                        	BlackVO bvo = new BlackVO();
	                        	System.out.println(bData);
	                        	bvo.setMem_no(bData.getMem_no());
	                        	System.out.println("버튼을 눌렀습니다.");
	                        	try {
	                        		int cnt = bs.deleteBlack(bvo);
	                        		
									if(cnt > 0) {
										AlertMsg.info("회원 탈퇴가 성공적으로 적용되었습니다!");
									}
								} catch (RemoteException e1) {
									e1.printStackTrace();
								}
//	                            System.out.println("selectedData: " + data);
	                        	try {
	                				list = (ArrayList<BlackVO>) bs.showAllB();
	                			}catch (RemoteException e3) {
	                				e3.printStackTrace();
	                			}
//	                			memnoCol.setCellValueFactory(new PropertyValueFactory<>("mem_no"));
//	                			memidCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
//	                			cancelnumCol.setCellValueFactory(new PropertyValueFactory<>("black_cnt"));
//	                			emailCol.setCellValueFactory(new PropertyValueFactory<>("mem_email"));
//	                			telCol.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
//	                			addButtonToTable();
	                			
	                			allTableData = FXCollections.observableArrayList();
	                			allTableData.setAll(list);
	                			table_black.setItems(allTableData);
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

	        table_black.getColumns().add(colBtn);

	    }
	

}














