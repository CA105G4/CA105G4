<%@page import="java.util.*"%>
<%@page import="com.orderDetail.model.OrderDetailVO"%>
<%@page import="com.orders.model.OrdersVO"%>
<%@page import="com.orders.model.OrdersService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin - Blank Page</title>

<!-- <link href="https://fonts.googleapis.com/css?family=Rubik:300,400,500" rel="stylesheet"> -->
    <!-- 原本為網址(如上行)，自己新增資料夾 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/fontsgoogleapiscom.css">
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/animate.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/owl.carousel.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/magnific-popup.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/aos.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/ionicons.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery.timepicker.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/flaticon.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/icomoon.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/style.css">
	

</head>

<body bgcolor='white'>

<h4></h4>
<table id="table-1" class="myTable" style="width:760px">
	<tr><td>
		 <h3>${ordID} - 訂單明細</h3>
	</td></tr>
</table>

<table class="myTable table table-bordered table-striped table-hover" style="width:760px">
	<tr>
		<th>訂單編號</th>
		<th>房型名稱</th>
		<th>入住日期</th>
		<th>退房日期</th>
		<th>評價</th>
		<th>加床</th>
	</tr>
	<c:forEach var="odVO" items="${odSet}">
		<tr>
			<td>${odVO.ordID}</td>
		<jsp:useBean id="rtSvc" scope="page" class="com.roomType.model.RoomTypeService" />
			<td>${rtSvc.getOneRoomType(odVO.getRtID()).rtName}</td>
			<td>${odVO.checkIn}</td>
			<td>${odVO.checkOut}</td>
	<jsp:useBean id="odSvc" scope="page" class="com.orderDetail.model.OrderDetailService" />
	<jsp:useBean id="ordSvc" scope="page" class="com.orders.model.OrdersService" />
			<c:if test="${odVO.evaluates == 0.0}">
				<c:choose>
					<c:when test="${ordSvc.getOneOrders(ordID).getOrdState() == 0}">
						<td>尚無法給評</td>
					</c:when>
					<c:when test="${ordSvc.getOneOrders(ordID).getOrdState() == 1}">
						<td>尚無法給評</td>
					</c:when>
					<c:when test="${ordSvc.getOneOrders(ordID).getOrdState() == 2}">
						<td><span class="ratyli test" data-rate="0" data-ratemax="5" id="${odVO.odID}"></span> </td>
					</c:when>
					<c:otherwise>
						<td>無法給評</td>
					</c:otherwise>
				</c:choose>
			</c:if>

			<c:if test="${odVO.evaluates != 0.0}">
				<td><span class="ratyli test" data-rate="${odVO.evaluates}" data-ratemax="5" id="${odVO.odID}"></span> </td>
			</c:if>
			
			<td>${specialMap.get(odVO.special)}</td>
		</tr>
	</c:forEach>
</table>

  <script>
  $(function() {
	  console.log(222);
	  $('.ratyli').ratyli();
	  updateEvaluates();
	});   
  
	function updateEvaluates(){
		  $('.test').on('click', function(){
	    	  console.log($(this).attr('data-rate'));
	    	  console.log($(this).attr('id'));
	    	  
				$.ajax({
					url: "<%=request.getContextPath()%>/roomType/AjaxResRoomType.do",
					type: "get",
					data: { 
							action: 'updateEvaluates', 
							evaluates: $(this).attr('data-rate'),
							odID: $(this).attr('id')
						},
					dataType: 'json',
					success: function(res){
						console.log(res.odID);
						console.log(res.evaluates);
						$('#'+res.odID).attr('data-rate', res.evaluates);
						
						$(function(){
							swal("評價成功!", "感謝您的支持!", "success");
						});
					},
					error: function(res){
							swal("Sorry!", "已給過評價囉!", "error");
					}
				});
	    	  
	     });	
	}
  </script>  

</body>
	<!-- sweetalert-->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</html>
