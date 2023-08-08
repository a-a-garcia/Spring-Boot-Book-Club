<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.Date"%> <!-- Tells us meta info, what language we're using.. etc.. we can add edits later. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>  
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
			<h1>Add a Book to your Shelf!</h1>
			<a href="/books">back to the shelves</a>
		</div>
		<div class="card-body">
			<form:form action="/books/new/submit" method="POST" modelAttribute="book">
			<form:errors path="title" class="error" />
			<form:errors path="author" class="error" />
			<form:errors path="thoughts" class="error" />
			  <div class="form-group">
			    <form:label path="title" for="title">Title</form:label>
			    <form:input path="title" type="text" class="form-control" id="title"/>
			  </div>
			  <div class="form-group">
			    <form:label path="author" for="author">Author</form:label>
			    <form:input path="author"	 type="text" class="form-control" id="author"/>
			  </div>
			  <div class="form-group">
			 	<form:label path="thoughts" for="thoughts">Thoughts</form:label>
				<form:textarea path="thoughts" id="thoughts" name="thoughts" rows="4" cols="20" class="form-control"></form:textarea>
			  </div>
			  <button type="submit" class="btn btn-primary">Submit</button>
			</form:form> 
		</div>
	</div>
</body>
</html>