package han.gyoul.shop.controller;

import javax.inject.Inject;

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

import han.gyoul.shop.dto.ItemManagementDTO;
import han.gyoul.shop.dto.PageMaker;
import han.gyoul.shop.dto.SearchCriteria;
import han.gyoul.shop.review.dto.ReviewDTO;
import han.gyoul.shop.review.service.ReviewService;
import han.gyoul.shop.service.CustomerItemManagementService;
import han.gyoul.shop.service.ItemManagementService;

@Controller
@SessionAttributes({"memberDTO","itemList"})
@RequestMapping("/customer_item/*") // /WEB-INF/views/customer_item �뤃�뜑 �쓽誘�
public class SearchCustomerItemManagementController {

	@Inject
	private ItemManagementService service;
	@Inject
	private CustomerItemManagementService customerService;
	@Inject
	private ReviewService reviewService;

	// 1-1. �븳寃곗쿂�읆 �눥�븨紐� �긽�뭹愿�由�-�긽�뭹 議고쉶 - 由ъ뒪�듃[�럹�씠吏� 泥섎━ �룷�븿](怨좉컼)
//	@RequestMapping(value = "/customer_itemManagement_list", method = RequestMethod.GET)
//	@RequestMapping(value = "/mainPage", method = RequestMethod.GET)
//	public void customerListPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
//
//		logger.info(cri.toString());
//
//		model.addAttribute("list", service.listCriteria(cri));
//		model.addAttribute("list", service.listSearchCriteria(cri));
//		// �럹�씠吏� 泥섎━ 遺�遺�
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setCri(cri);
//		// pageMaker.setTotalCount(15);
//
//		pageMaker.setTotalCount(service.listCountCriteria(cri));
//
//		model.addAttribute("pageMaker", pageMaker);
//	}
	
	@RequestMapping(value = "/customer_itemManagement_viewItemDetailPage", method = RequestMethod.GET)
	public void customerRead(@RequestParam("item_no") int item_no, @ModelAttribute("cri") han.gyoul.shop.review.dto.SearchCriteria cri,
			Model model) throws Exception {
		// 상품상세보기 후
		model.addAttribute(customerService.customerViewDetail(item_no));
		
		// 각 상품번호의 후기 리스트를 불러오는 서비스
		model.addAttribute("reviewList", reviewService.listSearchCriteria(cri, item_no));
		han.gyoul.shop.review.dto.PageMaker pageMaker = new han.gyoul.shop.review.dto.PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(reviewService.listSearchCount(cri));

		model.addAttribute("pageMaker", pageMaker);
	}

}
