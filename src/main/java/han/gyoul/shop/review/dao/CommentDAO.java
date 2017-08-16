package han.gyoul.shop.review.dao;

import java.util.List;

import han.gyoul.shop.review.dto.CommentDTO;
import han.gyoul.shop.review.dto.Criteria;

public interface CommentDAO {
	public List<CommentDTO> list(Integer review_no) throws Exception;
	
	public void insert(CommentDTO commentDTO) throws Exception;
	
	public void update(CommentDTO commentDTO) throws Exception;
	
	public void delete(Integer comment_no) throws Exception;
	
	public List<CommentDTO> listPage(Integer review_no, Criteria cri) throws Exception;
	
	public int count(Integer reveiw_no) throws Exception;
	
	// 댓글카운트 - 해당댓글이 삭제됐을 때의 게시글 번호를 구한다.
	public int getReview_no(Integer comment_no) throws Exception;
}
