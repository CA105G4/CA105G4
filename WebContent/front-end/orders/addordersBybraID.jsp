<%@page import="com.member.model.MemberVO"%>
<%@page import="java.util.*"%>
<%@page import="com.roomType.model.RoomTypeVO"%>
<%@page import="com.roomType.model.RoomTypeService"%>
<%@page import="com.orders.model.OrdersVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
	
<%	
	MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
	String chooseBraID = request.getParameter("braID");
	String chooseRtID = request.getParameter("rtID");
	String checkinDate = request.getParameter("checkinDate");
	
	pageContext.setAttribute("chooseBraID", chooseBraID);
	pageContext.setAttribute("chooseRtID", chooseRtID);
	pageContext.setAttribute("checkinDate", checkinDate);
	 
// 	OrdersVO ordVO = (OrdersVO) request.getAttribute("ordVO");
	 
	RoomTypeService rtSvc = new RoomTypeService();
	List<RoomTypeVO> rtlist = rtSvc.findRoomTypeByBraID(chooseBraID);
	pageContext.setAttribute("memberVO", memberVO);
	request.setAttribute("rtlist", rtlist);
%>
	
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- 頁面標籤 -->
    <title>翔太山莊</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Rubik:300,400,500" rel="stylesheet">
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
    
	<!-- datepicker-->
	<link href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" 
		rel="stylesheet" type="text/css" />
    
<style type="text/css">
   .list-group {
     
     margin:auto;
     float:left;
     padding-top:20px;
    }
    .lead {
     
     margin:auto;
     left:0;
     right:0;
     padding-top:10%;
    }
</style>

  </head>
  
<body>
	<!-- NavBar -->
	<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
		<div class="container">
			<a class="navbar-brand" href="<%=request.getContextPath()%>/front-end/indexSearch2.jsp">Xiangtai village</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#ftco-nav" aria-controls="ftco-nav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="oi oi-menu"></span> Menu
			</button>
			<!--NavBar 右半部-->
			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/indexSearch2.jsp" class="nav-link">Home</a></li>
					<li class="nav-item active"><a href="<%=request.getContextPath()%>/front-end/roomType/roomType.jsp" class="nav-link">Room</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/workExchange/listAllWE.jsp" class="nav-link">Stay&Help</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/coupon/coupon.jsp" class="nav-link">Coupon</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/member/myAccountMyPage.jsp" class="nav-link">My Account</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/question/frontChat.jsp" class="nav-link">Q&A</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/neighbourhood.jsp" class="nav-link">About Us</a></li>
					<c:choose>
						<c:when test="${memberVO == null}">
							<li class="nav-item">
								<a class="nav-link" href="<%=request.getContextPath()%>/front-end/Login.jsp">Login</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item">
								<a class="nav-link" href="<%=request.getContextPath()%>/front-end/MemLogout.do">Logout&nbsp;&nbsp;
								<img src="<%=request.getContextPath()%>/member/memImg.do?memID=${memberVO.memID}" style="border-radius: 50%; width:30px; height: 30px"></a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
	<!-- END nav -->
  



