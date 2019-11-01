package kr.or.ddit.service.cart;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.PointVO;
import kr.or.ddit.vo.ShoppingVO;

public interface CartService  extends Remote{

	
	
	public List<ShoppingVO> ListAll(ShoppingVO sv) throws RemoteException; // 카트에 담겨져있는거 모두 보여주기

	public int insertCart(ShoppingVO sv) throws RemoteException ; // 카트에 상품 추가하기

	public int deleteCart(ShoppingVO sv) throws RemoteException ; // 카트에 상품 삭제하기

	public PointVO getPoint(PointVO pv) throws RemoteException ; // 포인트 불러오기

	
}
