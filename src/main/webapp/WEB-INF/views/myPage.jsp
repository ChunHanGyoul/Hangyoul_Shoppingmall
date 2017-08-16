<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="basicBody.jsp" flush="true"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/memberModify1.css" type="text/css">
<title>:: 마이페이지 ::</title>
</head>
<body>
<input type="hidden" value="${success}" id="success"/>
	<p style="position: absolute; left: 15cm; top: 3cm;"><font style="font-family:'Nanum Pen Script', cursive; font-size:1.5cm;">회원정보</font></p>
	<div>
		<form id="myPageForm">
			<div class="information">
			
				<label for="mb_id">
					<p><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;">아이디</font>
					&nbsp;&nbsp;${memberDTO.mb_id}
					</p>
				</label>
				
				<label for="mb_pw">
					<p><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;">비밀번호</font>
					<input type="text" id="mb_pw" name="mb_pw" value="${memberDTO.mb_pw}" required/>
					</p>
				</label>
				
				<label for="mb_nickname">
					<p><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;">닉네임</font>
					&nbsp;&nbsp;${memberDTO.mb_nickname}
					</p>
				</label>
				
				<label for="mb_email">
					<p><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;">이메일</font>
					<input type="text" id="mb_email" name="mb_email" value="${memberDTO.mb_email}" required/>
					</p>
				</label>
				
				<label>
					<p><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;">주소</font>
					<input type="text" id="mb_zipcode" name="mb_zipcode" value="${memberDTO.mb_zipcode}" required/>
					<input type="text" id="mb_address" name="mb_address" value="${memberDTO.mb_address}" required/>
					</p>
				</label>
				
				<label for="mb_username">
					<p><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;">성명</font>
					<input type="text" id="mb_name" name="mb_name" value="${memberDTO.mb_name}" required/>
					</p>
				</label>
				
				<label for="mb_sex">
					<p><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;">성별</font>
					<input type="radio" name="mb_sex" id="mb_sex_man" value="man" required>남자
					<input type="radio" name="mb_sex" id="mb_sex_woman" value="woman" required>여자
					</p>
				</label>
				
				<label for="mb_bday">
					<p><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;">생일</font>
					<input type="text" value="${memberDTO.mb_bday}" id="mb_bday" readonly>
					</p>
				</label>
				
				<label for="mb_mobile">
					<p><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;">연락처</font>
					<input type="text" id="mb_mobile" name="mb_mobile" value="${memberDTO.mb_mobile}" required/>
					</p>
				</label>
			
			 	<button class="modifyBtn">정보수정</button>
			 	<button class="removeBtn">회원탈퇴</button>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		var myPageF = $("form[id='myPageForm']")
		
		$(".modifyBtn").on("click", function(){
			myPageF.attr("action", "/memberModify");
			myPageF.attr("method", "post");
			myPageF.submit();
		});
		
		$(".removeBtn").on("click", function(){
			myPageF.attr("action", "/memberRemove");
			myPageF.attr("method", "post");
			myPageF.submit();
		});
		
	});
</script>
</html>