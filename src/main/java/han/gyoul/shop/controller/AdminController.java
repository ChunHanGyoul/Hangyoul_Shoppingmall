package han.gyoul.shop.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import han.gyoul.shop.member.dao.AdminDAO;
import han.gyoul.shop.member.dto.MemberDTO;

@Controller
@SessionAttributes({"memberDTO","itemList"})
public class AdminController {
	@Inject
	private AdminDAO adminDAO;
	
//	@RequestMapping(value="/adminPage", method=RequestMethod.GET)
//	public String adminPage()throws Exception{
//		
//		
//		return "/adminPage";
//	}
//	
	
	@RequestMapping(value="/memberList", method=RequestMethod.GET)
	public void memberList(Model model)throws Exception{
		
		model.addAttribute("list", adminDAO.memberList());

	}
	
	
	@RequestMapping(value="/gradeMediate", method=RequestMethod.POST)
	public String gradeMediatePOST(Model model, MemberDTO memberDTO)throws Exception{
		
		
		System.out.println(memberDTO.getMb_no());
		System.out.println(memberDTO.getMb_grade());
		
		adminDAO.Mediate(memberDTO);
		
		return "redirect:/memberList";
	}

}
