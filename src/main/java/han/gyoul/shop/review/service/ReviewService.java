package han.gyoul.shop.review.service;

import java.util.List;

import han.gyoul.shop.review.dto.Criteria;
import han.gyoul.shop.review.dto.ReviewDTO;
import han.gyoul.shop.review.dto.SearchCriteria;

public interface ReviewService {
	// 후기 게시글 등록 서비스
	public void add(ReviewDTO reviewDTO) throws Exception;
	
	// 후기 게시글 읽기 서비스
	public ReviewDTO read(Integer review_no) throws Exception;
	
	// 후기 게시글 수정 서비스
	public void  modify(ReviewDTO reviewDTO) throws Exception;
	
	// 후기 게시글 삭제 서비스
	public void remove(Integer review_no) throws Exception;
	
	public List<ReviewDTO> listAll() throws Exception;
	
	// 후기 게시글 각 페이지 수 + 페이지마다 게시글 수  서비스
	public List<ReviewDTO> listCriteria(Criteria cri) throws Exception;
	
	// 후기 게시글 전체 총 개수 서비스
	public int listCountCriteria(Criteria cri) throws Exception;
	
	public List<ReviewDTO> listSearchCriteria(SearchCriteria cri, Integer item_no) throws Exception;
	
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	
}
