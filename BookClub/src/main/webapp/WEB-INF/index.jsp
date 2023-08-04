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
<!-- Section: Design Block -->
<section class="text-center text-lg-start">
  <style>
    .cascading-right {
      margin-right: -50px;
    }

    @media (max-width: 991.98px) {
      .cascading-right {
        margin-right: 0;
      }
    }
  </style>

  <!-- Jumbotron -->
  <div class="container py-4">
    <div class="row g-0 align-items-center">
      <div class="col-lg-6 mb-5 mb-lg-0">
        <div class="card cascading-right" style="
            background: hsla(0, 0%, 100%, 0.55);
            backdrop-filter: blur(30px);
            ">
          <div class="card-body p-5 shadow-5 text-center">
            <h2 class="fw-bold mb-3">Welcome to the best book club ever!!!!</h2>
            <h2 class="fw-bold mb-3">Register</h2>
            <form:form modelAttribute="newUser" action="/register">
			<form:errors path="userName" class="error" />
			<form:errors path="email" class="error" />
			<form:errors path="password" class="error" />
			<form:errors path="confirm" class="error" />
    <!-- 2 column grid layout with text inputs-->
			    <div class="row">
			        <div class="col-md-6 mb-4">
			            <div class="form-outline">
			                <div class="input-container">
			                    <form:label for="userName" path="userName">User Name</form:label>
			                    <form:input type="text" class="form-control" id="userName" path="userName" />
			                </div>
			            </div>
			        </div>
			        <div class="col-md-6 mb-4">
			            <div class="form-outline">
			                <div class="input-container">
			                    <form:label for="email" path="email">Email</form:label>
			                    <form:input type="email" class="form-control" id="email" path="email" />
			                </div>
			            </div>
			        </div>
			    </div>
			
			    <div class="form-outline mb-4">
			        <form:label for="password1" path="password">Password</form:label>
			        <form:input type="password" class="form-control" id="password1" path="password" />
			    </div>
			
			    <div class="form-outline mb-4">
			        <form:label for="confirm" path="confirm">Confirm Password</form:label>
			        <form:input type="password" class="form-control" id="confirm" path="confirm" />
			    </div>
			
			    <!-- Submit button -->
			    <button type="submit" class="btn btn-primary btn-block mb-4">
			        Sign up
			    </button>
			</form:form>
			
			<hr class="style-six">
		
			<h2 class="text-center fw-bold mb-3">Login</h2>
			<form:form modelAttribute="newLogin" action="/login">
    			<!-- 2 column grid layout with text inputs-->
    			<c:if test="${not empty loginError}">
				    <p class="error">${loginError}</p>
				</c:if>
			    <form:errors path="email" class="error" />
				<form:errors path="password" class="error" />
			    <div class="row">
			        <div class="col-md-6 mb-4">
			            <div class="form-outline">
			                <form:label for="email" path="email">Email</form:label>
			                <form:input type="email" class="form-control" id="email" path="email" />
			            </div>
			        </div>
			        <div class="col-md-6 mb-4">
			            <div class="form-outline mb-4">
					        <form:label for="password1" path="password">Password</form:label>
					        <form:input type="password" class="form-control" id="password1" path="password" />
					    </div>
			        </div>
			    </div>
			    <!-- Submit button -->
			    <button type="submit" class="btn btn-primary btn-block mb-4">
			        Login
			    </button>
			</form:form>
          </div>
        </div>
      </div>

      <div class="col-lg-6 mb-5 mb-lg-0">
        <img src="${pageContext.request.contextPath}/images/bookclub.jpg" class="w-100 rounded-4 shadow-4"
          alt="" />
      </div>
    </div>
  </div>
  <!-- Jumbotron -->
</section>
<!-- Section: Design Block -->

</body>
</html>