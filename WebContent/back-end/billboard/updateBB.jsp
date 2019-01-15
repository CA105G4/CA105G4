<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.billboard.model.*"%>
<%@page import="java.util.*"%>

<%
	BillboardVO bbVO = (BillboardVO) request.getAttribute("bbVO");//BBServlet.java (Concroller) 存入req的bbVO物件 (包括幫忙取出的bbVO, 也包括輸入資料錯誤時的bbVO物件)
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>修改輪播廣告</title>

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
.container {
	margin-left: -13px;
	padding: 10px;
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
					<li class="breadcrumb-item active">修改輪播廣告</li>
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


				<br>

				<div class="container">
					<div class="row">
						<div class="col-sm-7 offset-sm-3 ">

							<form method="post" action="<%=request.getContextPath()%>/billboard/bb.do" name="updateform"
								class="form-horizontal justify-content-center"
								enctype="multipart/form-data">

								<div class="form-row">
									<div class="form-group">
										<label for="aa">廣告編號:<font color=red><b>*</b></font></label> <input
											type="text" name="bbID" id="bbID" placeholder=""
											class="form-control" style="width: 200px"
											value="<%=bbVO.getbbID()%>">
									</div>
									
									<div class="form-group" style="margin-left: 15px">
										<label for="aa">URL:</label> <input type="text" name="url"
											id="aa" placeholder="" class="form-control"
											style="width: 200px" value="<%=bbVO.geturl()%>">
									</div>
									
								</div>

							
							
							
							

								<div class="form-row">
									<div class="form-group" style="margin-right: 15px">
										<label for="aa">廣告開始時間:</label> <input type="text" name="bbStart"
											id="f_date1" placeholder="" class="form-control"
											style="width: 180px" value="<%=bbVO.getbbStart()%>">

									</div>




									<div class="form-group">
										<label for="aa">廣告結束時間:</label> <input type="text" name="bbEnd"
											id="f_date2" placeholder="" class="form-control"
											style="width: 180px" value="<%=bbVO.getbbEnd()%>">
									</div>

								</div>



					
								<br>

<%

Base64.Encoder encoder = Base64.getEncoder();
String encodedText = "";

if (bbVO.getpic() != null) {
	encodedText = encoder.encodeToString(bbVO.getpic());
	pageContext.setAttribute("bbPic", new Integer(1));
} else {
	pageContext.setAttribute("bbPic", new Integer(0));
}

%>
								<div class="form-row">
									<div class="input-group mb-3 form-group">

										<c:choose>
									<c:when test="${ bbPic==1}">
												<img id="blah" width="377.8" height="250"
													src="data:image ;base64, <%=encodedText%>">
										</c:when> 


										<c:otherwise> 
											<img id="icon_preview" width="377.8" height="250" 
												src="<%=request.getContextPath()%>/image/noImage.jpg"> 
										</c:otherwise>



								</c:choose> 


									</div>
									
									
									
									
									<div class="input-group mb-3 form-group">

										<div class="custom-file">

											<input class="custom-file-input" id="inputGroupFile01"
												name="bbPic" multiple type="file"> <label
												class="custom-file-label" for="inputGroupFile02"
												id="labelPicName">上傳輪播廣告 file</label>
										</div>


										<div class="input-group-append">
											<span class="input-group-text" id="">Upload</span>
										</div>

									</div>

								</div>
																	

								<div class="col-12 text-center">
									<input type="hidden" name="action" value="update"> <input
										type="hidden" name="requestURL"
										value="<%=request.getParameter("requestURL")%>">
									<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
									<input class="btn btn-primary" type="submit" value="送出修改">
									<button class="btn btn-primary">返回</button>
								</div>

							</form>

							<!--解決按鈕置中的問題 https://stackoverflow.com/questions/41664991/bootstrap-4-how-do-i-center-align-a-button -->

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

<% 
  java.sql.Date date = null;
			java.sql.Timestamp time = null;
  try {
	  time = bbVO.getbbStart();
   } catch (Exception e) {
	   time = new java.sql.Timestamp(System.currentTimeMillis());
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
	$(function() {

		$(document).ready(function() {
			$('#inputGroupFile01').on('change', function(event) {
				// and you can get the name of the image like this:
				console.log(event.target.files[0].name);
				$('#labelPicName').text(event.target.files[0].name);
			});
		});



		$("#inputGroupFile01").change(function() {
			if (this.files && this.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#icon_preview').attr('src', e.target.result);

				}

				reader.readAsDataURL(this.files[0]);

			}
		});

	

	});
	
	$(function() {

		$(document).ready(function() {
			$('#inputGroupFile01').on('change', function(event) {
				// and you can get the name of the image like this:
				console.log(event.target.files[0].name);
				$('#labelPicName').text(event.target.files[0].name);
			});
		});

		$("#inputGroupFile01").change(function() {
			if (this.files && this.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#blah').attr('src', e.target.result);
				}

				reader.readAsDataURL(this.files[0]);

			}
		});
	});
	
	

    $.datetimepicker.setLocale('zh');
    $('#f_date1').datetimepicker({
       theme: '',              //theme: 'dark',
       timepicker:false,       //timepicker:true,
       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
	   value: '<%=date%> 	', // value:   new Date(),
//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//startDate:	            '2017/07/10',  // 起始日
//minDate:               '-1970-01-01', // 去除今日(不含)之前
//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});
$('#f_date2').datetimepicker({
	theme : '', //theme: 'dark',
	timepicker : false, //timepicker:true,
	step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
	format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
	value :<%=str%>, // value:   new Date(),
//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//startDate:	            '2017/07/10',  // 起始日
	minDate:               '-1970-01-01', // 去除今日(不含)之前
//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});
	
	
	
	
	
	
</script>



</html>