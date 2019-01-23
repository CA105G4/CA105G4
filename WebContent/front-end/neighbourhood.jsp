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
  </head>
  
  
  <style>
      /* Always set the map height explicitly to define the size of the div
     * element that contains the map. */
    #map {
    	height: 500px;
    	width: 100%;
    }
	.all-bigtitle {
	    padding: 34px 15px 25px 15px;
	    background-color: #FFF;
	    text-align: center;
	}
	.all-bigtitle .bigtitle-tw {
	    margin-bottom: 5px;
	    padding: 0 45px;
	    display: inline-block;
	    position: relative;
	}
	.clean {
	    clear: both;
	    line-height: 0;
	    height: 0;
	}
	.all-bigtitle .bigtitle-tw:before {
    	left: 0;
	}
	.all-bigtitle .bigtitle-tw:before, .all-bigtitle .bigtitle-tw:after {
	    content: "";
	    width: 32px;
	    height: 1px;
	    position: absolute;
	    top: 50%;
	    background-color: #231815;
	}
  </style>
  
  
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
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/roomType/roomType.jsp" class="nav-link">Room</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/workExchange/listAllWE.jsp" class="nav-link">Stay&Help</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/coupon/coupon.jsp" class="nav-link">Coupon</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/member/myAccountMyPage.jsp" class="nav-link">My Account</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/question/frontChat.jsp" class="nav-link">Q&A</a></li>
					<li class="nav-item active"><a href="<%=request.getContextPath()%>/front-end/neighbourhood.jsp" class="nav-link">About Us</a></li>
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
        <li><a class="menu-header" href="<%=request.getContextPath()%>/front-end/neighbourhoodScenery.jsp">熱門景點</a></li>
      </ul>
      <div class="all-bigtitle">
		<p class="bigtitle-tw"><img src="<%=request.getContextPath()%>/front-end/imagesCustom/addr.jpg" alt="交通位置"></p>
    	<div class="clean"></div>
    	<p class="bigtitle-img"><img src="<%=request.getContextPath()%>/front-end/imagesCustom/Hito.jpg" alt="交通位置"></p>
      </div>
      <div id="map"></div>
      <jsp:useBean id ="braSvc" class="com.branch.model.BranchService"/>
   </div>
    </div>
    
    
    
<!-- google map -->
    <script>
    var map;
    var centerPosition;
    var bra1 ;
    var bra2 ;
    var bra1M;
    var bra2M;
    var infoWindow;
    
      function initMap() { 
    	  //中心點位置
         centerPosition = {lat: 23.432260, lng: 121.013020};
		 //兩家分店位置
// 		 b1lat = ${braSvc.getOneByID("B01").getBraLat()}
// 		 b1lng = ${braSvc.getOneByID("B01").getBraLng()}
    	 bra1 = {lat:${braSvc.getOneByID("B01").getBraLat()}, lng:${braSvc.getOneByID("B01").getBraLng()}};
    	 bra2 = {lat:${braSvc.getOneByID("B02").getBraLat()}, lng:${braSvc.getOneByID("B02").getBraLng()}};
         map = new google.maps.Map(document.getElementById('map'), { 
         	center: centerPosition, 
         	zoom: 8 
         });
         infoWindow = new google.maps.InfoWindow();
         

        // 放置中心點的Marker
//         centerPosition = new google.maps.Marker({ 
//           map: map, 
//           position: centerPosition, 
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
          animation: google.maps.Animation.BOUNCE
         }); 
        if(${braSvc.getAll().size()} >= 3){
	       	bra3M = new google.maps.Marker({ 
	               map: map, 
	               position: {lat:22.654914, lng:120.304696},
	               animation: google.maps.Animation.DROP,
	               label: 'New'
	              }); 
   	   		bra3M.addListener('click', function() {
   	   		infoWindow.setContent('<h4>大翔山莊</h4>');
   	    	infoWindow.open(map, bra3M);
   	   		});
        }

   	   bra1M.addListener('click', function() {
   	   		infoWindow.setContent('<h4>福翔山莊</h4>');
   	    	infoWindow.open(map, bra1M);
   	   });
   	   bra2M.addListener('click', function() {
   	   		infoWindow.setContent('<h4>麻翔山莊</h4>');
   	    	infoWindow.open(map, bra2M);
        });
      }
    </script> 
    
        
     <div class="site-section" style="padding-top: 30px;background:#f1efed">   
       <div class="container">
         <div class="row">
         	<div>
         	  <h3 style="padding-bottom: 20px;">如何前往</h3>
		      <p>福翔山莊位於台東市太麻里鄉，第二間分店麻翔山莊則是位於花蓮車站，坐擁鬧中取靜的絕佳位置。<br>
	       	    兩間分店都有個共同點，火車站近在咫尺，往來台東市區、花蓮市區、或是我們所提供的私房景點，皆十分便捷順暢。<br>
		             本飯店未提供停車場服務，請利用大眾運輸工具或周邊停車場。不便之處敬請見諒。</p>
         	</div>
       	 </div>
       	 	<div>
	         	<a data-toggle="collapse" href="#cc1" aria-expanded="false" aria-controls="#cc1">
					<img src="<%=request.getContextPath()%>/front-end/imagesCustom/icon_car.png"><span>汽車</span>
				</a>
				<div class="collapse" id="cc1">
					<p>福翔:從平鎮區出發行駛國道一號，沿國道一號和國道3號前往南州鄉。從國道3號的 424-南州 號出口下交流道，沿屏鵝公路/縱貫公路/台1線和南迴公路前往目的地太麻里鄉，到達金崙站後，再從南迴公路接到富山產業道路，沿途經過富之山溫泉民宿後，就可以看到我們福翔山莊。<br><br>
					麻翔:走國道一號，沿國道一號和國道5號前往蘇澳的馬賽路/宜42鄉道，沿台9線和蘇花公路前往花蓮市的花19鄉道，即可看到山莊緊鄰美崙山生態展示館。</p>
				</div>
			</div><br>
			<div>
	         	<a data-toggle="collapse" href="#cc2" aria-expanded="false" aria-controls="#cc2">
					<img src="<%=request.getContextPath()%>/front-end/imagesCustom/icon_bus.png"><span>公車</span>
				</a>
				<div class="collapse" id="cc2">
					<p>福翔:先步行到公車站 ，搭乘公車132，到達中壢火車站後，搭乘自強號 ，到達板橋車站，由於福翔過於偏僻，這邊建議搭乘高鐵到新左營站，山莊會提供補助，最後再從新左營站搭乘火車到達金崙車站，再步行14分鐘途經富山產業道路即可到達。<br><br>
					麻翔:先步行到公車站 ，搭乘公車132，到達中壢火車站後，搭乘自強號 ，經過3個小時後，即可到達花蓮火車站 ，再步行16分鐘即可到達目的地。</p>
				</div>
			</div><br>
			<div>
	         	<a data-toggle="collapse" href="#cc3" aria-expanded="false" aria-controls="#cc3">
					<img src="<%=request.getContextPath()%>/front-end/imagesCustom/icon_parking.png"><span>停車資訊</span>
				</a>
				<div class="collapse" id="cc3">
					<p>雖然說山莊都沒有附設停車場，但是山莊附近空地很多，所以只要看到有位置都可以停，距離飯店步行不到5分鐘，若有老人或小孩，需要接駁服務，可以馬上聯絡我們，我們會派服務人員接駁。</p>
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