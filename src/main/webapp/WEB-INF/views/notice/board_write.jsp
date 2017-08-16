<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>공지사함</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
</head>
<body>
	<div id="contentsArea" style = "margin : auto; margin-top : 100px; width : 60%;">
		<section id="titlename">
			<h1>글쓰기</h1>
			<p class="formSign">
				<strong class="require">필수</strong> 는 반드시 입력하여야 하는 항목입니다.
			</p>
			<form action="./NoticeWrite" method="post" id="joinForm"
				name="boardform" enctype="multipart/form-data">
				<fieldset>
					<legend>글쓰기</legend>
					<p>
						<label for="name">글쓴이 <strong class="require">필수</strong></label>
						<input type="text" id="name" name="name" required
							placeholder="홍길동" />
					</p>
					<p>
						<label for="password">암호 <strong class="require">필수</strong></label>
						<input type="password" id="password" name="pass" required
							placeholder="영문/숫자 4~8자 이내" />
					</p>
					<p>
						<label for="subject">제목 <strong class="require">필수</strong></label>
						<input type="text" id="subject" name="subject" required
							placeholder="글의 제목을 입력하세요." />
					</p>
					<p>
						<label for="content">내용 <strong class="require">필수</strong></label>
						<textarea id="content" name="content" cols="74" rows="15" required
							placeholder="글의 내용을 입력하세요."> </textarea>
					</p>
					<div class="btnJoinArea">
						<button type="submit" class="btnOk">글 등록</button>
						<button type="reset" class="btnCancel">취소</button>
						<button type="button" value="button"
							onclick="location.href='./NoticeMain'" class="btnOk">목록
						</button>
					</div>
				</fieldset>
			</form>
		</section>
	</div>
</body>
</html>