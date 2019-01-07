<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%
MemberService memSvc = new MemberService();
	List<MemberVO> list = memSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin - Tables</title>

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
              <a class="dropdown-item" href="addEmployee.jsp">新增員工</a>
              <div class="dropdown-divider"></div>
              <h6 class="dropdown-header">列表:</h6>
              <a class="dropdown-item" href="listAllEmp_1.jsp">員工列表</a>
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
            <li class="breadcrumb-item active">Tables</li>
          </ol>

          <!-- DataTables Example -->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table"></i>
              Data Table Example</div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>會員編號</th>
                      <th>會員姓名</th>
                      <th>會員帳號</th>
                      <th>會員密碼</th>
                      <th>會員生日</th>
                      <th>郵件信箱</th>
                      <th>聯絡電話</th>
                      <th>地址</th>
                      <th>性別</th>
                      <th>註冊日期</th>
                      <th>會員技能</th>
                      <th>會員狀態</th>
                      <th>身分證字號</th>
                      <th>查看</th>
                      
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>會員編號</th>
                      <th>會員姓名</th>
                      <th>會員帳號</th>
                      <th>會員密碼</th>
                      <th>會員生日</th>
                      <th>郵件信箱</th>
                      <th>聯絡電話</th>
                      <th>地址</th>
                      <th>性別</th>
                      <th>註冊日期</th>
                      <th>會員技能</th>
                      <th>會員狀態</th>
                      <th>身分證字號</th>
                      <th>查看</th>
                      
                    </tr>
                  </tfoot>
                  <tbody>
                    
                   <c:forEach var="memberVO" items="${list}">
		<tr>
			<td>${memberVO.memID}</td>
			<td>${memberVO.memName}</td>
			<td>${memberVO.memAcc}</td>
			<td>${memberVO.memPsw}</td>
			<td>${memberVO.memBirth}</td>
			<td>${memberVO.memEmail}</td>
			<td>${memberVO.memTel}</td>			
			<td>${memberVO.memAddr}</td>			
			<td>${memSexMap.get(memberVO.getMemSex())}</td>			
			<td>${memberVO.memReg}</td>			
			<td>${memberVO.memSkill}</td>			
			<td>${memStateMap.get(memberVO.getMemState())}</td>				
			<td>${memberVO.memIDcard}</td>			
					
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/mem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="查看"> 
			     <input type="hidden" name="memID"      value="${memberVO.memID}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--><!-- 目前尚未用到  -->
			     <input type="hidden" name="action"	    value="getOne_For_Display"></FORM>
			</td>
			
		</tr>
	</c:forEach>

                  </tbody>
                </table>
              </div>
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
          </div>

          <p class="small text-center text-muted my-5">
            <em>More table examples coming soon...</em>
          </p>

        </div>
        <!-- /.container-fluid -->

        <!-- Sticky Footer -->
        <footer class="sticky-footer">
          <div class="container my-auto">
            <div class="copyright text-center my-auto">
              <span>Copyright © Your Website 2018</span>
            </div>
          </div>
        </footer>

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

    <!-- Bootstrap core JavaScript-->
    <script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="<%=request.getContextPath()%>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Page level plugin JavaScript-->
    <script src="<%=request.getContextPath()%>/back-end/vendor/datatables/jquery.dataTables.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>

    <!-- Demo scripts for this page-->
    <script src="<%=request.getContextPath()%>/back-end/js/demo/datatables-demo.js"></script>

  </body>

</html>
