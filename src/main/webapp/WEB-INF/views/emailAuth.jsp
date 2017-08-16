<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>이메일 인증</title>
<script src="resources/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	function check() {
		var form = document.authenform;
		var authNum = ${authNum};
		
		if(!form.authnum.value) {
			alert("인증번호를 입력하세요.");
			return false;
		}
		if(form.authnum.value!=authNum) {
			alert("틀린 인증번호입니다. 인증번호를 다시 입력해주세요.");
			form.authnum.value="";
			return false;
		}
		if(form.authnum.value == authNum) {
			alert("인증완료");
			window.opener.document.getElementById("mailCheck").value="인증완료";
			window.opener.document.getElementById("mb_email").readOnly = true;
			window.opener.document.getElementById("mb_email").style.backgroundColor = "#e2e2e2";
			self.close();
		}
	}
</script>
</head>
<body>
<center>
<br/><br/>
<h5>인증번호 7자리를 입력하세요.</h5>
<div class = "container">
	<form method = "post" name = "authenform">
		<input type = "text" name = "authnum"/><br/><br/>
		<input type = "button" class = "btn btn-info" value = "Submit" onclick = "check();"/>
	</form>
</div>
</center>

</body>
</html>