<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../../basicBody.jsp" flush="true"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<style type="text/css">
div.deginVar{
	position : absolute;
	top: 5cm;
	width: 70%;
	background-color: black;
	color: balck;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div class="deginVar"></div>
	<div style="margin-top: 100px; margin-left: 100px;">
		1. 주문하기
		<form action="./addCart" method="post">
			상품번호 : <input type="text" id="item_no" name="item_no"> <br />
			수량 : <input type="text" id="order_qty" name="order_qty"> <br />
			<input type="submit" value="주문하기" /> <input type="reset" value="취소하기" />
		</form>
		2. 장바구니 보기
		<form action="./viewCart" method="get">
			<input type="submit" value="장바구니 보기" />
		</form>
		3. 주문내역 보기
		<form action="./showDelivery" method="post">
			<input type="submit" value="주문내역 보기" />
		</form>
		4. (관리자) 주문내역 보기 및 수정
		<form action="./showAllDelivery" method="post">
			<input type="submit" value="(관리자)주문내역 보기" />
		</form>
	</div>
</body>
</html>