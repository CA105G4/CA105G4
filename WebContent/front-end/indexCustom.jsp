<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	ArticleService articleService = new ArticleService();
	List<ArticleVO> list = articleService.getLastestThree();
	pageContext.setAttribute("list",list);
%>



<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- 頁面標籤 -->
    <title>CA105G4-翔太山莊</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Rubik:300,400,500" rel="stylesheet">
    <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/aos.css">
    <link rel="stylesheet" href="css/ionicons.min.css">
    <link rel="stylesheet" href="css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="css/jquery.timepicker.css">
    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/icomoon.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.0.0-beta.2.4/assets/owl.carousel.min.css"></link>  -->
    <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.0.0-beta.2.4/assets/owl.theme.default.min.css"></link> -->
    <style type="text/css">
            .thumbnail iframe {
                 width: 1440px;
                 height: 900px;
        }
        .thumbnail {
               position: relative; 
              -ms-zoom: 0.25;
              -moz-transform: scale(0.25);
              -moz-transform-origin: 0 0;
              -o-transform: scale(0.25);
              -o-transform-origin: 0 0;
              -webkit-transform: scale(0.25);
              -webkit-transform-origin: 0 0;
        }
        .thumbnail:after {
              content: "";
              display: block;
              position: absolute;
              top: 0;
              left: 0;
              right: 0;
              bottom: 0;
        }
        </style>
  </head>
  <body>
  



    <!-- NavBar -->
  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
      <a class="navbar-brand" href="indexCustom.html">Xiangtai village</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="oi oi-menu"></span> Menu
      </button>
      <!--NavBar 右半部-->
      <div class="collapse navbar-collapse" id="ftco-nav">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active"><a href="indexCustom.html" class="nav-link">Home</a></li>
          <li class="nav-item"><a href="roomsC.html" class="nav-link">Rooms</a></li>
          <li class="nav-item"><a href="Stay&Help.html" class="nav-link">Stay and Help</a></li>
          <li class="nav-item"><a href="Coupon.html" class="nav-link">Coupon</a></li>
          <li class="nav-item"><a href="Neighbourhood.html" class="nav-link">Neighbourhood</a></li>
          <li class="nav-item"><a href="MyAccount.html" class="nav-link">My Account</a></li>
          <li class="nav-item"><a href="FAQ.html" class="nav-link">FAQ</a></li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- END nav -->
  



<!-- 廣告瀏覽區 -->
  <div class="block-31" style="position: relative;">
    <div class="owl-carousel loop-block-31 ">
      <div class="block-30 item" style="background-image: url('imagesCustom/mountain village.jpg'); min-height: 600px; height: 80vh" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center">
            <div class="col-md-10">
              <span class="subheading-sm">Welcome</span>
              <h2 class="heading">Enjoy a Luxury Experience</h2>
            </div>
          </div>
        </div>
      </div>
      <div class="block-30 item" style="background-image: url('imagesCustom/workExchange.jpg'); min-height: 600px; height: 80vh " data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center">
            <div class="col-md-10">
              <!-- <span class="subheading-sm">Welcome</span> -->
              <h2 class="heading"></h2>
            </div>
          </div>
        </div>
      </div>
      <div class="block-30 item" style="background-image: url('imagesCustom/EastScenery2.jpg');  min-height: 600px; height: 80vh" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center">
            <div class="col-md-10">
              <!-- <span class="subheading-sm">Welcome</span> -->
              <h2 class="heading">Simple Life</h2>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>






<!-- 快速查房  style.css Line8464 -->
  <div class="container">
      <div class="row mb-5">
        <div class="col-md-12">
          <div class="block-32">
            <form action="#">
              <div class="row">
                <div class="col-md-6 mb-3 mb-lg-0 col-lg-3">
                  <label for="checkin">Check In</label>
                  <div class="field-icon-wrap">
                    <div class="icon"><span class="icon-calendar"></span></div>
                    <input type="text" id="checkin_date" class="form-control">
                  </div>
                </div>
                <div class="col-md-6 mb-3 mb-lg-0 col-lg-3">
                  <label for="checkin">Check Out</label>
                  <div class="field-icon-wrap">
                    <div class="icon"><span class="icon-calendar"></span></div>
                    <input type="text" id="checkout_date" class="form-control">
                  </div>
                </div>
                <div class="col-md-6 mb-3 mb-md-0 col-lg-3">
                  <div class="row">
                    <div class="col-md-6 mb-3 mb-md-0">
                      <label for="checkin">Adults</label>
                      <div class="field-icon-wrap">
                        <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                        <select name="" id="" class="form-control">
                          <option value="">1</option>
                          <option value="">2</option>
                          <option value="">3</option>
                          <option value="">4+</option>
                        </select>
                      </div>
                    </div>
                    <div class="col-md-6 mb-3 mb-md-0">
                      <label for="checkin">Branch</label>
                      <div class="field-icon-wrap">
                        <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                        <select name="" id="" class="form-control">
                          <option value="">1</option>
                          <option value="">2</option>
                        </select>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-6 col-lg-3 align-self-end">
                  <button class="btn btn-primary btn-block icon-search">&nbsp;Search</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
      








      <!-- 山莊設施 -->
      <div class="row site-section">
        <div class="col-md-12">
          <div class="row mb-5">
            <div class="col-md-7 section-heading">
              <span class="subheading-sm">Services</span>
              <h2 class="heading">Facilities &amp; Services</h2>
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-4">
          <div class="media block-6">
            <div class="icon"><span class="flaticon-double-bed"></span></div>
            <div class="media-body">
              <h3 class="heading">Luxury Rooms</h3>
              <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
            </div>
          </div>      
        </div>
        <div class="col-md-6 col-lg-4">
          <div class="media block-6">
            <div class="icon"><span class="flaticon-wifi"></span></div>
            <div class="media-body">
              <h3 class="heading">Fast &amp; Free Wifi</h3>
              <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
            </div>
          </div>
        </div>
        <div class="col-md-6 col-lg-4">
          <div class="media block-6">
            <div class="icon"><span class="flaticon-customer-service"></span></div>
            <div class="media-body">
              <h3 class="heading">Call Us 24/7</h3>
              <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
            </div>
          </div>
        </div>

        <div class="col-md-6 col-lg-4">
          <div class="media block-6">
            <div class="icon"><span class="flaticon-taxi"></span></div>
            <div class="media-body">
              <h3 class="heading">Travel Accomodation</h3>
              <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
            </div>
          </div>      
        </div>
        <div class="col-md-6 col-lg-4">
          <div class="media block-6">
            <div class="icon"><span class="flaticon-credit-card"></span></div>
            <div class="media-body">
              <h3 class="heading">Accepts Credit Card</h3>
              <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
            </div>
          </div>
        </div>
