<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.activityDetail.model.*"%>
<%@page import="java.util.*"%>

<jsp:useBean id="listDetail_ByactID" scope="request"
	type="java.util.Set<ActivityDetailVO>" />
<jsp:useBean id="rtSvc" scope="page"
	class="com.roomType.model.RoomTypeService" />

<%--    <% 
//     ActivityDetailVO adVO =(ActivityDetailVO)request.getAttribute("adVO"); 
%> --%>

<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>促銷活動明細</title>

<!-- Bootstrap core CSS-->


</head>

<body id="page-top">    <!-- include 進來用 -->

	<!-- Navbar -->

<%-- 	<jsp:include page="/back-end/navbar.jsp" /> --%>
	
	<!-- /Navbar -->
	
<!-- 	<div id="wrapper"> -->

		<!-- Sidebar -->
		
<%-- 		<jsp:include page="/back-end/sidebar.jsp" /> --%>
		
		<!-- /Sidebar -->

		<div id="content-wrapper">

			<div class="container-fluid">

				<!-- Breadcrumbs-->
<!-- 				<ol class="breadcrumb"> -->
<!-- 					<li class="breadcrumb-item"><a -->
<%-- 						href="<%=request.getContextPath()%>/back-end/room/roomState.jsp">首頁</a> --%>
<!-- 					</li> -->
<!-- 					<li class="breadcrumb-item active">促銷活動明細</li> -->
<!-- 				</ol> -->

				<!-- Page Content 這邊開始自由發揮-->
				<h3>促銷活動明細</h3>

				<div class="container">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>促銷編號</th>
								<th>促銷活動名稱</th>
								<th>房型名稱</th>
								<th>折扣</th>
								<th>刪除</th>
							</tr>
						</thead>
						<tbody>
							<jsp:useBean id="actSvc" scope="page" class="com.activity.model.ActivityService" />
							
							<c:forEach var="adVO" items="${listDetail_ByactID}">

								<tr id="${adVO.rtID}">
								
									<td>${adVO.actID}</td>
									<td>${actSvc.getOneByID(adVO.actID).actName}</td>

									<td>
										<c:forEach var="rtVO" items="${rtSvc.all}">
											<c:if test="${rtVO.rtID==adVO.rtID}">
												${rtVO.rtName}		
											</c:if>
										</c:forEach></td>
									<td>${adVO.discount}</td>
									
									<td>
										<form METHOD="post"
											style="margin-bottom: 0px;" id="form1" onclick="formSubmit();">
											<button class="btn btn-info deleteAD"  type="button">刪除</button>
											<input type="hidden" name="actID" value="${adVO.actID}">
											<input type="hidden" name="rtID" value="${adVO.rtID}">
											<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											<input type="hidden" name="action" value="delete_activitydetail">
										</form>
									</td>
									
								</tr>

							</c:forEach>
						</tbody>


					</table>
				</div>

				<!-- Page Content 這邊開始自由發揮結束-->
			</div>
			<!-- /.container-fluid -->

			<!-- Sticky Footer -->

		</div>

	</div>
	<!-- /.content-wrapper -->

	<!-- /#wrapper -->

	<!-- Scroll to Top Button-->

	<!-- Logout Modal-->






	<!-- Bootstrap core JavaScript-->


</body>
	<!--   //sweet alert 引用 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.all.js"></script>
<script type="text/javascript">
   function formSubmit(){

	   $.ajax({
	       url:'<%=request.getContextPath()%>/activity/act.do',
	       data: $("#form1").serialize(),
	       dataType: "json",
	       success: function (data) {
	    	   $('#' + data.rtID).remove();
	    	   Swal({
	    		   position: 'top-end',
	    		   type: 'success',
	    		   title: 'Your work has been saved',
	    		   showConfirmButton: false,
	    		   timer: 1500
	    		 });
	    	   
	      },
	      error: function(){    
         	 alert("大家好");     
          }
	       
	  });
	  }
  
  </script>
</html>