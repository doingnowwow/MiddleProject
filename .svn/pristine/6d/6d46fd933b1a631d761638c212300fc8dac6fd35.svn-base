package kr.or.ddit.service.horeview;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.HotelReviewVO;


public interface HoReviewService extends Remote{

	/**
	 * 1.자신이 쓴 리뷰 조회
	 * @param mem_no
	 * @return
	 * @throws RemoteException
	 */
	public List<HotelReviewVO> selectRewview(HotelReviewVO mem_no) throws RemoteException;
	
	/**
	 * 날짜로 자신이 쓴 리뷰 조회
	 */
	public List<HotelReviewVO> selectMyReviewDate(HotelReviewVO mv) throws RemoteException;
	
	/**
	 * 자신이 쓴 리뷰 제목과 내용으로 조회
	 */
	public List<HotelReviewVO> selectMyReviewText(HotelReviewVO mv) throws RemoteException;
	
	/**
	 * 호텔 넘버로 조회
	 */
	public List<HotelReviewVO> selectReviewHotel(HotelReviewVO hotel_no) throws RemoteException;

	/**
	 * 2.리뷰작성 
	 */
	public int insertHotelReview(HotelReviewVO vo) throws RemoteException;
	
	/**
	 * 3.리뷰삭제(글번호, mem_no 받아와야함)
	 */
	public int deleteHotelReview(HotelReviewVO vo) throws RemoteException;
	
	/**
	 * 4.리뷰수정(글번호, mem_no 받아와야함)
	 */
	public int updateHotelReview(HotelReviewVO vo) throws RemoteException;
	
	/**
	 * 5. 관리자 리뷰 관리
	 * @return
	 * @throws RemoteException
	 */
	public List<HotelReviewVO> adminHotelReview() throws RemoteException;
	
	/**
	 * 6. 관리자 리뷰 삭제
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public int deleteAdminHotelReview(HotelReviewVO vo) throws RemoteException;
	
	/**
	 * 7. 호텔별 리뷰 조회
	 * @param hotel_no
	 * @return
	 * @throws RemoteException
	 */
	public List<HotelReviewVO> selectHotelReview(HotelReviewVO hotel_no) throws RemoteException;
	
}
