<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="java.util.*"%>

<%
	RoomService roomSvc = new RoomService();
	List<RoomVO> listByBranch = roomSvc.getRoomByBranch("B01");
	List<RoomVO> list = roomSvc.getAll();
	pageContext.setAttribute("listByBranch", listByBranch);
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
					<li class="breadcrumb-item"><a href="index.html">Dashboard</a>
					</li>
					<li class="breadcrumb-item active">Blank Page</li>
				</ol>
				<!-- Icon Card -->
				<div class="row">
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-primary o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-user-clock"></i>
								</div>
								<div class="mr-5">共 xx 間 保留!</div>
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
								<div class="mr-5">共 xx 間 打掃中!</div>
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
								<div class="mr-5">共 xx 間 空房!</div>
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
								<div class="mr-5">共 xx 間 入住!</div>
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
								${roomVO.roomState==1 ? "style='background-Color:#F5DEB3;border: 2px solid #000000;'" : 
												 (roomVO.roomState==2) ? "style='background-Color:paleturquoise;border: 2px solid #000000;'": 
												 (roomVO.roomState==3) ? "style='background-Color:palegreen;border: 2px solid #000000;'" : 
												 (roomVO.roomState==4) ? "style='background-Color:red;border: 2px solid #000000;'": 
												 (roomVO.roomState==5) ? "style='background-Color:yellow;border: 2px solid #000000;'" : ''}>
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
<%-- 					<a class="btn btn-primary" href="<%=request.getContextPath()%>/back-end/EmpLogout.do">Logout</a> --%>
				</div>
			</div>
		</div>
	</div>

	<script>
        $(function(){
       		$("select").change(function(){
       			$.ajax({
       				type:"GET",
       				url:"<%=request.getContextPath()%>/room/AjaxResRoom.do",
					data : {
							"action" : 'UpdateRoomState',
							"roomNo" : $(this).parent().parent().parent().attr("id"),
							"roomState" : $(this).val()
							},
					dataType : 'json',
					success : function(res) {
						console.log(res);
						$(this).val(res.roomState);
						console.log(res.roomState);
	
						var roomNo = "#" + res.roomNo;
						console.log(roomNo);
						if (res.roomState === 1) {
							$(roomNo)
									.attr("style",
											'background-Color:#F5DEB3 ; border:2px solid #000000;');
						} else if (res.roomState === 2) {
							$(roomNo)
									.attr("style",
											'background-Color:paleturquoise ; border:2px solid #000000;');
						} else if (res.roomState === 3) {
							$(roomNo)
									.attr("style",
											'background-Color:palegreen ; border:2px solid #000000;');
							return;
						} else if (res.roomState === 4) {
							$(roomNo)
									.attr("style",
											'background-Color:red ; border:2px solid #000000;');
							return;
						} else if (res.roomState === 5) {
							$(roomNo)
									.attr("style",
											'background-Color:yellow ; border:2px solid #000000;');
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

</body>

</html>

