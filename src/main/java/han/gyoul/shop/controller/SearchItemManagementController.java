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
import han.gyoul.shop.service.ItemManagementService;
import han.gyoul.shop.category.service.*;



@Controller
@SessionAttributes({"memberDTO","itemList"})
@RequestMapping("/item/*") // /WEB-INF/views/item �뤃�뜑 �쓽誘�
public class SearchItemManagementController {
	
	 private static final Logger logger = LoggerFactory.
			 getLogger(SearchItemManagementController.class);

	  @Inject //�쓽議댁꽦 二쇱엯 �쓽誘�
	  private ItemManagementService service;
	  
	  @Inject //의존성 주입 의미
	  private CategoryService categoryService;
	  
	  //1. �븳寃곗쿂�읆 �눥�븨紐� �긽�뭹愿�由�-�긽�뭹 議고쉶 - 由ъ뒪�듃[�럹�씠吏� 泥섎━ �룷�븿]
	  @RequestMapping(value = "/itemManagement_list", method = RequestMethod.GET)
//	  @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	  public void listPage(@ModelAttribute("cri") SearchCriteria cri, 
			  Model model) throws Exception {
	
	    logger.info(cri.toString());
	
//	    model.addAttribute("list", service.listCriteria(cri));
	    model.addAttribute("list", service.listSearchCriteria(cri));
	    //�럹�씠吏� 泥섎━ 遺�遺�
	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);
	    //pageMaker.setTotalCount(15);
	    
  //    pageMaker.setTotalCount(service.listCountCriteria(cri));
	    pageMaker.setTotalCount(service.listSearchCount(cri));
	
	    model.addAttribute("pageMaker", pageMaker);
	  }
	  
	  //itemManagement_listPage�뒗 湲곗〈�쓽 ItemManagementController瑜� 洹몃�濡� �궗�슜�븿
	  
	  //2. �븳寃곗쿂�읆 �눥�븨紐� �긽�뭹愿�由�-�긽�뭹 議고쉶- �긽�꽭蹂닿린[�럹�씠吏� 泥섎━ �룷�븿] 泥섎━
	  //議고쉶 �럹�씠吏� 泥섎━ -> �궗�슜�옄媛� 議고쉶 �럹�씠吏��뿉�꽌 �떎�떆 '紐⑸줉 蹂닿린'瑜� �씠�슜�빐�꽌 湲곗〈�뿉 �옄�떊�씠 蹂대뜕 紐⑸줉 �럹�씠吏�濡� �쟾�솚�릺�뼱�빞 �븿
	  //洹몃윭湲� �쐞�빐�꽌 �듅�젙 �긽�뭹 踰덊샇留뚯쓣 �쟾�떖�빐以섏빞 �븿: item_no瑜� �뙆�씪誘명꽣濡� 諛쏆쓣 �닔 �엳�뼱�빞 �븿
	  @RequestMapping(value = "/itemManagement_viewItemDetailPage", method = RequestMethod.GET)
	  public void read(@RequestParam("item_no") int item_no, @ModelAttribute("cri") 
	  SearchCriteria cri, Model model) throws Exception {
	    //page�� perPageNum �뙆�씪誘명꽣�쓽 寃쎌슦: Criteria ���엯�쓽 媛앹껜濡� 泥섎━�븿
	    model.addAttribute(service.viewDetail(item_no));
	  }
	  
	  //1-1. �븳寃곗쿂�읆 �눥�븨紐� �긽�뭹愿�由�-�긽�뭹 議고쉶 - 由ъ뒪�듃[�럹�씠吏� 泥섎━ �룷�븿](怨좉컼)
//	  @RequestMapping(value = "/customer_itemManagement_list", method = RequestMethod.GET)
//	  public void customerListPage(@ModelAttribute("cri") SearchCriteria cri, 
//			  Model model) throws Exception {
//		  
//		  logger.info(cri.toString());
		  
//	    model.addAttribute("list", service.listCriteria(cri));
//		  model.addAttribute("list", service.listSearchCriteria(cri));
		  //�럹�씠吏� 泥섎━ 遺�遺�
