package han.gyoul.shop.review.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import han.gyoul.shop.review.dto.CommentDTO;
import han.gyoul.shop.review.dto.Criteria;
@Repository
public class CommentDAOImpl implements CommentDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "han.gyoul.shop.mapper.CommentMapper";
	
	@Override
	public List<CommentDTO> list(Integer review_no) throws Exception {
		return session.selectList(namespace + ".list", review_no);
	}

	@Override
	public void insert(CommentDTO commentDTO) throws Exception {
		session.insert(namespace + ".insert", commentDTO);
	}

	@Override
	public void update(CommentDTO commentDTO) throws Exception {
		session.update(namespace + ".update", commentDTO);
	}

	@Override
	public void delete(Integer comment_no) throws Exception {
		session.delete(namespace + ".delete", comment_no);
	}

	@Override
	public List<CommentDTO> listPage(Integer review_no, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("review_no", review_no);
		paramMap.put("cri", cri);
		return session.selectList(namespace + ".listPage", paramMap);
	}

	@Override
	public int count(Integer review_no) throws Exception {
		return session.selectOne(namespace + ".count", review_no);
	}

	@Override
	public int getReview_no(Integer comment_no) throws Exception {
		return session.selectOne(namespace + ".getReview_no", comment_no);
	}

}
