package han.gyoul.shop.member.dao;

import java.util.List;

import han.gyoul.shop.member.dto.MemberDTO;

public interface AdminDAO {
	
	public List<MemberDTO> memberList()throws Exception; // 회원 리스트 
	public int Mediate(MemberDTO memberDTO)throws Exception; // 회원 등급변경
}
