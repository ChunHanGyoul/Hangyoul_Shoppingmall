<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%><% request.setCharacterEncoding("UTF-8"); %>
<%@ page import = "han.gyoul.shop.member.dto.*" %>
<%MemberDTO memberDTO = (MemberDTO)request.getAttribute("memberDTO"); %>
<%if(memberDTO.getMb_grade().equals("1")) { %>
<jsp:include page="../basicBody.jsp" flush="true"></jsp:include>
<%} else { %>
<jsp:include page="../basicBody2.jsp" flush="true"></jsp:include>
<%} %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한결처럼 쇼핑몰 공지사항</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<script type="text/javascript" src="./js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<div id="contentsArea" style = "margin : auto; margin-top : 100px; width : 60%;">
		<section id="titlename">
			<h1>공지 내용</h1>
			<p class="formSign">공지 내용 입니다</p>
			<div id="joinForm">
				<input type="hidden" name="num" id="num"
					value="<c:out value="${noticeDTO.num }" />">
				<fieldset>
					<legend>공지 내용</legend>
					<p>
						<label for="name">이름 </label> <br />
						<c:out value="${noticeDTO.name }" />
					</p>
					<p>
						<label for="subject">제목 </label> <br />
						<c:out value="${noticeDTO.subject }" />
					</p>
					<p>
						<label for="content">내용</label> <br />
						<c:out value="${noticeDTO.content}" />
					</p>
					<div class="btnJoinArea">
<%-- 						<a href="./BoardReply?num=<c:out value="${noticeDTO.num}"/>"> --%>
<!-- 							<button type="button" class="btnOk">답변</button> -->
						</a> <a href="./NoticeModify?num=<c:out value="${noticeDTO.num}"/>">
							<button type="button" class="btnOk">수정</button>
						</a> <a href="./NoticeDelete?num=<c:out value="${noticeDTO.num}"/>">
							<button type="button" class="btnOk">삭제</button>
						</a>
						<button type="button" value="button"
							onclick="location.href='./NoticeMain'" class="btnOk">목록</button>
					</div>
				</fieldset>
			</div>
		</section>
	</div>
</body>
</html>