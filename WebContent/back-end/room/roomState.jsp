<%@page import="com.employee.model.EmployeeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="java.util.*"%>

<%
	EmployeeVO empVO = (EmployeeVO)session.getAttribute("employeeVO");
	String braId = empVO.getBraID();
			
	RoomService roomSvc = new RoomService();
	
	List<RoomVO> listByBranch = roomSvc.getRoomByBranch(braId);
	Integer empty = roomSvc.getEachRoomState(1, braId);
	Integer checkIn = roomSvc.getEachRoomState(2, braId);
	Integer clean = roomSvc.getEachRoomState(3, braId);
	Integer outOfOrder = roomSvc.getEachRoomState(4, braId);
	Integer reserved = roomSvc.getEachRoomState(5, braId);

	pageContext.setAttribute("listByBranch", listByBranch);
	
	System.out.println("roomState = " + braId);
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

<title>後台首頁-當日房況</title>

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
<style>
.grid-container {
	grid-template-columns: repeat(5, 20%);
	text-align: right;
}

.flex-container {
	justify-content: start;
}

.modal-backdrop {
	display: none;
}
</style>
</head>

<body id="page-top">

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
				</ol>
				<!-- Icon Card -->
				<div class="row">
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-primary o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-user-clock"></i>
								</div>
								<div class="mr-5 reservedText">共  <%=reserved%>間 保留!</div>
								<input type="hidden" name="reserved" id="reserved" value="<%=reserved%>">
								<div class="mr-5" ><input type="hidden" name="outOfOrder" id="outOfOrder" value="<%=outOfOrder%>"></div>
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#">
								<span class="float-left">保留</span> <span class="float-right">
									<i class="fas fa-angle-right"></i>
							</span>
							</a>
						</div>
					</div>
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-warning o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-stopwatch"></i>
								</div>
								<div class="mr-5 cleanText">共  <%=clean%>間 打掃!</div>
								<input type="hidden" name="clean" id="clean" value="<%=clean%>">
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#">
								<span class="float-left">打掃</span> <span class="float-right">
									<i class="fas fa-angle-right"></i>
							</span>
							</a>
						</div>
					</div>
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-success o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-edit"></i>
								</div>
								<div class="mr-5 emptyText">共  <%=empty%>間 空房!</div>
								<input type="hidden" name="empty" id="empty" value="<%=empty%>">
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#">
								<span class="float-left">空房</span> <span class="float-right">
									<i class="fas fa-angle-right"></i>
							</span>
							</a>
						</div>
					</div>
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-danger o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-user-check"></i>
								</div>
								<div class="mr-5 checkInText">共  <%=checkIn%>間 入住!</div>
								<input type="hidden" name="checkIn" id="checkIn" value="<%=checkIn%>">
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#">
								<span class="float-left">入住</span> <span class="float-right">
									<i class="fas fa-angle-right"></i>
							</span>
							</a>
						</div>
					</div>
				</div>


				<!-- Page Content 這邊開始自由發揮-->
				<hr>
				<div class="container-fluid">
					<div class="col-xs-12 col-sm-6 col-sm-offset-3 grid-container"
						style="display: grid; max-width: 100%">
						<c:forEach var="roomVO" items="${listByBranch}">
							<div class="btn" id="${roomVO.roomNo}"
								${roomVO.roomState==1 ? "style='background-Color:#28a745;border: 2px solid #000000;'" : 
												 (roomVO.roomState==2) ? "style='background-Color:#dc3545;border: 2px solid #000000;'": 
												 (roomVO.roomState==3) ? "style='background-Color:#FFFF00;border: 2px solid #000000;'" : 
												 (roomVO.roomState==4) ? "style='background-Color:#F5DEB3;border: 2px solid #000000;'": 
												 (roomVO.roomState==5) ? "style='background-Color:#007bff;border: 2px solid #000000;'" : ''}>
								<!-- 1.空房  2.入住  3.打掃  4.維修  5.保留  -->
								<div class="flex-container" style="display: flex;">
									<input type="hidden" name="roomNo" id="${roomVO.roomNo}"
										value="${roomVO.roomNo}">${roomVO.roomNo}
									<div>
										<select class="roomState" id="${roomVO.roomNo}">
											<c:forEach var="roomState" items="${roomStateMap}">
												<!-- 1.空房  2.入住  3.打掃  4.維修  5.保留 -->
												<option value="${roomState.key}"
													${(roomVO.roomState == roomState.key) ? 'selected' : '' }>${roomState.value}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<br>
								<br>
								<br>
								<div class="text-right">旅客姓名:${ roomVO.memName== null ? "MCPIG"  :  roomVO.memName}</div>
							</div>
						</c:forEach>
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

	<script>
        $(function(){
       		$("select").change(function(){
       			$.ajax({
       				type:"GET",
       				url:"<%=request.getContextPath()%>/room/AjaxResRoom.do",
					data : {
							"action" : 'UpdateRoomState',
							"roomNo" : $(this).parent().parent().parent().attr("id"),
							"roomState" : $(this).val(),
							"empty" : $("#empty").val(),
							"checkIn" : $("#checkIn").val(),
							"clean" : $("#clean").val(),
							"outOfOrder" :$("#outOfOrder").val(),
							"reserved" : $("#reserved").val(),
							},
					dataType : 'json',
					success : function(res) {
							console.log(res);
							
							$(this).val(res.roomState);
							console.log(res.roomState);
							
							$("#empty").val(res.empty);
							$("#checkIn").val(res.checkIn);
							$("#clean").val(res.clean);
							$("#outOfOrder").val(res.outOfOrder);
							$("#reserved").val(res.reserved);
							
							//更換上方各房間狀態數
							$(".emptyText").text("共 " +res.empty+"間  空房!");
							$(".checkInText").text("共 " +res.checkIn+"間  入住!");
							$(".cleanText").text("共 " +res.clean+"間  打掃!");
							$(".reservedText").text("共 " +res.reserved+"間  保留!");
							
							console.log(res.empty);
							console.log(res.checkIn);
							console.log(res.clean);
							console.log(res.outOfOrder);
							console.log(res.reserved);
							
							
							var roomNo = "#" + res.roomNo;
							console.log(roomNo);
							if (res.roomState === 1) {
								$(roomNo).attr("style",'background-Color:#28a745 ; border:2px solid #000000;');
							} else if (res.roomState === 2) {
								$(roomNo).attr("style",'background-Color:#dc3545 ; border:2px solid #000000;');
							} else if (res.roomState === 3) {
								$(roomNo).attr("style",'background-Color:#FFFF00 ; border:2px solid #000000;');
							} else if (res.roomState === 4) {
								$(roomNo).attr("style",'background-Color:#F5DEB3 ; border:2px solid #000000;');
							} else if (res.roomState === 5) {
								$(roomNo).attr("style",'background-Color:#007bff ; border:2px solid #000000;');
							}
						},
					error : function() {
						alert("發生錯誤請小心!!!")
					}
				})
			})
		});
	</script>

	<!-- Bootstrap core JavaScript-->
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>
		
	<!--WebSocket-->
	<script>
	var OrdersPoint = "/OrdersEchoServer";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0,path.indexOf('/',1));
	var endPointURL = "ws://"+host+webCtx+OrdersPoint;
	
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
		};
		
		webSocket.onmessage = function(event){
			console.log(event.data);
			alert("我重刷囉!");
			window.location.reload();
		};
		
		webSocket.onclose = function(event){
			webSocket.close();
			console.log("WebSocket disconnected");
		};
	}
	
	</script>

</body>

</html>

