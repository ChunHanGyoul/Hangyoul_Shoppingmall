<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Testing websockets</title>
</head>
<body>
	<div style = "width : 50px; hegith : auto; margin-top : 200px; position: absolute;" >
	<fieldset>
		현재 닉네임 ↓ : <input id="nickname" type="text" /> <input type = "button" value = "설정" onclick = "seteNick()"/>
		<br/><br/>
		<textarea id="messageWindow" rows="10" cols="20" readonly="true"></textarea>
		<br /> <input id="inputMessage" type="text" /> <input type="submit"
			value="send" onclick="send()" />
	</fieldset>
	</div>
</body>
<script type="text/javascript">
	var result = Math.floor(Math.random() * 100000) + 1;
	var nickname = "손님"+result;
	document.getElementById("nickname").value = nickname;
	var textarea = document.getElementById("messageWindow");
	var webSocket = new WebSocket(
			'ws://localhost:8080/shop/broadcasting');
	var inputMessage = document.getElementById('inputMessage');
	webSocket.onerror = function(event) {
		onError(event)
	};
	webSocket.onopen = function(event) {
		onOpen(event)
	};
	webSocket.onmessage = function(event) {
		onMessage(event)
	};
	function onMessage(event) {
		textarea.value += event.data + "\n";
	}
	function onOpen(event) {
		textarea.value += "연결 성공\n";
	}
	function onError(event) {
		alert(event.data);
	}
	function send() {
		textarea.value += nickname + " : " + inputMessage.value + "\n";
		webSocket.send(nickname + " : " + inputMessage.value);
		inputMessage.value = "";
	}
	function seteNick() {
		nickname = document.getElementById("nickname").value;
		alert(nickname+"(으)로 설정되었습니다.");
	}
</script>
</html>