<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	$(document).ready(function() {
		var formObj = $("form[role='form']");
		console.log(formObj);

		$(".btn-update").on("click", function() {
			formObj.attr("action", "/shop/sreview/modifyPage");
			formObj.attr("method", "get");
			formObj.submit();
		});

		$(".btn-delete").on("click", function() {
			formObj.attr("action", "/shop/sreview/removePage?item_no=${param.item_no}");
			formObj.submit();
		});

		$(".btn-list").on("click", function() {
			formObj.attr("method", "get");
			formObj.attr("action", "/shop/sreview/list");
			formObj.submit();
		});
		
		var file = '${reviewDTO.review_image}';
		var formData = new FormData();
		formData.append("file", file);
		var template = Handlebars.compile($("#templateAttach").html());

		var fileInfo = getFileInfo(file);
		alert(fileInfo);
		var html = template(fileInfo);
		alert(html);
		$(".uploadedList").append(html);

	});
</script>
</head>
<body>
	<div id="contentsArea" style = "margin : auto; margin-top : 100px; width : 60%;">
	<form role="form" method="post">
		<input type="hidden" name="review_no" value="${reviewDTO.review_no}">
		<input type="hidden" name="page" value="${cri.page}"> <input
			type="hidden" name="perPageNum" value="${cri.perPageNum}"> <input
			type="hidden" name="searchType" value="${cri.searchType}"> <input
			type="hidden" name="keyword" value="${cri.keyword}">
			<input type="hidden" name="item_no" value="${param.item_no}">
	</form>
	<div>
		<div>
			<label>TITLE</label> <input type="text" name="review_title"
				value="${reviewDTO.review_title}" readonly="readonly" /><br />
		</div>
		<div>
			<label>MB_ID</label> <input type="text" name="mb_id"
				value="${reviewDTO.mb_id}" readonly="readonly" /><br />
		</div>
		<div>
			<label>CONTENT</label>
			<textarea name="review_content" rows="3" readonly="readonly">${reviewDTO.review_content}</textarea>
		</div>
	</div>
	<div>
		<label>IMAGE</label>
		<ul class="mailbox-attachments clearfix uploadedList">
		</ul>
		<button type="submit" class="btn-update">수정</button>
		<button type="submit" class="btn-delete">삭제</button>
		<button type="submit" class="btn-list">목록</button>
	</div>

	<div class="row">
		<div class="col-md-12">

			<div class="box box-success">
				<div class="box-header">
					<h3 class="box-title">ADD NEW REPLY</h3>
				</div>

				<div class="box-body">
					<label for="exampleInputEmail1">Writer</label> <input
						class="form-control" type="text" placeholder="MB ID"
						id="newReplyWriter" value="${Comment.mb_id }" readonly="readonly">
					<label for="exampleInputEmail1">Comment Text</label> <input
						class="form-control" type="text" placeholder="COMMENT TEXT"
						id="newReplyText">
				</div>

				<div class="box-footer">
					<button type="submit" class="btn btn-primary" id="replyAddBtn">ADD
						COMMENT</button>
				</div>
			</div>

			<!-- The time line -->
			<ul class="timeline">
				<!-- timeline time label -->
				<li class="time-label" id="repliesDiv"><span class="bg-green">
						Comments List<small id="replycntSmall">[
							${reviewDTO.commentcnt}]</small>
				</span></li>
			</ul>
			<div class='text-center'>
				<ul id="pagination" class="pagination pagination-sm no-margin ">
				</ul>
			</div>
		</div>
		<!-- /.col -->
	</div>
	<!-- Modal -->
	<!-- 	<div id="modifyModal" class="modal modal-primary fade" role="dialog"> -->
	<div id="modifyModal" class="modal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body" data-rno>
					<p>
						<input type="text" id="replytext" class="form-control">
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" id="replyModBtn">Modify</button>
					<button type="button" class="btn btn-danger" id="replyDelBtn">DELETE</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<script id="template" type="text/x-handlebars-template">
				{{#each .}}
	         <li class="replyLi" data-rno={{comment_no}}>
             <i class="fa fa-comments bg-blue"></i>
             <div class="timeline-item" >
                <span class="time">
                  <i class="fa fa-clock-o"></i>{{prettifyDate comment_date}}
                </span>
                <h3 class="timeline-header"><strong>{{comment_no}}</strong> -{{mb_id}}</h3>
                <div class="timeline-body">{{comment_content}} </div>
								<div class="timeline-footer">
                  <a class="btn btn-primary btn-xs" 
									data-toggle="modal" data-target="#modifyModal">수정버튼</a>
							  </div>
	            </div>			
           </li>
        {{/each}}
</script>
	<script>
		Handlebars.registerHelper("eqReplyer", function(replyer, block) {
			var accum = '';
			if (replyer == '${login.uuid}') {
				accum += block.fn();
			}
			return accum;
		});

		Handlebars.registerHelper("prettifyDate", function(timeValue) {
			var dateObj = new Date(timeValue);
			var year = dateObj.getFullYear();
			var month = dateObj.getMonth() + 1;
			var date = dateObj.getDate();
			return year + "/" + month + "/" + date;
		});

		var printData = function(replyArr, target, templateObject) {

			var template = Handlebars.compile(templateObject.html());

			var html = template(replyArr);
			$(".replyLi").remove();
			target.after(html);

		}

		var bno = ${reviewDTO.review_no};

		var replyPage = 1;

		function getPage(pageInfo) {

			$.getJSON(pageInfo, function(data) {
				printData(data.list, $("#repliesDiv"), $('#template'));
				printPaging(data.pageMaker, $(".pagination"));
				// 							$("#modifyModal").modal('hide');
				$("#replycntSmall").html(
						"[ " + data.pageMaker.totalCount + " ]");

			});
		}

		var printPaging = function(pageMaker, target) {

			var str = "";

			if (pageMaker.prev) {
				str += "<li><a href='" + (pageMaker.startPage - 1)
						+ "'> << </a></li>";
			}

			for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
				var strClass = pageMaker.cri.page == i ? 'class=active' : '';
				str += "<li "+strClass+"><a href='"+i+"'>" + i + "</a></li>";
			}

			if (pageMaker.next) {
				str += "<li><a href='" + (pageMaker.endPage + 1)
						+ "'> >> </a></li>";
			}

			target.html(str);
		};

		$("#repliesDiv").on("click", function() {

			if ($(".timeline li").size() > 1) {
				return;
			}
			getPage("/shop/replies/" + bno + "/1");

		});

		$(".pagination").on("click", "li a", function(event) {

			event.preventDefault();

			replyPage = $(this).attr("href");

			getPage("/shop/replies/" + bno + "/" + replyPage);

		});

		$("#replyAddBtn").on("click", function() {

			var replyerObj = $("#newReplyWriter");
			var replytextObj = $("#newReplyText");
			var replyer = replyerObj.val();
			var replytext = replytextObj.val();

			$.ajax({
				type : 'post',
				url : '/shop/replies/',
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : 'text',
				data : JSON.stringify({
					review_no : bno,
					mb_id : replyer,
					comment_content : replytext
				}),
				success : function(result) {
					console.log("result: " + result);
					if (result == 'SUCCESS') {
						alert("등록 되었습니다.");
						replyPage = 1;
						getPage("/shop/replies/" + bno + "/" + replyPage);
						replyerObj.val("");
						replytextObj.val("");
					}
				}
			});
		});

		$(".timeline").on("click", ".replyLi", function(event) {

			var reply = $(this);

			$("#replytext").val(reply.find('.timeline-body').text());
			$(".modal-title").html(reply.attr("data-rno"));

		});

		$("#replyModBtn").on("click", function() {

			var rno = $(".modal-title").html();
			var replytext = $("#replytext").val();

			$.ajax({
				type : 'put',
				url : '/shop/replies/' + rno,
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "PUT"
				},
				data : JSON.stringify({
					comment_content : replytext
				}),
				dataType : 'text',
				success : function(result) {
					console.log("result: " + result);
					if (result == 'SUCCESS') {
						alert("수정 되었습니다.");
						getPage("/shop/replies/" + bno + "/" + replyPage);
					}
				}
			});
		});

		$("#replyDelBtn").on("click", function() {

			var rno = $(".modal-title").html();
			var replytext = $("#replytext").val();

			$.ajax({
				type : 'delete',
				url : '/shop/replies/' + rno,
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "DELETE"
				},
				dataType : 'text',
				success : function(result) {
					console.log("result: " + result);
					if (result == 'SUCCESS') {
						alert("삭제 되었습니다.");
						getPage("/shop/replies/" + bno + "/" + replyPage);
					}
				}
			});
		});
		$(".close").on("click", function(){
			
		});
	</script>
	</div>
</body>
</html>