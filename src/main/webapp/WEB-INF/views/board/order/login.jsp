<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../../basicBody.jsp" flush="true"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div style = "margin-top : 100px; margin-left : 100px; z-index : 10">
		MemberDTO 정보를 세션에 저장하기 위해 임시로 설정해준 페이지 입니다!
		<form action="./login1" method="post">
			mb_no : <input type="text" id="mb_no" name="mb_no"> <br /> <input
				type="submit" value="로그인" />
		</form>
	</div>
</body>
</html>