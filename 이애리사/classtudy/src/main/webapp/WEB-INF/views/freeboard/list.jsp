<%@page import="java.util.Date"%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.edu.domain.MemberDTO" %>
<%@ page import="com.edu.domain.FreeboardDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>자유게시판</title>

<%@ include file="../include/header.jsp" %>	

</head>
<body>
<%@ include file="../include/topmenu.jsp" %>

<div class="container">
	<header>
		<h1>자유게시판</h1><br>
	</header>
	<div class="col-sm-12" style="text-align: right; padding-bottom: 10px;">
	<button class="btn btn-warning" onclick="location.href='/community/writer'">글쓰기</button><br><br>
	<table class="table table-hover table-bordered">
		<thead>
			<tr>
				<th style="text-align: center;">글번호</th>
				<th style="text-align: center;">말머리</th>
				<th style="text-align: center;">제  목</th>
				<th style="text-align: center;">작성자</th>
				<th style="text-align: center;">작성일</th>
				<th style="text-align: center;">조회수</th>
				<th style="text-align: center;">좋아요</th>
			</tr>
		</thead>
			<c:forEach var="board" items="${list}">
			<tr>				
				<td>${board.boardNo}</td>
				<td>${board.category}</td>
				<td><a href="/community/detail/${board.boardNo}">${board.title}</a></td>
				<td>${board.writer}</td>				
				<td><fmt:formatDate value="${board.writeDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				<td>${board.views}</td>
				<td>${board.likes}</td>
			</tr>
			</c:forEach>
		<tbody>
		</tbody>
	</table>
</div>
</div>


<%@ include file="../include/footer.jsp" %>
</body>
</html>