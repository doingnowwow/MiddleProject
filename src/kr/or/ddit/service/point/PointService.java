package kr.or.ddit.service.point;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.PointVO;

public interface PointService extends Remote{
	
	/**
	 * 호텔 결제를 통한 적립금 처리
	 * @param vo
	 * @return
	 */
	public int insertPointReserv(PointVO vo) throws RemoteException;
	
	/**
	 * 회원번호를 통한 vo마일리지 호출
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public int searchMyPoint(PointVO vo) throws RemoteException;
	
	/**
	 * 멤버번호로 포인트 리스트 조회
	 * @param mem_no
	 * @return
	 * @throws RemoteException
	 */
	public List<PointVO> searchMem_no(int mem_no) throws RemoteException;
}
