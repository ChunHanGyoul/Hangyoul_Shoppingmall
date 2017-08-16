package han.gyoul.shop.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import han.gyoul.shop.dao.CustomerItemManagementDAO;
import han.gyoul.shop.dao.ItemManagementDAO;
import han.gyoul.shop.dto.Criteria;
import han.gyoul.shop.dto.ItemManagementDTO;
import han.gyoul.shop.dto.SearchCriteria;



@Service //스프링의 빈으로 인식되기 위해 어노테이션 적용
public class CustomerItemManagementServiceImpl implements CustomerItemManagementService {

	@Inject
	private ItemManagementDAO itemManagementDAO;
	@Inject
	private CustomerItemManagementDAO customerItemManagementDAO;
	
	//1. 상품 등록
//	@Override
//	public void regist(ItemManagementDTO itemManagementDTO) throws Exception {
//		itemManagementDAO.registerItem(itemManagementDTO);
//	}
    //2. 상품 상세 보기
//	@Override
//	public ItemManagementDTO viewDetail(Integer item_no) throws Exception {
//		return itemManagementDAO.viewItemDetail(item_no);
//	}
    //3. 상품 수정
//	@Override
//	public void modify(ItemManagementDTO itemManagementDTO) throws Exception {
//		itemManagementDAO.modifyItem(itemManagementDTO);
//	}
    //4. 상품 삭제
//	@Override
//	public void delete(Integer item_no) throws Exception {
//		itemManagementDAO.deleteItem(item_no);	
//	}
    //5. 상품 목록
//	@Override
//	public List<ItemManagementDTO> listAll() throws Exception {
//		return itemManagementDAO.listAll();
//	}
	//6. 페이징 처리 - 페이지당 있는 목록을 가져옴
	@Override
	public List<ItemManagementDTO> listCriteria(Criteria cri) throws Exception {
		return itemManagementDAO.listCriteria(cri);
	}
	//7. 페이징 처리 - 페이지 개수 처리
	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return itemManagementDAO.countPaging(cri);
	}
	//8. 동적 SQL문 작성을 위한 서비스 추가
	@Override
	public List<ItemManagementDTO> listSearchCriteria(SearchCriteria cri) throws Exception {
		return itemManagementDAO.listSearch(cri);
	}
	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return itemManagementDAO.listSearchCount(cri);
	}
	 //9. 상품 상세 보기(고객)
	@Override
	public ItemManagementDTO customerViewDetail(Integer item_no) throws Exception {
		System.out.println(item_no);
		return customerItemManagementDAO.customerViewItemDetail(item_no);
	}
	//10. 상품 목록(고객)
	@Override
	public List<ItemManagementDTO> customerListAll() throws Exception {
		return customerItemManagementDAO.customerListAll();
	}
}
