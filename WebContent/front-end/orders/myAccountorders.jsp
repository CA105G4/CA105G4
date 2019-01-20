<%@ page import="com.member.model.MemberVO"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orders.model.OrdersVO"%>
<%@ page import="com.orders.model.OrdersService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

<%
	MemberVO memVO = (MemberVO)session.getAttribute("memberVO");
	OrdersService ordSvc = new OrdersService();
	
	List<OrdersVO> list = ordSvc.findOrdersBymemIDordState0(memVO.getMemID());
	pageContext.setAttribute("list", list);
	
	System.out.println("myAccountOrder = " + memVO.getMemID());
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- 頁面標籤 -->
    <title>Order</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
<!-- <link href="https://fonts.googleapis.com/css?family=Rubik:300,400,500" rel="stylesheet"> -->
    <!-- 原本為網址(如上行)，自己新增資料夾 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/fontsgoogleapiscom.css">
    
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
					<li class="nav-item active"><a href="<%=request.getContextPath()%>/front-end/member/myAccountMyPage.jsp" class="nav-link">My Account</a></li>
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
  
  



<!-- 廣告瀏覽區 -->
  <div class="block-31" style="position: relative;">
    <div class="owl-carousel loop-block-31 ">
      <div class="block-30 item" style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/workExchangebanner.jpg'); min-height: 150px;height: 30vh" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center">
            <div class="col-md-10">
            </div>
          </div>
        </div>
      </div>
      <div class="block-30 item" style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/banner2.jpg'); min-height: 150px;height: 30vh" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center">
            <div class="col-md-10">
              <h2 class="heading"></h2>
            </div>
          </div>
        </div>
      </div>
      <div class="block-30 item" style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/EastScenerybanner.jpg'); min-height: 150px;height: 30vh" data-stellar-background-ratio="0.5">
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


  <br>
  
  
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-3">
				<!-- Sidebar -->
				<div class="list-group">
					<a href="<%=request.getContextPath()%>/front-end/member/myAccountMyPage.jsp" class="list-group-item"><i class="glyphicon glyphicon-user"></i> <span>My Page</span></a>
					<a href="<%=request.getContextPath()%>/front-end/orders/myAccountorders.jsp" class="list-group-item active"><i class="fa fa-credit-card"></i> <span>Orders</span></a>
					<a href="<%=request.getContextPath()%>/front-end/orders/myAccountordersRecord.jsp" class="list-group-item"><i class="fa fa-question-circle"></i> <span>Order Record</span></a>
					<a href="<%=request.getContextPath()%>/front-end/article/myExperience.jsp" class="list-group-item"><i class="fa fa-arrow-circle-o-left"></i><span>My Experience</span></a>
					<a href="<%=request.getContextPath()%>/front-end/coupon/myCoupon.jsp" class="list-group-item "><i class="fa fa-book"></i> <span>My Coupon</span></a>
					<a href="<%=request.getContextPath()%>/front-end/roomType/myRoomType.jsp" class="list-group-item"><i class="glyphicon glyphicon-heart"></i> <span>My RoomType</span></a>
				</div>
				<!-- Sidebar -->
			</div>
			
			<div class="col-xs-12 col-sm-9">
			<!--這邊開始自由發揮-->
				<h1>我的訂單</h1>
					<table class="table table-bordered table-striped table-hover">
							<tr>
								<th>訂單編號</th>
								<th>分店編號</th>
								<th>房間數</th>
								<th>訂單種類</th>
								<th>人數</th>
								<th>總額</th>
								<th>訂金</th>
								<th>付款方式</th>
								<th>下訂時間</th>
								<th>訂單狀態</th>
								<th></th>
							</tr>
							<%@ include file="page1.file" %> 
							<c:forEach var="ordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								
								<tr>
									<td><a href="<%=request.getContextPath()%>/orders/orders.do?ordID=${ordVO.ordID}&action=getAll_OrderDetail&requestURL=<%=request.getServletPath()%>&whichPage=<%=whichPage%>">${ordVO.ordID}</a></td>
					<jsp:useBean id="braSvc" scope="page" class="com.branch.model.BranchService" />
									<td>${braSvc.getOneByID(ordVO.braID).getBraName()}</td>
									<td>${ordVO.numOfRoom}</td>
									<td>${ordTypeMap.get(ordVO.getOrdType())}</td> 
									<td>${ordVO.numOfGuest}</td>
									<td>${ordVO.amount}</td>
									<td>${ordVO.bond}</td>
									<td>${paymentMap.get(ordVO.getPayment())}</td>
									<td>${ordVO.ordTime}</td>
									<td>${ordStateMap.get(ordVO.getOrdState())}</td>
									<td>
									  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" style="margin-bottom: 0px;">
									     <input type="submit" value="取消訂單" class="btn btn-info">
									     <input type="hidden" name="ordID"  value="${ordVO.ordID}">
									     <input type="hidden" name="requestURL"  value="<%=request.getServletPath()%>">
									     <input type="hidden" name="action"	value="CancelOrders"></FORM>
									</td>
								</tr>
							</c:forEach>
						</table>
						<%@ include file="page2.file" %>
    
          <!--這邊結束自由發揮--> 
       </div>
      </div>

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
  
<!--   <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script> -->
  <!-- 原本為網址(如上行)，自己新增資料夾 -->
    <script src="<%=request.getContextPath()%>/front-end/js/mapsgoogleapiscom.js"></script>
  
  <script src="<%=request.getContextPath()%>/front-end/js/google-map.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/main.js"></script>
  
  <%-- ${openModal!=null} --%>
<c:if test="${openModal!=null}">

<div id="myModal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Welcome</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="listOneOrder.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
     </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

        <script>
     		$('#myModal').modal({
 				 show: true
			})
        </script> 
 </c:if>	
<!-- Modal -->
  
  <script>
    $(document).ready(function() {
      $('.list-group-item').click(function(e) {
      e.stoppropagation()
      $('.list-group-item').removeClass('active');
      $(this).addClass('active');
    });
    });
  </script>  
  </body>
</html>