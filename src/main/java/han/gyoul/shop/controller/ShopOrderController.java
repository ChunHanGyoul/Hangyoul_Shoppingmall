package han.gyoul.shop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import han.gyoul.shop.member.dto.MemberDTO;
import han.gyoul.shop.order.dto.OrderDTO;
import han.gyoul.shop.order.dto.OrderdetailDTO;
import han.gyoul.shop.order.service.OrderServiceImpl;

@Controller
@SessionAttributes({"memberDTO", "orderDTOs", "itemDTOs","itemList"})
public class ShopOrderController {
	@Autowired
	OrderServiceImpl orderServiceImpl;

	// �α����� ���� �ӽ÷� ���� ��Ʈ�ѷ�	
	
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public String start(Model model) {
		return "/board/order/login";
	}
	
	@RequestMapping(value = "/login1", method = RequestMethod.POST)
	public String login(Model model, @ModelAttribute("mb_no") int mb_no) {
		orderServiceImpl.login(model, mb_no);
		return "/board/order/main";
	}

	// ��ٱ��Ͽ� �����ϴ� ��Ʈ�ѷ�
	@RequestMapping("/addCart")
	public String addCart(Model model, @ModelAttribute("orderDTO") OrderDTO orderDTO, HttpServletRequest request) {
		int item_no = Integer.parseInt(request.getParameter("item_no").toString());
		System.out.println("item_no = " + item_no);
		boolean result = orderServiceImpl.addCart(model, orderDTO);
		if (!result) {
			return "/board/order/error";
		}
		return viewCart(model);
	}

	// ��ٱ��ϸ� �����ִ� ��Ʈ�ѷ�
	@RequestMapping(value = "/viewCart", method = RequestMethod.GET)
	public String viewCart(Model model) {
		ArrayList<OrderDTO> orderDTOs = orderServiceImpl.getOrderDTOs(model);
		orderServiceImpl.getItemDTOs(model, orderDTOs);

		Map map = model.asMap();
		MemberDTO memberDTO = (MemberDTO)map.get("memberDTO");
		String loginTry = memberDTO.getMb_id();
		String loginTry2 = memberDTO.getMb_pw();
		System.out.println(loginTry);

		if (loginTry != null && loginTry2 != null) {
		String success = "1";
		model.addAttribute("success", success);
		model.addAttribute("memberDTO", memberDTO);
		return "/board/order/cartView";

		}else{
		String success = "0";
		model.addAttribute("success", success);
		return "/mainPage";
		}
	}

	// ��ٱ��ϸ� ������ ���� �����ִ� ��Ʈ�ѷ�
	@RequestMapping("/order")
	public String order(Model model) {
		Map map = model.asMap();
		MemberDTO memberDTO = (MemberDTO) map.get("memberDTO");
		int mb_no = memberDTO.getMb_no();
		memberDTO = orderServiceImpl.getMemberDTO(mb_no);
		model.addAttribute(memberDTO);
		return "/board/order/orderView";
	}

	// ������ �Ϸ�Ǹ� �����͸� �����ϰ� main�������� �̵��ϴ� ��Ʈ�ѷ�
	@RequestMapping(value = "/orderComplete", method = RequestMethod.POST)
	public String orderComplete(OrderdetailDTO orderdetailDTO, HttpServletRequest request, Model model) {
		orderServiceImpl.OrderItem(model, request, orderdetailDTO);
		return viewCart(model);
	}
	
	// ��ٱ��Ͽ��� ��ǰ �Ѱ��� ����ϴ� ��Ʈ�ѷ�
	@RequestMapping(value = "/removeCart", method = RequestMethod.GET)
	public String removeCart(HttpServletRequest request, Model model) {
		orderServiceImpl.removeCart(model, request);
		return viewCart(model);
	}
	
	// ��ٱ��ϸ� ���� ���� ��Ʈ�ѷ�
	@RequestMapping(value = "/removeAllCart", method = RequestMethod.GET)
	public String removeAllCart(Model model) {
		orderServiceImpl.removeAllCart(model);
		return viewCart(model);
	}
	
	// ��ۻ��¸� �����ִ� ��Ʈ�ѷ�
	@RequestMapping("/showDelivery")
	public String showDelivery(Model model) {
		orderServiceImpl.getOrderdetailDTO(model);
		List<OrderDTO> orderDTOs = orderServiceImpl.getPayOrderDTOs(model);
		orderServiceImpl.getPayOrderItemDTOs(model, orderDTOs);		
		
		Map map = model.asMap();
		MemberDTO memberDTO = (MemberDTO)map.get("memberDTO");
		String loginTry = memberDTO.getMb_id();
		String loginTry2 = memberDTO.getMb_pw();
		System.out.println(loginTry);

		if (loginTry != null && loginTry2 != null) {
		String success = "1";
		model.addAttribute("success", success);
		model.addAttribute("memberDTO", memberDTO);
		return "/board/order/deliveryView";

		}else{
		String success = "0";
		model.addAttribute("success", success);
		return "/mainPage";
		}
		
	}
	
	// ��� ��ۻ��¸� �����ִ� ��Ʈ�ѷ�
	@RequestMapping("/showAllDelivery")
	public String showAllDelivery(Model model) {
		orderServiceImpl.getAllOrderdetailDTO(model);
		List<OrderDTO> orderDTOs = orderServiceImpl.getAllPayOrderDTO(model);
		orderServiceImpl.getAllPayItemDTO(model, orderDTOs);
		orderServiceImpl.getAllPayMemberDTO(model, orderDTOs);
		return "/board/order/manageDeliveryView";
	}
	
	// ��ۻ��� ����� ajax ó���� �޴� ��Ʈ�ѷ�
	@RequestMapping(value = "/changeState", method = RequestMethod.POST)
	public String changeDeliveryState(int orderdetail_no, String delivery_state) {
		orderServiceImpl.changeDeliveryState(orderdetail_no, delivery_state);
		return "succes!!";
	}
	

}
