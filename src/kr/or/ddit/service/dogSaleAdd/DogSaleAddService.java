package kr.or.ddit.service.dogSaleAdd;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.DogSaleAddVO;

//서현작업중
public interface DogSaleAddService extends Remote {

	
	/**
	 * 분양등록
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public int insertDogSaleAdd(DogSaleAddVO vo) throws RemoteException;
	
	/**
	 * 분양 삭제
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public int deleteDogSaleAdd(DogSaleAddVO vo) throws RemoteException; 
	
	/**
	 * 사업자별 분양목록 전체 조회
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public List<DogSaleAddVO> AllSaleAddList(int com_no) throws RemoteException;
	
	/**
	 * 수정
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public int updateDogSaleAdd(DogSaleAddVO vo) throws RemoteException;
}
