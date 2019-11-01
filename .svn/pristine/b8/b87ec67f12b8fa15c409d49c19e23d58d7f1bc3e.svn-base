package kr.or.ddit.service.advertisement;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.AdvertisementVO;

public interface AdvertisementService extends Remote{
	
	/**
	 * 팝업 광고 추가
	 */
	public int insertAd(AdvertisementVO vo) throws RemoteException;
	
	/**
	 * 팝업 광고 삭제
	 */
	public int deleteAd(AdvertisementVO vo) throws RemoteException;
	
	/**
	 * 팝업광고 수정
	 */
	public int modifyAd(AdvertisementVO vo) throws RemoteException;
	
	/*
	 * 팝업 광고 전체 조회
	 */
	public List<AdvertisementVO> showAllAd() throws RemoteException;
	
	/**
	 * 팝업 광고 선택
	 */
	public List<AdvertisementVO> selectAd(AdvertisementVO vo) throws RemoteException;

}













