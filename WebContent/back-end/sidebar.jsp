<%@ page import="java.util.List"%>
<%@ page import="com.authorityRecord.model.AuthorityRecordVO"%>
<%@ page import="com.authorityRecord.model.AuthorityRecordService"%>
<%@ page import="com.android.employee.model.EmployeeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<!-- <title>SB Admin - Blank Page</title> -->

<!-- Bootstrap core CSS-->
<link href="<%=request.getContextPath()%>/back-end/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template-->
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

<!-- Page level plugin CSS-->
<link href="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="<%=request.getContextPath()%>/back-end/css/sb-admin.css"
	rel="stylesheet">

<!-- datepicker-->
<link href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />

</head>

<body id="page-top">

	<!-- Sidebar -->
	<ul class="sidebar navbar-nav">

		<!-- LOGO pic Gina -->
		<div class="profile_pic">
			<img src="<%=request.getContextPath()%>/back-end/images/mcPiglogo.jpg" class="img-circle profile_img" width='800px'>
		</div>

		<li class="nav-item active">
			<a class="nav-link" href="<%=request.getContextPath()%>/back-end/room/roomState.jsp">
				<i class="fa fa-home"></i>
				<span>當日房況一覽</span>
			</a>
		</li>
		
		<!-- Check In/Out 1005 -->
		<li class="nav-item dropdown" style="display: ${authRecordList.contains('1005') ? '' : 'none'}">
			<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">
				<i class="fa fa-edit"></i>
				<span>Check In/Out</span>
			</a>
			<div class="dropdown-menu" aria-labelledby="pagesDropdown">
				<h6 class="dropdown-header">入住:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/orders/checkIn.jsp">CheckIn</a>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/member/addMember.jsp">新增會員</a>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/orders/addorders.jsp">新增訂單</a>
				<div class="dropdown-divider"></div>
				<h6 class="dropdown-header">退房:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/orders/checkOut.jsp">CheckOut</a>
			</div>
		</li>
		
		<!-- 訂單管理 1012 -->
		<li class="nav-item dropdown" style="display: ${authRecordList.contains('1012') ? '' : 'none'}">
			<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown2" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">
				<i class="fa fa-edit"></i>
				<span>訂單管理</span>
			</a>
			<div class="dropdown-menu" aria-labelledby="pagesDropdown2">
				<h6 class="dropdown-header">新增/查詢:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/orders/listAllOrders.jsp">查詢訂單</a>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/orders/addorders.jsp">新增訂單</a>
				<div class="dropdown-divider"></div>
				<h6 class="dropdown-header">列表:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/orders/normalOrders.jsp">一般訂單列表</a>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/orders/workExchangeOrders.jsp">打工換宿訂單列表</a>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/orders/returnOrders.jsp">退訂訂單列表</a>
			</div>
		</li>
		
		<!-- 打工需求管理 1006 -->
		<li class="nav-item dropdown" style="display: ${authRecordList.contains('1006') ? '' : 'none'}">
			<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">
				<i class="fa fa-edit"></i>
				<span>打工需求管理</span>
			</a>
			<div class="dropdown-menu" aria-labelledby="pagesDropdown3">
				<h6 class="dropdown-header">新增/查詢:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/workExchange/addOneWorkExchange.jsp">新增打工需求</a>
				<div class="dropdown-divider"></div>
				<h6 class="dropdown-header">列表:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/workExchange/listAllWorkExchange.jsp">打工需求列表</a>
			</div>
		</li>
		
		<!-- 房型管理 1004 -->
		<li class="nav-item dropdown" style="display: ${authRecordList.contains('1004') ? '' : 'none'}">
			<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">
				<i class="fa fa-edit"></i>
				<span>房型管理</span>
			</a>
			<div class="dropdown-menu" aria-labelledby="pagesDropdown3">
				<h6 class="dropdown-header">新增:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/roomType/addroomType.jsp">新增房型</a>
<!-- 				<a class="dropdown-item" -->
<%-- 					href="<%=request.getContextPath()%>/back-end/roomType/select_roomType_page.jsp">查詢房型</a> --%>
				<div class="dropdown-divider"></div>
				<h6 class="dropdown-header">列表:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/roomType/listAllRoomType.jsp">房型列表</a>
			</div>
		</li>
		
		<!-- 房間管理 1011 -->
		<li class="nav-item dropdown" style="display: ${authRecordList.contains('1011') ? '' : 'none'}">
			<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">
				<i class="fa fa-edit"></i>
				<span>房間管理</span>
			</a>
			<div class="dropdown-menu" aria-labelledby="pagesDropdown3">
				<h6 class="dropdown-header">新增/查詢:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/room/addRoom.jsp">新增房間</a>
<%-- 				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/room/HomePage.jsp">查詢房間</a> --%>
				<div class="dropdown-divider"></div>
				<h6 class="dropdown-header">列表</h6>
