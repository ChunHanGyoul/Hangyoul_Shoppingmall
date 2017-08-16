<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<jsp:include page="../basicBody4.jsp" flush="true"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한결처럼 쇼핑몰 상품관리-상품 조회[페이징 처리 포함]</title>
<link rel="stylesheet" href="../resources/css/w3.css">
<link rel="stylesheet" href="../resources/css/basicBody1.css" type="text/css">
<script src="../resources/js/jquery-1.12.4.min.js"></script>
<script src="../resources/js/bagicLoginState.js"></script>
<script type="text/javascript" src="../resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
 //2> 페이지 번호를 클릭하면 처리하는 자바스크립트 작성
var result = '${msg}';

if (result == 'SUCCESS') {
	alert("처리가 완료되었습니다.");
}


//검색 버튼의 동작 처리를 위해 자바스크립트 작성
$(document).ready( function() {

			$('#searchBtn').on("click", function(event) {
						self.location = "/shop/item/itemManagement_list"
								+ '${pageMaker.makeQuery(1)}'
								+ "&searchType="
								+ $("select option:selected").val()
	     						+ "&keyword=" + $('#keywordInput').val();
    //이전의 PageMaker의 makeQuery()를 이용해서 처리하는 부분: 이 메서드는 검색 조건이 없는 상황에서 사용하는 메서드
    //검색 조건이 없는 링크를 생성하고, 필요한 링크를 뒤에 연결시키는 방식으로 처리함
					});

		});
</script>
</head>
<body>
	<div id="contentsArea" style = "margin : auto; margin-top : 100px; width : 60%;">
<!-- Main content -->
<section class="content">
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
					<button type="button" value="button" id="newBtn"
							onclick="location.href='/shop/item/itemManagement_register'">상품등록
						</button>
					<button type="button" value="button"
							onclick="location.href='/shop/category/list'">분류관리
						</button><br/>
						
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
					<button id="searchBtn">Search</button>
						
				</div>
			</div>
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">LIST PAGING</h3>
					<!-- 페이지 정보를 유지할 수 있또록 변경됨: page와 perPageNum과 연동 -->
				</div>
				<div class="box-body">
					<table class="table table-bordered" border="1">
						<tr>
							<th>CATEGORY_NAME</th>
                            <th>ITEM_NO</th>
						    <th>ITEM_NAME</th>
						    <th>ITEM_PRICE</th>
						    <th>ITEM_DISCOUNT</th>
						</tr>

						<c:forEach items="${list}" var="itemManagementDTO">
							<tr>
								<td>${itemManagementDTO.category_name}</td>
       							<td>${itemManagementDTO.item_no}</td>
      							 <td><a href='./itemManagement_viewItemDetailPage?item_no=${itemManagementDTO.item_no}'>
      							 ${itemManagementDTO.item_name}</a></td>
      							 <td>${itemManagementDTO.item_price}</td>
      							 <td>${itemManagementDTO.item_discount}</td>
							</tr>
						</c:forEach>
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
									href="itemManagement_list${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
							</c:if>

							<c:forEach begin="${pageMaker.startPage }"
								end="${pageMaker.endPage }" var="idx">
								<li
									<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
									<a href="itemManagement_list${pageMaker.makeSearch(idx)}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li><a
									href="itemManagement_list${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
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
  <input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum}" />
</form>
</div>
</body>
</html>