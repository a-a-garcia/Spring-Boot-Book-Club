<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Date"%> <!-- Tells us meta info, what language we're using.. etc.. we can add edits later. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/js/script.js"></script>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/style.css"/>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Best Book Club Ever</title>
</head>
<body>
	<div class="card">
		<div class="card-header d-flex justify-content-between align-items-center">
			<h1><c:out value="${book.title}"></c:out></h1>
			<a href="/books">back to the shelves</a>
		</div>
		<div class="card-body">
			<h2><span style="color: red"><c:out value="${book.user.userName}"></c:out></span> read <span style="color: purple"><c:out value="${book.title}"></c:out></span> by <span style="color: green"><c:out value="${book.author}"></c:out></span></h2>
			<p> Here are <c:out value="${book.user.userName}"></c:out>'s thoughts:
			<hr>
			<div style="font-style:italic"><c:out value="${book.thoughts}"></c:out></div>
			<hr>
		</div>
		<c:if test="${book.user.id == sessionScope.userId}">
			<div class="d-flex justify-content-around align-items-center m-3">
				<a class="btn btn-primary" href="/books/${book.id}/edit">Edit</a>
				<form method="POST" action="/books/${book.id}/delete">
					<input type="hidden" value="${book.id}">
					<input type="submit" value="Delete" class="btn btn-danger">
				</form>			
			</div>
		</c:if>
	</div>
</body>
</html>