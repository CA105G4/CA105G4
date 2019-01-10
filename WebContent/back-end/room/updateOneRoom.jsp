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
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin - Blank Page</title>

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
					<li class="breadcrumb-item"><a href="index.html">Dashboard</a>
					</li>
					<li class="breadcrumb-item active">Blank Page</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<h1>修改資料</h1>
				<hr>
				<div class="container-fluid">
					<div class="row">
						<!-- 錯誤驗證 -->
						<c:if test="${not empty errMessage}">
							<font style="color: red">請修正以下錯誤</font>
							<ul>
								<c:forEach var="message" items="${errMessage}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
					</div>
					<div class="row">
						<form method="post"
							action="<%=request.getContextPath()%>/room/room.do"
							style="margin-bottom: 0px;">
							<table>
								<jsp:useBean id="roomTypeSvc"
									class="com.roomType.model.RoomTypeService" />
								<tr>
									<td>房間編號:&nbsp;</td>
									<td class="text-center">${roomVO.roomID}</td>
								</tr>
								<tr>
									<td>房型:&nbsp;</td>
									<td class="text-center">
										${roomTypeSvc.getOneRoomType(roomVO.roomTypeID).rtName} <%-- <select size="1" name="rtID" class="form-control">
									<c:forEach var="roomTypeVO" items="${roomTypeSvc.all}">
									<option value="${roomTypeVO.rtID}">${roomTypeVO.rtID}
									</c:forEach>
								</select>--%> <input type="hidden" name="roomTypeID"
										value="${roomVO.roomTypeID}">
									</td>
								</tr>
								<tr>
									<td>店別:&nbsp;</td>
									<td class="text-center">${roomVO.braID}<input
										type="hidden" name="braID" value="${roomVO.braID}">
									</td>
								</tr>
								<tr>
									<td>房號:&nbsp;</td>
									<td class="text-center">${roomVO.roomNo}<input
										type="hidden" name="roomNo" value="${roomVO.roomNo}">
									</td>

								</tr>
								<tr>
									<td>房間狀態:&nbsp;</td>
									<td><select name="roomState" class="form-control">
											<option value="1">1-空房</option>
											<option value="2">2-入住</option>
											<option value="3">3-打掃</option>
											<option value="4">4-維修</option>
											<option value="5">5-保留</option>
									</select></td>
									<!--<input type="text" name="roomState" value="${roomVO.roomState}" class="form-control">-->
								</tr>
								<tr>
									<td class="text-center">旅客姓名:&nbsp;</td>
									<td><input type="text" name="memName"
										value="${roomVO.memName}" class="form-control"></td>
								</tr>
								<tr>
									<td></td>
									<td><input class="btn btn-info form-control" type="submit"
										value="送出"> <input type="hidden" name="roomID"
										value="${roomVO.roomID}"> <input type="hidden"
										name="action" value="confirm_Modify"></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<!-- Page Content 這邊開始自由發揮結束-->
			</div>
			<!-- /.container-fluid -->

			<!-- Sticky Footer -->
			<footer class="sticky-footer">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright © Your Website 2018</span>
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

