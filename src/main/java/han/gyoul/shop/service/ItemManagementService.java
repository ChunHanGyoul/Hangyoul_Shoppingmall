package han.gyoul.shop.service;

import java.util.List;

import han.gyoul.shop.dto.Criteria;
import han.gyoul.shop.dto.ItemManagementDTO;
import han.gyoul.shop.dto.SearchCriteria;



public interface ItemManagementService {
	    //1. 상품 등록 페이지
		public void regist(ItemManagementDTO itemManagementDTO) throws Exception;
		//2. 상품 상세 보기 페이지
		public ItemManagementDTO viewDetail(Integer item_no) throws Exception;
		//3. 상품 수정 페이지
		public void modify(ItemManagementDTO itemManagementDTO) throws Exception;
		//4. 상품 삭제 페이지
		public void delete(Integer item_no) throws Exception;
		//5. 상품 목록 페이지
		public List<ItemManagementDTO> listAll() throws Exception;
		//6. 페이징 처리 페이지
		public List<ItemManagementDTO> listCriteria(Criteria cri) throws Exception;
		//7. 페이징 처리 갯수 페이지
		public int listCountCriteria(Criteria cri) throws Exception;
		//8. 동적 SQL문을 위한 서비스 추가
		public List<ItemManagementDTO> listSearchCriteria(SearchCriteria cri) throws Exception;

		public int listSearchCount(SearchCriteria cri) throws Exception;
		//9. 상품 상세 보기 페이지(고객)
		//public ItemManagementDTO customerViewDetail(Integer item_no) throws Exception;
		//10. 상품 목록 페이지
		//public List<ItemManagementDTO> customerListAll() throws Exception;
}
