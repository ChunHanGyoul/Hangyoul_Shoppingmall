<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="han.gyoul.shop.dto.ItemManagementDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/css/w3.css">
<link rel="stylesheet" href="resources/css/mainCss6.css" type="text/css">
<base target="_self" />
<style media="screen">
</style>
<meta charset="UTF-8">
<title>:: 메인페이지 ::</title>
<script type="text/javascript" src="./resources/js/jquery-1.11.3.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script type="text/javascript">
	var result = '${msg}';
	if (result == 'SUCCESS') {
		alert("처리가 완료되었습니다.");
	}
	
	$(".pagination li a").on("click", function(event){
		event.preventDefault();
		
		var targetPage = $(this).attr("href");
		
		var jobForm = $("#jobForm");
		jobForm.find("[name='page']").val(targetPage);
		jobForm.attr("action", "/customer_item/customer_itemManagement_viewItemDetailPage").attr("method", "get");
		jobForm.submit();
	});
</script>


<script type="text/javascript" src="./resources/js/upload.js"></script>

<script>
	$(document).ready(
		function() {
			$('#searchBtn').on("click", function(event) {
				self.location = "./item/itemManagement_list"
					+ '${pageMaker.makeQuery(1)}'
					+ "&searchType="
					+ $("select option:selected").val()
					+ "&keyword=" + $('#keywordInput').val();
			});
	});
</script>


<script type="text/javascript">
	function checkEmail() {
		var gsWin = window.open("about:blank", "winName");
		var form1 = document.getElementById("mailCheckForm");
		form1.action = "/shop/emailAuth?email=" + $(mb_email).val();
		form1.target = "winName";
		form1.submit();
	}
</script>
<style type="text/css">
/*  이미지에 관한 css
/*  작은 이미지 리스트에 대한 css를 적용함 */
#imgList img {
	width: 150px;
	height: 200px;
	border: 1px solid #aaa;
	margin: 2px;
}

#imgList {
	float: left;
	margin-top: 0cm;
	margin-left: -2cm;
}

