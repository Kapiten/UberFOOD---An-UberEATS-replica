<%-- 
    Document   : _menu
    Created on : 27 Jul 2017, 2:01:35 PM
    Author     : StrettO


<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
 
<%-- 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>   
 --%>
 
<div class="menu-container">
  
   <a href="${pageContext.request.contextPath}/">Home</a>
   |
   <a href="${pageContext.request.contextPath}/productList">
      Product List
   </a>
   |
   <a href="${pageContext.request.contextPath}/shoppingCart">
      My Cart
   </a>
   |
   <security:authorize  access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
     <a href="${pageContext.request.contextPath}/orderList">
         Order List
     </a>
     |
   </security:authorize>
   
   <security:authorize  access="hasRole('ROLE_MANAGER')">
         <a href="${pageContext.request.contextPath}/product">
                        Create Product
         </a>
         |
   </security:authorize>
  
</div>
