<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
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
		
	<style type="text/css">
		.list-group {
			margin:auto;
			float:left;
			padding-top:20px;
		}
	    
		.lead {
			margin:auto;
			left:0;
			right:0;
			padding-top:10%;
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
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/roomType/roomType.jsp" class="nav-link">Room Type</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/workExchange/listAllWE.jsp" class="nav-link">Stay and Help</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/coupon/coupon.jsp" class="nav-link">Coupon</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/member/myAccountMyPage.jsp" class="nav-link">My Account</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/question/frontChat.jsp" class="nav-link">FAQ</a></li>
					<li class="nav-item active"><a href="<%=request.getContextPath()%>/front-end/neighbourhood.jsp" class="nav-link">About Us</a></li>
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
				style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/workExchangebanner.jpg'); min-height: 150px;height: 30vh"
				data-stellar-background-ratio="0.5">
				<div class="container">
					<div class="row align-items-center">
						<div class="col-md-10"></div>
					</div>
				</div>
			</div>
			<div class="block-30 item"
				style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/banner2.jpg'); min-height: 150px;height: 30vh"
				data-stellar-background-ratio="0.5">
				<div class="container">
					<div class="row align-items-center">
						<div class="col-md-10">
							<h2 class="heading"></h2>
						</div>
					</div>
				</div>
			</div>
			<div class="block-30 item"
				style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/EastScenerybanner.jpg'); min-height: 150px;height: 30vh"
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



  <div class="container">
    <!--這邊開始自由發揮-->
<div class="container">
         	<div class="row">
         		<div class="col-xs-12 col-sm-8">
         			 <h1 >會員註冊</h1>
         			 <div class="container" style="background-color: #e3e3e3; text-align: center; font-weight: bold;">
							<div class="row">
								<span>請注意「</span>
									<span style="color:red;">※ </span>
								<span>」為必填欄位。</span>
			</div>
		</div>
          <hr>
          


<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/mem.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>會員姓名:</td>
		<td><input type="TEXT" name="memName" size="45" 
			 value="<%= (memberVO==null)? "Tomdag" : memberVO.getMemName()%>" /><span style="color:red;">※ </span></td>
	</tr>
	<tr>
		<td>會員帳號:</td>
		<td><input type="TEXT" name="memAcc" size="45" 
			 value="<%= (memberVO==null)? "overtom" : memberVO.getMemAcc()%>" /><span style="color:red;">※ </span></td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><input type="password" name="memPsw" size="45"
			 value="<%= (memberVO==null)? "overdag" : memberVO.getMemPsw()%>" /><span style="color:red;">※ </span></td>
	</tr>
	<tr>
		<td>生日:</td>
		<td><input type="TEXT" name="memBirth" id="f_date1" size="45"
			  /><span style="color:red;">※ </span></td>
	</tr>
	<tr>
		<td>會員信箱:</td>
		<td><input type="TEXT" name="memEmail" size="45"
			 value="<%= (memberVO==null)? "tomdog@gmail.com" : memberVO.getMemEmail()%>" /><span style="color:red;">※ </span></td>
	</tr>
	<tr>
		<td>會員電話:</td>
		<td><input type="TEXT" name="memTel" size="45"
			 value="<%= (memberVO==null)? "0978534656" : memberVO.getMemTel()%>" /><span style="color:red;">※ </span></td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="memAddr" size="45"
			 value="<%= (memberVO==null)? "台北市永和區明樂街59號10樓" : memberVO.getMemAddr()%>" /><span style="color:red;">※ </span></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td>
				<input type="radio" name="memSex" value="M" checked="true">男
				<input type="radio" name="memSex" value="F">女<span style="color:red;">※ </span><br>
		</td>
	</tr>
	
	<tr>
		<td>會員技能:</td>
		<td><input type="TEXT" name="memSkill" size="45"
			 value="<%= (memberVO==null)? "大胃王" : memberVO.getMemSkill()%>" /></td>
	</tr>
	<tr>
		<td>身分字號:</td>
		<td><input type="TEXT" name="memIDcard" size="45"
			 value="<%= (memberVO==null)? "T123456789" : memberVO.getMemIDcard()%>" /><span style="color:red;">※ </span></td>
	</tr>
<tr>
		<td>會員頭貼:</td>
		<td>
			<img src="<%=request.getContextPath()%>/back-end/member/images/nopic.jpg" id="previewpic" 
													class="img-fluid" width="300px">
		</td>
	</tr>
	<tr>
		<td></td>
				<td>
		<input type="file" name="memPic"  id="inputfile01">
  				</td>
	</tr>
	

</table>
<br>
	<input type="hidden" name="action" value="register">
	<input type="submit" value="送出">
</FORM>
        </div>
        <!-- /.container-fluid -->

        <!-- Sticky Footer -->
        <footer class="sticky-footer">
          <div class="container my-auto">
            <div class="copyright text-center my-auto">
            
            </div>
          </div>
        </footer>

      </div>
      <!-- /.content-wrapper -->

    </div>
















    
    <!--這邊結束自由發揮-->
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
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/google-map.js"></script>
  <script src="<%=request.getContextPath()%>/front-end/js/main.js"></script>
  <script>
    $(document).ready(function() {
  

	  $('.list-group-item').click(function(e) {
	    e.preventDefault();
	    $('.list-group-item').removeClass('active');
	    $(this).addClass('active');
	  });
	});
  </script>
  <% 
  java.sql.Date hiredate = null;
  try {
	    hiredate = memberVO.getMemBirth();
   } catch (Exception e) {
	    hiredate = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hiredate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        }); 
        
 </script>
         		</div>
         	</div>
         </div>
    <!-- Bootstrap core JavaScript-->
    <script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="<%=request.getContextPath()%>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>
	
		<script>
		$("#inputfile01").change(function(){
	        if (this.files && this.files[0]) {
	                var reader = new FileReader();
	                
	                reader.onload = function (e) {
	                        $('#previewpic').attr('src', e.target.result);
	                }
	                
	                reader.readAsDataURL(this.files[0]);
	        }
		});
	</script>
  </body>
</html>