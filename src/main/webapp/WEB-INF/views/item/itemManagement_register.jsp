<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../basicBody4.jsp" flush="true"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한결처럼 쇼핑몰 상품관리</title>
<link rel="stylesheet" href="../resources/css/w3.css">
<link rel="stylesheet" href="../resources/css/basicBody1.css" type="text/css">
<script src="../resources/js/jquery-1.12.4.min.js"></script>
<script src="../resources/js/bagicLoginState.js"></script>
<style type="text/css">
.imgDrop {
	width: 80%;
	height : 100px;
	border: 1px dotted gray;
	background-color: lightslategrey;
	margin: auto;
}
</style>
</head>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
$(function(){
 $("#category_no").change(function(){
	   var category_no = $('#category_no').val();
	   console.log(category_no);
	   $.ajax({
	      type : 'GET',
	      url : '../category/subclassList',
	      data : "category_no="+category_no,
	      dataType : "JSON",
	      success: function(response){
			
				$("#subclass_no").empty();
				$("#category_name").empty();//소분류 리스트를 먼저 비워주고 그 다음 그곳에 채워넣는다.
				$.each(response,function(i,d){
// 					alert(i+"/"+d["category_name"]);
 					//F12 Elements tab 에서 확인해본다.
 					$("#subclass_no").append("<option value='"+d["subclass_no"]+"'>"+d["category_name"]+"</option>");
 				});
				// 변수명 subclass_no에 현재 소분류에 선택되어져있는 값을 넣어준다.
				// if문으로 option value 에 있는 숫자와 같으면 그것의 text 값을 띄어주고
				// category_name에 위에 if문에서 얻은 category_name 변수를 넣어준다.
				var subclass_no = $("#subclass_no").val();
				$("#subclass_no option").each(function(){
					if($(this).attr("value")==subclass_no){
						 category_name = $(this).text();
						 
					}//if
				});//$("#subclass_no option")	 
				 
				$("#category_name").val(category_name);

			}//success
	   });//ajax	 
	 });//$("#category_no")
 
	 
// 소분류를 수정했을 때도 아래쪽 category_name 에 값이 들어가야 하기 때문에 위에와 같은 코드를 
// subclass_no 에 이벤트로 달아준다.
 $("#subclass_no").change(function(){
	 var subclass_no = $("#subclass_no").val();
	$("#subclass_no option").each(function(){
		if($(this).attr("value")==subclass_no){
			 category_name = $(this).text();
			 
		}//if
	});//$("#subclass_no option")	 
	 
	$("#category_name").val(category_name);
	
 });//$("#subclass_no")

	 
})//$(function)

   
	



</script>
<body>
	<div id="contentsArea" style = "margin : auto; margin-top : 100px; width : 60%;">
	<div>
		<section>
			<h1>한결처럼 쇼핑몰 상품 등록</h1>
			<p>
				<strong class="require">필수</strong> 는 반드시 입력하여야 하는 항목입니다.
			</p>
			<form method="post" role="form" action="#"  id="registerForm">
				<!-- 상품 등록 처리하는 ItemManagementRegisterService 클래스를 호출한다. enctype="multipart/form-data"로 설정하여 파일 업로드를 가능하게 함. -->
					
					<p>
						<label for="item_no">상품코드 <strong class="require">필수</strong></label>
						<input type="text" id = "item_no" name="item_no" required
							placeholder="0000" />
					</p>
					<p>
						<label for="item_name">상품명 <strong class="require">필수</strong></label>
						<input type="text" id="text" name="item_name" required
							placeholder="skirt" />
					</p>
<!-- 					<p> -->
						<!-- 상품이미지를 첨부할 때 파일 형식으로 첨부함 -->
<!-- 						<label for="item_image">상품이미지 </label> <input type="file" -->
<!-- 							id="item_image" name="item_image" placeholder="파일(이미지)첨부" /> -->
<!-- 					</p> -->
					<p>
						<label for="item_price">상품가격 <strong class="require">필수</strong></label>
						<!-- 상품가격은 관리자가 직접 입력하는 항목임 -->
						<input type="text" id="item_price" name="item_price" required
							placeholder="상품가격을 입력하시오" />
					</p>
					<p>
						<label for="item_material">상품소재 <strong class="require">필수</strong></label>
