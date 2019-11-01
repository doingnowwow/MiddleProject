package kr.or.ddit.view.reviewboard;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.reviewBoard.ReviewBoardService;
import kr.or.ddit.vo.ReviewBoardVO;

public class ReviewController implements Initializable {

	@FXML private TableView<ReviewBoardVO> board_table;
	
	@FXML private TableColumn<ReviewBoardVO,Integer> bd_no;
	@FXML private TableColumn<ReviewBoardVO,String> bd_title;
	@FXML private TableColumn<ReviewBoardVO,String> mem_id;
	@FXML private TableColumn<ReviewBoardVO,String> bd_date;
	@FXML private TableColumn<ReviewBoardVO,Integer> bd_open;
	@FXML private TableColumn<ReviewBoardVO,Integer> bd_up;
	
	@FXML 
	private Pagination pagination;
	
	private int from, to, itemsForPage;
	
	private ObservableList<ReviewBoardVO> allTableData, currentPageData;
	
	private ReviewBoardVO vo = new ReviewBoardVO();
	public static ReviewBoardVO boardvo;
	
	private Registry reg;
	private ReviewBoardService bds;
	
	@FXML private Button write_regist_btn;

	@FXML private ScrollPane ap_home;
	
	@FXML private JFXButton write_btn;
	
	private ArrayList<ReviewBoardVO> list = new ArrayList<>();
	
	@FXML TableColumn bd_writer;
	
	@FXML private JFXButton delete_btn;

	@FXML Label count_label; 
	
