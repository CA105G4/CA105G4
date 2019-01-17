<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>

    
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
    <style>
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
	.page-menu2 {
	    padding: 17px 15px;
	    text-align: center;
	    background-color: #7a7d80;
	}
	.page-menu2 li {
    	display: inline-block;
    	font-family:"微軟正黑體";
	}
	.page-menu2 li a {
	    padding: 0 10px;
	    display: block;
	    color: #fff;
	    font-size: 24px;
    }
    .img-bg {
	    display: inline-block;
	    padding: 0 15px;
 	    background-color: #f2f2f2; 
	    position: relative;
	    z-index: 3;
	}
	.title02 {
	    padding: 0 0 20px 0;
	    text-align: center;
	    position: relative;
    }
    .title02 .img-bg {
	    display: inline-block;
	    padding: 0 15px;
	    background-color: #f2f2f2;
	    position: relative;
	    z-index: 3;
	}
	.title02 .img-bg h3 {
	    color: #F05060;
	    font-size: 24px;
	    line-height: 30px;
	    font-weight: normal;
	}
	.title01:after, .title02:after {
	    content: "";
	    width: 100%;
	    height: 1px;
	    background-color: #837d7b;
	    position: absolute;
	    left: 0;
	    bottom: 42px;
	}
	.mb-5{
    margin-bottom: 2rem !important;
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
<!--  				NavBar 右半部 -->
			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/indexSearch2.jsp" class="nav-link">Home</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/roomType/roomType.jsp" class="nav-link">Room Type</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/workExchange/listAllWE.jsp" class="nav-link">Stay and Help</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/coupon/coupon.jsp" class="nav-link">Coupon</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/member/myAccountMyPage.jsp" class="nav-link">My Account</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/question/frontChat.jsp" class="nav-link">F&Q</a></li>
					<li class="nav-item active"><a href="<%=request.getContextPath()%>/front-end/neighbourhood.jsp" class="nav-link">About Us</a></li>
					<c:choose>
						<c:when test="${memberVO == null}">
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/front-end/Login.jsp">Login</a>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/front-end/MemLogout.do">Logout</a>
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
  <div class="container">
      <ul class="page-menu" style="padding-bottom: 0px;">
        <li><a class="menu-header" href="<%=request.getContextPath()%>/front-end/neighbourhood.jsp">交通資訊</a></li>
        <li><a class="menu-header" href="<%=request.getContextPath()%>/front-end/neighbourhoodFood.jsp">附近餐廳</a></li>
        <li class="page-menu-on"><a class="menu-header" href="#">熱門景點</a></li>
      </ul>
      <!--  標題  -->
  	  <div class="all-bigtitle">
		<p class="bigtitle-tw"><img src="<%=request.getContextPath()%>/front-end/imagesCustom/hot.jpg" alt="熱們景點"></p>
    	  <div class="clean"></div>
    	<p class="bigtitle-img"><img src="<%=request.getContextPath()%>/front-end/imagesCustom/Hito.jpg" alt="熱們景點"></p>
      </div>
  </div>
<!--       <div class="text-center"> -->
<!--         <h2>鄰近景點</h2> -->
<!--       </div> -->
<!--       <div class="input-group"> -->
<!-- 	  	<input id="searchtext" type="text" class="form-control" placeholder="想去哪裡呢..."> -->
<!--         <input class="btn btn-success" type="button" onclick="mySearch();" value="GO!"> -->
<!--       </div> -->
<!--       <div id="map"></div> -->
   
   <div class="page-menu2">
    <li><a href="javascript:;" title="台北市商圈" onclick="go_where('show01');">台北市商圈</a></li>
    <li><a href="javascript:;" title="台北市夜市" onclick="go_where('show02');">台北市夜市</a></li>
    <li><a href="javascript:;" title="台北市景點" onclick="go_where('show03');">台北市景點</a></li>
    <li><a href="javascript:;" title="台北市風景區" onclick="go_where('show04');">台北市風景區</a></li>
   </div>
   
   
   
  <div class="site-section bg-light" style="background: #f1efed !important;padding-top: 50px;">    
	  <div class="container">
    <div class="title02" id="show01">
      <div class="img-bg"><h3>台北市熱門商圈</h3></div>
    </div>
	  	<div class="row mb-5">
	      <div class="col-lg-4 mb-5">
	            <div class="block-34">
	              <div class="image">
	                <a href="#"><img src="images/img_1.jpg" alt="Image placeholder"></a>
	              </div>
	              <div class="text">
	                <h2 class="heading">Bachelor Room</h2>
	                <div class="price"><sup>$</sup><span class="number">156</span><sub>/per night</sub></div>
	                <ul class="specs">
	                  <li><strong>Adults:</strong> 1</li>
	                  <li><strong>Categories:</strong> Single</li>
	                  <li><strong>Facilities:</strong> Closet with hangers, HD flat-screen TV, Telephone</li>
	                  <li><strong>Size:</strong> 20m<sup>2</sup></li>
	                  <li><strong>Bed Type:</strong> One bed</li>
	                </ul>
	              </div>
	            </div>
	      </div>
	      <div class="col-lg-4 mb-5">
	            <div class="block-34">
	              <div class="image">
	                <a href="#"><img src="images/img_1.jpg" alt="Image placeholder"></a>
	              </div>
	              <div class="text">
	                <h2 class="heading">Bachelor Room</h2>
	                <div class="price"><sup>$</sup><span class="number">156</span><sub>/per night</sub></div>
	                <ul class="specs">
	                  <li><strong>Adults:</strong> 1</li>
	                  <li><strong>Categories:</strong> Single</li>
	                  <li><strong>Facilities:</strong> Closet with hangers, HD flat-screen TV, Telephone</li>
	                  <li><strong>Size:</strong> 20m<sup>2</sup></li>
	                  <li><strong>Bed Type:</strong> One bed</li>
	                </ul>
	              </div>
	            </div>
	      </div>
	      <div class="col-lg-4 mb-5">
	            <div class="block-34">
	              <div class="image">
	                <a href="#"><img src="images/img_1.jpg" alt="Image placeholder"></a>
	              </div>
	              <div class="text">
	                <h2 class="heading">Bachelor Room</h2>
	                <div class="price"><sup>$</sup><span class="number">156</span><sub>/per night</sub></div>
	                <ul class="specs">
	                  <li><strong>Adults:</strong> 1</li>
	                  <li><strong>Categories:</strong> Single</li>
	                  <li><strong>Facilities:</strong> Closet with hangers, HD flat-screen TV, Telephone</li>
	                  <li><strong>Size:</strong> 20m<sup>2</sup></li>
	                  <li><strong>Bed Type:</strong> One bed</li>
	                </ul>
	              </div>
	            </div>
	      </div>
  	</div>
  </div>
  
  
  <div class="container">
    <div class="title02" id="show02">
      <div class="img-bg"><h3>台北市熱門夜市</h3></div>
    </div>
	  	<div class="row mb-5">
	      <div class="col-lg-4 mb-5">
	            <div class="block-34">
	              <div class="image">
	                <a href="#"><img src="images/img_1.jpg" alt="Image placeholder"></a>
	              </div>
	              <div class="text">
	                <h2 class="heading">Bachelor Room</h2>
	                <div class="price"><sup>$</sup><span class="number">156</span><sub>/per night</sub></div>
	                <ul class="specs">
	                  <li><strong>Adults:</strong> 1</li>
	                  <li><strong>Categories:</strong> Single</li>
	                  <li><strong>Facilities:</strong> Closet with hangers, HD flat-screen TV, Telephone</li>
	                  <li><strong>Size:</strong> 20m<sup>2</sup></li>
	                  <li><strong>Bed Type:</strong> One bed</li>
	                </ul>
	              </div>
	            </div>
	      </div>
	      <div class="col-lg-4 mb-5">
	            <div class="block-34">
	              <div class="image">
	                <a href="#"><img src="images/img_1.jpg" alt="Image placeholder"></a>
	              </div>
	              <div class="text">
	                <h2 class="heading">Bachelor Room</h2>
	                <div class="price"><sup>$</sup><span class="number">156</span><sub>/per night</sub></div>
	                <ul class="specs">
	                  <li><strong>Adults:</strong> 1</li>
	                  <li><strong>Categories:</strong> Single</li>
	                  <li><strong>Facilities:</strong> Closet with hangers, HD flat-screen TV, Telephone</li>
	                  <li><strong>Size:</strong> 20m<sup>2</sup></li>
	                  <li><strong>Bed Type:</strong> One bed</li>
	                </ul>
	              </div>
	            </div>
	      </div>
	      <div class="col-lg-4 mb-5">
	            <div class="block-34">
	              <div class="image">
	                <a href="#"><img src="images/img_1.jpg" alt="Image placeholder"></a>
	              </div>
	              <div class="text">
	                <h2 class="heading">Bachelor Room</h2>
	                <div class="price"><sup>$</sup><span class="number">156</span><sub>/per night</sub></div>
	                <ul class="specs">
	                  <li><strong>Adults:</strong> 1</li>
	                  <li><strong>Categories:</strong> Single</li>
	                  <li><strong>Facilities:</strong> Closet with hangers, HD flat-screen TV, Telephone</li>
	                  <li><strong>Size:</strong> 20m<sup>2</sup></li>
	                  <li><strong>Bed Type:</strong> One bed</li>
	                </ul>
	              </div>
	            </div>
  </div>
	      </div>
	    </div>
	  </div>
  
  
  
  
  
  
  
  
  
  
<!-- js -->
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="<%= request.getContextPath() %>/front-end/js/lity.min.js" type="text/javascript"></script>
<script type="text/javascript">

$(function() {
  var view_width = parseInt($(document).width(),10);
  if(view_width <= 768){$('.menu').addClass('menu-go');
    initMenu();
  }
  else{
    $('.menu').removeClass('menu-go');
    $('.menu li a').unbind('click');
  }

  $(window).resize(function() {
    var _width = $(window).width();
    if (_width <= 768) {
      $('.menu').addClass('menu-go');
      initMenu();
    } else {
      $('.menu').removeClass('menu-go');
      $('.menu li a').unbind('click');
    }
  });

  $(window).scroll(function() {//顯示按鈕
    if($(this).scrollTop() != 0) {
      $('.gotop').fadeIn();
    } else {
      $('.gotop').fadeOut();
    }
  });
});
</script>
  
<!-- menu-->
<script type="text/javascript" src="<%= request.getContextPath() %>/front-end/js/trunk.js"></script>
<script type="text/javascript">
function initMenu() {
    $(".sub-menu").hide();
    $(".current_page_item .sub-menu").show();
    $('.menu-go li a').click(function() {
        var checkElement = $(this).next();
        if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
            return false;
        }
        if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
            console.log(checkElement.parentsUntil('.menu-go'));
            $('.menu-go ul:visible').not(checkElement.parentsUntil('.menu-go')).slideUp('normal');
            checkElement.slideDown('normal');
            return false;
        }
    });
}
</script>