<!-- 						다양한 상품소재가 존재하지만 여기서는 면, 마, 가죽, 모만 존재한다고 가정 -->
						<select name="item_material" id="item_material" required>
							<option value="cotton" selected="selected">cotton</option>
							<option value="ramie">ramie</option>
							<option value="skin">skin</option>
							<option value="wool">wool</option>
						</select>
						<!-- 						<input type="checkbox" id="item_material" name="item_material" checked value="cotton" />cotton(면) -->
						<!-- 						<input type="checkbox" id="item_material" name="item_material" value="ramie" />ramie(마) -->
						<!-- 						<input type="checkbox" id="item_material" name="item_material" value="skin" />skin(가죽) -->
						<!-- 						<input type="checkbox" id="item_material" name="item_material" value="wool" />wool(모) -->
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
						<label for="item_mfr">상품제조사 <strong class="require">필수</strong></label>
						<!-- 상품제조사: 협력 맺은 몇몇의 공급업체가 있다고 가정 -->
						<select name="item_mfr" id="item_mfr" required>
							<option value="seun" selected="selected">seun</option>
							<option value="seunseun">seunseun</option>
							<option value="blank">blank</option>
							<option value="diamond">diamond</option>
							<option value="cruncky">cruncky</option>
						</select>
					</p>
					<p>
						<label for="item_country">상품제조국<strong class="require">필수</strong></label>
						<!-- 상품제조국: 대부분 한국과 중국,일본,베트남,인도에서 만들어진다고 가정 -->
						<select name="item_country" id="item_country" required>
							<option value="Korea" selected="selected">Korea</option>
							<option value="China">China</option>
							<option value="Japan">Japan</option>
							<option value="Vietnam">Vietnam</option>
							<option value="India">India</option>
						</select>

					</p>
					<p>
						<label for="item_mdate">상품제조일자<strong class="require">필수</strong></label>
						<input type="date" id="item_mdate" name="item_mdate" required
							placeholder="상품제조일자를 입력하시오" />
					</p>
					<p>
						<label for="item_expln">상품상세설명</label>
						<textarea id="item_expln" name="item_expln" cols="74" rows="15"
							required placeholder="상품의 상세 내용을 입력하시오(ex.주의사항)"> </textarea>
					</p>
					<p>
						<label for="item_discount">상품할인가</label>
						<input type="text" id="item_discount" name="item_discount" required
							placeholder="상품할인가를 입력하시오" />
					</p>
					<p>
					<p>
						<label for="category_no">대분류<strong class="require">필수</strong></label>
					   
					<select class="category_no" name="category_no" id="category_no">
                     <option>--대분류선택--</option>
                     <c:forEach var="category" items="${list}" varStatus="status">
                        <c:if test="${category.subclass_no eq '10'}">
                           
                           <option value="${category.category_no}"><c:out
                                  value="${category.category_name}" /></option> 
                         </c:if> 
                      </c:forEach> 
                  </select>
					</p>
					<p>
						<label for="subclass_no">소분류<strong class="require">필수</strong></label>
						 
						 <select name="subclass_no" id="subclass_no" required>
						 	
						 </select>
									
					</p>
					<p>
						<label for="category_name">분류명<strong class="require">필수</strong></label>
						
						<input name="category_name" id="category_name" required>
						
						
						
					
						<div>
			<label for="item_image">Img Drop Here</label>
			<div class="imgDrop"></div>
			
<!-- 			<input name="item_image" class="imgDrop"/> -->
		</div>
					
					<!-- 					<p> -->
					<!-- 						<label for="admin_password">관리자비밀번호 <strong class="require">필수</strong></label> -->
					<!-- 						<input type="hidden" id="admin_password" name="admin_password" required -->
					<!-- 							placeholder="영문/숫자 4~8자 이내" /> -->
					<!-- 					</p> -->
					<div>
					<div>
			<hr>
		</div>
		<ul class="mailbox-attachments clearfix uploadedList">
		</ul>
						<button type="submit">상품등록</button>
						<button type="reset">등록취소</button>
						<button type="button" value="button"
							onclick="location.href='./itemManagement_listAll'">상품목록
						</button>
					</div>
			</form>
		</section>
	</div>
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../resources/js/upload.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<script id="template" type="text/x-handlebars-template">
<li>
  <span class="mailbox-attachment-icon has-img"><img src="/shop{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
	<a href="/shop{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	<a href="{{fullName}}" 
     class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
	</span>
  </div>
</li>                
</script>
<script>

var template = Handlebars.compile($("#template").html());

$(".imgDrop").on("dragenter dragover", function(event){
	event.preventDefault();
});


$(".imgDrop").on("drop", function(event){
	event.preventDefault();
	
	var files = event.originalEvent.dataTransfer.files;
	
	var file = files[0];

	var formData = new FormData();
	
	formData.append("file", file);	
	
	$.ajax({
		  url: '/shop/uploadAjax',
		  data: formData,
		  dataType:'text',
		  processData: false,
		  contentType: false,
		  type: 'POST',
		  success: function(data){
			  
			  var fileInfo = getFileInfo(data);
			  alert(fileInfo);
			  var html = template(fileInfo);
			  alert(html);
			  $(".uploadedList").append(html);
		  }
		});	
});


$("#registerForm").submit(function(event){
	event.preventDefault();
	
	var that = $(this);
	
	var str ="";
	$(".uploadedList .delbtn").each(function(index){
		 str += "<input type='hidden' name='item_image' value='"+$(this).attr("href") +"'> ";
	});
	
	that.append(str);

	that.get(0).submit();
});



</script>
</div>
</body>
</html>