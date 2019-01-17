<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon.model.*"%>
<%@page import="java.util.*"%>

<%
	CouponVO cpnVO = (CouponVO) request.getAttribute("cpnVO");
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

<title>新增優惠券</title>

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

img {
	max-width: 400px;
	max-height: 250px;
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
					<li class="breadcrumb-item active">新增優惠券</li>
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

							<form method="post" action="<%=request.getContextPath() %>/coupon/cpn.do" name="insertbraform"
								class="form-horizontal justify-content-center"
								enctype="multipart/form-data">


								<div class="form-row">
									<div class="form-group">
										<label for="aa">折扣金額:</label> <input type="text"
											name="discount" id="discount" placeholder="請輸入折扣金額"
											class="form-control" style="width: 200px">
									</div>

							
								</div>


								<div class="form-row">
<!-- 									<div class="form-group" style="margin-right: 15px"> -->
<!-- 										<label for="aa">數量:</label>  -->
<!-- 										<input type="number" name="quantity" -->
<!-- 											id="aa" placeholder="請輸入數量" class="form-control" -->
<!-- 											style="width: 140px"> -->
<!-- 									</div> -->




									<div class="form-group">
										<label for="aa">申請數量:</label>

										 <input type="text" name="appQuantity"
											id="aa" placeholder="申請數量" class="form-control"
											style="width: 140px ">
									</div>

								</div>

					

								<br>
				






								<div class="form-row">

									<div class="form-row" style="margin-bottom: 15px">
										<img id="blah" />
									</div>


									<div class="input-group mb-3 form-group">
										<div class="custom-file">

											<input class="custom-file-input" id="inputGroupFile01"
												name="cpnPic" multiple type="file" style="margin-left:-20px"> <label
												class="custom-file-label" for="inputGroupFile01"
												id="labelPicName">上傳優惠卷 file</label>

										</div>
										<div class="input-group-append">
											<span class="input-group-text" id="">Upload</span>
										</div>


									</div>

								</div>




								<div class="form-row">
								<div class="col-12 text-center">
									<input type="hidden" name="action" value="insert">
									<input class="btn btn-primary" type="submit" value="送出新增">
<!-- 									<button class="btn btn-primary">返回</button> -->
								</div>
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
					$('#blah').attr('src', e.target.result);

				}

				reader.readAsDataURL(this.files[0]);

			}
		});



	});
</script>



</html>