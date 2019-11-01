package kr.or.ddit.service.join;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MemDogVO;
import kr.or.ddit.vo.MemberVO;

public interface IJoinService extends Remote{
	// 아이디 중복 체크
	public List<MemberVO> selectId(MemberVO mv) throws RemoteException;
	
	// 이메일주소 중복 체크
	public List<MemberVO> selectEmail(MemberVO mv) throws RemoteException;
	
	// 멤버추가
	public int insertMember(MemberVO mv) throws RemoteException ;
	
	// 멤버의 개 추가
	public int insertMemDog(MemDogVO dv) throws RemoteException ;
}
