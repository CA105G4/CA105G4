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

<title>打工需求詳情</title>

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
<%-- 	<style>   	
	table, th, td { 
    	border: 2px solid #CCCCFF; 
   	} 
	</style>
--%>
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
					<li class="breadcrumb-item active">打工需求詳情</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<h1>打工需求詳情</h1>
				<hr>
				<div class="container-fluid">
					<div class="row">
						<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />
						<jsp:useBean id="employeeSvc"
							class="com.employee.model.EmployeeService" />
						<jsp:useBean id="roomTypeSvc"
							class="com.roomType.model.RoomTypeService" />
						<div class="col-xs-12 col-sm-3"></div>
						<div class="col-xs-12 col-sm-6">
							<Form method="post"
								action="<%=request.getContextPath()%>/workExchange/workExchange.do"
								style="margin-bottom: 0px;">
								<table>
									<tr>
										<th>需求編號:</th>
										<td>${workExchangeVO.weID}</td>
									</tr>
									<tr>
										<th>創建員工:</th>
										<td>${employeeSvc.getOneEmp(workExchangeVO.empID).empName}</td>
									</tr>
									<!-- 					<tr> -->
									<%-- 						<th>會員姓名:</th><td>${memberSvc.getOneMem(workExchangeVO.memID).memName}</td> --%>
									<!-- 					</tr> -->
									<tr>
										<th>房型名稱:</th>
										<td>${roomTypeSvc.getOneRoomType(workExchangeVO.rtID).rtName}</td>
									</tr>
									<tr>
										<th>需求名稱:</th>
										<td>${workExchangeVO.weName}</td>
									</tr>
									<tr>
										<th>需求內容:</th>
										<td>${workExchangeVO.weContent}</td>
									</tr>
									<tr>
										<th>需求照片:</th>
										<td><img
											src="<%=request.getContextPath()%>/workExchange/workExchangeImg.do?weID=${workExchangeVO.weID}"
											class="img-fluid"></td>
									</tr>
									<tr>
										<th>需求影片:</th>
										<td><video width="500" height="350" controls>
												<source
													src="<%=request.getContextPath()%>/workExchange/workExchangeVideo.do?weID=${workExchangeVO.weID}"
													type="video/mp4">
											</video></td>
									</tr>
									<tr>
										<th>開始時間:</th>
										<td>${workExchangeVO.weStart}</td>
									</tr>
									<tr>
										<th>結束時間:</th>
										<td>${workExchangeVO.weEnd}</td>
									</tr>
									<tr>
										<td><a class="btn btn-info"
											href="<%=request.getContextPath()%>/back-end/workExchange/listAllWorkExchange.jsp">回首頁</a></td>
										<td><input type="hidden" name="weID"
											value="${workExchangeVO.weID}"> <input type="submit"
											class="btn-danger form-control" value="修改"> <input
											type="hidden" name="action" value="getOne_For_Update">
										</td>
									</tr>
									<tr>
										<td><input type="button" class="btn btn-warning"
											value="發送打工邀請"></td>
									</tr>
								</table>
							</Form>
						</div>
						<div class="col-xs-12 col-sm-3"></div>
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

