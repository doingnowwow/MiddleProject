package kr.or.ddit.service.prod;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ProdVO;

public interface ProdService extends Remote {
	
	
	/**
	 *  메인페이지 검색에 사용하는 메서드
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	
	 public List<ProdVO> getSearchProdList(ProdVO vo) throws RemoteException;	
	
	/**
	 * 물품 전체 정보 반환
	 * @return
	 * @throws RemoteException
	 */
	
	public List<ProdVO> getAllProd() throws RemoteException;
	
	/**
	 * 물건정보 등록 메서드
	 */
	public int insertProd(ProdVO vo) throws RemoteException;
	
	/**
	 * 물건 검색 메서드
	 */
	public List<ProdVO> SearchProd(ProdVO vo) throws RemoteException;
	
	/**
	 * 물건 정보 수정 메서드
	 */
	public int updateProd(ProdVO vo) throws RemoteException;
	
	/**
	 * 물건정보 삭제 메서드 (ID로 삭제)
	 */
	public int deleteProd(ProdVO vo)  throws RemoteException;
	
	/**
	 * 선택한 상품정보 가져오기
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public List<ProdVO> getSelectedProd(ProdVO vo) throws RemoteException;
	

}
