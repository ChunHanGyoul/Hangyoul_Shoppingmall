<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../basicBody3.jsp" flush="true"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기글등록</title>
<link rel="stylesheet" href="../resources/css/w3.css">
<link rel="stylesheet" href="../resources/css/basicBody1.css" type="text/css">
<script src="../resources/js/jquery-1.12.4.min.js"></script>
<script src="../resources/js/bagicLoginState.js"></script>
<script type="text/javascript" src="../resources/js/jquery-1.11.3.min.js"></script>
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
<body>
	<div id="contentsArea" style = "margin : auto; margin-top : 100px; width : 60%;">
<form id="registerForm" role="form" method="post" action="./register">
	<div>
		<div>
			<label for="review_title">Title</label> 
			<input type="text" name="review_title" placeholder="Enter Title">
		</div>
		<div>
			<label for="review_content">Content</label>
			<textarea name="review_content" rows="3" placeholder="Enter ..."></textarea>
		</div>
		<div>
			<label for="mb_id">Id</label>
			<input type="text" name="mb_id" placeholder="Enter Id">
		</div>
		
		<div>
			<label for="item_no">Item_no</label>
			<input type="number" id = "item_no" name="item_no" placeholder="Enter Item_no" value="${param.item_no }">
		</div>
		
		<div>
			<label for="review_image">Img Drop Here</label>
			<div class="imgDrop"></div>
<!-- 			<input name="review_image" class="imgDrop"/> -->
		</div>
	</div>
	
	<div>
		<div>
			<hr>
		</div>
		<ul class="mailbox-attachments clearfix uploadedList">
		</ul>
		<button class="sub" type="submit">Submit</button>
	</div>
</form>

<script type="text/javascript" src="../resources/js/jquery-1.11.3.min.js"></script>
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
		 str += "<input type='hidden' name='review_image' value='"+$(this).attr("href") +"'> ";
	});
	
	that.append(str);

	that.get(0).submit();
});



</script>
</div>
</body>
</html>