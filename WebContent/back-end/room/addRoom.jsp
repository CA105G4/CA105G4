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

<title>新增房間</title>

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
					<li class="breadcrumb-item active">新增房間</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
 <h1>新增房間</h1>
          <hr>
          	<div class="container-fluid">
<!--           		<div class="row"> -->
					<!-- 錯誤驗證 -->
<%-- 					<c:if test="${not empty errMessage}"> --%>
<!-- 						<font style="color:red">請修正以下錯誤</font> -->
<!-- 						<ul> -->
<%-- 						<c:forEach var="message" items="${errMessage}"> --%>
<%-- 							<li style="color:red">${message}</li> --%>
<%-- 						</c:forEach> --%>
<!-- 						</ul>	 -->
<%-- 					</c:if> --%>
<!-- 				</div> -->
				<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12 col-sm-2">
						<c:if test="${not empty errMessage}">
						<font style="color:red">請修正以下錯誤</font>
						<ul>
						<c:forEach var="message" items="${errMessage}">
							<li style="color:red">${message}</li>
						</c:forEach>
						</ul>	
					</c:if>
					</div>
					<div class="col-xs-12 col-sm-8">
					<form method="post" action="<%=request.getContextPath()%>/room/room.do" style="margin-bottom: 0px;">
						<jsp:useBean id="roomTypeSvc" class="com.roomType.model.RoomTypeService"/>
						<jsp:useBean id="braSvc" class="com.branch.model.BranchService" />
						<table class="table table-bordered table-striped table-hover">
							<tr>	
								<td class="text-center">房型:</td>
								<td class="text-center">
									<select size="1" name="roomTypeID" class="form-control">
										<c:forEach var="roomTypeVO" items="${roomTypeSvc.all}" begin="0" end="4">
										<option value="${roomTypeVO.rtID}">${roomTypeSvc.getOneRoomType(roomTypeVO.rtID).rtName}
										</c:forEach>
									</select>
								</td>
								<!--<input type="text" name="roomTypeID" value="${roomVO.roomTypeID}" class="form-control" size="45">-->
							</tr>
							<tr>	
								<td class="text-center">店別:</td>
								<td class="text-center">
								<select name="braID" class="form-control">
									<c:forEach var="branchVO" items="${braSvc.all}">
									<option value="${branchVO.braID}">${branchVO.braName}</option>
									</c:forEach>
								</select>
								</td>
							</tr>	
							<tr>	
								<td class="text-center">房號:</td>
								<td class="text-center"><input type="text" name="roomNo" value="${roomVO.roomNo}" class="form-control" size="20"></td>
							</tr>
							<tr>
								<td class="text-center">房間狀態:</td>
								<td class="text-center">
								<select name="roomState" class="form-control">
								<c:forEach var="roomState" items="${roomStateMap}">
									<option value="${roomState.key}">${roomState.value}</option>
								</c:forEach>
								</select>				
								</td>
							<!--	<input type="text" name="roomState" value="${roomVO.roomState}" class="form-control"> -->
							</tr>
							<tr>	
								<td class="text-center">旅客姓名:</td>
								<td class="text-center"><input type="text" name="memName" value="${roomVO.memName}" class="form-control" size="20"></td>
							</tr>
							<tr>
								<td></td>
								<td class="text-center">
									<input class="btn-info form-control" type="submit" value="送出">
									<input type="hidden" name="action" value="confirm_Add">
								</td>
							</tr>	
						</table>
					</form>
					</div>
					<div class="col-xs-12 col-sm-2"></div>
				</div>
			  </div>
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

