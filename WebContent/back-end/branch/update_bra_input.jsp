<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.branch.model.*"%>
<%@page import="java.util.*"%>

<%
	BranchVO braVO = (BranchVO) request.getAttribute("braVO");//braServlet.java (Concroller) 存入req的bchVO物件 (包括幫忙取出的bchVO, 也包括輸入資料錯誤時的braVO物件)
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

<title>修改分店資料</title>

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
					<li class="breadcrumb-item active">修改分店資料</li>
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

							<form method="post" action="<%=request.getContextPath() %>/branch/bra.do" name="updateform"
								class="form-horizontal justify-content-center"
								enctype="multipart/form-data">


								<div class="form-row">
									<div class="form-group">
										<label >分店編號:<font color=red><b>*</b></font></label> 
										${braVO.getBraID()}
<!-- 										<input type="text" name="braID" id="braID"  -->
<!-- 											class="form-control" style="width: 200px"  -->
<%-- 											value="<%=braVO.getBraID()%>"> --%>
									</div>
								</div>



								<div class="form-row">
									<div class="form-group">
										<label >分店名稱:</label> <input type="text"
											name="braName" id="braName" placeholder=""
											class="form-control" style="width: 200px"
											value="<%=braVO.getBraName()%>">
									</div>

									<div class="form-group" style="margin-left: 15px">
										<label >分店電話:</label> <input type="text" name="phone"
											 placeholder="" class="form-control"
											style="width: 200px" value="<%=braVO.getBraTel()%>">
									</div>
								</div>


								<div class="form-row">
									<div class="form-group" style="margin-right: 15px">
										<label>分店經度:</label> <input type="text" name="lng"
											 placeholder="" class="form-control"
											style="width: 140px" value="<%=braVO.getBraLng()%>">

									</div>




									<div class="form-group">
										<label >分店緯度:</label> <input type="text" name="lat"
											 placeholder="" class="form-control"
											style="width: 140px" value="<%=braVO.getBraLat()%>">
									</div>

								</div>

								<div class="form-group">
									<label >分店地址:</label> <input type="text"
										class="form-control" id="inputAddress" placeholder=""
										style="margin-left: -4px" name="addr"
										value="<%=braVO.getBraAddr()%>">
								</div>


								<div class="form-row">

									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text">分店介紹</span>
										</div>
										<textarea class="form-control" aria-label="With textarea"
											Cols="25" Rows="5" name="intro"
											placeholder="<%=braVO.getBraIntro()%>"></textarea>


									</div>

								</div>
								<br>

<%

Base64.Encoder encoder = Base64.getEncoder();
String encodedText = "";

if (braVO.getBraPic() != null) {
	encodedText = encoder.encodeToString(braVO.getBraPic());
	pageContext.setAttribute("braPic", new Integer(1));
} else {
	pageContext.setAttribute("braPic", new Integer(0));
}

%>




								<div class="form-row">
									<div class="input-group mb-3 form-group">



										<c:choose>
									<c:when test="${braPic==1}">
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
												name="braPic" multiple type="file"> <label
												class="custom-file-label" for="inputGroupFile02"
												id="labelPicName">上傳分店照片 file</label>
										</div>


										<div class="input-group-append">
											<span class="input-group-text" id="">Upload</span>
										</div>

									</div>

								</div>

								<%
									Base64.Encoder encoder1 = Base64.getEncoder();
									String encodeText1 = "";

									if (braVO.getBraVideo() != null) {
										encodeText1 = encoder1.encodeToString(braVO.getBraVideo());
										pageContext.setAttribute("braVideo", new Integer(1));
									} else {
										pageContext.setAttribute("braVideo", new Integer(0));
									}
								%>





								<div class="form-row">
									<c:choose>

										<c:when test="${braVideo==1}">
											<div class="form-row" style="margin-bottom: 15px">
												<video id="vlah" width="377.8" height="250" controls>
													<source type="video/webm"
														src="data:video/webm;base64,<%=encodeText1%>">
												</video>
											</div>
										</c:when>

										<c:otherwise>
											<video id="vlah" width="377.8" height="250" controls></video>
										</c:otherwise>

									</c:choose>
								</div>

								<br>
								<div class="form-row">
									<div class="input-group mb-3 form-group">
										<div class="custom-file">
											<input type="file" class="custom-file-input"
												id="inputGroupFile02" name="braVideo"> <label
												class="custom-file-label" for="inputGroupFile02"
												id="labelVideoName">上傳分店影片 file</label>
										</div>
										<div class="input-group-append">
											<span class="input-group-text">Upload</span>
										</div>
									</div>

								</div>






								<div class="form-group">


									<div class="form-check form-check-inline form-group">
										<input class="form-check-input" type="radio" name="braState"
											value="1" checked="checked"> <label
											class="form-check-label" >營業中</label>
									</div>
									<div class="form-check form-check-inline form-group">
										<input class="form-check-input" type="radio" name="braState"
											 value="0"> <label
											class="form-check-label" >休息中</label>
									</div>
								</div>

								<div class="col-12 text-center">
									<input type="hidden" name="action" value="update"> 
									<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
									<input type="hidden" name="braID" value="<%= braVO.getBraID() %>">
									<input class="btn btn-primary"  type="submit" value="送出修改">
									
									<button class="btn btn-primary">
										<a href="<%=request.getContextPath()%>/back-end/branch/listAllBranch.jsp"   style="color:#fff">返回</a>
									</button>
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
<script>
	$(function() {

		$(document).ready(function() {
			$('#inputGroupFile01').on('change', function(event) {
				// and you can get the name of the image like this:
				console.log(event.target.files[0].name);
				$('#labelPicName').text(event.target.files[0].name);
			});
		});

		$(document).ready(function() {
			$('#inputGroupFile02').on('change', function(event) {
				// and you can get the name of the image like this:
				console.log(event.target.files[0].name);
				$('#labelVideoName').text(event.target.files[0].name);
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

		$("#inputGroupFile02").change(function() {
			if (this.files && this.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#vlah').attr('src', e.target.result);

				}

				reader.readAsDataURL(this.files[0]);

			}
		});

	});
</script>



</html>