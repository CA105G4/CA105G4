<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.workExchange.model.*"%>
<%@ page import="java.util.*"%>

<% 
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
				<div class="container-fluid">
					<div class="row">
						<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />
						<jsp:useBean id="employeeSvc"
							class="com.employee.model.EmployeeService" />
						<jsp:useBean id="roomTypeSvc"
							class="com.roomType.model.RoomTypeService" />
						<div class="col-xs-12 col-sm-1"></div>
						<div class="col-xs-12 col-sm-10">
							<Form method="post"
								action="<%=request.getContextPath()%>/workExchange/workExchange.do"
								enctype="multipart/form-data" style="margin-bottom: 0px;">
								<!-- 				<div class="form-group"> -->
								<!-- 					<label for="weID">需求編號</label> -->
								<!-- 					<input type="hidden" name="weID" id="weID" placeholder="系統自動幫您生成" class="form-control" readonly="true"> -->
								<!-- 				</div> -->
								<div class="form-group">
									<label for="empID">創建員工</label> <select name="empID"
										class="form-control">
										<c:forEach var="empVO" items="${employeeSvc.all}">
											<option value="${empVO.empID}">${employeeSvc.getOneEmp(empVO.empID).empName}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="memID">會員姓名</label> <input class="form-control"
										type="text" name="memID" readonly="true"
										value="${workExchangeVO.memID}">${memberSvc.getOneMem(workExchangeVO.memID).memName}
								</div>
								<div class="form-group">
									<label for="rtID">房型名稱</label> <select name="rtID"
										class="form-control">
										<c:forEach var="roomTypeVO" items="${roomTypeSvc.all}">
											<option value="${roomTypeVO.rtID}">${roomTypeSvc.getOneRoomType(roomTypeVO.rtID).rtName}
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="weName">需求名稱</label> <input type="text"
										class="form-control" name="weName" id="weName">
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
										class="form-control" name="wePic" id="wePic">
									<div style="color: red">${errMsgs.wePic}</div>
								</div>
								<div class="form-group">
									<label for="weVideo">選擇影片</label> <input type="file"
										class="form-control" name="weVideo" id="weVideo">
									<div style="color: red">${errMsgs.weVideo}</div>
								</div>
								<div class="form-group">
									<label for="weStart">開始時間</label> <input type="text"
										name="weStart" id="start">
								</div>
								<div class="form-group">
									<label for="weEnd">結束時間</label> <input type="text" name="weEnd"
										id="end">
								</div>
								<input type="submit" class="btn-success form-control"
									value="確認新增"> <input type="hidden" name="action"
									value="confirm_Add">
							</Form>
						</div>
						<div class="col-xs-12 col-sm-1"></div>
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
	        $('#start').datetimepicker({
	           theme: '',              //theme: 'dark',
	  	       timepicker:false,       //timepicker:true,
	  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
	  		   value: '<%=weStart%>' 
	  		   // value:   new Date(),
	           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	           //startDate:	            '2017/07/10',  // 起始日
	           //minDate:               '-1970-01-01', // 去除今日(不含)之前
	           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	        });
	        
	        $.datetimepicker.setLocale('zh');
	        $('#end').datetimepicker({
	           theme: '',              //theme: 'dark',
	  	       timepicker:false,       //timepicker:true,
	  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 	  		   value: '<%=weEnd%>', 	// value:   new Date(),
	           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	           //startDate:	            '2017/07/10',  // 起始日
	           //minDate:               '-1970-01-01', // 去除今日(不含)之前
	           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	        });
	        
	        
	   
	        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------
	
	        //      1.以下為某一天之前的日期無法選擇
	        //      var somedate1 = new Date('2017-06-15');
	        //      $('#f_date1').datetimepicker({
	        //          beforeShowDay: function(date) {
	        //        	  if (  date.getYear() <  somedate1.getYear() || 
	        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	        //              ) {
	        //                   return [false, ""]
	        //              }
	        //              return [true, ""];
	        //      }});
	
	        
	        //      2.以下為某一天之後的日期無法選擇
	        //      var somedate2 = new Date('2017-06-15');
	        //      $('#f_date1').datetimepicker({
	        //          beforeShowDay: function(date) {
	        //        	  if (  date.getYear() >  somedate2.getYear() || 
	        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	        //              ) {
	        //                   return [false, ""]
	        //              }
	        //              return [true, ""];
	        //      }});
	
	
	        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	        //      var somedate1 = new Date('2017-06-15');
	        //      var somedate2 = new Date('2017-06-25');
	        //      $('#f_date1').datetimepicker({
	        //          beforeShowDay: function(date) {
	        //        	  if (  date.getYear() <  somedate1.getYear() || 
	        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	        //		             ||
	        //		            date.getYear() >  somedate2.getYear() || 
	        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	        //              ) {
	        //                   return [false, ""]
	        //              }
	        //              return [true, ""];
	        //      }});
	        
  </script>

</html>

