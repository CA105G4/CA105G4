<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.activity.model.*" %>
     <%@ page import="com.activityDetail.model.*" %>
   <%@page import="java.util.*" %>
   
   <jsp:useBean id="listDetail_ByactID" scope="request"  type="java.util.Set<ActivityDetailVO>" />
   <jsp:useBean id="rtSvc" scope="page" class="com.roomType.model.RoomTypeService" />
   
<%--    <% 
//     ActivityDetailVO adVO =(ActivityDetailVO)request.getAttribute("adVO"); 
%> --%>
    
<!DOCTYPE html>
<html >

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>listAllActivityDetail</title>

    <!-- Bootstrap core CSS-->
 

  </head>

  <body id="page-top">

    

    <div id="wrapper">

     

      <div id="content-wrapper">

        <div class="container-fluid">

          <!-- Breadcrumbs-->
          <ol class="breadcrumb">
            <li class="breadcrumb-item">
              <a href="index.html">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">促銷活動明細</li>
          </ol>

          <!-- Page Content 這邊開始自由發揮-->
     		<h3>促銷活動明細 - ListAllActivityDetail</h3>
     		
     		<div class="container">
          		<table class="table table-hover">
          		<thead>
					<tr>
						<th>促銷編號</th>
						<th>房型編號</th>
						<th>房型名稱</th>
						<th>折扣</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="adVO" items="${listDetail_ByactID}" >
					
					<tr>
						<td>${adVO.actID}</td>
						
						<td>${adVO.rtID}</td>
						
						<td>
							<c:forEach var="rtVO" items="${rtSvc.all}">
								<c:if test="${rtVO.rtID==adVO.rtID}">
									${rtVO.rtName}		
								</c:if>
							</c:forEach>
							
						</td>
						<td>${adVO.discount}</td>
					</tr>
					
					
					
					</c:forEach>
				</tbody>
				
				
          		</table>
          	</div>
        
          <!-- Page Content 這邊開始自由發揮結束-->
        </div>
        <!-- /.container-fluid -->

        <!-- Sticky Footer -->
      
    </div>

      </div>
      <!-- /.content-wrapper -->
  
    <!-- /#wrapper -->

    <!-- Scroll to Top Button-->
  
    <!-- Logout Modal-->
   





    <!-- Bootstrap core JavaScript-->
   

  </body>

</html>