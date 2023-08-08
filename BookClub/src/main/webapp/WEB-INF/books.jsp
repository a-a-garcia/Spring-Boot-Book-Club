<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Date"%> <!-- Tells us meta info, what language we're using.. etc.. we can add edits later. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<div class="wrapper">
		<div class="card">
			<div class="card-header d-flex justify-content-between">
				<div class="left-header d-flex flex-column justify-content-between align-items-start">
					<h2>Welcome, <c:out value="${user.userName}"></c:out>!</h2>
					<h5>Books from everyone's shelves:</h5>
				</div>
				<div class="right-header d-flex flex-column justify-content-between align-items-end">
					<a href="/logout">LOGOUT</a>
					<a href="/books/new">+ Add a book to your shelf!</a>
				</div>
			</div>
			<div class="card-body">
				<c:if test="${not empty errorMessage}">
				    <div class="error">
				        ${errorMessage}
				    </div>
				</c:if>
				<table class="table table-dark">
				  <thead>
				    <tr>
				      <th scope="col">ID</th>
				      <th scope="col">Title</th>
				      <th scope="col">Author Name</th>
				      <th scope="col">Posted By</th>
				    </tr>
				  </thead>
				  <tbody>
				  	<c:forEach var="book" items="${allBooks}">
					    <tr>
					      <th scope="row"><c:out value="${book.id}"></c:out></th>
					      <td><a href="books/${book.id}"><c:out value="${book.title}"></c:out></a></td>
					      <td><c:out value="${book.author}"></c:out></td>
					      <td><c:out value="${book.user.userName}"></c:out></td>
					    </tr>
					</c:forEach>
				  </tbody>
				</table>
			</div>
		</div>	
	</div>
</body>
</html>