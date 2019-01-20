<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>
<%@page import="java.util.*"%>

<%
	EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
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

<title>新增員工資料</title>

<!-- Bootstrap core CSS-->
<link href="<%=request.getContextPath()%>/back-end/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template-->
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

<!-- Page level plugin CSS-->
<link href="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="<%=request.getContextPath()%>/back-end/css/sb-admin.css" rel="stylesheet">


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
							<li class="breadcrumb-item active">新增員工資料</li>
						</ol>
				
						<!-- Page Content 這邊開始自由發揮-->
						<h1>員工新增</h1>
						<hr>
						
						<div class="container-fluid">
							<div class="row">
								<div class="col-xs-12 col-sm-3">
									
								</div>
								<div class="col-xs-12 col-sm-6">
									<div align="center">
									<c:if test="${not empty errorMsgs}">
										<font style="color: red">請修正以下錯誤:</font>
										<ul>
											<c:forEach var="message" items="${errorMsgs}">
												<li style="color: red">${message}</li>
											</c:forEach>
										</ul>
									</c:if>
					
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/employee/emp.do" name="form1"
										enctype="multipart/form-data">
										<table>
											<tr>
												<td>員工姓名:</td>
												<td><input type="TEXT" name="empName" size="45" class="form-control"
													value="<%=(employeeVO == null) ? "Tomdag" : employeeVO.getEmpName()%>" /></td>
											</tr>
											<tr>
												<td>分店編號:</td>
												<td>
													<jsp:useBean id="brSvc" scope="page" class="com.branch.model.BranchService" />
													<select name="braID" class="form-control">
														<option value="-1">請選擇</option>
														<c:forEach var="brVO" items="${brSvc.all}">
															<option value="${brVO.getBraID()}" >${brVO.braName}</option>
														</c:forEach>
													</select>
														
												</td>
											</tr>
											<tr>
												<td>職位:</td>
												<td><input type="TEXT" name="empJob" size="45" class="form-control"
													value="<%=(employeeVO == null) ? "警衛" : employeeVO.getEmpJob()%>" /></td>
											</tr>
											<tr>
												<td>電話:</td>
												<td><input type="TEXT" name="empTel" size="45" class="form-control"
													value="<%=(employeeVO == null) ? "0978001235" : employeeVO.getEmpTel()%>" /></td>
											</tr>
											<tr>
												<td>員工帳號:</td>
												<td><input type="TEXT" name="empAcc" size="45" class="form-control"
													value="<%=(employeeVO == null) ? "aaaaa" : employeeVO.getEmpAcc()%>" /></td>
											</tr>
											<tr>
												<td>員工密碼:</td>
												<td><input type="password" name="empPsw" size="45" class="form-control"
													value="<%=(employeeVO == null) ? "123456" : employeeVO.getEmpPsw()%>" /></td>
											</tr>
											<tr>
												<td>員工頭貼:</td>
												<td>
	<!-- 												<div align="center"> -->
														<img
														src="<%=request.getContextPath()%>/back-end/member/images/nopic.jpg"
														id="previewpic" class="img-fluid" width="300px">
	<!-- 												</div> -->
												</td>
											</tr>
											<tr>
												<td></td>
												<td><input type="file" name="empPic" id="inputfile01">
												</td>
											</tr>
					
					
										</table>
										<br>
										<div align="center">
											<input type="hidden" name="action" value="insert" >
											<input type="submit" value="送出" class="btn btn-info">
										</div> 
										
									</FORM>
									</div>
								</div>
								<div class="col-xs-12 col-sm-3">
							
								</div>
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

	<!-- Custom scripts for all pages-->
	<script src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>

	<script>
		$("#inputfile01").change(function() {
			if (this.files && this.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#previewpic').attr('src', e.target.result);
				}

				reader.readAsDataURL(this.files[0]);
			}
		});
	</script>
</body>

</html>
