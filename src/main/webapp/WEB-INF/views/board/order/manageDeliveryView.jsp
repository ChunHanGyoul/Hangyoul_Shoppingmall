<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="han.gyoul.shop.order.dto.*"%>
<%@ page import="han.gyoul.shop.dto.*"%>
<%@ page import="han.gyoul.shop.member.dto.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%
	ArrayList<OrderDTO> orderDTOs = (ArrayList<OrderDTO>) request.getAttribute("orderDTOs");
	ArrayList<OrderdetailDTO> orderdetailDTOs = (ArrayList<OrderdetailDTO>) request
			.getAttribute("orderdetailDTOs");
	ArrayList<ItemManagementDTO> itemDTOs = (ArrayList<ItemManagementDTO>) request.getAttribute("itemDTOs");
	Map itemMap = new HashMap();
	for (int i = 0; i < itemDTOs.size(); i++) {
		if (!itemMap.containsKey(itemDTOs.get(i).getItem_no())) {
			itemMap.put(itemDTOs.get(i).getItem_no(), itemDTOs.get(i));
		}
	}
	ArrayList<MemberDTO> memberDTOs = (ArrayList<MemberDTO>) request.getAttribute("memberDTOs");
	Map memberMap = new HashMap();
	for (int i = 0; i < memberDTOs.size(); i++) {
		if (!memberMap.containsKey(memberDTOs.get(i).getMb_no())) {
			memberMap.put(memberDTOs.get(i).getMb_no(), memberDTOs.get(i));
		}
	}
%>
<jsp:include page="../../basicBody2.jsp" flush="true"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/blue_table.css">
<style type="text/css">
.blue_button {
	background-color: #5cc2f2;
	padding: 7.5px;
	font-family: 'Open Sans', sans-serif;
	font-size: 12px;
	font-weight: bold;
	text-decoration: none;
	color: #fff;
	background-image: linear-gradient(bottom, rgb(171, 27, 27) 0%,
		rgb(212, 51, 51) 100%);
	border-radius: 5px;
}

.blue_button:active {
	padding-bottom: 9px;
	padding-left: 10px;
	padding-right: 10px;
	padding-top: 11px;
	top: 1px;
	background-image: linear-gradient(bottom, rgb(171, 27, 27) 100%,
		rgb(212, 51, 51) 0%);
}
</style>
<script type="text/javascript" src="./js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
function showDetail(i){
	var id = "#DetailRow"+i;
	var buttonId = "showDetailButton"+i;
	
	var status = $(id).css("display");
	if(status == "none") {
		$(id).css("display","");
		document.getElementById(buttonId).value = "취소"
	} else {
		$(id).css("display","none");
		document.getElementById(buttonId).value = "확인"
	}
}
function changeDeliveryState(obt) {
	var delivery_state = obt.value;
	var orderdetail_no = obt.id.substring(14);
	$.ajax({
		url : './changeState',
		type : 'post',
		data:{
			orderdetail_no : obt.id.substring(14),
			delivery_state : obt.value
		},
		async: false,
		success:function(t) { alert(t);}
	});
	alert("주문상태가 변경되었습니다.");
}
</script>
</head>
<body>
	<div style="margin-left: 300px; margin-right: 300px; margin-top: 150px; text-align: center; z-index : 10;">
		<h1>MEMBER'S ORDER CHECK</h1>
		<table class = "blue_table" style="margin: 0 auto;">
			<tr>
				<th>배송번호</th>
				<th>배송자</th>
				<th>배송자핸드폰</th>
				<th>배송자이메일</th>
				<th>배송지우편</th>
				<th>배송지</th>
				<th>배송메모</th>
				<th>배송상태</th>
				<th>주문내역</th>
			</tr>
			<%
				MemberDTO tempMemberDTO;
				for (int i = 0; i < orderdetailDTOs.size(); i++) {
			%>
			<tr>
				<td><%=orderdetailDTOs.get(i).getOrderdetail_no()%></td>
				<td><%=orderdetailDTOs.get(i).getReceiver_name()%></td>
				<td><%=orderdetailDTOs.get(i).getReceiver_mobile()%></td>
				<td><%=orderdetailDTOs.get(i).getReceiver_email()%></td>
				<td><%=orderdetailDTOs.get(i).getReceiver_zipcode()%></td>
				<td><%=orderdetailDTOs.get(i).getReceiver_address()%></td>
				<td><%=orderdetailDTOs.get(i).getReceiver_memo()%></td>
				<td><select
					id="delivery_state<%=orderdetailDTOs.get(i).getOrderdetail_no()%>"
					onchange="changeDeliveryState(this)">
						<option
							<%if (orderdetailDTOs.get(i).getDelivery_state().equals("배송준비중"))
					out.print("selected = 'selected'");%>>배송준비중</option>
						<option
							<%if (orderdetailDTOs.get(i).getDelivery_state().equals("배송중"))
					out.print("selected = 'selected'");%>>배송중</option>
						<option
							<%if (orderdetailDTOs.get(i).getDelivery_state().equals("배송지연"))
					out.print("selected = 'selected'");%>>배송지연</option>
						<option
							<%if (orderdetailDTOs.get(i).getDelivery_state().equals("배송완료"))
					out.print("selected = 'selected'");%>>배송완료</option>
				</select></td>
				<td><input id="showDetailButton<%=i%>" type="button"
					onclick="showDetail(<%=i%>)" value="확인" class = "blue_button"></td>
			</tr>
			<tr id="DetailRow<%=i%>" style="display: none">
				<td colspan=10>
					<table border="solid 1px black">
						<tr>
							<th>회원번호</th>
							<th>회원이름</th>
							<th>회원 전화번호</th>
							<th>회원 이메일</th>
						</tr>
						<%
							tempMemberDTO = (MemberDTO) memberMap.get(orderdetailDTOs.get(i).getMb_no());
						%>
						<tr>
							<td><%=tempMemberDTO.getMb_no()%></td>
							<td><%=tempMemberDTO.getMb_name()%></td>
							<td><%=tempMemberDTO.getMb_mobile()%></td>
							<td><%=tempMemberDTO.getMb_email()%></td>
						</tr>
					</table>
					<table border="solid 1px black">
						<tr>
							<td>주문번호</td>
							<td>주문날짜</td>
							<td>상품이름</td>
							<td>상품가격</td>
							<td>주문수량</td>
							<td>합계액</td>
						</tr>
						<%
							ItemManagementDTO tempItemDTO;
							int tot_price = 0;
								for (int j = 0; j < orderDTOs.size(); j++) {
									if (orderDTOs.get(j).getOrderdetail_no() == orderdetailDTOs.get(i).getOrderdetail_no()) {
						%>
						<%
							tempItemDTO = (ItemManagementDTO) itemMap.get(orderDTOs.get(j).getItem_no());
						%>
						<tr>
							<td><%=orderDTOs.get(j).getOrder_no()%></td>
							<td><%=orderDTOs.get(j).getOrder_date()%></td>
							<td><%=tempItemDTO.getItem_name()%></td>
							<td><%=tempItemDTO.getItem_price()%>원</td>
							<td><%=orderDTOs.get(j).getOrder_qty()%></td>
							<td><%=(tempItemDTO.getItem_price()*orderDTOs.get(j).getOrder_qty())%>원</td>
							<%tot_price += (tempItemDTO.getItem_price()*orderDTOs.get(j).getOrder_qty());%>
						</tr>
						<%
							}
								}
						%>
						<tr>
							<td colspan = "2">총 가격</td>
							<td colspan = "4"><%=tot_price %>원</td>
							<%tot_price = 0; %>
						</tr>
					</table>
				</td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>