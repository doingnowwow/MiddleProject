package kr.or.ddit.service.book;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.BookVO;

public interface BookService extends Remote{
	
	/**
	 * 예약 현황 조회
	 * @param hotel_no
	 * @return
	 */
	public List<BookVO> getAllBook(BookVO vo) throws RemoteException;
	
	
	
	/**
	 * 퍼피 예약
	 * @param vo
	 * @return
	 */
	public int insertBooking(BookVO vo) throws RemoteException;
	
	/**
	 * 사용자 나의 예약 현황 조회
	 * @param mem_no
	 * @return
	 */
	public List<BookVO> selectMyBook(int mem_no) throws RemoteException;
	
	/**
	 * 예약현황 수정
	 * @param vo
	 * @return
	 */
	public int updateBooking(BookVO vo) throws RemoteException;
	
	/**
	 * 예약 삭제
	 * @param vo
	 * @return
	 */
	public int deleteBooking(BookVO vo) throws RemoteException;

	/**
	 * 회사 예약 현황 조회
	 * @param com_no
	 * @return
	 */
	public List<BookVO> selectComBook(int com_no) throws RemoteException;
	
	/**
	 * 현재까지 저장된 호텔 예약번호 호출 --승재
	 * @return
	 * @throws RemoteException
	 */
	public int selectMaxBook_no() throws RemoteException;
	
	/**
	 * 호텔번호 수기 등록을 통한 호텔 예약-- 승재
	 * @return
	 * @throws RemoteException
	 */
	public int insertBookReserv(BookVO vo) throws RemoteException;
	
}
