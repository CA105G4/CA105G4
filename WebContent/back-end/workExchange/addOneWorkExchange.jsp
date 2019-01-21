<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.employee.model.EmployeeVO"%>
<%@ page import="com.workExchange.model.*"%>
<%@ page import="java.util.*"%>

<%  
	EmployeeVO empVO = (EmployeeVO)session.getAttribute("employeeVO");
	String braID = empVO.getBraID();
	pageContext.setAttribute("braID",braID);
	pageContext.setAttribute("empVO",empVO);	
	WorkExchangeVO workExchangeVO = (WorkExchangeVO)(request.getAttribute("workExchangeVO"));
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

<title>新增打工需求</title>

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
					<li class="breadcrumb-item active">新增打工需求</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<h1>新增打工需求</h1>
				<hr>
				<div class="container">
					<div class="row">
						<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />
						<jsp:useBean id="employeeSvc"
							class="com.employee.model.EmployeeService" />
						<jsp:useBean id="roomTypeSvc"
							class="com.roomType.model.RoomTypeService" />
						<div class="col-xs-12 col-sm-1"></div>
						<div class="col-xs-12 col-sm-10 col-lg-6 offset-lg-2">
							<Form method="post"
								action="<%=request.getContextPath()%>/workExchange/workExchange.do"
								enctype="multipart/form-data" autocomplete="off" style="margin-bottom: 0px;">
								<!-- 				<div class="form-group"> -->
								<!-- 					<label for="weID">需求編號</label> -->
								<!-- 					<input type="hidden" name="weID" id="weID" placeholder="系統自動幫您生成" class="form-control" readonly="true"> -->
								<!-- 				</div> -->
								<div class="form-group">
									<label for="empID">創建員工</label> 
									<input type="hidden" name="empID" class="form-control" value="${empVO.empID}" size="18">
									<input type="text" name="empID" class="form-control" value="${empVO.empName}" readOnly size="18">
								</div>
								<div class="form-group">
<!-- 									<label for="memID">會員姓名</label>  -->
									<input class="form-control" type="hidden" name="memID" readOnly value="${workExchangeVO.memID}" size="18">
								</div>
								<div class="form-group">
									<label for="rtID">房型名稱</label> <select name="rtID"
										class="form-control">
										<c:forEach var="roomTypeVO" items="${roomTypeSvc.findRoomTypeByBraID(braID)}">
											<option value="${roomTypeVO.rtID}">${roomTypeVO.rtName}
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="weName">需求名稱</label> <input type="text"
										class="form-control" name="weName" id="weName" value="${param.weName}">
									<div style="color: red">${errMsgs.weName}</div>
								</div>
								<div class="form-group">
									<label for="weContent">需求內容</label>
									<textarea class="form-control" name="weContent"
										style="resize: none" id="weContent"></textarea>
									<div style="color: red">${errMsgs.weContent}</div>
								</div>
								<div class="form-group">
									<label for="wePic">選擇照片</label> <input type="file"
										class="form-control" name="wePic" id="wePic" size="18">
									<div style="color: red">${errMsgs.wePic}</div>
								</div>
								<div class="form-group">
									<label for="weVideo">選擇影片</label> <input type="file"
										class="form-control" name="weVideo" id="weVideo" size="18">
									<div style="color: red">${errMsgs.weVideo}</div>
								</div>
								<div class="form-group">
									<label for="weStart">開始時間</label> <input type="text"
										name="weStart" id="start_date" required="required">
								</div>
								<div class="form-group">
									<label for="weEnd">結束時間</label> <input type="text" name="weEnd"
										id="end_date" required="required">
								</div>
								<div class="text-center">
								<input type="submit" class="btn btn-success" value="確認新增"> 
								<input type="hidden" name="action" value="confirm_Add">
								<button id="magic" class="btn btn-warning" type="button">按我</button>
								</div>
							</Form>
						</div>
						<div class="col-xs-12 col-sm-1"></div>
					</div>
					<!-- Page Content 這邊開始自由發揮結束-->
				</div>
				<!-- /.container-fluid -->
				
				
				<script>
				$(document).ready(function(){
		  			$("#magic").click(function(){
		    		$("#weName").val("抓蟲");
		    		$("#weContent").val("蚊蟲肆虐，需要人才來幫忙滅蟲，不論蟑螂、蜈蚣、喇牙、大至專題報告的蟲，有意願的都可以來電諮詢。");
		  			});
				});
				</script>
				
				

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


<% 
	java.sql.Date weStart = null;
	java.sql.Date weEnd = null;
	try {
   		weStart = workExchangeVO.getWeStart();
   		weEnd = workExchangeVO.getWeEnd();
	 } catch (Exception e) {
	 	weStart = new java.sql.Date(System.currentTimeMillis());
	 	weEnd = new java.sql.Date(System.currentTimeMillis());
	 }
%>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
$.datetimepicker.setLocale('zh'); 
$(function(){
	 $('#start_date').datetimepicker({
	  format:'Y-m-d',
	  minDate: new Date(),
	  maxDate: new Date(new Date().getTime() + 30*24*60*60*1000),
	  timepicker:false
	 });
	 
	 $('#end_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
		   this.setOptions({
		    minDate:$('#start_date').val()?$('#start_date').val():false
		   })
	  },
	  maxDate: new Date(new Date().getTime() + 30*24*60*60*1000),
	  timepicker:false
	 });
	 

});
</script>

</html>

