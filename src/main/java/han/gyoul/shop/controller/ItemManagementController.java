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

import han.gyoul.shop.dto.Criteria;
import han.gyoul.shop.dto.ItemManagementDTO;
import han.gyoul.shop.dto.PageMaker;
import han.gyoul.shop.service.ItemManagementService;

@Controller
@SessionAttributes({"memberDTO","itemList"})
@RequestMapping("/itemManagement/*") // /WEB-INF/views/itemManagement 폴더 의미
public class ItemManagementController {

	  private static final Logger logger = LoggerFactory.getLogger(ItemManagementController.class);

	  @Inject
	  private ItemManagementService service;

	  //1-1. 상품 등록 폼
	  @RequestMapping(value = "/itemManagement_register", method = RequestMethod.GET)
	   ///WEB-INF/views/itemManagement/itemManagement_register.jsp 의미
	  public void registerGET(ItemManagementDTO itemManagementDTO, Model model) throws Exception {
	    logger.info("register get ...........");
	  }

//	   @RequestMapping(value = "/itemManagement_register", method = RequestMethod.POST)
//	   public String registPOST(@ModelAttribute ItemManagementDTO itemManagementDTO, Model model) throws Exception {
//	  
//	   logger.info("regist post ...........");
//	   logger.info(itemManagementDTO.toString());
//	   
//	   service.regist(itemManagementDTO);
//	   
//	  
//	   model.addAttribute("result", "success");
//	  
//	   //return "/itemManagement/success";
//	   return "redirect:/itemManagement/itemManagement_listAll";
//	   }
	   
	  //1-2. 상품 등록 처리 폼
	  //RedirectAttributes를 이용한 숨김 데이터를 전송하기 위해 addFlashAttribute()이용
	   @RequestMapping(value = "/itemManagement_register", method = RequestMethod.POST)
	   public String registPOST(@ModelAttribute ItemManagementDTO itemManagementDTO, RedirectAttributes rttr) throws Exception {
		   
		   logger.info("regist post ...........");
		   logger.info(itemManagementDTO.toString());
		   
		   service.regist(itemManagementDTO);
		  
		    rttr.addFlashAttribute("msg", "SUCCESS");
		   //브라우저에서 msg로 추가된 데이터는 보여지지 않음
		   
		   //return "/itemManagement/success";
		  // return "redirect:/itemManagement/itemManagement_listAll";
		   return "redirect:/itemManagement/itemManagement_listPage";
	   }

	   //2. 상품 전체 목록 보기
	  @RequestMapping(value = "/itemManagement_listAll", method = RequestMethod.GET)
	  // /WEB-INF/views/itemManagement/itemManagement_listAll.jsp
	  public void listAll(Model model) throws Exception {
	
	    logger.info("show all list......................");
	    model.addAttribute("list", service.listAll());
	    //listAll()은 스프링 MVC의 Model 타입의 객체를 파라미터로 받음
	    //Model을 이용해서 모든 게시물을 JSP로 전송하는 작업을 model.addAttribute()로 처리하고 있음
	  }
	
	  //3. 상품 상세 보기
	  @RequestMapping(value = "/itemManagement_viewItemDetail", method = RequestMethod.GET)
	  public void read(@RequestParam("item_no") int item_no, Model model) throws Exception {
	    //파라미터는 외부에서 전달될 item_no 값을 전달받음: @RequestParam을 이용해 구성
		//Model객체 사용 이유: 조회된 게시물을 JSP로 전달해야 하기 때문에
	    model.addAttribute(service.viewDetail(item_no));
	  }
	
	  //4. 상품 삭제 처리
	  @RequestMapping(value = "/itemManagement_delete", method = RequestMethod.POST)
	  public String remove(@RequestParam("item_no") int item_no, RedirectAttributes rttr) throws Exception {
	
	    service.delete(item_no);
	
	    rttr.addFlashAttribute("msg", "SUCCESS");
//	    return "redirect:/itemManagement/itemManagement_listAll";
	    return "redirect:/itemManagement/itemManagement_listPage";
	  }
	
	  //5-1. 상품 수정 처리 폼: 조회 페이지로 이동-> 원래의 게시물 데이터를 읽어와서 Model에 넣어 전달
	  @RequestMapping(value = "/itemManagement_modify", method = RequestMethod.GET)
	  public void modifyGET(int item_no, Model model) throws Exception {
	
	    model.addAttribute(service.viewDetail(item_no));
	  }
	
	  //5-2. 상품 수정 처리 화면: 실제 수정 작업 처리 -> 리턴 타입은 등록이나 삭제와 동일하게 처리함
	  @RequestMapping(value = "/itemManagement_modify", method = RequestMethod.POST)
	  public String modifyPOST(ItemManagementDTO itemManagementDTO, RedirectAttributes rttr) throws Exception {
	
	    logger.info("mod post............");
	
	    service.modify(itemManagementDTO);
	    rttr.addFlashAttribute("msg", "SUCCESS");
	
//	    return "redirect:/itemManagement/itemManagement_listAll";
	    return "redirect:/itemManagement/itemManagement_listPage";
	  }
	