//		  PageMaker pageMaker = new PageMaker();
//		  pageMaker.setCri(cri);
		  //pageMaker.setTotalCount(15);
		  
	//	  pageMaker.setTotalCount(service.listCountCriteria(cri));
		  
	//	  model.addAttribute("pageMaker", pageMaker);
	//  }
	  
	  //itemManagement_listPage�뒗 湲곗〈�쓽 ItemManagementController瑜� 洹몃�濡� �궗�슜�븿
	  
	  //2-2. �븳寃곗쿂�읆 �눥�븨紐� �긽�뭹愿�由�-�긽�뭹 議고쉶- �긽�꽭蹂닿린[�럹�씠吏� 泥섎━ �룷�븿] 泥섎━(怨좉컼)
	  //議고쉶 �럹�씠吏� 泥섎━ -> �궗�슜�옄媛� 議고쉶 �럹�씠吏��뿉�꽌 �떎�떆 '紐⑸줉 蹂닿린'瑜� �씠�슜�빐�꽌 湲곗〈�뿉 �옄�떊�씠 蹂대뜕 紐⑸줉 �럹�씠吏�濡� �쟾�솚�릺�뼱�빞 �븿
	  //洹몃윭湲� �쐞�빐�꽌 �듅�젙 �긽�뭹 踰덊샇留뚯쓣 �쟾�떖�빐以섏빞 �븿: item_no瑜� �뙆�씪誘명꽣濡� 諛쏆쓣 �닔 �엳�뼱�빞 �븿
	  //@RequestMapping(value = "/customer_itemManagement_viewItemDetailPage", method = RequestMethod.GET)
	  //public void customerRead(@RequestParam("item_no") int item_no, @ModelAttribute("cri") 
	  //SearchCriteria cri, Model model) throws Exception {
		  //page�� perPageNum �뙆�씪誘명꽣�쓽 寃쎌슦: Criteria ���엯�쓽 媛앹껜濡� 泥섎━�븿
		 // model.addAttribute(service.customerViewDetail(item_no));
	//  }
	  
	  //3. �븳寃곗쿂�읆 �눥�븨紐� �긽�뭹愿�由�-�긽�뭹 �궘�젣[�럹�씠吏� 泥섎━ �룷�븿] 泥섎━
	//�궘�젣 �럹�씠吏� 泥섎━: 議고쉶 �럹�씠吏��뿉�꽌 �궘�젣 -> SearchItemManagementController -> 紐⑸줉 �럹�씠吏�
	  @RequestMapping(value = "/itemManagement_deletePage", method = RequestMethod.POST)
	  public String remove(@RequestParam("item_no") int item_no, SearchCriteria cri, 
			  RedirectAttributes rttr) throws Exception {
	
	    service.delete(item_no);
	
	    rttr.addAttribute("page", cri.getPage());
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addAttribute("searchType", cri.getSearchType());
	    rttr.addAttribute("keyword", cri.getKeyword());
	    
	    rttr.addFlashAttribute("msg", "SUCCESS");
	    //�궘�젣 寃곌낵�뒗 �엫�떆濡� �궗�슜�븯�뒗 �뜲�씠�꽣�씠誘�濡� addFlashAttribute()瑜� �씠�슜�빐�꽌 泥섎━�븿
	
	    return "redirect:/item/itemManagement_list";
	  }
	  
	  //4. �븳寃곗쿂�읆 �눥�븨紐� �긽�뭹愿�由�-�긽�뭹 �닔�젙[�럹�씠吏� 泥섎━ �룷�븿] 泥섎━
	  //4-1. �닔�젙 �럹�씠吏� 泥섎━: 議고쉶 �럹�씠吏�-> SearchItemManagementController -> �닔�젙 �럹�씠吏� -> SearchItemManagementController -> 議고쉶 �럹�씠吏�
	  @RequestMapping(value = "/itemManagement_modifyPage", method = RequestMethod.GET)
	  //�닔�젙 �옉�뾽�뿉 �쑀吏��빐�빞 �븯�뒗 �젙蹂닿� �룷�븿�릺�뼱�빞 �븯誘�濡� itemManagement_modifyPage.jsp �옉�꽦
	  public void modifyPagingGET(@RequestParam("item_no") int item_no, @ModelAttribute("cri") 
	  SearchCriteria cri, Model model)
	      throws Exception {
	
	    model.addAttribute(service.viewDetail(item_no));
	  }
	  //4-2. �닔�젙 �럹�씠吏� 泥섎━: 議고쉶 �럹�씠吏�-> SearchItemManagementController -> �닔�젙 �럹�씠吏� -> SearchItemManagementController -> 議고쉶 �럹�씠吏�
	  @RequestMapping(value = "/itemManagement_modifyPage", method = RequestMethod.POST)
	  //�닔�젙 �옉�뾽�뿉 �쑀吏��빐�빞 �븯�뒗 �젙蹂닿� �룷�븿�릺�뼱�빞 �븯誘�濡� itemManagement_modifyPage.jsp �옉�꽦
	  public String modifyPagingPOST(ItemManagementDTO itemManagementDTO, 
	  SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		  
		  logger.info(cri.toString());
		  service.modify(itemManagementDTO);
		  
		  rttr.addAttribute("page", cri.getPage());
		  rttr.addAttribute("perPageNum", cri.getPerPageNum());
		  rttr.addAttribute("searchType", cri.getSearchType());
		  rttr.addAttribute("keyword", cri.getKeyword());
		    
		  rttr.addFlashAttribute("msg", "SUCCESS");
		  //�닔�젙 �옉�뾽�씠 �걹�굹硫� �궘�젣�� 留덉갔媛�吏�濡� �떎�떆 紐⑸줉 �럹�씠吏�濡� 由щ떎�씠�젆�듃 �릺�뼱�빞 �븯怨�, �닔�젙�맂 寃곌퀬�뿉 ���빐�꽌�뒗 'msg'濡� �쟾�넚
		  
		  logger.info(rttr.toString());
		  
		return "redirect:/item/itemManagement_list";
		//�닔�젙 �옉�뾽: �듅�젙 �럹�씠吏� �궡�뿉�꽌 �듅�젙 寃뚯떆臾� 議고쉶�븯怨�, �떎�떆 �닔�젙 �깮�븿
		//�닔�젙�씠 媛��뒫�븳 �솕硫댁뿉�꽌�뒗 POST 諛⑹떇�쑝濡� �닔�젙�쓣 �슂泥��븯寃� �릺怨� �옉�뾽 �셿猷� �썑
		//�썝�옒�쓽 �젙蹂닿� �쑀吏��맂 梨꾨줈 紐⑸줉 �럹�씠吏�濡� �씠�룞
	  }

	  //5. �븳寃곗쿂�읆 �눥�븨紐� �긽�뭹愿�由�-�긽�뭹 �벑濡�[�럹�씠吏� 泥섎━ �룷�븿] 泥섎━
	  
	  //5-1. �긽�뭹 �벑濡� �뤌
	  @RequestMapping(value = "/itemManagement_register", method = RequestMethod.GET)
	   ///WEB-INF/views/itemManagement/itemManagement_register.jsp �쓽誘�
	  public void registerGET(ItemManagementDTO itemManagementDTO, Model model) throws Exception {
	    logger.info("register get ...........");
	  //대분류 번호를 가져와서 대분류 명이 나오도록 함
	    categoryService.getCategoryList();
	    model.addAttribute("list", categoryService.getCategoryList());
	  }
	  
	  //5-2. �긽�뭹 �벑濡� 泥섎━ �뤌
	  //RedirectAttributes瑜� �씠�슜�븳 �닲源� �뜲�씠�꽣瑜� �쟾�넚�븯湲� �쐞�빐 addFlashAttribute()�씠�슜
	   @RequestMapping(value = "/itemManagement_register", method = RequestMethod.POST)
	   public String registPOST(@ModelAttribute ItemManagementDTO itemManagementDTO, RedirectAttributes rttr) throws Exception {
		   
		   logger.info("regist post ...........");
		   logger.info(itemManagementDTO.toString());
		   
		   service.regist(itemManagementDTO);
		  
		    rttr.addFlashAttribute("msg", "SUCCESS");
		   //釉뚮씪�슦���뿉�꽌 msg濡� 異붽��맂 �뜲�씠�꽣�뒗 蹂댁뿬吏�吏� �븡�쓬
		   
		   //return "/itemManagement/success";
		  // return "redirect:/itemManagement/itemManagement_listAll";
		   return "redirect:/item/itemManagement_list";
	   }
}
