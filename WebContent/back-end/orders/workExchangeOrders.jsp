<%@page import="com.employee.model.EmployeeVO"%>
<%@page import="com.orders.model.OrdersVO"%>
<%@page import="java.util.*"%>
<%@page import="com.orders.model.OrdersService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	EmployeeVO empVO = (EmployeeVO)session.getAttribute("employeeVO");
	OrdersService ordSvc = new OrdersService();
	List<OrdersVO> list = ordSvc.findByordType2(empVO.getBraID());
	pageContext.setAttribute("list", list);
	System.out.println("workExchangeOrders = " + empVO.getBraID());
%>

<%-- <jsp:useBean id="ordSvc"  scope="page" class="com.orders.model.OrdersService" /> --%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>打工換宿訂單</title>

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

<!-- datepicker-->
<link href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" 
	rel="stylesheet" type="text/css" />




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
					<li class="breadcrumb-item active">打工換宿訂單</li>
				</ol>

				<!-- Page Content 這邊開始自由發揮-->
				<h1>訂單列表</h1>
				<hr>
				<div class="container-fluid" align="right">
					<button type="button" class="btn btn-info">
						<a href='<%=request.getContextPath()%>/back-end/orders/addorders.jsp' style="color:#fff">新增訂單</a>
					</button>
				</div>
				<div class="container-fluid">
					<h2>打工換宿訂單列表</h2>

						<!--開始自由發揮-->
						<table class="table table-bordered table-striped table-hover">
							<tr>
								<th>訂單編號</th>
								<th>會員姓名</th>
								<th>分店</th>
								<th>房間數</th>
								<th>訂單種類</th>
								<th>人數</th>
								<th>總額</th>
								<th>訂金</th>
								<th>付款方式</th>
								<th>訂單狀態</th>
								<th>下訂單時間</th>
								<th>修改</th>
								<th>取消訂單</th>
							</tr>
							<%@ include file="page1.file" %> 
							<c:forEach var="ordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								
								<tr>
									<td><a href="<%=request.getContextPath()%>/orders/orders.do?ordID=${ordVO.ordID}&action=getAll_OrderDetail&requestURL=<%=request.getServletPath()%>&whichPage=<%=whichPage%>">${ordVO.ordID}</a></td>
							<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
									<td>${memSvc.getOneMem(ordVO.memID).getMemName()}</td>
							<jsp:useBean id="braSvc" scope="page" class="com.branch.model.BranchService" />
									<td>${braSvc.getOneByID(ordVO.braID).getBraName()}</td>
									<td>${ordVO.numOfRoom}</td>
									<td>${ordTypeMap.get(ordVO.getOrdType())}</td> 
									<td>${ordVO.numOfGuest}</td>
									<td>${ordVO.amount}</td>
									<td>${ordVO.bond}</td>
									<td>${paymentMap.get(ordVO.getPayment())}</td>
									<td>${ordStateMap.get(ordVO.getOrdState())}</td>
									<td>${ordVO.ordTime}</td>
									<td>
									  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" style="margin-bottom: 0px;">
									     <input type="submit" value="修改" class="btn btn-info">
									     <input type="hidden" name="ordID"  value="${ordVO.ordID}">
									     <input type="hidden" name="action"	value="GetOneUpdate"></FORM>
									</td>
									<td>
									  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" style="margin-bottom: 0px;">
									     <input type="submit" value="取消訂單" ${ (ordVO.ordState==2) || (ordVO.ordState==3) ? "class='btn btn-secondary' disabled" : "class='btn btn-info'" }>
									     <input type="hidden" name="ordID"  value="${ordVO.ordID}">
									     <input type="hidden" name="requestURL"  value="<%=request.getServletPath()%>">
									     <input type="hidden" name="action" value="CancelOrders"></FORM>
									</td>
								</tr>
							</c:forEach>
						</table>
						<%@ include file="page2.file" %>

					<!-- 結束自由發揮-->
				</div>
	
				
				<div class="container-fluid" align="right">
					<button type="button" class="btn btn-info">
						<a href='<%=request.getContextPath()%>/back-end/orders/select_orders_page.jsp' style="color:#fff">返回</a>
					</button>
				</div>
				<!-- Page Content 這邊開始自由發揮結束-->
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
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Bootstrap core JavaScript-->
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Modal -->
<%-- ${openModal!=null} --%>
<c:if test="${openModal!=null}">

<div id="myModal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title"></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="listOneOrder.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
     </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

        <script>
     		$('#myModal').modal({
 				 show: true
			})
        </script> 
 </c:if>	
<!-- Modal -->

	<!-- Core plugin JavaScript-->
	<script
		src="<%=request.getContextPath()%>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="<%=request.getContextPath()%>/back-end/js/sb-admin.min.js"></script>

	<!-- datetimepicker JavaScript-->
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	
</body>

</html>
