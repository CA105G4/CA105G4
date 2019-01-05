<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<style>
		div{
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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin - Dashboard</title>

    <!-- Bootstrap core CSS-->
    <link href="<%=request.getContextPath()%>/back-end/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%=request.getContextPath()%>/back-end/css/sb-admin.css" rel="stylesheet">

  </head>

  <body id="page-top">

    <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

      <a class="navbar-brand mr-1" href="index.html">M.C.P.I.G villa</a>

      <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
        <i class="fas fa-bars"></i>
      </button>

      <!-- Navbar Search -->
      <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
        <div class="input-group">
          <input type="text" class="form-control" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
          <div class="input-group-append">
            <button class="btn btn-primary" type="button">
              <i class="fas fa-search"></i>
            </button>
          </div>
        </div>
      </form>

      
      <!-- Navbar -->
      <ul class="navbar-nav ml-auto ml-md-0">
        <li class="nav-item dropdown no-arrow mx-1">
          <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-bell fa-fw"></i>
            <span class="badge badge-danger">9+</span>
          </a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="alertsDropdown">
            <a class="dropdown-item" href="#">Action</a>
            <a class="dropdown-item" href="#">Another action</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">Something else here</a>
          </div>
        </li>
        <li class="nav-item dropdown no-arrow mx-1">
          <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-envelope fa-fw"></i>
            <span class="badge badge-danger">7</span>
          </a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="messagesDropdown">
            <a class="dropdown-item" href="#">Action</a>
            <a class="dropdown-item" href="#">Another action</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">Something else here</a>
          </div>
        </li>
        <li class="nav-item dropdown no-arrow">
          <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-user-circle fa-fw"></i>
          </a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
            <a class="dropdown-item" href="#">Settings</a>
            <a class="dropdown-item" href="#">Activity Log</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Logout</a>
          </div>
        </li>
      </ul>

    </nav>

    <div id="wrapper">


      <!-- Sidebar -->
      <ul class="sidebar navbar-nav">

        <!-- profile pic Gina -->
        <div class="profile_pic">
          <img src="https://api.fnkr.net/testimg/1200x1200/00CED1/FFF/?text=img+placeholder" class="img-circle profile_img">
        </div>

        <li class="nav-item active">
          <a class="nav-link" href="index.html">
            <i class="fa fa-home"></i>
            <span>當日房況一覽</span>
          </a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-edit"></i>
            <span>Check In/Out</span>
          </a>
          <div class="dropdown-menu" aria-labelledby="pagesDropdown">
            <h6 class="dropdown-header">入住:</h6>
            <a class="dropdown-item" href="blank.html">Check In</a>
            <a class="dropdown-item" href="blank.html">新增會員</a>
            <a class="dropdown-item" href="blank.html">新增訂單</a>
            <div class="dropdown-divider"></div>
            <h6 class="dropdown-header">退房:</h6>
            <a class="dropdown-item" href="blank.html">Check Out</a>
          </div>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown2"role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-edit"></i>
            <span>訂單管理</span>
          </a>
          <div class="dropdown-menu" aria-labelledby="pagesDropdown2">
            <h6 class="dropdown-header">新增/查詢:</h6>
            <a class="dropdown-item" href="blank.html">查詢訂單</a>
            <a class="dropdown-item" href="blank.html">新增訂單</a>
            <div class="dropdown-divider"></div>
            <h6 class="dropdown-header">列表:</h6>
            <a class="dropdown-item" href="tables.html">一般訂單列表</a>
            <a class="dropdown-item" href="tables.html">打工換宿訂單列表</a>
            <a class="dropdown-item" href="tables.html">退訂訂單列表</a>
          </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3"role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fa fa-edit"></i>
              <span>打工需求管理</span>
            </a>
            <div class="dropdown-menu" aria-labelledby="pagesDropdown3">
              <h6 class="dropdown-header">新增/查詢:</h6>
              <a class="dropdown-item" href="blank.html">新增需求</a>
              <a class="dropdown-item" href="blank.html">查詢需求</a>
              <div class="dropdown-divider"></div>
              <h6 class="dropdown-header">列表:</h6>
              <a class="dropdown-item" href="tables.html">打工需求列表</a>
            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3"role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fa fa-edit"></i>
              <span>房型管理</span>
            </a>
            <div class="dropdown-menu" aria-labelledby="pagesDropdown3">
              <h6 class="dropdown-header">新增:</h6>
              <a class="dropdown-item" href="blank.html">新增房型</a>
              <div class="dropdown-divider"></div>
              <h6 class="dropdown-header">列表:</h6>
              <a class="dropdown-item" href="tables.html">房型列表</a>
            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3"role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fa fa-edit"></i>
              <span>房間管理</span>
            </a>
            <div class="dropdown-menu" aria-labelledby="pagesDropdown3">
              <h6 class="dropdown-header">新增/查詢:</h6>
              <a class="dropdown-item" href="blank.html">新增房間</a>
              <a class="dropdown-item" href="blank.html">查詢房間</a>
              <div class="dropdown-divider"></div>
              <h6 class="dropdown-header">房況一覽</h6>
              <a class="dropdown-item" href="index.html">當日</a>
              <a class="dropdown-item" href="blank.html">選擇日期</a>
            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3"role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fa fa-edit"></i>
              <span>會員管理</span>
            </a>
            <div class="dropdown-menu" aria-labelledby="pagesDropdown3">
              <h6 class="dropdown-header">新增/查詢:</h6>
              <a class="dropdown-item" href="blank.html">新增會員</a>
              <a class="dropdown-item" href="blank.html">查詢會員</a>
              <div class="dropdown-divider"></div>
              <h6 class="dropdown-header">列表:</h6>
              <a class="dropdown-item" href="tables.html">會員列表</a>
            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3"role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fa fa-edit"></i>
              <span>員工管理</span>
            </a>
            <div class="dropdown-menu" aria-labelledby="pagesDropdown3">
              <h6 class="dropdown-header">新增:</h6>
              <a class="dropdown-item" href="blank.html">新增員工</a>
              <div class="dropdown-divider"></div>
              <h6 class="dropdown-header">列表:</h6>
              <a class="dropdown-item" href="tables.html">員工列表</a>
            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3"role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fa fa-edit"></i>
              <span>前台網頁管理</span>
            </a>
            <div class="dropdown-menu" aria-labelledby="pagesDropdown3">
              <h6 class="dropdown-header">輪播看板:</h6>
              <a class="dropdown-item" href="blank.html">新增</a>
              <a class="dropdown-item" href="table.html">列表</a>
              <div class="dropdown-divider"></div>
              <h6 class="dropdown-header">廣告彈跳:</h6>
              <a class="dropdown-item" href="blank.html">新增</a>
              <a class="dropdown-item" href="table.html">列表</a>
            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3"role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fa fa-edit"></i>
              <span>促銷活動管理</span>
            </a>
            <div class="dropdown-menu" aria-labelledby="pagesDropdown3">
              <h6 class="dropdown-header">新增:</h6>
              <a class="dropdown-item" href="blank.html">新增活動</a>
              <div class="dropdown-divider"></div>
              <h6 class="dropdown-header">列表:</h6>
              <a class="dropdown-item" href="table.html">促銷活動列表</a>
            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3"role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fa fa-edit"></i>
              <span>優惠劵管理</span>
            </a>
            <div class="dropdown-menu" aria-labelledby="pagesDropdown3">
              <h6 class="dropdown-header">新增:</h6>
              <a class="dropdown-item" href="blank.html">新增優惠劵</a>
              <div class="dropdown-divider"></div>
              <h6 class="dropdown-header">列表:</h6>
              <a class="dropdown-item" href="table.html">優惠劵列表</a>
            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3"role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fa fa-edit"></i>
              <span>檢舉管理</span>
            </a>
            <div class="dropdown-menu" aria-labelledby="pagesDropdown3">
              <h6 class="dropdown-header">文章檢舉:</h6>
              <a class="dropdown-item" href="blank.html">被檢舉文章列表</a>
              <div class="dropdown-divider"></div>
              <h6 class="dropdown-header">留言檢舉:</h6>
              <a class="dropdown-item" href="table.html">被檢舉留言列表</a>
            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown3"role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fa fa-edit"></i>
              <span>分店管理</span>
            </a>
            <div class="dropdown-menu" aria-labelledby="pagesDropdown3">
              <h6 class="dropdown-header">新增:</h6>
              <a class="dropdown-item" href="blank.html">新增分店</a>
              <div class="dropdown-divider"></div>
              <h6 class="dropdown-header">列表:</h6>
              <a class="dropdown-item" href="table.html">分店列表</a>
            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link" href="blank.html">
              <i class="fa fa-edit"></i>
              <span>客服Q&A</span>
            </a>
        </li>
      </ul>

      <div id="content-wrapper">

        <div class="container-fluid">

          <!-- Breadcrumbs-->
          <ol class="breadcrumb">
            <li class="breadcrumb-item">
              <a href="#">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">Overview</li>
          </ol>

          <!-- Icon Cards  不用即可刪除-->
          <div class="row">
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-primary o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-user-clock"></i>
                  </div>
                  <div class="mr-5">共 xx 間 保留!</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="#">
                  <span class="float-left">保留</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-warning o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-stopwatch"></i>
                  </div>
                  <div class="mr-5">共 xx 間 打掃中!</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="#">
                  <span class="float-left">打掃</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-success o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-edit"></i>
                  </div>
                  <div class="mr-5">共 xx 間 空房!</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="#">
                  <span class="float-left">空房</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-danger o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-user-check"></i>
                  </div>
                  <div class="mr-5">共 xx 間 入住!</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="#">
                  <span class="float-left">入住</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
          </div>
<!-- CHATROOM -->
		<div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table"></i>
              客服中心</div>
            <div class="card-body">
              <div class="table-responsive">
              <br>
              <br>
		<div style="width: 100%; height: 600px; display: flex; justify-content: center; flex-wrap: wrap;">
			<div id="content" style="border: 1px solid black; width: 600px; height: 500px;  color: #7f3f00; overflow-y:scroll;"></div> 
			<div id="userList" style="border: 1px solid black; width: 150px; height: 500px; color: #B8860B; text-align: center;"></div> 
			<div style="width: 100%; "></div> 
			<div style="clear: both;" style="color:#00ff00"> 
		        <div id="msg" style=" background-color:#FFFFFF; height: 100px; width: 700px; overflow-y:scroll; border: 1px solid black; float:left;"></div>
		        <button id="send" class="btn btn-info" onclick="send();" style="float:left; margin-top: 60px;">送出</button> 
		    </div>
        </div> 
             		
<!-- CHATROOM -->
              </div>
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
          </div>

        </div>
        <!-- /.container-fluid -->

        <!-- Sticky Footer -->
<!--         <footer class="sticky-footer"> -->
<!--           <div class="container my-auto"> -->
<!--             <div class="copyright text-center my-auto"> -->
<!--               <span>Copyright © Your Website 2018</span> -->
<!--             </div> -->
<!--           </div> -->
<!--         </footer> -->

      </div>
      <!-- /.content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="login.html">Logout</a>
          </div>
        </div>
      </div>
    </div>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
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
    <script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="<%=request.getContextPath()%>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Page level plugin JavaScript-->
    <script src="<%=request.getContextPath()%>/back-end/vendor/chart.js/Chart.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/vendor/datatables/jquery.dataTables.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>

    <!-- Demo scripts for this page-->
    <script src="<%=request.getContextPath()%>/back-end/js/demo/datatables-demo.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/js/demo/chart-area-demo.js"></script>
	
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
<!-- chatroom script -->
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
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
