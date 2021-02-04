<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("euc-kr");%>
<%
	String memberId = (String) session.getAttribute("memberId");
	if(memberId == null) {
		response.sendRedirect("/login");
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<title>공지사항</title>
	</head>
	<body class="bg-light">
	<div class="container">
		<div class="page-header">
			<h1><c:if test="${param.seq ne null and param.seq ne ''? '공지사항 상세' : '공지사항 등록'}"></c:if></h1>
		</div>
		<br/>
		<input id="seq" type="hidden" value="${notice.seq }" />
		<input id="userId" type="hidden" value="${sessionScope.memberId }" />
	    <table class="table">
	    	<tr>
	    		<th>이름</th>
	    		<td><input type="text" id="username" name="username" value="${notice.username }"/></td>
	    	</tr>
	    	<tr>
	    		<th>제목</th>
	    		<td><input type="text" id="title" name="title" value="${notice.title }"/></td>
	    	</tr>
	    	<tr>
	    		<th>내용</th>
	    		<td><textarea id="contents" name="contents">${notice.contents }</textarea></td>
	    	</tr>
	    </table>
	    <div class="pull-left">
	    	<a href="/notice/list?page=${param.page }" class="btn btn-success">목록</a>
	    	<c:if test="${empty notice.seq }"><button type="button" class="btn btn-primary" id="insert">저장</button></c:if>
	    	<c:if test="${not empty notice.seq }">
	    		<button type="button" class="btn btn-primary" id="update">수정</button>
	    		<button type="button" class="btn btn-primary" id="delete">삭제</button> 
	    	</c:if>
	    </div>	    
	</div>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script>
			var pageInfo = '${param.page}';
			
			$("#insert").click(function(){
				var jsonData = JSON.stringify({
					title : $("#title").val()
					, userId : $("#userId").val()
					, username : $("#username").val()
					, contents : $("#contents").val() 
				});
				
				$.ajax({
					url: "/notice"
				    , type: "POST"
					, data : jsonData
					, contentType : "application/json"
					, dataType : "json"
					, success : function() {
						alert("공지사항이 등록되었습니다.");
						location.href = "/notice/list?page="+pageInfo;
					}
					, error : function() {
						alert("공지사항 등록 에러!!");
					}
					
				});	
			});

			$("#update").click(function(){
				var jsonData = JSON.stringify({
					title : $("#title").val()
					, userId : $("#userId").val()
					, username : $("#username").val()
					, contents : $("#contents").val() 
				});
				
				$.ajax({
					url: "/notice/" + $("#seq").val()
					, type : "PUT"
					, data : jsonData
					, contentType : "application/json"
					, dataType : "json"
					, success : function() {
						alert("공지사항 수정 성공하였습니다.");
						location.href = "/notice/list?page="+pageInfo;
					}
					, error : function() {
						alert("공지사항 수정 실패!!");
					}
				});
			});

			$("#delete").click(function(){
				$.ajax({
					url : "/notice/" + $("#seq").val()
					, type : "DELETE"
					, success : function() {
						alert("공지사항 삭제 성공하였습니다.");
						location.href="/notice/list";
					}
					, error : function() {
						alert("공지사항 삭제 실패!!!");
					}
				});
			});
		</script>
    
</html>