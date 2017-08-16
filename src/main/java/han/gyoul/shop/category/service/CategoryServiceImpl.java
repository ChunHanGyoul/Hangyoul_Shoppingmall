package han.gyoul.shop.category.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import han.gyoul.shop.category.dao.CategoryDAO;
import han.gyoul.shop.category.dto.CategoryDTO;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Inject
	private CategoryDAO categoryDAO;
	
	
	@Override
	public List<CategoryDTO> getSubclassList(int category_no) throws Exception {
		return categoryDAO.getSubclassList(category_no);
	}
	
	@Override
	public List<CategoryDTO> getCategoryList() throws Exception {
		return categoryDAO.getCategoryList();
	}

	@Override
	public int category_noCheck(int category_no) throws Exception {
		return categoryDAO.category_noCheck(category_no);
	}

	@Override
	public int subclass_noCheck(int category_no, int subclass_no) throws Exception {
		
		return categoryDAO.subclass_noCheck(category_no, subclass_no);
	}

	@Override
	public boolean categoryInsert(CategoryDTO categoryDTO) throws Exception {
		return categoryDAO.categoryInsert(categoryDTO);
	}

	@Override
	public boolean subclassModify(CategoryDTO categoryDTO, int origin_category_no, int origin_subclass_no) throws Exception {

		return categoryDAO.subclassModify(categoryDTO, origin_category_no, origin_subclass_no);
	}

	@Override
	public boolean categoryModify(CategoryDTO categoryDTO, int origin_category_no, String origin_category_name) throws Exception {

		return categoryDAO.categoryModify(categoryDTO, origin_category_no, origin_category_name);
	}

	@Override
	public boolean categoryDelete(int category_no) throws Exception {
		
		return categoryDAO.categoryDelete(category_no);
	}

	@Override
	public boolean subclassDelete(int categroy_no, int subclass_no) throws Exception {
		
		return categoryDAO.subclassDelete(categroy_no, subclass_no);
	}

}
