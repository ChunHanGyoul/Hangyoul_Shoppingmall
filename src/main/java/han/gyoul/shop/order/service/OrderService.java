package han.gyoul.shop.order.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.Model;

import han.gyoul.shop.member.dto.MemberDTO;
import han.gyoul.shop.order.dao.OrderDAO;
import han.gyoul.shop.order.dto.OrderDTO;
import han.gyoul.shop.order.dto.OrderdetailDTO;

public interface OrderService {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("config/OrderBean.xml");
	OrderDAO orderDAO = (OrderDAO) context.getBean("orderDAO");
	
	// �ӽ÷� �α������ִ� ����... �����ߴ��
	public void login(Model model, int mb_no);
	// īƮ�� ��ǰ �߰�
	public boolean addCart(Model model, OrderDTO orderDTO);
	// �ӽ÷� �α��� ���� ������ ���� memberDTO ��ü ����... �������� �κ�
	public MemberDTO getMemberDTO(int login_mb_no);
	// �ֹ��� īƮ�� ��ǰ�� �ֹ��ϱ�
	public void OrderItem(Model model, HttpServletRequest request, OrderdetailDTO orderdetailDTO);
	// ��ٱ��� ��ǰ ����
	public void removeCart(Model model, HttpServletRequest request);
	// ��ٱ��� ��ǰ ���� ����
	public void removeAllCart(Model model);
	// ��ٱ��� ��ǰ�� ���
	public ArrayList<OrderDTO> getOrderDTOs(Model model);
	// orderTable�� �ش��ϴ� itemDTO �� ���
	public void getItemDTOs(Model model, ArrayList<OrderDTO> orderDTOs);
	// mb_no �� �ش��ϴ� orderdetailDTO ���
	public void getOrderdetailDTO(Model model);
	// mb_no�� �ش��ϴ� ������ orderdto ���
	public List<OrderDTO> getPayOrderDTOs(Model model);
	// mb_no�� �ش��ϴ� ������ orderdto�� item�� ���
	void getPayOrderItemDTOs(Model model, List<OrderDTO> orderDTOs);
	// ��� orderdetailDTO�� ���
	void getAllOrderdetailDTO(Model model);
	// ��� ������ orderDTO ���
	List<OrderDTO> getAllPayOrderDTO(Model model);
	// ��� ������ itemDTO ���
	void getAllPayItemDTO(Model model, List<OrderDTO> orderDTOs);
	// ��� ������ orderDTO�� memberDTO ���
	void getAllPayMemberDTO(Model model, List<OrderDTO> orderDTOs);
	// ��� ���� ���� (Ajax)
	void changeDeliveryState(int orderdetail_no, String delivery_state);
		
}
