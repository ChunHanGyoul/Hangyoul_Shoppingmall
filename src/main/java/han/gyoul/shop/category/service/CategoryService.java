package han.gyoul.shop.category.service;

import java.util.List;
import java.util.Map;

import han.gyoul.shop.category.dto.CategoryDTO;

public interface CategoryService {
	
	// 소분류목록
	public List<CategoryDTO> getSubclassList(int category_no) throws Exception;
	
	// 분류관리목록
	public List<CategoryDTO> getCategoryList() throws Exception;
	
	// 대분류번호 중복체크
	public int category_noCheck(int category_no) throws Exception;
	
	// 소분류번호 중복체크
	public int subclass_noCheck(int category_no, int subclass_no) throws Exception;
	
	// 분류 등록(대분류,소분류)
	public boolean categoryInsert(CategoryDTO categoryDTO) throws Exception;
	
	// 소분류 수정
	public boolean subclassModify(CategoryDTO categoryDTO, int origin_category_no, int origin_subclass_no) throws Exception;
	
	// 대분류 수정
	public boolean categoryModify(CategoryDTO categoryDTO, int origin_category_no, String origin_category_name) throws Exception;
	
	// 대분류 삭제
	public boolean categoryDelete(int category_no) throws Exception;
	
	// 소분류 삭제
	public boolean subclassDelete(int category_no, int subclass_no) throws Exception;

	



	
}
