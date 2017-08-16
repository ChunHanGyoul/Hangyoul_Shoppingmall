package han.gyoul.shop.review.dao;

import java.util.List;

import han.gyoul.shop.review.dto.Criteria;
import han.gyoul.shop.review.dto.ReviewDTO;
import han.gyoul.shop.review.dto.SearchCriteria;

public interface ReviewDAO {
	// �Խñ� ���
	public void insert(ReviewDTO reviewDTO) throws Exception;
	
	public ReviewDTO read(Integer review_no) throws Exception;
	
	public void update(ReviewDTO reviewDTO) throws Exception;
	
	public void delete(Integer review_no) throws Exception;
	
	public List<ReviewDTO> listAll() throws Exception;
	
	public List<ReviewDTO> listPage(int page) throws Exception;
	
	// ����Ʈ ���������� ���
	public List<ReviewDTO> listCriteria(Criteria cri) throws Exception;
	
	// totalCount ��ȯ
	public int countPaging(Criteria cri) throws Exception;
	
	// ��ǰ��ȣ�� ���� ��ǰ�ı� ����Ʈ �ҷ�����
	public List<ReviewDTO> listSearch(SearchCriteria cri, Integer item_no) throws Exception;
	
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	// ����� �߰� or �����Ǿ��� ������ ��� ī��Ʈ�� ������.
	public void updateCommentCnt(Integer review_no, int amount) throws Exception;
	
	// �Խñ� ��ȸ��
	public void updateViewcnt(Integer review_no) throws Exception;
}
