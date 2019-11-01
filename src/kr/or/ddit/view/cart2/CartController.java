package kr.or.ddit.view.cart2;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.cart2.Cart2Service;
import kr.or.ddit.util.AlertMsg;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.MemberVO;


public class CartController implements Initializable {

	
	@FXML private TableView<CartVO> cart_table;

	@FXML private TableColumn<CartVO,Integer>cart_no;
	@FXML private TableColumn<CartVO,String> prod_name;
	@FXML private TableColumn<CartVO,String> prod_info;
	@FXML private TableColumn<CartVO,String> prod_gu_nm;
	@FXML private TableColumn<CartVO,Integer> cart_qty;
	@FXML private TableColumn<CartVO,Integer> prod_total_cost;
	
	TableColumn<CartVO, Void> colBtn = new TableColumn("삭제");

//	@FXML private TableColumn<CartVO,Boolean>checkbox= new TableColumn<CartVO,Boolean>("chk");
	
	@FXML 
	private Pagination pagination;
	
	private int from, to, itemsForPage;
	
	private ObservableList<CartVO> allTableData, currentPageData;
	
	// 장바구니 정보 저장용도vo 객체
	private CartVO vo = new CartVO();
	
	// 회원정보 저장
	MemberVO mv;
	MemberVO mv2 = new MemberVO();
	int mNo = 0;
	
	public static CartVO CartVO;
	
	private Registry reg;
	private Cart2Service bds;
	
	private ArrayList<CartVO> list = new ArrayList<>();
	private ObservableValue<Boolean> ov;

	@FXML private JFXButton delete_btn;

	@FXML Label count_label;

	@FXML Button ct_order;


	@FXML Label totalCost;

