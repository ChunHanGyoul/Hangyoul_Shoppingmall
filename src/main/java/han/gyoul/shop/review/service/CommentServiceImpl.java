package han.gyoul.shop.review.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import han.gyoul.shop.review.dao.CommentDAO;
import han.gyoul.shop.review.dao.ReviewDAO;
import han.gyoul.shop.review.dto.CommentDTO;
import han.gyoul.shop.review.dto.Criteria;

@Service
public class CommentServiceImpl implements CommentService {

	@Inject
	private CommentDAO commentDAO;
	
	@Inject
	private ReviewDAO reviewDAO;
	
	@Transactional
	@Override
	public void addComment(CommentDTO commentDTO) throws Exception {
		commentDAO.insert(commentDTO);
		reviewDAO.updateCommentCnt(commentDTO.getReview_no(), 1);
	}

	@Override
	public List<CommentDTO> listComment(Integer review_no) throws Exception {
		return commentDAO.list(review_no);
	}

	@Override
	public void modifyComment(CommentDTO commentDTO) throws Exception {
		commentDAO.update(commentDTO);
	}
	
	@Transactional
	@Override
	public void removeComment(Integer comment_no) throws Exception {
		
		int review_no = commentDAO.getReview_no(comment_no);
		commentDAO.delete(comment_no);
		reviewDAO.updateCommentCnt(review_no, -1);
		
	}

	@Override
	public List<CommentDTO> listCommentPage(Integer review_no, Criteria cri) throws Exception {
		return commentDAO.listPage(review_no, cri);
	}

	@Override
	public int count(Integer review_no) throws Exception {
		return commentDAO.count(review_no);
	}

}
