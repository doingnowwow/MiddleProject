package kr.or.ddit.service.hotelInfoUpdate;

import java.rmi.Remote;

import kr.or.ddit.vo.ComVO;
import kr.or.ddit.vo.HotelVO;

/**
 * 호텔 정보수정을 해 만듬
 * @author hanseon
 *
 */
public interface HotelInfoUpdateService extends Remote {

	//사업자의 마이페이지로 들어가기위한 비빌번호 입력
	public ComVO getCom_pw(ComVO cvo); //비밀번호 가져오기

	// 사업자의 마이페이지로 들어온 후
	public HotelVO getHotel_no(HotelVO hvo); //호텔번호를 가져옴
	public int updateHotel(HotelVO hvo); //호텔정보수정
	public int deleteHotel(HotelVO hvo);
	public int updateHotelPhoto(HotelVO hvo); //호텔사진수정
}
