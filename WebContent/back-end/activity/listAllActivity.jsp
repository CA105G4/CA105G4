<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%@page import="java.util.*"%>

<%
	ActivityService actSvc = new ActivityService();
	List<ActivityVO> list = actSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>促銷活動列表</title>

<!-- Bootstrap core CSS-->
<link href="<%=request.getContextPath()%>/back-end/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template-->
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">

<!-- Page level plugin CSS-->
<link href="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

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
					<li class="breadcrumb-item">
						<a href="<%=request.getContextPath()%>/back-end/room/roomState.jsp">首頁</a>
					</li>
					<li class="breadcrumb-item active">促銷活動列表</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<h1>促銷活動列表</h1>
				<hr>
				<div class="container-fluid" align="right"  style="margin:0px  0px -25px -63px">
					<button type="button" class="btn btn-info">
						<a href="<%=request.getContextPath()%>/back-end/activity/addAct.jsp" style="color:#fff">新增促銷活動</a>
					</button>
				</div>
			
				<div class="container">
					
				
					<br>
					<table class="table table-hover">

						<thead>
							<tr>
								<th>促銷活動編號</th>
								<th>促銷活動名稱</th>
								<th>活動開始時間</th>
								<th>活動結束時間</th>
								<th align="center">修改</th>
								<th align="center">查詢明細</th>
							</tr>
						</thead>

						<tbody>
							<%@ include file="page1.file"%>
							<c:forEach var="actVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									<td>${actVO.actID}</td>
									<td>${actVO.actName}</td>
									<td>${actVO.actStart}</td>
									<td>${actVO.actEnd}</td>
									<td>
										<form METHOD="post"
											ACTION="<%=request.getContextPath()%>/activity/act.do"
											style="margin-bottom: 0px;">
											<button class="btn btn-info" type="submit">修改</button>
											<input type="hidden" name="actID" value="${actVO.actID}">
											<input type="hidden" name="action" value="getOne_For_Update">
										</form>
									</td>
							
									
									<td>
										<form METHOD="post"
											ACTION="<%=request.getContextPath()%>/activity/act.do"
											style="margin-bottom: 0px;">
											<button class="btn btn-info" type="submit">查詢明細</button>
											<input type="hidden" name="actID" value="${actVO.actID}">
											<input type="hidden" name="action" value="get_detail_by_actID">
										</form>
									</td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<%@ include file="page2.file"%>
					
					<br>
					<br>
					<br>
					
					<%if (request.getAttribute("listDetail_ByactID")!=null){%>
       					<jsp:include page="listDetail_ByactID.jsp" />
					<%} %>
					
					<br>
					<a href="select_page.jsp">回選取頁面</a>
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

	</div>
	<!-- /.content-wrapper -->

	<!-- /#wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top">
		<i class="fas fa-angle-up"></i>
	</a>


	<!-- Bootstrap core JavaScript-->
	<script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="<%=request.getContextPath()%>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>

</body>

</html>