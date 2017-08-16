<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../basicBody4.jsp" flush="true"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한결처럼 쇼핑몰 상품관리-상품 수정[페이징 처리 포함] 처리</title>
<link rel="stylesheet" href="../resources/css/w3.css">
<link rel="stylesheet" href="../resources/css/basicBody1.css" type="text/css">
<script src="../resources/js/jquery-1.12.4.min.js"></script>
<script src="../resources/js/bagicLoginState.js"></script>
<script type="text/javascript" src="../resources/js/jquery-1.11.3.min.js"></script>
<script>
$(document).ready(function() {
	
	var formObj = $("form[role='form']");
	
	console.log(formObj);
	
		$(".btn-cancle").on("click", function() {
			self.location = "/shop/item/itemManagement_list?page=${cri.page}&perPageNum=${cri.perPageNum}";
		 + "&searchType=${cri.searchType}&keyword=${cri.keyword}";						
		});
	
	   $(".btn-save").on("click", function() {
			formObj.submit();
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
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">MODIFY ITEM</h3>
				</div>
				<!-- /.box-header -->

				<form role="form" action="./itemManagement_modifyPage" method="post">
   <!-- 페이징 처리에 대한 정보 유지 -->
					<input type='hidden' name='page' value="${cri.page}"> 
					<input type='hidden' name='perPageNum' value="${cri.perPageNum}">

					<div class="box-body">
	                <p>
						<label for="item_no">상품코드 <strong class="require">필수</strong></label>
						<input type="hidden" name="item_no"
						value="<c:out value="${itemManagementDTO.item_no}"/>" />
					</p>
					<p>
						<label for="item_name">상품명 <strong class="require">필수</strong></label>
						<input type="text" id="item_name"
							name="item_name" value="<c:out value="${itemManagementDTO.item_name}"/>" />
					</p>
                         <c:if test="${empty itemManagementDTO.item_image}">
						<p>
							<label for="item_image">이미지 첨부</label><br />
							<c:out value="${itemManagementDTO.item_image}" />
							&nbsp;&nbsp;&nbsp; <a
								href="./itemImageUpload/<c:out value="${itemManagementDTO.item_image}"/>">이미지
								보기</a> <input type="hidden" name="old_image"
								value="<c:out value="${itemManagementDTO.item_image}"/>" />
						</p>
					</c:if>
						<p>
						<label for="old_image">이미지 수정</label> <input type="file"
							id="old_image" name="old_image">
					</p>
					<p>
						<label for="item_size">상품사이즈<strong class="require">필수</strong></label>
						<!-- 원래 사이즈는 xs과 2XL도 존재하지만 여기서는 4가지의 사이즈만 존재한다고 가정 -->
						<select name="item_size" id="item_size" required>
							<option value="S" selected="selected">S</option>
							<option value="M">M</option>
							<option value="L">L</option>
							<option value="XL">XL</option>
						</select>
					</p>
					<p>
						<label for="item_color">상품색상 <strong class="require">필수</strong></label>
						<!-- 색상은 많이 존재하지만 여기서는 5가지 색상만 존재한다고 가정(흰색, 분홍색, 파란색, 보라색, 검정색) -->
						<select name="item_color" id="item_color" required>
							<option value="white" selected="selected">white</option>
							<option value="pink">pink</option>
							<option value="blue">blue</option>
							<option value="purple">purple</option>
							<option value="black">black</option>
						</select>
					</p>
					<p>
						<label for="item_qty">상품수량 <strong class="require">필수</strong></label>
						<input type="text" id="item_qty" name="item_qty" required
							placeholder="상품수량을 입력하시오" />
					</p>
						<p>
						<label for="item_expln">상품상세설명</label>
						<textarea id="item_expln" name="item_expln" cols="74" rows="15"
							required placeholder="상품의 상세 내용을 입력하시오(ex.주의사항)"> </textarea>
					</p>
					<p>
						<label for="item_discount">상품할인가</label>
						<input type="text" id="item_discount" name="item_installment" required
							placeholder="상품할인가를 입력하시오" />
					</p>
					<p>
						<label for="category_no">대분류번호<strong class="require">필수</strong></label>
						<input type="text" id="category_no" name="category_no" required
							placeholder="대분류번호 입력" size="8">
					</p>
					<p>
						<label for="subclass_no">소분류번호<strong class="require">필수</strong></label>
						<input type="text" id="subclass_no" name="subclass_no" required
							placeholder="소분류번호 입력" size="8">
					</p>
					<p>
						<label for="category_name">분류명<strong class="require">필수</strong></label>
						<input type="text" id="category_name" name="category_name" required
							placeholder="분류명 입력">
					</p>
						
					</div>
					<!-- /.box-body -->
				</form>
				<div class="box-footer">
					<button type="submit" class="btn-save">SAVE</button>
					<button type="submit" class="btn-cancle">CANCEL</button>
				</div>
					</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->
				</div>
</body>
</html>