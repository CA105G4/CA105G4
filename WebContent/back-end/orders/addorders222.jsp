<%@page import="com.orders.model.OrdersVO"%>
<%@ page import="com.roomType.model.*"%>
<%@ page import="com.branch.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
  OrdersVO ordVO = (OrdersVO) request.getAttribute("ordVO");
%>

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
					<li class="breadcrumb-item"><a href="index.html">Dashboard</a>
					</li>
					<li class="breadcrumb-item active">Blank Page</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<h1>新增訂單</h1>
				<hr>
				<div class="container-fluid" >
				<br>
					<div class="row">
						<div class="col-xs-12 col-sm-2">
							
						</div>
						<div class="col-xs-12 col-sm-8">
							<div align="center">
								<c:if test="${not empty errorMsgs}">
									<font style="color:red">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color:red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>		
							</div>					
						</div>
						<div class="col-xs-12 col-sm-2">
							
						</div>
					</div>
					<div class="row">  
						<div class="col-xs-12 col-sm-1">
							
						</div>
						<div class="col-xs-12 col-sm-10">
							<div align="center">
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" name="form1" autocomplete="off">
								<table>
									<tr>
										<th>會員編號:</th>
										<td><input type="TEXT" name="memID" size="45" class="form-control"
											 value="<%= (ordVO==null)? "M0001" : ordVO.getMemID() %>" readonly="true"/></td>
									</tr>
									<jsp:useBean id="brSvc" scope="page" class="com.branch.model.BranchService" />
									<tr>
										<th>分店編號:</th>
										<td>
											<select size="1" name="braID" id="selectBranch" class="custom-select">
												<option value="-1">請選擇</option>
												<c:forEach var="brVO" items="${brSvc.all}">
													<option value="${brVO.braID}">${brVO.braName}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<th>訂單種類:</th>
										<td><select size="1" name="ordType" class="custom-select">
												<option value="0">線上</option>
												<option value="1">臨櫃</option>
												<option value="2">打工換宿</option>
											</select></td>
									</tr>
									<tr>
										<th>入住人數:</th>
										<td><input type="TEXT" name="numOfGuest" size="45" class="form-control"
											 value="<%= (ordVO==null)? "2" : ordVO.getNumOfGuest() %>" /></td>
									</tr>
									<tr>
										<th>付款方式:</th>
										<td><select size="1" name="payment" class="custom-select">
												<option value="0">現金</option>
												<option value="1">信用卡</option>
											</select>
										</td>				
									</tr>
									<tr>
										<th>入住日期:</th>
										<td><input type="text" name="checkIn" id="start_date" size="45" class="form-control"/></td>
									</tr>
									<tr>
										<th>退房日期:</th>
										<td><input type="text" name="checkOut" id="end_date" size="45" class="form-control"/></td>
									</tr>
								</table>
								<!-- 以下為明細 -->
								<caption>選擇房型</caption>
								<table class="orderDetail">
									<tr>
										<td>
											<img src="<%=request.getContextPath()%>/roomType/roomTypeImg.do?rtID=RT01" class="img-fluid showrtpic" width="200px">
										</td>
										<jsp:useBean id="rtSvc" scope="page" class="com.roomType.model.RoomTypeService" />
										<td>選擇房型:
											<select size="1" name="rtID" class="custom-select selectrtPic">
													<option value="-1">請選擇</option>
											</select>
										</td>
										<td>房間數量:
											<select size="1" name="numOfRoom" class="custom-select">
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
												<option value="6">6</option>
												<option value="7">7</option>
												<option value="8">8</option>
												<option value="9">9</option>
												<option value="10">10</option>
											</select>
										</td>
										<td>加床需求:
											<select size="1" name="special" class="custom-select">
												<option value="0">不加床</option>
												<option value="1">加床</option>
											</select>
										</td>
										<td>
											<input type="button" class="add btn btn-secondary" value="新增">
										</td>
									</tr>
								</table>
								<br>
									<input type="hidden" name="action" value="insert">
									<input type="submit" value="送出新增" class="btn btn-info" >
									<button type="button" class="btn btn-info">
										<a href='<%=request.getContextPath()%>/back-end/orders/select_orders_page.jsp' style="color:#fff">返回</a>
									</button>
								</FORM>	
														
							</div>
						</div>
						<div class="col-xs-12 col-sm-1">
							
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

	<!-- Custom scripts for all pages-->
	<script src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>
	
	<!-- datetimepicker-->
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	
	<!-- sweetalert-->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	
	<script>
		$.datetimepicker.setLocale('zh'); 
		$(function(){
			 $('#start_date').datetimepicker({
			  format:'Y-m-d',
			  minDate: new Date(),
			  maxDate: new Date(new Date().getTime() + 30*24*60*60*1000),
// 			  onShow:function(){
// 			   this.setOptions({
// 			    maxDate:$('#end_date').val()?$('#end_date').val():false
// 			   })
// 			  },
			  timepicker:false
			 });
			 
			 $('#end_date').datetimepicker({
			  format:'Y-m-d',
			  onShow:function(){
				   this.setOptions({
				    minDate:$('#start_date').val()?$('#start_date').val():false
				   })
			  },
			  maxDate: new Date(new Date().getTime() + 30*24*60*60*1000),
			  timepicker:false
			 });

			 initAddOrder();
			 changertpic();
			 
			 changeRoomTypebyBranch();

		});
	</script>
	
	<script>

		
	
	function initAddOrder(){
	      $('.orderDetail').on('click', '.add', function(){
	       		var str = '';
	       		for(var i = 0; i < selectData.length; i++){
		        	str += ("<option value='"+selectData[i].value+"'>"+selectData[i].text+"</option>");
		       	}
		        $(this).closest('tr').after(
			         '<tr>'+
 			         '<td><img src="<%=request.getContextPath()%>/roomType/roomTypeImg.do?rtID=RT01" class="img-fluid showrtpic" width="200px">'+
			         '</td>'+
			         '<td>選擇房型:'+
			          '<select size="1" name="rtID" class="custom-select selectrtPic">'+
			            '<option value="-1">請選擇</option>'+
			            str+
			          '</select>'+
			         '</td>'+
			         '<td>房間數量:'+
			          '<select size="1" name="numOfRoom" class="custom-select">'+
			           '<option value="1">1</option>'+
			           '<option value="2">2</option>'+
			           '<option value="3">3</option>'+
			           '<option value="4">4</option>'+
			           '<option value="5">5</option>'+
			           '<option value="6">6</option>'+
			           '<option value="7">7</option>'+
			           '<option value="8">8</option>'+
			           '<option value="9">9</option>'+
			           '<option value="10">10</option>'+
			          '</select>'+
			         '</td>'+
			         '<td>加床需求:'+
			          '<select size="1" name="special" class="custom-select">'+
			           '<option value="0">不加床</option>'+
			           '<option value="1">加床</option>'+
			          '</select>'+
			         '</td>'+
			         '<td>'+
			          '<input type="button" class="add btn btn-secondary" value="新增">'+
			          '<input type="button" class="remove btn btn-secondary" value="刪除">'+
			         '</td>'+
			        '</tr>'
		        );
	
		       refreshItem();
	     });	
	      
		$('.orderDetail').on('click', '.remove', function(){
			$(this).closest('tr').remove();
			refreshItem();
		});
	}
	function refreshItem(){
		$('.orderDetail tr').each(function(index, item){
			$(this).find('.rank').text(index);
		});	
	};
	
	function changertpic(){
	    $('.orderDetail').on('change', '.selectrtPic', function(){
	     var rtid = $(this).val();
	     console.log(rtid);
	     console.log($(this).parent().prev().find('img'));
	     $(this).parent().prev().find('img').attr('src', "<%=request.getContextPath()%>/roomType/roomTypeImg.do?rtID="+rtid);
	     refreshItem();
	    });
	}
	
	var selectData = []; 
	
	function changeRoomTypebyBranch(){
	    $('#selectBranch').change(function(){
		     $.ajax({
			      type:"GET",
			      url:"<%=request.getContextPath()%>/roomType/AjaxResRoomType.do",
			      data:creatQueryString($(this).val()),
			      dataType: "json",
			      success: function (data){
				       console.log(data);
				       clearSelect();
				       selectData = [];
				       $.each(data, function(i, item){ //foreach寫法
					        $('.selectrtPic').append("<option value='"+item.rtID+"'>"+item.rtName+"</option>");
					        var obj = {value: item.rtID, text: item.rtName};
					        selectData.push(obj);
			      	   });
		     	  },
		     error: function(){alert("AJAX-grade發生錯誤囉!")}
		     })
	    })
	}
	
	function creatQueryString(braID){
		console.log("braID:" + braID);
		var queryString= {"action":"getRoomTypeByBranch","braID":braID};
		return queryString;
	}
	
	function clearSelect(){
		$('.selectrtPic').empty();	//清資料
		$('.selectrtPic').append("<option value='-1'>請選擇</option>");
	}
	</script>
</body>

</html>
