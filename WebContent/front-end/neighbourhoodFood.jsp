<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.branch.model.*"%>
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
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    
    <style>
	#map {
		height: 600px;
		position: relative;
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




  <div style="padding-bottom: 30px;">
	  <div class="container">
	  	  <!-- 頁籤 -->
	      <ul class="page-menu" style="padding-bottom: 0px;">
	        <li><a class="menu-header" href="<%=request.getContextPath()%>/front-end/neighbourhood.jsp">交通資訊</a></li>
	        <li class="page-menu-on"><a class="menu-header" href="#">附近餐廳</a></li>
	        <li><a class="menu-header" href="<%=request.getContextPath()%>/front-end/neighbourhoodScenery.jsp">熱門景點</a></li>
	      </ul>
	      <div class="all-bigtitle">
			<p class="bigtitle-tw"><img src="<%=request.getContextPath()%>/front-end/imagesCustom/foodie.jpg" alt="附近餐廳"></p>
    	  <div class="clean"></div>
    		<p class="bigtitle-img"><img src="<%=request.getContextPath()%>/front-end/imagesCustom/Hito.jpg" alt="附近餐廳"></p>
     	 </div>
	      <div class="input-group">
	        <jsp:useBean id="braSvc" class="com.branch.model.BranchService"/>
	        <select class="branch">
	      		  <option value="0">選擇分店</option>
	      	  <c:forEach var="braVO" items="${braSvc.all}">
	      	  	  <option id="${braVO.braID}" value="${braVO.braID}">${braVO.braName}</option>
	      	  </c:forEach>
	        </select>
		  	<input id="searchtext" type="text" class="form-control" placeholder="想吃點甚麼呢...">
	        <input class="btn btn-primary" type="button" onclick="mySearch();" value="搜尋Let's GO!">
	      </div>
	<!--       <div id="right-panel"></div> -->
	      <div id="map"></div>
	  </div>
  </div>
  
  
  
  
  <!-- googleMap -->
  <script>
  var branch;
  var bra;
  var bralat;
  var bralng;
  var center; //中心點位置
  var map;    
  var infoWindow; //資訊視窗
  var placesService;
  var directionsService;
  var directionsDisplay;
  
  //更換分店傳回Servlet取得分店座標
  $(function(){
 		$("select").change(function(){
 			$.ajax({
 				type:"GET",
 				url:"<%=request.getContextPath()%>/MapServlet",
				data : {
						"action" : 'getBranchLocation',
						"braID" : $(this).val(),
						},
				dataType : 'json',
				success : function(res) {
						console.log(res);
						bralat = parseFloat(res.lat);
						bralng = parseFloat(res.lng);
						console.log(typeof bralat);
						console.log(typeof bralng);
						console.log(bralat);
						console.log(bralng);
						
						bra = {lat:bralat,lng:bralng};
						initMap(bra);
						console.log("我換分店了");
						console.log(bra);
					},
				error : function() {
					alert("請幫我選擇一間分店!!!");
				}
			})
		})
	});
  
  	
  
  
  
  function initMap() {
	  window.navigator.geolocation.getCurrentPosition(myPosition);
  }
  
  function myPosition(bra){
	  if(center == null){
		  center = {lat:bra.coords.latitude, lng:bra.coords.longitude};
		  console.log("載入畫面時座標");
		  console.log(center);
	  }else{
		  center = {lat:bralat,lng:bralng};
		  console.log(center);
		  console.log("新座標");
	  }
	  map = new google.maps.Map(document.getElementById('map'),{
		 center : center,
		 zoom : 15
	  });
	  var marker = new google.maps.Marker({position:center, map:map});
	  infoWindow = new google.maps.InfoWindow();
	  placesService  = new google.maps.places.PlacesService(map); 
	  
	  // 載入路線服務與路線顯示圖層
      directionsService = new google.maps.DirectionsService();
	  directionsDisplay = new google.maps.DirectionsRenderer();
	  // 放置路線圖層
	  directionsDisplay.setMap(map);
// 	  directionsDisplay.setPanel(document.getElementById('right-panel'));
	  
  }
  	
  
  function mySearch(){
	 	//清空Marker所有結果
	    for(var i = 0; i < markerArray.length; i++){
	        markerArray[i].setMap(null);
	    }
// 	 	//清空路線Markers
	 	for(var k = 0; k<markers.length; k++){
	 		markers[k].setMap(null);
	 	}

 	 	//清空先前呈現的路線
	    directionsDisplay.setMap(null); // clear direction from the map
	    directionsDisplay = new google.maps.DirectionsRenderer(); 
	    directionsDisplay.setMap(map); //this is to set up again
	    

	    markerArray = [];
	    placesService.nearbySearch({
	        location: center,
	        radius: 2500,
	        //類型參考:https://developers.google.com/places/supported_types
	        type: 'restaurant',
	        keyword: document.getElementById("searchtext").value}, callback);
	    window.sessionStorage.lastSearch = document.getElementById("searchtext").value;   // sessionStorage    
	}
  
  function callback(results, status){
	  if (status == google.maps.places.PlacesServiceStatus.OK) {
		    for (var i = 0; i < results.length; i++) {
		      var place = results[i];
		      createMarker(results[i]);
		      console.log(results[i]);
		    }
		  }
  }
  
  //查詢後的Markers
  var markerArray = [];
  //路徑規劃後的Markers及infoWindows
  var markers=[];
  var infoWindows =[];
  
  function createMarker(place) {
      var placeLoc = place.geometry.location;
      var marker = new google.maps.Marker({
          map: map,
          position: placeLoc,
          icon: {
              url: '<%=request.getContextPath()%>/front-end/imagesCustom/mapIcon/cutlery512.png',
              anchor: new google.maps.Point(10, 10),    
              scaledSize: new google.maps.Size(20, 34)  // 調整大小
          }
      });
      
      markerArray.push(marker);
      
      //註冊click事件在Marker上
      google.maps.event.addListener(marker, 'click', function() {
  	 	
    	//清空路徑中的Marker
  	    for(var j = 0; j < markers.length; j++){
  	        markers[j].setMap(null);
  	    }
  		//清空先前呈現的路線
	    directionsDisplay.setMap(null); // clear direction from the map
	    directionsDisplay = new google.maps.DirectionsRenderer(); 
	    directionsDisplay.setMap(map); //this is to set up again  
    	  
    	  
    	  //呈現在infoWindow上面的東西
    	  msg = place.name + "<br>"; //店名
          var openOrNot; //是否有營業
          if(place.opening_hours != null){
	       	  if(place.opening_hours.open_now === true ){
	       	      openOrNot = "營業中";
	   		  }else{
	       		  openOrNot = "休息中";
	          };
	          msg = msg + openOrNot + "<br>" ;
          }
          //評分
          for(var i = 0; i < parseInt(place.rating);i++){
              msg =  msg + "\u261D" ;
          }
          msg = msg + "<br>";	
          //地址
          msg =  msg + place.vicinity;
          infoWindow.setContent(msg);
          infoWindow.open(map, this);
          
    	  
          //路線起點與終點
          var request = {
              origin : center,
              destination : place.geometry.location,
              travelMode : 'DRIVING'
          };
          
          
          
          // 繪製路線
          directionsService.route(request, function (response, status) {
              if (status == 'OK') {
            	  //去除規劃路徑A->B的Marker
            	  directionsDisplay.setOptions( { suppressMarkers: true } );
                  var steps = response.routes[0].legs[0].steps;
                  steps.forEach((e,i) => {
                  console.log(steps);
               	  //放置Marker
              	  markers[i] = new google.maps.Marker({
  				  	  position:{lat:e.start_location.lat(), lng:e.start_location.lng()},
                      map:map,
//   					  label: { text: i + '', color: "#fff" },
  				  	  icon:{
  			              url: '<%=request.getContextPath()%>/front-end/imagesCustom/mapIcon/car-trip.png',
  			              anchor: new google.maps.Point(10, 10),    
  			              scaledSize: new google.maps.Size(25, 34)  // 調整大小
  			          }
                  });
                  //加入infoWindow	
                  infoWindows[i] = new google.maps.InfoWindow({
                      content:e.instructions
                	});
                	markers[i].addListener('click',function(){
               		    if(infoWindows[i].anchor){
               	            infoWindows[i].close();
               	        }else{
               	            infoWindows[i].open(map, markers[i]);
               	        }
                	});
                  });
              directionsDisplay.setDirections(response);
              } else {
                  console.log(status);
              }
          });
      });
  }
  
  
  //保存先前搜尋過的在seesionStorage
  window.onload = function(){
      if(window.sessionStorage.lastSearch != undefined){
          document.getElementById("searchtext").value = window.sessionStorage.lastSearch; 
      }  
  };
  
  
  </script>































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
  <script src="<%=request.getContextPath()%>/front-end/js/jquery.min.js"></script>
  </body>
</html>  