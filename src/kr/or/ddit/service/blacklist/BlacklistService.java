package kr.or.ddit.service.blacklist;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.BlackVO;

public interface BlacklistService extends Remote {

	/**
	 * 블랙리스트 회원 첫 추가
	 */
	public int insertBlack(BlackVO bv) throws RemoteException;
	
	/**
	 * 블랙리스트에 등록된 회원의 카운트 업
	 */
	public int updateBlack(BlackVO bv) throws RemoteException;
	
	/**
	 * 블랙리스트 회원 탈퇴
	 */
	public int deleteBlack(BlackVO bv) throws RemoteException;
	
	/**
	 * 블랙리스트 회원 전부 출력
	 */
	public List<BlackVO> showAllB() throws RemoteException;
	
	/**
	 * 블랙리스트 회원 찾기
	 */
	public List<BlackVO> searchB(BlackVO bv) throws RemoteException;
}
