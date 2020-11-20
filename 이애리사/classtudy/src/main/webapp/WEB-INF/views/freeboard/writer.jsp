<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.edu.domain.MemberDTO" %>
<%@ page import="com.edu.domain.FreeboardDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>자유게시판 작성</title>
<%@ include file="../include/header.jsp" %>	
</head>
<body>
<%@ include file="../include/topmenu.jsp" %>

<div class="container" style="padding-bottom: 30px">
	<form class="form-horizontal" name="writeform" action="/community/writer" method="post">
		<header>
			<h1>자유게시판</h1><br>
		</header> 
		<div class="form-group">
			<label class="control-label col-sm-2">말머리</label>
			<div class="col-sm-2">
				<select class="form-control" id="category" name="category">
					<option value="이야기">이야기</option>
					<option value="공지사항">공지사항</option>
					<option value="질문">질문</option>
					<option value="정보">정보</option>					
					<option value="그룹홍보">그룹홍보</option>
				</select>
			</div>
		</div>		
		<div class="form-group">
			<label class="control-label col-sm-2">작성자</label>
			<div class="col-sm-3">
				<input type="text" id="name" name="name" class="form-control" value="${member.name}" readonly="readonly">
			</div>
			<!-- 숨겨서 넘길 정보들 -->
			<input type="hidden" id="writer" name="writer" class="form-control" value="${member.memberId}" maxlength=16/>
		<!--	<input type="hidden" id="views" name="views" class="form-control" value="${board.views}" maxlength=16/>
			<input type="hidden" id="likes" name="likes" class="form-control" value="${board.likes}" maxlength=16/>-->
		</div>
		<div class="form-group">		
			<label class="control-label col-sm-2">제  목</label>
			<div class="col-sm-7">
				<input type="text" class="form-control" id="title" name="title" placeholder="제목" maxlength=50/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">내  용</label>
			<div id="test-editormd">
				<textarea style="display:none;" id="content" name="content"></textarea>
			</div>
		</div>
		<!-- 
		<div class="form-group">		
			<label class="control-label col-sm-2">태  그</label>
			<div class="col-sm-7">
				<input type="text" class="form-control" id="tags" name="tags" onkeydown="nextFocus(content)" placeholder="태그"/>
				<input type="text" class="form-control" id="tags" name="tags" value="${board.tags}" readonly="readonly" onkeydown="nextFocus(category)">
			</div>
		</div>
		-->
		<div class="form-group">
			<div class="col-sm-offset-0 col-sm-12" style="text-align: center;">
			<!-- <div class="col-sm-9" align="right">-->
				<button type="submit" class="btn btn-success" id="submit">등록</button>&nbsp;
				<button type="button" class="btn btn-warning cancel">다시작성</button>	
			</div>
		</div>	
	</form>
</div>


<%@ include file="../include/footer.jsp" %>
	<script>
	$(document).ready(function() {	
		// 취소 버튼이 눌렸을 경우 => OK하면 메인으로 이동
		$(".cancel").on("click", function() {
			if(confirm("다시 작성 하시겠습니까?") == false){
				return false;
			} else {
				location.href ="/community/writer";
			}
		});	
	});
	</script>
	<script>
	var testEditor;
	$(function() {
		testEditor = editormd("test-editormd", {
			width 		: "95%",
			height 		: 640,
			syncScrolling : "single",
			path 		: '/static/js/lib/',
			readOnly 	: false,
			watch 		: true
		});
	});
	</script>
</body>


</html>