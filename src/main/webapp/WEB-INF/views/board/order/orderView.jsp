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
<script type="text/javascript" src="./js/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>
<script type="text/javascript">
function payOrder() {
	var IMP = window.IMP; // 생략가능
	IMP.init('imp38770058');
	var method = document.getElementById("pay_method").value;
	if(method == "bank") {
		if(document.getElementById('bank_acct1').value == "" || document.getElementById('bank_sender1').value == "") {
			alert("계좌번호와 예금주 성명을 적어주세요.")
		} else {
			document.getElementById('payForm').submit();
		}
		return null;
	} 
	else {
		IMP.request_pay({
		    pg : 'html5_inicis', // version 1.1.0부터 지원.
		    pay_method : method,
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : '한결단결:결제테스트',
		    amount : 100,
		    buyer_email : document.getElementById("receiver_email").value,
		    buyer_name : document.getElementById("receiver_name").value,
		    buyer_tel : document.getElementById("receiver_mobile").value,
		    buyer_addr : document.getElementById("receiver_address").value,
		    buyer_postcode : document.getElementById("receiver_zipcode").value,
		    m_redirect_url : 'https://www.yourdomain.com/payments/complete'
		}, function(rsp) {
		    if ( rsp.success ) {
		        var msg = '결제가 완료되었습니다.';
		        msg += '고유ID : ' + rsp.imp_uid;
			    msg += '상점 거래ID : ' + rsp.merchant_uid;
	    	    msg += '결제 금액 : ' + rsp.paid_amount;
	    	    msg += '카드 승인번호 : ' + rsp.apply_num;
	    	    alert(msg);
	    	    document.getElementById('payForm').submit();
	    	} else {
	    	    var msg = '결제에 실패하였습니다.';
	    	    msg += '에러내용 : ' + rsp.error_msg;
	    	    msg += '\n해당에러가 계속되면 관리자에게 문의해주세요.';
	    	    alert(msg);
	    	    location.href = "./order";
	    	}
	    
		});
	}
}
function MemberCheck() {
	if(document.getElementsByName("memberCheck")[0].checked) {
		document.getElementById("receiver_zipcode").value = <%=memberDTO.getMb_zipcode()%>;
		document.getElementById("receiver_zipcode").readOnly = true;
		document.getElementById("receiver_address").value = "<%=memberDTO.getMb_address()%>";
		document.getElementById("receiver_address").readOnly = "true";
		document.getElementById("receiver_name").value = "<%=memberDTO.getMb_name()%>";
		document.getElementById("receiver_name").readOnly = true;
		document.getElementById("receiver_email").value = "<%=memberDTO.getMb_email()%>";
		document.getElementById("receiver_email").readOnly = true;
		document.getElementById("receiver_mobile").value = "<%=memberDTO.getMb_mobile()%>";
			document.getElementById("receiver_mobile").readOnly = true;
		} else {
			document.getElementById("receiver_zipcode").value = "";
			document.getElementById("receiver_address").value = "";
			document.getElementById("receiver_name").value = "";
			document.getElementById("receiver_email").value = "";
			document.getElementById("receiver_mobile").value = "";
			document.getElementById("receiver_zipcode").readOnly = false;
			document.getElementById("receiver_address").readOnly = false;
			document.getElementById("receiver_name").readOnly = false;
			document.getElementById("receiver_email").readOnly = false;
			document.getElementById("receiver_mobile").readOnly = false;
		}
	}
	function selectCheck() {
		var method = document.getElementById("pay_method").value;
		if (method == "bank") {
			document.getElementById("bank_sender1").required = "required";
			document.getElementById("bank_sender1").readOnly = false;
			document.getElementById("bank_sender1").style = "background-color: #ffffff;";
			document.getElementById("bank_acct1").required = "required";
			document.getElementById("bank_acct1").style = "background-color: #ffffff;";
			document.getElementById("bank_acct1").readOnly = false;
		} else {
			document.getElementById("bank_sender1").required = "";
			document.getElementById("bank_sender1").style = "background-color: #e2e2e2;";
			document.getElementById("bank_sender1").readOnly = true;
			document.getElementById("bank_acct1").required = "";
			document.getElementById("bank_acct1").style = "background-color: #e2e2e2;";
			document.getElementById("bank_acct1").readOnly = true;
		}
	}
