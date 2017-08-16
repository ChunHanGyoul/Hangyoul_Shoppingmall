package han.gyoul.shop.member.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import han.gyoul.shop.member.dto.MemberDTO;

@Repository
public class MemberDAOlmpl implements MemberDAO{

	@Inject
 	private SqlSession sqlSession; //���옣�냼
	
	private static final String namespace="han.gyoul.mapper.MemberMapper"; //memberMapper
	
	
	@Override
	public void insertMember(MemberDTO memberDTO) throws Exception { //memberRegist
		sqlSession.insert(namespace+".insertMember", memberDTO);
		
	}


	@Override
	public MemberDTO memberLogin(MemberDTO memberDTO) throws Exception {
		return sqlSession.selectOne(namespace+".login", memberDTO);
	}


	@Override
	public MemberDTO memberRead(MemberDTO memberDTO) throws Exception {
		return sqlSession.selectOne(namespace+".memberRead", memberDTO);
	}


	@Override
	public void updateMember(MemberDTO memberDTO) throws Exception {
		sqlSession.update(namespace+".updateMember", memberDTO);
		
	}


	@Override
	public int removeMember(int mb_no) throws Exception {
		return sqlSession.delete(namespace+".deleteMember", mb_no);
	}


	@Override
	public String idUnique(String mb_id) throws Exception {
		String id = sqlSession.selectOne(namespace+".idUnique", mb_id);
		if(id == null){
			return "2";
		}
			return "-2";	
	}
	
	@Override
	public String nickNameUnique(String mb_nickname) throws Exception {
		String nickname = sqlSession.selectOne(namespace+".nickNameUnique", mb_nickname);
		if(nickname == null){
			return "3";
		}
			return "-3";
	}


}
