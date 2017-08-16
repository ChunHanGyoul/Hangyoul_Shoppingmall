package han.gyoul.shop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import han.gyoul.shop.dto.ItemManagementDTO;
import han.gyoul.shop.member.dao.MemberDAO;
import han.gyoul.shop.member.dto.MemberDTO;
import han.gyoul.shop.member.service.MemberService;
import han.gyoul.shop.service.CustomerItemManagementService;
import han.gyoul.shop.service.ItemManagementService;

@Controller
@SessionAttributes({"memberDTO","itemList"})
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject
	private MemberDAO memberDAO;

	@Inject
	private MemberService memberService;
	
	@Inject
	private CustomerItemManagementService customerItemService;
	
	@Inject
	private ItemManagementService itemService;
	

	@RequestMapping(value = "/mainPage", method = RequestMethod.GET)
	public String mainGET(MemberDTO memberDTO, ItemManagementDTO itemManagementDTO, Model model, String success,
			@ModelAttribute("cri") han.gyoul.shop.dto.SearchCriteria cri) throws Exception {
		
		  logger.info(cri.toString());
		  
		      model.addAttribute("list", itemService.listCriteria(cri));
			  model.addAttribute("list", itemService.listSearchCriteria(cri));
			  //페이징 처리 부분
			  han.gyoul.shop.dto.PageMaker pageMaker = new han.gyoul.shop.dto.PageMaker();
			  pageMaker.setCri(cri);
			  //pageMaker.setTotalCount(15);
			  
			  pageMaker.setTotalCount(itemService.listCountCriteria(cri));
			  
			  model.addAttribute("pageMaker", pageMaker);
			  
			  //아이템을 리스트 객체에 담아와서 뿌려줌
			    List<ItemManagementDTO> list = customerItemService.customerListAll();
			    List<ItemManagementDTO> sendList = new ArrayList<ItemManagementDTO>();
			    for(int i = 0; i < 6; i++) {
			    	sendList.add(list.get(i));
			    }
			    model.addAttribute("itemList", sendList);
			    
				model.addAttribute("idUnique", 1);
				model.addAttribute("nickUnique", 1);
				String loginTry = memberDTO.getMb_id();
				String loginTry2 = memberDTO.getMb_pw();
				
				if (loginTry != null && loginTry2 != null) {
					
					success = "1";
					model.addAttribute("memberDTO", memberDTO);
					
				} else {
					success = "0";
				};
				
		
				model.addAttribute("success", success);
		
				return "/mainPage";

//	@RequestMapping(value = "/mainPage", method = RequestMethod.GET)
//	public String mainGET(MemberDTO memberDTO, Model model, String success) throws Exception {
//		model.addAttribute("idUnique", 1);
//		model.addAttribute("nickUnique", 1);
//		String loginTry = memberDTO.getMb_id();
//		String loginTry2 = memberDTO.getMb_pw();
//		
//		if (loginTry != null && loginTry2 != null) {
//			
//			success = "1";
//			model.addAttribute("memberDTO", memberDTO);
//			
//		} else {
//			success = "0";
//		};
//		
//
//		model.addAttribute("success", success);
//
//		return "/mainPage";
//
	}

	@RequestMapping(value = "/insertMember", method = RequestMethod.POST)
	public String mainPOST(MemberDTO memberDTO, SessionStatus ss) throws Exception {

		memberDAO.insertMember(memberDTO);

		ss.setComplete();

		return "redirect:/mainPage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET(@ModelAttribute("memberDTO") MemberDTO memberDTO, RedirectAttributes rttr, String success)throws Exception {
		
		String loginTry3 = memberDTO.getMb_grade();

		System.out.println(loginTry3);
		if(loginTry3.equals("1")){
		System.out.println("ddd");
		rttr.addFlashAttribute("memberDTO", memberDTO);
		rttr.addFlashAttribute("success", success);
		return "redirect:/mainPage";
		
		}else{
		System.out.println(loginTry3);
		return "/adminPage";
		}
		
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(MemberDTO memberDTO, Model model, String success, RedirectAttributes rttr, SessionStatus ss)
			throws Exception {

		MemberDTO dto = memberDAO.memberLogin(memberDTO);
		

		if (dto == null) {
			ss.setComplete();
			success = "0";
			model.addAttribute("success", success);

			return "redirect:/mainPage";
		}
		success = "1";
		model.addAttribute("success", success);
		model.addAttribute("memberDTO", dto);

		return loginGET(dto, rttr, success);
	}

	@RequestMapping(value = "/logOut", method = RequestMethod.POST)
	public String logOutPOST(MemberDTO memberDTO, SessionStatus ss) throws Exception {

		ss.setComplete();

		return "redirect:/mainPage";
	}

	@RequestMapping(value = "/myPage", method = RequestMethod.GET)
	public String myPageGET(MemberDTO memberDTO, String success, RedirectAttributes rttr, SessionStatus ss, Model model)
			throws Exception {

		if (memberDTO.getMb_id() == null) {
			ss.setComplete();
			success = "0";
			model.addAttribute("success", success);
			return "redirect:/mainPage";
		} else {
			success = "1";
			model.addAttribute("success", success);
			MemberDTO read = memberService.memberRead(memberDTO);
			model.addAttribute("memberDTO", read);
		}
		return "/myPage";
	}

	@RequestMapping(value = "/memberModify", method = RequestMethod.POST)
	public String memberModify(MemberDTO memberDTO, String success, Model model, RedirectAttributes rttr)
			throws Exception {

		rttr.addAttribute("success", success);
		memberService.updateMember(memberDTO);

		return "redirect:/myPage";
	}

	@RequestMapping(value = "/memberRemove", method = RequestMethod.POST)
	public String memberRemove(MemberDTO memberDTO, String success, Model model, SessionStatus ss) throws Exception {

		int mb_no = memberDTO.getMb_no();

		memberService.removeMember(mb_no);

		ss.setComplete();

		return "redirect:/mainPage";
	}

	@RequestMapping(value = "/mainPage", method = RequestMethod.POST)
	public String idUnique(MemberDTO memberDTO, Model model) throws Exception {
		String mb_nickname = memberDTO.getMb_nickname();
		
		if(memberDTO.getMb_id().equals("delete")) {
			model.addAttribute("nickUnique", 1);
			model.addAttribute("idUnique", -2);
			memberDTO.setMb_nickname(null);
			return "/mainPage";
		}
		
		if (mb_nickname == null) {
			model.addAttribute("nickUnique", 1);
			String mb_id = memberDTO.getMb_id();
			String unique = memberService.idUnique(mb_id);
			model.addAttribute("idUnique", unique);
			return "/mainPage";

		}
		String mb_id = memberDTO.getMb_id();
		String unique = memberService.idUnique(mb_id);
		
		model.addAttribute("idUnique", unique);
		String mb_nickname1 = memberDTO.getMb_nickname();
		String unique1 = memberService.nickNameUnique(mb_nickname1);
		model.addAttribute("nickUnique", unique1);
		return "/mainPage";
	}

	@RequestMapping("/emailAuth")
	public ModelAndView emailAuth(HttpServletResponse response, HttpServletRequest request) throws Exception {
		String email = request.getParameter("email");
		String authNum = "";

		String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		boolean isNormal = m.matches();
		if(!isNormal) {
			ModelAndView emailError = new ModelAndView();
			emailError.setViewName("/emailError");
			return emailError;
		}

		authNum = memberService.RandomNum();

		memberService.sendEmail(email.toString(), authNum);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("/emailAuth");
		mv.addObject("email", email);
		mv.addObject("authNum", authNum);

		return mv;
	}

}
