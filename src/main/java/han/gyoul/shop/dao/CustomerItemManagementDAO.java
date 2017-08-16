package han.gyoul.shop.dao;

import java.util.List;

import han.gyoul.shop.dto.Criteria;
import han.gyoul.shop.dto.ItemManagementDTO;
import han.gyoul.shop.dto.SearchCriteria;

public interface CustomerItemManagementDAO {
	
	//1. 상품 등록 페이지
	//public void registerItem(ItemManagementDTO itemManagementDTO) throws Exception;
	//2. 상품 상세 보기 페이지
	//public ItemManagementDTO viewItemDetail(Integer item_no) throws Exception;
	//3. 상품 수정 페이지
	//public void modifyItem(ItemManagementDTO itemManagementDTO) throws Exception;
	//4. 상품 삭제 페이지
	//public void deleteItem(Integer item_no) throws Exception;
	//5. 상품 목록 페이지
	//public List<ItemManagementDTO> listAll() throws Exception;
	//6. 페이징 처리 -> Mapper 수정
	public List<ItemManagementDTO> listPage(int page) throws Exception;
	//7. 리스트를 출력할 부분 추가: 페이징 처리에서
	public List<ItemManagementDTO> listCriteria(Criteria cri) throws Exception;
	//8. 리스트를 출력할 부분 추가: 페이징 처리에서
	public int countPaging(Criteria cri) throws Exception;
	//9. use for dynamic sql: 동적 SQL문을 사용하기 위해 추가
	public List<ItemManagementDTO> listSearch(SearchCriteria cri)throws Exception;
	  //SearchCriteria 네 개의 데이터 유지: page, perPageNum, searchType, keyword
	public int listSearchCount(SearchCriteria cri)throws Exception;
	//10. 상품 상세 보기 페이지(고객)
		public ItemManagementDTO customerViewItemDetail(Integer item_no) throws Exception;
    //11 상품 목록 페이지(고객)
		public List<ItemManagementDTO> customerListAll() throws Exception;

}
