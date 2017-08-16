var myIndex = 0;
carousel();

function carousel() {
	var i;
	var x = document.getElementsByClassName("mySlides");
	for (i = 0; i < x.length; i++) {
		x[i].style.display = "none";
	}
	myIndex++;
	if (myIndex > x.length) {
		myIndex = 1
	}
	x[myIndex - 1].style.display = "block";
	setTimeout(carousel, 2500);
};

function openRightMenu() {
	document.getElementById("rightMenu").style.display = "block";
};
function closeRightMenu() {
	document.getElementById("rightMenu").style.display = "none";
};

$(function() {
	var lastScrollTop = 0, delta = 15;
	$(window).scroll(function(event) {
		var st = $(this).scrollTop();

		if (Math.abs(lastScrollTop - st) <= delta)
			return; // 스크롤값을 받아서 리턴한다.
		if ((st > lastScrollTop) && (lastScrollTop > 0)) {
			// downscroll code
			$("#header").css("top", "8px"); // 스크롤을 내렸을때 #header의 CSS 속성중 top 값을
			// -50px로 변경한다.

		} else {
			// upscroll code
			$("#header").css("top", "8px"); // 스크롤을 올렸을때 #header의 CSS 속성중 top 값을
			// 0px로 변경한다.
		}
		lastScrollTop = st;
	});
});

function openRegist() {
	document.getElementById('id02').style.display = 'block';
};

// 아이디 중복여부 체크중 유효성 검사 후 form 넘기기
function idUniqueCheck() {
	// 아이디 입력여부 검사
	if (document.getElementById("mb_id_regist").value == "") {
		alert("아이디를 입력하지 않았습니다.");
		return false;
	}

	// 아이디 유효성 검사 (영문소문자, 숫자만 허용) (공백도 걸러냄)
	for (i = 0; i < document.getElementById("mb_id_regist").value.length; i++) {
		ch = document.getElementById("mb_id_regist").value.charAt(i)
		if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z')) {
			alert("아이디는 소문자, 숫자만 입력가능합니다.");
			document.getElementById("mb_id_regist").focus();
			document.getElementById("mb_id_regist").select();
			return false;
		}
	}

	// 아이디 길이 체크 (6~12자)
	if (document.getElementById("mb_id_regist").value.length < 6
			|| document.getElementById("mb_id_regist").value.length > 12) {
		alert("아이디를 6~12자까지 입력해주세요.");
		document.getElementById("mb_id_regist").focus();
		document.getElementById("mb_id_regist").select();
		return false;
	}
	var form1 = document.getElementById("idUniques");
	form1.submit();
}

// 닉네임 중복여부 검사 후 폼 실행
function NickUniqueCheck() {
	// 닉네임 길이 체크(3~12자 까지 허용)
	if (document.getElementById("mb_nickname_regist").value.length < 3
			|| document.getElementById("mb_nickname_regist").value.length > 12) {
		alert("닉네임을 3~12자까지 입력해주세요.");
		document.getElementById("mb_nickname_regist").focus();
		document.getElementById("mb_nickname_regist").select();
		return false;
	}
	var form2 = document.getElementById("nicknameUniques");
	form2.submit();

}

