<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%@page import="java.util.*"%>

<%
	ActivityVO actVO = (ActivityVO) request.getAttribute("actVO");
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

<title>Add Activity</title>

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

<body id="page-top" onload="connect();" onunload="disconnect();">

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
					<li class="breadcrumb-item active">新增一筆促銷活動</li>
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

				<h3 style="text-align: center">新增一筆促銷活動</h3>
				<div class="container">
					<div class="row">
						<div class="col-sm-7 offset-sm-3 ">
							<form METHOD="post" ACTION="act.do"
								class="form-inline form-horizontal" name="insertform">
								<table class="table table-hover">
									<tr>
										<td>促銷活動名稱</td>
										<td><input class="form-control " type="TEXT"
											name="actName" placeholder="請新增一筆促銷活動"
											value="<%=(actVO == null) ? "" : actVO.getActName()%>" /></td>
									</tr>

									<tr>
										<td>促銷活動開始時間</td>
										<td><input name="actStart" id="f_date1" type="text"
											class="form-control " /></td>
									</tr>


									<tr>
										<td>促銷活動結束時間</td>
										<td><input name="actEnd" id="f_date2" type="text"
											class="form-control " /></td>
									</tr>

									<tr>
										<td>促銷折扣</td>
										<td><input name="discount" type="number"
											class="form-control " min="0" max="1"  step="0.01" /> 折</td>
									</tr>

									<tr>
										<td>選擇房型</td>
										<td class="form-inline">
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="checkbox"
												 name="roomType" value="RT01"> 
												<label class="form-check-label" >RT01</label>
										</div>
										
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="checkbox"
												 name="roomType"  value="RT02">
												 <label class="form-check-label" >RT02</label>
										</div>
										
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="checkbox"
												name="roomType"  value="RT03"> 
												<label class="form-check-label" >RT03</label>
										</div>		
										
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="checkbox"
												name="roomType"  value="RT04"> 
												<label class="form-check-label" >RT04</label>
										</div>										
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="checkbox"
												name="roomType"  value="RT05"> 
												<label class="form-check-label" >RT05</label>
										</div>										
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="checkbox"
												name="roomType"  value="RT06"> 
												<label class="form-check-label" >RT06</label>
										</div>										
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="checkbox"
												name="roomType"  value="RT07"> 
												<label class="form-check-label" >RT07</label>
										</div>										
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="checkbox"
												name="roomType"  value="RT08"> 
												<label class="form-check-label" >RT08</label>
										</div>										
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="checkbox"
												name="roomType"  value="RT09"> 
												<label class="form-check-label" >RT09</label>
										</div>										
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="checkbox"
												name="roomType"  value="RT10"> 
												<label class="form-check-label" >RT10</label>
										</div>


										</td>
									</tr>


								</table>

								<div class="col-12 text-center">
									<input type="hidden" name="action" value="insert">

									<button class="btn btn-info" type="submit">送出新增</button>
								</div>
							</form>
							
							<br><br>
							<div class="input-group">
  									<div class="input-group-prepend">
    									<span class="input-group-text">網站推播訊息</span>
  									</div>
  									<textarea class="form-control text-field" aria-label="With textarea"  id="message"  placeholder="親愛翔泰山莊的翔友們，請注意近期我們推出的促銷訊息"  onkeydown="if (event.keyCode == 13) sendMessage();"></textarea>
							</div>
								
								  <div class="panel input-area">
           							 <input id="userName" class="text-field" type="hidden" placeholder="使用者名稱" value="user"/>
            						<br>
            						 <input id="message2"  class="text-field" type="text" placeholder="訊息" onkeydown="if (event.keyCode == 13) sendMessage();"/>
            						<input type="submit" id="sendMessage" class="button" value="送出" onclick="sendMessage();"/>
		   							 <input type="button" id="connect"     class="button" value="連線" onclick="connect();"/>
		   							 <input type="button" id="disconnect"  class="button" value="離線" onclick="disconnect();"/>
	   							 </div>
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

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="<%=request.getContextPath()%>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>

</body>

<%
	java.sql.Date date = null;
	try {
		date = actVO.getActStart();
	} catch (Exception e) {
		date = new java.sql.Date(System.currentTimeMillis());
	}

	String str = null;
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
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=date%>', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
	$('#f_date2').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value :<%=str%>, // value:   new Date(),
		//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		//startDate:	            '2017/07/10',  // 起始日
		minDate : '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	
</script>



<script>
    
    var MyPoint = "/MyEchoServer";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
	var webSocket;
	
	//建立連線
	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket("ws://localhost:8081/CA105G4PO/MyEchoServer");
		
		webSocket.onopen = function(event) {
			console.log("WebSocket 成功連線");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

	
	
	}
	
	
	
	function sendMessage() {
	    
	    var inputMessage = document.getElementById("message");
	    var message = inputMessage.value.trim();
	    console.log(message);
	    if (message === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	        var jsonObj = {"message" : message};
	        console.log(JSON.stringify(jsonObj));
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.value = "";
	        inputMessage.focus();
	    }

	}
	function disconnect () {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}


    
</script>


</html>