<!-- 廣告瀏覽區 -->
  <div class="block-31"  style="position: relative">
    <div class="owl-carousel loop-block-31 ">
      <div class="block-30 item" style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/workExchangebanner.jpg');; min-height: 150px;height: 30vh;" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center">
            <div class="col-md-10">
            </div>
          </div>
        </div>
      </div>
      <div class="block-30 item" style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/banner2.jpg');; min-height: 150px;height: 30vh;" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center">
            <div class="col-md-10">
              <!-- <span class="subheading-sm">Welcome</span> -->
              <h2 class="heading"></h2>
            </div>
          </div>
        </div>
      </div>
      <div class="block-30 item" style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/EastScenerybanner.jpg');; min-height: 150px;height: 30vh;" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center">
            <div class="col-md-10">
              <!-- <span class="subheading-sm">Welcome</span> -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>



  <div class="container">
    <!--這邊開始自由發揮-->

				<h1>${memberVO.memName} 您好，歡迎預約</h1>
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
										<th></th>
										<td>
											<img id="image" src="<%=request.getContextPath()%>/member/memImg.do?memID=${memberVO.memID}" style="border-radius: 50%; width:400px;"></img>
										</td>
									</tr>
									<tr>
										<th>會員名稱:</th>
								<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
										<td>${memSvc.getOneMem(memberVO.getMemID()).getMemName()}
											<input type='hidden' name='memID' value='${memberVO.memID}'>	  
										</td>
									</tr>
									<jsp:useBean id="brSvc" scope="page" class="com.branch.model.BranchService" />
									<tr>
										<th>分店名稱:</th>
										<td>
											<select size="1" name="braID" id="selectBranch" class="custom-select">
												<option value="-1">請選擇</option>
												<c:forEach var="brVO" items="${brSvc.all}">
													<option value="${brVO.braID}" ${ (brVO.braID==chooseBraID) ? "selected='true'" : "" }>${brVO.braName}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<th>訂單種類:</th>
										<td><input type='hidden' name='ordType' value='0'>
											線上訂單</td>
									</tr>
									<tr>
										<th>入住人數:</th>
										<td><input type="TEXT" name="numOfGuest" size="45" class="form-control"
											 value="${(ordVO==null)? "2" : ordVO.getNumOfGuest()}" /></td>
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
										<td><input type="text" name="checkIn" id="start_date" size="45" class="form-control" 
										value="${(checkinDate!=null)? checkinDate : '' }"/>
										
										</td>
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
									<% int count=1; %>
									<c:forEach var="rtVO" items="${rtlist}">
										<tr>
					        				<td>				        
					        					<input class='form-check-input' type='checkbox' name='rtID' value='${rtVO.rtID}' id='defaultCheck<%= count %>' ${(rtVO.rtID == chooseRtID) ? 'checked' : ''}>
					       					</td>
					        				<td>
					        					<label class='form-check-label' for='defaultCheck<%= count %>'>
					        						<img src='<%=request.getContextPath()%>/roomType/roomTypeImg.do?rtID=${rtVO.rtID}' class='img-fluid' style='border-radius: 10%; width:400px;'>
					        					</label>
					        				</td><% count++; %>
					        				<td>		        
					        					${rtVO.rtName}		 
					       					</td>
					        				<td>			        
					        					<select size='1' name='${rtVO.rtID}' class='custom-select rtNumClean' id='${rtVO.rtID}'>
					        						<option value='-1'>請選擇</option>
					        					</select>
					       					</td>
					        				<td>		        
												<input type='hidden' name='special' value='0'>
					       					</td>
					        			</tr>
					        		</c:forEach>
								</table>
								<br>
									<input type="hidden" name="action" value="insert">
									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
									<input type="submit" value="送出新增" class="btn btn-info" >
									<button type="button" class="btn btn-info">
										<a href='<%=request.getContextPath()%>/back-end/orders/select_orders_page.jsp' style="color: #AAAAAA">返回</a>
									</button>
								</FORM>	
														
							</div>
						</div>
						<div class="col-xs-12 col-sm-1">
							
						</div>
						   				

          			</div>
											
				</div>

    <!--這邊結束自由發揮-->
  </div>
<br>

    <!-- Footer尾巴 -->
    <footer class="footer">
      <div class="container">
        <div class="row">
          <div class="col-xs-12 col-md-4"><img src="<%=request.getContextPath()%>/front-end/imagesCustom/logoC.jpg" width="250px" height="200px">
          </div>
          <div class="col-xs-12 col-md-4">
            <!-- style.css Line7633 -->
              <h3 class="heading-section">About Us</h3>
                <p class="mb-5">麻翔山莊創立於1923年，於日治時期台東地區第一家現代化旅館，超過90年以上的經營，成為台灣最具指標性的山莊，分店翔泰山莊於2018年，符合環境友善，同時會及最新科技的六星級旅館. </p>
          </div>
          <div class="col-xs-12 col-md-4">
            <h3 class="heading-section">Contact Info</h3>
              <div class="text-left">
                <span class="icon icon-map-marker"></span><span class="text">&nbsp;&nbsp;台東縣太麻里福翔村高達斯路
                </span><br>
                <span class="icon icon-map-marker"></span><span class="text">&nbsp;&nbsp;花蓮縣長濱鄉達達鄉
                </span><br>
                <a href="#"><span class="icon icon-phone"></span><span class="text">&nbsp;&nbsp;03 8538 5385</span></a><br>
                <a href="#"><span class="icon icon-envelope"></span><span class="text">&nbsp;&nbsp;CA105G4@gmail.com</span></a><br>
                <span class="icon icon-clock-o"></span><span class="text">&nbsp;&nbsp;Monday &mdash; Friday 8:00am - 5:00pm</span><br>
              </div>
          </div>
       </div>
       <div class="row pt-2">
          <div class="col-md-12 text-left">
            &copy;<script>document.write(new Date().getFullYear());</script>&nbsp;XIANGTAI INTERNATINAL, INC All RIGHTS RESERVED.
          </div>
       </div>
    </div>
</footer>


  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


  <script src="<%=request.getContextPath()%>/front-end/js/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/jquery-migrate-3.0.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/popper.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/jquery.easing.1.3.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/jquery.waypoints.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/jquery.stellar.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/owl.carousel.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/jquery.magnific-popup.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/bootstrap-datepicker.js"></script>
  
  <script src="<%=request.getContextPath()%>/front-end/js/aos.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/jquery.animateNumber.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/google-map.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/main.js"></script>
  
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
			 
			 changeRoomTypebyBranch();
			 
			 changeRoomTypeNumbers();

		});
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
					        								 "<img src='<%=request.getContextPath()%>/roomType/roomTypeImg.do?rtID=" + item.rtID + "' class='img-fluid showrtpic' style='border-radius: 10%; width:400px;'>"+
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