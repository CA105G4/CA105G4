<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.workExchangeRecord.model.*"%>
<%@ page import="java.util.*"%>

<% 
	WorkExchangeRecordVO workExchangeRecordVO = (WorkExchangeRecordVO)(request.getAttribute("workExchangeRecordVO"));
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

<title>SB Admin - Blank Page</title>

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
					<li class="breadcrumb-item active">修改打工換宿紀錄</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<h1>審核打工申請紀錄</h1>
				<hr>
				<div class="container-fluid">
					<div class="row">
						<form method="post"
							action="<%=request.getContextPath()%>/workExchangeRecord/workExchangeRecord.do"
							enctype="multipart/form-data" style="margin-bottom: 0px;">
							<jsp:useBean id="memberSvc"
								class="com.member.model.MemberService" />

							<table>
								<tr>
									<th>需求編號:&nbsp;</th>
									<td>${workExchangeRecordVO.weID}<input type="hidden"
										class="form-control" name="weID"
										value="${workExchangeRecordVO.weID}"></td>
								</tr>
								<tr>
									<th>會員姓名:&nbsp;</th>
									<td>${memberSvc.getOneMem(workExchangeRecordVO.memID).memName}
										<input type="hidden" class="form-control" name="memID"
										value="${workExchangeRecordVO.memID}">
									</td>
								</tr>
								<tr>
									<th>審核狀況:&nbsp;</th>
									<td><select name="werState" id="werState"
										class="form-control">
											<option value="0">待審核</option>
											<option value="1">通過</option>
											<option value="2">不通過</option>
									</select></td>
								</tr>
								<tr>
									<th>訂單編號:&nbsp;</th>
									<td>${workExchangeRecordVO.orderID}<input type="hidden"
										class="form-control" name="orderID"
										value="${workExchangeRecordVO.orderID}"></td>
								</tr>
								<tr>
									<th>申請圖片:&nbsp;</th>
									<td><img
										src="<%=request.getContextPath()%>/workExchangeRecord/workExchangeRecordImg.do?weID=${workExchangeRecordVO.weID}&memID=${workExchangeRecordVO.memID}"
										class="img-fluid"></td>
								</tr>
								<tr>
									<td></td>
									<td><input type="hidden" name="weApp"
										value="${workExchangeRecordVO.weApp}"> <input
										type="submit" class="btn-success form-control" value="確認修改">
										<input type="hidden" name="action" value="confirm_Modify">
									</td>
								</tr>
							</table>
						</form>
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

	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>

	<!--讓前面頁面的取得傳值過來 -->
	<script type="text/javascript">
  	$("#werState option[value="+<%= workExchangeRecordVO.getWerState()%>+"]").attr('selected', 'selected');			
  	</script>
</body>

</html>

