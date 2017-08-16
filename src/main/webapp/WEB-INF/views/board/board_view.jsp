<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%><% request.setCharacterEncoding("UTF-8"); %>
<%@ page import = "han.gyoul.shop.member.dto.*" %>
<%MemberDTO memberDTO = (MemberDTO)request.getAttribute("memberDTO"); %>
<%if(memberDTO.getMb_grade().equals("1")) { %>
<jsp:include page="../basicBody.jsp" flush="true"></jsp:include>
<%} else { %>
<jsp:include page="../basicBody2.jsp" flush="true"></jsp:include>
<%} %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한결처럼 쇼핑몰 Q&amp;A</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<script type="text/javascript" src="./js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	function updateReply(reply_no) {
		$("#replyTr_"+reply_no).css("display","none");
		$("#replyUpdateTr_"+reply_no).css("display","");
		$("#passTd").css("display","");
	}
	function updateCancle(reply_no) {
		$("#replyTr_"+reply_no).css("display","");
		$("#replyUpdateTr_"+reply_no).css("display","none");
		$("#passTd").css("display","none");
	}
	function updateComplete(reply_no) {
		var board_no = ${boardDTO.num};
		var replyWriter = $("#replyWriter_" + reply_no).val();
		var replyContent = $("#replyContent_" + reply_no).val();
		var replyPass = $("#replyPass_" + reply_no).val();
		$.ajax({
			type : 'POST',
			url : './ReplyChange',
			data:{
				writer : replyWriter,
				content : replyContent,
				pass : replyPass,
				no : reply_no,
				board_no : board_no
			},
			async: false,
			success:function(response) {
				alert("수정되었습니다.");
				location.href = "./BoardDetail?num="+board_no;
			},
			error: function(response) {
				alert("비밀번호가 다릅니다.");
			}
		});
	}
</script>
</head>
<body>
	<div id="contentsArea" style = "margin : auto; margin-top : 100px; width : 60%;">
		<div id="contentsArea">
			<section id="titlename">
				<h1>Q&amp;A 내용</h1>
				<p class="formSign">게시판의 글 내용 입니다</p>
				<div id="joinForm">
					<input type="hidden" name="num" id="num"
						value="<c:out value="${boardDTO.num }" />">
					<fieldset>
						<legend>Q&amp;A 내용</legend>
						<p>
							<label for="name">이름 </label> <br />
							<c:out value="${boardDTO.name }" />
						</p>
						<p>
							<label for="subject">제목 </label> <br />
							<c:out value="${boardDTO.subject }" />
						</p>
						<p>
							<label for="content">내용</label> <br />
							<c:out value="${boardDTO.content}" />
						</p>

						<div class="btnJoinArea">
							<a href="./BoardReply?num=<c:out value="${boardDTO.num}"/>">
								<button type="button" class="btnOk">답변</button>
							</a> <a href="./BoardModify?num=<c:out value="${boardDTO.num}"/>">
								<button type="button" class="btnOk">수정</button>
							</a> <a href="./BoardDelete?num=<c:out value="${boardDTO.num}"/>">
								<button type="button" class="btnOk">삭제</button>
							</a>
							<button type="button" value="button"
								onclick="location.href='./BoardMain'" class="btnOk">목록</button>
						</div>
					</fieldset>
				</div>
			</section>
		</div>
		<div id="contentsArea">
			<section id=reply>
				<fieldset>
					<h1>댓글 달기</h1>
					<br />
					<form action="./Reply_Write" method="post">
						<input type="hidden" name="boardNum" id="boardNum"
							value="<c:out value="${boardDTO.num }" />"> <label
							for="reply_writer">작성자</label> <input type="text"
							id="reply_writer" name="reply_writer" /> <label for="reply_pass">비밀번호</label>
						<input type="text" id="reply_pass" name="reply_pass" /> <br /> <label
							for="reply_content">내용</label> <input type="text"
							id="reply_content" name="reply_content" /> <br /> <input
							type="submit" value="작성" /> <input type="reset" value="취소" />
					</form>
				</fieldset>
				<fieldset>
					<legend>댓글</legend>
					<table border="1px solid black">
						<c:choose>
							<c:when test="${replycount>0}">
								<tbody>
									<tr>
										<td>작성자</td>
										<td>내용</td>
										<td id="passTd" style="display: none">비밀번호</td>
										<td>버튼</td>
									</tr>
								</tbody>
								<c:forEach var="reply" items="${replyList}">
									<tbody class="search">
										<tr id="replyTr_${reply.reply_no}">
											<td><c:out value="${reply.reply_writer}" /></td>
											<td><c:out value="${reply.reply_content}" /></td>
											<td><input type="button" value="수정"
												onclick="updateReply(${reply.reply_no})" /> <input
												type="button" value="삭제"
												onclick="location.href='./ReplyDelete?num=<c:out value="${boardDTO.num}" />&reply_num=<c:out value="${reply.reply_no}" />'">
											</td>
										</tr>
										<tr id="replyUpdateTr_${reply.reply_no}" style="display: none">
											<td><input type="text"
												id="replyWriter_${reply.reply_no}"
												value="${reply.reply_writer}"></td>
											<td><input type="text"
												id="replyContent_${reply.reply_no}"
												value="${reply.reply_content}"></td>
											<td><input type="password"
												id="replyPass_${reply.reply_no}" /></td>
											<td><input type="button" value="취소"
												onclick="updateCancle(${reply.reply_no})" /> <input
												type="button" value="확인"
												onclick="updateComplete(${reply.reply_no})" /></td>
										</tr>
									</tbody>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
				</fieldset>
			</section>
		</div>
	</div>
</body>
</html>