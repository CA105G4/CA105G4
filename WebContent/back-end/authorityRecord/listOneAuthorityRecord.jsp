<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.authorityRecord.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%
	AuthorityRecordVO authorityRecordVO = (AuthorityRecordVO) request.getAttribute("authorityRecordVO");
%>

 <jsp:useBean id="empSvc" scope="page" class="com.employee.model.EmployeeService" />

<!DOCTYPE html>
<html lang="en">


<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>員工的權限</title>

<!-- Bootstrap core CSS-->
<link href="<%=request.getContextPath()%>/back-end/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template-->
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

<!-- Page level plugin CSS-->
<link href="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="<%=request.getContextPath()%>/back-end/css/sb-admin.css" rel="stylesheet">

<style type="text/css">
.grid-container {
	grid-template-columns: repeat(3, 33.33%);
	grid-gap: 10px;
	margin-top: 5%;
}
</style>

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
            <li class="breadcrumb-item active"> 員工的權限 </li>
          </ol>
			
          <!-- DataTables Example -->
 <div  align="center">
          <div class="card mb-3" style="width: 1200px">
            <div class="card-header">
              <i class="fas fa-table"></i> ${empSvc.getOneEmp(empID).empName} 員工的權限</div>
            <div class="card-body">
            		<img src="<%=request.getContextPath()%>/employee/empImg.do?empID=${empID}"
										style="border-radius: 50%; width: 300px;">
            	<div class="grid-container" style="display:grid">
	                <jsp:useBean id="authSvc" scope="page" class="com.authority.model.AuthorityService" />
						<c:forEach var="authorityRecordVO" items="${listEmps_ByAuthID}" >
							<div>
										<i class="fa fa-tags"></i>
										</a> ${authSvc.getOneAuth(authorityRecordVO.authID).authName}
		
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/authorityRecord/authorityRecord.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="刪除" class="btn btn-info"> <input type="hidden"
												name="empID" value="${authorityRecordVO.empID}"> <input
												type="hidden" name="action" value="delete">
										</FORM>
							</div>
						</c:forEach>
				</div>
            </div>
            <div class="card-footer small text-muted"></div>
          </div>
</div>          
			<li><a href='<%=request.getContextPath()%>/back-end/authorityRecord/select_page.jsp'>返回</a> <br><br></li>

        </div>
        <!-- /.container-fluid -->

        <!-- Sticky Footer -->
        <footer class="sticky-footer">
          <div class="container my-auto">
            <div class="copyright text-center my-auto">
              <span>© M.C.P.I.G 2019</span>
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
