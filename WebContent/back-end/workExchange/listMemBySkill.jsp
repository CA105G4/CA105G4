<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
	String weName =(String)request.getAttribute("weName");
	System.out.println(weName);	
	
	MemberService memSvc = new MemberService();
	List<MemberVO> list = memSvc.getMemBySkill(weName); 
	pageContext.setAttribute("list",list);
	System.out.println(list== null);
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

<title>會員列表</title>

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
					<li class="breadcrumb-item active">會員列表</li>
				</ol>

				<!-- DataTables Example -->
				<div class="card mb-3">
					<div class="card-header">
						<i class="fas fa-table"></i> 符合打工需求會員
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
								<thead>
									<tr>
										<th>會員編號</th>
										<th>會員姓名</th>
										<th>性別</th>
										<th>身分證字號</th>
										<th>郵件信箱</th>
										<th>聯絡電話</th>
										<th>會員狀態</th>
										<th>地址</th>
										<th>會員技能</th>
										<th>打工邀請</th>

									</tr>
								</thead>
								<tbody>

									<c:forEach var="memberVO" items="${list}">
										<tr>
											<td>${memberVO.memID}</td>
											<td>${memberVO.memName}</td>
											<td>${memSexMap.get(memberVO.getMemSex())}</td>
											<td>${memberVO.memIDcard}</td>
											<td>${memberVO.memEmail}</td>
											<td>${memberVO.memTel}</td>
											<td>${memStateMap.get(memberVO.getMemState())}</td>
											<td>${memberVO.memAddr}</td>
											<td>${memberVO.memSkill}</td>
											<td align="center">
													<input type="button" class="btn btn-warning sendEmail" value="發送"> 
													<input type="hidden" name="memID" id="${memberVO.memID}" value="${memberVO.memID}">
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
			</div>
			<!-- /.container-fluid -->
			
			<!-- Ajax送信回原畫面 -->
			<script>
			$(function(){
				$('.sendEmail').click(function(){
					console.log("123");
					$.ajax({
						type:"POST",
						url: "<%=request.getContextPath()%>/workExchange/workExchange.do",
						data :{
								"action" : 'send_Email',
								"memID"  : $(this).next().val(),
						},
						dataType : 'json',
						success : function(res){
							console.log(res);
							console.log(res.memID);
							swal("發送成功!", "已傳送訊息!", "success");
						},
						error : function() {
							alert("出大事啦!!!");
						}
					})
				})
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

	<!-- Page level plugin JavaScript-->
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/datatables/jquery.dataTables.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>

	<!-- Demo scripts for this page-->
	<script
		src="<%=request.getContextPath()%>/back-end/js/demo/datatables-demo.js"></script>
		
	 <!-- sweetalert-->
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</body>
</html>
