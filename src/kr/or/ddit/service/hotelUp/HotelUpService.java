package kr.or.ddit.service.hotelUp;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.HotelUpVO;





public interface HotelUpService extends Remote {


	/**
	 * 호텔등록
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public int insertHotelUp(HotelUpVO vo) throws RemoteException;
	
	/**
	 * 호텔 수정
	 * @param vo
	 * @return
	 * @throws RemoteException
	 *//*
	public int updateHotel(HotelVO vo) throws RemoteException;*/
	
	/**
	 * 호텔 삭제
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public int deleteHotel(HotelUpVO vo) throws RemoteException;
	
	
	/**
	 * 사업자별 호텔 검색
	 * @param com_no
	 * @return
	 * @throws RemoteException
	 */
	public List<HotelUpVO> selectHotelList(HotelUpVO vo) throws RemoteException;
	
	/**
	 * 수정
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public int updateHotelUp(HotelUpVO vo) throws RemoteException;
	
	/**
	 *  호텔 전체 목록 출력
	 */
	public List<HotelUpVO> getAllHotelList(HotelUpVO vo) throws RemoteException;
	
	
}
