<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- 頁面標籤 -->
    <title>CA105G4-翔太山莊</title>
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
          <li class="nav-item"><a href="indexCustom.html" class="nav-link">Home</a></li>
          <li class="nav-item"><a href="roomsC.html" class="nav-link">Rooms</a></li>
          <li class="nav-item"><a href="Stay&Help.html" class="nav-link">Stay and Help</a></li>
          <li class="nav-item active"><a href="Coupon.html" class="nav-link">Coupon</a></li>
          <li class="nav-item"><a href="Neighbourhood.html" class="nav-link">Neighbourhood</a></li>
          <li class="nav-item"><a href="account.html" class="nav-link">My Account</a></li>
          <li class="nav-item"><a href="FAQ.html" class="nav-link">FAQ</a></li>
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
  <div class="container" >
    <!--這邊開始自由發揮-->

      <br>
      <br>
  <div style=" display: block; border-radius: 20%; background-color: #AAAAAA;" >  
      <h3><div style="border-radius: 50%; text-align: center; background-color: #0088A8; display:block; width: 300px; margin: 0px auto; color: #FFFFFF">客服中心</div></h3>
      <div>
        <div style="width: 100%; height: 600px; display: flex; justify-content: center; flex-wrap: wrap;">
          <div id="content" style="border-radius: 8px ;border: 2px solid black; width: 700px; height: 500px;  color: #7f3f00; overflow-y:auto ; background-color: #FFFFFF;"></div> 
          <div style="width: 100%; "></div>
          <div style=" width: 700px; height: 110px;"> 
            <div style="clear: both;" style="color:#00ff00"> 
                <div id="msg" style="margin-left:30px; background-color:#FFFFFF; width: 590px; height: 100px; overflow-y:auto; border: 1px solid black; float:left;"></div>
                <button id="send" class="btn btn-info rounded" onclick="send();" style="float:left; margin-top: 60px;">送出</button> 
            </div>
          </div>
        </div> 
  </div>

    <!--這邊結束自由發揮-->
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
	        });
	</script>
  
  <!-- 	TinyMCE -->
	<script src="<%=request.getContextPath()%>/back-end/article/tinymce/tinymce.js"></script>
	<script>
		tinymce.init({
		  selector:'#msg',
		  plugins: 'image code link imagetools codesample emoticons textcolor table preview',
		  toolbar1: 'undo redo |  bold italic | alignleft aligncenter alignright alignjustify | forecolor backcolor emoticons  | bullist numlist outdent indent | link image code |print preview media | codesample help  | insert styleselect ',
		  menubar: false,
	      inline: true,
	      statusbar: false,
		  image_title: true,
		  paste_data_images: true,
		  automatic_uploads: true,
		  file_picker_types: 'image', 
		  file_picker_callback: function(cb, value, meta) {
		    var input = document.createElement('input');
		    input.setAttribute('type', 'file');
		    input.setAttribute('accept', 'image/*');
		    input.onchange = function() {
		      var file = this.files[0];    
		      var reader = new FileReader();
		      reader.onload = function () {
		        var id = 'blobid' + (new Date()).getTime();
		        var blobCache =  tinymce.activeEditor.editorUpload.blobCache;
		        var base64 = reader.result.split(',')[1];
		        var blobInfo = blobCache.create(id, file, base64);
		        blobCache.add(blobInfo);
		        cb(blobInfo.blobUri(), { title: file.name });
		      };
		      reader.readAsDataURL(file);
		    };
		    
		    input.click();
		  },
		});
	</script>
		
<!-- 	chatRoom -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript">
	
    var ws;
	var userName = '${memberVO.memName}';
	var url = "ws://localhost:8081/CA105G4/question?username=${memberVO.memName}";
    window.onload = function() {
        if ('WebSocket' in window) {
            ws = new WebSocket(url);
        } else if ('MozWebSocket' in window) {
            ws = new MozWebSocket(url);
        } else {
            alert('WebSocket is not supported by this browser.');
            return;
        }

        ws.onmessage = function(event) {
            eval("var result=" + event.data);
			console.log(event.data);
            if (result.names != undefined) {
                $("#userList").html("");
                $(result.names).each(   		
                        function() {  
                        	console.log(result.names);
	                            $("#userList").append(
	                                    "<input type=checkbox value='"+this+"'/>"
	                                            + this + "<br/>"
	                             );  
                        });       		
            }
            var inputMessage = document.getElementById("content");
            
            if (result.from != undefined) {
            	console.log("result.from" + result.from);
                $("#content").append(
                        "【" + result.from + "】" + "&nbsp;&nbsp;" + result.date + "：<br/>" + result.sendMsg); 
                inputMessage.scrollTop = inputMessage.scrollHeight;
                
            }
        }
    };
    
    function formatDate(date) {
    	 
		  var month = ("0" + (date.getMonth() + 1)).slice(-2);
	  	  var day = ("0" + date.getDate()).slice(-2);
	  	  var hour = ("0" + date.getHours()).slice(-2);
	  	  var min = ("0" + date.getMinutes()).slice(-2);
	  	  var sec = ("0" + date.getSeconds()).slice(-2);

    	  return hour + ':' + min + ':' + sec + '  ' + month + '/' + day;
    }

    function send() {
	    var value = tinyMCE.activeEditor.getContent();
	   	if( value.length != 0){
	    	var inputMessage = document.getElementById("content");
	        var ss = $("#userList :checked");
	        var to = "商商";
	        var date = new Date();
	        console.log('to: ' + to);
	        if (to == userName) {
	            alert("不能送給自己");
	            return;
	        }
	        
	        var object = null;
	        	object = {
	        	from : userName,
	            to : to,
	            msg : value,
	            type : 2, 
	        }
	        $("#content").append( "【"+ userName +"】" + formatDate(new Date()) +":" + value + "<br/>");
	        inputMessage.scrollTop = inputMessage.scrollHeight;
	        var json = JSON.stringify(object);
	        ws.send(json);
	        $("#msg").val(tinyMCE.activeEditor.setContent(''));
	   	}else{
			alert('請輸入訊息');
	}
   	
    }
</script>
  </body>
</html>