package kr.or.ddit.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MemberVO;

public interface AdminService extends Remote{
	
	public List<MemberVO> getAllBlackMember() throws RemoteException; //전체 블랙 회원 조회
	public int insertBlackMember(MemberVO mvo) throws RemoteException; //블랙 회원 정보 삽입
	public int deleteBlackMember(MemberVO mvo) throws RemoteException; //블랙 회원 정보 삭제 (회원번호 사용)
}
