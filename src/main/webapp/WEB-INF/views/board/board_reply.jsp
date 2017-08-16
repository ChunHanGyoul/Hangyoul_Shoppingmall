<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("UTF-8"); %><% request.setCharacterEncoding("UTF-8"); %>
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
<title>한결처럼 쇼핑몰 Q&amp;A</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
</head>
<body>
	<div id="contentsArea" style = "margin : auto; margin-top : 100px; width : 60%;">
		<section id="titlename">
			<h1>답변 글 등록</h1>
			<p class="formSign">
				<strong class="require">필수</strong> 는 반드시 입력하여야 하는 항목입니다.
			</p>
			<form action="./BoardReply" method="post" id="joinForm"
				name="boardform" enctype="multipart/form-data">
				<fieldset>
					<input type="hidden" name="num"
						value="<c:out value="${boardDTO.num}"/>" /> <input type="hidden"
						name="answer_num" value="<c:out value="${boardDTO.answer_num}"/>" />
					<input type="hidden" name="answer_lev"
						value="<c:out value="${boardDTO.answer_lev}"/>" /> <input
						type="hidden" name="answer_seq"
						value="<c:out value="${boardDTO.answer_seq}"/>" />
					<legend>답변 글 </legend>
					<p>
						<label for="name">글쓴이 <strong class="require">필수</strong></label>
						<input type="text" id="name" name="name" required
							placeholder="홍길동">
					</p>
					<p>
						<label for="subject">제목</label> <input type="text" id="subject"
							name="subject" value="[답변]<c:out value="${boardDTO.subject}"/>">
					</p>
					<p>
						<label for="content">내용</label>
						<textarea name="content" cols="74" rows="15"></textarea>
					</p>
					<p>
						<label for="pass">비밀번호 <strong class="require">필수</strong></label>
						<input type="password" id="pass" name="pass" required
							placeholder="비밀번호입력" size="12" />
					</p>
					<div class="btnJoinArea">
						<button type="submit" class="btnOk">글 등록</button>
						<button type="reset" class="btnCancel">취소</button>
						<button type="button" value="button"
							onclick="location.href='./BoardMain'" class="btnOk">
							목록</button>
					</div>
				</fieldset>
			</form>
		</section>
	</div>
</body>
</html>