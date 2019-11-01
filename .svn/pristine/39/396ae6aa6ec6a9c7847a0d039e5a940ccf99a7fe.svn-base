package kr.or.ddit.service.member;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.MemDogVO;
import kr.or.ddit.vo.MemberVO;

public interface MemberService extends Remote{
	
	public MemberVO login(String mem_id, String mem_pw) throws RemoteException;
	
	public List<MemberVO> getAllMember() throws RemoteException;
	                                              
	public List<MemberVO> getSearchMember(MemberVO mv) throws RemoteException;
	
	public List<MemDogVO> getDogList(int mem_no) throws RemoteException;
	
	public int updateMember(MemberVO mv) throws RemoteException;
	
	public int deleteMember(String mem_id) throws RemoteException;

	public List<MemberVO> getgrade(int mem_point) throws RemoteException;
 
	public int updategrade(MemberVO mem_point) throws RemoteException;
	
	public List<MemberVO> getAllMemberAll() throws RemoteException; //전체회원조회
}
