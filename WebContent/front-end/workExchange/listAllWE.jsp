<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.workExchange.model.*"%>
<%@ page import="java.util.*"%>

<%
	WorkExchangeService weSvc = new WorkExchangeService();
	List<WorkExchangeVO> list = weSvc.getAllEmpty();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html lang="en">
<head>
	<!-- 頁面標籤 -->
	<title>Stay and Help</title>
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
	
	<style>
		.pic{overflow:hidden;}
		.pic img{transform:scale(1,1);transition: all 1s ease-out;}
		.pic img:hover{transform:scale(1.2,1.2);}
		.head{text-shadow:2px 3px 5px #4d4d4d;font-size:40px;}
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
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/roomType/roomType.jsp" class="nav-link">Room Type</a></li>
					<li class="nav-item active"><a href="<%=request.getContextPath()%>/front-end/workExchange/listAllWE.jsp" class="nav-link">Stay and Help</a></li>
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
	<div class="block-31" style="position: relative;">
		<div class="block-30 item"
			style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/workExchange.jpg'); min-height: 400px; height: 30vh"
			data-stellar-background-ratio="0.5">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-md-10">
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- 打工需求 -->
	<div class="container">
		<div class="site-section bg-light main-section">
			<div class="container">
				<div class="row mb-5">
					<!--ln8670-->
					<div class="col-xs-12 col-sm-6 block-3">
						<img src="<%=request.getContextPath()%>/front-end/imagesCustom/Ryze.jpg" style="width: 100%">
					</div>
					<div class="col-xs-12 col-sm-6 head">
					<p>想為你的人生增添色彩嗎?</p>
					<p>想享受免費的房間設施嗎?</p>
					<p>快看你是否擁有以下技能?</p>
					</div>
				</div>
			</div>

			<jsp:useBean id="rtSvc" class="com.roomType.model.RoomTypeService" />
			<div class="tab-content" id="pills-tabContent">
				<div class="tab-pane fade show active" id="pills-home"
					role="tabpanel" aria-labelledby="pills-home-tab">
					<div class="row">
						<div class="col-md-12 block-13">
							<div class="nonloop-block-13 owl-carousel">
								<c:forEach var="workExchangeVO" items="${list}">
									<div class="item">
										<div class="block-34">
										<div class="image pic">
	        							<label for="${workExchangeVO.weID}"><img src="<%=request.getContextPath()%>/workExchange/workExchangeImg.do?weID=${workExchangeVO.weID}" class="btn" height="300" width="500" alt="Image placeholder"></label>
	      									</div>
											<div class="text">
												<h1 class="heading">${rtSvc.getOneRoomType(workExchangeVO.rtID).rtName}</h1>
											 <ul class="specs">
	         									 <li><strong>需求:</strong> ${workExchangeVO.weName}</li>
										          <li><strong>開始日期:</strong> ${workExchangeVO.weStart}</li>
										          <li><strong>結束日期:</strong> ${workExchangeVO.weEnd}</li>
									        </ul>
												<div class="price">
													<sup>$</sup><span class="number">Free</span><sub>/per night</sub>
												</div>
												<form action="<%=request.getContextPath()%>/workExchange/workExchange.do">
												<p>
													<input type="submit" class="btn btn-primary py-3 px-5" value="Read More">
													<input type="hidden" name="weID" value="${workExchangeVO.weID}">
													<input type="hidden" name="action" value="displayDetail_FromFrontEnd">
												</p>
												</form>
												<form action="<%=request.getContextPath()%>/workExchange/workExchange.do">
													<input type="submit" name="weID" id="${workExchangeVO.weID}" value="${workExchangeVO.weID}" style="display:none">
													<input type="hidden" name="action" value="displayDetail_FromFrontEnd">
												</form>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>
</div>


	<!-- Footer尾巴 -->
	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-md-4">
					<img
						src="<%=request.getContextPath()%>/front-end/imagesCustom/logoC.jpg"
						width="250px" height="200px">
				</div>
				<div class="col-xs-12 col-md-4">
					<!-- style.css Line7633 -->
					<h3 class="heading-section">About Us</h3>
					<p class="mb-5">麻翔山莊創立於1923年，於日治時期台東地區第一家現代化旅館，超過90年以上的經營，成為台灣最具指標性的山莊，分店翔泰山莊於2018年，符合環境友善，同時會及最新科技的六星級旅館.
					</p>
				</div>
				<div class="col-xs-12 col-md-4">
					<h3 class="heading-section">Contact Info</h3>
					<div class="text-left">
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
					<script>
						document.write(new Date().getFullYear());
					</script>
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

</body>
</html>