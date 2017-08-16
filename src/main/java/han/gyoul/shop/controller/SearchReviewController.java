package han.gyoul.shop.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import han.gyoul.shop.review.dto.ReviewDTO;
import han.gyoul.shop.review.dto.SearchCriteria;
import han.gyoul.shop.review.service.ReviewService;

@Controller
@SessionAttributes({"memberDTO","itemList"})
@RequestMapping("/sreview/*")
public class SearchReviewController {
	private static final Logger logger = LoggerFactory.getLogger(SearchReviewController.class);

	@Inject
	private ReviewService service;

	// �ı� ��Ϻ��� -- ���� ��ǰ������ ���⿡ ���񽺸� �־���.
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
//
//		logger.info(cri.toString());
//		
//		model.addAttribute("list", service.listSearchCriteria(cri));
//
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setCri(cri);
//
//		pageMaker.setTotalCount(service.listSearchCount(cri));
//
//		model.addAttribute("pageMaker", pageMaker);
//	}

	// �ı� �Խñ� �б�
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("review_no") int review_no, @ModelAttribute("cri") SearchCriteria cri, Model model)
			throws Exception {
		model.addAttribute(service.read(review_no));
	}

	// �ı�� ����
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String remove(@RequestParam("review_no") int review_no, SearchCriteria cri, RedirectAttributes rttr, HttpServletRequest request)
			throws Exception {
		// public String remove(@RequestParam("review_no")int review_no,
		// Criteria cri, RedirectAttributes rttr) throws Exception {
		// System.out.println("������Ʈ�ѷ�->����");
		System.out.println("������ �Խñ� ��ȣ : " + review_no);
		service.remove(review_no);
		// System.out.println("�����Ϸ�");
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());

		rttr.addFlashAttribute("msg", "SUCCESS");

		String item_no = request.getParameter("item_no");
		return "redirect:/customer_item/customer_itemManagement_viewItemDetailPage?item_no="+ item_no;
	}

	// ����������
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(int review_no, @ModelAttribute("cri") SearchCriteria cri, Model model)
			throws Exception {

		model.addAttribute(service.read(review_no));
	}

	// ����������
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagingPOST(ReviewDTO reviewDTO, SearchCriteria cri, RedirectAttributes rttr, HttpServletRequest request) throws Exception {

		logger.info(cri.toString());
		service.modify(reviewDTO);

		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());

		rttr.addFlashAttribute("msg", "SUCCESS");
		String item_no = request.getParameter("item_no");
		return "redirect:/customer_item/customer_itemManagement_viewItemDetailPage?item_no="+ item_no;
	}

	// �ı� �� ���
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registGET() throws Exception {

		logger.info("regist get...");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String regisjtPOST(ReviewDTO reviewDTO, RedirectAttributes rttr, HttpServletRequest request) throws Exception {

		logger.info("regist post...");
		logger.info(reviewDTO.toString());

		service.add(reviewDTO);

		rttr.addFlashAttribute("msg", "SUCCESS");
		String item_no = request.getParameter("item_no");
		return "redirect:/customer_item/customer_itemManagement_viewItemDetailPage?item_no=" + item_no;
	}

}
