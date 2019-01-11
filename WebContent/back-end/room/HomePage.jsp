<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="java.util.*"%>

<% 
	RoomVO roomVO = (RoomVO)(request.getAttribute("roomVO"));
%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>房間查詢</title>

<!-- Bootstrap core CSS-->
<link
	href="<%=request.getContextPath() %>/back-end/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom fonts for this template-->
<link
	href="<%=request.getContextPath() %>/back-end/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Page level plugin CSS-->
<link
	href="<%=request.getContextPath() %>/back-end/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="<%=request.getContextPath() %>/back-end/css/sb-admin.css"
	rel="stylesheet">

</head>

<body id="page-top">

	<!-- Navbar -->

	<jsp:include page="/back-end/navbar.jsp" />

	<!-- /Navbar -->

	<div id="wrapper">

		<!-- Sidebar -->

		<jsp:include page="/back-end/sidebar.jsp" />

		<!-- /Sidebar -->

		<div id="content-wrapper">

			<div class="container-fluid">
				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item">
						<a href="<%=request.getContextPath()%>/back-end/room/roomState.jsp">首頁</a>
					</li>
					<li class="breadcrumb-item active">房間查詢</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<h1>房間查詢</h1>
				<hr>
				<div class="container-fluid">
					<!-- 錯誤驗證 -->
<%-- 					<c:if test="${not empty errMessage}"> --%>
<!-- 						<font style="color: red">請修正以下錯誤</font> -->
<!-- 						<ul> -->
<%-- 							<c:forEach var="message" items="${errMessage}"> --%>
<%-- 								<li style="color: red">${message}</li> --%>
<%-- 							</c:forEach> --%>
<!-- 						</ul> -->
<%-- 					</c:if> --%>

					<ul>
						<jsp:useBean id="roomSvc" class="com.room.model.RoomService" />
						<li>
							<form method="post"
								action="<%=request.getContextPath()%>/room/room.do">
								<b>選擇房間編號:</b> <select name="roomID">
									<c:forEach var="roomVO" items="${roomSvc.all}">
										<option value="${roomVO.roomID}">${roomVO.roomID}
									</c:forEach>
								</select> <input type="hidden" name="action" value="getOne_For_Display">
								<input class="btn-warning" type="submit" value="送出">
							</form>
						</li>
						<li>
							<form method="post"
								action="<%=request.getContextPath()%>/room/room.do">
								<b>選擇房間號碼:</b> <select name="roomID">
									<c:forEach var="roomVO" items="${roomSvc.all}">
										<option value="${roomVO.roomID}">${roomVO.roomNo }
									</c:forEach>
								</select> <input type="hidden" name="action" value="getOne_For_Display">
								<input class="btn-warning" type="submit" value="送出">
							</form>
						</li>
						<li>
							<jsp:useBean id="braSvc" class="com.branch.model.BranchService"/>
							<form method="post" action="<%=request.getContextPath()%>/room/room.do">
								<b>選擇分店:</b>
								<select name="braID">
									<c:forEach var="branchVO" items="${braSvc.all}">
									<option value="${branchVO.braID}">${branchVO.braName}</option>
									</c:forEach>
								</select> 
								<input type="hidden" name="action" value="getRoomByBranch_For_Display"> 
								<input class="btn-warning" type="submit" value="送出">
							</form>
						</li>
					</ul>
				</div>
				<!-- Page Content 這邊開始自由發揮結束-->
			</div>
			<!-- /.container-fluid -->

			<!-- Sticky Footer -->
			<footer class="sticky-footer">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>© M.C.P.I.G 2019</span>
					</div>
				</div>
			</footer>

		</div>
		<!-- /.content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>


	<!-- Bootstrap core JavaScript-->
	<script
		src="<%=request.getContextPath() %>/back-end/vendor/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath() %>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="<%=request.getContextPath() %>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="<%=request.getContextPath() %>/back-end/js/sb-admin.min.js"></script>

</body>

</html>



