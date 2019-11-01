package kr.or.ddit.service.qna;

import java.rmi.Remote;

import java.rmi.RemoteException;

import java.util.List;

import kr.or.ddit.vo.QnABoardVO;

public interface QnAService extends Remote{
	// 공지, 분양, 호텔, 쇼핑 각  말머리로 구분
	
	/**
	 * 게시글 전체 목록 조회 (페이지별 구분)
	 */
	public List<QnABoardVO> selectAllQnA(int qa_no) throws RemoteException;
	
	
	/**
	 * 신규 게시물은 ui 표시 -> ??? 오또케하징?
	 */
	
	
	
	/**
	 * 게시물 등록(비밀번호 삽입) -> 쇼핑 : 관리자는 비밀번호 없이 조회 가능 // 분양, 호텔  : 관리자, 사업주는 비번 없이 조회 가능(회원만 못보게)
	 */
	public int insertQnA(QnABoardVO qv) throws RemoteException;
	
	
	/**
	 * 게시물 수정
	 */
	public int updateQnA(QnABoardVO qv) throws RemoteException;
	
	
	/**
	 * 게시물 삭제
	 */
	public int deleteQnA(int qa_no) throws RemoteException;
	
	
	/**
	 * 게시물 검색
	 */
	public List<QnABoardVO> getSearchQnA(QnABoardVO qv) throws RemoteException;
	
	
	/**
	 * 게시물 댓글 등록
	 */
	public int insertQnAComment(QnABoardVO qv) throws RemoteException;
	
	
	/**
	 * 게시물 댓글 수정
	 */
	public int updateQnAComment(QnABoardVO qv) throws RemoteException;
	
	
	/**
	 * 게시물 댓글 삭제
	 */
	public int deleteQnAComment(String qa_re) throws RemoteException;
	


}
