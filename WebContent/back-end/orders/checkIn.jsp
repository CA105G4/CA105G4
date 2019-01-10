<%@page import="com.employee.model.EmployeeVO"%>
<%@page import="com.orders.model.OrdersCheckInOutVO"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.*"%>
<%@page import="com.orders.model.OrdersService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	EmployeeVO empVO = (EmployeeVO)session.getAttribute("employeeVO");

	OrdersService ordSvc = new OrdersService();
	java.util.Date udate = new java.util.Date();
	java.sql.Date today = new java.sql.Date(udate.getTime());
	List<OrdersCheckInOutVO> checkInlist = ordSvc.findCheckInByOrdJoinOD(today, empVO.getBraID());
	pageContext.setAttribute("checkInlist", checkInlist);
	System.out.println(checkInlist.size());
	System.out.println("checkin = " + empVO.getBraID());
%>

<!--WebSocket-->
<% 
	if(request.getAttribute("refreshRoomState") != null){
		String refreshRoomState = (String)request.getAttribute("refreshRoomState");
		pageContext.setAttribute("refreshRoomState", refreshRoomState);
	}
%>
<!--WebSocket-->

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Checkin</title>

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
					<li class="breadcrumb-item active">Checkin</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<h1>Check In</h1>
				<hr>
				<div class="container-fluid">
					<h2>Check In</h2>
					<div align="center">
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
					</div>
					<!--開始自由發揮-->
						<table class="table table-bordered table-striped table-hover">
							<tr>
								<th>訂單編號</th>
								<th>會員姓名</th>
								<th>房型</th>
								<th>房號</th>
								<th>房間數</th>
								<th>人數</th>
								<th>入住時間</th>
								<th>退房時間</th>
								<th></th>
								<th></th>
							</tr>
					
							<c:forEach var="ordcheckInVO" items="${checkInlist}">
								<tr>
									<td>${ordcheckInVO.ordID}</td>
							<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
									<td>${memSvc.getOneMem(ordcheckInVO.getMemID()).getMemName()}</td>
							<jsp:useBean id="rtSvc" scope="page" class="com.roomType.model.RoomTypeService" />		
									<td>${rtSvc.getOneRoomType(ordcheckInVO.getRtID()).getRtName()}</td>
							<jsp:useBean id="roomSvc" scope="page" class="com.room.model.RoomService" />		
									<td><span ${ordcheckInVO.roomID == null? "style='color:#FF3333;font-weight:bold;'" : ""}>
									${ordcheckInVO.roomID == null? "尚未分房" : roomSvc.getOneRoom(ordcheckInVO.getRoomID()).getRoomNo()}
										</span>
									</td>
									<td>${ordcheckInVO.numOfRoom}</td>
									<td>${ordcheckInVO.numOfGuest}</td> 
									<td>${ordcheckInVO.checkIn}</td>
									<td>${ordcheckInVO.checkOut}</td>
									<td>
									  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" style="margin-bottom: 0px;">
									     <input type="submit" value="分配房間"  ${ordcheckInVO.roomID == null? "class='btn btn-danger'" : "class='btn btn-secondary'"}>
									     <input type="hidden" name="ordID" value="${ordcheckInVO.ordID}">
									     <input type="hidden" name="odID" value="${ordcheckInVO.odID}">
									     <input type="hidden" name="braID" value="${ordcheckInVO.braID}">
									     <input type="hidden" name="rtID" value="${ordcheckInVO.rtID}">
									     <input type="hidden" name="roomID" value="${ordcheckInVO.roomID}">
									     <input type="hidden" name="roomState" value="1">
									     <input type="hidden" name="action"	value="GiveRoom"></FORM>
									</td>
									<td>
									  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" style="margin-bottom: 0px;">
									     <input type="submit" value="CheckIn" ${ordcheckInVO.ordState == 1? "class='btn btn-secondary'" : "class='btn btn-info'"} >
									     <input type="hidden" name="ordID"  value="${ordcheckInVO.ordID}">
									     <input type="hidden" name="action" value="CheckInUpdate"></FORM>
									</td>
								</tr>
							</c:forEach>
						</table>
						
						
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
	
	<!--WebSocket-->
	<script>
	var OrdersPoint = "/OrdersEchoServer";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0,path.indexOf('/',1));
	var endPointURL = "ws://"+host+webCtx+OrdersPoint;
	var refreshRoomState = ${refreshRoomState};
	console.log(refreshRoomState);
	var webSocket;
	
	$(function(){
		connect();
	})
	
	function connect(){
		webSocket = new WebSocket(endPointURL);
		console.log(webSocket);
		
		webSocket.onopen = function(event){
			//alert(event.data);
			console.log("WebSocket connected successful");
			if(refreshRoomState===1){
				console.log("connection:"+refreshRoomState);
				sendMessage();
			}
		};
		
		webSocket.onmessage = function(event){
			console.log(event.data);
			<% pageContext.setAttribute("refreshRoomState", "0"); %>
			//alert(event.data);
		};
		
		webSocket.onclose = function(event){
			webSocket.close();
			console.log("WebSocket disconnected");
		};
	}
	
	function sendMessage(){
		webSocket.send('refreshRoomState');
		console.log('refreshRoomState');
	}
	</script>
	<!--WebSocket-->

</body>

</html>
