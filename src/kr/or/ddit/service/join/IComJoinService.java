package kr.or.ddit.service.join;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ComVO;

public interface IComJoinService extends Remote{

	// 아이디 중복 체크
	public List<ComVO> comSelectId(ComVO cv) throws RemoteException ;
	
	// 이메일 주소 중복 체크
	public List<ComVO> comSelectEmail(ComVO cv) throws RemoteException;
	
	// 사업자 회원 추가
	public int insertCom(ComVO cv) throws RemoteException;
}
