	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.employee.model.*"%>
    <%EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");%>
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
                      <th>員工編號</th>
                      <th>所屬分店</th>
                      <th>員工姓名</th>
                      <th>員工職稱</th>
                      <th>連絡電話</th>
                      <th>員工帳號狀態</th>
                      <th>員工帳號</th>
                      <th>員工密碼</th>

                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>員工編號</th>
                      <th>所屬分店</th>
                      <th>員工姓名</th>
                      <th>員工職稱</th>
                      <th>連絡電話</th>
                      <th>員工帳號狀態</th>
                      <th>員工帳號</th>
                      <th>員工密碼</th>
                      
                    </tr>
                  </tfoot>
                  <tbody>
                    <tr>
						<td><%=employeeVO.getEmpID()%></td>
						<td><%=employeeVO.getBraID()%></td>
						<td><%=employeeVO.getEmpName()%></td>
						<td><%=employeeVO.getEmpJob()%></td>
						<td><%=employeeVO.getEmpTel()%></td>
						<td>${empStateMap.get(employeeVO.getEmpState())}</td>
						<td><%=employeeVO.getEmpAcc()%></td>
						<td><%=employeeVO.getEmpPsw()%></td>
		
					</tr>
                   
                  </tbody>
                </table>
              </div>
            </div>
            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
          </div>
			<li><a href='<%=request.getContextPath()%>/back-end/employee/listAllEmp_1.jsp'>返回列表</a> <br><br></li>
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
