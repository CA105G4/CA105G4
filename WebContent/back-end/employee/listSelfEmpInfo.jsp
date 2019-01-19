
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.employee.model.*"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>單筆員工資料</title>

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
					<li class="breadcrumb-item"><a
						href="<%=request.getContextPath()%>/back-end/room/roomState.jsp">首頁</a>
					</li>
					<li class="breadcrumb-item active">單筆員工資料</li>
				</ol>

					<div class="container-fluid">
						<div class="row">
							<div class="col-xs-12 col-sm-3">
							
							</div>
							<div class="col-xs-12 col-sm-6">
							<div class="card mb-3">
								<div class="card-header">
									<i class="fas fa-table"></i> 員工個人資料
								</div>
								<br>
								<div align="center">
									<img
										src="<%=request.getContextPath()%>/employee/empImg.do?empID=${employeeVO.empID}"
										style="border-radius: 50%; width: 300px;">
								</div>
								<br>
								<div align="center">
								<table class="table table-bordered table-striped table-hover"
									style="width: 80%;">
									<tr>
										<th>員工編號</th>
										<td>${employeeVO.getEmpID()}</td>
									</tr>
									<tr>
										<th>所屬分店</th>
										<jsp:useBean id="brSvc" scope="page"
											class="com.branch.model.BranchService" />
										<td>${brSvc.getOneByID(employeeVO.getBraID()).braName}</td>
									</tr>
									<tr>
										<th>員工姓名</th>
										<td>${employeeVO.getEmpName()}</td>
									</tr>
									<tr>
										<th>員工職稱</th>
										<td>${employeeVO.getEmpJob()}</td>
									</tr>
									<tr>
										<th>連絡電話</th>
										<td>${employeeVO.empTel}</td>
									</tr>
									<tr>
										<th>員工帳號狀態</th>
										<td>${empStateMap.get(employeeVO.getEmpState())}</td>
									</tr>


								</table>
								</div>
							</div>
							
						</div>
							<div class="col-xs-12 col-sm-3">
								
							</div>
						</div>
					</div>
					<div align="center">
						<button type="button" class="btn btn-info">
							<a href='<%=request.getContextPath()%>/back-end/room/roomState.jsp' style="color:#fff" >返回</a>
						</button>
					</div>

					
				
				

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

	<!-- Page level plugin JavaScript-->
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/datatables/jquery.dataTables.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>

	<!-- Demo scripts for this page-->
	<script
		src="<%=request.getContextPath()%>/back-end/js/demo/datatables-demo.js"></script>
</body>

</html>
