package kr.or.ddit.view.board;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.ddit.clientMain.LoginSession;
import kr.or.ddit.service.board.BoardService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import javafx.scene.control.ComboBox;

public class BoardWriteController implements Initializable {
	
	@FXML private TableView<BoardVO> board_table;
	
	@FXML private TableColumn<BoardVO,Integer> bd_no;
	@FXML private TableColumn<BoardVO,String> bd_title;
	@FXML private TableColumn<BoardVO,String> mem_id;
	@FXML private TableColumn<BoardVO,String> bd_date;
	@FXML private TableColumn<BoardVO,Integer> bd_open;
	@FXML private TableColumn<BoardVO,Integer> bd_up;
	
	@FXML 
	private Pagination pagination;
	
	private int from, to, itemsForPage;
	
	private ObservableList<BoardVO> allTableData, currentPageData;
	
	private BoardVO vo = new BoardVO();
	public static BoardVO boardvo;
	
	private Registry reg;
	private BoardService bds;
	
	@FXML private Button write_regist_btn;

	@FXML private ScrollPane ap_home;
	
	@FXML private JFXButton write_btn;
	
	private ArrayList<BoardVO> list = new ArrayList<>();
	
	@FXML TableColumn bd_writer;
	
	@FXML private JFXButton delete_btn;

	@FXML Label count_label; 
	
	@FXML Button searchBtn;

	@FXML TextField searchContent;

	@FXML Button search;

	@FXML ComboBox combobox;
	   private ObservableList<String> li = 
		         FXCollections.observableArrayList("제목", "작성자");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 combobox.setItems(li);
		//rmi연결
		try {	
			reg = LocateRegistry.getRegistry("localhost", 8888);
			bds = (BoardService) reg.lookup("boardservice");
			System.out.println("연결 성공");
			
		}catch (RemoteException e) {
			System.out.println("실패 ㅋ");
			e.printStackTrace();
		}catch(NotBoundException e) {
			e.printStackTrace();
		}
				
				
		/*
		companyInformationBut
		]ton1.setOnAction(e ->{
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
		
		
		//테이블에 리스트 추가
		bd_no.setCellValueFactory(new PropertyValueFactory<>("bd_no"));
		bd_title.setCellValueFactory(new PropertyValueFactory<>("bd_title"));
		mem_id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		bd_date.setCellValueFactory(new PropertyValueFactory<>("bd_date"));
		bd_open.setCellValueFactory(new PropertyValueFactory<>("bd_open"));
		bd_up.setCellValueFactory(new PropertyValueFactory<>("bd_up"));
		
	
		//테이블에 리스트 추가
				bd_no.setCellValueFactory(new PropertyValueFactory<>("bd_no"));
				bd_title.setCellValueFactory(new PropertyValueFactory<>("bd_title"));
				mem_id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
				bd_date.setCellValueFactory(new PropertyValueFactory<>("bd_date"));
				bd_open.setCellValueFactory(new PropertyValueFactory<>("bd_open"));
				bd_up.setCellValueFactory(new PropertyValueFactory<>("bd_up"));
				
			
				allTableData = FXCollections.observableArrayList();
				
				try {
					list = (ArrayList<BoardVO>) bds.getAllList();
				} catch (RemoteException e2) {
					e2.printStackTrace();
				}
				
				
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
					
					BoardVO bt = board_table.getSelectionModel().getSelectedItem();

					Parent change = null;
					try {
						
						change = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/board/community_read5.fxml"));
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
					read_title.setText(bt.getBd_title());
					read_writer.setText(bt.getMem_id());
					read_date.setText(bt.getBd_date());
					read_up.setText(String.valueOf(bt.getBd_up()));
					read_view.setText(String.valueOf(bt.getBd_open()));
					read_content.setText(bt.getBd_content());
					
					
					//수정버튼
					JFXButton modify_btn = (JFXButton) change.lookup("#modify_btn"); //게시글 목록 버튼
					modify_btn.setOnAction(e1->{
						Parent change2 = null;
						try {
							change2 = FXMLLoader.load(getClass().getResource("community_update.fxml"));
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
					
							vo.setBd_no(bt.getBd_no());
							vo.setMem_id(bt.getMem_id());
							
							if(update_title.getText()!=null) {
								vo.setBd_title(update_title.getText());
							}else {
								errMsg("실패","제목을 입력해주세요.");
								return;
							}
							
							if(update_content.getText()!=null) {
								vo.setBd_content(update_content.getText());
							}else {
								errMsg("실패","내용을 입력해주세요.");
								return;
							}
							
							try {
								Parent change3 = null;
								int in_cnt =0;
								in_cnt = bds.updateBoard(vo);
								
								if(in_cnt > 0) {
									infoMsg("성공!", "게시글이 성공적으로 수정되었습니다!");
									
									//게시글 등록시 게시글 목록화면으로 
									change3 = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/startMain/main_topandbottom.fxml"));
									Scene scene = new Scene(change3);
									Stage primaryStage = (Stage)commit_btn.getScene().getWindow();
									primaryStage.setScene(scene);
									
									LoginSession.startMain.NomarBoardLink();
									
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
			            int sel_re_no = list.get(sel_ind).getBd_no();
			            
			            String user = bt.getMem_id();
			            vo.setMem_id(user);
			            vo.setBd_no(sel_re_no);
			            
			            try {
			               int del_re = bds.deleteBoard(vo);
			               
			               
			               if(del_re > 0) {
			                   infoMsg("성공!", "게시글이 삭제되었습니다!");
			                   Parent change2 = null;
			                   try {
			                      change2 = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/startMain/main_topandbottom.fxml"));
			                      Scene scene = new Scene(change2);
			                      Stage primaryStage = (Stage)delete_btn.getScene().getWindow();
			                      primaryStage.setScene(scene);
			                      
			                      LoginSession.startMain.NomarBoardLink();
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
							
		                    LoginSession.startMain.NomarBoardLink();
					
						}catch (Exception e2) {
							e2.printStackTrace();
						}
					});
				});
			
				
				
				//게시글 목록화면에서 글작성 눌렀을때
				write_btn.setOnAction(e ->{
					Parent change =null;	
					try {
						change = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/board/community_write.fxml"));
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
							vo.setBd_title(write_title.getText());
						}else {
							errMsg("실패","제목을 입력해주세요.");
							return;
						}
						
						if(write_content.getText()!=null) {
							vo.setBd_content(write_content.getText());
						}else {
							errMsg("실패","내용을 입력해주세요.");
							return;
						}
						
						try {
							Parent change2 =null;
							int in_cnt =0;
							in_cnt = bds.insertBoard(vo);
							
							if(in_cnt > 0) {
								infoMsg("성공!", "게시글이 성공적으로 등록되었습니다!");
								
								//게시글 등록시 게시글 목록화면으로 
								change2 = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/startMain/main_topandbottom.fxml"));
								Scene scene = new Scene(change2);
								Stage primaryStage = (Stage)write_regist_btn.getScene().getWindow();
								primaryStage.setScene(scene);
								
								LoginSession.startMain.NomarBoardLink();
								
						
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
					
					// 검색
					/*BoardVO vo = new BoardVO();*/
					/*searchBtn.setOnAction(event -> {
						bd_no.setCellValueFactory(new PropertyValueFactory<>("bd_no"));
						bd_title.setCellValueFactory(new PropertyValueFactory<>("bd_title"));
						mem_id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
						bd_date.setCellValueFactory(new PropertyValueFactory<>("bd_date"));
						bd_open.setCellValueFactory(new PropertyValueFactory<>("bd_open"));
						bd_up.setCellValueFactory(new PropertyValueFactory<>("bd_up"));
						
					
						allTableData = FXCollections.observableArrayList();
						
						try {
							
							list = (ArrayList<BoardVO>) bds.searchList(vo);
						} catch (RemoteException e2) {
							e2.printStackTrace();
						}
						
						
						allTableData.setAll(list);
						allTableData.add(vo);
						
						board_table.setItems(allTableData);
						
						itemsForPage = 14; //한페이지에 보여줄 항목 수 설정
						totPageCount = allTableData.size()%itemsForPage == 0?
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
					*/
										
