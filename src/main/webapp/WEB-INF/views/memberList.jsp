<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="basicBody2.jsp" flush="true"></jsp:include>
<style type="text/css">
div.testDiv{
	position: absolute;
	top: 5cm;
	left: 5cm;
}
</style>
<title>:: 회원관리 ::</title>
</head>
<body>
	<div class="testDiv">
		<table border="1px solid black">
			<th>mb_no</th>
			<th>mb_id</th>
			<th>mb_nickname</th>
			<th>mb_email</th>
			<th>mb_zipcode</th>
			<th>mb_address</th>
			<th>mb_name</th>
			<th>mb_sex</th>
			<th>mb_bday</th>
			<th>mb_grade</th>
	

		<c:forEach items="${list}" var="memberDTO">
			<tr>
				<td>${memberDTO.mb_no }</td>
				<td>${memberDTO.mb_id }</td>
				<td>${memberDTO.mb_nickname }</td>
				<td>${memberDTO.mb_email }</td>
				<td>${memberDTO.mb_zipcode }</td>
				<td>${memberDTO.mb_address }</td>
				<td>${memberDTO.mb_name }</td>
				<td>${memberDTO.mb_sex }</td>
				<td>${memberDTO.mb_bday }</td>
				<td>${memberDTO.mb_grade }</td>
			</tr>
		</c:forEach>
		</table>
		
		
		<form action="gradeMediate" method="post">
		<th>회원번호</th>
		<th>회원등급</th>
		<tr>
		<td><input type="text" name="mb_no" id="mb_no"></td>
		<td><input type="text" name="mb_grade" id="mb_grade"></td>
		</tr>
		<button>등급변경</button>
		</form>	
	</div>
	
</body>
</html>