<!-- 				<a class="dropdown-item" href="blank.html">選擇日期</a> -->
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/room/listAllRoom.jsp">所有房間</a>
			</div>
		</li>
		
		<!-- 會員管理 1010 -->
		<li class="nav-item dropdown" style="display: ${authRecordList.contains('1010') ? '' : 'none'}">
			<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">
				<i class="fa fa-edit"></i>
				<span>會員管理</span>
			</a>
			<div class="dropdown-menu" aria-labelledby="pagesDropdown3">
				<h6 class="dropdown-header">新增/查詢:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/member/addMember.jsp">新增會員</a>
				<div class="dropdown-divider"></div>
				<h6 class="dropdown-header">列表:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/member/listAllMem.jsp">會員列表</a>
			</div>
		</li>
		
		<!-- 員工管理 1009 -->
		<li class="nav-item dropdown" style="display: ${authRecordList.contains('1009') ? '' : 'none'}">
			<a class="nav-link dropdown-toggle"
				href="#" id="pagesDropdown3" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">
				<i class="fa fa-edit"></i>
				<span>員工管理</span>
			</a>
			<div class="dropdown-menu" aria-labelledby="pagesDropdown3">
				<h6 class="dropdown-header">新增:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/employee/addEmployee.jsp">新增員工</a>
				<div class="dropdown-divider"></div>
				<h6 class="dropdown-header">列表:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/employee/listAllEmp_1.jsp">員工列表</a>
			</div>
		</li>
		
		<!-- 前台網頁管理 1003 -->
		<li class="nav-item dropdown" style="display: ${authRecordList.contains('1003') ? '' : 'none'}">
			<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">
				<i class="fa fa-edit"></i>
				<span>輪播廣告管理</span>
			</a>
			<div class="dropdown-menu" aria-labelledby="pagesDropdown3">
				<h6 class="dropdown-header">新增</h6>
				<a class="dropdown-item" 
					href="<%=request.getContextPath()%>/back-end/billboard/addBB.jsp">新增輪播廣告</a> 
				<div class="dropdown-divider"></div>
				<h6 class="dropdown-header">列表:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/billboard/listAllBillBoard.jsp">輪播廣告列表</a> 
			</div>
		</li>
		
		<!-- 促銷活動管理 1007 -->
		<li class="nav-item dropdown" style="display: ${authRecordList.contains('1007') ? '' : 'none'}">
			<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">
				<i class="fa fa-edit"></i>
				<span>促銷活動管理</span>
			</a>
			<div class="dropdown-menu" aria-labelledby="pagesDropdown3">
				<h6 class="dropdown-header">新增:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/activity/addAct.jsp">新增促銷活動</a>
				<div class="dropdown-divider"></div>
				<h6 class="dropdown-header">列表:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/activity/listAllActivity.jsp">促銷活動列表</a>
			</div>
		</li>
		
		<!-- 優惠劵管理 1008 -->
		<li class="nav-item dropdown" style="display: ${authRecordList.contains('1008') ? '' : 'none'}">
			<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">
				<i class="fa fa-edit"></i>
				<span>優惠劵管理</span>
			</a>
			<div class="dropdown-menu" aria-labelledby="pagesDropdown3">
				<h6 class="dropdown-header">新增:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/coupon/addCoupon.jsp">新增優惠劵</a>
				<div class="dropdown-divider"></div>
				<h6 class="dropdown-header">列表:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/coupon/listAllCoupon.jsp">優惠劵列表</a>
			</div>
		</li>
		
		<!-- 檢舉管理 1013 -->
		<li class="nav-item dropdown" style="display: ${authRecordList.contains('1013') ? '' : 'none'}">
			<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">
				<i class="fa fa-edit"></i>
				<span>檢舉管理</span>
			</a>
			<div class="dropdown-menu" aria-labelledby="pagesDropdown3">
				<h6 class="dropdown-header">文章檢舉:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/report/listAllReport.jsp">被檢舉文章列表</a>
				<div class="dropdown-divider"></div>
				<h6 class="dropdown-header">留言檢舉:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/messageReport/listAllMessageReport.jsp">被檢舉留言列表</a>
			</div>
		</li>
		
		<!-- 權限管理 1002 -->
		<li class="nav-item dropdown" style="display: ${authRecordList.contains('1002') ? '' : 'none'}">
			<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">
				<i class="fa fa-edit"></i>
				<span>權限管理</span>
			</a>
			<div class="dropdown-menu" aria-labelledby="pagesDropdown3">
				<h6 class="dropdown-header">新增/查詢:</h6>
<%-- 				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/authority/listEmp_ByAuthID.jsp">新增</a> --%>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/authorityRecord/select_page.jsp">權限查詢與新增</a>
<!-- 				<div class="dropdown-divider"></div> -->
<!-- 				<h6 class="dropdown-header">列表:</h6> -->
<%-- 				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/branch/listAllBranch.jsp">分店列表</a> --%>
			</div>
		</li>
		
		<!-- 分店管理 1001 -->
		<li class="nav-item dropdown" style="display: ${authRecordList.contains('1001') ? '' : 'none'}">
			<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">
				<i class="fa fa-edit"></i>
				<span>分店管理</span>
			</a>
			<div class="dropdown-menu" aria-labelledby="pagesDropdown3">
				<h6 class="dropdown-header">新增:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/branch/addBra.jsp">新增分店</a>
				<div class="dropdown-divider"></div>
				<h6 class="dropdown-header">列表:</h6>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/branch/listAllBranch.jsp">分店列表</a>
			</div>
		</li>
		
		<!-- 客服Q&A 1014 -->
		<li class="nav-item dropdown" style="display: ${authRecordList.contains('1014') ? '' : 'none'}">
			<a class="nav-link" href="<%=request.getContextPath()%>/back-end/question/backendChat.jsp">
			<i class="fa fa-edit"></i> <span>客服Q&A</span>
			</a>
		</li>
	</ul>


	<!-- Bootstrap core JavaScript-->
	<script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="<%=request.getContextPath()%>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>

	<!-- datetimepicker JavaScript-->
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

</body>

</html>
