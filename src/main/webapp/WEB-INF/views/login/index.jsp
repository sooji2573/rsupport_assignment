<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<title>로그인</title>
	</head>
	<body class="bg-light">
	<div class="container">
		<div class="page-header">
			<h1>로그인</h1>
		</div>
		<br/>
	    <table class="table">
	    	<tr>
	    		<th>아이디</th>
	    		<td><input type="text" id="id" name="id"/></td>
	    	</tr>
	    	<tr>
	    		<th>비밀번호</th>
	    		<td><input type="password" id="password" name="password"/></td>
	    	</tr>
	    </table>
	    <div class="pull-left">
	    	<button type="button" class="btn btn-primary" id="login">로그인</button>
	    </div>	    
	</div>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script>
			$("#login").click(function(){
				var jsonData = JSON.stringify({
					id : $("#id").val()
					, password : $("#password").val()
				});
				
				$.ajax({
					url: "/login"
				    , type: "POST"
					, data : jsonData
					, contentType : "application/json"
					, dataType : "json"
					, success : function() {
						location.href = "/notice/list";
					}
					, error : function() {
						alert("로그인 정보를 확인해주세요!!");
					}
					
				});	
			});

		</script>
    
</html>