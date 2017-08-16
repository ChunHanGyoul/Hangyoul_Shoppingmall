package han.gyoul.shop.review.service;

import java.util.List;

import han.gyoul.shop.review.dto.Criteria;
import han.gyoul.shop.review.dto.ReviewDTO;
import han.gyoul.shop.review.dto.SearchCriteria;

public interface ReviewService {
	// �ı� �Խñ� ��� ����
	public void add(ReviewDTO reviewDTO) throws Exception;
	
	// �ı� �Խñ� �б� ����
	public ReviewDTO read(Integer review_no) throws Exception;
	
	// �ı� �Խñ� ���� ����
	public void  modify(ReviewDTO reviewDTO) throws Exception;
	
	// �ı� �Խñ� ���� ����
	public void remove(Integer review_no) throws Exception;
	
	public List<ReviewDTO> listAll() throws Exception;
	
	// �ı� �Խñ� �� ������ �� + ���������� �Խñ� ��  ����
	public List<ReviewDTO> listCriteria(Criteria cri) throws Exception;
	
	// �ı� �Խñ� ��ü �� ���� ����
	public int listCountCriteria(Criteria cri) throws Exception;
	
	public List<ReviewDTO> listSearchCriteria(SearchCriteria cri, Integer item_no) throws Exception;
	
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	
}
