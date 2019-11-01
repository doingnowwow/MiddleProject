package kr.or.ddit.service.Human_Mypage_Ranking;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.PointVO;

public interface Human_MypageRankService extends Remote{
	
	
	
	/**
	 * 로그인 사용자 mem_no를 통해 해당 사용자의 PointVO를 가져오는 메서드
	 * @param Rank
	 * @throws RemoteException
	 */
	public List<PointVO> getMemberPoint(PointVO Rank) throws RemoteException;

}
