<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.member.model.*"%>
<%@ page import="com.message.model.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" errorPage="error.jsp" %> 
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- 頁面標籤 -->
	<title>翔太山莊</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
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
	
	<style type="text/css">
		iframe{
            /*position: absolute;*/
            width: 100%; height: 500px;
            z-index: -1;
            border-collapse: collapse;
            /*border: 2px #00FFbc solid;*/
            background-color: #FFFFFF;

            /*overflow:hidden;*/
        }
        .abuse-box {
        	width: 300px;
        	height:100px;
			display: none;
			margin: 0 auto;
			position: absolute;
			top:40%;
			left: 40%;
			opacity: 0.8;
        }
        
        .messageReport_box{
        	width: 200px;
        	height: 50px;
			display: none;
			margin: 0 auto;
			top:40%;
			left: 0%;
			opacity: 0.8;
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
					<li class="nav-item active"><a href="<%=request.getContextPath()%>/front-end/member/myAccountMyPage.jsp" class="nav-link">My Account</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/question/frontChat.jsp" class="nav-link">F&Q</a></li>
					<li class="nav-item"><a href="<%=request.getContextPath()%>/front-end/neighbourhood.jsp" class="nav-link">About Us</a></li>
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
  <div class="block-31"  style="position: relative">
    <div class="owl-carousel loop-block-31 ">
      <div class="block-30 item" style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/workExchangebanner.jpg');; min-height: 150px;height: 30vh;" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center">
            <div class="col-md-10">
            </div>
          </div>
        </div>
      </div>
      <div class="block-30 item" style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/banner2.jpg');; min-height: 150px;height: 30vh;" data-stellar-background-ratio="0.5">
        <div class="container">
          <div class="row align-items-center">
            <div class="col-md-10">
              <!-- <span class="subheading-sm">Welcome</span> -->
              <h2 class="heading"></h2>
            </div>
          </div>
        </div>
      </div>
      <div class="block-30 item" style="background-image: url('<%=request.getContextPath()%>/front-end/imagesCustom/EastScenerybanner.jpg');; min-height: 150px;height: 30vh;" data-stellar-background-ratio="0.5">
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

<jsp:useBean id="articleService" scope="page" class="com.article.model.ArticleService" />
  <div class="container">
    <!--這邊開始自由發揮-->

<jsp:useBean id="memberService" scope="page" class="com.member.model.MemberService" />
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-4"> 
					<b class="text-warning" style="font-size: 15px;">文章編號: ${param.artid} &nbsp;&nbsp; 發文者: ${memberService.getOneMem(articleService.getOneArticle(param.artid).memid).memName}</b>	
																						
				</div>
				<div class="col-xs-12 col-sm-8"> 
				
					
					
					<button id="abuse" class="btn btn-warning" style="float: right;">檢舉</button>
					
					<div class="abuse-box" style="float: right;" >
							<table class="table table-striped">
					  			<tbody>				
								    <tr>
								    <td>理由</td>
								    <c:if test="${memberVO != null}">
									    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/report/report.do" >
									      <td><input type="text" name="repreason" size="30" required></td>
										  <input type="hidden" name="artid" value="${param.artid}">
										  
	<!-- 									  以後改SESSION  -->
	
									      <input type="hidden" name="repmemid" value="${memberVO.memID}">
									      <input type="hidden" name="repstate" value="0">								
									      <input type="hidden" name="repdate"  id="f_date1">								      
									      <input type="hidden" name="action" value="insert">
									      <td><input id="repreason" type="submit" value="送出" class="btn btn-info"></td>
									    </FORM>
									 </c:if>
									 <c:if test="${memberVO == null}">
								    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/Login.jsp" >
								    		<td><input type="text" name="repreason" size="30"></td>
								    		<input type="hidden" name="from" value="${pageContext.request.requestURL}?${pageContext.request.queryString}">
								    		<td><input id="repreason" type="submit" value="送出" class="btn btn-info"></td>
								    	</FORM>
								    </c:if>
								    </tr>
								 </tbody>
							</table>
					</div>
				</div>
			</div>
		</div>
		<br>
		<br>
		<br>
		<div class="container">
			<div class="col-xs-12 col-sm-12">
					<jsp:include page="/article/oneArticle">
						<jsp:param name="artid" value="${param.artid}" />
					</jsp:include>
			</div>
		</div>
		<hr>
	<%  	
		Integer artid = new Integer(request.getParameter("artid"));
		MessageService messageService = new MessageService();
		List<MessageVO> list = messageService.getArticleMessage(artid);
  	  	pageContext.setAttribute("list",list);
	%>	
  
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<div class="message" >
							<table class="table table-striped">
					  			<tbody>				
								    <tr>
								   	  <td>留言</td>
								   	  <c:if test="${memberVO != null}">
										   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/message/message.do" >
										      <td><input id="msginput" type="text" name="msgcontent" size="80" required></td>
										      <input type="hidden" name="artid" value="${param.artid}">
										      <!--  以後改SESSION  -->
										      <input type="hidden" name="msgmemid" value="${memberVO.memID}">
										      <input type="hidden" name="msgstate" value="0">								
										      <input type="hidden" name="msgdate"  id="f_date2">								      
										      <input type="hidden" name="action" value="insert">
										      <td><input type="submit" value="送出" class="btn btn-info"></td>
										    </FORM>
								      </c:if>
								      <c:if test="${memberVO == null}">
									    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/Login.jsp" >
									    		<td><input id="msginput" type="text" name="msgcontent" size="80" ></td>
									    		<input type="hidden" name="from" value="${pageContext.request.requestURL}?${pageContext.request.queryString}">
									    		<td><input type="submit" value="送出" class="btn btn-info"></td>
									    	</FORM>
								   	  </c:if>
								    </tr>
								    
								    <c:forEach var="messageVO" items="${list}" > 
          							 <tr>
          								<td>會員: ${memberService.getOneMem(messageVO.msgmemid).memAcc}</td>
<%--           								 - ${messageVO.msgid} --%>
          							  	<td>${messageVO.msgcontent}</td>
          							  	<td>
          							  		<button id="" class="btn btn-warning MsgRepBtn">檢舉</button>
          							  		
          							  		<div class="messageReport_box" style="float: right;" >
												<table class="table">
										  			<tbody>				
													    <tr>
													    <td>理由</td>
													     <c:if test="${memberVO != null}">
														    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/messageReport/messageReport.do" >
														      <td><input type="text" name="mrreason" size="20" required></td>
															  <input type="hidden" name="artid" value="${param.artid}">
															  <input type="hidden" name="msgid" value="${messageVO.msgid}">
														      <input type="hidden" name="mrstate" value="0">																      
														      <input type="hidden" name="action" value="insert">
														      <td><input id="msgrepreason" type="submit" value="送出" class="btn btn-info"></td>
														    </FORM>
														  </c:if>
														  <c:if test="${memberVO == null}">
														    	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/Login.jsp" >
														    		<td><input type="text" name="mrreason" size="20" ></td>
														    		<input type="hidden" name="from" value="${pageContext.request.requestURL}?${pageContext.request.queryString}">
														    		<td><input  id="msgrepreason" type="submit" value="送出" class="btn btn-info"></td>
														    	</FORM>
									   					  </c:if>
													    </tr>
													 </tbody>
												</table>
											</div>
											
          							  	</td>
          							  </tr>
         							</c:forEach>
								</tbody>
							</table>
					</div>					   
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<div class="message" >
						<table class="table table-striped">
					  			<tbody id="test">				
								    <!-- <tr>
								      <td>Username</td>
								      <td><p class="text-left">Left aligned text on all viewport sizes.</p></td>
								    </tr> -->
								 </tbody>
						</table>	
					</div>
				</div>
			</div>
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
  
<!--   datetimepicker -->
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
  	<script>
	        $.datetimepicker.setLocale('zh');
	        $('#f_date1').datetimepicker({
		       theme: '',              //theme: 'dark',
		       timepicker:false,       //timepicker:true,
		       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
		       format:'Y-m-d',         //format:'Y-m-d H:i:s',
			   value: 'new Date()', // value:   new Date(),
	           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	           //startDate:	            '2017/07/10',  // 起始日
	           //minDate:               '-1970-01-01', // 去除今日(不含)之前
	           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	        });
	        $.datetimepicker.setLocale('zh');
	        $('#f_date2').datetimepicker({
		       theme: '',              //theme: 'dark',
		       timepicker:false,       //timepicker:true,
		       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
		       format:'Y-m-d',         //format:'Y-m-d H:i:s',
			   value: 'new Date()', // value:   new Date(),
	           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	           //startDate:	            '2017/07/10',  // 起始日
	           //minDate:               '-1970-01-01', // 去除今日(不含)之前
	           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	        });
			</script>
  
		<script type="text/javascript">
       		$(document).ready(function(){
            	$('#abuse').click(function(){
            		$('.abuse-box').toggle();
            	});
            	$('#repreason').click(function(){
            		$('.abuse-box').hide();
            	});
       		 });
    		
       		$(document).ready(function(){
            	$('.MsgRepBtn').each(function(){
        			$(this).click(function(){
                		$(this).next().toggle();
                	});
        		});

            	$('#msgrepreason').click(function(){
            		$('.messageReport_box').hide();
            	});
       		 });

    </script>
  </body>
</html>