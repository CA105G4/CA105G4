<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.authorityRecord.model.*"%>
<jsp:useBean id="empSvc" scope="page" class="com.employee.model.EmployeeService" />
<jsp:useBean id="listEmps_ByAuthID" scope="request" type="java.util.Set<AuthorityRecordVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="authIDSvc" scope="page" class="com.authority.model.AuthorityService" />


    
<!DOCTYPE html>
<html lang="en">

  <head>

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
                    
                      <th>員工姓名</th>
                      <th>權限編號</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                     
                      <th>員工姓名</th>
                       <th>權限編號</th>
                      
                    </tr>
                  </tfoot>
                  <tbody>
	<c:forEach var="authorityRecordVO" items="${listEmps_ByAuthID}" >
		<tr>
			<td>${empSvc.getOneEmp(authorityRecordVO.empID).getEmpName()}</td>
			
			<td><c:forEach var="authorityVO" items="${authIDSvc.all}">
                    <c:if test="${authorityRecordVO.authID==authorityVO.authID}">
	                    ${authorityVO.authID}【<font color=orange>${authorityVO.authName}</font> 】
                    </c:if>
                </c:forEach>	
		
			
			
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
