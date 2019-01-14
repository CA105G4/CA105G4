<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%@page import="com.coupon.model.*"%>

<%
    CouponService cpnSvc = new CouponService();
    List<CouponVO>cpnList = cpnSvc.getAll();
    pageContext.setAttribute("cpnList",cpnList);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- 頁面標籤 -->
	<title>Coupon</title>
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
	<!--      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" /> -->

<style>
.couponmargin {
	margin: 25px;
}
</style>
</head>


<!-- </script> -->
<body>

    <!-- NavBar -->
	<nav
		class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
		id="ftco-navbar">
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
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/roomType/roomType.jsp" class="nav-link">Room Type</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/workExchange/listAllWE.jsp" class="nav-link">Stay and Help</a></li>
					<li class="nav-item active"><a href="<%=request.getContextPath()%>/front-end/coupon/coupon.jsp" class="nav-link">Coupon</a></li>
<!-- 					<li class="nav-item"><a href="Neighbourhood.html" class="nav-link">Neighbourhood</a></li> -->
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/member/myAccountMyPage.jsp" class="nav-link">My Account</a></li>
					<li class="nav-item"><a href="FAQ.html" class="nav-link">FAQ</a></li>
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
	<div class="block-31" style="position: relative;">
		<div class="owl-carousel loop-block-31 ">
			<div class="block-30 item"
				style="background-image: url('imagesCustom/mountain village.jpg'); min-height: 500px; height: 30vh"
				data-stellar-background-ratio="0.5">
				<div class="container">
					<div class="row align-items-center">
						<div class="col-md-10"></div>
					</div>
				</div>
			</div>
			<div class="block-30 item"
				style="background-image: url('imagesCustom/workExchange.jpg'); min-height: 500px; height: 30vh"
				data-stellar-background-ratio="0.5">
				<div class="container">
					<div class="row align-items-center">
						<div class="col-md-10">
							<!-- <span class="subheading-sm">Welcome</span> -->
							<h2 class="heading"></h2>
						</div>
					</div>
				</div>
			</div>
			<div class="block-30 item"
				style="background-image: url('imagesCustom/EastScenery2.jpg'); min-height: 500px; height: 30vh"
				data-stellar-background-ratio="0.5">
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

	<!-- 主畫面優惠券照片 -->
	<div class="container">
		<div class="site-section bg-light main-section">
			<h2>&nbsp;All For Free! Let's join us !!!</h2>


			<c:forEach var="cpnVO" items="${cpnList}" varStatus="status"
				begin="0" end="2">


				<div class="container couponmargin">
					<div class="row">

						<c:set var="index" value="${status.index}" />

						<%
							int count = (Integer) pageContext.getAttribute("index");
							if (cpnList.get(count).getquantity() != 0) {
								pageContext.setAttribute("cpn", new Integer(1));
							} else {
								pageContext.setAttribute("cpn", new Integer(0));
							}
						%>


						<c:choose>
							<c:when test="${cpn==1}">

								<div class="col-xs-12 col-sm-6">
									<img
										src="<%=request.getContextPath()%>/coupon/cpnImg.do?cpnID=${cpnVO.cpnID}"
										width="500px" id="image">
								</div>
							</c:when>
							<c:otherwise>
								<div class="col-xs-12 col-sm-6">
									<img
										src="<%=request.getContextPath()%>/front-end/imagesCustom/noCoupon.png"
										width="500px">
								</div>
							</c:otherwise>
						</c:choose>


						<div class="col-xs-12 col-sm-6">
							<br>
							<div class="price">
								<h3>
									<sup>$</sup><span class="number sale">${cpnVO.discount}</span>
								</h3>
							</div>
							<div id="quantity2">
								<Strong>Coupon Number:</Strong>
								<p id="${cpnVO.cpnID}">${cpnVO.quantity}</p>
							</div>
							<br>
							<button type="submit" class="btn-info" value="${cpnVO.cpnID }">Get Coupon!</button>
						</div>
					</div>
				</div>

			</c:forEach>


		</div>
	</div>

	<!-- 主畫面優惠券照片結尾 -->


	<!-- Footer尾巴 -->
	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-md-4">
					<img src="imagesCustom/logoC.jpg" width="250px" height="200px">
				</div>
				<div class="col-xs-12 col-md-4">
					<!-- style.css Line7633 -->
					<h3 class="heading-section">About Us</h3>
					<p class="mb-5">麻翔山莊創立於1923年，於日治時期台東地區第一家現代化旅館，超過90年以上的經營，成為台灣最具指標性的山莊，分店翔泰山莊於2018年，符合環境友善，同時匯集最新科技的六星級旅館.
					</p>
				</div>
				<div class="col-xs-12 col-md-4">
					<h3 class="heading-section">Contact Info</h3>
					<span class="icon icon-map-marker"></span><span class="text">&nbsp;&nbsp;台東縣太麻里福翔村高達斯路
					</span><br> <span class="icon icon-map-marker"></span><span
						class="text">&nbsp;&nbsp;花蓮縣長濱鄉達達鄉 </span><br> <a href="#"><span
						class="icon icon-phone"></span><span class="text">&nbsp;&nbsp;03
							8538 5385</span></a><br> <a href="#"><span
						class="icon icon-envelope"></span><span class="text">&nbsp;&nbsp;CA105G4@gmail.com</span></a><br>
					<span class="icon icon-clock-o"></span><span class="text">&nbsp;&nbsp;Monday
						&mdash; Friday 8:00am - 5:00pm</span><br>
				</div>
			</div>
		</div>
		<div class="row pt-2">
			<div class="col-md-12 text-left">
				&copy;
				<script>document.write(new Date().getFullYear());</script>
				&nbsp;XIANGTAI INTERNATINAL, INC All RIGHTS RESERVED.
			</div>
		</div>
		</div>
	</footer>


	<!-- loader -->
	<div id="ftco-loader" class="show fullscreen">
		<svg class="circular" width="48px" height="48px">
			<circle class="path-bg" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke="#eeeeee" />
			<circle class="path" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg>
	</div>


	<script src="<%=request.getContextPath()%>/front-end/js/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/js/jquery-migrate-3.0.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/js/jquery.easing.1.3.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/js/jquery.waypoints.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/js/jquery.stellar.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/js/owl.carousel.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/js/jquery.magnific-popup.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/js/bootstrap-datepicker.js"></script>

	<script src="<%=request.getContextPath()%>/front-end/js/aos.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/js/jquery.animateNumber.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/google-map.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/main.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.all.js"></script>
	<!--     <script src="https://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script> -->

	<script type="text/javascript">
	$(document).ready(function(){
		console.log("123");
		var d;
		 $('button').click(function(){
			console.log("456");
			 $.ajax({
				 type: "GET",
				 url: "<%=request.getContextPath()%>/coupon/cpn.do",
				 data: creatQueryString($(this).val()),
				 dataType: "json",
				 success: function (data){
 						var id = "#" + data.cpnID;
 						
					console.log(data.cpnVO);
					console.log(data.cpnID);
 						$(id).text(data.cpnVO);
			     },
	             error: function(){alert("AJAX-grade發生錯誤囉!")}
	         })
		 })
	
	})
	
		function creatQueryString(cpnID){
		var queryString= {"action":"get_coupon", "cpnID":cpnID};
		console.log(queryString);
		return queryString;
	}

</script>

	<script type="text/javascript">


	 $(function () {
            $("button").click(function () {
                //alert範例
                swal({
                		position: 'top-end',
                		type: 'success',
                		 title: 'You got a perfect coupon!!!',
                		 showConfirmButton: false,
                		 timer: 1500
                }        
                );

            });
        });
	

</script>
</body>
</html>