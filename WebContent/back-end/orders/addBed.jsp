<%@page import="com.orderDetail.model.OrderDetailVO"%>
<%@page import="com.orders.model.OrdersVO"%>
<%@page import="com.orders.model.OrdersCheckInOutVO"%>
<%@page import="com.orders.model.OrdersService"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	OrdersVO ordVO = (OrdersVO) request.getAttribute("ordVO");
	OrderDetailVO odVO = (OrderDetailVO)request.getAttribute("odVO");
	
	pageContext.setAttribute("ordVO", ordVO);
	pageContext.setAttribute("odVO", odVO);
%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>訂單明細-加床</title>

<!-- Bootstrap core CSS-->
<link
	href="<%=request.getContextPath()%>/back-end/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom fonts for this template-->
<link
	href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Page level plugin CSS-->
<link
	href="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="<%=request.getContextPath()%>/back-end/css/sb-admin.css"
	rel="stylesheet">

<!-- datepicker-->
<link href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" 
	rel="stylesheet" type="text/css" />

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
					<li class="breadcrumb-item active">訂單明細-加床</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<h1>分配房間</h1>
				<hr>
				<div class="container-fluid">
					<h2>分配房間</h2>
						<div class="row">
							<div class="col-xs-12 col-sm-3">
						
							</div>
							<div class="col-xs-12 col-sm-6">
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" style="margin-bottom: 0px;">
									<!--開始自由發揮-->
									<table class="table table-bordered table-striped table-hover">
										<tr>
											<th>訂單編號</th>
											<td>${ordVO.ordID}</td>
										</tr>
										<tr>	
											<th>房型照片</th>
											<td><img src="<%=request.getContextPath()%>/roomType/roomTypeImg.do?rtID=${odVO.rtID}" class="img-fluid showrtpic" width="400px"></td>
										</tr>
										<tr>	
				<jsp:useBean id="rtSvc" scope="page" class="com.roomType.model.RoomTypeService" />
											<th>房型</th>
											<td>${rtSvc.getOneRoomType(odVO.rtID).getRtName()}</td>
										</tr>
										<tr>	
											<th>特殊需求(加床)</th>
											<td>
												<select name="special" class="custom-select">
														<option value="0" ${odVO.special==0? 'selected' : ''}>不加床</option>
														<option value="1" ${odVO.special==1? 'selected' : ''}>加床</option>
												</select>
											</td>
										</tr>
										<tr>	
											<th>入住時間</th>
											<td>${odVO.checkIn}</td>
										</tr>
										<tr>	
											<th>退房時間</th>
											<td>${odVO.checkOut}</td>
										</tr>
									</table>
									<div align="center">
										<input type="submit" value="確認" class="btn btn-info">											
									    <input type="hidden" name="ordID" value="${ordVO.ordID}">
									    <input type="hidden" name="odID" value="${odVO.odID}">
									    <input type="hidden" name="comeURI" value="${comeURI}">
									    <input type="hidden" name="action"	value="ChangeAmount">
									</div>
								</FORM>
								<br>
									
												
							</div>
							<div class="col-xs-12 col-sm-3">
								
							</div>
						<!-- 結束自由發揮-->
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
		src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>

	<!-- datetimepicker JavaScript-->
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

</body>

</html>
