<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.workExchange.model.*"%>
<%@ page import="java.util.*"%>

<%
	WorkExchangeVO workExchangeVO = (WorkExchangeVO) (request.getAttribute("workExchangeVO"));
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

<title>修改打工需求</title>

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
  	table, th, td {
    	text-align:center;
  	}
  td{
  	width:680px;
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
					<li class="breadcrumb-item active">修改打工需求</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
          <h1>修改打工需求</h1>
          <hr>
          <div class="container-fluid">
			<div class="row">
			<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
			<jsp:useBean id="employeeSvc" class="com.employee.model.EmployeeService"/>
			<jsp:useBean id="roomTypeSvc" class="com.roomType.model.RoomTypeService" />
			<div class="col-xs-12 col-sm-2"></div>
			<div class="col-xs-12 col-sm-8">
			<Form method="post" action="<%=request.getContextPath()%>/workExchange/workExchange.do" enctype="multipart/form-data" style="margin-bottom: 0px;">	
				<table class="table table-bordered table-striped table-hover">
					<tr>
						<th>需求編號</th><td>${workExchangeVO.weID}</td>
					</tr>
					<tr>
						<th>創建員工</th><td>${employeeSvc.getOneEmp(workExchangeVO.empID).empName}
						<input type="hidden" name="empID" value="${workExchangeVO.empID}"></td>
					</tr>
					<tr>
						<th>會員姓名</th><td>${memberSvc.getOneMem(workExchangeVO.memID).memName}
						<input type="hidden" name="memID" value="${workExchangeVO.memID}"></td>
					</tr>
					<tr>
						<th>房型名稱</th><td>${roomTypeSvc.getOneRoomType(workExchangeVO.rtID).rtName}
						<input type="hidden" name="rtID" value="${workExchangeVO.rtID}"></td>
					</tr>
					<tr>
						<th>需求名稱</th><td>${workExchangeVO.weName}
						<input type="hidden" name="weName" value="${workExchangeVO.weName}"></td>
					</tr>
					<tr>
						<th>需求內容</th><td>${workExchangeVO.weContent}
						<input type="hidden" name="weContent" value="${workExchangeVO.weContent}"></td>
					</tr>
					<tr>
						<th>需求照片</th>
						<td><input type="file" name="wePic">
<%-- 						<input type="hidden" name="wePic" value="${workExchangeVO.wePic}"> --%>
						</td>
					</tr>
					<tr>
						<th>需求影片</th>
						<td><input type="file" name="weVideo">
<%-- 						<input type="hidden" name="weVideo" value="${workExchangeVO.weVideo}"> --%>
						</td>
					</tr>
					<tr>
						<th>開始時間</th><td>${workExchangeVO.weStart}
						<input type="hidden" name="weStart" value="${workExchangeVO.weStart}"></td>
					</tr>
					<tr>
						<th>結束時間</th><td>${workExchangeVO.weEnd}
						<input type="hidden" name="weEnd" value="${workExchangeVO.weEnd}"></td>
					</tr>
				</table>
					<input type="hidden" name="weID" value="${workExchangeVO.weID}">
					<input type="submit" class="btn-success form-control" value="確認修改">
					<input type="hidden" name="action" value="confirm_Modify">
				</Form>
			  </div>
			<div class="col-xs-12 col-sm-2"></div>			
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
	<script
		src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>

</body>

</html>

