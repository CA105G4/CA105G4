<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="java.util.*"%>

<% 
	RoomService roomSvc = new RoomService();
	List<RoomVO> list = roomSvc.getAll();
	pageContext.setAttribute("list", list);
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

<title>房間列表</title>

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
<style>
table, th, td {
	border: 2px solid #CCCCFF;
}
</style>
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
					<li class="breadcrumb-item active">房間列表</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<h1>查詢所有房間</h1>
				<hr>
				<div class="container-fluid">
					<!-- 錯誤驗證 -->
					<c:if test="${not empty errMessage}">
						<font style="color: red">請修正以下錯誤</font>
						<ul>
							<c:forEach var="message" items="${errMessage}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<table>
						<tr>
							<th>房間編號</th>
							<th class="text-center">房型</th>
							<th>店別</th>
							<th>房號</th>
							<th>房間狀態</th>
							<th>旅客姓名</th>
							<th>修改</th>
						</tr>
						<%@ include file="page1.file"%>
						<jsp:useBean id="roomTypeSvc" scope="page"
							class="com.roomType.model.RoomTypeService" />
						<c:forEach var="roomVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">

							<tr>
								<td>${roomVO.roomID}</td>
								<td>${roomTypeSvc.getOneRoomType(roomVO.roomTypeID).rtName}</td>
								<td>${roomVO.braID}</td>
								<td>${roomVO.roomNo}</td>
								<td align="center">${roomVO.roomState}</td>
								<td>${roomVO.memName}</td>
								<td>
									<FORM method="post"
										action="<%=request.getContextPath()%>/room/room.do"
										style="margin-bottom: 0px;">
										<input class="btn-danger" type="submit" value="修改"> <input
											type="hidden" name="roomID" value="${roomVO.roomID}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>
							</tr>

						</c:forEach>
					</table>
					<%@ include file="page2.file"%>
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

