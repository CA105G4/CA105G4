<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%@page import="java.util.*"%>


<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>促銷活動查詢</title>

<!-- Bootstrap core CSS-->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">

<!-- Page level plugin CSS-->
<link href="vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin.css" rel="stylesheet">
<style>
.form-group {
	margin-right: 8px
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
					<li class="breadcrumb-item active">促銷活動查詢</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
							<%--錯誤列表 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
									

				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-6">


							<a href='listAllActivity.jsp'>促銷活動列表</a> <br>
							<br>

							<form METHOD="post" ACTION="act.do" class="form-inline">

								<label>促銷活動編號:</label>
								<!-- 						<span class="form-inline form-group">
								<input type="text" name="actID"  placeholder="請輸入促銷活動編號"  class="form-group">
								<input type="hidden" name="action" value="getOne_For_Display" class="form-group">
								<button type="submit" class="btn btn-info">送出</button>	</span>    -->

								<div class="input-group mb-3">
									<div class="input-group-append">
										<input type="text" name="actID" class="form-control "
											placeholder="請輸入促銷活動編號"> <input type="hidden"
											name="action" value="getOne_For_Display">
										<button class="btn btn-info" type="submit">送出</button>
									</div>
								</div>

							</form>



							<jsp:useBean id="actSvc" scope="page"
								class="com.activity.model.ActivityService" />


							<form METHOD="post" ACTION="act.do">
								<label>促銷活動名稱:</label><br> <select size="1" name="actID">
									<c:forEach var="actVO" items="${actSvc.all}">
										<option value="${actVO.actID}">${actVO.actName}
									</c:forEach>
								</select> <input type="hidden" name="action" value="getOne_For_Display">
								<button class="btn btn-info" type="submit">送出</button>

							</form>
							<br>
							<a href='addAct.jsp'>新增一筆促銷活動</a>



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

	</div>
	<!-- /.content-wrapper -->

	<!-- /#wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>


	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin.min.js"></script>

</body>

</html>