<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.billboard.model.*"%>
<%@page import="java.util.*"%>

<%
	BillboardService bbSvc = new BillboardService();
	List<BillboardVO> list = bbSvc.getAll();
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

<title>輪播廣告列表</title>

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
<style>



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
					<li class="breadcrumb-item active">輪播廣告列表</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<h1>輪播廣告</h1>
				<hr>
				<div class="container-fluid" align="right"  style="margin:0px  0px -25px 0px">
					<button type="button" class="btn btn-info">
						<a href="<%=request.getContextPath()%>/back-end/billboard/addBB.jsp"     style="color:#fff">新增輪播廣告</a>
					</button>
				</div>
				
				
				<div class="container-fluid">
					<br>
					<table class="table table-hover">

						<thead>
							<tr>
								<th >廣告編號</th>
								<th >圖片URL</th>
								<th>開始時間</th>
								<th >結束時間</th>
								<th>廣告圖片</th>
								<th>廣告狀態</th>
								<th align="center">修改</th>
								<th align="center">刪除</th>
							</tr>
						</thead>

						<tbody>
							<%@ include file="page1.file"%>
							<c:forEach var="bbVO"  items="${list}" varStatus="status"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									<td>${bbVO.bbID}</td>
									<td>${bbVO.url}</td>
									<td>${bbVO.bbStart}</td>
									<td>${bbVO.bbEnd}</td>

									<c:set var="index" value="${status.index}" />

									<%
										int count = (Integer) pageContext.getAttribute("index");
											String encodedText = null;
											if (list.get(count).getpic() != null) {
												Base64.Encoder encoder = Base64.getEncoder();
												encodedText = encoder.encodeToString(list.get(count).getpic());
												pageContext.setAttribute("icon_", new Integer(1));
											} else {
												pageContext.setAttribute("icon_", new Integer(0));
											}
									%>

									<c:choose>
										<c:when test="${ icon_== 1}">
											<td style=""><img width="200"
												src="data:image/png;base64, <%=encodedText%>"></td>
										</c:when>
										<c:otherwise>
											<td><img
												src="<%=request.getContextPath()%>/image/noImage.jpg"
												width="200" height="132"></td>
										</c:otherwise>
									</c:choose>

									<td>${bbStatusMap.get(bbVO.getbbStatus())}</td>

									<td>
										<form METHOD="post"
											ACTION="<%=request.getContextPath()%>/billboard/bb.do"
											style="margin-bottom: 0px;">
											<button class="btn btn-info" type="submit">修改</button>
											<input type="hidden" name="bbID" value="${bbVO.bbID }">
											<input type="hidden" name="requestURL"
												value="<%=request.getServletPath()%>">
											<!--送出本網頁的路徑給Controller-->
											<input type="hidden" name="action" value="getOne_For_Update">
										</form>
									</td>
									
									
									<td>
										<form METHOD="post"
											ACTION="<%=request.getContextPath()%>/billboard/bb.do"
											style="margin-bottom: 0px;">
											<button class="btn btn-info" type="submit">刪除</button>
											<input type="hidden" name="bbID" value="${bbVO.bbID }">
											<input type="hidden" name="requestURL"
												value="<%=request.getServletPath()%>">
											<!--送出本網頁的路徑給Controller-->
											<input type="hidden" name="action" value="delete">
										</form>
									</td>
									


								</tr>
							</c:forEach>
						</tbody>
					</table>
					<%@ include file="page2.file"%>
					<br> <a href="">返回頁面</a>
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
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>


	<!-- Bootstrap core JavaScript-->
	<script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="<%=request.getContextPath()%>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>

</body>
<style>
table {
	table-layout: auto;
	width: 100%;
	margin-top: 5px;
	margin-bottom: 5px;
}

.table>thead>tr>th {
	text-align: center;
	vertical-align: middle;
}

.table>tbody>tr>td {
	word-break: break-all;
	text-align: center;
	vertical-align: middle;
	border-top: 0px;
}
</style>
</html>