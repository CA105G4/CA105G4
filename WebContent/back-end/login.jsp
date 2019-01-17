<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>員工登入</title>

<!-- Bootstrap core CSS-->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">

<!-- Custom styles for this template-->
<link href="css/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">

	<div class="container">
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">Employee Login</div>
			<div class="card-body">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/Emplogin.do">
					<c:if test="${not empty errorMsgs}">
						<font color='red'>請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<BR>
					<div class="form-group">
						<div class="form-label-group">

							<input type="text" id="inputEmail" class="form-control"
								placeholder="Email address" required="required"
								autofocus="autofocus" name="empAcc" id="empAcc"
								value="${param.empAcc}"> <label for="inputEmail">Employee
								address</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-label-group">
							<input type="password" id="inputPassword" class="form-control"
								placeholder="Password" required="required" name="empPsw"
								id="empPsw" value="${param.empPsw}"> <label
								for="inputPassword">Password</label>
						</div>
					</div>
					<div class="form-group">
						<div class="checkbox"></div>
					</div>
					<!--             <a class="btn btn-primary btn-block" href="index.html"name="button" value="login">Login</a> -->
					<button type="submit" name="button"
						class="btn btn-primary btn-block" value="login">登入</button>
				</form>

			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

</body>

</html>