<!--go top-->
<script src="<%= request.getContextPath() %>/front-end/js/animatescroll.js"></script>
<script type="text/javascript">
  function getScrollTop() { // 取得捲軸位置
    if (typeof pageYOffset != 'undefined') {
      //most browsers except IE before #9
      return pageYOffset;
    } else {
      var B = document.body; //IE 'quirks'
      var D = document.documentElement; //IE with doctype
      D = (D.clientHeight) ? D : B;
      return D.scrollTop;
    }
  }
  var pos, head_h, width_w;

  function go_where(value) {
    var where = document.getElementById(value);
    pos = getScrollTop();
    head_h = parseInt($('header').height(), 10);
    width_w = parseInt($(document).width(), 10);
    $(window).resize(function() {
      pos = getScrollTop();
      if (width_w <= 768) {
        head_h = head_h - 2;
        pos = pos + 1;
      } else {
        head_h = head_h + 37;
      }
    });
    if (width_w <= 768) {
      head_h = head_h - 2;
      pos = pos + 1;
    } else {
      head_h = head_h + 37;
    }
    if (pos > 0) {
      $(where).animatescroll({
        padding: head_h,
        scrollSpeed: 2000,
        easing: 'easeInOutExpo'
      });
    } else {
      $(where).animatescroll({
        scrollSpeed: 2000,
        easing: 'easeInOutExpo'
      });
    }
  }
  $(window).load(function() {
    setAnchorPos();
  });

  function setAnchorPos() {
    var hash = window.location.hash.substring(1);
    if (hash.length > 0) {
      //$('html,body').animate({
      //    scrollTop: 0
      //}, 1);
      console.log(hash);
      var target = $('#' + hash);
      target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
      if (target.length) {
        $('html,body').animate({
          scrollTop: target.offset().top - 140
        }, 1000);
        return true;
      }
    }
  }
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
  <script src="<%=request.getContextPath()%>/front-end/js/main.js"></script>
  
    
  </body>
</html>  