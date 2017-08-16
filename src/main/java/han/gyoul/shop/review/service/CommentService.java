package han.gyoul.shop.review.service;

import java.util.List;

import han.gyoul.shop.review.dto.CommentDTO;
import han.gyoul.shop.review.dto.Criteria;

public interface CommentService {
	
	public void addComment(CommentDTO commentDTO) throws Exception;

	public List<CommentDTO> listComment(Integer review_no) throws Exception;
	
	public void modifyComment(CommentDTO commentDTO) throws Exception;
	
	public void removeComment(Integer comment_no) throws Exception;
	
	public List<CommentDTO> listCommentPage(Integer review_no, Criteria cri) throws Exception;
	
	public int count(Integer review_no) throws Exception;
}
