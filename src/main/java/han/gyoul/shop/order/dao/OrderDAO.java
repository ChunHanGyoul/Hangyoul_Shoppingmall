package han.gyoul.shop.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import han.gyoul.shop.dto.ItemManagementDTO;
import han.gyoul.shop.member.dto.MemberDTO;
import han.gyoul.shop.order.dto.OrderDTO;
import han.gyoul.shop.order.dto.OrderdetailDTO;

@Repository
public class OrderDAO {
	SqlSessionTemplate template;

	public void setTemplate(SqlSessionTemplate template) {
		this.template = template;
	}

	public void makeTemplate() {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/OrderBean.xml");
		template = (SqlSessionTemplate) context.getBean("sqlSessionTemplate");
	}

	public MemberDTO getMemberDTO(int mb_no) {
		makeTemplate();
		Map map = new HashMap();
		map.put("mb_no", mb_no);
		return template.selectOne("getMemberDTO", map);
	}

	public boolean isAlreadyItem(OrderDTO orderDTO) {
		makeTemplate();
		OrderDTO tempOrderDTO = template.selectOne("isAlreadyItem", orderDTO);
		if(tempOrderDTO == null) {
			return false;
		}
		return true;
	}
	
	public ItemManagementDTO searchItem(OrderDTO orderDTO) {
		makeTemplate();
		return template.selectOne("searchItem", orderDTO);
	}
	
	public boolean addCart(OrderDTO orderDTO) {
		makeTemplate();
		int result = template.insert("addCart", orderDTO);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public List<OrderDTO> getOrderDTOs(MemberDTO memberDTO) {
		makeTemplate();
		return template.selectList("getOrderDTOs", memberDTO);
	}

	public ItemManagementDTO getItemDTO(OrderDTO orderDTO) {
		makeTemplate();
		return template.selectOne("getItemDTO", orderDTO);
	}

	public void OrderItems(MemberDTO memberDTO, OrderdetailDTO orderdetailDTO) {
		makeTemplate();
		Map map = new HashMap();
		map.put("receiver_name", orderdetailDTO.getReceiver_name());
		map.put("receiver_mobile", orderdetailDTO.getReceiver_mobile());
		map.put("receiver_email", orderdetailDTO.getReceiver_email());
		map.put("receiver_zipcode", orderdetailDTO.getReceiver_zipcode());
		map.put("receiver_address", orderdetailDTO.getReceiver_address());
		map.put("receiver_memo", orderdetailDTO.getReceiver_memo());
		map.put("pay_method", orderdetailDTO.getPay_method());
		map.put("bank_kind", orderdetailDTO.getBank_kind());
		map.put("bank_acct", orderdetailDTO.getBank_acct());
		map.put("bank_sender", orderdetailDTO.getBank_sender());
		map.put("mb_no", memberDTO.getMb_no());
		template.insert("makeOrderDetail", map);
		map.put("orderdetail_no", template.selectOne("getOrderdetail_no"));
		template.update("orderItems", map);
	}

	public void removeCart(Map map) {
		makeTemplate();
		template.delete("removeCart", map);
		
	}

	public void removeAllCart(MemberDTO memberDTO) {
		makeTemplate();
		template.delete("removeAllCart", memberDTO);
	}

	public List<OrderdetailDTO> getOrderdetailDTO(MemberDTO memberDTO) {
		makeTemplate();
		return template.selectList("getOrderdetailDTO", memberDTO);
	}

	public List<OrderDTO> getPayOrderDTOs(MemberDTO memberDTO) {
		makeTemplate();
		return template.selectList("getPayOrderDTOs", memberDTO);
	}

	public ItemManagementDTO getPayItemDTO(OrderDTO orderDTO) {
		makeTemplate();
		return template.selectOne("getPayItemDTO", orderDTO);
	}

	public List<OrderdetailDTO> getAllOrderdetailDTO() {
		makeTemplate();
		return template.selectList("getAllOrderdetailDTO");
	}

	public List<OrderDTO> getAllPayOrderDTOs() {
		makeTemplate();
		return template.selectList("getAllPayOrderDTOs");
	}

	public void changeDeliveryState(Map map) {
		makeTemplate();
		template.update("changeDeliveryState", map);
		
	}
	
}
