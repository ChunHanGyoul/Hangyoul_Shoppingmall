package han.gyoul.shop.member.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import han.gyoul.shop.member.dto.MemberDTO;

@Repository
public class AdminDAOlmpl implements AdminDAO{

	@Inject
	private SqlSession sqlSession;
	
//	@Inject
	private static final String namespace="han.gyoul.mapper.AdminMapper";
	
	@Override
	public List<MemberDTO> memberList() throws Exception {
		return sqlSession.selectList(namespace+".memberList");
	}

	@Override
	public int Mediate(MemberDTO memberDTO) throws Exception {
		return sqlSession.update(namespace+".Mediate", memberDTO);
	}

}
