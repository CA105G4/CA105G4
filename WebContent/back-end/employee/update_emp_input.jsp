<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>

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

<title>修改員工資料</title>

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
					<li class="breadcrumb-item active">修改員工資料</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<h1>員工資料修改</h1>
				<hr>
				<%-- 錯誤表列 --%>
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
							<td>員工編號:<font color=red><b>*</b></font></td>
							<td><%=employeeVO.getEmpID()%></td>
						</tr>
						<tr>
							<td>所屬分店:</td>
							<td><select name="braID" class="form-control"
								value="<%=employeeVO.getEmpName()%>">
								<option value="B00" ${employeeVO.getBraID()=="B00" ? 'selected' : '' }>總管理處</option>
								<option value="B01"${employeeVO.getBraID()=="B01" ? 'selected' : '' }>福翔</option>
								<option value="B02"${employeeVO.getBraID()=="B02" ? 'selected' : '' }>麻翔</option>						
							</select></td>
						</tr>
						<tr>
							<td>員工姓名:</td>
							<td><input type="TEXT" name="empName" size="45"
								value="<%=employeeVO.getEmpName()%>" /></td>
						</tr>
						<tr>
							<td>職位:</td>
							<td><input type="TEXT" name="empJob" size="45"
								value="<%=employeeVO.getEmpJob()%>" /></td>
						</tr>
						<tr>
							<td>電話:</td>
							<td><input type="TEXT" name="empTel" size="45"
								value="<%=employeeVO.getEmpTel()%>" /></td>
						</tr>
						
						<tr>
								<td>帳號狀態 :</td>
									<td><select name="empState" class="form-control">
										<option value="1"${employeeVO.getEmpState()==1 ? 'selected' : '' }>在職</option>
										<option value="0"${employeeVO.getEmpState()==0 ? 'selected' : '' }>離職</option>						
										</select></td>
						</tr>
						<tr>
							<td>員工帳號:</td>
							<td><input type="TEXT" name="empAcc" size="45"
								value="<%=employeeVO.getEmpAcc()%>" /></td>
						</tr>
						<tr>
							<td>員工密碼:</td>
							<td><input type="TEXT" name="empPsw" size="45"
								value="<%=employeeVO.getEmpPsw()%>" /></td>
						</tr>



					</table>
					<br> <input type="hidden" name="action" value="update">
					<input type="hidden" name="empID"
						value="<%=employeeVO.getEmpID()%>"> <input type="submit"
						value="送出修改">
				</FORM>

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
</body>

</html>