	@FXML Button buyBtn;
	Stage windowPop;
	CartVO carts = new CartVO();

	
	public void initialize(URL location, ResourceBundle resources) {
		
		mv = LoginSession.session;
		mNo = mv.getMem_no();
		
		
		carts.setMem_no(mNo);
		
		//rmi연결
		try {	
			reg = LocateRegistry.getRegistry("localhost", 8888);
			bds = (Cart2Service) reg.lookup("cart2Service");
			System.out.println("연결 성공");
			
		}catch (RemoteException e) {
			System.out.println("실패 ㅋ");
			e.printStackTrace();
		}catch(NotBoundException e) {
			e.printStackTrace();
		}
				
		//테이블 가져오기
		prod_gu_nm.setCellValueFactory(new PropertyValueFactory<>("prod_gu_nm"));
		cart_no.setCellValueFactory(new PropertyValueFactory<>("cart_no"));
		cart_qty.setCellValueFactory(new PropertyValueFactory<>("cart_qty"));		
		prod_name.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
		prod_info.setCellValueFactory(new PropertyValueFactory<>("prod_info"));
		prod_total_cost.setCellValueFactory(new PropertyValueFactory<>("prod_total_cost"));
		addButtonToTable();
		
/*		
		
		//
//		TableColumn<CartVO,Boolean> checkBox = new TableColumn<CartVO,Boolean>("체크박스");
        // 체크박스를 Cell에 표시
		checkbox.setCellValueFactory(new PropertyValueFactory<CartVO,Boolean>("checkbox"));
       
        // 체크이벤트 처리
		checkbox.setCellFactory(column -> new TableCell<CartVO, Boolean>(){
	       public void updateItem(Boolean chk, boolean empty) {
	         super.updateItem(chk, empty);
	         System.out.println("check = "+ chk);
	         System.out.println("empty = "+ empty);
	         CheckBox box = new CheckBox();
	          if (empty) {
	           setGraphic(null);
	          } else {
//	        	BooleanProperty checked = (BooleanProperty)column.getCellObservableValue(getIndex());
	        	box.setSelected(false);
//	        	box.selectedProperty().bindBidirectional(checked);
	        	setGraphic(box);
//	           BooleanProperty checked = (BooleanProperty)column.getCellObservableValue(getIndex());
//	           BooleanProperty checked = (BooleanProperty)column.getCellObservableValue(getIndex());
//	           CartVO cn = (CartVO)column.getTableView().getItems().get(getIndex());
	           
	           if (checked.get()){
	        	   //체크가 됐을 때
	           } else {
	        	   //체크가 풀렸을 때
	        	   
	           }
	           box.setSelected(checked.get());
	           box.selectedProperty().bindBidirectional(checked);
	           
//	           setGraphic(box);
	          }
		    }
	    });
//        cart_table.getColumns().add(checkbox);
        cart_table.setEditable(true);
        
        checkbox.setOnEditStart(e -> {
        	System.out.println(e.getTablePosition());
        	System.out.println(e.getNewValue());
        	System.out.println(e.getOldValue());
        	System.out.println(e.getRowValue());
        	System.out.println(e.getSource().toString());
        });
        */
		//
		allTableData = FXCollections.observableArrayList(list);
		
		

		try {
			list = (ArrayList<CartVO>) bds.getAllList(carts);
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}
		
		
		allTableData.setAll(list);
	//	allTableData.add(vo);
		
		cart_table.setItems(allTableData);
		
		calAllCost(list);
		
   	 	itemsForPage = 10; // 한페이지에 보여줄 항목수 설정
   	 	int totPageCount = allTableData.size()%itemsForPage == 0 ? 
   			 allTableData.size()/itemsForPage : allTableData.size()/itemsForPage + 1;
   	 
   			pagination.setPageCount(totPageCount); //전체페이지 수 설정
			
			pagination.setPageFactory(new Callback<Integer, Node>(){

				@Override
				public Node call(Integer pageIndex) {
					from = pageIndex * itemsForPage;
					to = from + itemsForPage -1;
					cart_table.setItems(getTableViewData(from, to));
					return cart_table;
				}

				private ObservableList<CartVO> getTableViewData(int from, int to) {
					currentPageData = FXCollections.observableArrayList(); //초기화
					int totSize = allTableData.size();
						
					for(int i=from; i<=to && i<totSize; i++) {
						currentPageData.add(allTableData.get(i));
					}
					return currentPageData;
					
				}
				
			});
			
			//리스트 선택하여 누르면 접속
			
			Popup popup;
			popup = new Popup();
			
			cart_table.setOnMouseClicked(e->{
				CartVO bt = cart_table.getSelectionModel().getSelectedItem();
				if(bt==null) {
					return;
				}
				
				ScrollPane root1 = new ScrollPane();
				try {
					root1 = FXMLLoader.load(getClass().getResource("cart_read.fxml"));
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
//				root1.setPrefSize(300, 350);

				Parent content = root1;

				Scene scene = new Scene(content);

				Stage window = new Stage();
				window.setScene(scene);
				window.setTitle("장바구니 상세");

				window.show();
				
				//빈목록 클릭안되게 하는 메서드
				/*
				Parent pop = null;
				 if(popup.isShowing()){
			            popup.hide();
			        }else {
			       pop = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/cart2/cart_read.fxml"));
						Scene scene = new Scene(pop);
						popup(scene);

						팝업시*/
				
				
//				Parent change = null;
//				
//				try {
//					
//					change = FXMLLoader.load(getClass().getResource("cart_read.fxml"));
//					Scene scene = new Scene(change);
//					Stage primaryStage = (Stage)cart_table.getScene().getWindow();
//					primaryStage.setScene(scene);
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
				
				Text read_title = (Text) content.lookup("#read_title");  	//제목
				Text read_writer = (Text) content.lookup("#read_writer");	//작성자
				Text read_date = (Text) content.lookup("#read_date");		//이름이 저렇다고 혼동x 가격
				Text read_content = (Text) content.lookup("#read_content");	//내용
					
			
				//게시글 내용 변경 하기
				
				read_title.setText(bt.getProd_name());
				read_writer.setText(String.valueOf(bt.getProd_gu_nm()));
				read_date.setText(String.valueOf(bt.getProd_total_cost()));
				read_content.setText(bt.getProd_info());
	
		/* 목록으로 가기 버튼
			JFXButton list_btn = (JFXButton) change.lookup("#list_btn"); //게시글 목록 버튼
			list_btn.setOnAction(e1->{
				Parent change2 = null;
				try {
//					change2 = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/cart2/main_topandbottom.fxml"));
					change2 = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/cart2/cart2.fxml"));
					Scene scene = new Scene(change2);
					
					System.out.println("실행되냐?");
					
					Stage primaryStage = (Stage)list_btn.getScene().getWindow();
					primaryStage.setScene(scene);
					
//                    LoginSession.startMain.NomarBoardLink();
	
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			});			
			*/
			
//				int sel_no = cart_table.getSelectionModel().getSelectedIndex();
//				
//				int sel_ind = (pagination.getCurrentPageIndex()) * (itemsForPage)
//						+ cart_table.getSelectionModel().getSelectedIndex();
//				int sel_re_no = list.get(sel_ind).getProd_id();
				vo = cart_table.getSelectionModel().getSelectedItem();
				String user = bt.getProd_name();
				vo.setProd_name(user);
//				vo.setProd_id(sel_re_no);
		});	
			
		windowPop = new Stage();
		buyBtn.setOnAction(e3 -> {
			 List<CartVO> cartVO = new ArrayList<CartVO>();
			
			 for(int i = 0; i <= list.size()-1; i++) {
				 LoginSession.cartssion.add(list.get(i)); // 리스트 정보 담기
			 }
		 	AnchorPane pop = new AnchorPane();
//				 	Parent contentPop = pop;
		 	try {
				pop = FXMLLoader.load(getClass().getResource("../main/salse/center/cartPayCheck/cart_payCheck.fxml"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			pop.setPrefSize(990, 1475);

			Parent content = pop;

			Scene scene = new Scene(content);
			
			
			windowPop = new Stage();
			windowPop.setScene(scene);
			windowPop.setTitle("장바구니 상세");

			windowPop.show();
			
			windowPop.setOnHidden((WindowEvent event1)->{
				System.out.println("setOnHidden");
				list = new ArrayList<>();
				allTableData.setAll(list);
				cart_table.setItems(allTableData);
				totalCost.setText("0");
			});
//			infoMsg("결제", "결제 페이지로 넘어가주세요!");
		});
		
		
}
	
	
	private void addButtonToTable() {
		        Callback<TableColumn<CartVO, Void>, TableCell<CartVO, Void>> cellFactory = new Callback<TableColumn<CartVO, Void>, TableCell<CartVO, Void>>() {
		            @Override
		            public TableCell<CartVO, Void> call(final TableColumn<CartVO, Void> param) {
		                final TableCell<CartVO, Void> cell = new TableCell<CartVO, Void>() {

		                    private final Button btn = new Button("선택");

		                    {
		                        btn.setOnAction(e -> {
		                        	CartVO cData = getTableView().getItems().get(0);
		                        	CartVO cvo = new CartVO();
		                        	CartVO cvo1 = new CartVO();
		                        	cvo.setMem_no(cData.getMem_no());
		                        	cvo.setCart_no(cData.getCart_no());
		                        	cvo.setCart_qty(cData.getCart_qty());
		                        	cvo.setProd_total_cost(cData.getProd_total_cost());
		                        	cvo.setProd_id(cData.getProd_id());
		                        	
		                        	System.out.println("버튼을 눌렀습니다.");
		                        	
		                        	try {
		                        		int cnt = bds.deleteCart(cvo);
		                        		System.out.println("cnt = " + cnt);
										if(cnt > 0) {
											AlertMsg.info("장바구니목록에서 삭제되었습니다.");
										}
									} catch (RemoteException e1) {
										e1.printStackTrace();
									}
//			                            System.out.println("selectedData: " + data);
		                        	
		                      
		                        	
		                        	try {
		                				list = (ArrayList<CartVO>) bds.getAllList(carts);
		                			}catch (RemoteException e3) {
		                				e3.printStackTrace();
		                			}
		                        	prod_gu_nm.setCellValueFactory(new PropertyValueFactory<>("prod_gu_nm"));
		                    		cart_no.setCellValueFactory(new PropertyValueFactory<>("cart_no"));
		                    		cart_qty.setCellValueFactory(new PropertyValueFactory<>("cart_qty"));		
		                    		prod_name.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
		                    		prod_info.setCellValueFactory(new PropertyValueFactory<>("prod_info"));
		                    		prod_total_cost.setCellValueFactory(new PropertyValueFactory<>("prod_total_cost"));
		                    		
		                	//		addButtonToTable();
		                			
		                			allTableData = FXCollections.observableArrayList();
		                			allTableData.setAll(list);
		                			cart_table.setItems(allTableData);
		                			calAllCost( list);
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

		        cart_table.getColumns().add(colBtn);

		    }
	//확인 알림메시지
	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("알림");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}
	
	//에러 알림메시지
	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
	@FXML public void countChk() {
		
		
	}
	
	public void calAllCost(ArrayList<CartVO> list) {
		int allCost = 0;
		
		for (int i = 0; i < list.size(); i++) {
			allCost += list.get(i).getProd_total_cost();
		}
		
		totalCost.setText(allCost+"");
	}
	
}
