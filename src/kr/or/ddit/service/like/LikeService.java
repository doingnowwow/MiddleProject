package kr.or.ddit.service.like;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.LikeVO;

public interface LikeService extends Remote {
	//찜 조회 회원 별
		public List<LikeVO> dlikelist (LikeVO vo) throws RemoteException;
		
		public int deleteDlike (LikeVO vo) throws RemoteException;
		
		public int detailview(LikeVO vo) throws RemoteException;
		
		public int insertLike(LikeVO vo) throws RemoteException;
}
