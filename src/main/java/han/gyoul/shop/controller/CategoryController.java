package han.gyoul.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import han.gyoul.shop.category.dto.CategoryDTO;
import han.gyoul.shop.category.service.CategoryService;

@Controller
@RequestMapping(value=" /category/*")
@SessionAttributes({"memberDTO","itemList"})
public class CategoryController {
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Inject
	private CategoryService categoryService;

	// 대분류, 소분류 등록
	@RequestMapping(value = "/categoryInsert", method = RequestMethod.POST)
	public String categoryInsert(CategoryDTO categoryDTO) throws Exception {
		logger.info("등록");

		categoryService.categoryInsert(categoryDTO);

		return "redirect:/category/list";//url

	}

	// 분류관리 목록
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws Exception {
		logger.info("분류관리목록");

		categoryService.getCategoryList();
		model.addAttribute("list", categoryService.getCategoryList());

		return "category/category_list"; // 반환되는 jsp 뷰 파일
	}
	
	// 소분류목록
			@RequestMapping(value = "/subclassList", method = RequestMethod.GET)
			public @ResponseBody List<CategoryDTO> subclassList(@RequestParam int category_no) throws Exception {
				// @ResponseBody, @RequestParam 어노테이션으로 jsp 값을 받아오고 map에 담아준 후에 결과 반환.
				Map<String, Object> map = new HashMap<>();
				map.get(category_no);
				logger.info("소분류목록" + category_no);
				List<CategoryDTO> result = categoryService.getSubclassList(category_no);
				logger.info("결과" + result);
				return result;

			}


	// 대분류번호 중복확인
	@RequestMapping(value = "/category_noCheck", method = RequestMethod.POST)
	public @ResponseBody String category_noCheck(@RequestParam int category_no) throws Exception {
		// @ResponseBody, @RequestParam 어노테이션으로 jsp 값을 받아오고 map에 담아준 후에 결과 반환.
		Map<String, Object> map = new HashMap<>();
		map.get(category_no);
		logger.info("대분류중복확인" + category_no);
		int result = categoryService.category_noCheck(category_no);
		logger.info("결과" + result);
		return "" + result;

	}

	// 소분류번호 중복확인
	@RequestMapping(value = "/subclass_noCheck", method = RequestMethod.POST)
	public @ResponseBody String subclass_noCheck(@RequestParam int category_no, @RequestParam  int subclass_no) throws Exception {
		// @ResponseBody, @RequestParam 어노테이션으로 jsp 값을 받아오고 map에 담아준 후에 결과 반환.
		logger.info("소분류중복확인" + category_no + "/" + subclass_no);
		Map<String, Object> map = new HashMap<>();
		map.get(category_no);
		map.get(subclass_no);
		logger.info("소분류중복확인" + category_no + "/" + subclass_no);
		int result = categoryService.subclass_noCheck(category_no, subclass_no);
		logger.info("결과" + result);
		return "" + result;

	}
	
	// 대분류 수정
	@RequestMapping(value = "/categoryModify", method = RequestMethod.POST)
	public String categoryModify(CategoryDTO categoryDTO, @RequestParam("origin_category_no") int origin_category_no, @RequestParam("origin_category_name") String origin_category_name) throws Exception{
		logger.info("대분류 수정");
		System.out.println("category_no = " + categoryDTO.getCategory_no());
		System.out.println("origin_category_no = " + origin_category_no);
		categoryService.categoryModify(categoryDTO, origin_category_no, origin_category_name);
		return "redirect:/category/list";
		
	}
	
	// 소분류 수정
		@RequestMapping(value = "/subclassModify", method = RequestMethod.POST)
		public String subclassModify(CategoryDTO categoryDTO, @RequestParam("origin_category_no") int origin_category_no, @RequestParam("origin_subclass_no") int origin_subclass_no) throws Exception{
			logger.info("소분류 수정");
			System.out.println("category_no = " + categoryDTO.getCategory_no());
			System.out.println("subclass_no = " + categoryDTO.getSubclass_no());
			System.out.println("category_name = " + categoryDTO.getCategory_name());
			System.out.println("origin_category_no = " + origin_category_no);
			System.out.println("origin_subclass_no = " + origin_subclass_no);
			categoryService.subclassModify(categoryDTO, origin_category_no, origin_subclass_no);
			return "redirect:/category/list";
			
		}
		
	// 대분류 삭제
	// ajax post방식으로 번호를 받아온 후 삭제.
		@RequestMapping(value = "/categoryDelete", method = RequestMethod.POST)
		public String categoryDelete(int category_no) throws Exception{
			logger.info("대분류 삭제");
			categoryService.categoryDelete(category_no);
			return "redirect:/category/list";
			
		}
		
	// 소분류
	// ajax post방식으로 번호를 받아온 후 삭제.
		@RequestMapping(value = "/subclassDelete", method = RequestMethod.POST)
		public String subClassDelete(int category_no,int subclass_no) throws Exception{
			logger.info("소분류 삭제");
			categoryService.subclassDelete(category_no, subclass_no);
			return "redirect:/category/list";
				
			}
}
