<%@page import="com.employee.model.EmployeeVO"%>
<%@page import="com.roomType.model.*"%>
<%@page import="java.util.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%	
	EmployeeVO employeeVO = (EmployeeVO)session.getAttribute("employeeVO");
	RoomTypeService rtSvc = new RoomTypeService();
	List<RoomTypeVO> list = rtSvc.findRoomTypeByBraID(employeeVO.getBraID());
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>房型列表</title>

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
					<li class="breadcrumb-item">
						<a href="<%=request.getContextPath()%>/back-end/room/roomState.jsp">首頁</a>
					</li>
					<li class="breadcrumb-item active">房型列表</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<h1>房型列表</h1>
				<hr>
				<div class="container-fluid" align="right">
					<button type="button" class="btn btn-info">
						<a href="<%=request.getContextPath()%>/back-end/roomType/addroomType.jsp"     style="color:#fff">新增房型</a>
					</button>
				</div>
				<div class="container-fluid">
				<jsp:useBean id="braSvc2" scope="page" class="com.branch.model.BranchService" />
					<h2>${braSvc2.getOneByID(employeeVO.getBraID()).braName} 所有房型資料</h2>

						<!--開始自由發揮-->
						<table class="table table-bordered table-striped table-hover">
							<thead>
							<tr>
<!-- 								<th>房型編號</th> -->
<!-- 								<th>分店名稱</th> -->
								<th>房型名稱</th>
								<th>房型照片</th>
								<th>房型介紹</th>
								<th>一般住房人數</th>
								<th>住房上限人數</th>
								<th>平日價格</th>
								<th>假日價格</th>
<!-- 								<th>房間剩餘數量</th> -->
								<th>總數量</th>
								<th>修改</th>
							</tr>
							</thead>
							<tbody>
							<%@ include file="page1.file" %> 
							<c:forEach var="roomTypeVO" items="${list}"  varStatus="status" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								
								<tr>
<%-- 									<td>${roomTypeVO.rtID}</td> --%>
								<jsp:useBean id="rtSvc2" scope="page" class="com.roomType.model.RoomTypeService" />
<%-- 									<td>${braSvc.getOneByID(roomTypeVO.braID).getBraName()}</td> --%>
									<td>${roomTypeVO.rtName}</td>
									<td >
										<c:choose>
												<c:when test="${rtSvc2.getOneRoomType(roomTypeVO.rtID).rtPic == null}">
														<img src="<%=request.getContextPath()%>/images/noImage.jpg" id="previewpic" 
									 						class="img-fluid" width="300px">
									 			</c:when>
									 			<c:otherwise>
														<img src="<%=request.getContextPath()%>/roomType/roomTypeImg.do?rtID=${roomTypeVO.rtID}" id="previewpic" 
									 						class="img-fluid" width="300px">
												</c:otherwise>
										</c:choose>
									</td>
									<td>${rtSvc2.getOneRoomType(roomTypeVO.rtID).rtIntro}</td> 
									<td>${rtSvc2.getOneRoomType(roomTypeVO.rtID).rtMinimum}</td>
									<td>${rtSvc2.getOneRoomType(roomTypeVO.rtID).rtLimit}</td>
									<td>${rtSvc2.getOneRoomType(roomTypeVO.rtID).weeklyPrice}</td>
									<td>${rtSvc2.getOneRoomType(roomTypeVO.rtID).holidayPrice}</td>
<%-- 									<td>${roomTypeVO.balance}</td> --%>
									<td>${rtSvc2.getOneRoomType(roomTypeVO.rtID).total}</td>
									<td>
									  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomType/roomType.do" style="margin-bottom: 0px;">
									     <input type="submit" value="修改" class="btn btn-info">
									     <input type="hidden" name="rtID"  value="${roomTypeVO.rtID}">
									     <input type="hidden" name="action"	value="GetOneUpdate"></FORM>
									</td>
									
								</tr>
							</c:forEach>
							</tbody>
						</table>
						<%@ include file="page2.file" %>	
						
						<!-- 結束自由發揮-->
				</div>
				<div class="container-fluid" align="right">
					<button type="button" class="btn btn-info">
						<a href='<%=request.getContextPath()%>/back-end/roomType/select_roomType_page.jsp' style="color:#fff">返回</a>
					</button>
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
		src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script>

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