	public void initialize(URL location, ResourceBundle resources) {
		
		//rmi연결
		try {	
			reg = LocateRegistry.getRegistry("localhost", 8888);
			bds = (ReviewBoardService) reg.lookup("reviewboardservice");
			System.out.println("연결 성공");
			
		}catch (RemoteException e) {
			System.out.println("실패 ㅋ");
			e.printStackTrace();
		}catch(NotBoundException e) {
			e.printStackTrace();
		}
				
				
		/*
		companyInformationButton1.setOnAction(e ->{
			try {
				Parent change = FXMLLoader.load(getClass().getResource("/kr/or/ddit/job/view/companyInformation/companyInformationMain.fxml"));
				Scene scene = new Scene(change);
				Stage primaryStage = (Stage)companyInformationButton1.getScene().getWindow();
				primaryStage.setScene(scene);
			}catch (Exception i) {
				i.printStackTrace();
			}
		});
		*/
		
		
//		//테이블에 리스트 추가
//		bd_no.setCellValueFactory(new PropertyValueFactory<>("rebd_no"));
//		bd_title.setCellValueFactory(new PropertyValueFactory<>("rebd_title"));
//		mem_id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
//		bd_date.setCellValueFactory(new PropertyValueFactory<>("rebd_date"));
//		bd_open.setCellValueFactory(new PropertyValueFactory<>("rebd_open"));
//		bd_up.setCellValueFactory(new PropertyValueFactory<>("rebd_up"));
		
	
		//테이블에 리스트 추가
		bd_no.setCellValueFactory(new PropertyValueFactory<>("rebd_no"));
		bd_title.setCellValueFactory(new PropertyValueFactory<>("rebd_title"));
		mem_id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		bd_date.setCellValueFactory(new PropertyValueFactory<>("rebd_date"));
		bd_open.setCellValueFactory(new PropertyValueFactory<>("rebd_open"));
		bd_up.setCellValueFactory(new PropertyValueFactory<>("rebd_up"));
				
				try {
					list = (ArrayList<ReviewBoardVO>) bds.getReAllList();
				} catch (RemoteException e2) {
					e2.printStackTrace();
				}
			
				allTableData = FXCollections.observableArrayList();
				
				
				
				allTableData.setAll(list);
				allTableData.add(vo);
				
				board_table.setItems(allTableData);
				
				itemsForPage = 14; //한페이지에 보여줄 항목 수 설정
				int totPageCount = allTableData.size()%itemsForPage == 0?
						allTableData.size() / itemsForPage :
						allTableData.size()/itemsForPage+1;
						
				pagination.setPageCount(totPageCount); //전체페이지 수 설정
				
				pagination.setPageFactory(new Callback<Integer, Node>(){

					@Override
					public Node call(Integer pageIndex) {
						from = pageIndex * itemsForPage;
						to = from + itemsForPage -1;
						board_table.setItems(getTableViewData(from, to));
						return board_table;
					}
				});
				
				
				
				
				//리스트 선택
				board_table.setOnMouseClicked(e->{
					
					ReviewBoardVO bt = board_table.getSelectionModel().getSelectedItem();

					Parent change = null;
					try {
						change = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/reviewboard/review_read5.fxml"));
						Scene scene = new Scene(change);
						Stage primaryStage = (Stage)board_table.getScene().getWindow();
						primaryStage.setScene(scene);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					
					
					//community_read2.fxml
				
					Text read_title = (Text) change.lookup("#read_title");  	//제목
					Text read_writer = (Text) change.lookup("#read_writer");	//작성자
					Text read_date = (Text) change.lookup("#read_date");		//작성일
					Text read_up = (Text) change.lookup("#read_up");			//추천수
					Text read_view = (Text) change.lookup("#read_view");		//조회수
					Text read_content = (Text) change.lookup("#read_content");	//내용
						
					
					//게시글 내용 변경 하기
					read_title.setText(bt.getRebd_title());
					read_writer.setText(bt.getMem_id());
					read_date.setText(bt.getRebd_date());
					read_up.setText(String.valueOf(bt.getRebd_up()));
					read_view.setText(String.valueOf(bt.getRebd_open()));
					read_content.setText(bt.getRebd_content());
					
					
					//수정버튼
					JFXButton modify_btn = (JFXButton) change.lookup("#modify_btn"); //게시글 목록 버튼
					modify_btn.setOnAction(e1->{
						Parent change2 = null;
						try {
							change2 = FXMLLoader.load(getClass().getResource("review_update.fxml"));
							Scene scene = new Scene(change2);
							Stage primaryStage = (Stage)modify_btn.getScene().getWindow();
							primaryStage.setScene(scene);
					
						}catch (Exception e2) {
							e2.printStackTrace();
						}
						

						TextField update_title = (TextField) change2.lookup("#update_title"); //게시글 제목
						TextArea update_content = (TextArea) change2.lookup("#update_content"); //게시글 내용
						
						update_title.setText(read_title.getText());
						update_content.setText(read_content.getText());
						

						//수정완료 버튼
						JFXButton commit_btn = (JFXButton) change2.lookup("#commit_btn");
						
						commit_btn.setOnAction(e2->{
					
							vo.setRebd_no(bt.getRebd_no());
							vo.setMem_id(bt.getMem_id());
							
							if(update_title.getText()!=null) {
								vo.setRebd_title(update_title.getText());
							}else {
								errMsg("실패","제목을 입력해주세요.");
								return;
							}
							
							if(update_content.getText()!=null) {
								vo.setRebd_content(update_content.getText());
							}else {
								errMsg("실패","내용을 입력해주세요.");
								return;
							}
							
							try {
								Parent change3 = null;
								int in_cnt =0;
								in_cnt = bds.updateReBoard(vo);
								
								if(in_cnt > 0) {
									infoMsg("성공!", "게시글이 성공적으로 수정되었습니다!");
									
									//게시글 등록시 게시글 목록화면으로 
									change3 = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/startMain/main_topandbottom.fxml"));
									Scene scene = new Scene(change3);
									Stage primaryStage = (Stage)commit_btn.getScene().getWindow();
									primaryStage.setScene(scene);
									
									LoginSession.startMain.ReviewBoardLink();
							
								}else {
									errMsg("실패!", "빈 항목이 있는지 확인하여 주세요.");
								}
								
								
							} catch (RemoteException e5) {
								e5.printStackTrace();
							} catch (IOException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
							}
							
							
						});
						
					});
			
					   
			         //삭제하기
			         JFXButton delete_btn = (JFXButton) change.lookup("#delete_btn"); //게시글 목록 버튼
			         delete_btn.setOnAction(e2->{
			            int sel_no = board_table.getSelectionModel().getSelectedIndex();
			              
			             int sel_ind = (pagination.getCurrentPageIndex()) * (itemsForPage)
			                  + board_table.getSelectionModel().getSelectedIndex();
			            int sel_re_no = list.get(sel_ind).getRebd_no();
			            
			            String user = bt.getMem_id();
			            vo.setMem_id(user);
			            vo.setRebd_no(sel_re_no);
			            
			            try {
			               int del_re = bds.deleteReBoard(vo);
			               
			               
			               if(del_re > 0) {
			                   infoMsg("성공!", "게시글이 삭제되었습니다!");
			                   Parent change2 = null;
			                   try {
			                      change2 = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/startMain/main_topandbottom.fxml"));
			                      Scene scene = new Scene(change2);
			                      Stage primaryStage = (Stage)delete_btn.getScene().getWindow();
			                      primaryStage.setScene(scene);
			                      LoginSession.startMain.ReviewBoardLink();
			                
			                   }catch (Exception e1) {
			                      e1.printStackTrace();
			                   }
			                }
			              
			                 if(del_re < 0) {
			                    System.out.println("삭제 안됨..");
			                    errMsg("실패!", "삭제가 안된 원인을 찾아보세요.."); 
			                 }
			            } catch (RemoteException e1) {
			               e1.printStackTrace();
			            }
			         });
			         
					
					
					//목록버튼 
					JFXButton list_btn = (JFXButton) change.lookup("#list_btn"); //게시글 목록 버튼
					list_btn.setOnAction(e1->{
						Parent change2 = null;
						try {
							change2 = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/startMain/main_topandbottom.fxml"));
							Scene scene = new Scene(change2);
							Stage primaryStage = (Stage)list_btn.getScene().getWindow();
							primaryStage.setScene(scene);
							LoginSession.startMain.ReviewBoardLink();
					
						}catch (Exception e2) {
							e2.printStackTrace();
						}
					});
				});
			
				
				
				//게시글 목록화면에서 글작성 눌렀을때
				write_btn.setOnAction(e ->{
					Parent change =null;	
					try {
						change = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/reviewboard/review_write.fxml"));
						Scene scene = new Scene(change);
						Stage primaryStage = (Stage)write_btn.getScene().getWindow();
						primaryStage.setScene(scene);
						
					}catch(Exception i) {
						i.printStackTrace(); 
					}
					
					TextField write_title = (TextField) change.lookup("#write_title"); //제목
					TextArea write_content = (TextArea) change.lookup("#write_content"); //내용		
					JFXButton write_regist_btn = (JFXButton) change.lookup("#write_regist_btn"); //게시글 등록 버튼
					JFXButton write_cancle_btn = (JFXButton) change.lookup("#write_cancle_btn"); //게시글 취소 버튼
					
					
					/**
					 * 게시글 작성 등록하기
					 */
					write_regist_btn.setOnAction(e1->{
						
						if(write_title.getText()!=null) {
							vo.setRebd_title(write_title.getText());
						}else {
							errMsg("실패","제목을 입력해주세요.");
							return;
						}
						
						if(write_content.getText()!=null) {
							vo.setRebd_content(write_content.getText());
						}else {
							errMsg("실패","내용을 입력해주세요.");
							return;
						}
						
						try {
							Parent change2 =null;
							int in_cnt =0;
							in_cnt = bds.insertReBoard(vo);
							
							if(in_cnt > 0) {
								infoMsg("성공!", "게시글이 성공적으로 등록되었습니다!");
								
								//게시글 등록시 게시글 목록화면으로 
								change2 = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/startMain/main_topandbottom.fxml"));
								Scene scene = new Scene(change2);
								Stage primaryStage = (Stage)write_regist_btn.getScene().getWindow();
								primaryStage.setScene(scene);
								
								LoginSession.startMain.ReviewBoardLink();
						
							}else {
								errMsg("실패!", "빈 항목이 있는지 확인하여 주세요.");
							}
							
							
						} catch (RemoteException e5) {
							e5.printStackTrace();
						} catch (IOException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						
					});
					
					//삭제
					
					
					
					
					
					
					/**
					 * 글쓰기 작성 취소
					 */
					write_cancle_btn.setOnAction(e2->{
						Parent change1 =null;
						try {
							LoginSession.startMain.ReviewBoardLink();
							change1 = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/startMain/main_topandbottom.fxml"));
							Scene scene = new Scene(change1);
							Stage primaryStage = (Stage)write_cancle_btn.getScene().getWindow();
							primaryStage.setScene(scene);
							LoginSession.startMain.ReviewBoardLink();
						} catch (IOException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						
					});
					
					
				});
			}
				
				

			private ObservableList<ReviewBoardVO> getTableViewData(int from, int to) {
				currentPageData = FXCollections.observableArrayList(); //초기화
				int totSize = allTableData.size();
					
				for(int i=from; i<=to && i<totSize; i++) {
					currentPageData.add(allTableData.get(i));
				}
				return currentPageData;

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
			
		}
