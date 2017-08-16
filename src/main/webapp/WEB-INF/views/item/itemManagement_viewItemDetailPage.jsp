<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../basicBody4.jsp" flush="true"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이징 처리: 특정한 페이지를 보다가 조회 페이지로 이동</title>
<link rel="stylesheet" href="../resources/css/w3.css">
<link rel="stylesheet" href="../resources/css/basicBody1.css" type="text/css">
<script src="../resources/js/jquery-1.12.4.min.js"></script>
<script src="../resources/js/bagicLoginState.js"></script>
<script type="text/javascript" src="../resources/js/jquery-1.11.3.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script id="templateAttach" type="text/x-handlebars-template">
<li data-src='{{fullName}}'>
  <span class="mailbox-attachment-icon has-img"><img src="/shop{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
	<a href="/shop{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	</span>
  </div>
</li>                
</script>
<script type="text/javascript" src="../resources/js/upload.js"></script>
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
	
	var file = '${itemManagementDTO.item_image}';
	var formData = new FormData();
	formData.append("file", file);
	var template = Handlebars.compile($("#templateAttach").html());
  //화면에 이미지를 띄워주는 부분
	var fileInfo = getFileInfo(file);
	alert(fileInfo);
	var html = template(fileInfo);
	alert(html);
	$(".uploadedList").append(html);
	});
	//최종 결과: 목록 페이지 -> 조회 페이지 -> 목록 페이지로 가는 버튼 클릭을 통해서 확인
</script>
</head>
<body>
	<div id="contentsArea" style = "margin : auto; margin-top : 100px; width : 60%;">
 <form action="./itemManagement_viewItemDetailPage" method="post" role="form">
    <input type="hidden" name="item_no" value="${itemManagementDTO.item_no}" />
    <input type='hidden' name='page' value ="${cri.page}">
    <input type='hidden' name='perPageNum' value ="${cri.perPageNum}">
    <input type='hidden' name='searchType' value="${cri.searchType}">
	<input type='hidden' name='keyword' value="${cri.keyword}">
 </form>
 <div>
		<section>
			<div align="center">
				<h2>상품 상세 정보 화면</h2>
<!-- 				<input type="hidden" name="item_no" -->
<%-- 					value="<c:out value="${itemManagementDTO.item_no}" />"> --%>
				<fieldset>
					<legend>상품 기본 정보</legend>
					<table border="1">
						<tr>
							<td class="mailbox-attachments clearfix uploadedList"></td>
							<td>
								<ul>
									<li>상품명: ${itemManagementDTO.item_name}</li>
									<li>가격: ${itemManagementDTO.item_price}원</li>
									<li>상품상세설명: ${itemManagementDTO.item_expln}</li>
									<li>상품할인가: ${itemManagementDTO.item_discount}원</li>
								</ul>
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset>
					<legend>상품 추가 정보</legend>
					<table border="1">
						<tr>
							<td>
								<ul>
								   <li>상품소재: ${itemManagementDTO.item_material}</li>
									<li>사이즈: ${itemManagementDTO.item_size}</li>
									<li>색상: ${itemManagementDTO.item_color}</li>
									<li>수량: ${itemManagementDTO.item_qty}</li>
									<li>제조사: ${itemManagementDTO.item_mfr}</li>
									<li>제조국: ${itemManagementDTO.item_country}</li>
									<li>제조일자: ${itemManagementDTO.item_mdate}</li>
									<li>대분류번호: ${itemManagementDTO.category_no}</li>
									<li>소분류번호: ${itemManagementDTO.subclass_no}</li>
									<li>분류명: ${itemManagementDTO.category_name}</li>
								</ul>
							</td>

						</tr>
					</table>
					
					<div>
					 <button type="submit" class="btn-update">수정</button>
					 <button type="submit" class="btn-delete">삭제</button>
					 <button type="submit" class="btn-listAll">목록</button>
					</div>
				</fieldset>
			</div>
		</section>
	</div>
	</div>
</body>
</html>