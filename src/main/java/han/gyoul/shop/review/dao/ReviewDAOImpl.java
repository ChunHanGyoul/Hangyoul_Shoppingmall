package han.gyoul.shop.review.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import han.gyoul.shop.review.dto.Criteria;
import han.gyoul.shop.review.dto.ReviewDTO;
import han.gyoul.shop.review.dto.SearchCriteria;

@Repository
public class ReviewDAOImpl implements ReviewDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace = "han.gyoul.shop.mapper.reviewMapper";
	
	@Override
	public void insert(ReviewDTO reviewDTO) throws Exception {
		session.insert(namespace + ".insert", reviewDTO);
	}

	@Override
	public ReviewDTO read(Integer review_no) throws Exception {
		return session.selectOne(namespace + ".read", review_no);
	}

	@Override
	public void update(ReviewDTO reviewDTO) throws Exception {
		session.update(namespace + ".update", reviewDTO);
	}

	@Override
	public void delete(Integer review_no) throws Exception {
		session.delete(namespace + ".delete", review_no);
	}

	@Override
	public List<ReviewDTO> listAll() throws Exception {
		return session.selectList(namespace + ".listAll");
	}

	@Override
	public List<ReviewDTO> listPage(int page) throws Exception {
		// page�� ���� 0�����̸� page�� 1�̴�.
		if (page <= 0) {
			page = 1;
		}
		// �����������ϱ� �������� 2�������̸� ��� 10���� ������ �̻���� �����ؾ��� 11��,12..
		page = (page - 1) * 10;
		return session.selectList(namespace + ".listPage", page);
	}

	@Override
	public List<ReviewDTO> listCriteria(Criteria cri) throws Exception {
		return session.selectList(namespace + ".listCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".countPaging", cri);
	}

	
	@Override
	public List<ReviewDTO> listSearch(SearchCriteria cri, Integer item_no) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cri", cri);
		paramMap.put("item_no", item_no);
		return session.selectList(namespace + ".listSearch", paramMap);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listSearchCount", cri);
	}

	@Override
	public void updateCommentCnt(Integer review_no, int amount) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("review_no", review_no);
		paramMap.put("amount", amount);
		session.update(namespace + ".updateCommentCnt", paramMap);
	}

	@Override
	public void updateViewcnt(Integer review_no) throws Exception {
		session.update(namespace + ".updateViewCnt", review_no);
	}

}
