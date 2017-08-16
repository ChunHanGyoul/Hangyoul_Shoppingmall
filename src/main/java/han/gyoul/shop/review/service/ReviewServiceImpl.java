package han.gyoul.shop.review.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import han.gyoul.shop.review.dao.ReviewDAO;
import han.gyoul.shop.review.dto.Criteria;
import han.gyoul.shop.review.dto.ReviewDTO;
import han.gyoul.shop.review.dto.SearchCriteria;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Inject
	private ReviewDAO reviewDAO;
	
	@Override
	public void add(ReviewDTO reviewDTO) throws Exception {
//		String[] files = reviewDTO.getReview_image();
//		if(files == null) {return;}
//		for (String fileName : files) {
			reviewDAO.insert(reviewDTO);
//		}
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public ReviewDTO read(Integer review_no) throws Exception {
		reviewDAO.updateViewcnt(review_no);
		System.out.println(review_no);
		return reviewDAO.read(review_no);
	}

	@Override
	public void modify(ReviewDTO reviewDTO) throws Exception {
		reviewDAO.update(reviewDTO);
	}

	@Override
	public void remove(Integer review_no) throws Exception {
		reviewDAO.delete(review_no);
	}

	@Override
	public List<ReviewDTO> listCriteria(Criteria cri) throws Exception {
		return reviewDAO.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return reviewDAO.countPaging(cri);
	}

	@Override
	public List<ReviewDTO> listSearchCriteria(SearchCriteria cri, Integer item_no) throws Exception {
		return reviewDAO.listSearch(cri, item_no);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return reviewDAO.listSearchCount(cri);
	}

	@Override
	public List<ReviewDTO> listAll() throws Exception {
		return reviewDAO.listAll();
	}

}
