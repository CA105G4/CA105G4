<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<style>
div {
	font-size: 18px;
}
/* The container */
.container {
	display: block;
	position: relative;
	padding-left: 35px;
	margin-bottom: 12px;
	cursor: pointer;
	font-size: 22px;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

/* Hide the browser's default checkbox */
.container input {
	position: absolute;
	opacity: 0;
	cursor: pointer;
	height: 0;
	width: 0;
}

/* Create a custom checkbox */
.checkmark {
	position: absolute;
	top: 0;
	left: 0;
	height: 25px;
	width: 25px;
	background-color: #eee;
}

/* On mouse-over, add a grey background color */
.container:hover input ~ .checkmark {
	background-color: #ccc;
}

/* When the checkbox is checked, add a blue background */
.container input:checked ~ .checkmark {
	background-color: #2196F3;
}

/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
	content: "";
	position: absolute;
	display: none;
}

/* Show the checkmark when checked */
.container input:checked ~ .checkmark:after {
	display: block;
}

/* Style the checkmark/indicator */
.container .checkmark:after {
	left: 9px;
	top: 5px;
	width: 5px;
	height: 10px;
	border: solid white;
	border-width: 0 3px 3px 0;
	-webkit-transform: rotate(45deg);
	-ms-transform: rotate(45deg);
	transform: rotate(45deg);
}
</style>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>後台-客服Q&A</title>

<!-- Bootstrap core CSS-->
<link
	href="<%=request.getContextPath()%>/back-end/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom fonts for this template-->
<link
	href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Page level plugin CSS-->
<link
	href="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="<%=request.getContextPath()%>/back-end/css/sb-admin.css"
	rel="stylesheet">

</head>

<body id="page-top">

	<!-- Navbar -->

	<jsp:include page="/back-end/navbar.jsp" />
	
	<!-- /Navbar -->
	
	<div id="wrapper">

		<!-- Sidebar -->
		
		<jsp:include page="/back-end/sidebar.jsp" />
		
		<!-- /Sidebar -->

		<div id="content-wrapper">

			<div class="container-fluid">

				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item">
						<a href="<%=request.getContextPath()%>/back-end/room/roomState.jsp">首頁</a>
					</li>
					<li class="breadcrumb-item active">客服</li>
				</ol>

				<!-- CHATROOM -->
				<div class="card mb-3">
					<div class="card-header">
						<i class="fas fa-table"></i> 客服中心
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<br> <br>
							<div
								style="width: 100%; height: 600px; display: flex; justify-content: center; flex-wrap: wrap;">
								<div id="content"
									style="border: 1px solid black; width: 600px; height: 500px; color: #7f3f00; overflow-y: scroll;"></div>
								<div id="userList"
									style="border: 1px solid black; width: 150px; height: 500px; color: #B8860B; text-align: center;"></div>
								<div style="width: 100%;"></div>
								<div style="clear: both;" style="color:#00ff00">
									<div id="msg"
										style="background-color: #FFFFFF; height: 100px; width: 700px; overflow-y: scroll; border: 1px solid black; float: left;"></div>
									<button id="send" class="btn btn-info" onclick="send();"
										style="float: left; margin-top: 60px;">送出</button>
								</div>
							</div>

							<!-- CHATROOM -->
						</div>
					</div>
					<div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
				</div>

			</div>
<!-- 			/.container-fluid -->

			<!-- Sticky Footer -->
<!-- 			<footer class="sticky-footer"> -->
<!-- 				<div class="container my-auto"> -->
<!-- 					<div class="copyright text-center my-auto"> -->
<!-- 						<span>© M.C.P.I.G 2019</span> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</footer> -->

		</div>
		<!-- /.content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script
		src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	<script>
		function read(input){
			  if(input.files && input.files[0]){
			    var imageTagID = input.getAttribute("targetID");
			    var reader = new FileReader();
			    reader.onload = function (e) {
			       var img = document.getElementById(imageTagID);
			       img.setAttribute("src", e.target.result) 
			    }
			    reader.readAsDataURL(input.files[0]);
			  }
			}
	</script>
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
	</script>
	<!-- Bootstrap core JavaScript-->
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Page level plugin JavaScript-->
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/chart.js/Chart.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/datatables/jquery.dataTables.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>

	<!-- Demo scripts for this page-->
	<script
		src="<%=request.getContextPath()%>/back-end/js/demo/datatables-demo.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/js/demo/chart-area-demo.js"></script>

	<!-- 	TinyMCE -->
	<script
		src="<%=request.getContextPath()%>/back-end/article/tinymce/tinymce.js"></script>
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
	<!-- chatroom script -->
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
	<script type="text/javascript">
	
    var ws;
//     var userName = ${sessionScope.username};
	var userName = '456';
//     var url = "ws://localhost:8081/CA105G4/chatSocket?username=${sessionScope.username}";
	var url = "ws://localhost:8081/CA105G4/chatSocket?username=456";
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
            if (result.alert != undefined) {
                $("#content").append(result.alert + "<br/>");
            }
            if (result.names != undefined) {
                $("#userList").html("");
                $(result.names).each(   		
                        function() {  
                        	console.log(result.names);                    
	                            $("#userList").append(
// 	                                    "<input type=checkbox value='"+this+"'/>"
// 	                                            + this + "<br/>"
										"<label class='container'>" + this + "<input type='checkbox' value='" + this + "'/><span class='checkmark'></span></label>"								  
	                             );  
                        });       		
            }
            var inputMessage = document.getElementById("content");
            
            if (result.from != undefined) {
            	console.log("result.from" + result.from);
                $("#content").append(
                        "【" + result.from + "】" + "&nbsp;&nbsp;" + result.date + "：<br/>" + result.sendMsg); 
//                 		+ result.sendMsg + "<br/>"); 
                inputMessage.scrollTop = inputMessage.scrollHeight;
                
            }
//             else{
//             	 $("#content").append(
//             			 "【" + result.from + "】" + "&nbsp;&nbsp;" + result.date + "：<br/>" + result.sendMsg + "<br/>"
//             			 );
//             	 inputMessage.scrollTop = inputMessage.scrollHeight;
//             }

        };
    };
    
    function formatDate(date) {
   	 
      var month = ("0" + (date.getMonth() + 1)).slice(-2);
  	  var day = ("0" + (date.getDate() + 1)).slice(-2);
  	  var hour = ("0" + (date.getHours() + 1)).slice(-2);
  	  var min = ("0" + (date.getMinutes() + 1)).slice(-2);
  	  var sec = ("0" + (date.getSeconds() + 1)).slice(-2);

  	  return hour + ':' + min + ':' + sec + '  ' + month + '/' + day;
  }

    function send() {
    	var inputMessage = document.getElementById("content");
        var ss = $("#userList :checked");
        var to = $('#userList :checked').val();
        console.log('to: ' + to);
        if (to == userName) {
            alert("不能送給自己");
            return;
        }
        var value = tinyMCE.activeEditor.getContent();
        var object = null;
        if (ss.size() == 0) {
            object = {
                msg : value,
                type : 1, 
            };
        } else {
            object = {
                to : to,
                msg : value,
                type : 2, 
            };
            $("#content").append( "【"+ userName +"】" + formatDate(new Date()) +":" + value );
            inputMessage.scrollTop = inputMessage.scrollHeight;
        }
        var json = JSON.stringify(object);
        ws.send(json);
        $("#msg").val(tinyMCE.activeEditor.setContent(''));
    }
</script>
</body>

</html>