#rownum {
	margin-top: 50cm;
}
</style>
</head>
<body>
	<input type="hidden" value="${memberDTO.mb_id}" id="mb_iddd" />
	<input type="hidden" value="${memberDTO.mb_nickname}" id="mb_idddd" />
	<input type="hidden" value="${idUnique}" id="idUniqueke" />
	<input type="hidden" value="${nickUnique}" id="nickUniqueke" />
	<input type="hidden" value="${success}" id="success" />
	<!--카테고리 헤더-->
	<header>
		<div id="header">
			<table
				style="position: absolute; left: 7.7cm; bottom: -8px; border-spacing: 100px 0px;">
				<th><a href="#" class="MenuLink">TOP</a></th>
				<th><a href="#" class="MenuLink">BOTTOM</a></th>
				<th><a href="#" class="MenuLink">OUTWEAR</a></th>
				<th><a href="#" class="MenuLink">DRESS</a></th>
			</table>
			<p style="position: absolute; left: 40px; top: -27px;">
				<a href="./mainPage" class="MenuLink"> <font
					style="font-family: 'Nanum Pen Script', cursive; font-size: 0.6cm;">패션은
						누구처럼?? </font> <font
					style="font-family: 'Nanum Pen Script', cursive; font-size: 0.8cm;">한결처럼~</font>
				</a>
			</p>
			<p style="position: absolute; left: 7.5cm; top: -24px;">ㅣㅣ</p>
			<p
				style="position: absolute; right: 12cm; top: -24px; cursor: pointer; display: block;"
				onclick="document.getElementById('id01').style.display='block'"
				id="loginVar">
				<font
					style="font-family: 'Nanum Pen Script', cursive; font-size: 0.8cm;">Login</font>
			</p>
			<form role="form" method="post" id="loginStatus">
				<p
					style="position: absolute; right: 12cm; top: -24px; cursor: pointer; display: none;"
					id="logOutVar" class="logOutVar">
					<font
						style="font-family: 'Nanum Pen Script', cursive; font-size: 0.8cm;">LogOut</font>
				</p>
			</form>
			<p
				style="position: absolute; right: 8cm; top: -24px; cursor: pointer;"
				onclick="openRegist()">
				<font
					style="font-family: 'Nanum Pen Script', cursive; font-size: 0.8cm;">회원가입</font>
			</p>
			<p style="position: absolute; right: 15cm; top: -24px;">ㅣㅣ</p>
		</div>
	</header>
	<!--카테고리 헤더-->
	<!--메인 페이지 테두리-->
	<div class="topRight"></div>
	<div class="topLeft"></div>
	<div class="RightVar"></div>
	<div class="LeftVar"></div>
	<!--메인 페이지 테두리-->
	<!--메인 페이지 가운데 폰트-->
	<div class="CenterView">
		<p>
			<font
				style="font-family: 'Nanum Pen Script', cursive; font-size: 1.5cm;">The
				<font style="color: #f6c1c1;">Style </font> Like one
			</font>
		</p>
	</div>
	<!--메인 페이지 가운데 폰트-->
	<!--main background-->
	<div class="mainImage"
		style="transform: rotate(-30deg); position: absolute; top: 3cm; left: 2cm;">
		<img src="<%=request.getContextPath()%>/resources/image/main.jpg">
	</div>
	<div class="mainImage2"
		style="transform: rotate(20deg); position: absolute; top: 15cm; left: 4cm;">
		<img src="<%=request.getContextPath()%>/resources/image/main2.jpg">
	</div>
	<div class="mainImage3"
		style="transform: rotate(20deg); position: absolute; top: 15cm; right: 4cm;">
		<img src="<%=request.getContextPath()%>/resources/image/main3.jpg">
	</div>
	<div class="mainImage4"
		style="transform: rotate(25deg); position: absolute; top: 3cm; right: 4cm;">
		<img src="<%=request.getContextPath()%>/resources/image/main4.jpg">
	</div>
	<div class="mainImage5"
		style="transform: rotate(20deg); position: absolute; top: 6cm; left: 12cm;">
		<img src="<%=request.getContextPath()%>/resources/image/main5.jpg">
	</div>
	<div class="mainImage6"
		style="transform: rotate(-20deg); position: absolute; top: 9.5cm; left: 12cm;">
		<img src="<%=request.getContextPath()%>/resources/image/main6.jpg"
			width="80%">
	</div>
	<div class="mainImage8"
		style="transform: rotate(5deg); position: absolute; top: 7cm; right: 14cm;">
		<img src="<%=request.getContextPath()%>/resources/image/main8.jpg"
			width="110%">
	</div>
	<div class="mainImage7"
		style="transform: rotate(10deg); position: absolute; top: 10cm; right: 18cm;">
		<img src="<%=request.getContextPath()%>/resources/image/main7.jpg"
			width="150%">
	</div>
	<div class="mainImage10">
		<img src="<%=request.getContextPath()%>/resources/image/main10.jpg">
	</div>
	<!--main background-->


	<!--메뉴버튼-->
	<div class="MenuBtnSide" id="rightMenu">
		<font
			style="font-family: 'Nanum Pen Script', cursive; font-size: 1cm; color: white; cursor: pointer;"
			onclick="closeRightMenu()">Menu 닫기</font></br> <a href="./myPage"
			class="MenuLink"><font
			style="font-family: 'Nanum Pen Script', cursive; font-size: 1cm; color: white;">마이페이지</font></a></br>
		<a href="./viewCart" class="MenuLink"><font
			style="font-family: 'Nanum Pen Script', cursive; font-size: 1cm; color: white;">장바구니</font></a></br>
		<a href="./showDelivery" class="MenuLink"><font
			style="font-family: 'Nanum Pen Script', cursive; font-size: 1cm; color: white;">배송상태확인</font></a></br>
		<a href="./BoardMain" class="MenuLink"><font
			style="font-family: 'Nanum Pen Script', cursive; font-size: 1cm; color: white;">Q&A</font></a></br>
		<a href="./NoticeMain" class="MenuLink"><font
			style="font-family: 'Nanum Pen Script', cursive; font-size: 1cm; color: white;">NOTICE</font></a></br>
		
	</div>
	<div class="MenuBtn">
		<img src="<%=request.getContextPath()%>/resources/image/menuBtn.png"
			alt="메뉴버튼" onclick="openRightMenu()" style="cursor: pointer;"><font
			style="font-family: 'Nanum Pen Script', cursive; font-size: 1cm; cursor: pointer;"
			onclick="openRightMenu()"> Menu</font>
		</button>
	</div>
	<!--메뉴버튼-->
	<!--리모컨-->
	<div class="ScrollTopBtn">
		<a href="#"><img
			src="<%=request.getContextPath()%>/resources/image/button.jpg" alt=""></a>
	</div>
	<div class="ScrollHomeBtn">
		<img src="<%=request.getContextPath()%>/resources/image/home.jpg"
			alt="" width="8%">
	</div>
	<div class="ScrollSerchBtn">
		<img src="<%=request.getContextPath()%>/resources/image/serch.jpg"
			alt="" width="8%">
	</div>
	<div class="ScrollDownBtn">
		<a href="#"><img
			src="<%=request.getContextPath()%>/resources/image/button2.jpg"
			alt=""></a>
	</div>
	<!--리모컨-->
	<!--로그인-->
	<div class="loginVar">
		<div class="w3-container">
			<button
				onclick="document.getElementById('id01').style.display='block'"
				class="loginbtn" id="loginbtn">
				<font
					style="font-family: 'Nanum Pen Script', cursive; font-size: 1.1cm;">로그인</font>
			</button>
			<form role="form" method="post" id="loginStatus1">
				<button class="logOutbtn" id="logOutbtn">
					<font
						style="font-family: 'Nanum Pen Script', cursive; font-size: 1.1cm;">로그아웃</font>
				</button>
			</form>

			<div id="id01" class="w3-modal">
				<div class="w3-modal-content w3-card-4 w3-animate-zoom"
					style="position: absolate; top: 3cm; width: 500px; height: 300px;">
					<!--모델 창-->
					<div class="w3-center">
						<br> <span
							onclick="document.getElementById('id01').style.display='none'"
							class="w3-button w3-xlarge w3-hover-red w3-display-topright"
							title="닫기">&times;</span>
						<!--오른쪽 상단 취소버튼-->

						<form class="w3-container" action="login" id="loginTry"
							method="post">
							<div class="w3-section">
								<label for="mb_id"><font
									style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm;"><p
											style="position: absolute; left: 0.5cm; top: 0.25cm;">아이디</p></font></label>
								<input type="text" placeholder="아이디" name="mb_id"
									id="mb_id_login" required> <label><font
									style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm;"><p
											style="position: absolute; left: 0.5cm; top: 2cm;">비밀번호</p></font></label> <input
									type="password" placeholder="비밀번호" name="mb_pw"
									id="mb_pw_login" required>
								<button type="submit" id="loginbtnTest"
									style="position: absolute; top: 5cm; width: 90%; height: 10%; left: 0.5cm; color: white; background-color: black; border: 0; outline: 0; cursor: pointer;">로그인</button>
								<input type="checkbox" checked="checked" id="mb_idSave">
								<font
									style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm;"><p
										style="position: absolute; left: 1.2cm; top: 5.1cm;">아이디
										저장</p></font>
							</div>
						</form>

						<div class="loginModalBottom">
							<span style="position: absolute; left: 9.2cm; bottom: 0.25cm;"><font
								style="font-family: 'Nanum Pen Script', cursive; font-size: 0.5cm;">아이디
									/ </font></span> <span
								style="position: absolute; left: 10.5cm; bottom: 0.25cm;"><font
								style="font-family: 'Nanum Pen Script', cursive; font-size: 0.5cm;">비밀번호
									찾기</font></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--로그인-->
	<!--회원가입-->
	<div class="RegistVar">
		<button onclick="openRegist()" class="registbtn">
			<font
				style="font-family: 'Nanum Pen Script', cursive; font-size: 1.1cm;">회원가입</font>
		</button>
		<div id="id02" class="w3-modal">
			<div class="w3-modal-content w3-card-4 w3-animate-zoom"
				style="position: absolate; top: 0cm; width: 500px; height: 98%;">
				<!--모델 창-->
				<div class="w3-center">
					<br> <span
						onclick="document.getElementById('id02').style.display='none'"
						class="w3-button w3-xlarge w3-hover-red w3-display-topright"
						title="닫기">&times;</span>
					<!--오른쪽 상단 취소버튼-->



					<div class="w3-section">
						<form id="idUniques" action="mainPage" method="post">
							<label for="mb_id"><font
								style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm;"><p
										style="position: absolute; left: 0.5cm; top: 0.8cm;">userid</p></font></label>
							<input type="text" placeholder="아이디" name="mb_id"
								id="mb_id_regist" required />
						</form>
						<button class="idBtn" onclick="idUniqueCheck()">아이디 중복체크</button>
						<input type="hidden" id="memberCheck" value="false" />

						<form id="nicknameUniques" action="mainPage" method="post">
							<label><font
								style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm;"><p
										style="position: absolute; left: 0.5cm; top: 5.3cm;">nickname</p></font></label>
							<input type="text" placeholder="닉네임" name="mb_nickname"
								id="mb_nickname_regist" required />
						</form>
						<button class="nickBtn" onclick="NickUniqueCheck()">닉네임
							중복체크</button>
						<input type="hidden" id="nickCheck" value="false" /> <label><font
							style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm;"><p
									style="position: absolute; left: 0.5cm; top: 2.3cm;">password</p></font></label>
						<input type="password" placeholder="비밀번호" name="mb_pw"
							id="mb_pw_regist" required> <label><font
							style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm;"><p
									style="position: absolute; left: 0.5cm; top: 3.8cm;">password
									reconfirm</p></font></label> <input type="password" placeholder="비밀번호 재확인"
							name="mb_pw_reconfirm" id="mb_pw_reconfirm" required> <label><font
							style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm;"><p
									style="position: absolute; left: 0.5cm; top: 6.8cm;">email</p></font></label>
						<input type="text" placeholder="이메일" name="mb_email" id="mb_email"
							required>
						<button class="mobileBtn" onclick="checkEmail();">본인인증</button>
						<form action="./shop/emailAuth" method="post" id="mailCheckForm">
						</form>
						<input type="hidden" id="mailCheck" name="mailCheck" value="인증불완료">
						<button
							style="position: absolute; bottom: 0.5cm; width: 90%; height: 5%; left: 0.5cm; color: white; background-color: black; border: 0; outline: 0; cursor: pointer;"
							onclick="checkValidate()">회원가입</button>

					</div>
					<div class="basicInfo">
						<label><font
							style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm;"><p
									style="position: absolute; left: 0cm; top: -0.5cm;">username</p></font></label>
						<input type="text" placeholder="이름" name="mb_name" id="mb_name"
							required> <label><font
							style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm;"><p
									style="position: absolute; left: 0cm; top: 1cm;">성별</p></font></label> <input
							type="radio" name="mb_sex" id="mb_sex_man" value="man" required><font
							style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm;"><p
								style="position: absolute; left: 0.4cm; top: 1.7cm;">남자</p></font> <input
							type="radio" name="mb_sex" id="mb_sex_woman" value="woman"><font
							style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm;"><p
								style="position: absolute; left: 2.2cm; top: 1.7cm;">여자</p></font> <label><font
							style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm;"><p
									style="position: absolute; left: 0cm; top: 2.5cm;">birthday</p></font></label>
						<input type="text" placeholder="생년월일(ex : 19940216)"
							name="mb_bday" id="mb_bday" required> <input
							type="hidden" name="mb_grade" id="mb_grade" value="1">
					</div>
					</form>
					<label><font
						style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm;"><p
								style="position: absolute; left: 0.5cm; bottom: 2.6cm;">mobile</p></font></label>
					<input type="text" placeholder="연락처(ex : 010-5366-2824)"
						name="mb_mobile" id="mb_mobile" required> <label><font
						style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm;"><p
								style="position: absolute; left: 0.5cm; top: 8.3cm;">address</p></font></label>
					<input type="text" id="sample4_postcode" name="mb_zipcode"
						placeholder="우편번호" readonly="true"
						onclick="sample4_execDaumPostcode()"> <input type="button"
						onclick="sample4_execDaumPostcode()" value="우편번호 찾기"
						style="position: absolute; top: 9.8cm; width: 25%; height: 3.45%; right: 6.4%; color: white; background-color: black; border: 0; outline: 0; cursor: pointer;"><br>
					<input type="text" placeholder="배송지 상세주소" name="mb_address"
						id="mb_address" required> <input type="hidden"
						id="sample4_roadAddress" placeholder="도로명주소"> <input
						type="hidden" id="sample4_jibunAddress" placeholder="지번주소">
					<span id="guide" style="color: #999"></span>

					<div class="registModalTop">
						<font
							style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm;"><p
								style="position: absolute; left: 0.5cm; top: 0cm;">회원정보</font>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!--회원가입-->


	<!--New & Best 상품-->
	<div class="NewVar"></div>
	<div class="NewLogo">
		<img src="<%=request.getContextPath()%>/resources/image/new.jpg"
			alt="" width="6%"
			style="transform: rotate(-20deg); position: absolute; left: 1cm; top: 28.2cm;">
	</div>
	<div class="w3-content w3-section"
		style="max-width: 500px; position: absolute; right: 28cm; top: 30.5cm;">
		<img src="<%=request.getContextPath()%>/resources/image/img_rr_04.jpg"
			class="mySlides w3-animate-right"> <img
			src="<%=request.getContextPath()%>/resources/image/img_rr_12.jpg"
			class="mySlides w3-animate-right">
	</div>
	<div class="Middle">
		<p>
			<font
				style="font-family: 'Nanum Pen Script', cursive; font-size: 1.5cm; position: absolute; left: 9cm; top: 1cm;">new
				& best info</font>
		</p>
		<img src="<%=request.getContextPath()%>/resources/image/info.jpg"
			style="position: absolute; left: 10.5cm; top: 4cm;"> <img
			src="<%=request.getContextPath()%>/resources/image/info2.jpg"
			style="position: absolute; left: 24cm; top: 4cm;"> <img
			src="<%=request.getContextPath()%>/resources/image/info3.jpg"
			style="position: absolute; left: 10.5cm; top: 12.69cm;">
		<div class="MiddleLine"></div>
	</div>


	<!-- itemList -->
	<div>
		<section class="content"
			style="position: absolute; left: 10.5cm; top: 85cm;">
			<div class="row">
				<!-- left column -->
				<div class="col-md-12">
					<!-- general form elements -->
					<div class='box'>
						<div class="box-header with-border">
							<h3 class="box-title">Item List</h3>
						</div>
						<div class='box-body'>
							<!-- 				<button id='newBtn'>상품등록</button> -->
							<!-- 					<button type="button" value="button" id="newBtn" -->
							<!-- 							onclick="location.href='/shop/item/itemManagement_register'">상품등록 -->
							<!-- 						</button> -->
							<!-- 					<button type="button" value="button" -->
							<!-- 							onclick="location.href='#'">분류관리 -->
							<!-- 						</button><br/> -->

							<select name="searchType">
								<option value="n"
									<c:out value="${cri.searchType == null?'selected':''}"/>>
									---</option>
								<option value="t"
									<c:out value="${cri.searchType eq 't'?'selected':''}"/>>
									item_name</option>
								<option value="c"
									<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
									category_name</option>
								<option value="p"
									<c:out value="${cri.searchType eq 'p'?'selected':''}"/>>
									item_price</option>
								<option value="tc"
									<c:out value="${cri.searchType eq 'tc'?'selected':''}"/>>
									item_name OR category_name</option>
								<option value="tp"
									<c:out value="${cri.searchType eq 'cp'?'selected':''}"/>>
									item_name OR item_price</option>
								<option value="tcp"
									<c:out value="${cri.searchType eq 'tcp'?'selected':''}"/>>
									item_name OR category_name OR item_price</option>
							</select>
							<!-- keyword는 input type으로 처리해줌 -->
							<input type="text" name='keyword' id="keywordInput"
								value='${cri.keyword}'>
							<button id="searchBtn" type="submit">Search</button>

						</div>
					</div>
					<div class="box">
						<div class="box-header with-border">
							<h3 class="box-title">LIST PAGING</h3>
							<!-- 페이지 정보를 유지할 수 있또록 변경됨: page와 perPageNum과 연동 -->
						</div>
						<div class="box-body">
							<!-- 					<table class="table table-bordered" border="1"> -->
							<!-- 						<tr> -->
							<!-- 							<th>CATEGORY_NAME</th> -->
							<!--                             <th>ITEM_NO</th> -->
							<!-- 						    <th>ITEM_NAME</th> -->
							<!-- 						    <th>ITEM_PRICE</th> -->
							<!-- 						    <th>ITEM_DISCOUNT</th> -->
							<!-- 						</tr> -->

							<%-- 						<c:forEach items="${list}" var="itemManagementDTO"> --%>
							<!-- 							<tr> -->
							<%-- 								<td>${itemManagementDTO.category_name}</td> --%>
							<%--        							<td>${itemManagementDTO.item_no}</td> --%>
							<%--       							 <td><a href='./customer_item/customer_itemManagement_viewItemDetailPage?item_no=${itemManagementDTO.item_no}'> --%>
							<%--       							 ${itemManagementDTO.item_name}</a></td> --%>
							<%--       							 <td>${itemManagementDTO.item_price}</td> --%>
							<%--       							 <td>${itemManagementDTO.item_discount}</td> --%>
							<!-- 							</tr> -->
							<%-- 						</c:forEach> --%>
							<!-- 					</table> -->

							<!-- 이미지로 띄워주게 하는 부분 -->
							<table id="imgList">
								<%
									int count = 0;
									int tot = ((List) (request.getAttribute("itemList"))).size();
									System.out.println(tot);
								%>
								<tr>
									<c:forEach var="item" items="${itemList}" varStatus="status">
										<!--   <td id="item" align="center" class="mailbox-attachments clearfix uploadedList" -->
										<%--    id="img" onclick="location.href='/shop/customer_item/customer_itemManagement_viewItemDetailPage?item_no=<c:out value="${item.item_no}" />'"> --%>
										<!--    <br/>  -->
										<td id="item" align="center"><img
											src="http://localhost/shop/displayFile?fileName=${item.item_image}"
											alt="이미지" id="img"
											onclick="location.href='/shop/customer_item/customer_itemManagement_viewItemDetailPage?item_no=<c:out value="${item.item_no}" />'" />
											<br /> <%--    <c:out value="${item.item_image}"/> --%> <c:out
												value="${item.item_name}" /><br /> <c:out
												value="${item.item_price}" />원</td>
										<%
											count++;
												if (count % 3 == 0 && count != tot) {
													System.out.println(count);
													out.write("</tr><tr>");
												}
										%>
									</c:forEach>
								</tr>
							</table>
						</div>
						<!-- /.box-body -->
						<div class="box-footer">
							<!-- 페이지를 보여주는 부분 -->
							<div class="text-center">
								<ul class="pagination">
									<!-- 페이징 처리 부분을 아래와 같이 문자열이 적용될 수 있도록 작성함 -->
									<c:if test="${pageMaker.prev}">
										<li><a
											href="./item/itemManagement_list${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
									</c:if>

									<c:forEach begin="${pageMaker.startPage }"
										end="${pageMaker.endPage }" var="idx">
										<li
											<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
											<a
											href="./item/itemManagement_list${pageMaker.makeSearch(idx)}">${idx}</a>
										</li>
									</c:forEach>

									<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
										<li><a
											href="./item/itemManagement_list${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
									</c:if>
								</ul>
							</div>

						</div>
						<!-- /.box-footer-->
					</div>
				</div>
				<!--/.col (left) -->

			</div>
			<!-- /.row -->
		</section>
		<!-- /.content -->

		<!-- Javascript를 이용한 처리: 페이지 번호를 클릭했을 때 이벤트 제어 -->
		<!-- 1> <form> 태그 처리 -->
		<form id="jobForm">
			<input type="hidden" name="page" value="${pageMaker.cri.perPageNum}" />
			<input type="hidden" name="perPageNum"
				value="${pageMaker.cri.perPageNum}" />
		</form>
	</div>

	<!--기업정보-->
	<div class="BottomVar"></div>
	<p>
		<font
			style="font-family: 'Nanum Pen Script', cursive; font-size: 2cm; position: absolute; left: 19cm; top: 105cm;">Han
			gyoul</font>
	</p>
	<p>
		<font
			style="font-family: 'Nanum Pen Script', cursive; font-size: 0.7cm; position: absolute; left: 18.3cm; top: 108cm;">you
			want passion in grade of top </font>
	</p>
	<div class="BottomInfo">
		<table style="border-spacing: 4cm 5px;">
			<tr style="text-align: left;">
				<th>GUIDE</th>
				<th>CUSTOMER SERVICE</th>
				<th>BANK INFO</th>
				<th>DELIVERY</th>
			</tr>
			<font style="color: #e4e4e4">
				<tr>
					<td>회사소개</td>
					<td>공지사항</td>
					<td>예금주 : 한결처럼(주)</td>
					<td>반품주소 : 인천시 산곡동 푸르지오</td>
				</tr>
				<tr>
					<td>개인정보처리방침</td>
					<td>이벤트</td>
					<td>국민은행 0000-0000000-0000</td>
					<td>반품요청 : 우체국 택배 (1588-1300)</td>
				</tr>
				<tr>
					<td>이용약관</td>
					<td>포토리뷰</td>
					<td>기업은행 000-00000-000000</td>
					<td>배송조회</td>
				</tr>
				<tr>
					<td>인재채용</td>
					<td>배송조회</td>
					<td>농협 0000-0000-00-000000</td>
				</tr>
				<tr>
					<td>도매/제휴 문의</td>
					<td>출석체크</td>
					<td>우리은행 0000-00000-000000</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td>신한은행 0000-000-00-000000</td>
				</tr>
			</font>
		</table>
	</div>
	<div class="Bottom"></div>
	<div class="BottomInfomation">
		<font style="color: white"><p
				style="position: absolute; left: 7.7cm;">㉿ 이 콘텐츠는 콘텐츠 산업 진흥법에 따라
				최초 제작일로부터 5년간 보호됩니다.</p></font>
		<table class="InfoTable"
			style="position: absolute; left: 7.7cm; top: 1.3cm; border-spacing: 20px 0px;">
			<tr>
				<td>회사명 : 한결처럼(주)</td>
				<td>대표이사 : 최아름</td>
				<td>사업자등록번호 : 000-00-00000</td>
				<td>통신판매업신고 : 0000-부평-0000</td>
			</tr>
			<tr>
				<td>주소 : (우)02-511 인천시 부평구 경인교대로 000(작전동, 건물 5층)</td>
				<td>전화 : 1588-1588</td>
				<td>팩스 : 032-1588-1588</td>
			</tr>
			<tr>
				<td>개인정보관리책임자 : 형아형님</td>
				<td>이메일 : 1급 기밀</td>
				<td>제휴문의 : 선미찡에게</td>
			</tr>
			<tr>
				<td colspan="4">고객님은 안전한 거래를 위해 현금등으로 결제시 저희 쇼핑몰에서 가입한 LG유플러스
					구매안전서비스를 이용하실 수 있습니다.</td>
			</tr>
			<tr>
				<td colspan="4">COPYRIGHT (c) 2017 BY MUTNAM.COM ALL RIGHTS
					RESERVED. design by 석인이형</td>
			</tr>
		</table>
	</div>
	<!--기업 정보-->
