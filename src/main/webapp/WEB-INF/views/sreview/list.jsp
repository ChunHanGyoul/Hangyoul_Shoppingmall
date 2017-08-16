<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@page isELIgnored="false"%>
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
<script type="text/javascript"
	src="../resources/js/jquery-1.11.3.min.js"></script>
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
		jobForm.attr("action", "/sreview/list").attr("method", "get");
		jobForm.submit();
	});
</script>
<script>
	$(document).ready(
		function() {
			$('#searchBtn').on("click", function(event) {
				self.location = "list"
					+ '${pageMaker.makeQuery(1)}'
					+ "&searchType="
					+ $("select option:selected").val()
					+ "&keyword=" + $('#keywordInput').val();
			});
			$('#newBtn').on("click", function(evt) {
				self.location = "register";
			});
	});
</script>
</head>
<body>
	<div id="contentsArea" style = "margin : auto; margin-top : 100px; width : 60%;">
<h1>한결처럼쇼핑몰!!!</h1>
<div class='box-body'>
		<select name="searchType">
			<option value="n"
				<c:out value="${cri.searchType == null?'selected':''}"/>>
				---</option>
			<option value="t"
				<c:out value="${cri.searchType eq 't'?'selected':''}"/>>
				Title</option>
			<option value="c"
				<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
				Content</option>
			<option value="w"
				<c:out value="${cri.searchType eq 'w'?'selected':''}"/>>
				Writer</option>
			<option value="tc"
				<c:out value="${cri.searchType eq 'tc'?'selected':''}"/>>
				Title OR Content</option>
			<option value="cw"
				<c:out value="${cri.searchType eq 'cw'?'selected':''}"/>>
				Content OR Writer</option>
			<option value="tcw"
				<c:out value="${cri.searchType eq 'tcw'?'selected':''}"/>>
				Title OR Content OR Writer</option>
		</select> 
		<input type="text" name='keyword' id="keywordInput"
			value='${cri.keyword }'>
		<button id='searchBtn'>Search</button>
		<button id='newBtn'>New Board</button>
	</div>
	<table border="1">
		<tr>
			<th>REVIEW_NO</th>
			<th>REVIEW_TITLE</th>
			<th>MB_ID</th>
			<th>DATE</th>
			<th>HIT</th>
		</tr>

		<c:forEach items="${list}" var="reviewDTO">
			<tr>
				<td>${reviewDTO.review_no}</td>
				<td><a
					href='../sreview/readPage${pageMaker.makeSearch(pageMaker.cri.page)}&review_no=${reviewDTO.review_no}'>
						${reviewDTO.review_title} <strong>[ ${reviewDTO.commentcnt} ]</strong></a></td>
				<td>${reviewDTO.mb_id}</td>
				<td>${reviewDTO.review_date}</td>
				<td>${reviewDTO.review_hit}</td>
			</tr>
		</c:forEach>
	</table>
	<!-- 페이지를 보여주는 부분 -->
               <div class="text-center">
                  <ul class="pagination">
                     <c:if test="${pageMaker.prev}">
                        <li><a
                           href="listPage${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
                     </c:if>
				<%-- ${pageMaker.endPage} 값이 0이여서 c태그가 안먹은 것처럼 보임. --%>
                     <c:forEach begin="${pageMaker.startPage}"
                        end="${pageMaker.endPage}" var="idx">
                        <li
                           <c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
                           <a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
                        </li>
                     </c:forEach>

                     <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                        <li><a
                           href="list${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
                     </c:if>
                  </ul>
               </div>
	<form>
		<input type="hidden" name="page" value="${pageMaker.cri.perPageNum}" />
		<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum}">
	</form>
	</div>
</body>
</html>