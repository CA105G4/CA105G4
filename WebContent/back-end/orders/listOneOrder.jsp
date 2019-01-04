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
	
<!-- datepicker-->
<link href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" 
	rel="stylesheet" type="text/css" />
	
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h3 {
    color: black;
    display: block;
    margin: 5px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
    text-align: center;
  }
  
</style>

</head>

<body bgcolor='white'>

<h4></h4>
<table id="table-1" class="myTable" style="width:760px">
	<tr><td>
		 <h3>${ordID} - 訂單明細</h3>
	</td></tr>
</table>

<table class="myTable" style="width:760px">
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
			<td>${odVO.evaluates}</td>
			<td>${odVO.special}</td>
		</tr>
	</c:forEach>
</table>

</body>

</html>