</script>
</head>
<body>
	<div style="margin-left: 300px; margin-right: 300px; margin-top: 150px; z-index : 10;">
		<h1 style="text-align:center;">ORDER VIEW</h1>
		<form action="./orderComplete" method="post" id="payForm">
			<table class = "blue_table" style="margin: 0 auto;">
				<tr>
					<th>이미지</th>
					<th>상품명</th>
					<th>가격</th>
					<th>사이즈</th>
					<th>색상</th>
					<th>수량</th>
					<th>총 가격</th>
				</tr>
				<%
					for (int i = 0; i < orderDTOs.size(); i++) {
				%>
				<tr>
					<td><img src = "http://localhost/shop/displayFile?fileName=<%=itemDTOs.get(i).getItem_image()%>" alt = "이미지" id = "img" onclick = "location.href = '/shop/customer_item/customer_itemManagement_viewItemDetailPage?item_no=<c:out value="${item.item_no}"'"></td>
					<td><%=itemDTOs.get(i).getItem_name()%></td>
					<td><%=itemDTOs.get(i).getItem_price()%>원</td>
					<td><%=orderDTOs.get(i).getItem_size()%></td>
					<td><%=orderDTOs.get(i).getItem_color()%></td>
					<td><%=orderDTOs.get(i).getOrder_qty()%></td>
					<td><%=itemDTOs.get(i).getItem_price() * orderDTOs.get(i).getOrder_qty()%>원</td>
					<%
						tot_price += itemDTOs.get(i).getItem_price() * orderDTOs.get(i).getOrder_qty();
					%>
				</tr>
				<%
					}
				%>
				<tr>
					<td colspan = "2">총 결제 금액</td>
					<td colspan = "5"><%=tot_price%>원</td>
				</tr>
			</table>
			<div style="margin: 0 auto; width : 600px">
			<br /> ※회원정보와 동일 <input type="checkbox" name="memberCheck"
				onclick="MemberCheck()" />
			<fieldset style="width: 150px;">
				<legend> 배송지 정보</legend>
				---- 배송지 주소 ----<br /> 우편번호 : <input type="text"
					id="receiver_zipcode" name="receiver_zipcode" required /><br />
				상세주소 : <input type="text" id="receiver_address"
					name="receiver_address" size="60" required /><br />
				-----------------------<br /> 수취인 이름 : <input type="text"
					id="receiver_name" name="receiver_name" required /><br /> 구매자
				E-mail : <input type="text" id="receiver_email"
					name="receiver_email" required /><br /> 수취인 휴대폰 (-없이): <input
					type="text" id="receiver_mobile" name="receiver_mobile" required /><br />
				배송시 요청사항 : <input type="text" id="receiver_memo"
					name="receiver_memo" /><br />
			</fieldset>
			<fieldset style="width: 550px;">
				<legend>결재 정보</legend>
				결제 방법 : <select name="pay_method" id="pay_method"
					onchange="selectCheck()">
					<option>선택하세요</option>
					<option value="card">카드 결제</option>
					<option value="bank">무통장 입금</option>
					<option value="phone">핸드폰 결제</option>
				</select><br /> 계좌번호 : <input type="text" name="bank_acct1" id="bank_acct1"><br />
				송금자 : <input type="text" name="bank_sender1" id="bank_sender1" /><br />
			</fieldset>
			<br /> <input type="button" onclick="payOrder()" value="결제하기" class = "blue_button"/> <input
				type="submit" value="테스트" class = "blue_button"/>
			</div>
		</form>
	</div>
</body>
</html>