<%@page import="java.util.*"%>
<%@page import="com.orderDetail.model.OrderDetailVO"%>
<%@page import="com.orders.model.OrdersVO"%>
<%@page import="com.orders.model.OrdersService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<%	
	String getmemID = (String)request.getAttribute("memID");
System.out.println("jsp收到的memID"+getmemID);
	OrdersService odSvc = new OrdersService();
	String ordID = odSvc.findNewOrderID(getmemID);
	
	OrdersVO ordVO = odSvc.getOneOrders(ordID);
	pageContext.setAttribute("ordVO", ordVO);
	
	Set<OrderDetailVO> odSet = odSvc.getOrderDetailByOrders(ordID);
	OrderDetailVO odVO = odSet.iterator().next();
	pageContext.setAttribute("odVO", odVO);
%>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>確認新增訂單</title>

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
					<li class="breadcrumb-item active">確認新增訂單</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<h1>確認新增訂單！</h1>
				<hr>
				<div class="container-fluid">
					<div class="row">
						<div class="col-xs-12 col-sm-3">
						</div>
						<div class="col-xs-12 col-sm-6">
						
							<table class="table table-bordered table-striped table-hover">
								<caption></caption>
								<tr>
									<th>訂單編號：</th>
									<td>${ordVO.ordID}</td>
								</tr>
								<tr>
									<th>會員姓名：</th>
					<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
									<td>${memSvc.getOneMem(ordVO.memID).getMemName()}</td>
								</tr>
								<tr>
					<jsp:useBean id="braSvc" scope="page" class="com.branch.model.BranchService" />
									<th>分店名稱：</th>
									<td>${braSvc.getOneByID(ordVO.braID).getBraName()}</td>
								</tr>
								<tr>
									<th>訂房數量：</th>
									<td>${ordVO.numOfRoom}</td>
								</tr>
								<tr>
									<th>訂單種類：</th>
									<td>${ordTypeMap.get(ordVO.ordType)}</td>
								</tr>
								<tr>
									<th>入住時間：</th>
									<td>${odVO.checkIn}</td>
								</tr>
								<tr>
									<th>退房時間：</th>
									<td>${odVO.checkOut}</td>
								</tr>
								<tr>
									<th>付款方式：</th>
									<td>${paymentMap.get(ordVO.payment)}</td>
								</tr>
								<tr>
									<th>下訂時間：</th>
									<td>${ordVO.ordTime}</td>
								</tr>
							</table>
							<br>
							<div align="center">
								<table>
									<c:forEach var="rtIDandNum" items="${rtIDandNumMap.entrySet()}">
										<tr>
											<th>
												<img src="<%=request.getContextPath()%>/roomType/roomTypeImg.do?rtID=${rtIDandNum.getKey()}" class="img-fluid showrtpic" style='border-radius: 10%; width:400px; padding-right: 15px;' >
											</th>
							<jsp:useBean id="rtSvc" scope="page" class="com.roomType.model.RoomTypeService" />
											<td>${rtSvc.getOneRoomType(rtIDandNum.getKey()).rtName} : </td>
											<td>${rtIDandNum.getValue()} 間</td>
										</tr>
									</c:forEach>
									<tr>
										<th>訂單總金額：</th>
										<td>${ordVO.amount}</td>
									</tr>
									<tr>
										<th>訂金：</th>
										<td>${ordVO.bond}</td>
									</tr>
								</table>
							</div>
							<br>
							<div align="center">
									<button type="button" class="btn btn-info" id="confirmtoPay">確認</button>
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
	
	<!-- sweetalert-->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	
	<script>
		$(function(){
		    $('#confirmtoPay').on('click', function(){
		    	swal({
		            title: "訂單新增成功!", text: "感謝您的支持", type: "success", icon: "success",showCancelButton: true, confirmButtonText: "前往"
		          }).then(
		           function (result) {
		           if(result){
		        	   document.location.href='<%=request.getContextPath()%>/back-end/orders/listAllOrders.jsp';
		           }
		           });
		    });
		});

	</script>

</body>

</html>
