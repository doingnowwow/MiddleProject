package kr.or.ddit.service.memInfoUpdate;

import java.rmi.Remote;
import java.rmi.RemoteException;

import kr.or.ddit.vo.MemberVO;

public interface MemInfoUpdateService extends Remote {

	// 관리페이지로 들어가기 위한 비밀번호 입력

	public MemberVO getPw(MemberVO mvo) throws RemoteException; // 비밀번호 값 가져오기

	public MemberVO getDefaultInfo(MemberVO mvo) throws RemoteException; // 수정불가한 정보 가져오기

	public int deleteMember(MemberVO mvo) throws RemoteException; // 회원정보 삭제

	public int updateMember(MemberVO mvo) throws RemoteException;// 회원정보업데이트

}
