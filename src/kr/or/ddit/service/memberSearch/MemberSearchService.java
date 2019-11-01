package kr.or.ddit.service.memberSearch;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MemberSearchVO;

public interface MemberSearchService extends Remote{

	/**
	 * 일반회원 조회
	 */
	public List<MemberSearchVO> getAllMember() throws RemoteException;
	
	/**
	 * 이름, 이메일, 주소로 일반회원 조회
	 */
	public List<MemberSearchVO> getMemberByTF(MemberSearchVO ms) throws RemoteException;
	
	/**
	 * 일반 회원의 애견 조회
	 */
	public List<MemberSearchVO> getAllMemPet() throws RemoteException;
	
	/**
	 * 애견이름, 크기, 종 등으로 애견 조회
	 */
	public List<MemberSearchVO> getMemPetByTF(MemberSearchVO ms) throws RemoteException;
	
	/**
	 * 사업자 회원 조회
	 */
	public List<MemberSearchVO> getAllComMem() throws RemoteException;
	
	/**
	 * 이름, 호텔 이름 주소 등으로 사업자 회원 조회
	 */
	public List<MemberSearchVO> getComMemByTF(MemberSearchVO ms) throws RemoteException;
}
