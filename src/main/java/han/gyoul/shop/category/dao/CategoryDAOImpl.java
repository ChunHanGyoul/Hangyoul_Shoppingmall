package han.gyoul.shop.category.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import han.gyoul.shop.category.dto.CategoryDTO;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace = "han.gyoul.shop.mappers.categoryMapper";
	
	// 소분류목록(아이템등록용)
	@Override
	public List<CategoryDTO> getSubclassList(int category_no) throws Exception {
		System.out.println("getSubclassList DAOImple");
		System.out.println(category_no);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category_no", category_no);
		//map에 담아서 mapper로 돌리고 sublist에 담아줌
		List<CategoryDTO> sublist = session.selectList(namespace+".getSubclassList",map);
		
		return sublist;
	}
	
	
	@Override
	public List<CategoryDTO> getCategoryList() throws Exception {
		List<CategoryDTO> list = session.selectList(namespace+".getCategoryList");
		return list;
	}

	@Override
	public int category_noCheck(int category_no) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("category_no", category_no);
		List<CategoryDTO> searchCategoryDTO = session.selectList(namespace+".category_noCheck",map);
		if(searchCategoryDTO.size() != 0){
			return 0;
		}
		return 1;
	}

	@Override
	public int subclass_noCheck(int category_no, int subclass_no) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("category_no", category_no);
		map.put("subclass_no", subclass_no);
		CategoryDTO searchCategoryDTO = session.selectOne(namespace+".subclass_noCheck",map);
		if(searchCategoryDTO != null) {
			return 0;
		}
		return 1;
	}

	@Override
	public boolean categoryInsert(CategoryDTO categoryDTO) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("category_no", categoryDTO.getCategory_no());
		map.put("subclass_no", categoryDTO.getSubclass_no());
		map.put("category_name", categoryDTO.getCategory_name());
//		System.out.println(categoryDTO.getCategory_no());
//		System.out.println(categoryDTO.getSubclass_no());
		System.out.println(categoryDTO);
		int result = session.insert(namespace+".categoryInsert", map);
		if(result == 0){
			return false;
		}
		return true;
	}

	@Override
	public boolean subclassModify(CategoryDTO categoryDTO, int origin_category_no, int origin_subclass_no) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("category_no", categoryDTO.getCategory_no());
		map.put("subclass_no", categoryDTO.getSubclass_no());
		map.put("category_name", categoryDTO.getCategory_name());
		map.put("origin_category_no", origin_category_no);
		map.put("origin_subclass_no", origin_subclass_no);
		session.update(namespace+".subclassModify", map);
		return true;
	}

	@Override
	public boolean categoryModify(CategoryDTO categoryDTO, int origin_category_no, String origin_category_name) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("category_no", categoryDTO.getCategory_no());
		map.put("category_name", categoryDTO.getCategory_name());
		map.put("origin_category_no", origin_category_no);
		map.put("origin_category_name", origin_category_name);
		System.out.println("대분류수정: origin_category_name ="+origin_category_name);
		session.update(namespace+".categoryModify", map);
		return true;
	}

	@Override
	public boolean categoryDelete(int category_no) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("category_no", category_no);
		session.delete(namespace+".categoryDelete",map);
		return true;
	}

	@Override
	public boolean subclassDelete(int category_no, int subclass_no) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("category_no", category_no);
		map.put("subclass_no", subclass_no);
		session.delete(namespace+".subclassDelete", map);
		return true;
	}



}
