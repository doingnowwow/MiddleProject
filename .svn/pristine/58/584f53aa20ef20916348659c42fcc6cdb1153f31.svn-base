package kr.or.ddit.service.shopReview;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.ShopReviewVO;

/**
 * 
 * 쇼핑몰 리뷰 생성, 조회, 수정, 삭제
 * 
 * @author hanseon
 *
 */
public interface ShopReviewService extends Remote{

	public List<ShopReviewVO> ShopReviewAll() throws RemoteException; // 사용자가 보는거

	public List<ShopReviewVO> selectMyShopReview(ShopReviewVO mv) throws RemoteException;// 내가 쓴 리뷰 조회

	public List<ShopReviewVO> selectMyReviewDate(ShopReviewVO mv) throws RemoteException; // 날짜로 조회
	
	public int insertShopReveiw(ShopReviewVO vo) throws RemoteException; // 리뷰생성
	public int insertShopReveiw2(ShopReviewVO vo) throws RemoteException; // 리뷰생성

	public int deleteShopReview(ShopReviewVO vo) throws RemoteException; // 리뷰삭제

	public int updateShopReview(ShopReviewVO vo)throws RemoteException ; // 리뷰수정

	
	
	/**
	 * 상품별 리뷰조회
	 * @param prod_id
	 * @return
	 * @throws RemoteException
	 */
	public List<ShopReviewVO> selectProdReview(ShopReviewVO prod_id) throws RemoteException;
	
}