	  //6-1. 페이징 처리1: 각 목록마다 불러옴()하단의 1,2,....10표시
	  @RequestMapping(value = "/itemManagement_listCri", method = RequestMethod.GET)
	  public void listAll(Criteria cri, Model model) throws Exception {
	
	    logger.info("show list Page with Criteria......................");
	
	    model.addAttribute("list", service.listCriteria(cri));
	  }
	
	  //6-2. 페이징 처리2: 화면의 페이징 처리 - 데이터 개수만큼 10개씩 처리
	  //추가된 listPage()는 Criteria cri를 파라미터로 사용하고, model 객체를 이용해서 발생하는 PageMake를 저장함
	  //itemManagement_listPage.jsp에서 페이징 처리에 필요한 모든 데이터가 담긴 PageMaker가 보여짐
	  @RequestMapping(value = "/itemManagement_listPage", method = RequestMethod.GET)
	  public void listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
	
	    logger.info(cri.toString());
	
	    model.addAttribute("list", service.listCriteria(cri));
	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);
	    //pageMaker.setTotalCount(15);
	
	    pageMaker.setTotalCount(service.listCountCriteria(cri));
	
	    model.addAttribute("pageMaker", pageMaker);
	  }
	
	  //6-3. 페이징처리3: 조회 페이지 처리 -> 사용자가 조회 페이지에서 다시 '목록 보기'를 이용해서 기존에 자신이 보던 목록 페이지로 전환되어야 함
	  //그러기 위해서 특정 상품 번호만을 전달해줘야 함: item_no를 파라미터로 받을 수 있어야 함
	  @RequestMapping(value = "/itemManagement_viewItemDetailPage", method = RequestMethod.GET)
	  public void read(@RequestParam("item_no") int item_no, @ModelAttribute("cri") 
	  Criteria cri, Model model) throws Exception {
	    //page와 perPageNum 파라미터의 경우: Criteria 타입의 객체로 처리함
	    model.addAttribute(service.viewDetail(item_no));
	  }
	
	  //6-4. 페이징처리4: 수정 페이지와 삭제 페이지 처리
	  //6-4-1. 삭제 페이지 처리: 조회 페이지에서 삭제 -> ItemManagementController -> 목록 페이지
	  @RequestMapping(value = "/itemManagement_deletePage", method = RequestMethod.POST)
	  public String remove(@RequestParam("item_no") int item_no, Criteria cri, 
			  RedirectAttributes rttr) throws Exception {
	
	    service.delete(item_no);
	
	    rttr.addAttribute("page", cri.getPage());
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addFlashAttribute("msg", "SUCCESS");
	    //삭제 결과는 임시로 사용하는 데이터이므로 addFlashAttribute()를 이용해서 처리함
	
	    return "redirect:/itemManagement/itemManagement_listPage";
	  }
	 //6-4-2. 수정 페이지 처리: 조회 페이지-> ItemManagementController -> 수정 페이지 -> ItemManagementController -> 조회 페이지
	  @RequestMapping(value = "/itemManagement_modifyPage", method = RequestMethod.GET)
	  //수정 작업에 유지해야 하는 정보가 포함되어야 하므로 itemManagement_modifyPage.jsp 작성
	  public void modifyPagingGET(@RequestParam("item_no") int item_no, @ModelAttribute("cri") 
	  Criteria cri, Model model)
	      throws Exception {
	
	    model.addAttribute(service.viewDetail(item_no));
	  }
	  //6-4-3. 수정 페이지 처리: 조회 페이지-> ItemManagementController -> 수정 페이지 -> ItemManagementController -> 조회 페이지
	  @RequestMapping(value = "/itemManagement_modifyPage", method = RequestMethod.POST)
	  //수정 작업에 유지해야 하는 정보가 포함되어야 하므로 itemManagement_modifyPage.jsp 작성
	  public String modifyPagingPOST(ItemManagementDTO itemManagementDTO, 
	  Criteria cri, RedirectAttributes rttr) throws Exception {
		  
		  service.modify(itemManagementDTO);
		  
		  rttr.addAttribute("page", cri.getPage());
		  rttr.addAttribute("perPageNum", cri.getPerPageNum());
		  rttr.addFlashAttribute("msg", "SUCCESS");
		  //수정 작업이 끝나면 삭제와 마찬가지로 다시 목록 페이지로 리다이렉트 되어야 하고, 수정된 결고에 대해서는 'msg'로 전송
		return "redirect:/itemManagement/itemManagement_listPage";
		//수정 작업: 특정 페이지 내에서 특정 게시물 조회하고, 다시 수정 택함
		//수정이 가능한 화면에서는 POST 방식으로 수정을 요청하게 되고 작업 완료 후
		//원래의 정보가 유지된 채로 목록 페이지로 이동
	  }

	}