</body>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script
	src="http://dmaps.daum.net/map_js_init/postcode.v2.js?autoload=false"></script>
<script src="resources/js/jquery-1.12.4.min.js"></script>
<script src="resources/js/loginState5.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	 
	var formObj = $("form[role='form']");
	 //formObj는 아래에 선언된 <form> 태그를 의미하게 됨
	console.log(formObj);
	
	 //조회 페이지에서 수정 : 조회 페이지로 이동 -> 수정 버튼 클릭 -> 수정 화면 -> 작업 완료 후 원래의 목록 페이지로 이동
	 //조회 페이지-> SearchItemManagementController -> 수정 페이지 -> SearchItemManagementController -> 조회 페이지
	$(".btn-update").on("click", function(){
	formObj.attr("action", "/shop/item/itemManagement_modifyPage");
	formObj.attr("method", "get");		
	formObj.submit();
	});
	
	//조회 페이지에서 삭제 -> SearchItemManagementController -> 목록 페이지: 삭제 후 다시 원래의 목록 페이지로 이동
	$(".btn-delete").on("click", function(){
	formObj.attr("action", "/shop/item/itemManagement_deletePage");
	formObj.submit();
	});
	
	$(".btn-listAll").on("click", function(){
		formObj.attr("method", "get");
		formObj.attr("action", "/shop/item/itemManagement_list");
		formObj.submit();
	});
	
