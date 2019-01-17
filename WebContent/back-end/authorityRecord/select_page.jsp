<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>員工權限查詢</title>

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
            <li class="breadcrumb-item active">權限查詢</li>
          </ol>

          <!-- Page Content 這邊開始自由發揮-->

<h3>權限查詢:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/authorityRecord/listAllAuthorityRecord.jsp'>List</a> all Emps. <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/authorityRecord/authorityRecord.do" >
        <b>輸入員工編號 (如E0001):</b>
        <input type="text" name="empID">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listAuths_ByEmpID_A">
    </FORM>
  </li>


   <jsp:useBean id="empSvc" scope="page" class="com.employee.model.EmployeeService" />
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/authorityRecord/authorityRecord.do" >
     	<b>修改權限許可</b>
       <b>選擇員工姓名:</b>
       <select size="1" name="empID">
         <c:forEach var="employeeVO" items="${empSvc.all}" > 
          <option value="${employeeVO.empID}">${employeeVO.empName}
         </c:forEach>
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Update">
     </FORM>
  </li>
  
  
  
  <jsp:useBean id="authIDSvc" scope="page" class="com.authority.model.AuthorityService" />
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/authority/authority.do" >
       <b><font color=orange>選擇權限編號:</font></b>
       <select size="1" name="authID">
         <c:forEach var="authorityVO" items="${authIDSvc.all}" > 
          <option value="${authorityVO.authID}">${authorityVO.authName}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="listEmps_ByAuthID_A">
     </FORM>
  </li>
</ul>


<h3>權限管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/authorityRecord/addAuthorityRecord.jsp'>Add</a> a new Mem.</li>
</ul>
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