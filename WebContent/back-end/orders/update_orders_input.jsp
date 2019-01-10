<%@page import="com.orders.model.OrdersVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
  OrdersVO ordVO = (OrdersVO) request.getAttribute("ordVO");
%>

<%-- <%= ordVO == null %> --%>
<%-- --${ordVO.ordID}-- --%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin - Blank Page</title>

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
					<li class="breadcrumb-item"><a href="index.html">Dashboard</a>
					</li>
					<li class="breadcrumb-item active">Blank Page</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<h1>修改訂單資料</h1>
				<hr>
				<div class="container-fluid" >
				<br>
					<div class="row">
						<div class="col-xs-12 col-sm-5">
							
						</div>
						<div class="col-xs-12 col-sm-2">
							
								<c:if test="${not empty errorMsgs}">
									<font style="color:red">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color:red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>		
											
						</div>
						<div class="col-xs-12 col-sm-5">
							
						</div>
					</div>
					<div class="row" >  
						<div class="col-xs-12 col-sm-3">
							
						</div>
						<div class="col-xs-12 col-sm-6">
							<div align="center">
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" name="form1">
								<table>
									<tr>
										<td>訂單編號:</td>
										<td><input type="TEXT" name="ordID" size="45" class="form-control" 
											 value="<%= ordVO.getOrdID() %>" readonly="true"/></td>
									</tr>
									<tr>
										<td>會員編號:</td>
										<td><input type="TEXT" name="memID" size="45" class="form-control"
											 value="<%= ordVO.getMemID() %>" readonly="true"/></td> 
									</tr>
									<tr>
										<td>分店編號:</td>
										<td><input type="TEXT" name="braID" size="45" class="form-control"
											 value="<%= ordVO.getBraID() %>" /></td>
									</tr>
									<tr>
										<td>房間數量:</td>
										<td><input type="TEXT" name="numOfRoom" size="45" class="form-control"
											 value="<%= ordVO.getNumOfRoom() %>" /></td>
									</tr>
									<tr>
										<td>訂單種類:</td>
										<td><select size="1" name="ordType" class="custom-select">
												<option value="0" ${( 0 == ordVO.ordType)?'selected':'' }>線上0</option>
												<option value="1" ${( 1 == ordVO.ordType)?'selected':'' }>臨櫃1</option>
												<option value="2" ${( 2 == ordVO.ordType)?'selected':'' }>打工換宿2</option>
											</select></td>
									</tr>
									<tr>
										<td>入住人數:</td>
										<td><input type="TEXT" name="numOfGuest" size="45" class="form-control"
											 value="<%= ordVO.getNumOfGuest() %>" /></td>
									</tr>
									<tr>
										<td>總金額:</td>
										<td><input type="TEXT" name="amount" size="45" class="form-control"
											 value="<%= ordVO.getAmount() %>" /></td>
									</tr>
									<tr>
										<td>訂金:</td>
										<td><input type="TEXT" name="bond" size="45" class="form-control"
											 value="<%= ordVO.getBond() %>" /></td>
									</tr>
									<tr>
										<td>付款方式:</td>
										<td><select size="1" name="payment" class="custom-select">
												<option value="0" ${( 0 == ordVO.payment)?'selected':'' }>現金0</option>
												<option value="1" ${( 1 == ordVO.payment)?'selected':'' }>信用卡1</option>
											</select></td>										
									</tr>
									<tr>
										<td>訂單狀態:</td>
										<td><select size="1" name="ordState" class="custom-select">
												<option value="0" ${( 0 == ordVO.ordState)?'selected':'' }>預訂0</option>
												<option value="1" ${( 1 == ordVO.ordState)?'selected':'' }>入住1</option>
												<option value="2" ${( 2 == ordVO.ordState)?'selected':'' }>退房2</option>
												<option value="3" ${( 3 == ordVO.ordState)?'selected':'' }>退訂3</option>
											</select></td>
									</tr>
									<tr>
										<td>訂單成立時間:</td>
										<td><input type="TEXT" name="OrdTime" id="ordsysdate" size="45" class="form-control"
											 value="<%= ordVO.getOrdTime() %>" /></td>
									</tr>
								</table>
								<br>
									<input type="hidden" name="action" value="update">
									<input type="hidden" name="ordID" value="<%= ordVO.getOrdID() %>">
									<input type="submit" value="送出修改" class="btn btn-info" >
									<button type="button" class="btn btn-info">
										<a href='<%=request.getContextPath()%>/back-end/orders/select_orders_page.jsp' style="color:#fff">返回</a>
									</button>
								</FORM>							
							</div>
						</div>
						<div class="col-xs-12 col-sm-3">
							
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

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>

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
	
	<script>
	$.datetimepicker.setLocale('zh'); 
	$('#ordsysdate').datetimepicker({
        theme: '',          
        timepicker: false,             
	       format: 'Y-m-d',
	       value: new Date(),
	       minDate: new Date(),
     });
	</script>
	
</body>

</html>