// 	var file = '${itemManagementDTO.item_image}';
// 	alert(file);
// 	var formData = new FormData();
// 	formData.append("file", file);
// 	var template = Handlebars.compile($("#templateAttach").html());
//   //화면에 이미지를 띄워주는 부분
// 	var fileInfo = getFileInfo(file);
// 	//alert(fileInfo);
// 	var html = template(fileInfo);
// 	alert(html);
// 	$(".uploadedList").append(html);
// 	});
	//최종 결과: 목록 페이지 -> 조회 페이지 -> 목록 페이지로 가는 버튼 클릭을 통해서 확인
</script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {

						// 						var testa = document.getElementById('idUniqueke').value;
						var testa = ${idUnique};
						if (testa == "2" || testa == "-2") {
							if (testa == "2") {
								document.getElementById('id02').style.display = 'block';
								document.getElementById('mb_id_regist').value = "${memberDTO.mb_id}";
								document.getElementById('memberCheck').value = "true";
								document.getElementById('mb_id_regist').readOnly = "true";
								document.getElementById('mb_id_regist').style.backgroundColor = "#e2e2e2";
							} else if (testa == "-2") {
								document.getElementById('id02').style.display = 'block';
								document.getElementById('mb_id_regist').value = "사용불가능한 아이디 입니다.";
							}

						}
						;

					});

	$(document)
			.ready(
					function() {

						var testa2 = document.getElementById('nickUniqueke').value;

						if (testa2 == "3" || testa2 == "-3") {
							if (testa2 == "3") {

								document.getElementById('id02').style.display = 'block';
								document.getElementById('mb_nickname_regist').value = "${memberDTO.mb_nickname}";
								document.getElementById('nickCheck').value = "true";
								document.getElementById('mb_nickname_regist').readOnly = "true";
								document.getElementById('mb_nickname_regist').style.backgroundColor = "#e2e2e2";

							} else if (testa2 == "-3") {
								document.getElementById('id02').style.display = 'block';
								document.getElementById('mb_nickname_regist').value = "사용불가능한 닉네임입니다.";
							}

						}
						;

					});

	$(document).ready(function() {

		var testApi = document.getElementById("zipBtn");
		testApi.onclick = function() {
			daum.postcode.load(function() {
				new daum.Postcode({
					oncomplete : function(data) {
					}
				}).open();
			});
		};
	});

	function sample4_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 도로명 조합형 주소 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}
						// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
						if (fullRoadAddr !== '') {
							fullRoadAddr += extraRoadAddr;
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample4_postcode').value = data.zonecode; //5자리 새우편번호 사용
						document.getElementById('mb_address').value = fullRoadAddr;
						document.getElementById('sample4_roadAddress').value = fullRoadAddr;
						document.getElementById('sample4_jibunAddress').value = data.jibunAddress;

						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							//예상되는 도로명 주소에 조합형 주소를 추가한다.
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							document.getElementById('guide').innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';

						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							document.getElementById('guide').innerHTML = '(예상 지번 주소 : '
									+ expJibunAddr + ')';

						} else {
							document.getElementById('guide').innerHTML = '';
						}
					}
				}).open();
	};
</script>
<!-- <script id="templateAttach" type="text/x-handlebars-template"> -->
<!-- <li data-src='{{fullName}}'> -->
<!--   <span class="mailbox-attachment-icon has-img"><img src="/shop{{imgsrc}}" alt="Attachment"></span> -->
<!--   <div class="mailbox-attachment-info"> -->
<!-- 	<a href="/shop{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a> -->
<!-- 	</span> -->
<!--   </div> -->
<!-- </li>                 -->
<!-- </script> -->
</html>
