package han.gyoul.shop.review.dao;

import java.util.List;

import han.gyoul.shop.review.dto.Criteria;
import han.gyoul.shop.review.dto.ReviewDTO;
import han.gyoul.shop.review.dto.SearchCriteria;

public interface ReviewDAO {
	// 게시글 등록
	public void insert(ReviewDTO reviewDTO) throws Exception;
	
	public ReviewDTO read(Integer review_no) throws Exception;
	
	public void update(ReviewDTO reviewDTO) throws Exception;
	
	public void delete(Integer review_no) throws Exception;
	
	public List<ReviewDTO> listAll() throws Exception;
	
	public List<ReviewDTO> listPage(int page) throws Exception;
	
	// 리스트 페이지마다 출력
	public List<ReviewDTO> listCriteria(Criteria cri) throws Exception;
	
	// totalCount 반환
	public int countPaging(Criteria cri) throws Exception;
	
	// 상품번호에 따라 상품후기 리스트 불러오기
	public List<ReviewDTO> listSearch(SearchCriteria cri, Integer item_no) throws Exception;
	
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	// 댓글이 추가 or 삭제되었을 때마다 댓글 카운트를 세어줌.
	public void updateCommentCnt(Integer review_no, int amount) throws Exception;
	
	// 게시글 조회수
	public void updateViewcnt(Integer review_no) throws Exception;
}
