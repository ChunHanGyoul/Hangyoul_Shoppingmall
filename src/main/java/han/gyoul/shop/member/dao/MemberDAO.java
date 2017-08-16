package han.gyoul.shop.member.dao;

import han.gyoul.shop.member.dto.MemberDTO;


public interface MemberDAO {
	public void insertMember(MemberDTO memberDTO)throws Exception; //회원 가입
	public MemberDTO memberLogin(MemberDTO memberDTO)throws Exception; // 회원 로그인
	public MemberDTO memberRead(MemberDTO memberDTO)throws Exception; // 회원 정보가져오기
	public void updateMember(MemberDTO memberDTO)throws Exception; // 회원 정보 수정
	public int removeMember(int mb_no)throws Exception; // 회원 삭제
	public String idUnique(String mb_id)throws Exception;//아이디 중복체크 
	public String nickNameUnique(String mb_nickname)throws Exception;//닉네임 중복체크
}
