<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="han.gyoul.shop.order.dto.*"%>
<%@ page import="han.gyoul.shop.dto.*"%>
<%@ page import="han.gyoul.shop.member.dto.*"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<OrderDTO> orderDTOs = (ArrayList<OrderDTO>) request.getAttribute("orderDTOs");
	ArrayList<OrderdetailDTO> orderdetailDTOs = (ArrayList<OrderdetailDTO>) request
			.getAttribute("orderdetailDTOs");
	ArrayList<ItemManagementDTO> itemDTOs = (ArrayList<ItemManagementDTO>) request.getAttribute("itemDTOs");
	MemberDTO memberDTO = (MemberDTO) request.getAttribute("memberDTO");
%>
<jsp:include page="../../basicBody.jsp" flush="true"></jsp:include>
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
</script>
</head>
<body>
	<div style="margin-left: 300px; margin-right: 300px; margin-top: 150px; text-align: center; z-index : 10;">
		<h1>ORDER CHECK</h1>
		<br/>
		<table class="blue_table" style="margin: 0 auto;">
			<tr>
				<th>배송번호</td>
				<th>배송자</td>
				<th>회원번호</td>
				<th>배송자핸드폰</td>
				<th>배송자이메일</td>
				<th>배송지우편</td>
				<th>배송지</td>
				<th>배송메모</td>
				<th>배송상태</td>
				<th>주문내역</td>
			</tr>
			<%
				for (int i = 0; i < orderdetailDTOs.size(); i++) {
			%>
			<tr>
				<td><%=orderdetailDTOs.get(i).getOrderdetail_no()%></td>
				<td><%=orderdetailDTOs.get(i).getReceiver_name()%>
				<td><%=memberDTO.getMb_no()%></td>
				<td><%=orderdetailDTOs.get(i).getReceiver_mobile()%></td>
				<td><%=orderdetailDTOs.get(i).getReceiver_email()%></td>
				<td><%=orderdetailDTOs.get(i).getReceiver_zipcode()%></td>
				<td><%=orderdetailDTOs.get(i).getReceiver_address()%></td>
				<td><%=orderdetailDTOs.get(i).getReceiver_memo()%></td>
				<td><%=orderdetailDTOs.get(i).getDelivery_state()%></td>
				<td><input id="showDetailButton<%=i%>" type="button"
					onclick="showDetail(<%=i%>)" value="확인" class="blue_button"></td>
			</tr>
			<tr id="DetailRow<%=i%>" style="display: none">
				<td colspan=10>
					<table class="blue_table" style="margin: 0 auto;">
						<tr>
							<th>주문번호</th>
							<th>주문날짜</th>
							<th>상품이름</th>
							<th>상품가격</th>
							<th>주문수량</th>
							<th>합계액</th>
						</tr>
						<%
							int tot_price = 0;
							for (int j = 0; j < orderDTOs.size(); j++) {
									if (orderDTOs.get(j).getOrderdetail_no() == orderdetailDTOs.get(i).getOrderdetail_no()) {
						%>
						<tr>
							<td><%=orderDTOs.get(j).getOrder_no()%></td>
							<td><%=orderDTOs.get(j).getOrder_date()%></td>
							<td><%=itemDTOs.get(j).getItem_name()%></td>
							<td><%=itemDTOs.get(j).getItem_price()%>원</td>
							<td><%=orderDTOs.get(j).getOrder_qty()%></td>
							<td><%=(itemDTOs.get(j).getItem_price()*orderDTOs.get(j).getOrder_qty())%>원</td>
							<%tot_price += (itemDTOs.get(j).getItem_price()*orderDTOs.get(j).getOrder_qty());%>
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