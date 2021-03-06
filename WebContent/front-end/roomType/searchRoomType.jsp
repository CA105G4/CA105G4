<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.roomType.model.*"%>
<%@page import="java.util.*"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
	List<RoomTypeVO> sList=(List<RoomTypeVO>)request.getAttribute("searchList");
	
%>

<jsp:useBean id="adSvc" scope="page" class="com.activityDetail.model.ActivityDetailService" />
<%-- <jsp:useBean id="searchList" scope="request" type="java.util.List<RoomTypeVO>" /> --%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Bright Hotel - Free Bootstrap 4 Template by Colorlib</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://unpkg.com/gijgo@1.9.11/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> 
    <link href="https://fonts.googleapis.com/css?family=Rubik:300,400,500" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/animate.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/owl.carousel.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/magnific-popup.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/aos.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/ionicons.min.css">
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap-datepicker.css"> --%>
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery.timepicker.css"> --%>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/flaticon.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/icomoon.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/style.css">
    
    <style>
		.input-group-prepend .btn, .input-group-append .btn {
			position: relative;
			z-index: 0;
		}
		
		.input-group-append .btn, .input-group-prepend .btn {
			position: relative;
			z-index: 0; 
		}
		
			.badge {
  			font-family: oswald, sans-serif;
  			font-size: 28px;
  			transform: rotate(45deg);
  			margin: 50px auto;
  			background: #6c2d66;
  			width: 80px;
  			height: 80px;
  			line-height: 80px;
  			text-align: center;
  			text-transform: uppercase;
  			border-radius: 8px;
  			color: #FFFFFF;
  			text-shadow: 0 1px 1px rgba(0,0,0,.3);
			}
			
			.badge span {
  			display: block;
  			transform: rotate(-45deg);
 			 opacity: .9;
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



	<div class="block-30 block-30-sm item"
		style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/room.jpg'); min-height: 600px; height: 80vh"
		data-stellar-background-ratio="0.5">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-md-10">
					<span class="subheading-sm">Rooms</span>
					<h2 class="heading">Rooms &amp; Suites</h2>
				</div>
			</div>
		</div>
	</div>


  <!-- 複合查詢 -->
  <div class="container">
       <div class="row mb-5">
        <div class="col-md-12">
          <div class="block-32">
            <form METHOD="post"  action="<%=request.getContextPath() %>/roomType/roomType.do" autocomplete="off">
              
              <div class="row">
              
                <div class="col-md-6 mb-3 mb-lg-0 col-lg-3">
                  <label for="checkin">Check In</label>
                  <div class="field-icon-wrap">
                    <div class="icon"><span class="icon-calendar"></span></div>
                    <input type="text" id="checkin_date" class="form-control" name="startdate" value="${checkinDate}"  required="required">
                  </div>
                </div>
                
                <div class="col-md-6 mb-3 mb-lg-0 col-lg-3">
                  <label for="checkin">Check Out</label>
                  <div class="field-icon-wrap">
                    <div class="icon"><span class="icon-calendar"></span></div>
                    <input type="text"  id="checkout_date" class="form-control" name="enddate" value="${checkoutDate}"  required="required">
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
              <input type="hidden" name="action" value="search" >
              
            </form>
          </div>
        </div>
      </div>
   
</div>
    <!-- 房間一覽 style.css Line7622 -->
    <div class="site-section bg-light">
      <div class="container">
         <c:forEach var="rtVO" items="${searchList}"  varStatus="status"  >
        
        
        <div class="row mb-5">
          
          
          <div class="col-md-12 mb-5">
            
 <c:choose>
 		<c:when test="${status.index %2==0 }">
 		
            				<c:forEach var="adVO" items="${adSvc.all}" varStatus="">
            				
									<c:if test="${adVO.rtID==rtVO.rtID && adVO.actID==actVOInTime.actID}">
										
										<span class="flag-discount">
											<fmt:formatNumber type="number" value="${adVO.discount * 100}" maxFractionDigits="0"/>折
										</span>
									</c:if>
								</c:forEach>
							
            <div class="block-3 d-md-flex ">
              <div class="image" style="background-image: url('<%=request.getContextPath()%>/roomType/roomTypeImg.do?rtID=${rtVO.rtID}')"></div>
              <div class="text">
		<jsp:useBean id="rtSvc" scope="page" class="com.roomType.model.RoomTypeService" />	

                <h2 class="heading">${rtSvc.getOneRoomType(rtVO.getRtID()).rtName}</h2>
                <div class="price"><sup>$</sup><span class="number">${rtSvc.getOneRoomType(rtVO.getRtID()).weeklyPrice}</span><sub>/per night</sub></div>
                <ul class="specs mb-5">
                  <li><strong>Adults:</strong> ${rtSvc.getOneRoomType(rtVO.getRtID()).rtLimit} </li>
                  <li><strong>Total:</strong>  ${rtSvc.getOneRoomType(rtVO.getRtID()).total}</li>
                  <li><strong>Introduction:</strong>${rtSvc.getOneRoomType(rtVO.getRtID()).rtIntro}</li>
                   <li><strong>Facilities:</strong> Closet with hangers, HD flat-screen TV, Telephone</li>
                  <li><strong>Remaining Room:</strong> ${rtVO.getBalance()}</li>
                  <li><strong>Bed Type:</strong> One bed</li>
                </ul>
                <p><a href="<%=request.getContextPath()%>/front-end/orders/addordersBybraID.jsp?rtID=${rtVO.rtID}&braID=${rtSvc.getOneRoomType(rtVO.getRtID()).getBraID()}&checkinDate=${checkinDate}" class="btn btn-primary py-3 px-5">Reservation</a></p>
				<p><button class="btn btn-info py-3 px-5 collectRt" type="submit" value="${rtVO.rtID}">Collect Room</button></p>
				
              </div>
            </div>
</c:when>

<c:otherwise>
 <div class="col-md-12 mb-5">
            
            <div class="block-3 d-md-flex ">
              <div class="image order-2" style="background-image: url('<%=request.getContextPath()%>/roomType/roomTypeImg.do?rtID=${rtVO.rtID}'); "></div>
              		<c:forEach var="adVO" items="${adSvc.all}" varStatus="">
										<c:if test="${adVO.rtID==rtVO.rtID&& adVO.actID==actVOInTime.actID}">
										<div class="badge">
  											<span><fmt:formatNumber type="number" value="${adVO.discount * 100}" maxFractionDigits="0"/>折</span>
										</div>
										</c:if>
					</c:forEach>
              
              
              <div class="text order-1">
                
		<jsp:useBean id="rtSvc2" scope="page" class="com.roomType.model.RoomTypeService" />	
                <h2 class="heading">${rtSvc2.getOneRoomType(rtVO.getRtID()).rtName}</h2>
                <div class="price"><sup>$</sup><span class="number">${rtSvc2.getOneRoomType(rtVO.getRtID()).weeklyPrice}</span><sub>/per night</sub></div>
                <ul class="specs mb-5">
                   <li><strong>Adults:</strong> ${rtSvc2.getOneRoomType(rtVO.getRtID()).rtLimit} </li>
                   <li><strong>Total:</strong>  ${rtSvc2.getOneRoomType(rtVO.getRtID()).total}</li>
                  <li><strong>Introduction:</strong> ${rtSvc2.getOneRoomType(rtVO.getRtID()).rtIntro}</li>
                  <li><strong>Facilities:</strong> Closet with hangers, HD flat-screen TV, Telephone</li>
                  <li><strong>Remaining Room:</strong> ${rtVO.getBalance()}</li>
                  <li><strong>Bed Type:</strong> One bed</li>
                </ul>

                <p><a href="<%=request.getContextPath()%>/front-end/orders/addordersBybraID.jsp?rtID=${rtVO.rtID}&braID=${rtSvc2.getOneRoomType(rtVO.getRtID()).getBraID()}&checkinDate=${checkinDate}" class="btn btn-primary py-3 px-5">Reservation</a></p>
                <p><button class="btn btn-info py-3 px-5 collectRt" type="submit" value="${rtVO.rtID}">Collect Room</button></p>
                
              </div>
            </div>


          </div>  

</c:otherwise>




</c:choose>

          </div>  




     

        </div>

        </c:forEach>
          <!-- 房間與房間中間文字訊息 -->
        
        
           <div class="row mb-5 pt-5 justify-content-center">
            <div class="col-md-7 text-center section-heading">
              <h2 class="heading">More Rooms</h2>
              <p>Xiangtai village is your best choice for accommodation whether you are on a business or tourist trip to Taichung. It is convenient for our guests to go to tourist, shopping and dining spots in the city. It is located at a convenient location with quick access to popular spots in the city.</p>
            </div>
          </div>
          <c:set var="index" value="${status.index}" />
      
 
  <div class="row">
  <c:forEach var="rtVO" items="${searchSet}"  varStatus="status"  begin="0" end="2">   
          
          
          <div class="col-lg-4 mb-5">
            <div class="block-34">
            
              <div class="image">
                <a href="#"><img src="<%=request.getContextPath()%>/roomType/roomTypeImg.do?rtID=${rtVO.rtID}" alt="Image placeholder"></a>
              </div>
              <div class="text">
                <h2 class="heading">${rtVO.rtName}</h2>
                <div class="price"><sup>$</sup><span class="number">${rtVO.weeklyPrice}</span><sub>/per night</sub></div>
                <ul class="specs">
                  <li><strong>Adults:</strong> 1</li>
                  <li><strong>Categories:</strong> Single</li>
                  <li><strong>Facilities:</strong> Closet with hangers, HD flat-screen TV, Telephone</li>
<%--                   <li><strong>Remaining Room:</strong> ${rtVO.getBalance()}</li> --%>
                  <li><strong>Bed Type:</strong> One bed</li>
                </ul>
              </div>
            </div>
          </div>
    </c:forEach>
    
        </div>
      </div>
    </div>
    

<!--     旅客回饋 -->
<!--     <div class="site-section bg-light"> -->
<!--       <div class="container"> -->
<!--         <div class="row mb-5"> -->
<!--           <div class="col-md-7 section-heading"> -->
<!--             <span class="subheading-sm">Reviews</span> -->
<!--             <h2 class="heading">Guest Reviews</h2> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="row"> -->
<!--           <div class="col-md-6 col-lg-4"> -->

<!--             <div class="block-33"> -->
<!--               <div class="vcard d-flex mb-3"> -->
<%--                 <div class="image align-self-center"><img src="<%=request.getContextPath()%>/front-end/imagesCustom/SpongeBob.jpg" alt="Person here"></div> --%>
<!--                 <div class="name-text align-self-center"> -->
<!--                   <h2 class="heading">ZHENG BO YUAN</h2> -->
<!--                   <span class="meta">Satisfied Customer</span> -->
<!--                 </div> -->
<!--               </div> -->
<!--               <div class="text"> -->
<!--                 <blockquote> -->
<!--                   <p>&rdquo; Lorem ipsum dolor sit amet consectetur adipisicing elit. Fuga aliquid. Atque dolore esse veritatis iusto eaque perferendis non dolorem fugiat voluptatibus vitae error ad itaque inventore accusantium tempore dolores sunt. &ldquo;</p> -->
<!--                 </blockquote> -->
<!--               </div> -->
<!--             </div> -->

<!--           </div> -->
<!--           <div class="col-md-6 col-lg-4"> -->

<!--             <div class="block-33"> -->
<!--               <div class="vcard d-flex mb-3"> -->
<%--                 <div class="image align-self-center"><img src="<%=request.getContextPath()%>/front-end/imagesCustom/Sandy.jpg" alt="Person here"></div> --%>
<!--                 <div class="name-text align-self-center"> -->
<!--                   <h2 class="heading">LIU YAN JUN</h2> -->
<!--                   <span class="meta">Satisfied Customer</span> -->
<!--                 </div> -->
<!--               </div> -->
<!--               <div class="text"> -->
<!--                 <blockquote> -->
<!--                   <p>&rdquo; Lorem ipsum dolor sit amet consectetur adipisicing elit. Fuga aliquid. Atque dolore esse veritatis iusto eaque perferendis non dolorem fugiat voluptatibus vitae error ad itaque inventore accusantium tempore dolores sunt.adipisicing elit. Fuga aliquid. Atque dolore  &ldquo;</p> -->
<!--                 </blockquote> -->
<!--               </div> -->
<!--             </div> -->

<!--           </div> -->
<!--           <div class="col-md-6 col-lg-4"> -->

<!--             <div class="block-33"> -->
<!--               <div class="vcard d-flex mb-3"> -->
<%--                 <div class="image align-self-center"><img src="<%=request.getContextPath()%>/front-end/imagesCustom/Patrick_Star.png" alt="Person here"></div> --%>
<!--                 <div class="name-text align-self-center"> -->
<!--                   <h2 class="heading">CHEN YU XIANG</h2> -->
<!--                   <span class="meta">Satisfied Customer</span> -->
<!--                 </div> -->
<!--               </div> -->
<!--               <div class="text"> -->
<!--                 <blockquote> -->
<!--                   <p>&rdquo; Lorem ipsum dolor sit amet consectetur adipisicing elit. Fuga aliquid. Atque dolore esse veritatis iusto eaque perferendis non dolorem fugiat voluptatibus vitae error ad itaque inventore accusantium tempore dolores sunt. &ldquo;</p> -->
<!--                 </blockquote> -->
<!--               </div> -->
<!--             </div> -->

<!--           </div> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->







    <!-- Footer尾巴 -->
    <footer class="footer">
      <div class="container">
        <div class="row">
          <div class="col-xs-12 col-md-4"><img src="<%=request.getContextPath()%>/front-end/imagesCustom/logoC.jpg" width="250px" height="200px">
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


  <script src="<%=request.getContextPath()%>/front-end/js/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/jquery-migrate-3.0.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/popper.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/jquery.easing.1.3.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/jquery.waypoints.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/jquery.stellar.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/owl.carousel.min.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/jquery.magnific-popup.min.js"></script>
<%--   <script src="<%=request.getContextPath()%>/front-end/js/bootstrap-datepicker.js"></script> --%>
  
  <script src="<%=request.getContextPath()%>/front-end/js/aos.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/jquery.animateNumber.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/google-map.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/main.js"></script>
  <script src="https://unpkg.com/gijgo@1.9.11/js/gijgo.min.js" type="text/javascript"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.0.0/sweetalert2.all.js"></script>
  
</body>
<style>
	* {
		font-family: "Encode Sans Condensed", sans-serif;
	}
	
	.flag-discount {
		border-radius: 6px 0 0 6px;
		color: #fff;
		display: block;
		float: right;
		padding: 10px 20px;
		background: #93274f;
		font-size: 20px;
		font-weight: 400;
		position: relative;
	}
	
	.flag-discount::before, .flag-discount::after {
		content: "";
		position: absolute;
		left: 100%;
		width: 0;
		height: 0;
		border-style: solid;
		display: block;
	}
	
	.flag-discount::before {
		top: 0;
		border-width: 22px 15px 0 0;
		border-color: #93274f transparent transparent transparent;
	}
	
	.flag-discount::after {
		bottom: 0;
		border-width: 0 15px 22px 0;
		border-color: transparent transparent #93274f transparent;
	}
</style>

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
  
</script>


<script type="text/javascript">
	$(document).ready(function(){
		 $('.collectRt').click(function(){
			 $.ajax({
				 type: "GET",
				 url: "<%=request.getContextPath() %>/roomType/roomType.do",
				 data: creatQueryString($(this).val()),
				 dataType: "json",
				 success: function (){
					 swal({
	                		position: 'top-end',
	                		type: 'success',
	                		 title: 'Collect Room Successfully!!!',
	                		 showConfirmButton: false,
	                		 timer: 1500
	                } );       
			     },
	             error: function(){
	            	 swal("請登入會員", "拜託惹~");
	            	 }
	         })
		 })
	
	})
	
		function creatQueryString(rtID){
		var queryString= {"action":"collect_room", "rtID":rtID};
		console.log(queryString);
		return queryString;
	}
</script>

</html>