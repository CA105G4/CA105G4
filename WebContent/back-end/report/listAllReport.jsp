<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<%
	ReportService reportService = new ReportService();
	List<ReportVO> list = reportService.getAll();
	pageContext.setAttribute("list", list);
	//report status hashmap
	Map<Long, String> status = new HashMap<Long, String>();
	status.put(0L, "待審核");
	status.put(1L, "通過");
	status.put(2L, "駁回");

	pageContext.setAttribute("status", status);
%>

<style>
iframe {
	display: none;
	position: absolute;
	width: 600px;
	height: 600px;
	z-index: 1;
	border-collapse: collapse;
	border: 1px #FFFFFF solid;
	background-color: #CCEEFF;
}
</style>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>文章檢舉</title>

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
					<li class="breadcrumb-item active">文章檢舉</li>
				</ol>

				<jsp:useBean id="memberService" scope="page"
					class="com.member.model.MemberService" />

				<h4>
					<a
						href="<%=request.getContextPath()%>/back-end/article/select_page.jsp">回首頁</a>
				</h4>
				<!-- DataTables Example -->
				<div class="card mb-3">
					<div class="card-header">
						<i class="fas fa-table"></i> Data Table Example
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
							<table class="table table-bordered" id="dataTable" width="100%"
								cellspacing="0">
								<thead>
									<tr>
										<th>檢舉流水號</th>
										<th>文章編號</th>
										<th>檢舉原因</th>
										<th>檢舉會員</th>
										<th>檢舉時間</th>
										<th>審核狀態</th>
										<th>通過</th>
										<th>駁回</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th>檢舉流水號</th>
										<th>文章編號</th>
										<th>檢舉原因</th>
										<th>檢舉會員</th>
										<th>檢舉時間</th>
										<th>審核狀態</th>
										<th>通過</th>
										<th>駁回</th>
									</tr>
								</tfoot>
								<tbody>
									<c:forEach var="reportVO" items="${list}">
										<tr>
											<td>${reportVO.repid}</td>
											<td>${reportVO.artid}</td>
											<td>${reportVO.repreason}</td>
											<td>${memberService.getOneMem(reportVO.repmemid).memAcc}</td>

											<td>${reportVO.repdate}</td>
											<td><c:if test="${reportVO.repstate =='0'}"> ${ status[0]} </c:if>
												<c:if test="${reportVO.repstate =='1'}"> ${ status[1]} </c:if>
												<c:if test="${reportVO.repstate =='2'}"> ${ status[2]} </c:if>
											</td>
											<c:if test="${reportVO.repstate =='0'}">
												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/report/report.do"
														style="margin-bottom: 0px;">
														<input type="submit" class="btn btn-info" value="通過">
														<input type="hidden" name="artid"
															value="${reportVO.artid}"> <input type="hidden"
															name="repreason" value="${reportVO.repreason}"> <input
															type="hidden" name="repdate" value="${reportVO.repdate}">
														<input type="hidden" name="repmemid"
															value="${reportVO.repmemid}"> <input
															type="hidden" name="repstate" value="1"> <input
															type="hidden" name="repid" value="${reportVO.repid}">
														<input type="hidden" name="action" value="status_Update">
													</FORM>
												</td>
												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/report/report.do"
														style="margin-bottom: 0px;">
														<input type="submit" class="btn btn-warning" value="駁回">
														<input type="hidden" name="artid"
															value="${reportVO.artid}"> <input type="hidden"
															name="repreason" value="${reportVO.repreason}"> <input
															type="hidden" name="repdate" value="${reportVO.repdate}">
														<input type="hidden" name="repmemid"
															value="${reportVO.repmemid}"> <input
															type="hidden" name="repstate" value="2"> <input
															type="hidden" name="repid" value="${reportVO.repid}">
														<input type="hidden" name="repid"
															value="${reportVO.repid}"> <input type="hidden"
															name="action" value="update">
													</FORM>
												</td>
											</c:if>

											<c:if test="${reportVO.repstate !='0'}">
												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/report/report.do"
														style="margin-bottom: 0px;">
														<input type="submit" class="btn btn-danger " disabled
															value="通過"> <input type="hidden" name="artid"
															value="${reportVO.artid}"> <input type="hidden"
															name="repreason" value="${reportVO.repreason}"> <input
															type="hidden" name="repdate" value="${reportVO.repdate}">
														<input type="hidden" name="repmemid"
															value="${reportVO.repmemid}"> <input
															type="hidden" name="repstate" value="1"> <input
															type="hidden" name="repid" value="${reportVO.repid}">
														<input type="hidden" name="action" value="status_Update">
													</FORM>
												</td>
												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/report/report.do"
														style="margin-bottom: 0px;">
														<input type="submit" class="btn btn-danger" disabled
															value="駁回"> <input type="hidden" name="artid"
															value="${reportVO.artid}"> <input type="hidden"
															name="repreason" value="${reportVO.repreason}"> <input
															type="hidden" name="repdate" value="${reportVO.repdate}">
														<input type="hidden" name="repmemid"
															value="${reportVO.repmemid}"> <input
															type="hidden" name="repstate" value="2"> <input
															type="hidden" name="repid" value="${reportVO.repid}">
														<input type="hidden" name="repid"
															value="${reportVO.repid}"> <input type="hidden"
															name="action" value="update">
													</FORM>
												</td>
											</c:if>


										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
				</div>

			</div>
			<!-- /.container-fluid -->

			<!-- Sticky Footer -->
<!-- 			<footer class="sticky-footer"> -->
<!-- 				<div class="container my-auto"> -->
<!-- 					<div class="copyright text-center my-auto"> -->
<!-- 						<span>© M.C.P.I.G 2019</span> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</footer> -->

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
		src="<%=request.getContextPath()%>/back-end/vendor/chart.js/Chart.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/datatables/jquery.dataTables.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>

	<!-- Demo scripts for this page-->
	<script
		src="<%=request.getContextPath()%>/back-end/js/demo/datatables-demo.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/js/demo/chart-area-demo.js"></script>

</body>

</html>
