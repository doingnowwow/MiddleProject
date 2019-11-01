package kr.or.ddit.service.comReservCheck;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ComReservCheckVO;

public interface ReservCheckService extends Remote{

	/**
	 * 예약현황 조회
	 */
	public List<ComReservCheckVO> getAllBookCheck(ComReservCheckVO cv) throws RemoteException;
	
	/**
	 * month로 예약현황 조회
	 */
	public List<ComReservCheckVO> getAllBookByMonth(ComReservCheckVO cv) throws RemoteException;
}