<!--         <div class="col-md-6 col-lg-4">
          <div class="media block-6">
            <div class="icon"><span class="flaticon-dinner"></span></div>
            <div class="media-body">
              <h3 class="heading">Restaurant</h3>
              <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
            </div>
          </div>
        </div> -->

      </div>
    </div>
         





<jsp:useBean id="memberService" scope="page" class="com.member.model.MemberService" />
    <!-- 旅客回饋 -->
<!--   <div class="owl-carousel"> -->
    <div class="site-section bg-light">
      <div class="container">
        <div class="row mb-5">
          <div class="col-md-7 section-heading">
            <span class="subheading-sm">Reviews</span>
            <h2 class="heading">Guest Reviews</h2>
          </div>
        </div>
        <div class="row">
        	<c:forEach var="articleVO" items="${list}">
			<div class="col-md-6 col-lg-4">

	            <div class="block-33">
	              <div class="vcard d-flex mb-3">
	                <div class="image align-self-center"><img src="imagesCustom/Sandy.jpg" alt="Person here"></div>
	                <div class="name-text align-self-center">
	                  <h2 class="heading">${memberService.getOneMem(articleVO.memid).memAcc}</h2>
	                </div>
	              </div>
          
	              <div class="thumbnail" style="height: 300px">
	                <a href="<%=request.getContextPath()%>/front-end/article/viewArticle.jsp?artid=${articleVO.artid}&memid=${articleVO.memid}">
	                    <div style="position:absolute;height:1440px;width:1440px;"></div>
<%-- 	                     <iframe id="iFrame1" scrolling="no" frameborder="0" height="100%" width="100%" src="<%=request.getContextPath()%>/article/oneArticle?artid=${articleVO.artid}" onload="resizeIframe(this)"></iframe> --%>
						<iframe src="<%=request.getContextPath()%>/article/oneArticle?artid=${articleVO.artid}" frameborder="0"></iframe>
	                </a>
	              </div>
			   </div>
				
           </div>
       	   </c:forEach>
        </div>
      </div>
    </div> 



    <!-- Footer尾巴 -->
    <footer class="footer">
      <div class="container">
        <div class="row">
          <div class="col-xs-12 col-md-4"><img src="imagesCustom/logoC.jpg" width="250px" height="200px">
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


  <script src="js/jquery.min.js"></script>
  <script src="js/jquery-migrate-3.0.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/jquery.easing.1.3.js"></script>
  <script src="js/jquery.waypoints.min.js"></script>
  <script src="js/jquery.stellar.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/bootstrap-datepicker.js"></script>
  
  <script src="js/aos.js"></script>
  <script src="js/jquery.animateNumber.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="js/google-map.js"></script>
  <script src="js/main.js"></script>
  
<script type="text/javascript">
// 		$(document).ready(function(){
// 				$('#forecast_embed').load(function() {
// 				    $('#frame').height($('#frame').get(0).contentWindow.document.body.offsetHeight + 'px');
// 				});
// 		});
// 		function resizeIframe(obj) {
//     			obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
//  		 }
		function resizeIFrameToFitContent( iFrame ) {

		    iFrame.width  = iFrame.contentWindow.document.body.scrollWidth;
		    iFrame.height = iFrame.contentWindow.document.body.scrollHeight;
		}

		window.addEventListener('DOMContentLoaded', function(e) {
		
		    var iFrame = document.getElementById( 'iFrame1' );
		    resizeIFrameToFitContent( iFrame );
		
		    // or, to resize all iframes:
		    var iframes = document.querySelectorAll("iframe");
		    for( var i = 0; i < iframes.length; i++) {
		        resizeIFrameToFitContent( iframes[i] );
		    }
		} );
</script> 

  </body>
</html>