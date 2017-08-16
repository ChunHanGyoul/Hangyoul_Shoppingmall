package han.gyoul.shop.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import han.gyoul.shop.dto.ItemManagementDTO;
import han.gyoul.shop.member.dto.MemberDTO;
import han.gyoul.shop.order.dto.OrderDTO;
import han.gyoul.shop.order.dto.OrderdetailDTO;

@Service
public class OrderServiceImpl implements OrderService {
	Map map;
	// �α����� ���� �ӽ÷� ������� ����
	@Override
	public void login(Model model, int mb_no) {
		model.addAttribute("mb_no", mb_no);
		model.addAttribute("memberDTO", getMemberDTO(mb_no));
	}

	// īƮ�� ��ǰ �߰�
	@Override
	public boolean addCart(Model model, OrderDTO orderDTO) {
		// orderDTO�� �α��ε� mb_no ����
		map = model.asMap();
		orderDTO.setMb_no(((MemberDTO) map.get("memberDTO")).getMb_no());
		System.out.println("mb_no : " + ((MemberDTO) map.get("memberDTO")).getMb_no());
		// ��ǰ�� �����Ѵٸ� false ��ȯ
		boolean isAlreadyItem = orderDAO.isAlreadyItem(orderDTO);
		if (isAlreadyItem) {
			return false;
		}
		// �ش� ������ ������ ������ tot_price ����
		orderDTO.setTot_price(orderDTO.getOrder_qty() * orderDAO.searchItem(orderDTO).getItem_price());

		// ���� sql ������ ����� ��ȯ
		return orderDAO.addCart(orderDTO);
	}

	// �ӽ÷� �α��� ���� ������ ���� memberDTO ��ü ����... �������� �κ�
	@Override
	public MemberDTO getMemberDTO(int mb_no) {
		return orderDAO.getMemberDTO(mb_no);
	}

	// �ֹ��� īƮ�� ��ǰ�� �ֹ��ϱ�
	@Override
	public void OrderItem(Model model, HttpServletRequest request, OrderdetailDTO orderdetailDTO) {
		map = model.asMap();
		MemberDTO memberDTO = (MemberDTO) map.get("memberDTO");

		if (orderdetailDTO.getReceiver_memo() == null) {
			orderdetailDTO.setReceiver_memo("x");
		}
		if (orderdetailDTO.getBank_kind() == null) {
			orderdetailDTO.setBank_kind("x");
		}
		int bank_acct = -1;
		if (!request.getParameter("bank_acct1").equals("")) {
			orderdetailDTO.setBank_acct(request.getParameter("bank_acct1"));
		}
		String bank_sender = memberDTO.getMb_name();
		if (!request.getParameter("bank_sender1").equals("")) {
			orderdetailDTO.setBank_sender(request.getParameter("bank_sender1"));
		}
		orderDAO.OrderItems(memberDTO, orderdetailDTO);
	}

	// ��ٱ��� ��ǰ ����
	@Override
	public void removeCart(Model model, HttpServletRequest request) {
		map = model.asMap();
		Map insertMap = new HashMap();
		insertMap.put("mb_no", ((MemberDTO) map.get("memberDTO")).getMb_no());
		insertMap.put("item_no", Integer.parseInt(request.getParameter("item_no")));
		orderDAO.removeCart(insertMap);
	}

	// ��ٱ��� ��ǰ ���� ����
	@Override
	public void removeAllCart(Model model) {
		map = model.asMap();
		orderDAO.removeAllCart((MemberDTO) map.get("memberDTO"));
	}

	// ��ٱ��� ��ǰ�� ���
	@Override
	public ArrayList<OrderDTO> getOrderDTOs(Model model) {
		map = model.asMap();
		ArrayList<OrderDTO> orderDTOs = (ArrayList<OrderDTO>) orderDAO.getOrderDTOs((MemberDTO) map.get("memberDTO"));
		model.addAttribute("orderDTOs", orderDTOs);
		return orderDTOs;

	}