					/**
					 * 글쓰기 작성 취소
					 */
					write_cancle_btn.setOnAction(e2->{
						Parent change1 =null;
						try {
							change1 = FXMLLoader.load(getClass().getResource("/kr/or/ddit/view/startMain/main_topandbottom.fxml"));
							Scene scene = new Scene(change1);
							Stage primaryStage = (Stage)write_cancle_btn.getScene().getWindow();
							primaryStage.setScene(scene);
							LoginSession.startMain.NomarBoardLink();
						} catch (IOException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						
					});
				});
			
			}
			

			private ObservableList<BoardVO> getTableViewData(int from, int to) {
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
			//검색
			 @FXML
			   public void searchNotice() throws IOException {   
			      if(combobox.getValue()==null) {
//			         Message.errMsg("ERROR", "검색 항목을 선택해주세요");
			         return;
			      }
			      list = new ArrayList<BoardVO>();
			      
			      String combo = combobox.getValue().toString();
			      String text = searchContent.getText();
			      
			      if(combo.equals("제목")) {
			    	  list = (ArrayList<BoardVO>) bds.searchBoardTitle(text);
			      }else if(combo.equals("작성자")) {
			    	  list = (ArrayList<BoardVO>) bds.searchBoardWriter(text);
			      }
			      
			      
			      bd_no.setCellValueFactory(new PropertyValueFactory<>("bd_no"));
					bd_title.setCellValueFactory(new PropertyValueFactory<>("bd_title"));
					mem_id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
					bd_date.setCellValueFactory(new PropertyValueFactory<>("bd_date"));
					bd_open.setCellValueFactory(new PropertyValueFactory<>("bd_open"));
					bd_up.setCellValueFactory(new PropertyValueFactory<>("bd_up"));
			      
			      
					allTableData = FXCollections.observableArrayList(list);
			      
					board_table.setItems(allTableData);
					System.out.println(text.length());
					System.out.println(bds.searchBoardTitle(text).size());
			   }

			
		}