// 유효성 검사 후 submit
function checkValidate() {

	var sexRadio = document.getElementsByName("mb_sex");
	var checked_index = -1;
	var checked_value = "";
	for (var i = 0; i < sexRadio.length; i++) {
		if (sexRadio[i].checked) {
			checked_index = i;
			checked_value = sexRadio[i].value;
		}
	}

	// 아이디 중복검사 여부
	if (document.getElementById("memberCheck").value == "false") {
		alert("아이디 중복검사를 해주세요.");
		return false;
	}

	// 닉네임 중복검사 여부
	if (document.getElementById('nickCheck').value == "false") {
		alert("닉네임 중복검사를 해주세요.");
		return false;
	}

	// 비밀번호 입력여부 체크
	if (document.getElementById("mb_pw_regist").value == "") {
		alert("비밀번호를 입력하지 않았습니다.");
		document.getElementById("mb_pw_regist").focus();
		return false;
	}

	// 비밀번호 길이 체크(4~8자 까지 허용)
	if (document.getElementById("mb_pw_regist").value.length < 4
			|| document.getElementById("mb_pw_regist").value.length > 8) {
		alert("비밀번호를 4~8자까지 입력해주세요.");
		document.getElementById("mb_pw_regist").focus();
		document.getElementById("mb_pw_regist").select();
		return false;
	}

	// 비밀번호와 비밀번호 확인 일치여부 체크
	if (document.getElementById("mb_pw_regist").value != document
			.getElementById("mb_pw_reconfirm").value) {
		alert("비밀번호가 일치하지 않습니다");
		document.getElementById("mb_pw_regist").value = "";
		document.getElementById("mb_pw_reconfirm").value = "";
		document.getElementById("mb_pw_regist").focus();
		return false;
	}

	// 이메일 인증 체크
	if (document.getElementById("mailCheck").value == "인증불완료") {
		alert("이메일 인증을 완료해주세요.");
		return null;
	}

	// 우편번호 입력여부 검사
	if (document.getElementById("sample4_postcode").value == "") {
		alert("우편번호를 입력하지 않았습니다.");
		return false;
	}

	// 주소 입력여부 검사
	if (document.getElementById("mb_address").value == "") {
		alert("배송지 상세주소를 입력하지 않았습니다.");
		return false;
	}

	// 이름 입력여부 검사
	if (document.getElementById("mb_name").value == "") {
		alert("이름을 입력하지 않았습니다.");
		return false;
	}

	// 성별 체크 유무 검사
	if (checked_index == "-1") {
		alert("성별을 체크해 주세요");
		return false;
	}

	// 생년월일 입력여부 검사
	if (document.getElementById("mb_bday").value == "") {
		alert("생년월일을 입력하지 않았습니다.");
		return false;
	}

	// 생년월일 유효성 검사
	for (i = 0; i < document.getElementById("mb_bday").value.length; i++) {
		ch = document.getElementById("mb_bday").value.charAt(i);
		if (!(ch >= '0' && ch <= '9')) {
			alert("생년월일은 숫자만 입력해주세요.");
			document.getElementById("mb_bday").focus();
			document.getElementById("mb_bday").select();
			return false;
		}
	}

	// 생년월일 길이 검사
	if (document.getElementById("mb_bday").value.length != 8) {
		alert("생년월일의 형식을 지켜주세요. ex)19930606");
		document.getElementById("mb_bday").focus();
		document.getElementById("mb_bday").select();
		return false;
	}

	// 핸드폰 번호 입력여부 검사
	if (document.getElementById("mb_mobile").value == "") {
		alert("핸드폰 번호를 입력하지 않았습니다.");
		return false;
	}

	// 핸드폰 번호 유효성 검사
	if (document.getElementById("mb_mobile").value.length != 13) {
		alert("핸드폰 번호의 형식을 지켜주세요. ex)010-6681-2218");
		document.getElementById("mb_mobile").focus();
		document.getElementById("mb_mobile").select();
		return false;
	}
	for (i = 0; i < document.getElementById("mb_mobile").value.length; i++) {
		ch = document.getElementById("mb_mobile").value.charAt(i);
		if (i == 3 || i == 8) {
			if (ch != '-') {
				alert("핸드폰 번호의 형식을 지켜주세요. ex)010-6681-2218");
				document.getElementById("mb_mobile").focus();
				document.getElementById("mb_mobile").select();
				return false;
			}
		} else if (!(ch >= '0' && ch <= '9')) {
			alert("핸드폰 번호의 형식을 지켜주세요. ex)010-6681-2218");
			document.getElementById("mb_mobile").focus();
			document.getElementById("mb_mobile").select();
			return false;
		}
	}
	var $form = $('<form></form>');
	$form.attr('action', './insertMember');
	$form.attr('method', 'post');
	$form.appendTo('body');
	var mb_id = $("<input type = 'hidden' value = " + document.getElementById("mb_id_regist").value + " name = 'mb_id'>");
	var mb_pw = $("<input type = 'hidden' value = " + document.getElementById("mb_pw_regist").value + " name = 'mb_pw'>");
	var mb_nickname = $("<input type = 'hidden' value = " + document.getElementById("mb_nickname_regist").value + " name = 'mb_nickname'>");
	var mb_name = $("<input type = 'hidden' value = " + document.getElementById("mb_name").value + " name = 'mb_name'>");
	var mb_bday = $("<input type = 'hidden' value = " + document.getElementById("mb_bday").value + " name = 'mb_bday'>");
	var mb_sex = $("<input type = 'hidden' value = " + checked_value + " name = 'mb_sex'>");
	var mb_address = $("<input type = 'hidden' value = " + document.getElementById("mb_address").value + " name = 'mb_address'>");
	var mb_mobile = $("<input type = 'hidden' value = " + document.getElementById("mb_mobile").value + " name = 'mb_mobile'>");
	var mb_email = $("<input type = 'hidden' value = " + document.getElementById("mb_email").value + " name = 'mb_email'>");
	var mb_zipcode = $("<input type = 'hidden' value = " + document.getElementById("sample4_postcode").value + " name = 'mb_zipcode'>");
	var mb_grade = $("<input type = 'hidden' value = '1' name = 'mb_grade'>");
	
	$form.append(mb_id).append(mb_pw).append(mb_nickname).append(mb_name).append(mb_bday).append(mb_sex).append(mb_address).append(mb_mobile).append(mb_email).append(mb_zipcode).append(mb_grade);
	$form.submit();

}

$(document).ready(function() {

	var loginResult = document.getElementById('success').value;

	if (loginResult == 0) {

	}else if(loginResult == 1){
		document.getElementById('loginVar').style.display = 'none';
		document.getElementById('logOutVar').style.display = 'block';
		document.getElementById('loginbtn').style.display = 'none';
		document.getElementById('logOutbtn').style.display = 'block';
	}
	;
});

$(document).ready(function() {

	var logOutVar = document.getElementById('logOutVar');
	var formObj = $("form[id='loginStatus']");

	logOutVar.onclick = function() {
		formObj.attr("action", "logOut");
		formObj.submit();

	};

	var logOutbtn = document.getElementById('logOutbtn');
	var formObj1 = $("form[id='loginStatus1']");

	logOutbtn.onclick = function() {
		formObj1.attr("action", "logOut");
		formObj1.submit();

	};

});

// $(document).ready(function(){
//	
// var formObject = $("form[id='loginTry']");
// var loginbut = document.getElementById('loginbtnTest');
//	
// loginbut.onclick = function(){
// formObject.attr("method","post");
// formObject.submit();
// };
//	
// });
