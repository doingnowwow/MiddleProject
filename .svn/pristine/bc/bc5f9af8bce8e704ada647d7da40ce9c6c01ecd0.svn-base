package kr.or.ddit.service.hotel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.HotelVO;

public interface HotelService extends Remote{
	
	
	/**
	 * 호텔등록
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public int insertHotel(HotelVO vo) throws RemoteException;
	
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
	public int deleteHotel(HotelVO vo) throws RemoteException;
	
	
	/**
	 * 사업자별 호텔 검색
	 * @param com_no
	 * @return
	 * @throws RemoteException
	 */
	public List<HotelVO> selectHotelList(HotelVO com_no) throws RemoteException;
	
	/**
	 * 선택한 호텔 정보 가져오기
	 */
	public List<HotelVO> getSelectedHotel(HotelVO vo) throws RemoteException;
	
	/**
	 *  호텔 전체 목록 출력
	 */
	public List<HotelVO> getAllHotelList() throws RemoteException;
	
	
	public List<HotelVO> hoNoSearch(HotelVO vo) throws RemoteException;
}
