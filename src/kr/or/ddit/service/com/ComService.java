package kr.or.ddit.service.com;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ComVO;

public interface ComService extends Remote {

	/**
	 * 실제 DB에 연결해서 SQL문을 수행하여 결과를 작성하여 service에 전달하는 DAO의 Interface
	 * 
	 * @author PC02
	 */

	/**
	 * 실제 DB에 연결해서 SQL문을 수행하여 결과를 작성하여 service에 전달하는 DAO의 Interface
	 * 
	 * @author PC02
	 */

	// 관리 페이지로 들어가기 위한 비밀번호 입력
	public ComVO getCom_pw(ComVO cvo) throws  RemoteException ; // 비밀번호값 입력

	public ComVO ComgetDefaulInfo(ComVO cvo) throws  RemoteException ; // 수정불가한 기본정보 가져오기

	public int updateCom(ComVO cvo) throws  RemoteException; // 회원정보수정

	/**
	 * 사업자 회원 ID를 매개변수로 받아서 그 사업자 정보를 삭제하는 메서드.
	 * 
	 * @param com_id
	 *            삭제할 회원ID
	 * @return 작업성공 : 1, 작업실패 : 0'
	 */
	public int deleteCom(ComVO cvo) throws  RemoteException ;

	public List<ComVO> SearchComInfo(int com_no) throws RemoteException;
	
	public ComVO searchCom(ComVO com) throws RemoteException;
	
	public List<ComVO> getAllComMember()  throws RemoteException;


}
