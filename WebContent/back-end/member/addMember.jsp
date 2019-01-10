<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
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

<title>新增會員</title>

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
					<li class="breadcrumb-item active">新增會員</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-8">
							<h1>新增會員</h1>
							<hr>

							<h3>資料新增:</h3>

							<%-- ���~��C --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>

							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/member/mem.do"
								name="form1" enctype="multipart/form-data">
								<table>
									<tr>
										<td>會員姓名:</td>
										<td><input type="TEXT" name="memName" size="45"
											value="<%=(memberVO == null) ? "Tomdag" : memberVO.getMemName()%>" /></td>
									</tr>
									<tr>
										<td>會員帳號:</td>
										<td><input type="TEXT" name="memAcc" size="45"
											value="<%=(memberVO == null) ? "overtom" : memberVO.getMemAcc()%>" /></td>
									</tr>
									<tr>
										<td>會員密碼:</td>
										<td><input type="TEXT" name="memPsw" size="45"
											value="<%=(memberVO == null) ? "overdag" : memberVO.getMemPsw()%>" /></td>
									</tr>
									<tr>
										<td>生日:</td>
										<td><input type="TEXT" name="memBirth" id="f_date1"
											size="45" /></td>
									</tr>
									<tr>
										<td>會員信箱:</td>
										<td><input type="TEXT" name="memEmail" size="45"
											value="<%=(memberVO == null) ? "tomdog@gmail.com" : memberVO.getMemEmail()%>" /></td>
									</tr>
									<tr>
										<td>會員電話:</td>
										<td><input type="TEXT" name="memTel" size="45"
											value="<%=(memberVO == null) ? "097853656" : memberVO.getMemTel()%>" /></td>
									</tr>
									<tr>
										<td>地址:</td>
										<td><input type="TEXT" name="memAddr" size="45"
											value="<%=(memberVO == null) ? "台北市永和區明樂街59號10樓" : memberVO.getMemAddr()%>" /></td>
									</tr>
									<tr>
										<td>性別:</td>
										<td><input type="radio" name="memSex" value="M">男
											<input type="radio" name="memSex" value="F">女<span
											style="color: red;">※ </span><br></td>
									</tr>
									<tr>
										<td>會員技能:</td>
										<td><input type="TEXT" name="memSkill" size="45"
											value="<%=(memberVO == null) ? "大胃王" : memberVO.getMemSkill()%>" /></td>
									</tr>
									<tr>
										<td>身分字號:</td>
										<td><input type="TEXT" name="memIDcard" size="45"
											value="<%=(memberVO == null) ? "T123456789" : memberVO.getMemIDcard()%>" /></td>
									</tr>
									<tr>
										<td>會員頭貼:</td>
										<td><img
											src="<%=request.getContextPath()%>/back-end/member/images/nopic.jpg"
											id="previewpic" class="img-fluid" width="300px"></td>
									</tr>
									<tr>
										<td></td>
										<td><input type="file" name="memPic" id="inputfile01">
										</td>
									</tr>


								</table>
								<br> <input type="hidden" name="action" value="insert">
								<input type="submit" value="送出">
							</FORM>
						</div>
						<!-- /.container-fluid -->

						<!-- Sticky Footer -->
						<footer class="sticky-footer">
							<div class="container my-auto">
								<div class="copyright text-center my-auto">
									<span>© MCPIG 2019</span>
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


				<%
					java.sql.Date hiredate = null;
					try {
						hiredate = memberVO.getMemBirth();
					} catch (Exception e) {
						hiredate = new java.sql.Date(System.currentTimeMillis());
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
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hiredate%>
					', // value:   new Date(),
					//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
					//startDate:	            '2017/07/10',  // 起始日
					//minDate:               '-1970-01-01', // 去除今日(不含)之前
					//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
					});
				</script>
			</div>
		</div>
	</div>
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

	<script>
		$("#inputfile01").change(function() {
			if (this.files && this.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#previewpic').attr('src', e.target.result);
				}

				reader.readAsDataURL(this.files[0]);
			}
		});
	</script>
</body>

</html>
