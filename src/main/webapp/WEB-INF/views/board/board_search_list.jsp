<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("UTF-8"); %><% request.setCharacterEncoding("UTF-8"); %>
<%@ page import = "han.gyoul.shop.member.dto.*" %>
<%MemberDTO memberDTO = (MemberDTO)request.getAttribute("memberDTO"); %>
<%if(memberDTO.getMb_grade().equals("1")) { %>
<jsp:include page="../basicBody.jsp" flush="true"></jsp:include>
<%} else { %>
<jsp:include page="../basicBody2.jsp" flush="true"></jsp:include>
<%} %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>한결처럼 쇼핑몰 Q&amp;A</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
</head>
<body>
	<div id="contentsArea" style = "margin : auto; margin-top : 100px; width : 60%;">
		<section id="titlename" class="qnaBoard">
			<h1>Q&amp;A 검색 결과</h1>
			<p class="allPost">
				검색 글: &nbsp; <strong><c:out value="${searchlistcount}" /></strong>개
			</p>
			<table class="boardTable">
				<caption>Q&amp;A 검색</caption>
				<c:choose>
					<c:when test="${searchlistcount>0}">
						<thead>
							<tr>
								<th scope="col" class="bbsNumber">번호</th>
								<th scope="col" class="bbsTitle">제목</th>
								<th scope="col" class="bbsAuthor">글쓴이</th>
								<th scope="col" class="bbsDate">등록일</th>
								<th scope="col" class="bbsHit">조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="search" items="${searchBoardlist}">
								<tr>
									<td><c:out value="${search.num}" /></td>
									<td><c:if test="${!empty search.answer_lev}">
											<c:forEach var="j" begin="0" end="${search.answer_lev*2}"
												step="1">&nbsp;</c:forEach>
										</c:if> <a
										href="./BoardDetail?num=<c:out value="${search.num}" />">
											<c:out value="${search.subject}" />
									</a></td>
									<td><c:out value="${search.name}" /></td>
									<td><c:out value="${search.write_date}" /></td>
									<td><c:out value="${search.read_count}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</c:when>
				</c:choose>
			</table>
			<div align="center">
				<table id="boardTableNe" class="boardTableNe">
					<tbody>
						<c:if test="${searchlistcount==0}">
							<tr>
								<td colspan="4"></td>
								<td>검색된 글이 없습니다</td>
							</tr>
						</c:if>
						<tr>
							<td colspan="5"><c:choose>
									<c:when test="${page <= 1}"> [이전]&nbsp; </c:when>
									<c:otherwise>
										<a href="./BoardSearch?page=<c:out value="${page-1}"/>">[이전]</a>&nbsp;
</c:otherwise>
								</c:choose> <c:forEach var="start" begin="${startpage}" end="${endpage}"
									step="1">
									<c:choose>
										<c:when test="${start eq page}">[<c:out
												value="${start}" />]</c:when>
										<c:otherwise>
											<a href="./BoardSearch?page=<c:out value="${start}" />">[<c:out
													value="${start}" />]
											</a>&nbsp;
</c:otherwise>
									</c:choose>
								</c:forEach> <c:choose>
									<c:when test="${page >= maxpage}">[다음] </c:when>
									<c:otherwise>
										<a href="./BoardSearch?page=<c:out value="${page+1}" />">[다음]</a>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</tbody>
				</table>
				<div class="btnJoinAreb">
					<button type="button" value="button"
						onclick="location.href='./BoardMain'" class="btnOk">
						목록</button>
				</div>
			</div>
		</section>
	</div>
</body>
</html>