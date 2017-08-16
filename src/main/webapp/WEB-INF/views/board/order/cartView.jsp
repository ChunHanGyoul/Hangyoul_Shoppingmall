<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="han.gyoul.shop.order.dto.*"%>
<%@ page import="han.gyoul.shop.dto.*"%>
<%@ page import="han.gyoul.shop.member.dto.*"%>
<%@ page import="java.util.ArrayList"%>
<%
	request.setCharacterEncoding("UTF-8");
	ArrayList<OrderDTO> orderDTOs = (ArrayList<OrderDTO>) request.getAttribute("orderDTOs");
	ArrayList<ItemManagementDTO> itemDTOs = (ArrayList<ItemManagementDTO>) request.getAttribute("itemDTOs");
	MemberDTO memberDTO = (MemberDTO) request.getAttribute("memberDTO");
	int tot_price = 0;
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
</head>
<body>
	<div
		style="margin-left: 300px; margin-right: 300px; margin-top: 150px; text-align: center;">
		<h1>SHOPPING BAG</h1>
		<form action="./removeAllCart" method="get">
			<input type="submit" value="장바구니 비우기" class="blue_button"
				style="margin: 0 auto; margin-left: 400px" />
		</form>
		<br />
		<table class="blue_table" style="margin: 0 auto;">
			<tr>
				<th>이미지</th>
				<th>상품명</th>
				<th>가격</th>
				<th>사이즈</th>
				<th>색상</th>
				<th>수량</th>
				<th>총 가격</th>
				<th>&nbsp;</th>
			</tr>
			<%
				for (int i = 0; i < orderDTOs.size(); i++) {
			%>
			<tr>
				<td></td>
				<td><%=itemDTOs.get(i).getItem_name()%></td>
				<td><%=itemDTOs.get(i).getItem_price()%>원</td>
				<td><%=itemDTOs.get(i).getItem_size()%></td>
				<td><%=itemDTOs.get(i).getItem_color()%></td>
				<td><%=orderDTOs.get(i).getOrder_qty()%></td>
				<td><%=itemDTOs.get(i).getItem_price() * orderDTOs.get(i).getOrder_qty()%>원</td>
				<%
					tot_price += itemDTOs.get(i).getItem_price() * orderDTOs.get(i).getOrder_qty();
				%>
				<td><a
					href="./customer_item/customer_itemManagement_viewItemDetailPage?item_no=<%=orderDTOs.get(i).getItem_no()%>'">
						<input type="button" value="상세보기" class="blue_button" onclick = "location.href='/shop/customer_item/customer_itemManagement_viewItemDetailPage?item_no=<c:out value=${item.item_no}'" />
				</a> <a href="./removeCart?item_no=<%=orderDTOs.get(i).getItem_no()%>">
						<input type="button" value="취소" class="blue_button" />
				</a></td>
			</tr>
			<%
				}
			%>
			<tr>
				<th colspan="2">총 가격</th>
				<th colspan="6"><%=tot_price%>원</th>
			</tr>
		</table>
		<br />
		<form action="./order" method="post">
			<input type="submit" value="전체상품 바로주문" class="blue_button"
				style="margin: 0 auto; margin-left: 400px" />
		</form>
	</div>
</body>
</html>