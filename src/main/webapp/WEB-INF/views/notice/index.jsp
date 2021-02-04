<%@page import="org.apache.catalina.connector.Response"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<title>공지사항</title>
	</head>
	<body>
		<h2>공지사항</h2>
      	<div class="table-responsive">
        <table class="table table-striped table-sm">
        	<thead>
	            <tr>
	              <th>SEQ</th>
	              <th>제목</th>
	              <th>작성자</th>
	              <th>작성일</th>
	            </tr>
          	</thead>
          	<c:set var="page" value="${param.page eq null or param.page eq '' or param.page eq 0 ? 1 : param.page}" />
          	<tbody>
	          	<c:forEach var="noticeList" items="${notice.content }" varStatus="status">
		            <tr>
		              <td>${noticeList.seq }</td>	
		              <td><a href="/notice/form?seq=${noticeList.seq}&page=${page}">${noticeList.title }</a></td>
		              <td>${noticeList.username }</td>
		              <td>${fn:substring(noticeList.reg_date,0,10) }</td>
		            </tr>
		        </c:forEach>
           	</tbody>
        </table>
      	</div>
      	
      	<nav class="navbar" aria-label="Page navigation" style="text-align: center;">
      		<ul class="pagination"> 
      			<c:choose>
      				<c:when test="${notice.first }"></c:when>
      				<c:otherwise>
      					<li class="page-item"><a class="page-link" href="/notice/list?page=${notice.number-1 }">Previous</a></li>
      				</c:otherwise>
      			</c:choose>
				
      			<c:forEach var="i" begin="1" end="${notice.totalPages }">
      				<li class="page-item ${page == i ? 'active' : '' }"><a class="page-link " href="/notice/list?page=${i }">${i }</a></li>
      			</c:forEach>
      			
      			<c:choose>
      				<c:when test="${notice.last }"></c:when>
      				<c:otherwise>
      					<li class="page-item"><a class="page-link" href="/notice/list?page=${notice.number+1 }">Next</a></li>
      				</c:otherwise>
      			</c:choose>
            </ul>
      	</nav>
      	<button class="btn btn-success" onclick="location.href='/notice/form?page=${page}' ">등록</button>
	</body>
	
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</html>