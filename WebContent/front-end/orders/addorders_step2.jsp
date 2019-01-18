<%@page import="java.util.*"%>
<%@page import="com.orderDetail.model.OrderDetailVO"%>
<%@page import="com.orders.model.OrdersService"%>
<%@page import="com.orders.model.OrdersVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
	
<%
	String getmemID = (String)request.getAttribute("memID");
System.out.println("jsp收到的memID"+getmemID);
	OrdersService odSvc = new OrdersService();
	String ordID = odSvc.findNewOrderID(getmemID);
	
	OrdersVO ordVO = odSvc.getOneOrders(ordID);
	pageContext.setAttribute("ordVO", ordVO);
	
	Set<OrderDetailVO> odSet = odSvc.getOrderDetailByOrders(ordID);
	OrderDetailVO odVO = odSet.iterator().next();
	pageContext.setAttribute("odVO", odVO);
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
					<li class="nav-item active"><a href="<%=request.getContextPath()%>/front-end/roomType/roomType.jsp" class="nav-link">Room Type</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/workExchange/listAllWE.jsp" class="nav-link">Stay and Help</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/coupon/coupon.jsp" class="nav-link">Coupon</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/member/myAccountMyPage.jsp" class="nav-link">My Account</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/question/frontChat.jsp" class="nav-link">F&Q</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/neighbourhood.jsp" class="nav-link">About Us</a></li>
					<c:choose>
						<c:when test="${memberVO == null}">
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/front-end/Login.jsp">Login</a>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/front-end/MemLogout.do">Logout</a>
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
				<h1>確認新增訂單！</h1>
				<hr>
				<div class="container-fluid">
					<div class="row">
						<div class="col-xs-12 col-sm-3">
						</div>
						<div class="col-xs-12 col-sm-6">
						
							<table class="table table-bordered table-striped table-hover">
								<caption></caption>
								<tr>
									<th>訂單編號：</th>
									<td>${ordVO.ordID}</td>
								</tr>
								<tr>
									<th>會員姓名：</th>
		<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
									<td>${memSvc.getOneMem(ordVO.memID).getMemName()}</td>
								</tr>
								<tr>
									<th>分店名稱：</th>
		<jsp:useBean id="braSvc" scope="page" class="com.branch.model.BranchService" />
									<td>${braSvc.getOneByID(ordVO.braID).getBraName()}</td>
								</tr>
								<tr>
									<th>訂房數量：</th>
									<td>${ordVO.numOfRoom}</td>
								</tr>
								<tr>
									<th>訂單種類：</th>
									<td>${ordTypeMap.get(ordVO.ordType)}</td>
								</tr>
								<tr>
									<th>入住時間：</th>
									<td>${odVO.checkIn}</td>
								</tr>
								<tr>
									<th>退房時間：</th>
									<td>${odVO.checkOut}</td>
								</tr>
								<tr>
									<th>付款方式：</th>
									<td>${paymentMap.get(ordVO.payment)}</td>
								</tr>
								<tr>
									<th>下訂時間：</th>
									<td>${ordVO.ordTime}</td>
								</tr>
							</table>
							<br>
							<div align="center">
								<table>
									<c:forEach var="rtIDandNum" items="${rtIDandNumMap.entrySet()}">
										<tr>
											<th>
												<img src="<%=request.getContextPath()%>/roomType/roomTypeImg.do?rtID=${rtIDandNum.getKey()}" class="img-fluid showrtpic" width="300px" padding-right: 15px;>
											</th>
		<jsp:useBean id="rtSvc" scope="page" class="com.roomType.model.RoomTypeService" />
											<td>${rtSvc.getOneRoomType(rtIDandNum.getKey()).rtName} : </td>
											<td>${rtIDandNum.getValue()} 間</td>
										</tr>
									</c:forEach>
									<tr>
										<th>訂單總金額：</th>
										<td>${ordVO.amount}</td>
									</tr>
									<tr>
										<th>訂金：</th>
										<td>${ordVO.bond}</td>
									</tr>
								</table>
							</div>
							<br>
							<div align="center">
								<button type="button" class="btn btn-info" id="confirmtoPay">確認付款</button>
							</div>
						</div>
						<div class="col-xs-12 col-sm-3">
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
	$(function(){
	    $('#confirmtoPay').on('click', function(){
	    	swal({
	            title: "訂單新增成功!", text: "感謝您的支持", type: "success", icon: "success",showCancelButton: true, confirmButtonText: "前往"
	          }).then(
	           function (result) {
	           if(result){
	        	   document.location.href='<%=request.getContextPath()%>/front-end/orders/myAccountorders.jsp';
	           }
	           });
	    });
	});

</script>
  
  </body>
</html>