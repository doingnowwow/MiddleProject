package kr.or.ddit.service.dogSale;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


import kr.or.ddit.vo.DogSaleVO;

public interface DogSaleService extends Remote{
	

	public List<DogSaleVO> getAllDogSale() throws RemoteException;
	
	public int insertDogSale(DogSaleVO vo) throws RemoteException;

	public int updateDogSale(DogSaleVO vo) throws RemoteException;

	public List<DogSaleVO> getSearchDogSale(DogSaleVO vo) throws RemoteException;

	public int deleteDogSale(DogSaleVO vo) throws RemoteException;
	
	public List<DogSaleVO> getDogSaleSearchList(DogSaleVO vo)throws RemoteException;

}


