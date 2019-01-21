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

<title>新增訂單</title>

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
					<li class="breadcrumb-item">
						<a href="<%=request.getContextPath()%>/back-end/room/roomState.jsp">首頁</a>
					</li>
					<li class="breadcrumb-item active">新增訂單</li>
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
										<td><input type="hidden" name="memID" id="memID" value=""></td>
										<td>
											<img id="image" src="" style="display: none; border-radius: 50%; width:400px;"></img>
											<div align="center"><p id="answer"></p>	<p id="answer2"></p></div>
										</td>
									</tr>
									<tr>
										<th>會員身分證字號:</th>
										<td>
											<input type="TEXT" name="memIDcard" size="45" class="form-control" id="question" />
										</td>
									</tr>
									<tr>
										<jsp:useBean id="brSvc" scope="page" class="com.branch.model.BranchService" />
										<th>分店編號:</th>
										<td>
											<select size="1" name="braID" id="selectBranch" class="custom-select">
												<option value="-1">請選擇</option>
												<c:forEach var="brVO" items="${brSvc.all}">
													<option value="${brVO.braID}" ${(brVO.braID)==(employeeVO.braID)? 'selected' : '' } >${brVO.braName}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<th>訂單種類:</th>
										<td><select size="1" name="ordType" class="custom-select">
												<option value="0">線上</option>
												<option value="1" selected>臨櫃</option>
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
										<td><input type="text" name="checkIn" id="start_date" size="45" class="form-control" /></td>
									</tr>
									<tr>
										<th>退房日期:</th>
										<td><input type="text" name="checkOut" id="end_date" size="45" class="form-control" /></td>
									</tr>
								</table>
								
								<br>
								<div align="center" id="saychoose"></div>
								<br>	
														
								<!-- 以下為明細 -->
								<table class="roomTypeList">
										<!-- 明細在這 -->
								</table>
								<br>
									<input type="hidden" name="action" value="insert">
									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
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

	<!-- Custom scripts for all pages-->
	<script src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>
	
	<!-- datetimepicker-->
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	
	<!-- sweetalert-->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	
	<!-- sweetalert-->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	
  <script>
		$.datetimepicker.setLocale('zh'); 
		$(function(){
			 $('#start_date').datetimepicker({
			  format:'Y-m-d',
			  minDate: new Date(),
			  maxDate: new Date(new Date().getTime() + 30*24*60*60*1000),
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
			 
			 checkmemIDcardAJAX();
			 changeRoomTypebyBranch();
			 changeRoomTypeNumbers();

		});
 </script>
 
 <script>
		var timer;
		function checkmemIDcardAJAX(){
			$('#question').on('keyup', function(){
				$('#answer').text('資料輸入中...');
				$('#answer2').text('');
				$('#image').attr('src', '').css('display', 'none');
				var question = $(this).val();
				_debounce(function(){ 
					return getAnswer(question); 
				}, 500);
			});
		};
		
		function getAnswer(question){
			$('#answer').text('Thinking...');
			$.ajax({
				url: "<%=request.getContextPath()%>/roomType/AjaxResRoomType.do",
				type: "get",
				data: { 
						action: 'checkmemIDcard', 
						question: $('#question').val() 
					},
				dataType: 'json',
				success: function(res){
					console.log(res);
					$('#answer').text('');
					$('#answer2').text(res.answer2);
					$('#image').attr('src', "<%=request.getContextPath()%>/member/memImg.do?memID="+res.answer).css('display', 'block');
					$('#memID').attr('value' , res.answer);
				},
				error: function(res){
					$('#answer').text('查無此會員!請重新輸入會員身分證字號!');
				}
			});
		}
		
		function _debounce(callback, time){
			if(timer)
				 clearTimeout(timer);
			timer = setTimeout(function(){
				callback();
			}, time);
		}
  </script>
  
  <script>
	function changeRoomTypebyBranch(){
	    $('#selectBranch').change(function(){
		     $.ajax({
			      type:"GET",
			      url:"<%=request.getContextPath()%>/roomType/AjaxResRoomType.do",
			      data:creatQueryString($(this).val()),
			      dataType: "json",
			      success: function (data){
			    	   $('#saychoose').html("<span style='font-weight:bold;font-size:16px;'>請選擇以下房型及數量</span>");
				       console.log(data);
				       clearTable();
				       var labelcount = 1;
				       $.each(data, function(i, item){ //foreach寫法<option value='"+item.rtID+"'>"+item.rtName+"</option>
					        $('.roomTypeList').append("<tr>"+
					        							 "<td>"+					        
					        							 	"<input class='form-check-input' type='checkbox' name='rtID' value='" + item.rtID + "' id='defaultCheck" + labelcount + "' >"+
					       								 "</td>"+
					        							 "<td>"+
					        							 "<label class='form-check-label' for='defaultCheck" + labelcount + "'>"+
					        								 "<img src='<%=request.getContextPath()%>/roomType/roomTypeImg.do?rtID=" + item.rtID + "' class='img-fluid showrtpic' style='border-radius: 10%; width:400px;' >"+
					        							 "</label>"+
					        							 "</td>"+
					        							 "<td>"+					        
					        							 	item.rtName+
					       								 "</td>"+
					        							 "<td>"+					        
					        							 	"<select size='1' name='" + item.rtID + "' class='custom-select rtNumClean' id='" + item.rtID + "'>"+
					        							 		"<option value='-1'>請選擇</option>"+
					        							 	"</select>"+
					       								 "</td>"+
					        							 "<td>"+					        
					        							 	"<input type='hidden' name='special' value='0'>"+
					       								 "</td>"+
					        						 "</tr>"
					     	);
					        labelcount++;
			      	   });
		     	  },
		     error: function(){
		    		swal("請選擇分店!", "請重新選擇入住的分店!","warning");
				}
		     })
	    })
	}
	
	function creatQueryString(braID){
		console.log("braID:" + braID);
		var queryString= {"action":"getRoomTypeByBranch","braID":braID};
		return queryString;
	}
	
	function clearTable(){
		$('.roomTypeList').empty();	//清資料
		$('.roomTypeList').append("");
// 		$('#saychoose').html("<span style='font-weight:bold;font-size:16px;'></span>");
	}
  </script>
  
  <script>
	function changeRoomTypeNumbers(){
	    $('#end_date').blur(function(){
	    	console.log("123");
		     $.ajax({
			      type:"GET",
			      url:"<%=request.getContextPath()%>/roomType/AjaxResRoomType.do",
			      data:{
				    	  	"action":"getRoomTypeNumbers",
				    	  	"checkOutDay":$(this).val(),
				    	  	"checkInDay":$('#start_date').val(),
				    	  	"braID":$('#selectBranch').val()	    	  	
			    	  	},
			      dataType: "json",
			      success: function (data){
				       console.log(data);
				       clearSelect();
				       $.each(data, function(i, item){ //foreach寫法<option value='"+item.rtID+"'>"+item.rtName+"</option>
					       
				       		var minroomnum = parseInt(item.balance);
					        console.log(minroomnum);
				       		for(var j=1; j<=minroomnum; j++){
// 				       			alert("#"+item.rtID+"---<option value="+j+">"+j+"</option>");
					        	$("#"+item.rtID).append("<option value='"+j+"'>"+j+"</option>");
					
					        }
				       		
			      	   });
		     	  },
		     error: function(){		    		
			    	 swal("請選擇日期!", "請選擇入住及退房日期!","warning");
					 $('#end_date').val("");
				}
		     })
	    })
	}
	
	
	function clearSelect(){
		$('.rtNumClean').empty();	//清資料
		$('.rtNumClean').append("<option value='-1'>請選擇</option>");
	}
  </script>
</body>

</html>
