<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*" %>

<% 

MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
request.setAttribute("memberVO", memberVO);

%>

    
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- 頁面標籤 -->
    <title>CA105G4-翔太山莊</title>
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
  </head>
  
  
  <style>
      /* Always set the map height explicitly to define the size of the div
     * element that contains the map. */
    #map {
    	height: 500px;
    	width: 100%;
    }
  </style>
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
          <li class="nav-item"><a href="indexCustom.html" class="nav-link">Home</a></li>
          <li class="nav-item"><a href="roomsC.html" class="nav-link">Rooms</a></li>
          <li class="nav-item"><a href="Stay&Help.html" class="nav-link">Stay and Help</a></li>
          <li class="nav-item"><a href="Coupon.html" class="nav-link">Coupon</a></li>
          <li class="nav-item active"><a href="Neighbourhood.html" class="nav-link">Neighbourhood</a></li>
          <li class="nav-item"><a href="MyAccount.html" class="nav-link">My Account</a></li>
          <li class="nav-item"><a href="FAQ.html" class="nav-link">FAQ</a></li>
          <c:choose>
         	<c:when test="${memberVO == null}">
            	<li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/front-end/Login.jsp">Login</a>
         	</c:when>
         	<c:otherwise>
          		<li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/front-end/MemLogout.do">Logout</a>
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
      <div class="block-30 item" style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/mountain village.jpg');  min-height: 500px; height: 30vh" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center">
            <div class="col-md-10">
            </div>
          </div>
        </div>
      </div>
      <div class="block-30 item" style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/workExchange.jpg');  min-height: 500px; height: 30vh" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center">
            <div class="col-md-10">
              <!-- <span class="subheading-sm">Welcome</span> -->
              <h2 class="heading"></h2>
            </div>
          </div>
        </div>
      </div>
      <div class="block-30 item" style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/EastScenery2.jpg');  min-height: 500px; height: 30vh" data-stellar-background-ratio="0.5">
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



	
  <!-- 頁籤 -->
  <div style="padding-bottom: 30px;">
  <div class="container">
      <ul class="page-menu" style="padding-bottom: 0px;">
        <li class="page-menu-on"><a class="menu-header" href="#" id="traffic">交通位置</a></li>
        <li><a class="menu-header" href="<%=request.getContextPath()%>/front-end/neighbourhoodFood.jsp">附近餐廳</a></li>
        <li><a class="menu-header" href="<%=request.getContextPath()%>/front-end/neighbourhoodScenery.jsp">鄰近景點</a></li>
      </ul>
      <div class="text-center">
        <h2>交通位置</h2>
      </div>
      <div id="map"></div>
   </div>
    </div>
    
    
    
    <!-- google map -->
    <script>
    var map;
    var centerMarker;
    var bra1 ;
    var bra2 ;
    var bra1M;
    var bra2M;
    var infoWindow;
    
      function initMap() { 
    	  //中心點位置
         centerMarker = {lat: 23.100204, lng: 121.204421}; 
		 //兩家分店位置
    	 bra1 = {lat:22.614089, lng:121.005167};
    	 bra2 = {lat:23.376220, lng:121.432036};
         map = new google.maps.Map(document.getElementById('map'), { 
         	center: centerMarker, 
         	zoom: 9 
         });
         infoWindow = new google.maps.InfoWindow();
         

        // 放置中心點的Marker
//         centerMarker = new google.maps.Marker({ 
//           map: map, 
//           position: centerMarker, 
//          }); 
        bra1M = new google.maps.Marker({ 
          map: map, 
          position: bra1,
<%--           icon:'<%=request.getContextPath()%>/front-end/imagesCustom/mapIcon/villa.png', --%>
          animation: google.maps.Animation.BOUNCE//載入特效
         }); 
        bra2M = new google.maps.Marker({ 
          map: map, 
          position: bra2,
//           icon:'',
          animation: google.maps.Animation.BOUNCE
         }); 
        

   	   bra1M.addListener('click', function() {
   	   		infoWindow.setContent('<h4>麻翔山莊</h4>');
   	    	infoWindow.open(map, bra1M);
   	   });
   	   bra2M.addListener('click', function() {
   	   		infoWindow.setContent('<h4>翔泰山莊</h4>');
   	    	infoWindow.open(map, bra2M);
        });
      }
    </script> 
    
        
     <div class="site-section" style="padding-top: 30px;background:#f1efed">   
       <div class="container">
         <div class="row">
         	<div>
         	  <h3 style="padding-bottom: 20px;">如何前往</h3>
		      <p>位於台北市中心的閑靜社區中，Folio大安坐擁鬧中取靜的絕佳位置。<br>
	       	    台北東區及信義區近在咫尺，往來桃園機場/松山機場、台北最新熱門去處、或是我們提供的私房景點，皆十分便捷順暢。<br>
		             本飯店未提供停車場服務，請利用大眾運輸工具或周邊停車場。不便之處敬請見諒。</p>
         	</div>
       	 </div>
       	 	<div>
	         	<a data-toggle="collapse" href="#cc1" aria-expanded="false" aria-controls="#cc1">
					<img src="<%=request.getContextPath()%>/front-end/imagesCustom/icon_car.png"><span>汽車</span>
				</a>
				<div class="collapse" id="cc1">
					<p>行駛國道一號於圓山交流道駛離國道一號，接建國高架道路後由信義路匣口下至建國南路一段，前行約100公尺後於信義路三段左轉並靠右側直行，過復興南路後於第一條巷子(信義路四段30巷)右轉，前行100公尺即達Folio大安，正門口於左手邊位置。
						行駛國道三號下安坑交流道往台北方向接環河路，直行順接水源快速道路，下基隆路出口接基隆路高架道路，於辛亥路出口後，左轉辛亥路三段並靠慢車道直行，於復興南路一段右轉，直行後於信義路四段右轉(捷運大安站)，並於右手邊第一條巷子(信義路四段30巷)右轉，前行100公尺即達Folio大安，正門口於左手邊位置。</p>
				</div>
			</div><br>
			<div>
	         	<a data-toggle="collapse" href="#cc2" aria-expanded="false" aria-controls="#cc2">
					<img src="<%=request.getContextPath()%>/front-end/imagesCustom/icon_bus.png"><span>公車</span>
				</a>
				<div class="collapse" id="cc2">
					<p>行駛國道一號於圓山交流道駛離國道一號，接建國高架道路後由信義路匣口下至建國南路一段，前行約100公尺後於信義路三段左轉並靠右側直行，過復興南路後於第一條巷子(信義路四段30巷)右轉，前行100公尺即達Folio大安，正門口於左手邊位置。
						行駛國道三號下安坑交流道往台北方向接環河路，直行順接水源快速道路，下基隆路出口接基隆路高架道路，於辛亥路出口後，左轉辛亥路三段並靠慢車道直行，於復興南路一段右轉，直行後於信義路四段右轉(捷運大安站)，並於右手邊第一條巷子(信義路四段30巷)右轉，前行100公尺即達Folio大安，正門口於左手邊位置。</p>
				</div>
			</div><br>
			<div>
	         	<a data-toggle="collapse" href="#cc3" aria-expanded="false" aria-controls="#cc3">
					<img src="<%=request.getContextPath()%>/front-end/imagesCustom/icon_parking.png"><span>停車資訊</span>
				</a>
				<div class="collapse" id="cc3">
					<p>行駛國道一號於圓山交流道駛離國道一號，接建國高架道路後由信義路匣口下至建國南路一段，前行約100公尺後於信義路三段左轉並靠右側直行，過復興南路後於第一條巷子(信義路四段30巷)右轉，前行100公尺即達Folio大安，正門口於左手邊位置。
						行駛國道三號下安坑交流道往台北方向接環河路，直行順接水源快速道路，下基隆路出口接基隆路高架道路，於辛亥路出口後，左轉辛亥路三段並靠慢車道直行，於復興南路一段右轉，直行後於信義路四段右轉(捷運大安站)，並於右手邊第一條巷子(信義路四段30巷)右轉，前行100公尺即達Folio大安，正門口於左手邊位置。</p>
				</div>
			</div><br>
       </div>   
     </div>  
        
        
        
        
        
        
        
        
        
        
        
        
        
        
  
  <!-- style="background:#f1efed !important;"灰背景 -->
  

































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
<!--   <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script> -->
<%--   <script src="<%=request.getContextPath()%>/front-end/js/google-map.js"></script> --%>
  <script src="<%=request.getContextPath()%>/front-end/js/main.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAb2lDof7yMn-TTXwt2hwVm4y92t1AqvyU&callback=initMap&libraries=places" async defer></script>
    
  </body>
</html>  