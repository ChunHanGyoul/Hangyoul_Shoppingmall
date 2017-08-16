package han.gyoul.shop.member.service;

import han.gyoul.shop.member.dto.MemberDTO;

public interface MemberService {
	
	public void updateMember(MemberDTO memberDTO)throws Exception; // 회원 정보 수정
	public int removeMember(int mb_no)throws Exception; // 회원 탈퇴
	public MemberDTO memberRead(MemberDTO memberDTO)throws Exception; // 회원정보 가져오기
	public String idUnique(String mb_id)throws Exception;//아이디 중복체크 
	public String nickNameUnique(String mb_nickname)throws Exception;//닉네임 중복체크
	public void sendEmail(String string, String authNum);
	public String RandomNum();
}