	// orderTable�� �ش��ϴ� itemDTO �� ���
	@Override
	public void getItemDTOs(Model model, ArrayList<OrderDTO> orderDTOs) {
		ArrayList<ItemManagementDTO> itemDTOs = new ArrayList<ItemManagementDTO>();
		for (int i = 0; i < orderDTOs.size(); i++) {
			ItemManagementDTO itemDTO = orderDAO.getItemDTO(orderDTOs.get(i));
			itemDTOs.add(itemDTO);
		}
		model.addAttribute("itemDTOs", itemDTOs);
	}

	// �ֹ��� ��ǰ���� �ڼ��� ���� Ȯ��
	@Override
	public void getOrderdetailDTO(Model model) {
		map = model.asMap();
		List<OrderdetailDTO> orderdetailDTOs = orderDAO.getOrderdetailDTO((MemberDTO) map.get("memberDTO"));
		model.addAttribute("orderdetailDTOs", orderdetailDTOs);
	}

	// �ֹ��� �����鿡 ���� order_table ���� ���
	@Override
	public List<OrderDTO> getPayOrderDTOs(Model model) {
		map = model.asMap();
		List<OrderDTO> orderDTOs = orderDAO.getPayOrderDTOs((MemberDTO) map.get("memberDTO"));
		model.addAttribute("orderDTOs", orderDTOs);
		return orderDTOs;
	}

	// �ֹ��� �����鿡 ���� order�� ���� item_table ������ ���
	@Override
	public void getPayOrderItemDTOs(Model model, List<OrderDTO> orderDTOs) {
		List<ItemManagementDTO> itemDTOs = new ArrayList<ItemManagementDTO>();
		for (int i = 0; i < orderDTOs.size(); i++) {
			ItemManagementDTO itemDTO = orderDAO.getPayItemDTO(orderDTOs.get(i));
			itemDTOs.add(itemDTO);
		}
		model.addAttribute("itemDTOs", itemDTOs);
	}
	
	// ��� orderdetailDTO�� ���
	@Override
	public void getAllOrderdetailDTO(Model model) {
		List<OrderdetailDTO> orderdetailDTOs = orderDAO.getAllOrderdetailDTO();
		model.addAttribute("orderdetailDTOs", orderdetailDTOs);
	}

	@Override
	// ��� ������ orderDTO ���
	public List<OrderDTO> getAllPayOrderDTO(Model model) {
		List<OrderDTO> orderDTOs = orderDAO.getAllPayOrderDTOs();
		model.addAttribute("orderDTOs", orderDTOs);
		return orderDTOs;
	}

	@Override
	// ��� ������ itemDTO ���
	public void getAllPayItemDTO(Model model, List<OrderDTO> orderDTOs) {
		ArrayList<Integer> item_no_list = new ArrayList<Integer>();
		List<ItemManagementDTO> itemDTOs = new ArrayList<ItemManagementDTO>();
		for(int i = 0; i < orderDTOs.size(); i++) {
			if(!item_no_list.contains(orderDTOs.get(i).getItem_no())) {
				item_no_list.add(orderDTOs.get(i).getItem_no());
				itemDTOs.add(orderDAO.getPayItemDTO(orderDTOs.get(i)));
			}
		}
		model.addAttribute("itemDTOs", itemDTOs);
	}

	@Override
	// ��� ������ orderDTO�� memberDTO ���
	public void getAllPayMemberDTO(Model model, List<OrderDTO> orderDTOs) {
		ArrayList<Integer> mb_no_list = new ArrayList<Integer>();
		List<MemberDTO> memberDTOs = new ArrayList<MemberDTO>();
		for(int i = 0; i < orderDTOs.size(); i++) {
			if(!mb_no_list.contains(orderDTOs.get(i).getMb_no())) {
				mb_no_list.add(orderDTOs.get(i).getMb_no());
				memberDTOs.add(orderDAO.getMemberDTO(orderDTOs.get(i).getMb_no()));
			}
		}
		model.addAttribute("memberDTOs", memberDTOs);
	}

	@Override
	// ��� ���� ���� (Ajax)
	public void changeDeliveryState(int orderdetail_no, String delivery_state) {
		Map map = new HashMap();
		map.put("orderdetail_no", orderdetail_no);
		map.put("delivery_state", delivery_state);
		orderDAO.changeDeliveryState(map);
		
		
	}

}
