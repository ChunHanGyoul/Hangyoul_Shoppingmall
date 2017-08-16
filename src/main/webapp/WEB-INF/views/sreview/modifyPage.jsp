<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../basicBody3.jsp" flush="true"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/w3.css">
<link rel="stylesheet" href="../resources/css/basicBody1.css" type="text/css">
<script src="../resources/js/jquery-1.12.4.min.js"></script>
<script src="../resources/js/bagicLoginState.js"></script>
<script type="text/javascript" src="../resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var formObj = $("form[role='form']");
	console.log(formObj);
	$(".btn-update").on("click", function(){
		sel.location = "/shop/sreview/list?page=${cri.page}&perPageNum=${cri.perPageNum}" +
				"&searchType=${cri.searchType}&keyword=${cri.keyword}";
	});
	$(".btn-cencle").on("click", function(){
		formObj.submit();
	});

});
</script>
</head>
<body>
	<div id="contentsArea" style = "margin : auto; margin-top : 100px; width : 60%;">
수정페이지
<form role="form" method="post" action="modifyPage" >
	<input type="hidden" name="page" value="${cri.page}"/>
	<input type="hidden" name="perPageNum" value="${cri.perPageNum}"/>
	<input type="hidden" name="review_no" value="${reviewDTO.review_no}"/>
	<input type="hidden" name="searchType" value="${cri.searchType}">
	<input type="hidden" name="keyword" value="${cri.keyword}">
	<input type="hidden" name="item_no" value="${param.item_no}">
	<div>
		<div>
			<label for="review_title">Title</label> 
			<input type="text" name="review_title" value="${reviewDTO.review_title}">
		</div>
		<div>
			<label for="review_content">Content</label>
			<textarea name="review_content" rows="3">${reviewDTO.review_content}</textarea>
		</div>
		<div>
			<label for="mb_id">Id</label>
			<input type="text" name="mb_id" value="${reviewDTO.mb_id}" readonly="readonly">
		</div>
	</div>

	<div>
		<div>
			<hr>
		</div>
		<ul class="mailbox-attachments clearfix uploadedList">
		</ul>
		<div>
		<button type="submit" class="btn-update">수정</button>
		<button type="submit" class="btn-cencle">취소</button>
	</div>
	</div>
	</div>
</form>
</body>
</html>