<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.workExchange.model.*"%>
<%@ page import="java.util.*"%>

<% 
	WorkExchangeService weSvc = new WorkExchangeService();
	List<WorkExchangeVO> list = weSvc.getAll();
	pageContext.setAttribute("list", list);
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

<title>打工需求列表</title>

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
	border: 2px solid #CCCCFF;
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
					<li class="breadcrumb-item active">打工需求列表</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				          <h1>打工需求列表</h1>
          <hr>
          	<div class="container-fluid">
				<div class="row">
				<table class="table table-bordered table-striped table-hover">
					<tr>
						<th class="text-center">需求編號</th>
						<th class="text-center">創建員工</th>
						<th class="text-center">會員姓名</th>
						<th class="text-center">房型</th>
						<th class="text-center">需求名稱</th>
	<!-- 					<th>需求內容</th> -->
	<!-- 					<th>需求照片</th> -->
	<!-- 					<th>需求影片</th> -->
						<th class="text-center">開始時間</th>
						<th class="text-center">結束時間</th>
						<th class="text-center">需求詳情</th>
						<th class="text-center">審核</th>
					</tr>
<%-- 				<%@ include file="page1.file" %> --%>
					<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
					<jsp:useBean id="employeeSvc" class="com.employee.model.EmployeeService"/>
					<jsp:useBean id="roomTypeSvc" class="com.roomType.model.RoomTypeService" />
					
					<c:forEach var="workExchangeVO" items="${list}">
						<tr>
							<td class="text-center">${workExchangeVO.weID}</td>
							<td class="text-center">${employeeSvc.getOneEmp(workExchangeVO.empID).empName}</td>
							<td class="text-center">${memberSvc.getOneMem(workExchangeVO.memID).memName}</td>
							<td class="text-center">${roomTypeSvc.getOneRoomType(workExchangeVO.rtID).rtName}</td>
							<td class="text-center">${workExchangeVO.weName}</td>
		<%-- 					<td>${workExchangeVO.weContent}</td> --%>
							<td class="text-center">${workExchangeVO.weStart}</td>
							<td class="text-center">${workExchangeVO.weEnd}</td>
							<td class="text-center">
							<form method="post" action="<%=request.getContextPath()%>/workExchange/workExchange.do" style="margin-bottom: 0px;">
								<input type="hidden" name="weID" value="${workExchangeVO.weID}">
								<input type="submit" class="btn btn-info" value="查看">
								<input type="hidden" name="action" value="getOne_WEDET">
							</form>
							</td>
							<td class="text-center">
							<form method="post" action="<%=request.getContextPath()%>/workExchange/workExchange.do" style="margin-bottom: 0px;">
								<input type="hidden" name="weID" value="${workExchangeVO.weID}">
								<input type="submit" ${(workExchangeVO.memID == null) ? "class='btn btn-warning'" : "class='btn btn-success'"} 
								${(workExchangeVO.memID == null) ? "value='審核'"  : "value='完成'"}>  <!--disabled='disabled'-->
								<input type="hidden" name="action" value="choose_member">
							</form>
							</td>
<!-- 							<td class="text-center"> -->
<%-- 							<form method="post" action="<%=request.getContextPath()%>/workExchange/workExchange.do" style="margin-bottom: 0px;"> --%>
<%-- 								<input type="hidden" name="weID" value="${workExchangeVO.weID}"> --%>
<!-- 								<input type="submit" class="btn-warning" value="刪除"> -->
<!-- 								<input type="hidden" name="action" value="deleteOneWE"> -->
<!-- 							</form> -->
<!-- 							</td> -->
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

