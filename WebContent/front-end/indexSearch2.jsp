<%@page import="java.util.*"%>
<%@page import="com.roomType.model.RoomTypeVO"%>
<%@page import="com.billboard.model.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
	BillboardService bbSvc = new BillboardService();
	List<BillboardVO>bbList =bbSvc.getAll();
	pageContext.setAttribute("bbList", bbList);
%>
<%
	ArticleService articleService = new ArticleService();
	List<ArticleVO> list = articleService.getLastestThree();
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- 頁面標籤 -->
    <title>翔太山莊</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://unpkg.com/gijgo@1.9.11/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> 
    <link href="https://fonts.googleapis.com/css?family=Rubik:300,400,500" rel="stylesheet">
    <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/aos.css">
    <link rel="stylesheet" href="css/ionicons.min.css">
<!--     <link rel="stylesheet" href="css/bootstrap-datepicker.css"> -->
<!--     <link rel="stylesheet" href="css/jquery.timepicker.css"> -->
    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/icomoon.css">
    <link rel="stylesheet" href="css/style.css">
  
<!--    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">  -->
    
    <style>
		.input-group-prepend .btn, .input-group-append .btn {
			position: relative;
			z-index: 0;
		}
		
		.input-group-append .btn, .input-group-prepend .btn {
			position: relative;
			z-index: 0; 
		}
	</style>
	<style type="text/css">
        .thumbnail iframe {
                 width: 1440px;
                 height: 1200px;
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
			<a class="navbar-brand" href="<%=request.getContextPath()%>/front-end/indexSearch2.jsp">Xiangtai village</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#ftco-nav" aria-controls="ftco-nav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="oi oi-menu"></span> Menu
			</button>
			<!--NavBar 右半部-->
			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a href="<%=request.getContextPath()%>/front-end/indexSearch2.jsp" class="nav-link">Home</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/roomType/roomType.jsp" class="nav-link">Room Type</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/workExchange/listAllWE.jsp" class="nav-link">Stay and Help</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/coupon/coupon.jsp" class="nav-link">Coupon</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/member/myAccountMyPage.jsp" class="nav-link">My Account</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/question/frontChat.jsp" class="nav-link">FAQ</a></li>
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
		<div class="owl-carousel loop-block-31 ">
			<c:forEach var="bbVO" varStatus="count" items="${bbList}">
				<c:set var="bbindex" value="${count.index}" />
				<%
					int index = (Integer) pageContext.getAttribute("bbindex");
					String encodedText = null;
					
					if(bbList.get(index).getpic() != null){
						Base64.Encoder encoder = Base64.getEncoder();
						encodedText = encoder.encodeToString(bbList.get(index).getpic());
						pageContext.setAttribute("bb", new Integer(1));
					} else {
						pageContext.setAttribute("bb", new Integer(0));
					}
   				 %>
				<c:if test="${bb == 1 && bbVO.bbStatus == 1 }">
					<div class="block-30 item"
						style="background-image: url('data:image/png;base64, <%=encodedText%>'); min-height: 600px; height: 80vh"
						data-stellar-background-ratio="0.5">
						<div class="container">
							<div class="row align-items-center">
								<div class="col-md-10">
									<span class="subheading-sm">Welcome</span>
									<h2 class="heading">Enjoy a Luxury Experience</h2>
								</div>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>


	<!-- 快速查房  style.css Line8464 -->
  <div class="container">
      
      <div class="row mb-5">
        <div class="col-md-12">
          <div class="block-32">
            <form METHOD="post" action="<%=request.getContextPath() %>/roomType/roomType.do">
              <div class="row">
                <div class="col-md-6 mb-3 mb-lg-0 col-lg-3">
                  <label for="checkin">Check In</label>
                  <div class="field-icon-wrap">
                    <div class="icon"><span class="icon-calendar"></span></div>
                    <input type="text" id="checkin_date" class="form-control" name="startdate" required>
                  </div>
                </div>
                
                <div class="col-md-6 mb-3 mb-lg-0 col-lg-3">
                  <label for="checkin">Check Out</label>
                  <div class="field-icon-wrap">
                    <div class="icon"><span class="icon-calendar"></span></div>
                    <input type="text"  id="checkout_date" class="form-control" name="enddate" required>
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
                  
                  <jsp:useBean id="bchSvc" scope="page"  class="com.branch.model.BranchService" />
                    <div class="col-md-6 mb-3 mb-md-0">
                      <label for="checkin">Branch</label>
                      <div class="field-icon-wrap">
                        <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                        <select name="braID" id="" class="form-control">
                           <c:forEach var="bchVO" items="${bchSvc.all}" >
                          <option value="${bchVO.braID }">${bchVO.braName}</option>
                         </c:forEach>
                        </select>
                      </div>
                    </div>
                  </div>
                </div>
                
                <div class="col-md-6 col-lg-3 align-self-end">
                  <button class="btn btn-primary btn-block icon-search" type="submit">&nbsp;Search</button>
                </div>
                
              </div>
              <input  name="action" value="search"  style="display: none">
              
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
	                <div class="image align-self-center"><img src="<%=request.getContextPath()%>/member/memImg.do?memID=${articleVO.memid}" alt="Person here"></div>
	                <div class="name-text align-self-center">
	                  <h2 class="heading">${memberService.getOneMem(articleVO.memid).memAcc}</h2>
	                </div>
	              </div>
          
	              <div class="thumbnail" style="height: 300px">
	                <a href="<%=request.getContextPath()%>/front-end/article/viewArticle.jsp?artid=${articleVO.artid}">
	                    <div style="position:absolute;z-index:500;height:1440px;width:1440px;""></div>
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
                <p class="mb-5">麻翔山莊創立於1923年，於日治時期台東地區第一家現代化旅館，超過90年以上的經營，成為台灣最具指標性的山莊，分店翔泰山莊於2018年，符合環境友善，同時匯集最新科技的六星級旅館. </p>
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
  
  
<!--   <script src="js/bootstrap-datepicker.js"></script> -->
  
  
  
  <script src="js/aos.js"></script>
  <script src="js/jquery.animateNumber.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="js/google-map.js"></script>
  <script src="js/main.js"></script>
  <script src="https://unpkg.com/gijgo@1.9.11/js/gijgo.min.js" type="text/javascript"></script> 
  
<!--   <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
  
   
  
  
  
  
  
  
  
    
  </body>
  

<script>
var today = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());
$('#checkin_date').datepicker({
    uiLibrary: 'bootstrap4',
    iconsLibrary: 'fontawesome',
    format: 'yyyy-mm-dd',
    minDate: today,
    maxDate: function () {
        return $('#checkout_date').val();
    }
});
$('#checkout_date').datepicker({
    uiLibrary: 'bootstrap4',
    iconsLibrary: 'fontawesome',
    format: 'yyyy-mm-dd',
    minDate: function () {
        return $('#checkin_date').val();
    }
});

//$(".msgAlert").blur(function(){
//if($(this).val() === ''){
//	swal("請記得輸入入住日期喔", "拜託惹~");
//}else{
//	$("form").submit();
//}
//});

  </script>
</html>