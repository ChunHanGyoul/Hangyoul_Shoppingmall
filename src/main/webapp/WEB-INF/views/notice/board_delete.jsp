<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% request.setCharacterEncoding("UTF-8"); %><% request.setCharacterEncoding("UTF-8"); %>
<%@ page import = "han.gyoul.shop.member.dto.*" %>
<%MemberDTO memberDTO = (MemberDTO)request.getAttribute("memberDTO"); %>
<%if(memberDTO.getMb_grade().equals("1")) { %>
<jsp:include page="../basicBody.jsp" flush="true"></jsp:include>
<%} else { %>
<jsp:include page="../basicBody2.jsp" flush="true"></jsp:include>
<%} %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>한결처럼 쇼핑몰 공지사항</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
</head>
<body>
	<div id="contentsArea" style = "margin : auto; margin-top : 100px; width : 60%;">
		<section id="titlename">
			<h1>글 삭제</h1>
			<p class="formSign">
				<strong class="require">필수</strong> 는 반드시 입력하여야 하는 항목입니다.
			</p>
			<form id="joinForm" name="deleteForm"
				action="./BoardDelete?num=${param.num}" method="post">
				<fieldset>
					<legend>글 삭제</legend>
					<p>
						<label for="password">글 입력 비밀번호 <strong class="require">필수</strong></label>
						<input type="password" id="password" name="pass" required
							placeholder="비밀번호 입력" size="12" />
					</p>
					<div class="btnJoinArea">
						<button type="submit" class="btnOk">삭제</button>
						<button type="reset" class="btnCancel">취소</button>
						<button type="button" value="button"
							onclick="location.href='./NoticeMain'" class="btnOk">
							목록</button>
					</div>
				</fieldset>
			</form>
		</section>
	</div>
</body>
</html>