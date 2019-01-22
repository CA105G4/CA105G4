<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.authority.model.*"%>
<%@ page import="com.authorityRecord.model.*"%>

<%
	AuthorityRecordVO authorityRecordVO = (AuthorityRecordVO) request.getAttribute("authorityRecordVO");
	
	System.out.println("update = " + authorityRecordVO.getEmpID());

	AuthorityService authSvc = new AuthorityService();
	List<AuthorityVO> list = authSvc.getAll(); 
	
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>修改員工權限</title>

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
            <li class="breadcrumb-item active">修改員工權限</li>
          </ol>

          <!-- Page Content 這邊開始自由發揮-->
          <h1>權限許可更新</h1>

<%-- 錯誤表列 --%>
<div align="center">
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
</div>	

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/authorityRecord/authorityRecord.do" name="form1">
<div align="center">
	<table>
		<tr>
			<td rowspan="3">
				<img src="<%=request.getContextPath()%>/employee/empImg.do?empID=${authorityRecordVO.empID}"
										style="border-radius: 50%; width: 300px;">
			</td>
			<jsp:useBean id="empSvc" scope="page" class="com.employee.model.EmployeeService" />
			<td>員工編號:</td>
			<td>${authorityRecordVO.empID} - ${empSvc.getOneEmp(authorityRecordVO.empID).empName}</td>
		</tr>
		
		<tr><td></td></tr>
	
		<tr>
			<td>權限選擇:</td>
			<td>
				<c:forEach var="authVO" items="${list}">
					<input type="checkbox" name="authID" value="${authVO.authID}">${authVO.authName}<br>
				</c:forEach>
			</td>
		</tr>
	</table>
</div>	
	<br>
	<div align="center">
		<input type="hidden" name="empID" value="<%=authorityRecordVO.getEmpID()%>">
		<input type="hidden" name="action" value="update">
		<input type="submit" value="送出" class="btn btn-info">
	</div>	
	
	
	</FORM>

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
