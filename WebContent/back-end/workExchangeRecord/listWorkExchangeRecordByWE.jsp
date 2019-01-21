<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.workExchange.model.*"%>
<%@ page import="com.workExchangeRecord.model.*"%>
<%@ page import="java.util.*"%>

<% 
	WorkExchangeVO workExchangeVO = (WorkExchangeVO)request.getAttribute("workExchangeVO");
	pageContext.setAttribute("workExchangeVO",workExchangeVO);

	Integer weID = workExchangeVO.getWeID();
	WorkExchangeRecordService werSvc = new WorkExchangeRecordService();
	List<WorkExchangeRecordVO> list = werSvc.getAllByWE(weID);
	pageContext.setAttribute("list",list);
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

<title>打工申請紀錄列表</title>

<!-- Bootstrap core CSS-->
<link
	href="<%=request.getContextPath() %>/back-end/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom fonts for this template-->
<link
	href="<%=request.getContextPath() %>/back-end/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Page level plugin CSS-->
<link
	href="<%=request.getContextPath() %>/back-end/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="<%=request.getContextPath() %>/back-end/css/sb-admin.css"
	rel="stylesheet">
<style>
	table, th, td {
  	text-align:center;
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
					<li class="breadcrumb-item active">打工申請紀錄列表</li>
				</ol>

			  <!-- Page Content 這邊開始自由發揮-->
	          <h1>打工申請紀錄列表</h1>
	          <hr>
          		<div class="container-fluid">
				<div class="row">
				<table class="table table-bordered table-striped table-hover">
					<tr>
						<th>需求編號</th>
						<th>需求名稱</th>
						<th>會員姓名</th>
						<th>會員技能</th>
<!-- 						<th>訂單編號</th> -->
						<th>申請圖片</th>
						<th>審核狀況</th>
						<th class="text-center">審核</th>
						<th class="text-center">發信</th>
					</tr>
					<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
					<jsp:useBean id="rtSvc" class="com.roomType.model.RoomTypeService"/>
					<c:forEach var="workExchangeRecordVO" items="${list}">
						<tr>
							<td>${workExchangeRecordVO.weID}</td>
							<td>${workExchangeVO.weName}</td>
							<td>${memberSvc.getOneMem(workExchangeRecordVO.memID).memName}</td>
							<td>${memberSvc.getOneMem(workExchangeRecordVO.memID).memSkill}</td>	
<%-- 							<td>${workExchangeRecordVO.orderID}</td> --%>
							<td>
							<img src="<%=request.getContextPath()%>/workExchangeRecord/workExchangeRecordImg.do?weID=${workExchangeRecordVO.weID}&memID=${workExchangeRecordVO.memID}" class="img-fluid">
							</td>
							<td>
								${werStateMap.get(workExchangeRecordVO.getWerState())}
							</td>
							<td class="text-center">
							<form method="post" action="<%=request.getContextPath()%>/workExchangeRecord/workExchangeRecord.do" style="margin-bottom: 0px;">
								<input type="hidden" name="weID" value="${workExchangeRecordVO.weID}">
								<input type="hidden" name="memID" value="${workExchangeRecordVO.memID}">
								<input type="hidden" name="werState" value="1">
								<input type="submit" ${(workExchangeRecordVO.werState == 1) ? "class='btn btn-success'" : "class='btn btn-danger'"} 
								${(workExchangeRecordVO.werState == 1) ? "value='成立訂單'" : "value='通過'"}>
								<input type="hidden" name="action" value="OK">
							</form>
							</td>
							<td>
							<form method="post" action="<%=request.getContextPath()%>/workExchangeRecord/workExchangeRecord.do" style="margin-bottom: 0px;">
								<input type="hidden" name="weID" value="${workExchangeRecordVO.weID}">
								<input type="hidden" name="memID" value="${workExchangeRecordVO.memID}">
								<input type="submit" class="btn btn-warning" value="送信">
								<input type="hidden" name="action" value="Send_Email">
							</form>	
							</td>
						</tr>
					  </c:forEach>
				    </table>
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
		src="<%=request.getContextPath() %>/back-end/vendor/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath() %>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="<%=request.getContextPath() %>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="<%=request.getContextPath() %>/back-end/js/sb-admin.min.js"></script>

</body>

</html>

