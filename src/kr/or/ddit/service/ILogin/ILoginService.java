package kr.or.ddit.service.ILogin;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ComVO;
import kr.or.ddit.vo.MemberVO;

public interface ILoginService extends Remote{
	
	public List<MemberVO> memberLogin(MemberVO mv) throws RemoteException;
	
	public List<MemberVO> idSearch(MemberVO mv) throws RemoteException;
	
	public List<MemberVO> memNoSearch(MemberVO mv) throws RemoteException;
	
	public List<ComVO> comnoSearch(ComVO cv) throws RemoteException;
	
	public List<MemberVO> pwSearch(MemberVO mv) throws RemoteException;
	
	public List<ComVO> isCom(ComVO cv) throws RemoteException;
	
	public List<MemberVO> isMemPwchek(MemberVO mv) throws RemoteException;
	
	

}
